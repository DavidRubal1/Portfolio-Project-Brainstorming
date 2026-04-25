import java.util.ArrayList;

import components.piano.Piano;
import components.piano.Piano1;

/**
 * Use of Piano1 that includes the three pedals found on grand pianos.
 *
 * @author David Rubal
 *
 */
public class GrandPianoPOC {

    /**
     * Piano object to represent the keyboard of the grand piano.
     */
    private Piano keyboard;
    /**
     * Left pedal of the grand piano, softens the volume and the timbre of the
     * keys.
     */
    private boolean softPedal;
    /**
     * Middle pedal of the grand piano, sustains only the keys held prior to the
     * pedal being depressed.
     */
    private boolean sostPedal;
    /**
     * Collection to keep track of the keys retained by the sost. pedal.
     */
    private ArrayList<Piano.Key> sostKeys;
    /**
     * Right pedal of the grand piano, sustains the sound of every key while
     * this pedal is depressed.
     */
    private boolean susPedal;

    /**
     * No-args contructor.
     */
    public GrandPianoPOC() {
        this.keyboard = new Piano1();
        this.sostKeys = new ArrayList<>();
        this.softPedal = false;
        this.sostPedal = false;
        this.susPedal = false;
    }

    /**
     * Plays the Key by updating the respective Key's active time.
     *
     * @param keyPos
     *            the Key that is being pressed
     * @param time
     *            the time value that the Key will be set to
     * @requires time >= 0, keyPos is in range of this
     * @ensures
     */
    public void play(int keyPos, int time) {
        this.keyboard.play(keyPos, time);
    }

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
    public void tune(int keyPos, int pitch) {
        this.keyboard.tune(keyPos, pitch);
    }

    /**
     * Depresses the soft pedal.
     */
    public void pressSoft() {
        this.softPedal = true;
    }

    /**
     * Releases the soft pedal.
     */
    public void releaseSoft() {
        this.softPedal = false;
    }

    /**
     * Returns whether the soft pedal is depressed.
     *
     * @return state of soft pedal
     */
    public boolean softState() {
        return this.softPedal;
    }

    /**
     * Depresses the sost. pedal and keeps a list of the current active keys to
     * sustain.
     */
    public void pressSost() {
        Piano.Key[] keyList = this.keyboard.activeKeys();
        for (int i = 0; i < keyList.length; i++) {
            this.sostKeys.add(i, keyList[i]);
        }
        this.sostPedal = true;
    }

    /**
     * Releases the soft pedal.
     */
    public void releaseSost() {
        this.sostKeys.clear();
        this.sostPedal = false;
    }

    /**
     * Returns whether the sost. pedal is depressed.
     *
     * @return state of sost. pedal
     */
    public boolean sostState() {
        return this.sostPedal;
    }

    /**
     * Depresses the sus. pedal.
     */
    public void pressSus() {
        this.susPedal = true;
    }

    /**
     * Releases the sus. pedal.
     */
    public void releaseSus() {
        this.susPedal = false;
    }

    /**
     * Returns whether the sus. pedal is depressed.
     *
     * @return state of sus. pedal
     */
    public boolean susState() {
        return this.susPedal;
    }

    /**
     * Returns an ordered array of the active Keys from lowest key position to
     * highest. Aliases the active Keys in this.
     *
     * @return an array of all active Keys in this
     */
    public Piano.Key[] activeKeys() {
        return this.keyboard.activeKeys();
    }

    /**
     * Increments the Grand Piano's internal timer. Decrements the time of each
     * active key by the given time in milliseconds unless that key is being
     * sustained.
     *
     * @param milliseconds
     *            the increment of time that will be added to the current time
     *            decremented from all active unsustained keys
     * @requires milliseconds >= 0
     * @ensures for the piano, time = #time + (milliseconds / 1000.0), for all
     *          active unsustained keys, time = #time - (milliseconds / 1000.0)
     */
    public void passTime(int milliseconds) {
        assert milliseconds >= 0;

        final double millisecondsToSecondsRate = 1000.0;
        this.keyboard.setTime(this.keyboard.time()
                + milliseconds / millisecondsToSecondsRate);
        for (int i = 0; i < this.keyboard.length(); i++) {
            Piano.Key key = this.keyboard.key(i + this.keyboard.offset());
            if (key.time() > 0 && !this.sostKeys.contains(key)
                    && !this.susPedal) {
                key.setTime(
                        key.time() - milliseconds / millisecondsToSecondsRate);
            }
            if (key.time() < 0) {
                key.setTime(0);
            }
        }
    }

}
