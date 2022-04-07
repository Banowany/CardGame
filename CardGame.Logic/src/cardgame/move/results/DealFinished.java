package cardgame.move.results;

import cardgame.SingleDeal;


public class DealFinished extends OkResult 
{

    public DealFinished(BaseResult moveResult) {
        super(ResultType.DealFinished);
        this.originalMoveResult = moveResult;
    }
    
    private final BaseResult originalMoveResult;

    public BaseResult getOriginalMoveResult() {
        return originalMoveResult;
    }
    

    @Override
    public void handle(SingleDeal.SingleDealProxy playerSwapper) {
       
    }
     
}
