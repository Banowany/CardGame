package cardgame;

/*
    Klasa enum, ktora przyjmuje tak zwane wartosci, ktorymi sa wszystkie
    mozliwe kolory kart
    Dodatkwo do kazdych z tych wartosci jest przypisany pewien string,
    ktory tez symbolizuje podana karte w postaci oznaczenia koloru
*/
public enum Suit
{    
    Hearts("♥"),Spades("♣"),Diamonds("♦"),Clubs("♠");
    
    private final String symbol;
    
    Suit(String symbol)
    {
        this.symbol = symbol;        
    }

    public String getSymbol()
    {
        return symbol;
    }
    
}
