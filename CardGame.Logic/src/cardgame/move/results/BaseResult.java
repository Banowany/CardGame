/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.move.results;

import cardgame.SingleDeal;

/*
    Przechowuje wynik próby przekazania karty z ręki do tricku
 */
public abstract class BaseResult 
{
    private final ResultType resultType;

    public BaseResult(ResultType resultType) {
        this.resultType = resultType;
    }
    
    public abstract void handle(SingleDeal.SingleDealProxy dealProxy);
    
    public abstract boolean isItError();
    
    public ResultType getResultType() {
        return resultType;
    }
    
}
