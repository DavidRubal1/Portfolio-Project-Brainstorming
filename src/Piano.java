/**
 * PianoKernel with secondary methods.
 *
 * @author David Rubal
 */
public interface Piano extends PianoKernel {

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
         * @return the Key's time value
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
     * Returns an array of the active Keys.
     *
     * @return array of all active Keys in this
     */
    Piano.Key[] getActiveKeys();

    /**
     * Increments the Piano's internal timer and decrements the active time of
     * each active key by the given time in milliseconds.
     *
     * @param milliseconds
     *            the increment of time that will be added to the current time
     *            decremented from all active keys
     * @requires milliseconds >= 0
     * @ensures for the piano, time = #time + (milliseconds / 1000.0), for all
     *          keys, time = #time - (milliseconds / 1000.0)
     */
    void passTime(int milliseconds);

}
