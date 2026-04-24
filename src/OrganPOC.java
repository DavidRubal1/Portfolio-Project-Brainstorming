import java.util.ArrayList;

/**
 * Organ Proof of Concept. Use case of the Piano Component as part of
 * representation for a two manual organ with a pedal board.
 *
 * @author David Rubal
 *
 */
public class OrganPOC {

    private Piano great;
    private Piano swell;
    private Piano pedalBoard;

    // represents the states of each level's stops on the keys played
    // list of values of pipe length in feet, with unison pitch at 8.0 (ft)
    private ArrayList<Double> greatMutations;
    private ArrayList<Double> swellMutations;
    private ArrayList<Double> pedalBoardMutations;

    private int greatVolume;
    private int swellVolume;
    private int pedalBoardVolume;

    //TODO: double check to see if there is a lower bound on the piano key pitch
    // b/c organs can go down to C-1

    // same params as piano contructor for each keyboard
    // TODO: change this to be more clear
    public OrganPOC(int[] keyboardArgs) {
        this.great = new Piano1(keyboardArgs[0], keyboardArgs[1]);
        this.swell = new Piano1(keyboardArgs[2], keyboardArgs[3]);
        this.pedalBoard = new Piano1(keyboardArgs[4], keyboardArgs[5]);
    }

    // TODO: figure out the defaults for an organ
    public OrganPOC() {
        this.great = new Piano1();
        this.swell = new Piano1();
        this.pedalBoard = new Piano1();
    }

    double[] greatStopLengths() {
        return greatMutations.toArray();
    }

    double[] swellStopLengths() {
        return swellMutations.toArray();
    }

    double[] pedalBoardStopLengths() {
        return pedalBoardMutations.toArray();
    }

    void addGreatStop(double stopLength) {
        greatMutations.add(stopLength);
    }

    void addSwellStop(double stopLength) {
        swellMutations.add(stopLength);
    }

    void addPedalBoardStop(double stopLength) {
        pedalBoardMutations.add(stopLength);
    }

    void removeGreatStop(double stopLength) {
        greatMutations.remove(stopLength);
    }

    void removeSwellStop(double stopLength) {
        swellMutations.remove(stopLength);
    }

    void removePedalBoardStop(double stopLength) {
        pedalBoardMutations.remove(stopLength);
    }

    void removeAllStops() {
        greatMutations.clear();
        swellMutations.clear();
        pedalBoardMutations.clear();
    }

    // returns an array of all the pitches being played on the great
    double[] greatKeysWithMutations() {
        ArrayList<Double> pitchList = new ArrayList<>();
        Piano.Key[] activeKeys = great.activeKeys();
        for (Piano.Key k : activeKeys) {
            for (double pipeLength : greatMutations) {
                pitchList.add(k.pitch() * Math.pow(pipeLength / 8.0, -1));
            }
        }
    }

    // returns an array of all the pitches being played on the swell
    double[] swellKeysWithMutations() {
        ArrayList<Double> pitchList = new ArrayList<>();
        Piano.Key[] activeKeys = swell.activeKeys();
        for (Piano.Key k : activeKeys) {
            for (double pipeLength : swellMutations) {
                pitchList.add(k.pitch() * Math.pow(pipeLength / 8.0, -1));
            }
        }
    }

    // returns an array of all the pitches being played on the swell
    double[] pedalBoardWithMutations() {
        ArrayList<Double> pitchList = new ArrayList<>();
        Piano.Key[] activeKeys = pedalBoard.activeKeys();
        for (Piano.Key k : activeKeys) {
            for (double pipeLength : pedalBoardMutations) {
                pitchList.add(k.pitch() * Math.pow(pipeLength / 8.0, -1));
            }
        }
    }

}
