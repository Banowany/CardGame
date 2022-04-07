package cardgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import cardgame.cardsCollection.CardTransferer;
import cardgame.cardsCollection.CardsCollection;
import cardgame.cardsCollection.Hand;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import static cardgame.Utilities.*;

/**
 *
 * @author Krzysztof
 */
public class Trick extends CardsCollection
{    
    @Override
    public List<Card> getCardsAsList() 
    {
        return super.getCardsAsList();
    }
    public Trick() {
        super(new ArrayList<Card>());
    }
    public  static final int MAX_SIZE = 4;
   // private int pointsInTrick;
    
    //start gry
//    public void start()
//    {
//        this.cards
//        this.pointsInTrick=0;
//        cards.add(new CardForAddenda(null, 0, false));//dodaje pusta karte na poczatek
//        //tutaj nie mam lepszego pomyslu
//    }
    
//    private boolean isSuitDuplicatedAfterAdd(Card newCard)
//    {
//        List<Card> cardsWithNewCard = new ArrayList<>(cards);
//        cardsWithNewCard.add(newCard);
//        return Utilities.isSuitDuplicated(cardsWithNewCard);
//    }
    public void add(Card card,Hand hand)
    {        
        if(card == null)
            throw new NullPointerException("card");
        if(hand == null)
            throw new NullPointerException("hand");
        if (size() == MAX_SIZE)
            throw new IllegalStateException("Trick is already full");
        if ( isSuitDuplicated(Union(cards, card)))
            throw new IllegalStateException("Trick is duplicated");
        
        CardTransferer.transfer(card, hand, this);
    }
    public boolean isFull()
    {
        return this.size() == MAX_SIZE;
    } 
    public boolean isOwnedByLeader(Card card)
    {
        if (card == null)
            throw new NullPointerException("card is null");
        if (!cards.contains(card))
            throw new IllegalArgumentException("card is not contained in this trick");
               
        
        return getCardsAsList().indexOf(card)%2==0;
    }
    //dodanie karty
//    public void add(Card card)
//    {
//        //dokonczyc funkce add
//        if(card.getFace()==Face.Jack)
//        {
//            cards.add(new CardForAddenda(card,(-1) * cards.get(cards.size()-1).getPowerOfCard(), true));
//        }
//        else if(card.getFace()==Face.King)
//        {
//            cards.add(new CardForAddenda(card, cards.get(cards.size()-1).getPowerOfCard(), true));
//        }
//        else
//        {
//            
//        }
//    }
    
    //wyczysc trick
    
    //restart
        //zwraca punkty
        //odpala start tricku
    
    //koniec
    
    //wykrywanie czy ma ponownie potasowac karty
    
    @Override
    public String toString()
    {
        return String.format("%s", cards);
    }
    
}
