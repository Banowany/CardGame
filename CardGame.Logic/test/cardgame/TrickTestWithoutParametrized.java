package cardgame;

import cardgame.utils.CardsCollectionBuilder;
import cardgame.cardsCollection.Hand;
import cardgame.Trick;
import cardgame.cardsCollection.CardsCollection;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

public class TrickTestWithoutParametrized 
{
    @Test(expected = NullPointerException.class)
    public void addNullCard()
    {
        Trick instance = new Trick();
        
        //Create hand with expectations card
        Hand activePlayerHand = new Hand();
        
        instance.add(null, activePlayerHand);
    }
    
    @Test(expected = NullPointerException.class)
    public void addCardToNullHand()
    {
        Trick instance = new Trick();
        
        Card expCard = new Card(Suit.Clubs, Face.Ace);
        
        instance.add(expCard, null);
        
    }
    
    @Test(expected = NullPointerException.class)
    public void addNullCardToNullHand()
    {
        Trick instance = new Trick();
        
        instance.add(null, null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void tryToAddFifthCard() throws SecurityException, NoSuchFieldException, 
            IllegalArgumentException, IllegalAccessException
    {
        Collection<Card> collection = Stream.of(new Card(Suit.Diamonds, Face.Jack), 
                new Card(Suit.Clubs, Face.Five),
                new Card(Suit.Hearts, Face.Queen),
                new Card(Suit.Spades, Face.Ace)).collect(Collectors.toList());
        
        Trick instance = CardsCollectionBuilder.buildTrick(collection);
        
        //Create hand with expectations card
        Hand activePlayerHand = new Hand();
        Card expCard = new Card(Suit.Spades, Face.Two);
        activePlayerHand.add(expCard);        
        
        //Testing
              
        instance.add(expCard, activePlayerHand);
    }
}
