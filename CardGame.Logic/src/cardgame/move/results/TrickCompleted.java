/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.move.results;

import cardgame.Player;
import cardgame.Players;
import cardgame.SingleDeal;
import cardgame.Trick;
import cardgame.TrickWinnerFinder;
import cardgame.cardsCollection.CardsCollection;
import cardgame.trickValueCalculators.TrickValueCalculator;
import cardgame.trickValueCalculators.TrickValueCalculatorImplementation2;


public class TrickCompleted extends OkResult 
{   
    private final Trick currentTrick;
    private Player winner;
    
    public TrickCompleted(Trick currentTrick) {
        super(ResultType.TrickCompleted);
        this.currentTrick = currentTrick;
        winner = null;
    }
    
    @Override
    public void handle(SingleDeal.SingleDealProxy singleDealProxy) 
    {
        //CardsCollection d = new Trick();
        
        final TrickValueCalculator calculator = new TrickValueCalculatorImplementation2();
        TrickWinnerFinder finder = new TrickWinnerFinder(singleDealProxy.getPlayers(), calculator);
        winner = finder.find(currentTrick);
        winner.addPoints(calculator.calculate(currentTrick.getCardsAsList()));
        
        Players players = singleDealProxy.getPlayers();
        players.switchLeader(winner);
          
        
        singleDealProxy.restorePlayerCards(winner);
        singleDealProxy.restorePlayerCards(players.getOtherPlayer(winner));
        players.resetSuitDuplicatedIndex();
    }

    public Player getWinner() 
    {
        if(winner==null)
        {
            throw new IllegalStateException("Winner is not known yet. "
                    + "More information after using handle");
        }
        return winner;
    }
    
    
}
