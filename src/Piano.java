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
         * Returns this Key's numbered position relative to A0.
         *
         * @return the Key's position on a piano
         */
        int num();

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
         *            the pitch that the Key's current pitch will be updated to
         * @requires newPitch > 0
         * @ensures this.pitch = newPitch
         */
        void setPitch(double newPitch);
    }

    /**
     * Plays the key by updating the respective Key's active time.
     *
     * @param keyNum
     *            the Key that is being pressed
     * @param pressTime
     *            the duration of the press
     * @requires pressTime >= 0, keyNum is in range of this
     * @ensures the keyNum-th key's time = pressTime
     */
    void playKey(int keyNum, double pressTime);

    /**
     * Returns whether the given key is active.
     *
     * @param keyNum
     *            the number for the Key that is being checked for activity
     * @requires keyNum is in range of this
     * @return whether the keyNum-th key is active, if the keyNum-th Key has a
     *         time > 0
     */
    boolean isKeyActive(int keyNum);

    /**
     * Returns the press duration time of the given key.
     *
     * @param keyNum
     *            the number for the Key that is to have its time reported
     * @requires keyNum is in range of this
     * @return the keyNum-th Key's press duration, which is the time value
     *         associated with that key
     */
    double getPressDuration(int keyNum);

    /**
     * Returns the pitch of the given Key.
     *
     * @param keyNum
     *            the number for the Key that is to have its pitch reported
     * @requires keyNum is in range of this
     * @return the keyNum-th Key's pitch
     */
    double getPitch(int keyNum);

    /**
     * Changes the pitch of the given key to a specified value.
     *
     * @param keyNum
     *            the number for the Key that is to have its pitch changed
     * @param pitch
     *            the pitch that the given Key's pitch is to be changed to
     * @replaces the pitch of the keyNum-th key of this
     * @requires keyNum is in range of this, pitch >= 0
     * @ensures the pitch of the keyNum-th key of this = pitch
     */
    void setPitch(int keyNum, double pitch);

    /**
     * Returns an array of the active Keys.
     *
     * @return array of all active Keys in this
     */
    Piano.Key[] getActiveKeys();

    /**
     * Increments the internal timer of the clock by the given time in
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
