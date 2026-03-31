import java.util.ArrayList;

import components.sequence.Sequence;

public abstract class PianoSecondary implements Piano {

    /*
     * Public Members ------------
     */

    // These may have to be kernel methods instead
    public abstract class Key {

        public int num() {
            Sequence<Double> key =
        }

        /**
         * Returns the time left for which the key is active. Returns 0.0 if the
         * key is not active.
         *
         * @return the Key's time left active
         */
        double time();

        /**
         * Changes the active time of the key to the provided value.
         *
         * @param newTime
         *            the time that the Key's current time will be updated to
         * @requires newTime >= 0.0
         * @ensures this.time = newTime
         */
        void setTime(double newTime);

        /**
         * Returns the pitch of the Key.
         *
         * @return the Key's pitch
         */
        double pitch();

        /**
         * Changes the pitch of the Key to the provided value.
         *
         * @param newPitch
         *            the pitch that the Key's current pitch will be updated to
         * @requires newPitch > 0
         * @ensures this.pitch = newPitch
         */
        void setPitch(double newPitch);
    }

    @Override
    public void playKey(int keyNum, double pressTime) {
        Piano.Key key = this.getKey(keyNum);
        key.setTime(pressTime);
    }

    // maybe deprecate this method, same can be accomplished with just getPressDuration > 0
    @Override
    public boolean isKeyActive(int keyNum) {
        Piano.Key key = this.getKey(keyNum);
        return key.time() > 0;
    }

    @Override
    public double getPressDuration(int keyNum) {
        Piano.Key key = this.getKey(keyNum);
        return key.time();
    }

    @Override
    public double getPitch(int keyNum) {
        Piano.Key key = this.getKey(keyNum);
        return key.pitch();
    }

    @Override
    public void setPitch(int keyNum, double pitch) {
        Piano.Key key = this.getKey(keyNum);
        key.setPitch(pitch);
    }

    @Override
    public ArrayList<Piano.Key> getActiveKeys() {
        ArrayList<Piano.Key> activeKeys = new ArrayList<>();
        for (int i = 0; i < this.length(); i++) {
            Piano.Key key = this.getKey(i + this.getOffset());
            if (key.time() > 0) {
                activeKeys.add(key);
            }
        }
        return activeKeys;
    }

    @Override
    public void passTime(int milliseconds) {
        for (int i = 0; i < this.length(); i++) {
            Piano.Key key = this.getKey(i + this.getOffset());
            if (key.time() > 0) {
                key.setTime(key.time() - milliseconds);
            }
            if (key.time() < 0) {
                key.setTime(0);
            }
        }
    }

}