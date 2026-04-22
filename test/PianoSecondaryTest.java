// Do i include tests for equals, hashCode, and toString?

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PianoSecondaryTest {

    // In this case, time() and play() have overlap in
    // their test cases, since one is reliant on the other to check.
    @Test
    public void testSimpleKeyTimeInactive() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        int kTime = p.key(1).time();
        int kTimeCopy = pCopy.key(1).time();

        assertEquals(0.0, kTime);
        assertEquals(kTimeCopy, kTime);
        assertEquals(pCopy, p);
    }

    @Test
    public void testSimpleKeyTimeActive() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        p.key(1).play(3.0);
        pCopy.key(1).play(3.0);
        int kTime = p.key(1).time();
        int kTimeCopy = pCopy.key(1).time();

        assertEquals(3.0, kTime);
        assertEquals(kTimeCopy, kTime);
        assertEquals(pCopy, p);
    }

    @Test
    public void testSimpleKeyPitchKey1() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        double kPitch = p.key(1).pitch();
        double kPitchCopy = pCopy.key(1).pitch();

        assertEquals(27.5, kPitch, 0.0001);
        assertEquals(kPitchCopy, kPitch);
        assertEquals(pCopy, p);
    }

    @Test
    public void testSimpleKeyPitchKey40() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        double kPitch = p.key(40).pitch();
        double kPitchCopy = pCopy.key(40).pitch();

        assertEquals(261.6256, kPitch, 0.00001);
        assertEquals(kPitchCopy, kPitch);
        assertEquals(pCopy, p);
    }

    @Test
    public void testSimpleKeyPitchKey88() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        double kPitch = p.key(88).pitch();
        double kPitchCopy = pCopy.key(88).pitch();

        assertEquals(4186.009, kPitch, 0.00001);
        assertEquals(kPitchCopy, kPitch);
        assertEquals(pCopy, p);
    }

    @Test
    public void testSimpleKeySetPitchHigher() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.key(1).setPitch(500);
        pCopy.key(1).setPitch(500);
        double kPitch = p.key(1).pitch();
        double kPitchCopy = pCopy.key(1).pitch();

        assertEquals(500, kPitch, 0.00001);
        assertEquals(kPitchCopy, kPitch);
        assertEquals(pCopy, p);
    }

    @Test
    public void testSimpleKeySetPitchLower() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.key(1).setPitch(2);
        pCopy.key(1).setPitch(2);
        double kPitch = p.key(1).pitch();
        double kPitchCopy = pCopy.key(1).pitch();

        assertEquals(2, kPitch, 0.00001);
        assertEquals(kPitchCopy, kPitch);
        assertEquals(pCopy, p);
    }

    @Test
    public void testActiveKeysNoActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        assertEquals(kArrayCopy, kArray);
        assertEquals(0, kArray.length);
        assertEquals(pCopy, p);
    }

    @Test
    public void testActiveKeysOneActiveKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        p.key(1).play(3.0);
        pCopy.key(1).play(3.0):
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        assertEquals(kArrayCopy, kArray);
        assertEquals(1, kArray.length);
        assertEquals(p.key(1), kArray[0]);
        assertEquals(3.0, kArray[0].time(), 0.0001);
        assertEquals(pCopy, p);
    }

    @Test
    public void testActiveKeysManyActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        p.key(1).play(3.0);
        p.key(43).play(4.0);
        p.key(87).play(9.0);
        p.key(23).play(1.0);
        pCopy.key(1).play(3.0);
        pCopy.key(43).play(4.0);
        pCopy.key(87).play(9.0);
        pCopy.key(23).play(1.0);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        assertEquals(kArrayCopy, kArray);
        assertEquals(4, kArray.length);
        assertEquals(p.key(1), kArray[0]);
        assertEquals(p.key(23), kArray[1]);
        assertEquals(p.key(43), kArray[2]);
        assertEquals(p.key(87), kArray[3]);
        assertEquals(3.0, kArray[0].time(), 0.0001);
        assertEquals(1.0, kArray[1].time(), 0.0001);
        assertEquals(4.0, kArray[2].time(), 0.0001);
        assertEquals(9.0, kArray[3].time(), 0.0001);
        assertEquals(pCopy, p);
    }

    @Test
    public void testPassTimeZeroTimeNoActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        p.passTime(0);
        pCopy.passTime(0);

        assertEquals(kArrayCopy, kArray);
        assertEquals(0.0, p.time());
        assertEquals(pCopy, p);
    }

    @Test
    public void testPassTimeZeroTimeOneActiveKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.key(1).play(4.0);
        pCopy.key(1).play(4.0);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();
        p.passTime(0);
        pCopy.passTime(0);

        assertEquals(kArrayCopy, kArray);
        assertEquals(0.0, p.time());
        assertEquals(4.0, kArray[0].time());
        assertEquals(pCopy, p);
    }

    @Test
    public void testPassTimeWithTimeNoActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();
        p.passTime(5000);
        pCopy.passTime(5000);

        assertEquals(kArrayCopy, kArray);
        assertEquals(5.0, p.time(), 0.0001);
        assertEquals(pCopy, p);
    }

    @Test
    public void testPassTimeWithTimeOneActiveKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.key(1).play(7.0);
        pCopy.key(1).play(7.0);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();
        p.passTime(5000);
        pCopy.passTime(5000);

        assertEquals(kArrayCopy, kArray);
        assertEquals(5.0, p.time(), 0.0001);
        assertEquals(2.0, kArray[0].time(), 0.0001);
        assertEquals(pCopy, p);
    }

    @Test
    public void testPassTimeWithTimeManyActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.key(1).play(7.0);
        p.key(54).play(9.0);
        p.key(20).play(6.0);
        pCopy.key(1).play(7.0);
        pCopy.key(54).play(9.0);
        pCopy.key(20).play(6.0);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        p.passTime(5000);
        pCopy.passTime(5000);

        assertEquals(kArrayCopy, kArray);
        assertEquals(5.0, p.time(), 0.0001);
        assertEquals(2.0, kArray[0].time(), 0.0001);
        assertEquals(1.0, kArray[1].time(), 0.0001);
        assertEquals(4.0, kArray[2].time(), 0.0001);
        assertEquals(pCopy, p);
    }

}
