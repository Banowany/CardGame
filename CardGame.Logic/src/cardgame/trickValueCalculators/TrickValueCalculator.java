/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.trickValueCalculators;

import cardgame.Card;
import cardgame.cardsCollection.CardsCollection;
import java.util.List;

/**
 *
 * @author KG Studia
 */
public interface TrickValueCalculator 
{
   int calculate(List<Card> cards);
}
