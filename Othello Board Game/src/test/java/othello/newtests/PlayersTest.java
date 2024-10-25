package othello.newtests;

import othello.Move;
import othello.Othello;
import othello.OthelloBoard;
import othello.players.Player;
import othello.players.PlayerGreedy;
import othello.players.PlayerHuman;
import othello.players.PlayerRandom;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {
    @Nested
    class AbstractPlayerTests {
        class ConcretePlayerImplementation extends Player {
            // concrete implementation of the abstract player class to run tests
            public ConcretePlayerImplementation(Othello othello, char player) {
                super(othello, player);
            }

            @Override
            public Move getMove() {
                return new Move(0, 0); // dummy value
            }
        }

        Othello othello = new Othello();
        ConcretePlayerImplementation abstractPlayer = new ConcretePlayerImplementation(othello, OthelloBoard.P1);

        @Test
        public void testConstructor() {
            assertEquals(othello, abstractPlayer.getOthello());
            assertEquals(OthelloBoard.P1, abstractPlayer.getPlayerToken());
        }

        @Test
        public void testGetOthello() {
            assertEquals(othello, abstractPlayer.getOthello());
        }

        @Test
        public void testGetPlayerToken() {
            assertEquals(OthelloBoard.P1, abstractPlayer.getPlayerToken());
        }
    }

    @Nested
    class PlayerHumanTests {
        @Test
        public void testConstructor() {
            Othello othello = new Othello();
            PlayerHuman humanPlayer = new PlayerHuman(othello, OthelloBoard.P1);
            assertEquals(othello, humanPlayer.getOthello());
            assertEquals(OthelloBoard.P1, humanPlayer.getPlayerToken());
        }
    }

    @Nested
    class PlayerGreedyTests {
        Othello othello = new Othello();
        PlayerGreedy greedyPlayer = new PlayerGreedy(othello, OthelloBoard.P1);

        @Test
        public void testConstructor() {
            assertEquals(othello, greedyPlayer.getOthello());
            assertEquals(OthelloBoard.P1, greedyPlayer.getPlayerToken());
        }

        @Test
        public void testGetMoveNewGame() {
            // (2,4) is the best move the greedy player can make since it returns the most amount of tokens and has the
            // smallest row and column out of all the other possible best moves.
            assertEquals("(2,4)", greedyPlayer.getMove().toString());
        }
    }

    @Nested
    class PlayerRandomTests {
        @Test
        public void testConstructor() {
            Othello othello = new Othello();
            PlayerRandom randomPlayer = new PlayerRandom(othello, OthelloBoard.P1);
            assertEquals(othello, randomPlayer.getOthello());
            assertEquals(OthelloBoard.P1, randomPlayer.getPlayerToken());
        }
    }


}