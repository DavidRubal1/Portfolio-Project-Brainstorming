import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

// Do i include tests for equals, hashCode, and toString?
public class PianoSecondaryTest {
    @Test
    public void testPlayKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.playKey(1, 3.0);

        Piano.Key k = p.getKey(1);

        assertEquals(27.5, k.pitch(), 0.001);
        assertEquals(3.0, k.time());
        assertFalse(pCopy.equals(p));
    }

    @Test
    public void testGetPressDuration() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        assertEquals(0.0, p.getPressDuration(1));
        assertTrue(pCopy, p);
    }

    @Test
    public void testGetPitch() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.setPitch(1, 300.2);

        Piano.Key k = p.getKey(1);

        assertEquals(300.2, k.pitch());
        assertFalse(pCopy.equals(p));
    }
}
