/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.cardsCollection.Hand;
import static cardgame.Utilities.*;
import java.util.Optional;

/**
 *
 * @author Krzysztof
 */
public class Player 
{
    private final Hand hand;
    private int points;
    private final String name;
    private Optional<Integer> lastDuplicatedCardTrickIndex;
    
    public String getName() {
        return name;
    }
        
    
    public Player(String name ) {
        hand = new Hand();
        points = 0;
        lastDuplicatedCardTrickIndex = Optional.empty();
        this.name = name;
    }

    public Optional<Integer> getLastDuplicatedCardTrickIndex() {
        return lastDuplicatedCardTrickIndex;
    }
            
    
    //wybierz karte<--to raczej tu nie zadziala tylko w hand
    
    //Daj do tricku
    
    //dodaj punkty
    
    //pokaz punkty

    public Hand getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }
    
    public boolean hasEmptyHand()
    {
        return hand.isEmpty();
    }
    
    public void addPoints(int points)
    {
        if(points<0)
        {
            throw new IllegalArgumentException("points must be positive");
        }
        this.points+=points;
    }

    @Override
    public String toString() {
        return "Player{" + "points=" + points + '}';
    }

    protected boolean isPossibleNotToDuplicateSuit(Trick currentTrick)
    {
        //trick -> wyciagnac obecne karty z trick
        //player -> obecna reka
        //Cel -> czy da sie dolazyc karte bez duplikacji
        //Sposob -> sprawdzic kazda karte z reki czy nie zduplikuje sie z karta z tricku
//        for(Card card : hand.getCards())
//        {
//            if (!isSuitDuplicated(Union(currentTrick.getCards(), card)))
//            {
//                return true;
//            }
//        }
//        return  false;

        return hand.getCards().stream().map(card -> isSuitDuplicated(Union(currentTrick.getCards(), card)))
                .anyMatch(suitDuplicated -> !suitDuplicated);
    }

    public void registerSuitDuplication(int duplicatedCardTrickIndex) 
    {
        lastDuplicatedCardTrickIndex = Optional.of(duplicatedCardTrickIndex);
    }

    void resetSuitDuplicatedIndex() {
        lastDuplicatedCardTrickIndex = Optional.empty();
    }
    
    
}
