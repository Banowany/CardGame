/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.move.results;


public abstract class ErrorResult extends BaseResult 
{

    public ErrorResult(ResultType resultType) {
        super(resultType);
    }

    @Override
    public boolean isItError()
    {
        return true;
    }
}
