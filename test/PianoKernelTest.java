import static org.junit.Assert.assertEquals;

import java.security.Key;

import org.junit.Test;

public final class PianoKernelTest {

    //TODO: possibly add assert statements for the preconditions
    // add testing cases
    // Do I test the methods of the SimpleKey methods as well? getting a key relies on the Piano getter method
    //
    @Test
    public void testGetKeyFirstKey() {
        Piano p = new Piano1();
        Piano pCopy = new Piano1();
        Key k = p.getKey(1);
        int

        assertEquals(pCopy, p);
    }
}
