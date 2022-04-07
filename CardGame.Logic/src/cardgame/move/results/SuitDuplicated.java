/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.move.results;

import cardgame.SingleDeal;
import cardgame.move.Move;


public class SuitDuplicated extends ErrorResult {

    private final Move move;

    public SuitDuplicated(Move move) 
    {
        super(ResultType.SuitDuplicated);
        this.move = move;
    }

    @Override
    public void handle(SingleDeal.SingleDealProxy playerSwapper) {
        move.registerSuitDuplication(playerSwapper.getCurrentTrickSize());
        //wpisac do odopowiedniego gracza, informacje o indexie karty ktora gracz powtorzyl
        //W TRICKCOMPLETED: dodać resetowanie kroku powyzej
    }
    
}
