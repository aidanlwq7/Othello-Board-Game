package ca.utoronto.utm.assignment1.othello.newtests;

import ca.utoronto.utm.assignment1.othello.OthelloBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class OthelloBoardNewTest {
    OthelloBoard board;
    Random rand = new Random();
    int DIMENSION = 8;

    @Nested
    class OthelloBoardConstructorTests {
        @BeforeEach
        public void setUpBoard() {
            DIMENSION = rand.nextInt(8, 100);
            board = new OthelloBoard(DIMENSION);
        }

        @Test
        public void testDimension() {
            assertEquals(DIMENSION, board.getDimension());
        }

        @Test
        public void testCenterHas2CorrespondingP1AndP2Tokens() {
            int mid = DIMENSION / 2;
            assertEquals(OthelloBoard.P1, board.get(mid - 1, mid - 1));
            assertEquals(OthelloBoard.P2, board.get(mid - 1, mid));
            assertEquals(board.get(mid - 1, mid - 1), board.get(mid, mid));
            assertEquals(board.get(mid - 1, mid), board.get(mid, mid - 1));
        }

        @Test
        public void testBoardIsEmptyExceptCenter() {
            int mid = DIMENSION / 2;
            for (int row = 0; row < DIMENSION; row++) {
                for (int col = 0; col < DIMENSION; col++) {
                    if ((row == mid - 1 || row == mid) && (col == mid - 1 || col == mid)) continue;
                    assertEquals(OthelloBoard.EMPTY, board.get(row, col));
                }
            }
        }
    }

    @Nested
    class OthelloBoardGetDimensionTests {
        @BeforeEach
        public void setUpBoard() {
            DIMENSION = rand.nextInt(8, 1000);
            board = new OthelloBoard(DIMENSION);
        }

        @Test
        public void testGetDimension() {
            assertEquals(DIMENSION, board.getDimension());

            OthelloBoard board1 = new OthelloBoard(4);
            assertEquals(4, board1.getDimension());

            OthelloBoard board2 = new OthelloBoard(10);
            assertEquals(10, board2.getDimension());

            OthelloBoard board3 = new OthelloBoard(1000);
            assertEquals(1000, board3.getDimension());
        }
    }

    @Nested
    class OthelloBoardOtherPlayerTests {
        @Test
        public void testOtherPlayerP1GetsP2() {
            assertEquals(OthelloBoard.P2, OthelloBoard.otherPlayer(OthelloBoard.P1));
        }

        @Test
        public void testOtherPlayerP2GetsP1() {
            assertEquals(OthelloBoard.P1, OthelloBoard.otherPlayer(OthelloBoard.P2));
        }

        @Test
        public void testOtherPlayerOthersGetsEmpty() {
            assertEquals(OthelloBoard.EMPTY, OthelloBoard.otherPlayer(OthelloBoard.EMPTY));
            assertEquals(OthelloBoard.EMPTY, OthelloBoard.otherPlayer(OthelloBoard.BOTH));
            assertEquals(OthelloBoard.EMPTY, OthelloBoard.otherPlayer('C'));
        }
    }

    @Nested
    class OthelloBoardGetTests {
        @BeforeEach
        public void setUp() {
            board = new OthelloBoard(8);
            //   0 1 2 3 4 5 6 7
            //  +-+-+-+-+-+-+-+-+
            // 0| | | | | | | | |0
            //  +-+-+-+-+-+-+-+-+
            // 1| | | | | | | | |1
            //  +-+-+-+-+-+-+-+-+
            // 2| | | | | | | | |2
            //  +-+-+-+-+-+-+-+-+
            // 3| | | |X|O| | | |3
            //  +-+-+-+-+-+-+-+-+
            // 4| | | |O|X| | | |4
            //  +-+-+-+-+-+-+-+-+
            // 5| | | | | | | | |5
            //  +-+-+-+-+-+-+-+-+
            // 6| | | | | | | | |6
            //  +-+-+-+-+-+-+-+-+
            // 7| | | | | | | | |7
            //  +-+-+-+-+-+-+-+-+
            //   0 1 2 3 4 5 6 7
        }

        @Test
        public void testGet() {
            // getting tokens in the center
            assertEquals(OthelloBoard.P1, board.get(3, 3));
            assertEquals(OthelloBoard.P1, board.get(4, 4));
            assertEquals(OthelloBoard.P2, board.get(3, 4));
            assertEquals(OthelloBoard.P2, board.get(4, 3));

            // getting empty tiles
            assertEquals(OthelloBoard.EMPTY, board.get(5, 6));
            assertEquals(OthelloBoard.EMPTY, board.get(0, 0));
            assertEquals(OthelloBoard.EMPTY, board.get(2, 7));
        }

        @Test
        public void testGetInvalidCoordinates() {
            assertEquals(OthelloBoard.EMPTY, board.get(-1, -1));
            assertEquals(OthelloBoard.EMPTY, board.get(8, 8));
            assertEquals(OthelloBoard.EMPTY, board.get(9, 9));
            assertEquals(OthelloBoard.EMPTY, board.get(-1, 0));
            assertEquals(OthelloBoard.EMPTY, board.get(0, 8));
        }
    }

    @Nested
    class OthelloBoardMoveTests {
        @BeforeEach
        public void setUp() {
            board = new OthelloBoard(8);
        }

        @Nested
        class MoveOnValidCoordinatesTest {
            @Test
            public void testMoveSuccess() {
                assertTrue(board.move(2, 4, OthelloBoard.P1));
                assertTrue(board.move(4, 5, OthelloBoard.P2));
                assertTrue(board.move(5, 4, OthelloBoard.P1));
                assertTrue(board.move(2, 3, OthelloBoard.P2));
//              After moves:
//                          0 1 2 3 4 5 6 7
//                         +-+-+-+-+-+-+-+-+
//                        0| | | | | | | | |0
//                         +-+-+-+-+-+-+-+-+
//                        1| | | | | | | | |1
//                         +-+-+-+-+-+-+-+-+
//                        2| | | |O|X| | | |2
//                         +-+-+-+-+-+-+-+-+
//                        3| | | |O|O| | | |3
//                         +-+-+-+-+-+-+-+-+
//                        4| | | |O|X|O| | |4
//                         +-+-+-+-+-+-+-+-+
//                        5| | | | |X| | | |5
//                         +-+-+-+-+-+-+-+-+
//                        6| | | | | | | | |6
//                         +-+-+-+-+-+-+-+-+
//                        7| | | | | | | | |7
//                         +-+-+-+-+-+-+-+-+
//                          0 1 2 3 4 5 6 7
                assertEquals(OthelloBoard.P1, board.get(2, 4));
                assertEquals(OthelloBoard.P2, board.get(4, 5));
                assertEquals(OthelloBoard.P1, board.get(5, 4));
                assertEquals(OthelloBoard.P2, board.get(2, 3));
            }

            @Test
            public void testMoveFlipsP1andP2Tokens() {
                // Before
                //   0 1 2 3 4 5 6 7
                //  +-+-+-+-+-+-+-+-+
                // 0| | | | | | | | |0
                //  +-+-+-+-+-+-+-+-+
                // 1| | | | | | | | |1
                //  +-+-+-+-+-+-+-+-+
                // 2| | | | | | | | |2
                //  +-+-+-+-+-+-+-+-+
                // 3| | | |X|O| | | |3
                //  +-+-+-+-+-+-+-+-+
                // 4| | | |O|X| | | |4
                //  +-+-+-+-+-+-+-+-+
                // 5| | | | | | | | |5
                //  +-+-+-+-+-+-+-+-+
                // 6| | | | | | | | |6
                //  +-+-+-+-+-+-+-+-+
                // 7| | | | | | | | |7
                //  +-+-+-+-+-+-+-+-+
                //   0 1 2 3 4 5 6 7
                assertEquals(OthelloBoard.P2, board.get(4, 3));
                board.move(4, 2, OthelloBoard.P1);
                // After (P2 token at (4, 3) should be flipped to P1)
                //   0 1 2 3 4 5 6 7
                //  +-+-+-+-+-+-+-+-+
                // 0| | | | | | | | |0
                //  +-+-+-+-+-+-+-+-+
                // 1| | | | | | | | |1
                //  +-+-+-+-+-+-+-+-+
                // 2| | | | | | | | |2
                //  +-+-+-+-+-+-+-+-+
                // 3| | | |X|O| | | |3
                //  +-+-+-+-+-+-+-+-+
                // 4| | |X|X|X| | | |4
                //  +-+-+-+-+-+-+-+-+
                // 5| | | | | | | | |5
                //  +-+-+-+-+-+-+-+-+
                // 6| | | | | | | | |6
                //  +-+-+-+-+-+-+-+-+
                // 7| | | | | | | | |7
                //  +-+-+-+-+-+-+-+-+
                //   0 1 2 3 4 5 6 7
                assertEquals(OthelloBoard.P1, board.get(4, 3));

                board.move(3, 2, OthelloBoard.P2);
                // After (P1 token at (3, 2) should be flipped to P2)
                //   0 1 2 3 4 5 6 7
                //  +-+-+-+-+-+-+-+-+
                // 0| | | | | | | | |0
                //  +-+-+-+-+-+-+-+-+
                // 1| | | | | | | | |1
                //  +-+-+-+-+-+-+-+-+
                // 2| | | | | | | | |2
                //  +-+-+-+-+-+-+-+-+
                // 3| | |O|O|O| | | |3
                //  +-+-+-+-+-+-+-+-+
                // 4| | |X|X|X| | | |4
                //  +-+-+-+-+-+-+-+-+
                // 5| | | | | | | | |5
                //  +-+-+-+-+-+-+-+-+
                // 6| | | | | | | | |6
                //  +-+-+-+-+-+-+-+-+
                // 7| | | | | | | | |7
                //  +-+-+-+-+-+-+-+-+
                //   0 1 2 3 4 5 6 7
                assertEquals(OthelloBoard.P2, board.get(3, 2));
            }

            @Test
            public void testMoveNotAdjacentToOpponent() {
                assertFalse(board.move(0, 0, OthelloBoard.P1));
                assertFalse(board.move(0, 7, OthelloBoard.P2));
                assertFalse(board.move(7, 0, OthelloBoard.P1));
                assertFalse(board.move(7, 7, OthelloBoard.P2));
            }

            @Test
            public void testMoveWithNoAlternation() {
                assertFalse(board.move(4, 5, OthelloBoard.P1));
                assertFalse(board.move(3, 5, OthelloBoard.P2));
                assertFalse(board.move(3, 2, OthelloBoard.P1));
                assertFalse(board.move(4, 2, OthelloBoard.P2));
            }

            @Test
            public void testMoveDifferentPlayers() {
                assertTrue(board.move(2, 4, OthelloBoard.P1), "P1 can make a move");
                assertTrue(board.move(4, 5, OthelloBoard.P2), "P2 can make a move");
                assertFalse(board.move(5, 4, OthelloBoard.EMPTY), "EMPTY cannot make a move");
                assertFalse(board.move(2, 3, OthelloBoard.BOTH), "BOTH cannot make a move");
            }
        }

        @Nested
        class MoveOnInvalidCoordinatesTest {
            @Test
            public void testMoveFail() {
                int initialP1Count = board.getCount(OthelloBoard.P1);
                int initialP2Count = board.getCount(OthelloBoard.P2);
                assertFalse(board.move(-1, -1, OthelloBoard.P1));
                assertFalse(board.move(0, 8, OthelloBoard.P2));
                assertFalse(board.move(8, 0, OthelloBoard.P1));
                assertFalse(board.move(8, 8, OthelloBoard.P1));
                assertEquals(initialP1Count, board.getCount(OthelloBoard.P1), "initial P1 count should match current P1 count");
                assertEquals(initialP2Count, board.getCount(OthelloBoard.P2), "initial P2 count should match current P2 count");
            }
        }

    }

    @Nested
    class OthelloBoardGetCopyTest {
        @BeforeEach
        public void setUp() {
            board = new OthelloBoard(8);
        }

        @Test
        public void testGetCopyMatchesOriginalDimension() {
            OthelloBoard newBoard = board.getOthelloBoardCopy();
            assertEquals(newBoard.getDimension(), board.getDimension());
        }

        @Test
        public void testGetCopyNewBoard() {
            OthelloBoard newBoard = board.getOthelloBoardCopy();
            for (int row = 0; row < newBoard.getDimension(); row++) {
                for (int col = 0; col < newBoard.getDimension(); col++) {
                    assertEquals(board.get(row, col), newBoard.get(row, col));
                }
            }
        }

        @Test
        public void testCopiedBoardDoesNotAffectOriginal() {
            OthelloBoard copiedBoard = board.getOthelloBoardCopy();
            copiedBoard.move(2, 4, OthelloBoard.P1);

            assertEquals(OthelloBoard.P1, copiedBoard.get(2, 4), "Only copied board has token at (2, 4)");
            assertNotEquals(copiedBoard.get(2, 4), board.get(2, 4));
            assertEquals(OthelloBoard.EMPTY, board.get(2, 4), "Original board should be empty at (2, 4)");
        }
    }

    @Nested
    class OthelloBoardHasMoveTest {
        @BeforeEach
        public void setUp() {
            board = new OthelloBoard(8);
        }

        @Test
        public void testHasMovesNewBoard() {
            assertEquals(OthelloBoard.BOTH, board.hasMove());
        }
    }

    @Nested
    class OthelloBoardGetCountTest {
        @BeforeEach
        public void setUp() {
            board = new OthelloBoard(8);
        }

        @Test
        public void testGetCountNewBoard() {
            assertEquals(2, board.getCount(OthelloBoard.P1), "board starts with 2 P1 tokens");
            assertEquals(2, board.getCount(OthelloBoard.P2), "board starts with 2 P2 tokens");
        }

        @Test
        public void testGetCountAfterMove() {
            assertEquals(2, board.getCount(OthelloBoard.P1), "initial count of P1");
            board.move(2, 4, OthelloBoard.P1);
            assertEquals(4, board.getCount(OthelloBoard.P1), "P1 count after the move is made");
        }

        @Test
        public void testGetCountAfterFailedMove() {
            assertEquals(2, board.getCount(OthelloBoard.P1)); // initial count of P1
            board.move(2, 3, OthelloBoard.P1);
            assertEquals(2, board.getCount(OthelloBoard.P1)); // P1 count should remain the same after failed move
        }
    }

    @Nested
    class OthelloBoardToStringTest {
        @BeforeEach
        public void setUp() {
            board = new OthelloBoard(DIMENSION);
        }

        @Test
        public void testToStringNewBoard() {
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
            assertEquals(expected, board.toString());
        }

        @Test
        public void testToStringUpdateAfterMove() {
            board.move(2, 4, OthelloBoard.P1);
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
            assertEquals(expected, board.toString(), "The board string representation is updated to reflect the board after a move has been made.");

            board.move(4, 5, OthelloBoard.P2);
            expected = "  0 1 2 3 4 5 6 7 \n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "0| | | | | | | | |0\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "1| | | | | | | | |1\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "2| | | | |X| | | |2\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "3| | | |X|X| | | |3\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "4| | | |O|O|O| | |4\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "5| | | | | | | | |5\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "6| | | | | | | | |6\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "7| | | | | | | | |7\n" +
                    " +-+-+-+-+-+-+-+-+\n" +
                    "  0 1 2 3 4 5 6 7 \n";
            assertEquals(expected, board.toString());
        }
    }
}
