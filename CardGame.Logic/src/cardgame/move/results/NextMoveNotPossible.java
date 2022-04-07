/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.move.results;

import cardgame.SingleDeal;


public class NextMoveNotPossible extends OkResult {

    public NextMoveNotPossible() 
    {
        super(ResultType.NextMoveNotPossible);
    }

    @Override
    public void handle(SingleDeal.SingleDealProxy playerSwapper) 
    {
    }
    
}
