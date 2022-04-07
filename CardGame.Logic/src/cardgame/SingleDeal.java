/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.move.Move;
import cardgame.cardsCollection.CardTransferer;
import cardgame.cardsCollection.Deck;
import cardgame.move.results.BaseResult;
import cardgame.move.results.DealFinished;
import cardgame.move.results.InvalidCardOwner;
import cardgame.move.results.ResultType;
import java.util.Optional;

/**
 *
 * @author KG Studia
 */
public class SingleDeal 
{
    //private Player currentTrickLeader, currentTrickNonLeader;
    private final Players players;
    private Player activePlayer; 
    private Deck deck;
    private Tricks tricks;
    private boolean isFinished = true;
    public static final int FULL_HAND_SIZE = 12;
            
    public static class SingleDealProxy
    {
        private final SingleDeal sd;
        public SingleDealProxy(SingleDeal sd)
        {
            this.sd = sd;            
        }
        public void swapActivePlayer()
        {
            sd.swapActivePlayer();
        }
        public Players getPlayers()
        {
            return sd.players;
        }
        public void restorePlayerCards(Player player)
        {
            if(player==null)
            {
                throw new NullPointerException("Player can not be null");
            }
            sd.deck.restorePlayerCards(player);
        }
        public int getCurrentTrickSize()
        {
            return sd.getTricks().getCurrentTrick().size();
        }
    }
    
    /**
     * kjfkdjfkdjkfdjfkd
     * @param firstTrickLeader first player
     * @param otherPlayer second player
     */
    public SingleDeal(Player firstTrickLeader, Player otherPlayer)//nadaje referencje do graczy 
    {
        if(firstTrickLeader == null)
            throw new NullPointerException("first");
        if(otherPlayer == null)
            throw new NullPointerException("second");
        
        this.players = new Players(firstTrickLeader,otherPlayer);
        this.activePlayer  = firstTrickLeader;
        
        start();
    }
    private void swapActivePlayer()
    {
         // warunek ? wartosc1:wartosc2
        activePlayer = players.getOtherPlayer(activePlayer);
    }
    private void start()//rozdaje karty do ręki graczy 
    {
        tricks = new Tricks();
        deck = Deck.createStandard();
        deck.shuffle();
        isFinished = false;
        
        for (int i = 0; i < FULL_HAND_SIZE; i++) {
            CardTransferer.transfer(deck.getTopCard(),deck,players.getLeader().getHand());
            CardTransferer.transfer(deck.getTopCard(),deck,players.getNonLeader().getHand());
        }                
    }
    
    public BaseResult tryMakeMoveByActivePlayer(Card card)
    {
        if(card == null)
            throw new NullPointerException("card");
        if(isFinished) //??
            throw new IllegalStateException("Game is already finished");
        
        var move = Move.tryCreate(card, activePlayer,
                players.getOtherPlayer(activePlayer));//sprawdzi czy karta nalezy do gracza
        if (move.isEmpty())
            return new InvalidCardOwner();
        
        var moveResult = tricks.tryMakeMove(move.get());//przy powtórzeniu koloru w tricku wywali MoveResult na true = nieudanemu rochowi        
        moveResult.handle(new SingleDealProxy(this));
        
        // check if game finished and change to DealFinished instance
        updateIsFinished(moveResult);
        
        return isFinished ? new DealFinished(moveResult) : moveResult;
    }
       
       
    private void updateIsFinished(BaseResult lastMoveResult) 
    {       
        isFinished = lastMoveResult.getResultType() == ResultType.NextMoveNotPossible
                ||  players.haveEmptyHands();
    }
//    public 
    public Tricks getTricks() {
        return tricks;
    }
    
    public Player getActivePlayer()
    {
        return activePlayer;
    }
    
}
