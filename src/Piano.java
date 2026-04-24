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
        void setTime(double newTime);

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
         *            the pitch that the Key's current pitch will be changed to
         * @requires newPitch > 0
         * @ensures this Key's pitch = newPitch
         */
        void setPitch(double newPitch);
    }

    /**
     * Plays the Key by updating the respective Key's active time.
     *
     * @param keyPos
     *            the position of the Key that is being pressed
     * @param time
     *            the time value that the Key will be set to
     * @requires time >= 0, keyPos is in range of this
     * @ensures the Key at postion keyPos's time = time
     */
    void play(int keyPos, double time);

    /**
     * Changes the pitch of the given key to a specified value.
     *
     * @param keyPos
     *            the number for the Key that is to have its pitch changed
     * @param pitch
     *            the pitch that the key will be set to
     * @replaces the pitch of the Key at keyPos to pitch.
     * @requires keyPos is in range of this, pitch >= 0
     * @ensures the Key at postion keyPos's pitch = pitch
     */
    void tune(int keyPos, double pitch);

    /**
     * Returns an ordered array of the active Keys from lowest key position to
     * highest. Aliases the active Keys in this.
     *
     * @return an array of all active Keys in this
     */
    Piano.Key[] activeKeys();

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
