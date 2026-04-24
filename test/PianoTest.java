import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class PianoTest {

    final double epsilon = this.epsilon;

    @Test
    public void testPlayFirstKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        p.play(1, 5.0);
        pCopy.play(1, 5.0);

        assertEquals(5.0, p.key(1).time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    @Test
    public void testPlayLastKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        p.play(88, 5.0);
        pCopy.play(88, 5.0);

        assertEquals(5.0, p.key(88).time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    @Test
    public void testPlayLastKeyLong() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        p.play(88, 36000);
        pCopy.play(88, 36000);

        assertEquals(36000, p.key(88).time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    @Test
    public void testTuneFirstKeyGreater() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        p.tune(1, 28.5);
        pCopy.tune(1, 28.5);

        assertEquals(28.5, p.key(1).pitch(), this.epsilon);
        assertEquals(pCopy, p);
    }

    @Test
    public void testTuneFirstKeyLess() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        p.tune(1, 0.5);
        pCopy.tune(1, 0.5);

        assertEquals(0.5, p.key(1).pitch(), this.epsilon);
        assertEquals(pCopy, p);
    }

    @Test
    public void testTuneMiddleKeyGreater() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        p.tune(49, 4000.0);
        pCopy.tune(49, 4000.0);

        assertEquals(4000.0, p.key(49).pitch(), this.epsilon);
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
        p.key(1).setTime(3.0);
        pCopy.key(1).setTime(3.0);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        assertEquals(kArrayCopy, kArray);
        assertEquals(1, kArray.length);
        assertEquals(p.key(1), kArray[0]);
        assertEquals(3.0, kArray[0].time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    @Test
    public void testActiveKeysManyActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        p.key(1).setTime(3.0);
        p.key(43).setTime(4.0);
        p.key(87).setTime(9.0);
        p.key(23).setTime(1.0);
        pCopy.key(1).setTime(3.0);
        pCopy.key(43).setTime(4.0);
        pCopy.key(87).setTime(9.0);
        pCopy.key(23).setTime(1.0);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        assertEquals(kArrayCopy, kArray);
        assertEquals(4, kArray.length);
        assertEquals(p.key(1), kArray[0]);
        assertEquals(p.key(23), kArray[1]);
        assertEquals(p.key(43), kArray[2]);
        assertEquals(p.key(87), kArray[3]);
        assertEquals(3.0, kArray[0].time(), this.epsilon);
        assertEquals(1.0, kArray[1].time(), this.epsilon);
        assertEquals(4.0, kArray[2].time(), this.epsilon);
        assertEquals(9.0, kArray[3].time(), this.epsilon);
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
        assertEquals(0.0, p.time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    @Test
    public void testPassTimeZeroTimeOneActiveKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.key(1).setTime(4.0);
        pCopy.key(1).setTime(4.0);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();
        p.passTime(0);
        pCopy.passTime(0);

        assertEquals(kArrayCopy, kArray);
        assertEquals(0.0, p.time(), this.epsilon);
        assertEquals(4.0, kArray[0].time(), this.epsilon);
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
        assertEquals(5.0, p.time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    @Test
    public void testPassTimeWithTimeOneActiveKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.key(1).setTime(7.0);
        pCopy.key(1).setTime(7.0);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();
        p.passTime(5000);
        pCopy.passTime(5000);

        assertEquals(kArrayCopy, kArray);
        assertEquals(5.0, p.time(), this.epsilon);
        assertEquals(2.0, kArray[0].time(), this.epsilon);
        assertEquals(pCopy, p);
    }

    @Test
    public void testPassTimeWithTimeManyActiveKeys() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();

        p.key(1).setTime(7.0);
        p.key(54).setTime(9.0);
        p.key(20).setTime(6.0);
        pCopy.key(1).setTime(7.0);
        pCopy.key(54).setTime(9.0);
        pCopy.key(20).setTime(6.0);
        Piano.Key[] kArray = p.activeKeys();
        Piano.Key[] kArrayCopy = pCopy.activeKeys();

        p.passTime(5000);
        pCopy.passTime(5000);

        assertEquals(kArrayCopy, kArray);
        assertEquals(5.0, p.time(), this.epsilon);
        assertEquals(2.0, kArray[0].time(), this.epsilon);
        assertEquals(1.0, kArray[1].time(), this.epsilon);
        assertEquals(4.0, kArray[2].time(), this.epsilon);
        assertEquals(pCopy, p);
    }

}
