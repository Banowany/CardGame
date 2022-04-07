/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import static java.util.List.of;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author KG Studia
 */
public class Utilities {
    public static boolean isSuitDuplicated(Collection<Card> cards)
    {
        if (cards == null )
            throw new NullPointerException("cards");
        
        //Supplier<Object>
        Supplier<Stream<Suit>> supplier = () -> cards.stream().map((Card c) -> c.getSuit());
        
        var list1 = supplier.get().distinct().collect(Collectors.toList());
        var list2 = supplier.get().collect(Collectors.toList());
        
        return !list1.equals(list2);
                // anyMatch( (Card c) -> c.getSuit().equals(newCard.getSuit()));
    }
    public static <T> Collection<T> Union(Collection<T> col1,Collection<T> col2)
    {       
        return Stream.concat(col1.stream(),col2.stream()).collect(Collectors.toList());
    }
    public static <T> Collection<T> Union(Collection<T> col1,T el)
    {       
        return Union(col1,Collections.singleton(el));
    }
}
