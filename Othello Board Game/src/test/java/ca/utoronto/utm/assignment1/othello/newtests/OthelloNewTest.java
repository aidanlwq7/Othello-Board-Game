package ca.utoronto.utm.assignment1.othello.newtests;

import ca.utoronto.utm.assignment1.othello.Othello;
import ca.utoronto.utm.assignment1.othello.OthelloBoard;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class OthelloNewTest {
    Othello othello;

    @BeforeEach
    public void setUp() {
        othello = new Othello();
    }

    @Nested
    class OthelloGeneralConstructorTest {
        @Test
        public void testGeneralConstructorProperties() {
            assertEquals(0, othello.getNumMoves(), "a new othello game starts with 0 moves");
            assertEquals(OthelloBoard.P1, othello.getWhosTurn(), "the first player of a new game is OthelloBoard.P1");
        }
    }

    @Nested
    class OthelloGetWhosTurnTest {
        @Test
        public void testGetWhosTurnFirstRound() {
            assertEquals(OthelloBoard.P1, othello.getWhosTurn(), "first player of a game should be OthelloBoard.P1");
        }

        @Test
        public void testGetWhosTurnSecondRound() {
            othello.move(2, 4);
            assertEquals(OthelloBoard.P2, othello.getWhosTurn(), "Turn after the first player should be OthelloBoard.P2");
        }
    }

    @Nested
    class OthelloMoveTest {
        @Test
        public void testValidMove() {
            // if a valid move is played, then
            // 1) returns true
            // 2) change the current player to the opposing player if they can make a move
            // 3) the stat that holds the number of moves increases by 1
            // 4) the token is placed on the specified (row, col) tile
            assertEquals(OthelloBoard.P1, othello.getWhosTurn(), "first player");
            assertEquals(0, othello.getNumMoves(), "initial num moves count");
            assertTrue(othello.move(2, 4));
            assertEquals(OthelloBoard.P2, othello.getWhosTurn(), "second player's turn after move");
            assertEquals(1, othello.getNumMoves(), "num moves count after the move is made");
        }

        @Test
        public void testMoveInvalidMove() {
            // if an invalid move is played, then
            // 1) the stat that holds the number of moves remains the same
            // 2) the current player does not change
            // 3) returns false
            assertEquals(OthelloBoard.P1, othello.getWhosTurn(), "first player");
            assertEquals(0, othello.getNumMoves(), "initial num moves count");
            assertFalse(othello.move(0, 0));
            assertEquals(OthelloBoard.P1, othello.getWhosTurn(), "should still be first player turn after invalid move is made");
            assertEquals(0, othello.getNumMoves(), "num moves count should be the same after invalid move is made");
        }
    }

    @Nested
    class OthelloGetCountTest {
        @Test
        public void testGetCountNewGame() {
            assertEquals(2, othello.getCount(OthelloBoard.P1), "the 2 P1 tokens at the center");
            assertEquals(2, othello.getCount(OthelloBoard.P2), "the 2 P2 tokens at the center");
        }

        @Test
        public void testGetCountAfterMove() {
            assertEquals(2, othello.getCount(OthelloBoard.P1), "initial P1 count");
            othello.move(2, 4);
            assertEquals(4, othello.getCount(OthelloBoard.P1), "P1 count after the move is made");
        }
    }

    @Nested
    class OthelloGetWinnerTest {
        @Test
        public void testGetWinnerGameNotOver() {
            assertFalse(othello.isGameOver(), "game not over at the start of a new game");
            assertEquals(OthelloBoard.EMPTY, othello.getWinner(), "no winner when game is not over");
        }
    }

    @Nested
    class OthelloIsGameOverTest {
        @Test
        public void testIsGameOverNewGame() {
            assertFalse(othello.isGameOver(), "a new game still has many moves left for both players");
        }
    }

    @Nested
    class OthelloGetOthelloBoardCopy {
        @Test
        public void testGetOthelloBoardCopy() {
            OthelloBoard copiedBoard = othello.getOthelloBoardCopy();
            assertEquals(othello.getBoardString(), copiedBoard.toString());
        }

        @Test
        public void testGetOthelloBoardCopyDoesNotAffectOriginal() {
            OthelloBoard copiedBoard = othello.getOthelloBoardCopy();
            copiedBoard.move(2, 4, OthelloBoard.P1);
            assertNotEquals(othello.getCount(OthelloBoard.P1), copiedBoard.getCount(OthelloBoard.P1), "Original and copied board should not have the same number of tokens after a move is made");
        }

    }

    @Nested
    class OthelloGetNumMovesTest {
        @Test
        public void testGetNumMovesNewGame() {
            assertEquals(0, othello.getNumMoves(), "New game of othello should have 0 moves made");
        }

        @Test
        public void testGetNumMovesUpdateAfterMove() {
            assertEquals(0, othello.getNumMoves(), "Initial number of moves made");
            othello.move(2, 4);
            assertEquals(1, othello.getNumMoves(), "Number of moves after a move is made");

            othello.move(2, 3);
            othello.move(2, 2);
            othello.move(4, 5);
            assertEquals(4, othello.getNumMoves());
        }
    }

    @Nested
    class OthelloGetBoardString {
        @Test
        public void testGetBoardString() {
            String expected = "  0 1 2 3 4 5 6 7 \n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "0| | | | | | | | |0\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "1| | | | | | | | |1\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "2| | | | | | | | |2\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "3| | | |X|O| | | |3\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "4| | | |O|X| | | |4\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "5| | | | | | | | |5\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "6| | | | | | | | |6\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "7| | | | | | | | |7\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "  0 1 2 3 4 5 6 7 \n";
            assertEquals(expected, othello.getBoardString());
        }

        @Test
        public void testGetBoardStringUpdateAfterMove() {
            othello.move(2, 4);
            String expected = "  0 1 2 3 4 5 6 7 \n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "0| | | | | | | | |0\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "1| | | | | | | | |1\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "2| | | | |X| | | |2\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "3| | | |X|X| | | |3\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "4| | | |O|X| | | |4\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "5| | | | | | | | |5\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "6| | | | | | | | |6\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "7| | | | | | | | |7\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "  0 1 2 3 4 5 6 7 \n";
            assertEquals(expected, othello.getBoardString(), "Board string is updated to reflect the board after a move has been made");
        }
    }

}