package cardgame.cardsCollection;

import cardgame.Card;
import cardgame.Face;
import cardgame.Player;
import cardgame.SingleDeal;
import cardgame.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/*
    Podana klasa dziedziczy od CardCollection czyli Deck zawiera wszystkie 
    nieprywatne metody i zmienne jak i obiekty CardsCollection
*/
public class Deck extends CardsCollection
{   
    //private blokuje mozliwosc storzenia instacji poza klasa Deck
    private Deck(List<Card> cards)
    {              
        //powoluje sie na konstruktor klasy bazowej
        super(cards);        
    }
    
    /*
        Tworzy klasyczna talie kart do gry(52 karty)
    */
    public static Deck createStandard()
    {
        List<Card> cards = new ArrayList<>();
               
        for (Suit suit : Suit.values())
        {
            for (Face face : Face.values())
            {
                cards.add(new Card(suit, face));
            }
        }

        return new Deck(cards);
    }     
    public static Deck createEmpty()
    {
        return new Deck(new ArrayList<>());
    }
    public void shuffle()
    {
        //tasuje obecnie posiadana kolekcje kart(gdy pusta nic nie zrobi)
        Collections.shuffle(getCardsAsList());
    }
    
    /*
        Optional odpowiada ze mozemy sie spodziewaca obiektu lub tez zmiennej
        pewnego typu podanego w <(cos)> ale wcale nie musi go zwrocic 
    */
    public Optional<Card> tryGetTopCard()
    {    
        if(isEmpty())
            return Optional.empty();
        return Optional.of(getCardsAsList().get(0));
        
        //Metoda jest zabezbpieczona przez Optional
    }
//    private Exception foo()
//    {
//        return new IllegalStateException("Deck is empty");
//    }
    public Card getTopCard()
    {    
        return tryGetTopCard().orElseThrow( () -> new IllegalStateException("Deck is empty"));
//        if(isEmpty())
//            throw new IllegalStateException("Deck is empty");
//        return getCardsList().get(0);
        
        //Metoda jest zabezbpieczona przez Optional
    }

//    //rzutowanie Kolecji na liste
//    private List<Card> getCardsList() 
//    {
//        //W tym miejscu mamy nadzieje ze w przypdaku gdy podana kolekcja nie
//        //bedzie lista to po prostu zostanie wywalony wyjatek przez co bedziemy
//        //wiedzieli co mniej wiecej poprawic
//        return (List<Card>) cards;
//    }

    public void restorePlayerCards(Player player) {
        while (!isEmpty() && player.getHand().size() < SingleDeal.FULL_HAND_SIZE) {
            CardTransferer.transfer(getTopCard(), this, player.getHand());
        }
    }
}
