package components.piano;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Test Suite for Secondary Method implementation in PianoSecondary.
 *
 * @author David Rubal
 */
public final class PianoTest {

    /**
     * Margin of error for double values.
     */
    private final double epsilon = 0.0001;

    /**
     * Test playing the first Key of the Piano.
     */
    @Test
    public void testPlayFirstKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        final double playTime = 5.0;
        p.play(1, playTime);
        pCopy.play(1, playTime);

        assertEquals(playTime, p.key(1).time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test playing the last Key of the Piano.
     */
    @Test
    public void testPlayLastKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        final double playTime = 5.0;
        final int keyPos = 88;
        p.play(keyPos, playTime);
        pCopy.play(keyPos, playTime);

        assertEquals(playTime, p.key(keyPos).time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test playing a Key of the Piano for a long time duration.
     */
    @Test
    public void testPlayKeyLong() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        final int keyPos = 68;
        final double playTime = 36000.3;
        p.play(keyPos, playTime);
        pCopy.play(keyPos, playTime);

        assertEquals(playTime, p.key(keyPos).time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test tuning the first Key of the Piano to a higher pitch.
     */
    @Test
    public void testTuneFirstKeyGreater() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        final double expectedPitch = 28.5;
        p.tune(1, expectedPitch);
        pCopy.tune(1, expectedPitch);

        assertEquals(expectedPitch, p.key(1).pitch(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test tuning a the first Key of the Piano to a lower pitch.
     */
    @Test
    public void testTuneFirstKeyLess() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        final double expectedPitch = 0.5;
        p.tune(1, expectedPitch);
        pCopy.tune(1, expectedPitch);

        assertEquals(expectedPitch, p.key(1).pitch(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test tuning a Key of the Piano to a higher pitch.
     */
    @Test
    public void testTuneMiddleKeyGreater() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        final int keyPos = 49;
        final double expectedPitch = 4000.1;
        p.tune(keyPos, expectedPitch);
        pCopy.tune(keyPos, expectedPitch);

        assertEquals(expectedPitch, p.key(keyPos).pitch(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test retriving the array of active keys while no Keys are active.
     */
    @Test
    public void testActiveKeysNoActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        assertTrue(Arrays.equals(kArrayCopy, kArray));
        assertEquals(0, kArray.length);
        assertEquals(pCopy, p);
    }

    /**
     * Test retriving the array of active keys while one Key is active.
     */
    @Test
    public void testActiveKeysOneActiveKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        final double expectedTime = 3.0;
        p.key(1).setTime(expectedTime);
        pCopy.key(1).setTime(expectedTime);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        assertTrue(Arrays.equals(kArrayCopy, kArray));
        assertEquals(1, kArray.length);
        assertEquals(p.key(1), kArray[0]);
        assertEquals(expectedTime, kArray[0].time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test retriving the array of active keys while many Keys are active.
     */
    @Test
    public void testActiveKeysManyActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        final int firstKeyPos = 1, secondKeyPos = 23, thirdKeyPos = 43,
                lastKeyPos = 87;

        final double expectedTimeKeyFirst = 3.0, expectedTimeKeySecond = 1.0,
                expectedTimeKeyThird = 4.0, expectedTimeKeyLast = 9.0;

        final int expectedNumActiveKeys = 4;
        final int three = 3;
        p.key(firstKeyPos).setTime(expectedTimeKeyFirst);
        p.key(thirdKeyPos).setTime(expectedTimeKeyThird);
        p.key(lastKeyPos).setTime(expectedTimeKeyLast);
        p.key(secondKeyPos).setTime(expectedTimeKeySecond);

        pCopy.key(firstKeyPos).setTime(expectedTimeKeyFirst);
        pCopy.key(thirdKeyPos).setTime(expectedTimeKeyThird);
        pCopy.key(lastKeyPos).setTime(expectedTimeKeyLast);
        pCopy.key(secondKeyPos).setTime(expectedTimeKeySecond);

        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        assertTrue(Arrays.equals(kArrayCopy, kArray));
        assertEquals(expectedNumActiveKeys, kArray.length);
        assertEquals(p.key(firstKeyPos), kArray[0]);
        assertEquals(p.key(secondKeyPos), kArray[1]);
        assertEquals(p.key(thirdKeyPos), kArray[2]);
        assertEquals(p.key(lastKeyPos), kArray[three]);
        assertEquals(expectedTimeKeyFirst, kArray[0].time(), this.epsilon);
        assertEquals(expectedTimeKeySecond, kArray[1].time(), this.epsilon);
        assertEquals(expectedTimeKeyThird, kArray[2].time(), this.epsilon);
        assertEquals(expectedTimeKeyLast, kArray[three].time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test passing time with a time value of 0 while no Keys are active.
     */
    @Test
    public void testPassTimeZeroTimeNoActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        p.passTime(0);
        pCopy.passTime(0);

        assertTrue(Arrays.equals(kArrayCopy, kArray));
        assertEquals(0.0, p.time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test passing time with a time value of 0 while one Key is active.
     */
    @Test
    public void testPassTimeZeroTimeOneActiveKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        final double expectedTime = 4.0;
        p.key(1).setTime(expectedTime);
        pCopy.key(1).setTime(expectedTime);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();
        p.passTime(0);
        pCopy.passTime(0);

        assertTrue(Arrays.equals(kArrayCopy, kArray));
        assertEquals(0.0, p.time(), this.epsilon);
        assertEquals(expectedTime, kArray[0].time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test passing time with a postiive time value while no Keys are active.
     */
    @Test
    public void testPassTimeWithTimeNoActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        final int numMilliseconds = 5000;
        final double expectedNumSeconds = 5.0;
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();
        p.passTime(numMilliseconds);
        pCopy.passTime(numMilliseconds);

        assertTrue(Arrays.equals(kArrayCopy, kArray));
        assertEquals(expectedNumSeconds, p.time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test passing time with a postiive time value while one Key is active.
     */
    @Test
    public void testPassTimeWithTimeOneActiveKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        final double expectedTime = 7.0;
        final int numMilliseconds = 5000;
        final double expectedNumSeconds = 5.0, expectedKeyTime = 2.0;

        p.key(1).setTime(expectedTime);
        pCopy.key(1).setTime(expectedTime);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();
        p.passTime(numMilliseconds);
        pCopy.passTime(numMilliseconds);

        assertTrue(Arrays.equals(kArrayCopy, kArray));
        assertEquals(expectedNumSeconds, p.time(), this.epsilon);
        assertEquals(expectedKeyTime, kArray[0].time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    /**
     * Test passing time with a postiive time value while many Keys are active.
     */
    @Test
    public void testPassTimeWithTimeManyActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        final int firstKeyPos = 1, secondKeyPos = 20, lastKeyPos = 54;
        final double firstKeyTime = 7.0, secondKeyTime = 6.0, lastKeyTime = 9.0;
        final double firstKeyTimeExpected = 2.0, secondKeyTimeExpected = 1.0,
                lastKeyTimeExpected = 4.0;

        p.key(firstKeyPos).setTime(firstKeyTime);
        p.key(lastKeyPos).setTime(lastKeyTime);
        p.key(secondKeyPos).setTime(secondKeyTime);
        pCopy.key(firstKeyPos).setTime(firstKeyTime);
        pCopy.key(lastKeyPos).setTime(lastKeyTime);
        pCopy.key(secondKeyPos).setTime(secondKeyTime);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        final int numMilliseconds = 5000;
        final double expectedNumSeconds = 5.0;
        p.passTime(numMilliseconds);
        pCopy.passTime(numMilliseconds);

        assertTrue(Arrays.equals(kArrayCopy, kArray));
        assertEquals(expectedNumSeconds, p.time(), this.epsilon);
        assertEquals(firstKeyTimeExpected, kArray[0].time(), this.epsilon);
        assertEquals(secondKeyTimeExpected, kArray[1].time(), this.epsilon);
        assertEquals(lastKeyTimeExpected, kArray[2].time(), this.epsilon);
        assertEquals(pCopy, p);
    }

}
