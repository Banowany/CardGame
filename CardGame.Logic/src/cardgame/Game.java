/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.move.results.BaseResult;
import cardgame.move.results.ResultType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KG Studia
 */
public class Game 
{   
    private Player first, second;
    private List<SingleDeal> deals = new ArrayList<>();
    
//    private switchActivePlayer()
//    {
//    }     
    
    public Player getActivePlayer()
    {
        return getCurrentDeal().getActivePlayer();
    }    

    private void startNewDeal() 
    {
        deals.add(new SingleDeal(first, second));
    }
    private SingleDeal getCurrentDeal()
    {
        return deals.get(deals.size()-1);
    }
    
    public Game(String firstPlayerName,String secondPlayerName) {
        first = new Player(firstPlayerName);
        second = new Player(secondPlayerName);
    }
    
    public void startNew()
    {
        startNewDeal();
    }
    
    public BaseResult tryMakeMoveByActivePlayer(Card card)
    {
        if(card==null)
            throw new NullPointerException("card");
        
        BaseResult moveResult = getCurrentDeal().tryMakeMoveByActivePlayer(card);
        if(moveResult.getResultType() == ResultType.DealFinished)
        {
            startNewDeal();
        }
       
        return moveResult;
    }
    
    public Player getFirst() {
        return first;
    }

    public Player getSecond() {
        return second;
    }
    
    public GameFinishedInfo getGameFinishedInfo()
    {
       if (!isFinished()) 
           throw new IllegalStateException("Game is not finished yet");
       return (first.getPoints() > second.getPoints() ?
               new GameFinishedInfo(first, second)
               :new GameFinishedInfo(second, first));
    }
    
    //private 
    /*
        1.Powolanie do zycia 2 graczy
        2.Powolanie do zycia Decku
        3.Potasowanie Decku
        4.Rozdanie kart
        5.Rogrywka(Konczy sie gdy jeden z graczy ma 250 pkt)
            5.1. Dodawanie kart do tricku
            5.2. Wyliczenie pkt za trick
            5.3. Sprawdzenie kto wygral trick
            5.4. Przyznac wygranemu pkt.
            5.5. Sprawdzic czy trzeba ponownie przetasowac talie
            5.6. Uzupelnienie kart do 12 jesli sa karty w decku
    */

    public Tricks getTricks() {
        return getCurrentDeal().getTricks();
    }
    
    public boolean isFinished()
    {
       return first.getPoints() > WIN_SCORE ||  second.getPoints() > WIN_SCORE;
    }
    public final int WIN_SCORE = 250;
       
}
