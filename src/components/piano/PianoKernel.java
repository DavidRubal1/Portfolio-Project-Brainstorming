package components.piano;

import components.standard.Standard;

/**
 * Piano kernel component with kernel methods.
 *
 *
 * @author David Rubal
 */
public interface PianoKernel extends Standard<Piano> {

    /**
     * Returns the specified Key.
     *
     * @param keyPos
     *            the position associated with the key to be returned
     * @return the specified Key
     * @ensures Key with identifier keyPos is returned, and this is unchanged
     */
    Piano.Key key(int keyPos);

    /**
     * Returns the current time of the Piano's internal timer.
     *
     * @return the Piano's current time
     * @ensures this.time is returned, and this is unchanged
     */
    double time();

    /**
     * Sets the Piano's internal timer to a new time.
     *
     * @param newTime
     *            the time that the timer will be set to.
     * @ensures this.time = newTime
     */
    void setTime(double newTime);

    /**
     * Returns the length of this, given by the number of Keys.
     *
     * @return this.length
     * @ensures this.length is returned, and this is unchanged
     */
    int length();

    /**
     * Returns the offset of the Piano's first key from the number 0.
     *
     * @return 1st Key's associated number
     * @ensures this.offset is returned, and this is unchanged
     */
    int offset();

    // requires that keyPos must be at either ends of the current keyboard
    /**
     * Adds a key to either end of the Piano based on the given position.
     *
     * @param keyPos
     *            the position associated with the Key that is to be added to
     *            the Piano.
     *
     * @requires keyPos = this.offset - 1 OR keyPos = this.offset + this.length
     * @ensures this.length = #this.length + 1
     */
    void addKey(int keyPos);

    // Removes a key from either end of the keyboard
    // requires that the key removed
    /**
     * Removes and returns a key from either end of the Piano based on the given
     * position.
     *
     * @param keyPos
     *            the positoin associated with the Key that is to be removed
     *            from the Piano.
     * @requires keyPos = this.offset OR keyPos = this.offset + this.length - 1
     * @ensures this.length = #this.length - 1
     * @return the key that was removed
     */
    Piano.Key removeKey(int keyPos);

}
