package ca.utoronto.utm.assignment1.othello.players;

import ca.utoronto.utm.assignment1.othello.Move;
import ca.utoronto.utm.assignment1.othello.Othello;
import ca.utoronto.utm.assignment1.othello.OthelloBoard;

/**
 * PlayerGreedy makes a move by considering all possible moves that the player
 * can make. Each move leaves the player with a total number of tokens.
 * getMove() returns the first move which maximizes the number of
 * tokens owned by this player. In case of a tie, between two moves,
 * (row1,column1) and (row2,column2) the one with the smallest row wins. In case
 * both moves have the same row, then the smaller column wins.
 * <p>
 * Example: Say moves (2,7) and (3,1) result in the maximum number of tokens for
 * this player. Then (2,7) is returned since 2 is the smaller row.
 * <p>
 * Example: Say moves (2,7) and (2,4) result in the maximum number of tokens for
 * this player. Then (2,4) is returned, since the rows are tied, but (2,4) has
 * the smaller column.
 * <p>
 * See the examples supplied in the assignment handout.
 *
 * @author arnold
 */

public class PlayerGreedy extends Player {

    /**
     * Initializes a PlayerGreedy object.
     *
     * @param othello the Othello object associated with this player
     * @param player  this player's player token, either OthelloBoard.P1 or OthelloBoard.P2
     */
    public PlayerGreedy(Othello othello, char player) {
        super(othello, player);
    }

    /**
     * Compares two moves and selects the best move according to the PlayerGreedy's approach:
     * Select firstMove or secondMove depending on which has the smaller row size.
     * In case of a tie, select the one with the smaller column size.
     *
     * @param firstMove  the first move to compare
     * @param secondMove the second move to compare
     * @return the chosen move based on how PlayerGreedy selects their move
     */
    private Move getBestMove(Move firstMove, Move secondMove) {
        if (firstMove.getRow() < secondMove.getRow()) {
            return firstMove;
        } else if (secondMove.getRow() < firstMove.getRow()) {
            return secondMove;
        } else {
            if (firstMove.getCol() < secondMove.getCol()) {
                return firstMove;
            } else {
                return secondMove;
            }
        }
    }

    /**
     * Looks through the OthelloBoard for a move that leaves the player with the most amount of their tokens after
     * the move is played.
     *
     * @return the move that maximizes the number of tokens owned by this player
     */
    @Override
    public Move getMove() {
        char playerToken = getPlayerToken();
        int mostTokensAfterMove = getOthello().getCount(playerToken);
        Move bestMove = new Move(0, 0);

        for (int row = 0; row < Othello.DIMENSION; row++) {
            for (int col = 0; col < Othello.DIMENSION; col++) {
                // gets a new fresh copy of the board every iteration
                OthelloBoard tempOthelloBoard = getOthello().getOthelloBoardCopy();
                if (tempOthelloBoard.move(row, col, playerToken)) {
                    int currTokensAfterMove = tempOthelloBoard.getCount(playerToken);
                    if (currTokensAfterMove > mostTokensAfterMove) {
                        bestMove = new Move(row, col);
                        mostTokensAfterMove = currTokensAfterMove;
                    } else if (currTokensAfterMove == mostTokensAfterMove) {
                        bestMove = this.getBestMove(new Move(row, col), bestMove);
                    }
                }
            }
        }
        return bestMove;
    }
}
