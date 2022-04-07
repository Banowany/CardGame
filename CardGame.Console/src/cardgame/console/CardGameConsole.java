package cardgame.console;

import cardgame.*;
import cardgame.cardsCollection.Hand;
import cardgame.move.results.DealFinished;
import cardgame.move.results.ResultType;
import static cardgame.move.results.ResultType.*;
import cardgame.move.results.TrickCompleted;

public class CardGameConsole {

    public static void main(String[] args)
    {
        UserCardProvider cardProvider = null;

        Game game = new Game("Gracz 1","Gracz 2");
        
        game.startNew();

        while (!game.isFinished()) {
            Hand activeHand = game.getActivePlayer().getHand();
           
            cardProvider = new RandomCardProvider(activeHand);

            System.out.println(activeHand);
           
            Card card = null;
            do {
                card = cardProvider.get();
                System.out.println("Ruch gracza: " + game.getActivePlayer().getName() + " - " + card);
            
            } while (handleMove(game,card));
                                    
            System.out.println(game.getTricks());
        }
        System.out.println(game.getGameFinishedInfo());
        
    }

    private static boolean handleMove(Game game,Card card) {
        var moveResult = game.tryMakeMoveByActivePlayer(card);
        
        switch(moveResult.getResultType())
        {
            //SuitDuplicated,InvalidCardOwner,DealFinished,
            // NextMoveNotPossible
            case TrickCompleted:
                TrickCompleted trickCompleted = (TrickCompleted)moveResult;
                System.out.println("Zwyciezca tricku jest: " + trickCompleted.getWinner().getName());
                break;
            case SuitDuplicated:
                System.out.println("Zdublikowany kolor");
                break;
            case InvalidCardOwner:
                System.out.println("Nie jestes wlascicielem tej karty");
                break;
            case DealFinished:
                var originalResult = ((DealFinished) moveResult).getOriginalMoveResult();
                switch(originalResult.getResultType())
                {
                    case NextMoveNotPossible:                        
                        //string interpolation
                        System.out.println("Nie można wykonać ruchu" + System.lineSeparator() + "Zaczynam nowe rozdanie");
                        break;
                    default:
                        System.out.println("Zaczynam nowe rozdanie");
                        break;
                }
                System.out.println(game.getFirst() + " " + game.getSecond());
                break;
        }
        
        return moveResult.isItError();
    }

}
