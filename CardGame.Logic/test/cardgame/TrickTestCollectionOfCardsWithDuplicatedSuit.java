package cardgame;

import cardgame.cardsCollection.Hand;
import cardgame.utils.CardsCollectionBuilder;
import cardgame.utils.HandBuilder;
import net.jqwik.api.*;
import net.jqwik.api.Tuple.Tuple2;
import net.jqwik.api.lifecycle.BeforeContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrickTestCollectionOfCardsWithDuplicatedSuit
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
    boolean whenAddDuplicatedSuitToTrickThrowExepction(@ForAll("NewCardAndTrickWithDuplicatedSuitFromNewCard")
                                                                    Tuple2<Card, List<Card>> trickWithNewCard)
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

    private static List<Card> getOthersCardsInTheSameSuit(Card card)
    {
        return ALL_POSSIBLE_CARDS.stream().filter(c -> c!=card && c.getSuit()==card.getSuit())
                .collect(Collectors.toList());
    }

    @Provide("NewCardAndTrickWithDuplicatedSuitFromNewCard")
    Arbitrary<Tuple2<Card, List<Card>>> trickAndNewCard()
    {
        //wygenreuj ciagi kart(1,3) niezawieracjacy wylosowanej karty
        //przefiltruj ciagi by spelnial warunki tricku
        //wybierz dowolny kolor z ciagow(nazwijmy COL)
        //newCard wybieramy z nieużytych COL
        //Zwroc pare


        //Arbitraries.of(ALL_POSSIBLE_CARDS).list().ofSize(5).reduce();
        return Arbitraries.of(ALL_POSSIBLE_CARDS).list().ofMinSize(1).ofMaxSize(3)
                .filter(list -> list.stream().map(card -> card.getSuit()).distinct().count()==list.size())
                .flatMap(trick -> Arbitraries.of(getOthersCardsInTheSameSuit(trick.get(0)))
                        .map(newCard -> Tuple.of(newCard, trick)));
    }
}


//package cardgame;
//
//import cardgame.utils.CardsCollectionBuilder;
//import cardgame.cardsCollection.Hand;
//import cardgame.utils.CardsCollectionBuilderWithDuplicatedCardAtTheEnd;
//import cardgame.utils.HandBuilder;
//import cardgame.utils.ListReducer;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//
//@RunWith(Parameterized.class)
//public class TrickTestCollectionOfCardsWithDuplicatedSuit
//{
//    private final Trick instance;
//    private final Card duplicatedCard;
//    private final Hand activePlayerHand;
//    private final Collection<Card> collection;
//
//    @Parameterized.Parameters
//    public static Collection input()
//    {
//        CardsCollectionBuilderWithDuplicatedCardAtTheEnd cardsCollectionBuilder =
//                new CardsCollectionBuilderWithDuplicatedCardAtTheEnd();
//        System.out.println("Seed: "+ cardsCollectionBuilder.getListOfCardsSeed());
//        return cardsCollectionBuilder.createSomeListsOfCards();
//    }
//
//    private List<Card> collectionCastToList(Collection<Card> collection)
//    {
//        return (List<Card>) collection;
//    }
//
//    public TrickTestCollectionOfCardsWithDuplicatedSuit(Collection<Card> collection) throws NoSuchFieldException, IllegalAccessException
//    {
//        this.collection=collection;
//        instance=CardsCollectionBuilder.buildTrick(ListReducer.reduce(collectionCastToList(collection), collection.size()-1));
//        duplicatedCard=collectionCastToList(collection).get(collection.size()-1);
//        activePlayerHand = HandBuilder.build(duplicatedCard);
//    }
//
//    @Test(expected = IllegalStateException.class)
//    public void whenAddDuplicatedCardToTrickThrowExepction() throws ReflectiveOperationException
//    {
//        //Testing
//        instance.add(duplicatedCard, activePlayerHand);
//    }
//}
