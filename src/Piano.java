
import java.util.ArrayList;

/**
 * PianoKernel with secondary methods.
 *
 * @author David Rubal
 */
public interface Piano extends PianoKernel {

    // IS THIS A GOOD IDEA OR THE WORST THING EVER
    enum KeyName {
        //Key Names and their respective pitches (Hz)
        C0(),
        C#0(),
        Db0(),
        D0(),
        D#0(),
        Eb0(),
        E0(),
        F0(),
        F#0(),
        Gb0(),
        G0(),
        G#0(),
        Ab0(),
        A0(),
        A#0(),
        Bb0(),
        B0(),
        C1(),
        C#1(),
        Db1(),
        D1(),
        D#1(),
        Eb1(),
        E1(),
        F1(),
        F#1(),
        Gb1(),
        G1(),
        G#1(),
        Ab1(),
        A1(),
        A#1(),
        Bb1(),
        B1(),
        C2(),
        C#2(),
        Db2(),
        D2(),
        D#2(),
        Eb2(),
        E2(),
        F2(),
        F#2(),
        Gb2(),
        G2(),
        G#2(),
        Ab2(),
        A2(),
        A#2(),
        Bb2(),
        B2(),
        C3(),
    }

    /**
     * A key within the piano object. Each key holds information for its
     * positional number with respect to A0, the time it has left active, and
     * its pitch in hertz.
     */
    interface Key {

        /**
         * Returns the time left for which the key is active. Returns 0.0 if the
         * key is not active.
         *
         * @return the Key's time left active
         */
        double time();

        /**
         * Changes the active time of the key to the provided value.
         *
         * @param newTime
         *            the time that the Key's current time will be updated to
         * @requires newTime >= 0.0
         * @ensures this.time = newTime
         */
        void play(double newTime);

        /**
         * Returns the pitch of the Key.
         *
         * @return the Key's pitch
         */
        double pitch();

        /**
         * Changes the pitch of the Key to the provided value.
         *
         * @param newPitch
         *            the pitch that the Key's current pitch will be updated to
         * @requires newPitch > 0
         * @ensures this.pitch = newPitch
         */
        void setPitch(double newPitch);
    }

    /**
     * Returns an ArrayList of the active Keys.
     *
     * @return ArrayList of all active Keys in this
     */
    ArrayList<Piano.Key> getActiveKeys();

    /**
     * Decrements the active time of each active key by the given time in
     * milliseconds.
     *
     * @param milliseconds
     *            the increment of time that will be added to the current time
     * @requires milliseconds >= 0
     * @ensures if this.time is in milliseconds, then this.time = #this.time +
     *          milliseconds
     */
    void passTime(int milliseconds);

}
