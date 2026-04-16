import static org.junit.Assert.assertEquals;

import java.security.Key;

import org.junit.Test;

public final class PianoKernelTest {

    //TODO: possibly add assert statements for the preconditions
    // add testing cases
    // Do I test the methods of the SimpleKey methods as well? getting a key relies on the Piano getter method
    // Do i test using the different constructor parameters?
    // How do I test methods that first require another method to be called for any difference to be detected?
    @Test
    public void testGetKeyFirstKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        Key k = p.getKey(1);
        double pitch = k.pitch();
        double time = k.time();

        assertEquals(27.5, pitch, 0.000001);
        assertEquals(0.0, time);
        assertEquals(pCopy, p);
    }

    @Test
    public void testGetTime
}
