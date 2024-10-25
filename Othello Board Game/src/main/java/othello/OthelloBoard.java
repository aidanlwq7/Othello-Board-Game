package othello;

/**
 * Tracks all tokens on the Othello board and understands key aspects of the game, such as:
 * the initial board setup, player tokens ('X' and 'O'), valid coordinates, and whether a player has a move.
 * It also handles flipping the opponent's tokens when a move is made at a specific location.
 */

public class OthelloBoard {

    public static final char EMPTY = ' ', P1 = 'X', P2 = 'O', BOTH = 'B';
    private int dim = 8; // the dimensions of this board
    private char[][] board;

    /**
     * Initializes an empty dim x dim OthelloBoard, except for the center squares where there are
     * 2 P1 tokens and 2 P2 tokens diagonal to each other
     * Example: an 8 x 8 OthelloBoard has 4 tokens in its center, where
     * board[3][3] = board[4][4] = P1
     * board[3][4] = board[4][3] = P2
     *
     * @param dim the dimension of the OthelloBoard
     */
    public OthelloBoard(int dim) {
        this.dim = dim;
        board = new char[this.dim][this.dim];
        for (int row = 0; row < this.dim; row++) {
            for (int col = 0; col < this.dim; col++) {
                this.board[row][col] = EMPTY;
            }
        }
        int mid = this.dim / 2;
        this.board[mid - 1][mid - 1] = this.board[mid][mid] = P1;
        this.board[mid][mid - 1] = this.board[mid - 1][mid] = P2;
    }

    /**
     * @return the dimension of this OthelloBoard
     */
    public int getDimension() {
        return this.dim;
    }

    /**
     * @param player either P1 or P2
     * @return P2 or P1, the opposite of player
     */
    public static char otherPlayer(char player) {
        if (player == P1) {
            return P2;
        } else if (player == P2) {
            return P1;
        }
        return EMPTY;
    }

    /**
     * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
     * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
     * @return P1, P2 or EMPTY, EMPTY is returned for an invalid (row,col)
     */
    public char get(int row, int col) {
        if (this.validCoordinate(row, col)) {
            return this.board[row][col];
        }
        return EMPTY;
    }

    /**
     * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
     * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
     * @return whether (row,col) is a position on the board. Example: (6,12) is not
     * a position on the board.
     */
    private boolean validCoordinate(int row, int col) {
        int dimension = this.getDimension();
        return (0 <= row && row < dimension) && (0 <= col && col < dimension);
    }

    /**
     * Checks if (drow, dcol) is a valid direction
     *
     * @param drow the row direction, in {-1,0,1}
     * @param dcol the col direction, in {-1,0,1}
     * @return whether (drow, dcol) is a valid direction.
     */
    private boolean validDirection(int drow, int dcol) {
        return ((-1 <= drow && drow <= 1) &&
                (-1 <= dcol && dcol <= 1)) &&
                (drow != 0 || dcol != 0);
    }

    /**
     * Check if there is an alternation of P1 next to P2, starting at (row,col) in
     * direction (drow,dcol). That is, starting at (row,col) and heading in
     * direction (drow,dcol), you encounter a sequence of at least one P1 followed
     * by a P2, or at least one P2 followed by a P1. The board is not modified by
     * this method. Why is this method important? If
     * alternation(row,col,drow,dcol)==P1, then placing P1 right before (row,col),
     * assuming that square is EMPTY, is a valid move, resulting in a collection of
     * P2 being flipped.
     *
     * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
     * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
     * @param drow the row direction, in {-1,0,1}
     * @param dcol the col direction, in {-1,0,1}
     * @return P1, if there is an alternation P2 ...P2 P1, or P2 if there is an
     * alternation P1 ... P1 P2 in direction (dx,dy), EMPTY if there is no
     * alternation
     */
    private char alternation(int row, int col, int drow, int dcol) {
        if (this.validDirection(drow, dcol)) {
            char playerOnFirstTile = this.get(row, col);
            if (playerOnFirstTile != EMPTY) {
                int rowToCheck = row, colToCheck = col;
                while ((0 <= rowToCheck && rowToCheck < this.getDimension()) &&
                        (0 <= colToCheck && colToCheck < this.getDimension()) &&
                        this.get(rowToCheck, colToCheck) == playerOnFirstTile) {
                    rowToCheck += drow;
                    colToCheck += dcol;
                }

                if (this.get(rowToCheck, colToCheck) != playerOnFirstTile) {
                    return this.get(rowToCheck, colToCheck);
                }
            }
        }
        return EMPTY;
    }

    /**
     * sets the player token to the (row, col) tile of the board, only if
     * (row, col) is valid and player is a valid token, i.e. player = P1 or P2
     *
     * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
     * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
     * @param player either OthelloBoard.P1 or OthelloBoard.P2, the player token
     *               to be placed/flipped to
     */
    private void setBoardTile(int row, int col, char player) {
        if (this.validCoordinate(row, col) && (player == P1 || player == P2 || player == EMPTY)) {
            this.board[row][col] = player;
        }
    }

    /**
     * flip all other player tokens to player, starting at (row,col) in direction
     * (drow, dcol). Example: If (drow,dcol)=(0,1) and player==O then XXXO will
     * result in a flip to OOOO
     *
     * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
     * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
     * @param drow   the row direction, in {-1,0,1}
     * @param dcol   the col direction, in {-1,0,1}
     * @param player Either OthelloBoard.P1 or OthelloBoard.P2, the target token to
     *               flip to.
     * @return the number of other player tokens actually flipped, -1 if this is not
     * a valid move in this one direction, that is, EMPTY or the end of the
     * board is reached before seeing a player token.
     */
    private int flip(int row, int col, int drow, int dcol, char player) {
        if (this.validDirection(drow, dcol)) {
            if (this.alternation(row, col, drow, dcol) != EMPTY) {
                int tokensFlipped = 0, rowToCheck = row, colToCheck = col;
                while (this.get(rowToCheck, colToCheck) != player) {
                    this.setBoardTile(rowToCheck, colToCheck, player);
                    tokensFlipped += 1;
                    rowToCheck += drow;
                    colToCheck += dcol;
                }
                return tokensFlipped;
            }
        }
        return -1;
    }

    /**
     * Return which player has a move (row,col) in direction (drow,dcol).
     *
     * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
     * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
     * @param drow the row direction, in {-1,0,1}
     * @param dcol the col direction, in {-1,0,1}
     * @return P1, P2, EMPTY
     */
    private char hasMove(int row, int col, int drow, int dcol) {
        if (this.validDirection(drow, dcol)) {
            if (this.get(row, col) == EMPTY) {
                int rowToCheck = row + drow, colToCheck = col + dcol;
                return this.alternation(rowToCheck, colToCheck, drow, dcol);
            }
        }
        return EMPTY;
    }

    /**
     * @return whether P1,P2 or BOTH have a move somewhere on the board, EMPTY if
     * neither do.
     */
    public char hasMove() {
        boolean p1HasMove = false, p2HasMove = false;
        // goes over every tile on the board and checks every direction on each tile to see if
        // there are any moves left
        for (int row = 0; row < this.getDimension(); row++) {
            for (int col = 0; col < this.getDimension(); col++) {

                if (this.get(row, col) == EMPTY) {
                    for (int drow = -1; drow <= 1; drow++) {
                        for (int dcol = -1; dcol <= 1; dcol++) {
                            char playerThatHasMove = this.hasMove(row, col, drow, dcol);

                            if (playerThatHasMove == P1) {
                                p1HasMove = true;
                            } else if (playerThatHasMove == P2) {
                                p2HasMove = true;
                            }

                            if (p1HasMove && p2HasMove) {
                                return BOTH;
                            }
                        }
                    }

                }

            }
        }
        if (p1HasMove) {
            return P1;
        } else if (p2HasMove) {
            return P2;
        }
        return EMPTY;
    }

    /**
     * Make a move for player at position (row,col) according to Othello rules,
     * making appropriate modifications to the board. Nothing is changed if this is
     * not a valid move.
     *
     * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
     * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
     * @param player P1 or P2
     * @return true if player moved successfully at (row,col), false otherwise
     */
    public boolean move(int row, int col, char player) {
        if (this.validCoordinate(row, col) &&
                this.get(row, col) == EMPTY && // player can only make a move on empty squares
                (player == P1 || player == P2) &&
                (this.hasMove() == BOTH || this.hasMove() == player) // check if player has any possible moves
        ) {
            int totalTokensFlipped = 0;
            for (int drow = -1; drow <= 1; drow++) {
                for (int dcol = -1; dcol <= 1; dcol++) {
                    if (this.hasMove(row, col, drow, dcol) == player) {
                        // flips all flippable tokens starting from (row + drow, col + dcol) in the (drow, dcol)
                        // direction first, before placing the player token in (row, col)
                        int currTokensFlipped = this.flip(row + drow, col + dcol, drow, dcol, player);
                        if (currTokensFlipped > 0) {
                            totalTokensFlipped += currTokensFlipped;
                        }
                    }
                }
            }
            if (totalTokensFlipped > 0) {
                this.setBoardTile(row, col, player);
                return true;
            }
        }
        return false;
    }

    /**
     * @param player P1 or P2
     * @return the number of tokens on the board for player
     */
    public int getCount(char player) {
        int count = 0;
        if (player == P1 || player == P2) {
            for (int row = 0; row < this.getDimension(); row++) {
                for (int col = 0; col < this.getDimension(); col++) {
                    if (this.get(row, col) == player) {
                        count += 1;
                    }
                }
            }
        }
        return count;
    }

    /**
     * @return a string representation of this, just the play area, with no
     * additional information. DO NOT MODIFY THIS!!
     */
    public String toString() {
        /**
         * See assignment web page for sample output.
         */
        String s = "";
        s += "  ";
        for (int col = 0; col < this.dim; col++) {
            s += col + " ";
        }
        s += '\n';

        s += " +";
        for (int col = 0; col < this.dim; col++) {
            s += "-+";
        }
        s += '\n';

        for (int row = 0; row < this.dim; row++) {
            s += row + "|";
            for (int col = 0; col < this.dim; col++) {
                s += this.board[row][col] + "|";
            }
            s += row + "\n";

            s += " +";
            for (int col = 0; col < this.dim; col++) {
                s += "-+";
            }
            s += '\n';
        }
        s += "  ";
        for (int col = 0; col < this.dim; col++) {
            s += col + " ";
        }
        s += '\n';
        return s;
    }

    /**
     * @return a new copy of this OthelloBoard
     */
    public OthelloBoard getOthelloBoardCopy() {
        OthelloBoard newOthelloBoard = new OthelloBoard(this.getDimension());
        for (int row = 0; row < this.getDimension(); row++) {
            for (int col = 0; col < this.getDimension(); col++) {
                newOthelloBoard.setBoardTile(row, col, this.get(row, col));
            }
        }
        return newOthelloBoard;
    }

    /**
     * A quick test of OthelloBoard. Output is on assignment page.
     *
     * @param args
     */
    public static void main(String[] args) {
        OthelloBoard ob = new OthelloBoard(8);
        System.out.println(ob.toString());
        System.out.println("getCount(P1)=" + ob.getCount(P1));
        System.out.println("getCount(P2)=" + ob.getCount(P2));
        for (int row = 0; row < ob.dim; row++) {
            for (int col = 0; col < ob.dim; col++) {
                ob.board[row][col] = P1;
            }
        }
        System.out.println(ob.toString());
        System.out.println("getCount(P1)=" + ob.getCount(P1));
        System.out.println("getCount(P2)=" + ob.getCount(P2));

        // Should all be blank
        for (int drow = -1; drow <= 1; drow++) {
            for (int dcol = -1; dcol <= 1; dcol++) {
                System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
            }
        }

        for (int row = 0; row < ob.dim; row++) {
            for (int col = 0; col < ob.dim; col++) {
                if (row == 0 || col == 0) {
                    ob.board[row][col] = P2;
                }
            }
        }
        System.out.println(ob.toString());

        // Should all be P2 (O) except drow=0,dcol=0
        for (int drow = -1; drow <= 1; drow++) {
            for (int dcol = -1; dcol <= 1; dcol++) {
                System.out.println("direction=(" + drow + "," + dcol + ")");
                System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
            }
        }

        // Can't move to (4,4) since the square is not empty
        System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));

        ob.board[4][4] = EMPTY;
        ob.board[2][4] = EMPTY;

        System.out.println(ob.toString());

        for (int drow = -1; drow <= 1; drow++) {
            for (int dcol = -1; dcol <= 1; dcol++) {
                System.out.println("direction=(" + drow + "," + dcol + ")");
                System.out.println("hasMove at (4,4) in above direction =" + ob.hasMove(4, 4, drow, dcol));
            }
        }
        System.out.println("who has a move=" + ob.hasMove());
        System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));
        System.out.println(ob.toString());

    }
}
