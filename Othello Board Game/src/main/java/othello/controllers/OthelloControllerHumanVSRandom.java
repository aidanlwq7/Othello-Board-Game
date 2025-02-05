package othello.controllers;

import othello.OthelloBoard;
import othello.players.PlayerHuman;
import othello.players.PlayerRandom;

/**
 * Controller for a game where Human player (P1) plays against a computer (P2)
 * using a random strategy.
 */
public class OthelloControllerHumanVSRandom extends OthelloController {

    /**
     * Constructs an OthelloControllerHumanVsRandom with a new Othello game, ready to play
     * with a human player P1 and a random player P2.
     */
    public OthelloControllerHumanVSRandom() {
        super();
        this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
        this.player2 = new PlayerRandom(this.othello, OthelloBoard.P2);
    }

    /**
     * Run main to play a Human (P1) against the computer P2.
     * The computer uses a random strategy, that is, it randomly picks
     * one of its possible moves.
     * The output should be almost identical to that of OthelloControllerHumanVSHuman.
     *
     * @param args
     */
    public static void main(String[] args) {
        OthelloControllerHumanVSRandom oc = new OthelloControllerHumanVSRandom();
        oc.play();
    }
}
