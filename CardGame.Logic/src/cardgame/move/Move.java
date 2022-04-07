/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.move;

import cardgame.Card;
import cardgame.Player;

import java.util.Optional;

/*
    Klasa reprezentująca ruch gracza
    Ps. Instacja tej klasy nie powstanie bez funkcji fabrykującej
 */
public class Move 
{
    private final Card card;
    private final Player activePlayer;
    private final Player otherPlayer;

    public Player getOtherPlayer() {
        return otherPlayer;
    }

    public Card getCard() {
        return card;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

//    public Hand getPlayerHand()
//    {
//        return player.getHand();
//    }
    
    private Move(Card card, Player activePlayer, Player otherPlayer)
    {
        this.card = card;
        this.activePlayer = activePlayer;
        this.otherPlayer = otherPlayer;
    }
    
    //Stworzy instancje jeśli podana karta należy do gracza
    
    
    public static Optional<Move> tryCreate(Card card, Player player,Player otherPlayer)
    {
        if (!player.getHand().contains(card))
            return Optional.empty();
        return Optional.of(new Move(card,player, otherPlayer));
    }

    public void registerSuitDuplication(int duplicatedCardTrickIndex) 
    {
        getActivePlayer().registerSuitDuplication(duplicatedCardTrickIndex);
    }
   
}
