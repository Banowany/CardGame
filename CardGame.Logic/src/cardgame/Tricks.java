/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import static cardgame.Utilities.Union;
import static cardgame.Utilities.isSuitDuplicated;
import cardgame.move.Move;
import cardgame.move.results.BaseResult;
import cardgame.move.results.NextMoveNotPossible;
import cardgame.move.results.SuitDuplicated;
import cardgame.move.results.TrickCompleted;
import cardgame.move.results.TrickNotCompleted;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author KG Studia
 */
public class Tricks {
    private final List<Trick> tricks = new ArrayList<>();

    public Tricks() {
        tricks.add(new Trick());
    }
     
        
    public BaseResult tryMakeMove(Move move)
    {
        if(move == null)
            throw new NullPointerException("move");

        addNewTrickIfShould();
                
        if(isSuitDuplicated(Union(getCurrentTrick().getCards(), move.getCard())))
        {
            return new SuitDuplicated(move);
        }
        
        getCurrentTrick().add(move.getCard(), move.getActivePlayer().getHand());

                        
        if(getCurrentTrick().isFull())
        {
            return new TrickCompleted(getCurrentTrick());
        }
        else if (!move.getOtherPlayer().isPossibleNotToDuplicateSuit(getCurrentTrick()))
        {
            return new NextMoveNotPossible();
        }        
        return new TrickNotCompleted(); //
    }

    private void addNewTrickIfShould() {
        if(!getCurrentTrick().isFull())
            return;
        tricks.add(new Trick());
    }
    public Trick getCurrentTrick()
    {
        return tricks.get(tricks.size() -1);
    }
    
    public List<Trick> getTricks()
    {
        return Collections.unmodifiableList(tricks);
    }

    @Override
    public String toString() {
//        "1" "2" "3"  
//        
//        "" , "1" -> "1"
//        "1" , "2" -> "12"
//        
        //::metoda
        var stream =  getTricks().stream().map(Trick::toString);
        return stream.reduce("", (acc, str) -> String.format("%s\n%s", acc, str));
    }
    
}
