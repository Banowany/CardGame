/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import static cardgame.Utilities.Union;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author KG Studia
 */
public class UtilitiesTest {

    public UtilitiesTest() {
    }

    @Test
    public void singletonCardCollectionHasNoDuplicatedSuit() {
        System.out.println("testIsSuitDuplicatedWithOneCardInCollection");
        List<Card> cards = Stream.of(new Card(Suit.Clubs, Face.Ace))
                .collect(Collectors.toList());

        boolean expResult = false;
        boolean result = Utilities.isSuitDuplicated(cards);

        assertEquals(expResult, result);
    }

    @Test
    public void testIsSuitDuplicatedWithTwoCardsInCollectionAndWithoutDuplicate() {
        System.out.println("testIsSuitDuplicatedWithTwoCardsInCollectionAndWithoutDuplicate");
        List<Card> cards = Stream.of(new Card(Suit.Clubs, Face.Ace), new Card(Suit.Diamonds, Face.Jack))
                .collect(Collectors.toList());

        boolean expResult = false;
        boolean result = Utilities.isSuitDuplicated(cards);

        assertEquals(expResult, result);
    }

    @Test
    public void testIsSuitDuplicatedWithTwoCardsInCollectionAndWithDuplicate() {
        System.out.println("testIsSuitDuplicatedWithTwoCardsInCollectionAndWithDuplicate");
        List<Card> cards = Stream.of(new Card(Suit.Clubs, Face.Ace), new Card(Suit.Clubs, Face.Jack))
                .collect(Collectors.toList());

        boolean expResult = true;
        boolean result = Utilities.isSuitDuplicated(cards);

        assertEquals(expResult, result);
    }

    @Test
    public void testIsSuitDuplicatedWithThreeCardsInCollectionAndWithoutDuplicate() {
        System.out.println("testIsSuitDuplicatedWithThreeCardsInCollectionAndWithoutDuplicate");
        List<Card> cards = Stream.of(new Card(Suit.Clubs, Face.Ace), new Card(Suit.Diamonds, Face.Jack), new Card(Suit.Hearts, Face.King))
                .collect(Collectors.toList());

        boolean expResult = false;
        boolean result = Utilities.isSuitDuplicated(cards);

        assertEquals(expResult, result);
    }

    @Test
    public void testIsSuitDuplicatedWithThreeCardsInCollectionAndWithDuplicateFirstCard() {
        System.out.println("testIsSuitDuplicatedWithThreeCardsInCollectionAndWithDuplicateFirstCard");
        List<Card> cards = Stream.of(new Card(Suit.Clubs, Face.Ace),
                new Card(Suit.Diamonds, Face.Jack),
                new Card(Suit.Clubs, Face.King))
                .collect(Collectors.toList());//during game duplicated card could show on the end of List

        boolean expResult = true;
        boolean result = Utilities.isSuitDuplicated(cards);

        assertEquals(expResult, result);
    }

    @Test
    public void testIsSuitDuplicatedWithThreeCardsInCollectionAndWithDuplicateSecondtCard() {
        System.out.println("testIsSuitDuplicatedWithThreeCardsInCollectionAndWithDuplicateSecondtCard");
        List<Card> cards = Stream.of(new Card(Suit.Clubs, Face.Ace), new Card(Suit.Diamonds, Face.Jack), new Card(Suit.Diamonds, Face.King))
                .collect(Collectors.toList());//during game duplicated card could show on the end of List

        boolean expResult = true;
        boolean result = Utilities.isSuitDuplicated(cards);

        assertEquals(expResult, result);
    }

    @Test
    public void testIsSuitDuplicatedWithFourCardsInCollectionAndWithoutDuplicate() {
        System.out.println("testIsSuitDuplicatedWithFourCardsInCollectionAndWithoutDuplicate");
        List<Card> cards = Stream.of(new Card(Suit.Clubs, Face.Ace),
                new Card(Suit.Diamonds, Face.Jack),
                new Card(Suit.Hearts, Face.King),
                new Card(Suit.Spades, Face.Six))
                .collect(Collectors.toList());

        boolean expResult = false;
        boolean result = Utilities.isSuitDuplicated(cards);

        assertEquals(expResult, result);
    }

    @Test
    public void testIsSuitDuplicatedWithFourCardsInCollectionAndWithDuplicateFirstCard() {
        System.out.println("testIsSuitDuplicatedWithFourCardsInCollectionAndWithDuplicateFirstCard");
        List<Card> cards = Stream.of(new Card(Suit.Clubs, Face.Ace),
                new Card(Suit.Diamonds, Face.Jack),
                new Card(Suit.Hearts, Face.King),
                new Card(Suit.Clubs, Face.Six))
                .collect(Collectors.toList());//during game duplicated card could show on the end of List

        boolean expResult = true;
        boolean result = Utilities.isSuitDuplicated(cards);

        assertEquals(expResult, result);
    }

    @Test
    public void testIsSuitDuplicatedWithFourCardsInCollectionAndWithDuplicateSecondCard() {
        System.out.println("testIsSuitDuplicatedWithFourCardsInCollectionAndWithDuplicateSecondCard");
        List<Card> cards = Stream.of(new Card(Suit.Clubs, Face.Ace),
                new Card(Suit.Diamonds, Face.Jack),
                new Card(Suit.Hearts, Face.King),
                new Card(Suit.Diamonds, Face.Six))
                .collect(Collectors.toList());//during game duplicated card could show on the end of List

        boolean expResult = true;
        boolean result = Utilities.isSuitDuplicated(cards);

        assertEquals(expResult, result);
    }

    @Test
    public void testIsSuitDuplicatedWithFourCardsInCollectionAndWithDuplicateThirdCard() {
        System.out.println("testIsSuitDuplicatedWithFourCardsInCollectionAndWithDuplicateThirdCard");
        List<Card> cards = Stream.of(new Card(Suit.Clubs, Face.Ace),
                new Card(Suit.Diamonds, Face.Jack),
                new Card(Suit.Hearts, Face.King),
                new Card(Suit.Hearts, Face.Six))
                .collect(Collectors.toList());//during game duplicated card could show on the end of List

        boolean expResult = true;
        boolean result = Utilities.isSuitDuplicated(cards);

        assertEquals(expResult, result);
    }

    //In trick we can have only four Cards
    @Test
    public void testUnionWithTwoCollection() {
        System.out.println("testUnionWithTwoCollection");

        List<Card> list1 = Stream.of(new Card(Suit.Diamonds, Face.King),
                new Card(Suit.Clubs, Face.Nine)).collect(Collectors.toList());

        List<Card> list2 = Stream.of(new Card(Suit.Hearts, Face.Jack),
                new Card(Suit.Clubs, Face.Seven)).collect(Collectors.toList());

        List<Card> expresult = Stream.of(new Card(Suit.Diamonds, Face.King),
                new Card(Suit.Clubs, Face.Nine),
                new Card(Suit.Hearts, Face.Jack),
                new Card(Suit.Clubs, Face.Seven))
                .collect(Collectors.toList());

        Collection<Card> result = Union(list1, list2);

        assertArrayEquals(expresult.toArray(), result.toArray());

                
    }
    
    @Test
    public void testUnionWithCollectionAndObject() {
        System.out.println("testUnionWithTwoCollection");

        List<Card> list = Stream.of(new Card(Suit.Diamonds, Face.King),
                new Card(Suit.Clubs, Face.Nine)).collect(Collectors.toList());

        Card object = new Card(Suit.Hearts, Face.Jack);

        List<Card> expresult = Stream.of(new Card(Suit.Diamonds, Face.King),
                new Card(Suit.Clubs, Face.Nine),
                new Card(Suit.Hearts, Face.Jack))
                .collect(Collectors.toList());

        Collection<Card> result = Union(list, object);

        assertArrayEquals(expresult.toArray(), result.toArray());

                
    }
}
