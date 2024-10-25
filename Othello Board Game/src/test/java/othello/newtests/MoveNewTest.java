package othello.newtests;

import othello.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveNewTest {
    Move m;

    @Test
    public void testConstructor() {
        m = new Move(2, 4);
        assertEquals(2, m.getRow());
        assertEquals(4, m.getCol());

        m = new Move(0, 0);
        assertEquals(0, m.getRow());
        assertEquals(0, m.getCol());
    }

    @Test
    public void testConstructorInvalidCoordinates() {
        m = new Move(-1, -1);
        assertNotEquals(-1, m.getRow(), "Move constructor cannot be called with an invalid row");
        assertNotEquals(-1, m.getCol(), "Move constructor cannot be called with an invalid col");
    }

    @Test
    public void testGetRow() {
        m = new Move(1, 8);
        assertEquals(1, m.getRow());
    }

    @Test
    public void testGetCol() {
        m = new Move(8, 1);
        assertEquals(1, m.getCol());
    }

    @Test
    public void testToString() {
        m = new Move(4, 4);
        String expected = "(4,4)";
        assertEquals(expected, m.toString());
    }
}