package cardgame.cardsCollection;

import cardgame.Card;
import java.util.HashSet;

/*
    Jest interpretacja talii kart w rece.
    Z powodu ze gracz moze ustawiac sobie jak chce
    to nie jest tu wazna kolejnosc.
    Dziedziczy z CardsCollection!!!
*/
public class Hand extends CardsCollection
{
    //Konstruktor bez argumentow
    public Hand() {
        super(new HashSet<Card>());
    }
    
    //dodaje karte do reki gracza
    public void add(Card card)
    {
        if(card == null)
            throw new NullPointerException("card");
        cards.add(card);
    }
 
    /*
        usuwa karte z reki gracza uzywajac metody tryRemove()
        jesli ona da falsz to wywala wyjatek o braku karty w talii
    */
    public void remove(Card card)
    {                
        if (!tryRemove(card))
        {
            throw new IllegalArgumentException("card is not in hand");
        }
    }
    
    /*
        Probuje usunac karte.
        W razie pustej wywala wyjatek
    */
    public boolean tryRemove(Card card)
    {        
        if(card == null)
            throw new NullPointerException("card");
        return cards.remove(card);
    }  
}
