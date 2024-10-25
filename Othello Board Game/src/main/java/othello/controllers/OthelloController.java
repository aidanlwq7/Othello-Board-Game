package othello.controllers;

import othello.Move;
import othello.Othello;
import othello.OthelloBoard;
import othello.players.Player;

/**
 * An OthelloController simulates an Othello game between 2 players.
 */
public abstract class OthelloController {
    protected Othello othello;
    protected Player player1, player2;

    /**
     * Constructs a new OthelloController with a new Othello game.
     */
    public OthelloController() {
        this.othello = new Othello();
    }

    /**
     * @return the othello game associated with this controller.
     */
    public Othello getOthello() {
        return this.othello;
    }

    /**
     * @return the players of this controller in the form [player1, player2].
     */
    public Player[] getPlayers() {
        Player[] players = new Player[2];
        players[0] = this.player1;
        players[1] = this.player2;
        return players;
    }

    /**
     * Starts the simulation of an Othello game between 2 players.
     */
    public void play() {
        while (!othello.isGameOver()) {
            this.report();

            Move move = null;
            char whosTurn = othello.getWhosTurn();

            if (whosTurn == OthelloBoard.P1)
                move = player1.getMove();
            if (whosTurn == OthelloBoard.P2)
                move = player2.getMove();

            this.reportMove(whosTurn, move);
            othello.move(move.getRow(), move.getCol());
        }
        this.reportFinal();
    }

    /**
     * Reports the latest move made by the player
     *
     * @param whosTurn the player whose turn it is, either OthelloBoard.P1 or OthelloBoard.P2
     * @param move     the move the player made
     */
    private void reportMove(char whosTurn, Move move) {
        System.out.println(whosTurn + " makes move " + move + "\n");
    }

    /**
     * Logs the current display of the board, the number of tokens each player has and reports whose turn it is next.
     */
    private void report() {

        String s = othello.getBoardString() + OthelloBoard.P1 + ":"
                + othello.getCount(OthelloBoard.P1) + " "
                + OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2) + "  "
                + othello.getWhosTurn() + " moves next";
        System.out.println(s);
    }

    /**
     * Reports the display of the board, the number of tokens each player has and announces the player who won the game.
     */
    private void reportFinal() {

        String s = othello.getBoardString() + OthelloBoard.P1 + ":"
                + othello.getCount(OthelloBoard.P1) + " "
                + OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2)
                + "  " + othello.getWinner() + " won\n";
        System.out.println(s);
    }
}
