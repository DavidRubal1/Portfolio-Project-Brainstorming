import java.util.ArrayList;

public class PedalPianoPOC extends Piano1 {

    private Piano keyboard;
    private boolean softPedal;
    private boolean sostPedal;
    private ArrayList<Piano.Key> sostKeys;
    private boolean susPedal;

    public PedalPianoPOC() {
        this.keyboard = new Piano1();
        this.sostKeys = new ArrayList();
        this.softPedal = false;
        this.sostPedal = false;
        this.susPedal = false;
    }

    @Override
    public void play(int keyPos, int time) {
        Piano.Key key = this.keyboard.key(keyPos);
        key.setTime(time);
        if (this.sostPedal && !this.sostKeys.contains(key)) {
            this.sostKeys.add(key);
        }
    }

    @Override
    public void tune(int keyPos, int pitch) {
        this.keyboard.key(keyPos).setPitch(pitch);
    }

    public void pressSoft() {
        this.softPedal = true;
    }

    public void releaseSoft() {
        this.softPedal = false;
    }

    public void pressSost() {
        this.sostKeys = keyboard.activeKeys();
        this.sostPedal = true;
    }

    public void releaseSost() {
        this.sostKeys.clear();
        this.sostPedal = false;
    }

    public void pressSus() {
        this.susPedal = true;
    }

    public void releaseSus() {
        this.susPedal = false;
    }

    @Override
    public passTime(int milliseconds){
        assert milliseconds >= 0;

        final double millisecondsToSecondsRate = 1000.0;
        this.setTime(this.time() - milliseconds / millisecondsToSecondsRate);
        for (int i = 0; i < this.length(); i++) {
            Piano.Key key = this.key(i + this.offset());
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
