/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.console;

import cardgame.Card;
import cardgame.cardsCollection.Hand;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class RandomCardProvider implements UserCardProvider {

    private final Hand hand;

    public RandomCardProvider(Hand hand) {
        if( hand == null)
            throw new NullPointerException("hand");
        this.hand = hand;
        //hand = new 
    }

    public Hand getHand() {
        return hand;
    }
     
        
    @Override
    public Card get() 
    {
        Random random = new Random();
        List<Card> cards = hand.getCards().stream().collect(Collectors.toList());
        int randomIndex = random.nextInt(cards.size());
        return cards.get(randomIndex);
    }
    
}
