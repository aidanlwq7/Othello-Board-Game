package othello.players;

import othello.Move;
import othello.Othello;
import othello.OthelloBoard;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayerRandom selects a move by gathering all possible moves into an ArrayList
 * and randomly choosing one.
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
