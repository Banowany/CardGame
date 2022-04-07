package cardgame;

import cardgame.cardsCollection.Hand;
import cardgame.utils.CardsCollectionBuilder;
import cardgame.utils.HandBuilder;
import net.jqwik.api.*;
import net.jqwik.api.Tuple.Tuple2;
import net.jqwik.api.lifecycle.BeforeContainer;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TrickTestCorrectCollectionOfCards
{
    private static List<Card> ALL_POSSIBLE_CARDS;

    @BeforeContainer
    private static void fillAllPossibleCards()
    {
        ALL_POSSIBLE_CARDS =new ArrayList<>();
        for (Suit s : Suit.values())
        {
            for (Face f: Face.values())
            {
                ALL_POSSIBLE_CARDS.add(new Card(s, f));
            }
        }
    }

    @Property
    void addingNotDublicatedCardToNotFullTrickWorksCorrectly(@ForAll("CorrectTrickAndNotDuplicatedCard")
                                                                    Tuple2<Card, List<Card>> trickWithNewCard)
    {
        Card newCard = trickWithNewCard.get1();
        Trick trick = CardsCollectionBuilder.buildTrick(new ArrayList<Card>(trickWithNewCard.get2()));
        Hand hand = HandBuilder.build(newCard);

        System.out.println(newCard + " " + trick.getCards());

        trick.add(newCard, hand);

        Collection<Card> expectedTrick = Utilities.Union(trickWithNewCard.get2(), trickWithNewCard.get1());
        //expectedTrick.stream().

        Assert.assertEquals(new ArrayList<Card>(trick.getCards()), expectedTrick);
    }

    private static List<Card> getAllPossibleCardsWithoutSuit(Suit suit)
    {
        return ALL_POSSIBLE_CARDS.stream().filter(card -> card.getSuit()!=suit)
                .collect(Collectors.toList());
    }

    @Provide("CorrectTrickAndNotDuplicatedCard")
    Arbitrary<Tuple2<Card, List<Card>>> trickAndNewCard()
    {
        //Wygeneruj losowa karte
        //wygenreuj ciag kart(0,3) niezawieracjacy wylosowanej karty oraz jej koloru
        //przefiltruj ciag wzgledem koloru
        //Zwroc pare
        //string,int ->string ""
        //List<T> -> T // fold
        //10 3 4 1 2, 0

    //Arbitraries.of(ALL_POSSIBLE_CARDS).list().ofSize(5).reduce();
        return Arbitraries.of(ALL_POSSIBLE_CARDS)
                .flatMap(newCard -> Arbitraries.of(getAllPossibleCardsWithoutSuit(newCard.getSuit()))
                        .list().ofMinSize(0).ofMaxSize(3)//.reduce()
                        .uniqueElements(Card::getSuit)
                        //.filter(list -> list.stream().map(card -> card.getSuit()).distinct().count()==list.size())
                        .map(trick -> Tuple.of(newCard, trick)));
    }
}
