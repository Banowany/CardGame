/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.trickValueCalculators.TrickValueCalculator;
import cardgame.trickValueCalculators.TrickValueCalculatorImplementation2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Krzysztof
 */
@RunWith(Parameterized.class)
public class TrickValueCalculatorImplementationTest {

    private List<Card> cards;
    private int expectedTrickValue;
    protected TrickValueCalculator instance;
   

    @Before
    public void initialize() {
        instance = new TrickValueCalculatorImplementation2();
    }

    public TrickValueCalculatorImplementationTest(List<Card> cards, int expectedTrickValue) {
        this.cards = cards;
        this.expectedTrickValue = expectedTrickValue;
    }

    @Parameterized.Parameters
    public static Collection input() {
        var r = new Object[][]
        {
             {Arrays.asList(new Card[]{
                new Card(Suit.Diamonds, Face.Four),
                new Card(Suit.Hearts, Face.Five),
                new Card(Suit.Clubs, Face.Six),
                new Card(Suit.Spades, Face.Jack)
            }), 9},
            {Arrays.asList(new Card[]{
                new Card(Suit.Diamonds, Face.Four),
                new Card(Suit.Hearts, Face.Five),
                new Card(Suit.Clubs, Face.Jack),
                new Card(Suit.Spades, Face.King)
            }), 9},
            {Arrays.asList(new Card[]{
                new Card(Suit.Diamonds, Face.Four),
                new Card(Suit.Hearts, Face.Five),
                new Card(Suit.Clubs, Face.Jack),
                new Card(Suit.Spades, Face.Jack)
            }), 9},
            {Arrays.asList(new Card[]{
                new Card(Suit.Diamonds, Face.Four),
                new Card(Suit.Hearts, Face.Five),
                new Card(Suit.Clubs, Face.Six),
                new Card(Suit.Spades, Face.King)
            }), 21},
            {Arrays.asList(new Card[]{
                new Card(Suit.Diamonds, Face.Four),
                new Card(Suit.Hearts, Face.Five),
                new Card(Suit.Clubs, Face.King),
                new Card(Suit.Spades, Face.King)
            }), 19},
            {Arrays.asList(new Card[]{
                new Card(Suit.Diamonds, Face.Four),
                new Card(Suit.Hearts, Face.Five),
                new Card(Suit.Clubs, Face.Jack),
                new Card(Suit.Spades, Face.Seven)
            }), 11},
            {Arrays.asList(new Card[]{
                new Card(Suit.Diamonds, Face.Four),
                new Card(Suit.Hearts, Face.Five),
                new Card(Suit.Clubs, Face.King),
                new Card(Suit.Spades, Face.Jack)
            }), 9},
            {Arrays.asList(new Card[]{
                new Card(Suit.Diamonds, Face.Four),
                new Card(Suit.Hearts, Face.Five),
                new Card(Suit.Clubs, Face.King),
                new Card(Suit.Spades, Face.Seven)
            }), 21},
            {Arrays.asList(new Card[]{
                new Card(Suit.Diamonds, Face.Four),
                new Card(Suit.Hearts, Face.Jack),
                new Card(Suit.Clubs, Face.King),
                new Card(Suit.Spades, Face.King)
            }), 0}
        };
        //var i = r[0];
        return Arrays.asList(r);
    }
    
    @Test
    public void testCalculate() {
        System.out.println("calculate");
        int result1 = instance.calculate(cards);

        // assert
        assertEquals(expectedTrickValue, result1);
    }

}
