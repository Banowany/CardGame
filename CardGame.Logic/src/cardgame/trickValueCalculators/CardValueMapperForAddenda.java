package cardgame.trickValueCalculators;
import cardgame.Card;
import cardgame.Face;
import java.util.HashMap;

public class CardValueMapperForAddenda 
{
    private final HashMap<Face, Integer> faceValue = new HashMap<>();

    public CardValueMapperForAddenda() 
    {
        faceValue.put(Face.Ace, 1);
        faceValue.put(Face.Two, 2);
        faceValue.put(Face.Three, 3);
        faceValue.put(Face.Four, 4);
        faceValue.put(Face.Five, 5);
        faceValue.put(Face.Six, 6);
        faceValue.put(Face.Seven, 7);
        faceValue.put(Face.Eight, 8);
        faceValue.put(Face.Nine, 9);
        faceValue.put(Face.Ten, 10);
        faceValue.put(Face.Jack, 0);
        faceValue.put(Face.Queen, 0);
        faceValue.put(Face.King, 0);
    }
    
    public int extractValue(Card card)
    {
        return faceValue.get(card.getFace());  
    }
}
