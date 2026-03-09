import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A piano object that can be played. The properties of the individual keys of
 * the piano are represented by Sequences of Doubles. The whole keyboard is a
 * Sequence of these Sequences of Doubles.
 *
 * @author David Rubal
 *
 */
public class Piano1 {

    /*
     * Private Members
     */

    /**
     * Collection of keys of the piano keyboard
     */
    private Sequence<Sequence<Double>> pianoKeyboard;
    /**
     * Number to represent the number of the key, with A0 being key 1
     */
    private static final int KEYNUM_INDEX = 0;
    /**
     * Index for the inner sequence for the keys' time property. When the time
     * property is 0, the key is not actively being played.
     */
    private static final int TIME_INDEX = 1;
    /**
     * Index for the inner sequence for the keys' pitch property. The pitch
     * differentiates the notes sound-wise.
     */
    private static final int PITCH_INDEX = 2;
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
     * Number to represent the offset of the first key and index 0 of the
     * sequence
     */
    private int keyIndexOffset;

    // **Pedals currently unused, likely to be implemented when sound is added**
    // int values for each pedal, each pedal is either
    // pressed or not pressed (0 or 1)
    // array should be size 3 for soft, sostenudo, and sustain
    // private int[] pedals;
    // private static final int NUM_PEDALS = 3;
    // private static final double SOFT_DAMPENING_FACTOR = 0.5;

    /**
     * Total time acculmulated by the piano.
     */
    private double time;

    /* METHODS */

    // note frequency formula sourced from wikipedia
    // https://en.wikipedia.org/wiki/Piano_key_frequencies
    private static double pitchFromKeyNum(int keyNum) {
        return (Math.pow(2, ((keyNum - 49) / 12.0))) * 440;
    }

    //gets key starting at offset, not by index
    public Sequence<Double> getKey(int keyNum) {
        return this.pianoKeyboard.entry(keyNum - this.keyIndexOffset);
    }

    public double getTime() {
        return this.time;
    }

    public int getOffset() {
        return this.keyIndexOffset;
    }

    // Adds a key to either end of the keyboard
    // requires that keyNum must be at either ends of the current keyboard
    public void addKey(int keyNum) {
        Sequence<Double> newKey = new Sequence1L<>();
        newKey.add(KEYNUM_INDEX, keyNum * 1.0);
        newKey.add(TIME_INDEX, 0.0);
        newKey.add(PITCH_INDEX, pitchFromKeyNum(keyNum));
        this.pianoKeyboard.add(keyNum - this.keyIndexOffset, newKey);
        if (keyNum < this.pianoKeyboard.entry(0).entry(KEYNUM_INDEX)) {
            this.keyIndexOffset -= 1;
        }
    }

    // Removes a key from either end of the keyboard
    // requires that the key removed
    public Sequence<Double> removeKey(int keyNum) {
        int oldOffset = this.keyIndexOffset;
        this.keyIndexOffset += 1;

        return this.pianoKeyboard.remove(keyNum - oldOffset);
    }

    // Unusued pedal code
    // public void togglePedal(int pedalIndex) {
    //     if (this.pedals[pedalIndex] == 0) {
    //         this.pedals[pedalIndex] = 1;
    //     } else {
    //         this.pedals[pedalIndex] = 0;
    //     }
    // }

    // usused pedal code
    // public boolean isPedalActive(int pedalIndex) {
    //     return this.pedals[pedalIndex] == 1;
    // }

    // Creates a new piano object, used by constructors
    private void createNewRep(int numKeys, int startKey) {
        // create keyboard map and set pitch for each key
        this.keyIndexOffset = startKey;
        this.time = 0;
        this.pianoKeyboard = new Sequence1L<>();
        for (int i = 0; i < numKeys; i++) {
            // adds each note with the corresponding frequency for that note
            // each note is inactive by default
            Sequence<Double> keyData = new Sequence1L();
            keyData.add(KEYNUM_INDEX, i + startKey * 1.0);
            keyData.add(TIME_INDEX, 0.0);
            keyData.add(PITCH_INDEX, pitchFromKeyNum(i + startKey));
            this.pianoKeyboard.add(i, keyData);
        }
        // create pedal array and set each pedal to false
        // this.pedals = new int[NUM_PEDALS];
        // for (int i = 0; i < NUM_PEDALS; i++) {
        //     this.pedals[i] = 0;
        // }
    }

    /**
     * No-args constructor
     */
    public Piano1() {
        this.createNewRep(DEFAULT_NUM_KEYS, DEFAULT_START_KEY);
    }

    /**
     * Constructor with args for number of keys and the number of the starting
     * key
     */
    public Piano1(int numKeys, int startKey) {
        this.createNewRep(numKeys, startKey);
    }

    /**
     * Plays the key by setting the time property to a passed argument.
     */
    public void playKey(int keyNum, double pressTime) {
        this.getKey(keyNum).replaceEntry(TIME_INDEX, pressTime);
    }

    // returns whether a key is active based on whether the time property is greater than 0
    public boolean isKeyActive(int keyNum) {
        return this.getKey(keyNum).entry(TIME_INDEX) > 0;
    }

    public double getPressDuration(int keyNum) {
        return this.getKey(keyNum).entry(TIME_INDEX);
    }

    public double getPitch(int keyNum) {
        return this.getKey(keyNum).entry(PITCH_INDEX);
    }

    public void setPitch(int keyNum, double pitch) {
        this.getKey(keyNum).replaceEntry(PITCH_INDEX, pitch);
    }

    // Returns a sequence of sequences of doubles that are references to the keys that are active
    public Sequence<Sequence<Double>> getActiveKeys() {
        Sequence<Sequence<Double>> activeKeys = this.pianoKeyboard
                .newInstance();
        for (int i = 0; i < this.pianoKeyboard.length(); i++) {
            if (this.isKeyActive(i + this.keyIndexOffset)) {
                Sequence<Double> key = this.pianoKeyboard.remove(i);
                // place active key in the sequence that will be returned
                activeKeys.add(activeKeys.length(), key);
                // place key back into keyboard to not lose the key
                // and to prevent the loop from skipping
                this.pianoKeyboard.add(i, key);
            }
        }
        return activeKeys;
    }

    // increments the time of the piano by an amount in milliseconds
    public void passTime(int milliseconds) {

        for (int i = 0; i < this.pianoKeyboard.length(); i++) {
            Sequence<Double> key = this.getKey(i + this.keyIndexOffset);
            if (key.entry(TIME_INDEX) <= 0) {
                key.replaceEntry(TIME_INDEX, 0.0);
            } else {
                key.replaceEntry(TIME_INDEX,
                        key.entry(TIME_INDEX) - milliseconds / 1000.0);
            }
        }

        this.time += milliseconds / 1000.0;
    }

    // The main method.
    public void main(String[] args) {
        Piano1 myPiano = new Piano1();
        SimpleWriter out = new SimpleWriter1L();

        double timeLimit = 10;
        myPiano.playKey(13, 7.0);
        while (myPiano.getTime() < timeLimit) {
            double currentTime = myPiano.getTime();
            Sequence<Sequence<Double>> activeKeys = myPiano.getActiveKeys();
            out.print(" Active Keys: ");
            for (Sequence<Double> key : activeKeys) {
                out.print("Key " + key.entry(KEYNUM_INDEX).intValue()
                        + " Pitch: " + key.entry(PITCH_INDEX) + " Hz ");
            }
            if (currentTime > 5 && currentTime < 5.1) {
                myPiano.playKey(49, 2.0);
            }

            if (currentTime > 7 && currentTime < 7.1) {
                myPiano.setPitch(5, 100);
                myPiano.playKey(5, 2.0);
            }
            out.println(
                    "        ||   Seconds Passed: " + currentTime + "    ||");
            myPiano.passTime(16);
            // Probably just keep the Thread management stuff out of the methods
            // time delay code sourced from
            // Anju Aravind on https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int pianoSize = 13, startingKey = 28;
        Piano1 mySmallPiano = new Piano1(pianoSize, startingKey);
        mySmallPiano.playKey(28, 3.0);
        boolean keyAdded = false, keyRemoved = false;
        while (mySmallPiano.getTime() < timeLimit) {
            double currentTime = mySmallPiano.getTime();
            Sequence<Sequence<Double>> activeKeys = mySmallPiano
                    .getActiveKeys();
            out.print(" Active Keys: ");
            for (Sequence<Double> key : activeKeys) {
                out.print("Key " + key.entry(KEYNUM_INDEX).intValue()
                        + " Pitch: " + key.entry(PITCH_INDEX) + " Hz ");
            }

            if (currentTime > 4 && currentTime < 5.1 && !keyAdded) {
                mySmallPiano.addKey(41);
                mySmallPiano.playKey(41, 2);
                keyAdded = true;
            }
            if (currentTime > 7 && currentTime < 7.1 && !keyRemoved) {
                mySmallPiano.removeKey(28);
                mySmallPiano.playKey(40, 2);
                keyRemoved = true;
            }

            out.println(
                    "        ||   Seconds Passed: " + currentTime + "    ||");
            mySmallPiano.passTime(16);
            // Probably just keep the Thread management stuff out of the methods
            // time delay code sourced from
            // Anju Aravind on https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        out.close();
    }

}
