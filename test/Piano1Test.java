import org.junit.Test;

public final class Piano1Test {

    //TODO: possibly add assert statements for the preconditions
    // add testing cases
    // Do I test the methods of the SimpleKey methods as well? getting a key relies on the Piano getter method
    // Do i use multiple tests using the different constructor parameters?
    // Do i test the SimpleKey methods separately or just trust that they work
    // How do I test methods that first require another method to be called for any difference to be detected?
    @Test
    public void testGetKeyFirstKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        Piano.Key k = p.key(1);

        assertEquals(27.5, k.pitch(), 0.001);
        assertEquals(0.0, k.time());
        assertEquals(pCopy, p);
    }

    @Test
    public void testGetKeyLastKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        Piano.Key k = p.key(88);

        assertEquals(4186.009, k.pitch(), 0.001);
        assertEquals(0.0, k.time());
        assertEquals(pCopy, p);
    }

    @Test
    public void testGetTime() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        assertEquals(0.0, p.time());
        assertEquals(pCopy, p);
    }

    @Test
    public void testSetTime() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        p.setTime(3.0);
        pCopy.setTime(3.0);

        assertEquals(3.0, p.time());
        assertEquals(pCopy, p);
    }

    @Test
    public void testLengthDefault() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        assertEquals(88, p.length());
        assertEquals(pCopy, p);
    }

    @Test
    public void testLengthCustom() {
        Piano p = new Piano1(40, 1);
        Piano pCopy = new Piano1(40, 1);

        assertEquals(40, p.length());
        assertEquals(pCopy, p);
    }

    @Test
    public void testGetOffsetDefault() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        assertEquals(1, p.offset());
        assertEquals(pCopy, p);
    }

    @Test
    public void testGetOffsetCustom() {
        Piano p = new Piano1(88, 5);
        Piano pCopy = new Piano1(88, 5);

        assertEquals(5, p.offset());
        assertEquals(pCopy, p);
    }

    @Test
    public void testAddKeyAtStart() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.addKey(0);
        pCopy.addKey(0);

        // TODO: how do I check these without calling other kernel methods?
        // the length should be increased by 1
        // the offset should be decreased by 1
        // the new key should have proper time and pitch

        assertEquals(89, p.length());
        assertEquals(0, p.offset());
        assertEquals(pCopy, p);
    }

    @Test
    public void testAddKeyAtEnd() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.addKey(89);
        pCopy.addKey(89);

        // TODO: how do I check these without calling other kernel methods?
        // the length should be increased by 1
        // the offset should be decreased by 1
        // the new key should have proper time and pitch

        assertEquals(89, p.length());
        assertEquals(1, p.offset());
        assertEquals(pCopy, p);
    }

    @Test
    public void testRemoveKeyAtStart() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        Piano.Key k = p.removeKey(1);
        Piano.Key kCopy = pCopy.removeKey(1);

        // TODO: how do I check these without calling other kernel methods?
        // the length should be increased by 1
        // the offset should be decreased by 1
        // the new key should have proper time and pitch

        assertEquals(87, p.length());
        assertEquals(2, p.offset());
        assertEquals(0.0, k.time());
        assertEquals(27.5, k.pitch(), 0.0001);
        assertEquals(kCopy, k);
        assertEquals(pCopy, p);
    }

    @Test
    public void testRemoveKeyAtEnd() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        Piano.Key k = p.removeKey(88);
        Piano.Key kCopy = pCopy.removeKey(88);

        // TODO: how do I check these without calling other kernel methods?
        // the length should be increased by 1
        // the offset should be decreased by 1
        // the new key should have proper time and pitch

        assertEquals(87, p.length());
        assertEquals(1, p.getOffset());
        assertEquals(0.0, k.time());
        assertEquals(4186.009, k.pitch(), 0.0001);
        assertEquals(kCopy, k);
        assertEquals(pCopy, p);
    }

}
