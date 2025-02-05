package othello.controllers;

import othello.OthelloBoard;
import othello.players.PlayerGreedy;
import othello.players.PlayerHuman;

/**
 * Controller for a game where Human player (P1) plays against a computer (P2)
 * using a greedy strategy.
 */
public class OthelloControllerHumanVSGreedy extends OthelloController {

    /**
     * Constructs an OthelloControllerHumanVsGreedy with a new Othello game, ready to play
     * with a human player P1 and a greedy player P2.
     */
    public OthelloControllerHumanVSGreedy() {
        super();
        this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
        this.player2 = new PlayerGreedy(this.othello, OthelloBoard.P2);
    }

    /**
     * Run main to play a Human (P1) against the computer P2.
     * The computer uses a greedy strategy, that is, it picks the first
     * move which maximizes its number of token on the board.
     * The output should be almost identical to that of OthelloControllerHumanVSHuman.
     *
     * @param args
     */
    public static void main(String[] args) {
        OthelloControllerHumanVSGreedy oc = new OthelloControllerHumanVSGreedy();
        oc.play();
    }
}
