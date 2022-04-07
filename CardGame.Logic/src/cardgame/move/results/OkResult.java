/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.move.results;

import cardgame.SingleDeal;


public abstract class OkResult extends BaseResult 
{

    public OkResult(ResultType resultType) {
        super(resultType);
    }
    

    @Override
    public boolean isItError() {
        return false;
    }
    
}
