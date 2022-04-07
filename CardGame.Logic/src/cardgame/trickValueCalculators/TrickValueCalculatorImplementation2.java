/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.trickValueCalculators;

import cardgame.Card;
import cardgame.Face;
import java.util.List;

/**
 *
 * @author Krzysztof
 */
public class TrickValueCalculatorImplementation2 implements TrickValueCalculator
{
    private final CardValueMapperForAddenda cardValueForAddenda = new CardValueMapperForAddenda();
    
    @Override
    public int calculate(List<Card> cards)
    {
        int trickValue=0;
        int cardValue;
        int j;
        boolean onlyJack;
        //final int trickSize = 4;
        
        for(int i=0;i<cards.size();i++)
        {
            j=i;
            
            //cofam sie do karty roznej od Jack i King
            while((cards.get(j).getFace().equals(Face.Jack) 
                    || cards.get(j).getFace().equals(Face.King)) && j > 0)
            {
                j--;
            }
            
            //wracam do miejsca poczatkowego robiac zmiany po drodze
            onlyJack = false;
            cardValue=cardValueForAddenda.extractValue(cards.get(j));
            for(j=j+1;j<=i;j++)
            {
                //Ze względu ze w tym ciagu sa krole i Jacki to napotkanie jacka powoduje ze kazda karta dziala jak jack
                if(cards.get(j).getFace()==Face.Jack || onlyJack)
                {
                    cardValue = -cardValue;
                    onlyJack=true;
                }
                //King do nothing
            }
            trickValue += cardValue;
        }
        return trickValue;
    }
}
