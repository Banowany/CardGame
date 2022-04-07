package cardgame.utils;

import cardgame.Card;
import cardgame.Trick;
import cardgame.cardsCollection.CardsCollection;
import cardgame.cardsCollection.Deck;
import java.lang.reflect.Field;
import java.util.Collection;

public class CardsCollectionBuilder 
{    
    private static CardsCollection build(CardsCollection result,
            Collection<Card> collection) 
    {        
        Class classCardCollection = CardsCollection.class;
        try
        {
            Field cardsField = classCardCollection.getDeclaredField("cards");
            cardsField.setAccessible(true);
            cardsField.set(result,collection);
        }
        catch (ReflectiveOperationException ex)
        {
            throw new RuntimeException("Internal error", ex);
        }
            
        return result;
    }

    public static Trick buildTrick(Collection<Card> collection) 
          //  throws NoSuchFieldException, IllegalAccessException
    {
        Trick trick = new Trick();       
        return (Trick) build(trick,collection);
    }
    public static Deck buildDeck(Collection<Card> collection) 
          //  throws NoSuchFieldException, IllegalAccessException
    {
        Deck deck = Deck.createEmpty();
        return (Deck) build(deck,collection);
    }
}
