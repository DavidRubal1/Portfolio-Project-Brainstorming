import java.util.ArrayList;

import components.piano.Piano;
import components.piano.Piano1;

/**
 * Organ Proof of Concept. This is a use case of the Piano Component for a two
 * manual organ with a pedal board and stops.
 *
 * @author David Rubal
 *
 */
public class OrganPOC {

    /**
     * Representation for the lower keyboard of the organ.
     */
    private Piano great;
    /**
     * Representation for the upper keyboard of the organ.
     */
    private Piano swell;
    /**
     * Representation for the pedal board of the organ.
     */
    private Piano pedalBoard;

    // represents the states of each level's stops on the keys played
    // list of values of pipe length in feet, with unison pitch at 8.0 (ft)
    /**
     * Representation that holds the current stops and their mutations for the
     * great.
     */
    private ArrayList<Double> greatMutations;
    /**
     * Representation that holds the current stops and their mutations for the
     * swell.
     */
    private ArrayList<Double> swellMutations;
    /**
     * Representation that holds the current stops and their mutations for the
     * pedal board.
     */
    private ArrayList<Double> pedalBoardMutations;

    /**
     * Constructor for custimization of keyboard ranges.
     *
     * @param greatLength
     *            the number of keys that the great will have
     * @param greatStartPos
     *            the position of the first key of the great
     * @param swellLength
     *            the number of keys that the swell will have
     * @param swellStartPos
     *            the position of the first key of the swell
     * @param pedalBoardLength
     *            the number of keys that the pedal board will have
     * @param pedalBoardStartPos
     *            the position of the first key of the pedal board
     */
    public OrganPOC(int greatLength, int greatStartPos, int swellLength,
            int swellStartPos, int pedalBoardLength, int pedalBoardStartPos) {
        this.great = new Piano1(greatLength, greatStartPos);
        this.swell = new Piano1(swellLength, swellStartPos);
        this.pedalBoard = new Piano1(pedalBoardLength, pedalBoardStartPos);
        this.greatMutations = new ArrayList<>();
        this.swellMutations = new ArrayList<>();
        this.pedalBoardMutations = new ArrayList<>();
    }

    /**
     * No-Args constructor.
     */
    public OrganPOC() {
        this.great = new Piano1();
        this.swell = new Piano1();
        this.pedalBoard = new Piano1();
        this.greatMutations = new ArrayList<>();
        this.swellMutations = new ArrayList<>();
        this.pedalBoardMutations = new ArrayList<>();
    }

    /**
     * Plays a key on the great.
     *
     * @param keyPos
     *            the position of the Key that is being pressed
     * @param time
     *            the time value that the Key will be set to
     * @requires time >= 0, keyPos is in range of the great
     * @ensures the Key at postion keyPos's time = time
     */
    public void playGreat(int keyPos, int time) {
        this.great.play(keyPos, time);
    }

    /**
     * Plays a key on the swell.
     *
     * @param keyPos
     *            the position of the Key that is being pressed
     * @param time
     *            the time value that the Key will be set to
     * @requires time >= 0, keyPos is in range of the swell
     * @ensures the Key at postion keyPos's time = time
     */
    public void playSwell(int keyPos, int time) {
        this.swell.play(keyPos, time);
    }

    /**
     * Plays a key on the pedal board.
     *
     * @param keyPos
     *            the position of the Key that is being pressed
     * @param time
     *            the time value that the Key will be set to
     * @requires time >= 0, keyPos is in range of the pedal board
     * @ensures the Key at postion keyPos's time = time
     */
    public void playPedalBoard(int keyPos, int time) {
        this.pedalBoard.play(keyPos, time);
    }

    /**
     * Returns an array containing the "pipe" lengths that the current stops are
     * set to for the great.
     *
     * @return array of mutation lengths for the great
     */
    Double[] greatStopLengths() {
        return this.greatMutations.toArray(new Double[0]);
    }

    /**
     * Returns an array containing the "pipe" lengths that the current stops are
     * set to for the swell.
     *
     * @return array of mutation lengths for the swell
     */
    Double[] swellStopLengths() {
        return this.swellMutations.toArray(new Double[0]);
    }

    /**
     * Returns an array containing the "pipe" lengths that the current stops are
     * set to for the pedal board.
     *
     * @return array of mutation lengths for the pedal board
     */
    Double[] pedalBoardStopLengths() {
        return this.pedalBoardMutations.toArray(new Double[0]);
    }

    /**
     * Adds a mutation to the great based on the given length that the stop has.
     *
     * @param stopLength
     *            length of "pipe" that the stop opens the great to
     */
    void addGreatStop(double stopLength) {
        this.greatMutations.add(stopLength);
    }

    /**
     * Adds a mutation to the swell based on the given length that the stop has.
     *
     * @param stopLength
     *            length of "pipe" that the stop opens the swell to
     */
    void addSwellStop(double stopLength) {
        this.swellMutations.add(stopLength);
    }

    /**
     * Adds a mutation to the pedal board based on the given length that the
     * stop has.
     *
     * @param stopLength
     *            length of "pipe" that the stop opens the pedal board to
     */
    void addPedalBoardStop(double stopLength) {
        this.pedalBoardMutations.add(stopLength);
    }

    /**
     * Removes/Unpresses a stop for the great.
     *
     * @param stopLength
     *            lengths that the stop corresponds to.
     */
    void removeGreatStop(double stopLength) {
        this.greatMutations.remove(stopLength);
    }

    /**
     * Removes/Unpresses a stop for the swell.
     *
     * @param stopLength
     *            lengths that the stop corresponds to.
     */
    void removeSwellStop(double stopLength) {
        this.swellMutations.remove(stopLength);
    }

    /**
     * Removes/Unpresses a stop for the peadal board.
     *
     * @param stopLength
     *            lengths that the stop corresponds to.
     */
    void removePedalBoardStop(double stopLength) {
        this.pedalBoardMutations.remove(stopLength);
    }

    /**
     * Removes/Unpresses all stops for all keyboards.
     *
     * @clears all keyboard mutations
     */
    void resetAllStops() {
        this.greatMutations.clear();
        this.swellMutations.clear();
        this.pedalBoardMutations.clear();
    }

    /**
     * Returns an array of the mutated pitches of the current active keys on the
     * great.
     *
     * @return array of mutated pitches based on lengths in greatMutations
     */
    Double[] greatKeysWithMutations() {
        ArrayList<Double> pitchList = new ArrayList<>();
        Piano.Key[] activeKeys = this.great.activeKeys();
        for (Piano.Key k : activeKeys) {
            for (double pipeLength : this.greatMutations) {
                pitchList.add(k.pitch() * Math.pow(pipeLength / 8.0, -1));
            }
        }
        return pitchList.toArray(new Double[0]);
    }

    /**
     * Returns an array of the mutated pitches of the current active keys on the
     * swell.
     *
     * @return array of mutated pitches based on lengths in swellMutations
     */
    Double[] swellKeysWithMutations() {
        ArrayList<Double> pitchList = new ArrayList<>();
        Piano.Key[] activeKeys = this.swell.activeKeys();
        for (Piano.Key k : activeKeys) {
            for (double pipeLength : this.swellMutations) {
                pitchList.add(k.pitch() * Math.pow(pipeLength / 8.0, -1));
            }
        }
        return pitchList.toArray(new Double[0]);
    }

    /**
     * Returns an array of the mutated pitches of the current active keys on the
     * pedal board.
     *
     * @return array of mutated pitches based on lengths in pedalBoardMutations
     */
    Double[] pedalBoardWithMutations() {
        ArrayList<Double> pitchList = new ArrayList<>();
        Piano.Key[] activeKeys = this.pedalBoard.activeKeys();
        for (Piano.Key k : activeKeys) {
            for (double pipeLength : this.pedalBoardMutations) {
                pitchList.add(k.pitch() * Math.pow(pipeLength / 8.0, -1));
            }
        }
        return pitchList.toArray(new Double[0]);
    }

}
