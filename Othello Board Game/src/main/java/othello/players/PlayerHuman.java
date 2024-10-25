package othello.players;

import othello.Move;
import othello.Othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * PlayerHuman represents a human player who selects a move by entering the desired row and column from the board.
 */
public class PlayerHuman extends Player {

    private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter 1-8";
    private static final String IO_ERROR_MESSAGE = "I/O Error";
    private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    /**
     * initializes a PlayerHuman object.
     *
     * @param othello the Othello object associated with this player
     * @param player  this player's player token, either OthelloBoard.P1 or OthelloBoard.P2
     */
    public PlayerHuman(Othello othello, char player) {
        super(othello, player);
    }

    /**
     * prompts the player to enter the row and column they want to make a move on.
     *
     * @return the Move object associated with the row and column they entered
     */
    @Override
    public Move getMove() {
        int row = getMove("row: ");
        int col = getMove("col: ");
        return new Move(row, col);
    }

    /**
     * prompts the player with a message to ask them to enter the move they want to make and continually warns and asks
     * the player again if they entered an invalid move.
     *
     * @param message the message to be prompted to the player for their move
     * @return the move they entered as an integer, or -1 if an IOException was caught
     */
    private int getMove(String message) {

        int move, lower = 0, upper = 7;
        while (true) {
            try {
                System.out.print(message);
                String line = PlayerHuman.stdin.readLine();
                move = Integer.parseInt(line);
                if (lower <= move && move <= upper) {
                    return move;
                } else {
                    System.out.println(INVALID_INPUT_MESSAGE);
                }
            } catch (IOException e) {
                System.out.println(IO_ERROR_MESSAGE);
                break;
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
        return -1;
    }
}
