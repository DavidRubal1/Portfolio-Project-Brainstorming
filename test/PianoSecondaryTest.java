// Do i include tests for equals, hashCode, and toString?

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class PianoSecondaryTest {

    @Test
    public void testGetActiveKeysNoActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        Piano.Key[] keyList = p.getActiveKeys();
        Piano.Key[] keyListExpected = new ArrayList();

        assertEquals(keyListExpected, keyList);
        assertEquals(pCopy, p);
    }

    @Test
    public void testGetActiveKeysOneActiveKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        Piano.Key k = p.getKey(1);
        k.play(3.0);
        Piano.Key[] keyList = p.getActiveKeys();
        Piano.Key[] keyListExpected = new ArrayList();

        assertEquals(keyListExpected, keyList);
        assertEquals(pCopy, p);
    }

    @Test
    public void testGetKeyFirstKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        Piano.Key[] keyList = p.getActiveKeys();
        Piano.Key[] keyListExpected = new ArrayList();

        assertEquals(keyListExpected, keyList);
        assertEquals(pCopy, p);
    }
}
