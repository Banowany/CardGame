package cardgame.cardsCollection;

import cardgame.Card;
/*
    Ta klasa odpowiada za przemieszczenie karty pomiedzy 2 kolekcjami
    Unikamy dzieki temu mozliwosci wystapienia nieporzadanego duplikatu karty
*/
public class CardTransferer 
{
    //metoda statyczna, ktora moze byc wywolana bez instacji klasy
    //warunkiem jest by nie odwola sie do niestatycznych metod i zmiennnych danej klasy
    public static void transfer(Card card,CardsCollection source,CardsCollection destination)
    {
        if(card == null)
            throw new NullPointerException("card");
        if(source == null)
            throw new NullPointerException("source");        
        if(destination == null)
            throw new NullPointerException("destination");
        
        source.cards.remove(card);
        destination.cards.add(card);
    }
}
