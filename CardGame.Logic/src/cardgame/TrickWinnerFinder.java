/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.trickValueCalculators.TrickValueCalculator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author KG Studia
 */
public class TrickWinnerFinder {

    Players players;
    private final TrickValueCalculator calculator;
    private final int WINNER_TRICK_VALUE = 17;

    public TrickWinnerFinder(Players players, TrickValueCalculator calculator) {
        this.players = players;
        this.calculator = calculator;
    }

    private class TrickValue_IsLeader {

        public int value;
        public boolean isLeader;

        public TrickValue_IsLeader(int value, boolean isLeader) {
            this.value = value;
            this.isLeader = isLeader;
        }
    }

    public Player find(Trick trick) {
        if (trick == null) {
            throw new NullPointerException("trick");
        }
        if (!trick.isFull()) {
            throw new IllegalArgumentException("trick should be full");
        }

        var leaderIndex = players.getLeader().getLastDuplicatedCardTrickIndex();
        var nonLeaderIndex = players.getNonLeader().getLastDuplicatedCardTrickIndex();

        if (leaderIndex.isPresent() || nonLeaderIndex.isPresent()) 
        {
            return (leaderIndex.orElse(-1) < nonLeaderIndex.orElse(-1)) 
                    ? players.getLeader() : players.getNonLeader();
        }
        var limits = Stream.iterate(1, v -> v + 1).limit(Trick.MAX_SIZE);

        //Nie rozumiem czym jest CollectionFactory przy probie collect to Collection
        var allSubTricks = limits.map(maxSize -> trick.getCards().stream().limit(maxSize)
                .collect(Collectors.toList()));
        // .collect(Collectors.toList()));

        var allSubTricksValues = allSubTricks.map(subTrick
                -> new TrickValue_IsLeader(calculator.calculate(subTrick),
                        trick.isOwnedByLeader(subTrick.get(subTrick.size() - 1))
                ));

        var chooseWinner = allSubTricksValues.filter(pair -> pair.value > WINNER_TRICK_VALUE).findFirst();

        //   var tmp = chooseWinner.map( pair -> pair.isLeader ? otherPlayer : leader);
        return chooseWinner
                .map(pair -> pair.isLeader ? players.getNonLeader()
                : players.getLeader())
                .orElse(players.getNonLeader());
//        if(chooseWinner.isEmpty())
//        {
//            return otherPlayer;
//        }
//        else
//        {
//            return chooseWinner.get().isLeader ? otherPlayer : leader;
//        }
    }
}
