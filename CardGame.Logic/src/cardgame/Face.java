package cardgame;

/*
    Klasa enum, ktora przyjmuje tak zwane wartosci, ktorymi sa wszystkie
    mozliwe wartosci kart
    Dodatkwo do kazdych z tych wartosci jest przypisany pewien string,
    ktory tez symbolizuje podana karte w postaci oznaczenia wartosci
*/
public enum Face
{
    Two("2"),Three("3"),Four("4"),Five("5"),Six("6"),Seven("7"),Eight("8"),
    Nine("9"),Ten("10"),Jack("J"),Queen("Q"),King("K"),Ace("A");
    
    private final String label;
    
    Face(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
}