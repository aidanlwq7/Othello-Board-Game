package othello.players;

import othello.Move;
import othello.Othello;
import othello.OthelloBoard;

/**
 * PlayerGreedy selects a move by evaluating all possible moves and choosing the one
 * that maximizes the player's tokens. If two moves result in the same number of tokens,
 * the move with the smallest row wins. If rows are tied, the move with the smaller column wins.
 * <p>
 * Example 1: Between moves (2,7) and (3,1), (2,7) is chosen because 2 is the smaller row.
 * Example 2: Between moves (2,7) and (2,4), (2,4) is chosen because the rows are tied and 4 is the smaller column.
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
