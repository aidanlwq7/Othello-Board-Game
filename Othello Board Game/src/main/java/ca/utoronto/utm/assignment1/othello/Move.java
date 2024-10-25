package ca.utoronto.utm.assignment1.othello;

/**
 * A Move represents the row and column of a tile of the board that a player has made a move on.
 *
 * @author arnold
 */
public class Move {
    private int row, col;

    /**
     * Initializes a move with a specified row and column of the Othello board
     *
     * @param row the row of the tile the move was made on
     * @param col the column of the tile the move was made on
     */
    public Move(int row, int col) {
        if (row >= 0 && col >= 0) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * @return the row of this move
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the column of this move
     */
    public int getCol() {
        return col;
    }

    /**
     * @return the string representation of this move in the form (row, column)
     */
    public String toString() {
        return "(" + this.row + "," + this.col + ")";
    }
}
