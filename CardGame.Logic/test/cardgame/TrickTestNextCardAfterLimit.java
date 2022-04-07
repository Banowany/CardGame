package cardgame;

import cardgame.utils.CardsCollectionBuilder;
import cardgame.cardsCollection.Hand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cardgame.utils.HandBuilder;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeContainer;
import org.junit.Test;

public class TrickTestNextCardAfterLimit
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
    boolean whenAddNextCardAfterLimitThrowException(@ForAll("FullTrickAndNextCard")
                                                               Tuple.Tuple2<Card, List<Card>> trickWithNewCard)
    {
        Card newCard = trickWithNewCard.get1();
        Trick trick = CardsCollectionBuilder.buildTrick(new ArrayList<Card>(trickWithNewCard.get2()));
        Hand hand = HandBuilder.build(newCard);

        System.out.println(newCard + " " + trick.getCards());

        try
        {
            trick.add(newCard, hand);
        }
        catch (IllegalStateException e)
        {
            return true;
        }
        return false;
    }

    private static List<Card> getOthersCards(List<Card> cards)
    {
        return ALL_POSSIBLE_CARDS.stream().filter(c -> !cards.contains(c))
                .collect(Collectors.toList());
    }

    @Provide("FullTrickAndNextCard")
    Arbitrary<Tuple.Tuple2<Card, List<Card>>> trickAndNewCard()
    {
        //wygenreuj ciagi kart(4)
        //przefiltruj ciagi by spelnial warunki tricku
        //newCard wybierz z pozostalych kart
        //Zwroc pare


        //Arbitraries.of(ALL_POSSIBLE_CARDS).list().ofSize(5).reduce();
        return Arbitraries.of(ALL_POSSIBLE_CARDS).list().ofSize(4)
                .filter(list -> list.stream().map(card -> card.getSuit()).distinct().count()==list.size())
                .flatMap(trick -> Arbitraries.of(getOthersCards(trick))
                        .map(newCard -> Tuple.of(newCard, trick)));
    }
}
