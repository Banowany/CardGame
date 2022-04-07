/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author KG Studia
 */
public class GameFinishedInfo {
    private Player winner;
    private Player loser;

    public GameFinishedInfo(Player winner, Player loser) {
        this.winner = winner;
        this.loser = loser;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }

    @Override
    public String toString() {
        return "GameFinishedInfo{" + "winner=" + winner + ", loser=" + loser + '}';
    }

    
}
