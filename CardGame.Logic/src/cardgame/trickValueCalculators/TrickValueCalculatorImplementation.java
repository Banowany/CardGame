/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.trickValueCalculators;

import cardgame.Card;
import java.util.List;

/**
 *
 * @author Krzysztof
 */
public class TrickValueCalculatorImplementation implements TrickValueCalculator {

    @Override
    public int calculate(List<Card> cards) {
        int cardValueBefore = 0;
        boolean jackPowerBefore = false;
        int trickValue = 0;
        int cardValueNow;
        boolean jackPowerNow;

        for (int i = 0; i < cards.size(); i++) {
            cardValueNow = cardValue(cards.get(i), cardValueBefore, jackPowerBefore);
            jackPowerNow = jackPower(cards.get(i), jackPowerBefore);
            trickValue += cardValueNow;
            cardValueBefore = cardValueNow;
            jackPowerBefore = jackPowerNow;
        }
        return trickValue;
    }

//    private static List<Card> getCardsList(CardsCollection cards) 
//    {
//        return (List<Card>) cards.getCards();
//    }
    private int cardValue(Card card, int cardValueBefore, boolean jackPowerBefore) {
        int powerValue = 0;

        switch (card.getFace()) {
            case Ace:
                powerValue = 1;
                break;
            case Two:
                powerValue = 2;
                break;
            case Three:
                powerValue = 3;
                break;
            case Four:
                powerValue = 4;
                break;
            case Five:
                powerValue = 5;
                break;
            case Six:
                powerValue = 6;
                break;
            case Seven:
                powerValue = 7;
                break;
            case Eight:
                powerValue = 8;
                break;
            case Nine:
                powerValue = 9;
                break;
            case Ten:
                powerValue = 10;
                break;
            case Jack:
                powerValue = -cardValueBefore;
                break;
            case Queen:
                powerValue = 0;
                break;
            case King:
                if (jackPowerBefore) {
                    powerValue = -cardValueBefore;
                } else {
                    powerValue = cardValueBefore;
                }
                break;
        }
        return powerValue;
    }

    private boolean jackPower(Card card, boolean jackPowerBefore) {
        boolean jackPower = false;

        switch (card.getFace()) {
            case Jack:
                jackPower = true;
                break;
            case King:
                jackPower = jackPowerBefore;
                break;

            default:
                jackPower = false;
        }
        return jackPower;
    }
}
