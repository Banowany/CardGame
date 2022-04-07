/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.move.results;

import cardgame.SingleDeal;


public class InvalidCardOwner extends ErrorResult 
{

    public InvalidCardOwner() 
    {
        super(ResultType.InvalidCardOwner);        
    }
    

    @Override
    public void handle(SingleDeal.SingleDealProxy playerSwapper) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
