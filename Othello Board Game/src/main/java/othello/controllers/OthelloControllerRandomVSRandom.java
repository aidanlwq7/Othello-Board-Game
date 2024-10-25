package othello.controllers;

import othello.OthelloBoard;
import othello.players.PlayerRandom;

/**
 * Controller for a game where computers P1 and P2, both using random strategies, play against each other.
 */
public class OthelloControllerRandomVSRandom extends OthelloController {

    /**
     * Constructs an OthelloControllerRandomVsRandom with a new Othello game, ready to play
     * with a random player P1 and another random player P2.
     */
    public OthelloControllerRandomVSRandom() {
        super();
        this.player1 = new PlayerRandom(this.othello, OthelloBoard.P1);
        this.player2 = new PlayerRandom(this.othello, OthelloBoard.P2);
    }

    /**
     * Run main to execute the simulation and print out the two line results.
     * Output looks like
     * Probability P1 wins=.75
     * Probability P2 wins=.20
     *
     * @param args
     */
    public static void main(String[] args) {
        int p1wins = 0, p2wins = 0, numGames = 10000;

        for (int games = 0; games < numGames; games++) {
            OthelloControllerRandomVSRandom oc = new OthelloControllerRandomVSRandom();
            oc.play();
            if (oc.othello.getWinner() == OthelloBoard.P1) {
                p1wins += 1;
            } else if (oc.othello.getWinner() == OthelloBoard.P2) {
                p2wins += 1;
            }
        }

        System.out.println("Probability P1 wins=" + (float) p1wins / numGames);
        System.out.println("Probability P2 wins=" + (float) p2wins / numGames);
    }
}
