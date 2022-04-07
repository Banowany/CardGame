package cardgame.cardsCollection;

import cardgame.Card;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
    Jest klasa bazowa odpowiadajaca za kolekcje kart
    Kolekcja jest to interefejs zawierajacy takie metody jak:
    - dodaj element
    - usun element
    - iteracja po obiektach
    itp
    Kolekcja nie zawiera interpretacji tych metod. Ich interpretacje 
    zawieraja takie kontenery jak lista, zbior itp.
*/
public abstract class CardsCollection
{
    /*
        Protected odpowiada za to ze podanej kolkcji mozna uzyc tylko
        w obrebie jednego pakietu
        final wyjsanione w Card
    */
    protected final Collection<Card> cards;

    /*
        Zwraca referencje do chronionej kolekcji kart tylko do wglądu
    */
    public Collection<Card> getCards() 
    {
        return Collections.unmodifiableCollection(cards);
    }
    protected List<Card> getCardsAsList() 
    {
        //W tym miejscu mamy nadzieje ze w przypdaku gdy podana kolekcja nie
        //bedzie lista to po prostu zostanie wywalony wyjatek przez co bedziemy
        //wiedzieli co mniej wiecej poprawic
        if (!(cards instanceof List))
        {
            throw new IllegalStateException("This cards collection cannot be treated as list.");
        }
        return (List<Card>) cards;
    }
    /*
        Konstruktor odpowiadajacy za przypisanie interpretacji kolekcji
    */
    public CardsCollection(Collection<Card> cards)
    {
        if(cards == null)
            throw new NullPointerException("cards");
        this.cards = cards;
    }
    
    //Pokazuje ilosc elemntow w kolekcji
    public int size()
    {
        return cards.size();
    }
    
    //pokazuje czy kolekcja jest pusta
    public boolean isEmpty()//dodane ode mnie
    {
        return cards.isEmpty();
    }
    
    public boolean contains(Card card)
    {
        if(cards == null)
            throw new NullPointerException("cards");
        return cards.contains(card);
    }
    //metoda do wyswietlania
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
       
        for(Card card:cards)
        {
            //res + 
            sb.append(String.format("%s ", card)); // String.join(" ", card.toString());
            //System.out.println(card);
        }
        return sb.toString();
    }
}
