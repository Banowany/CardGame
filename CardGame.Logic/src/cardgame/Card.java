package cardgame;

import java.util.Objects;

/*
    Klasa ta jest polaczeniem 2 enumow symbolizujacych kolor jak i wartosc
    karty w postaci jednego obiektu(wygodniejsza do uzycia i zapisania)
*/
public class Card
{    
    /*
        prywatne i ostateczne zmienne oznaczaja ze mozmy tu przypisac
        tylko jeden obiekt/wartosc i nie mozemy pozniej tego zmienic.
        Dodatkow private powoduje ze moga byc uzyte w obrebie klasy 
        I NIE DALEJ
    */
    private final Suit suit;
    private final Face face;
    
    //zwraca kolor karty
    public Suit getSuit()
    {        
        return suit;
    }

    //zwraca wartosc karty
    public Face getFace()
    {
        return face;
    }
    
    //Konstruktor 
    public Card(Suit suit,Face face)
    {
        /*
            Sprawdza czy nie przydzielono nulla
            jesli tak to wywala wyjatek
        */
        if(suit == null)
            throw new NullPointerException("suit");
        if(face == null)
            throw new NullPointerException("face");
        
        /*
            Zmienna this.(zmienna) odwoluje sie do pierwszego napotkania podanej
            zmiennej od poczatku deklartacji klasy
        */
        this.suit = suit;
        this.face = face;
    }
    
    /*
       powoduje ze przy probie wypisania obiektu zostanie zrobione w sposob
       zaprogramowany w metodzie
    */
    @Override//<-- odpowiada za nadpisanie istniejacej juz wyzej metody
    public String toString()
    {
        return String.format("%s %s", suit.getSymbol(),face.getLabel());
       // return String.join(" ", suit.getSymbol(),face.getLabel());
       // return suit.getSymbol() + " " + face.getLabel();
    }

    /*
        Przypisuje orginalna wartosc dla podanego obiektu
        (przydatny do odróżniana przy zbiorach)
    */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.suit);
        hash = 79 * hash + Objects.hashCode(this.face);
        return hash;
    }

    /*
        Przyrownuje obiekty w sposob zadklarowny przez ta metode
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Card other = (Card) obj;
        if (this.suit != other.suit) {
            return false;
        }
        if (this.face != other.face) {
            return false;
        }
        return true;
    }
}
