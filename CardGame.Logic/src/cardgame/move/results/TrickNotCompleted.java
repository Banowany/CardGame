/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.move.results;

import cardgame.SingleDeal;

/**
 *
 * @author KG Studia
 */
public class TrickNotCompleted extends OkResult
{

    public TrickNotCompleted() 
    {
        super(ResultType.TrickNotCompleted);
    }
    
    @Override
    public void handle(SingleDeal.SingleDealProxy proxy) 
    {
        proxy.swapActivePlayer();   
    }
}
