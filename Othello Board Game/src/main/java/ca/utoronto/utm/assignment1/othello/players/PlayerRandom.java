package ca.utoronto.utm.assignment1.othello.players;

import ca.utoronto.utm.assignment1.othello.Move;
import ca.utoronto.utm.assignment1.othello.Othello;
import ca.utoronto.utm.assignment1.othello.OthelloBoard;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayerRandom makes a move by first determining all possible moves that this
 * player can make, putting them in an ArrayList, and then randomly choosing one
 * of them.
 *
 * @author arnold
 */
public class PlayerRandom extends Player {
    private Random rand = new Random();

    /**
     * Initializes a PlayerRandom object.
     *
     * @param othello the Othello object associated with this player
     * @param player  this player's player token, either OthelloBoard.P1 or OthelloBoard.P2.
     */
    public PlayerRandom(Othello othello, char player) {
        super(othello, player);
    }

    /**
     * Considers all the possible moves that this player can make and selects a random one.
     *
     * @return a random move from all the possible moves this player could have made, or null if no more moves
     * can be made.
     */
    @Override
    public Move getMove() {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        char playerToken = getPlayerToken();
        for (int row = 0; row < Othello.DIMENSION; row++) {
            for (int col = 0; col < Othello.DIMENSION; col++) {
                // gets a new fresh copy of the board every iteration
                OthelloBoard tempOthelloBoard = getOthello().getOthelloBoardCopy();
                if (tempOthelloBoard.move(row, col, playerToken)) {
                    possibleMoves.add(new Move(row, col));
                }
            }
        }
        if (!possibleMoves.isEmpty()) {
            int randomIndex = rand.nextInt(possibleMoves.size());
            return possibleMoves.get(randomIndex);
        }
        return null;
    }
}
