import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class FIFOArrayTest {
    static FIFOArray fila;

    @Before
    public void setup() {
        fila = new FIFOArray(3);
    }
    
    @Test
    public void isEmptyTest() {
        assertTrue(fila.isEmpty());
        assertFalse(!fila.isEmpty());
        
        fila.addLast("a");
        fila.addLast("b");
        assertFalse(fila.isEmpty());
        
        fila.removeFirst();
        fila.removeFirst();
        assertTrue(fila.isEmpty());
    }

    @Test
    public void isFullTest() {
        fila.addLast("a");
        fila.addLast("b");
        fila.addLast("c");
        assertTrue(fila.isFull());
        assertEquals(3, fila.size());

        fila.removeFirst();
        assertFalse(fila.isFull());

        fila.addLast(null);
        assertTrue(fila.isFull());
    }

    @Test
    public void removeFirstTest() {
        fila.addLast("a");
        assertEquals(fila.getFirst(), "a");
        assertEquals(fila.getLast(), "a");
        
        fila.removeFirst();
        assertTrue(fila.isEmpty());

        fila.addLast("a");
        fila.addLast("b");
        fila.addLast("c");
        assertTrue(fila.isFull());

        fila.removeFirst();
        fila.removeFirst();
        fila.removeFirst();
        assertTrue(fila.isEmpty());
    }

    @Test
    public void addLastTest() {
        fila.addLast("a");
        assertEquals(1, fila.size());

        fila.addLast("b");
        fila.addLast("c");
        assertTrue(fila.isFull());
        assertEquals(3, fila.size());
        assertEquals("a, b, c", fila.toString());

        try {
            fila.addLast("d");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "fila cheia");
        }

        fila.removeFirst();
        fila.removeFirst();
        assertEquals(1, fila.size());

        fila.addLast("d");
        fila.addLast("e");
        assertEquals("c, d, e", fila.toString());
        assertEquals("c", fila.getFirst());
        assertEquals("e", fila.getLast());
    }

}
