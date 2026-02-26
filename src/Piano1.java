import components.sequence.Sequence;
import components.sequence.Sequence1L;

// TODO: translate notes (As4, Gf2) from frequency and back
// add time dimension
// volume should decay at a certain rate when the key is not being held
// the volume should decay slower when the note is being held???
// the volume should not decay when the sus pedal is held
// the volume upper limit should be cut when the soft pedal is held
//

// TODO: for real:
/*
    - have updating the state of activity work, likely reference shenanigans
    - Display the current active notes, maybe also show their pitch
    - Figure out what the best way to represent this is, maybe an alternative to double arrays

*/

public class Piano1 {

    // sequence of the keys that make up the piano
    // Keys are represented as map pairs with
    // keys for the pitch and values representing active state
    // the pitch is a double of the key position relative to A0 while the active state is a double between 0 and 1,
    // with 1 being max volume, 0 representing inactive,
    // and the inbetween representing the note's decay
    private Sequence<Sequence<Double>> pianoKeyboard;
    //private static final int KEYPOSITION_INDEX = 0;
    private static final int ACTIVITY_INDEX = 0;
    private static final int DEFAULT_NUM_KEYS = 88;
    private static final int DEFAULT_START_KEY = 1;
    private int startKeyOffset;

    // instead of having the first index of the key sequence be the key num, I could have the index + starting position represent the key num

    // int values for each pedal, each pedal is either
    // pressed or not pressed (0 or 1)
    // array should be size 3 for soft, sostenudo, and sustain
    private int[] pedals;
    private static final int NUM_PEDALS = 3;
    private static final double SOFT_DAMPENING_FACTOR = 0.5;

    // I want a time interval after which the volume updates

    // note frequency formulas sourced from wikipedia
    // https://en.wikipedia.org/wiki/Piano_key_frequencies
    private static int keyNumFromPitch(double keyPitch) {
        // use delta as leniency for rounding errors before casting to int
        final double delta = 0.1;
        return (int) (12 * (Math.log(keyPitch / 440) / Math.log(2)) + 49
                + delta);
    }

    private static double pitchFromKeyNum(int keyNum) {
        return (Math.pow(2, ((keyNum - 49) / 12.0))) * 440;
    }

    public void setActivity(int keyIndex, double activityValue) {
        this.pianoKeyboard.entry(keyIndex).replaceEntry(ACTIVITY_INDEX,
                activityValue);
    }

    // sets the given key's activation value to the maximum for the given pedal state
    // (set to 1 for no pedals, set to 1 - SOFT_DAMPENING_FACTOR for soft pedal active)
    public void activateKey(int keyNum) {
        this.setActivity(keyNum - this.startKeyOffset, 1.0);
    }

    public void togglePedal(int pedalIndex) {
        if (this.pedals[pedalIndex] == 0) {
            this.pedals[pedalIndex] = 1;
        } else {
            this.pedals[pedalIndex] = 0;
        }
    }

    public boolean isKeyActive(int keyNum) {
        return this.pianoKeyboard.entry(keyNum - this.startKeyOffset)
                .entry(ACTIVITY_INDEX) > 0;
    }

    public boolean isPedalActive(int pedalIndex) {
        return this.pedals[pedalIndex] == 1;
    }

    private void createNewRep(int numKeys, int startKey) {
        this.startKeyOffset = startKey;
        // create keyboard map and set pitch for each key
        this.pianoKeyboard = new Sequence1L<>();
        for (int i = 0; i <= numKeys; i++) {
            // adds each note with the corresponding frequency for that note
            // each note is inactive by default
            Sequence<Double> keyData = new Sequence1L();
            keyData.add(ACTIVITY_INDEX, 0.0);
            this.pianoKeyboard.add(i, keyData);
        }
        // create pedal array and set each pedal to false
        this.pedals = new int[NUM_PEDALS];
        for (int i = 0; i < NUM_PEDALS; i++) {
            this.pedals[i] = 0;
        }
    }

    public Piano1() {
        this.createNewRep(DEFAULT_NUM_KEYS, DEFAULT_START_KEY);
    }

    public Piano1(int numKeys, int startKey) {
        this.createNewRep(numKeys, startKey);
    }

    // returns a map with only the pairs that have keys with non-zero activation values
    public Sequence<Sequence<Double>> getActiveKeys() {
        Sequence<Sequence<Double>> activeKeys = this.pianoKeyboard
                .newInstance();
        for (int i = 0; i < this.pianoKeyboard.length(); i++) {
            if (this.isKeyActive(i)) {
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

    public Sequence<Double> getPitchArray

    public void main(String[] args) {
        // MAKE SURE I AM CALLING THE PIANOKEYBOARD OF THE OBJECT I AM CREATING YOU DUMBASS!
        Piano1 myPiano = new Piano1();

        myPiano.activateKey(3);
        myPiano.activateKey(30);
        System.out.println("The pitches of the active keys are :" + pitchFromKeyNum(ACTIVITY_INDEX))
        Sequence<Sequence<Double>> active = myPiano.getActiveKeys();
    }

}