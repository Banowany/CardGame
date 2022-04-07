/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.utils;

import cardgame.Card;
import cardgame.Face;
import cardgame.Suit;
import cardgame.cardsCollection.Hand;

/**
 *
 * @author KG Studia
 */
public class HandBuilder
{
    public static Hand build(Card expCard)
    {
        Hand hand = new Hand();
        hand.add(expCard);
        return hand;
    }
}
