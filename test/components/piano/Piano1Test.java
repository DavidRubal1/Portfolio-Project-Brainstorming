package components.piano;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test Suite for Kernel Method implementation in Piano1.
 *
 * @author David Rubal
 */
public final class Piano1Test {

    /**
     * Margin of error for double values.
     */
    private final double epsilon = 0.0001;

    /**
     * Test retriving the first Key of the Piano.
     */
    public void testGetKeyFirstKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        Piano.Key k = p.key(1);

        final double pitchExpected = 27.5;

        assertEquals(pitchExpected, k.pitch(), this.epsilon);
        assertEquals(0.0, k.time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test retriving the last Key of the Piano.
     */
    @Test
    public void testGetKeyLastKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        final int lastKeyPos = 88;
        Piano.Key k = p.key(lastKeyPos);

        final double pitchExpected = 4186.009;

        assertEquals(pitchExpected, k.pitch(), this.epsilon);
        assertEquals(0.0, k.time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test retriving the first Key of the Piano that starts at a position other
     * than 1.
     */
    public void testGetKeyFirstKeyCustom() {
        final int customLength = 40, customStartPos = 3;
        Piano p = new Piano1(customLength, customStartPos);
        Piano pCopy = new Piano1(customLength, customStartPos);
        Piano.Key k = p.key(1);

        final double pitchExpected = 30.86771;

        assertEquals(pitchExpected, k.pitch(), this.epsilon);
        assertEquals(0.0, k.time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test getting the initial time.
     */
    @Test
    public void testGetTime() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        assertEquals(0.0, p.time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test setting the time to a small value.
     */
    @Test
    public void testSetTimeSmall() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        final double smallTimeValue = 3.4;
        p.setTime(smallTimeValue);
        pCopy.setTime(smallTimeValue);

        assertEquals(smallTimeValue, p.time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test setting the time to a large value.
     */
    @Test
    public void testSetTimeLarge() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        final double largeTimeValue = 9093021.9;
        p.setTime(largeTimeValue);
        pCopy.setTime(largeTimeValue);

        assertEquals(largeTimeValue, p.time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test getting the length of a Piano of default size.
     */
    @Test
    public void testLengthDefault() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        final int lastKeyPos = 88;

        assertEquals(lastKeyPos, p.length());
        assertEquals(pCopy, p);
    }

    /**
     * Test getting the length of a Piano of custom size.
     */
    @Test
    public void testLengthCustom() {
        final int customLength = 40, startPos = 1;
        Piano p = new Piano1(customLength, startPos);
        Piano pCopy = new Piano1(customLength, startPos);

        assertEquals(customLength, p.length());
        assertEquals(pCopy, p);
    }

    /**
     * Test getting the offset of a Piano of default size.
     */
    @Test
    public void testGetOffsetDefault() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        assertEquals(1, p.offset());
        assertEquals(pCopy, p);
    }

    /**
     * Test getting the offset of a Piano of custom size.
     */
    @Test
    public void testGetOffsetCustom() {
        final int length = 88, customStartPos = 5;
        Piano p = new Piano1(length, customStartPos);
        Piano pCopy = new Piano1(length, customStartPos);

        assertEquals(customStartPos, p.offset());
        assertEquals(pCopy, p);
    }

    /**
     * Test adding a key to the start (lowest position) of a Piano.
     */
    @Test
    public void testAddKeyAtStart() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.addKey(0);
        pCopy.addKey(0);

        final int expectedLength = 89;

        assertEquals(expectedLength, p.length());
        assertEquals(0, p.offset());
        assertEquals(pCopy, p);
    }

    /**
     * Test adding a key to the start (highest position) of a Piano.
     */
    @Test
    public void testAddKeyAtEnd() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        final int nextKeyPosAndExpectedLength = 89;
        p.addKey(nextKeyPosAndExpectedLength);
        pCopy.addKey(nextKeyPosAndExpectedLength);

        assertEquals(nextKeyPosAndExpectedLength, p.length());
        assertEquals(1, p.offset());
        assertEquals(pCopy, p);
    }

    /**
     * Test removing a key from the start (lowest position) of a Piano.
     */
    @Test
    public void testRemoveKeyAtStart() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        Piano.Key k = p.removeKey(1);
        Piano.Key kCopy = pCopy.removeKey(1);

        final int expectedLength = 87, expectedOffset = 2;
        final double pitchExpected = 27.5;

        assertEquals(expectedLength, p.length());
        assertEquals(expectedOffset, p.offset());
        assertEquals(0.0, k.time(), this.epsilon);
        assertEquals(pitchExpected, k.pitch(), this.epsilon);
        assertEquals(kCopy, k);
        assertEquals(pCopy, p);
    }

    /**
     * Test removing a key from the end (highest position) of a Piano.
     */
    @Test
    public void testRemoveKeyAtEnd() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        final int lastKeyPos = 88, expectedLength = 87;
        final double pitchExpected = 4186.009;

        Piano.Key k = p.removeKey(lastKeyPos);
        Piano.Key kCopy = pCopy.removeKey(lastKeyPos);

        assertEquals(expectedLength, p.length());
        assertEquals(1, p.offset());
        assertEquals(0.0, k.time(), this.epsilon);
        assertEquals(pitchExpected, k.pitch(), this.epsilon);
        assertEquals(kCopy, k);
        assertEquals(pCopy, p);
    }

}
