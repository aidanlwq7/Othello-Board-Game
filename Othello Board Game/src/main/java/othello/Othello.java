package othello;

import java.util.Random;

/**
 * Captures an Othello game, including the OthelloBoard, move count, and the current turn (OthelloBoard.P1 or OthelloBoard.P2).
 * It handles moves on the board and provides game statistics, such as the token count for P1 and P2.
 * It also determines the winner and knows when the game is over.
 */
public class Othello {
    public static final int DIMENSION = 8; // This is an 8x8 game
    private char whosTurn = OthelloBoard.P1; // P1 moves first!
    private int numMoves = 0;
    private OthelloBoard othelloBoard = new OthelloBoard(DIMENSION);

    /**
     * return P1,P2 or EMPTY depending on who moves next.
     *
     * @return P1, P2 or EMPTY
     */
    public char getWhosTurn() {
        if (!this.isGameOver() && (whosTurn == OthelloBoard.P1 || whosTurn == OthelloBoard.P2)) {
            return whosTurn;
        }
        return OthelloBoard.EMPTY;
    }

    /**
     * Attempt to make a move for P1 or P2 (depending on whos turn it is) at
     * position row, col. A side effect of this method is modification of whos turn
     * and the move count.
     *
     * @param row the row of the move being made
     * @param col the column of the move being made
     * @return whether the move was successfully made.
     */
    public boolean move(int row, int col) {
        char currPlayer = this.getWhosTurn();
        boolean result = othelloBoard.move(row, col, currPlayer);
        if (result) {
            numMoves += 1;
            if (othelloBoard.hasMove() == OthelloBoard.BOTH ||
                    othelloBoard.hasMove() == OthelloBoard.otherPlayer(currPlayer)) {
                whosTurn = OthelloBoard.otherPlayer(currPlayer);
            }
        }
        return result;
    }

    /**
     * @param player P1 or P2
     * @return the number of tokens for player on the board
     */
    public int getCount(char player) {
        return othelloBoard.getCount(player);
    }

    /**
     * Returns the winner of the game.
     *
     * @return P1, P2 or EMPTY for no winner, or the game is not finished.
     */
    public char getWinner() {
        if (this.isGameOver()) {
            int P1NumTokens = this.getCount(OthelloBoard.P1);
            int P2NumTokens = this.getCount(OthelloBoard.P2);
            if (P1NumTokens > P2NumTokens) {
                return OthelloBoard.P1;
            } else if (P2NumTokens > P1NumTokens) {
                return OthelloBoard.P2;
            }
        }
        return OthelloBoard.EMPTY;
    }

    /**
     * @return whether the game is over (no player can move next)
     */
    public boolean isGameOver() {
        return this.othelloBoard.hasMove() == OthelloBoard.EMPTY;
    }

    /**
     * @return a string representation of the board.
     */
    public String getBoardString() {
        return this.othelloBoard.toString();
    }

    /**
     * @return a copy of this Othello object's OthelloBoard
     */
    public OthelloBoard getOthelloBoardCopy() {
        return this.othelloBoard.getOthelloBoardCopy();
    }

    /**
     * @return the number of moves that have been made throughout this othello game
     */
    public int getNumMoves() {
        return this.numMoves;
    }

    /**
     * run this to test the current class. We play a completely random game. DO NOT
     * MODIFY THIS!! See the assignment page for sample outputs from this.
     *
     * @param args
     */
    public static void main(String[] args) {
        Random rand = new Random();

        Othello o = new Othello();
        System.out.println(o.getBoardString());
        while (!o.isGameOver()) {
            int row = rand.nextInt(8);
            int col = rand.nextInt(8);

            if (o.move(row, col)) {
                System.out.println("makes move (" + row + "," + col + ")");
                System.out.println(o.getBoardString() + o.getWhosTurn() + " moves next");
            }
        }

    }
}