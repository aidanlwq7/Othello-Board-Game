package othello.controllers;

import othello.OthelloBoard;
import othello.players.PlayerHuman;

/**
 * Controller for a game between two human players (P1 and P2).
 * Run main to start the game.
 */

public class OthelloControllerHumanVSHuman extends OthelloController {

    /**
     * Constructs an OthelloControllerHumanVsHuman with a new Othello game, ready to play with two human players
     * at the console.
     */
    public OthelloControllerHumanVSHuman() {
        super();
        this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
        this.player2 = new PlayerHuman(this.othello, OthelloBoard.P2);
    }

    /**
     * Run main to play two Humans against each other at the console.
     *
     * @param args
     */
    public static void main(String[] args) {
        OthelloControllerHumanVSHuman oc = new OthelloControllerHumanVSHuman();
        oc.play();
    }

}
