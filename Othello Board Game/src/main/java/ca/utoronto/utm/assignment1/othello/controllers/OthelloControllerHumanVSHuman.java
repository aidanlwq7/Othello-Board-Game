package ca.utoronto.utm.assignment1.othello.controllers;

import ca.utoronto.utm.assignment1.othello.OthelloBoard;
import ca.utoronto.utm.assignment1.othello.players.PlayerHuman;

/**
 * This controller uses the Model classes to allow the human player P1 to play against
 * another human player P2.
 * <p>
 * Run the main from this class to play two humans against each other. Only
 * minimal modifications to this class are permitted.
 *
 * @author arnold
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
