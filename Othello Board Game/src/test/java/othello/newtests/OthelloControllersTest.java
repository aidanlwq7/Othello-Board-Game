package othello.newtests;

import othello.controllers.*;
import othello.players.Player;
import othello.players.PlayerGreedy;
import othello.players.PlayerHuman;
import othello.players.PlayerRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OthelloControllersTest {
    OthelloController oc;

    @Nested
    class HumanVsGreedyTest {
        @Test
        public void testConstructorProperties() {
            oc = new OthelloControllerHumanVSGreedy();
            Player[] ocPlayers = oc.getPlayers();
            assertInstanceOf(PlayerHuman.class, ocPlayers[0], "player 1 should be a human");
            assertInstanceOf(PlayerGreedy.class, ocPlayers[1], "player 2 should be a greedy player");
        }
    }

    @Nested
    class HumanVsHumanTest {
        @Test
        public void testConstructorProperties() {
            oc = new OthelloControllerHumanVSHuman();
            Player[] ocPlayers = oc.getPlayers();
            assertInstanceOf(PlayerHuman.class, ocPlayers[0], "player 1 should be a human");
            assertInstanceOf(PlayerHuman.class, ocPlayers[1], "player 2 should be a human");
        }
    }

    @Nested
    class HumanVsRandomTest {
        @Test
        public void testConstructorProperties() {
            oc = new OthelloControllerHumanVSRandom();
            Player[] ocPlayers = oc.getPlayers();
            assertInstanceOf(PlayerHuman.class, ocPlayers[0], "player 1 should be a human");
            assertInstanceOf(PlayerRandom.class, ocPlayers[1], "player 2 should be a random player");
        }
    }

    @Nested
    class RandomVsGreedyTest {
        @BeforeEach
        public void setUp() {
            oc = new OthelloControllerRandomVSGreedy();
        }

        @Test
        public void testConstructorProperties() {
            Player[] ocPlayers = oc.getPlayers();
            assertInstanceOf(PlayerRandom.class, ocPlayers[0], "player 1 should be a random player");
            assertInstanceOf(PlayerGreedy.class, ocPlayers[1], "player 2 should be a greedy player");
        }

        @Test
        public void testPlayFinishesGame() {
            oc.play();
            assertTrue(oc.getOthello().isGameOver());
        }
    }

    @Nested
    class RandomVsRandomTest {
        @BeforeEach
        public void setUp() {
            oc = new OthelloControllerRandomVSRandom();
        }

        @Test
        public void testConstructorProperties() {
            Player[] ocPlayers = oc.getPlayers();
            assertInstanceOf(PlayerRandom.class, ocPlayers[0], "player 1 should be a random player");
            assertInstanceOf(PlayerRandom.class, ocPlayers[1], "player 2 should be a random player");
        }

        @Test
        public void testPlayFinishesGame() {
            oc.play();
            assertTrue(oc.getOthello().isGameOver());
        }
    }
}