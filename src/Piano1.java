import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

// TODO: translate notes (As4, Gf2) from frequency and back
// volume should decay at a certain rate when the key is not being held
// the volume should decay slower when the note is being held???
// the volume should not decay when the sus pedal is held
// the volume upper limit should be cut when the soft pedal is held
//

public class Piano1 {

    // sequence of the keys that make up the piano
    // Keys are represented as map pairs with
    // keys for the pitch and values representing active state
    // the pitch is a double of the key position relative to A0 while the active state is a double between 0 and 1,
    // with 1 being max volume, 0 representing inactive,
    // and the inbetween representing the note's decay
    private Sequence<Sequence<Double>> pianoKeyboard;
    private static final int TIME_INDEX = 0;
    private static final int PITCH_INDEX = 1;
    private static final int DEFAULT_NUM_KEYS = 88;
    private static final int DEFAULT_START_KEY = 1;

    // **Pedals currently unused**
    // int values for each pedal, each pedal is either
    // pressed or not pressed (0 or 1)
    // array should be size 3 for soft, sostenudo, and sustain
    // private int[] pedals;
    // private static final int NUM_PEDALS = 3;
    // private static final double SOFT_DAMPENING_FACTOR = 0.5;

    // Millisecond time interval after which the volume updates
    private static final int millisecondFrameDelay = 16;

    // note frequency formula sourced from wikipedia
    // https://en.wikipedia.org/wiki/Piano_key_frequencies
    private static double pitchFromKeyNum(int keyNum) {
        return (Math.pow(2, ((keyNum - 49) / 12.0))) * 440;
    }

    public void playKey(int keyIndex, double pressTime) {
        this.pianoKeyboard.entry(keyIndex).replaceEntry(TIME_INDEX, pressTime);
    }

    public double getPressDuration(int keyIndex) {
        return this.pianoKeyboard.entry(keyIndex).entry(TIME_INDEX);
    }

    public void setPitch(int keyIndex, double pitch) {
        this.pianoKeyboard.entry(keyIndex).replaceEntry(PITCH_INDEX, pitch);
    }

    public double getPitch(int keyIndex) {
        return this.pianoKeyboard.entry(keyIndex).entry(PITCH_INDEX);
    }

    // public void togglePedal(int pedalIndex) {
    //     if (this.pedals[pedalIndex] == 0) {
    //         this.pedals[pedalIndex] = 1;
    //     } else {
    //         this.pedals[pedalIndex] = 0;
    //     }
    // }

    public boolean isKeyActive(int keyIndex) {
        return this.pianoKeyboard.entry(keyIndex).entry(TIME_INDEX) > 0;
    }

    // public boolean isPedalActive(int pedalIndex) {
    //     return this.pedals[pedalIndex] == 1;
    // }

    private void createNewRep(int numKeys, int startKey) {
        // create keyboard map and set pitch for each key
        this.pianoKeyboard = new Sequence1L<>();
        for (int i = 0; i < numKeys; i++) {
            // adds each note with the corresponding frequency for that note
            // each note is inactive by default
            Sequence<Double> keyData = new Sequence1L();
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

    public Piano1() {
        this.createNewRep(DEFAULT_NUM_KEYS, DEFAULT_START_KEY);
    }

    public Piano1(int numKeys, int startKey) {
        this.createNewRep(numKeys, startKey);
    }

    // returns a map with only the pairs that have keys with non-zero activation values
    public Sequence<Sequence<Double>> getActiveKeyPitches() {
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

    // returns a map with only the pairs that have keys with non-zero activation values
    public Sequence<Integer> getActiveKeyIndecies() {
        Sequence<Integer> activeKeyIndecies = new Sequence1L<>();
        for (int i = 0; i < this.pianoKeyboard.length(); i++) {
            if (this.isKeyActive(i)) {
                activeKeyIndecies.add(activeKeyIndecies.length(), i);
            }
        }
        return activeKeyIndecies;
    }

    public double passTime() {

        for (int i = 0; i < this.pianoKeyboard.length(); i++) {
            Sequence<Double> key = this.pianoKeyboard.entry(i);
            if (key.entry(TIME_INDEX) <= 0) {
                key.replaceEntry(TIME_INDEX, 0.0);
            } else {
                key.replaceEntry(TIME_INDEX,
                        key.entry(TIME_INDEX) - millisecondFrameDelay / 1000.0);
            }
        }
        // time delay code sourced from
        // Anju Aravind on https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
        try {
            Thread.sleep(millisecondFrameDelay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return millisecondFrameDelay / 1000.0;
    }

    public void main(String[] args) {
        Piano1 myPiano = new Piano1();
        SimpleWriter out = new SimpleWriter1L();

        double timePassed = 0;
        myPiano.playKey(12, 7.0);
        while (timePassed < 10) {

            Sequence<Integer> activeKeyIndecies = myPiano
                    .getActiveKeyIndecies();
            out.print(" Active Keys: ");
            for (int i : activeKeyIndecies) {
                out.print(
                        "Key " + i + " Pitch: " + myPiano.getPitch(i) + " Hz ");
            }
            if (timePassed > 5 && timePassed < 5.1) {
                myPiano.playKey(48, 2.0);
            }

            if (timePassed > 7 && timePassed < 7.1) {
                myPiano.setPitch(5, 100);
                myPiano.playKey(5, 2.0);
            }
            out.println(
                    "        ||   Seconds Passed: " + timePassed + "    ||");
            timePassed += myPiano.passTime();
        }

        int pianoSize = 13, startingKey = 28;
        Piano1 mySmallPiano = new Piano1(pianoSize, startingKey);
        timePassed = 0;
        mySmallPiano.playKey(0, 3.0);
        while (timePassed < 10) {

            Sequence<Integer> activeKeyIndecies = mySmallPiano
                    .getActiveKeyIndecies();
            out.print(" Active Keys: ");
            for (int i : activeKeyIndecies) {
                out.print("Key " + i + " Pitch: " + mySmallPiano.getPitch(i)
                        + " Hz ");
            }

            if (timePassed > 5 && timePassed < 5.1) {
                mySmallPiano.setPitch(0, 10);
            }
            out.println(
                    "        ||   Seconds Passed: " + timePassed + "    ||");
            timePassed += myPiano.passTime();
        }

        out.close();
    }

}