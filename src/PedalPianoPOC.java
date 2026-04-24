import java.util.ArrayList;

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
    public PedalPianoPOC() {
        this.keyboard = new Piano1();
        this.sostKeys = new ArrayList();
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

    public void pressSoft() {
        this.softPedal = true;
    }

    public void releaseSoft() {
        this.softPedal = false;
    }

    public boolean softState() {
        return softPedal;
    }

    public void pressSost() {
        this.sostKeys = this.keyboard.activeKeys();
        this.sostPedal = true;
    }

    public void releaseSost() {
        this.sostKeys.clear();
        this.sostPedal = false;
    }

    public boolean sostState() {
        return sostPedal;
    }

    public void pressSus() {
        this.susPedal = true;
    }

    public void releaseSus() {
        this.susPedal = false;
    }

    public boolean susState() {
        return susPedal;
    }

    public Piano.Key[] activeKeys() {
        return this.keyboard.activeKeys();
    }

    @Override
    public passTime(int milliseconds){
        assert milliseconds >= 0;

        final double millisecondsToSecondsRate = 1000.0;
        this.setTime(this.time() - milliseconds / millisecondsToSecondsRate);
        for (int i = 0; i < this.length(); i++) {
            Piano.Key key = this.keyboard.key(i + this.offset());
            if (key.time() > 0 && !sostKeys.contains(key) && !susPedal) {
                key.setTime(
                        key.time() - milliseconds / millisecondsToSecondsRate);
            }
            if (key.time() < 0) {
                key.setTime(0);
            }
        }
    }

}
