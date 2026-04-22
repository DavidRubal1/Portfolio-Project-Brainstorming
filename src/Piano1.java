import components.map.Map;
import components.map.Map1L;

/**
 * Piano object represented as a Map with keys of integers denoting Key position
 * and values of SimpleKeys. Includes with implemented kernel methods.
 *
 * @convention [this.time >= 0.0 as time in units of seconds] and
 *             [this.keyIndexOffset is an offset from the standard position 1 at
 *             key A0] and [this.pianoKeyboard is a Map with keys of integers
 *             between this.offset and this.pianoKeyboard.length and values of
 *             SimpleKeys]
 *
 *
 * @correspondence this = [$this.pianoKeyboard as pairs of integer key positions
 *                 and SimpleKey objects containing time and pitch information]
 *                 and [$this.keyIndexOffset is the lowest position value of any
 *                 Key in this.pianoKeyboard and is based on Key A0 as position
 *                 1] and [this.time is an internal timer that counts up from 0
 *                 as the piano is used]
 *
 * @author David Rubal
 *
 */
public class Piano1 extends PianoSecondary {

    /*
     * Private Members----------------------------------------------------------
     */

    /**
     * Collection of keys of the piano keyboard represented as a Map of Integers
     * (Key identifiers, with A0 being key 1), and SimpleKeys.
     */
    private Map<Integer, SimpleKey> pianoKeyboard;

    // TODO: should I move these higher up in the hierarchy since they should be constants?
    /**
     * Value for default construction of a piano object. This is the default
     * number of keys to add to the keyboard.
     */
    private static final int DEFAULT_NUM_KEYS = 88;
    /**
     * Value for default construction of a piano object. This is the staring key
     * number to be added to the keyboard. Key 1 is key A0 on a piano. The key
     * number affects the freqency assigned to that key during construction.
     */
    private static final int DEFAULT_START_KEY = 1;
    /**
     * Number to represent the offset of the keyboard and the position of the
     * first key.
     */
    private int keyIndexOffset;
    /**
     * Total time acculmulated by the piano in seconds.
     */
    private double time;

    /**
     * Provides the pitch of the associated key position, with A0 being key 1.
     *
     * @param keyNum
     *            the positon of the given key
     * @return the pitch of the given key
     */
    private static double pitchFromKeyNum(int keyNum) {
        // Equation below sourced from wikipedia
        // https://en.wikipedia.org/wiki/Piano_key_frequencies
        return (Math.pow(2, ((keyNum - 49) / 12.0))) * 440;
    }

    /**
     * Creates a new piano object given a quantity of keys and position for the
     * start key.
     *
     * @param numKeys
     *            number of keys that the piano will have
     * @param startKey
     *            the position of the first key
     *
     */
    private void createNewRep(int numKeys, int startKey) {
        // create keyboard map and set pitch for each key
        this.keyIndexOffset = startKey;
        this.time = 0;
        this.pianoKeyboard = new Map1L<>();
        for (int i = 0; i < numKeys; i++) {
            // adds each note with the corresponding frequency for that note
            SimpleKey key = new SimpleKey(pitchFromKeyNum(i + startKey));
            this.pianoKeyboard.add(i + startKey, key);
        }
    }

    /*
     * Constructors-------------------------------------------------------------
     */

    /**
     * No-args constructor.
     *
     * @ensures |this| = DEFAULT_NUM_KEYS, position of first key =
     *          DEFAULT_START_KEY
     */
    public Piano1() {
        this.createNewRep(DEFAULT_NUM_KEYS, DEFAULT_START_KEY);
    }

    /**
     * Constructor for a piano with custom number of keys, starting at a given
     * key position.
     *
     * @param numKeys
     *            the number of keys the piano will have
     * @param startKey
     *            the position that the first key will have
     * @ensures |this| = numKeys, position of first key = startKey
     */
    public Piano1(int numKeys, int startKey) {
        this.createNewRep(numKeys, startKey);
    }

    /*
     * Standard Methods---------------------------------------------------------
     */

    @Override
    public final Piano1 newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep(DEFAULT_NUM_KEYS, DEFAULT_START_KEY);
    }

    @Override
    public final void transferFrom(Piano inPiano) {
        assert inPiano instanceof Piano1 : "Violation of: input is of dynamic type Piano1";

        Piano1 piano = (Piano1) inPiano;
        this.pianoKeyboard = piano.pianoKeyboard;
        this.keyIndexOffset = piano.keyIndexOffset;
        this.time = piano.time;
        piano.createNewRep(DEFAULT_NUM_KEYS, DEFAULT_START_KEY);
    }

    /*
     * Kernel Methods-----------------------------------------------------------
     */
    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public Piano.Key getKey(int keyNum) {
        return this.pianoKeyboard.value(keyNum);
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public double getTime() {
        return this.time;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public void setTime(double newTime) {
        this.time = newTime;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int length() {
        return this.pianoKeyboard.size();
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int getOffset() {
        return this.keyIndexOffset;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public void addKey(int keyNum) {
        if (keyNum + 1 == this.keyIndexOffset) {
            this.keyIndexOffset--;
        }
        this.pianoKeyboard.add(keyNum, new SimpleKey(this.getPitch(keyNum)));
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public Piano.Key removeKey(int keyNum) {
        if (keyNum + 1 == this.keyIndexOffset) {
            this.keyIndexOffset++;
        }
        return this.pianoKeyboard.remove(keyNum).value();
    }
}
