package ca.utoronto.utm.assignment1.othello.players;

import ca.utoronto.utm.assignment1.othello.Move;
import ca.utoronto.utm.assignment1.othello.Othello;

/**
 * A Player has an associated Othello object, a player token and a particular way
 * of making a move, depending on what kind of player they are.
 */
public abstract class Player {
    private Othello othello;
    private char player;

    /**
     * initializes a Player object with a specified othello object and player token
     *
     * @param othello the associated othello object
     * @param player  this player's player token, either OthelloBoard.P1 or OthelloBoard.P2
     */
    public Player(Othello othello, char player) {
        this.othello = othello;
        this.player = player;
    }

    /**
     * @return the Othello object associated with this Player
     */
    public Othello getOthello() {
        return this.othello;
    }

    /**
     * returns this player's player token
     *
     * @return this player's token, either OthelloBoard.P1 or OthelloBoard.P2
     */
    public char getPlayerToken() {
        return this.player;
    }

    /**
     * Considers the move this player will make depending on the type of player they are, and returns it.
     *
     * @return the move this player will make
     */
    public abstract Move getMove();

}
