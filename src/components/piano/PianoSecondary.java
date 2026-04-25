package components.piano;

import java.util.ArrayList;

/**
 * Layered implementation for secondary methods from Piano interface.
 */
public abstract class PianoSecondary implements Piano {

    /*
     * Protected Members ------------
     */

    /**
     * Implementation of Key interface.
     */
    protected final class SimpleKey implements Piano.Key {

        /**
         * Field for holding the time that the key will be active for. A time of
         * 0.0 means that the key is inactive, and the opposite is true if it
         * greater than 0.
         */
        private double time;
        /**
         * Field tha represents the pitch of the key. The pitch of the key
         * should be greater than 0.
         */
        private double pitch;

        /**
         * Constructor for SimpleKey.
         *
         * @param keyPitch
         *            incoming value for the pitch of the key.
         * @requires keyPitch is within [Piano offset, Piano offset + Piano
         *           length] and keyPitch > 0.
         * @ensures this.pitch is not null, this.time = 0.0
         */
        public SimpleKey(double keyPitch) {
            this.pitch = keyPitch;
            this.time = 0.0;
        }

        /**
         * Returns the time left for which the key is active. Returns 0.0 if the
         * key is not active.
         *
         * @return the Key's time left active
         */
        @Override
        public double time() {
            return this.time;
        }

        /**
         * Changes the active time of the key to the provided value.
         *
         * @param newTime
         *            the time that the Key's current time will be updated to
         * @requires newTime >= 0.0
         * @ensures this.time = newTime
         */
        @Override
        public void setTime(double newTime) {
            assert newTime >= 0;
            this.time = newTime;
        }

        /**
         * Returns the pitch of the Key.
         *
         * @return the Key's pitch
         */
        @Override
        public double pitch() {
            return this.pitch;
        }

        /**
         * Changes the pitch of the Key to the provided value.
         *
         * @param newPitch
         *            the pitch that the Key's current pitch will be updated to
         * @requires newPitch > 0
         * @ensures this.pitch = newPitch
         */
        @Override
        public void setPitch(double newPitch) {
            assert newPitch > 0;
            this.pitch = newPitch;
        }

        @Override
        public boolean equals(Object obj) {
            final double epsilon = 0.00001;
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Key)) {
                return false;
            }
            Key objKey = (Key) obj;
            if ((Math.abs(this.pitch() - objKey.pitch()) > epsilon)
                    || (Math.abs(this.time() - objKey.time()) > epsilon)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            return Double.valueOf(this.time()).hashCode()
                    + Double.valueOf(this.pitch()).hashCode();
        }

        @Override
        public String toString() {
            return "(" + this.time() + "," + this.pitch() + ")";
        }

    }

    /*
     * Public Members --------------------------------
     */

    /*
     * Common Members (from Object)------------------
     */

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Piano)) {
            return false;
        }
        Piano objPiano = (Piano) obj;
        if (this.length() != objPiano.length() || this.time() != objPiano.time()
                || this.offset() != objPiano.offset()) {
            return false;
        }
        // check each key for equality
        for (int i = this.offset(); i < this.length() + this.offset(); i++) {
            if (!this.key(i).equals(objPiano.key(i))) {
                return false;
            }
        }
        return true;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        return Double.valueOf(this.time()).hashCode()
                + Integer.valueOf(this.length()).hashCode()
                + Integer.valueOf(this.offset()).hashCode();
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        int offset = this.offset();
        StringBuilder keyBuilder = new StringBuilder();
        for (int i = 0; i < this.length(); i++) {
            Piano.Key key = this.key(i + offset);
            keyBuilder.append("\n" + key.toString());
        }
        return "(Time: " + this.time() + ", Length: " + this.length()
                + ", Offset:" + this.offset() + "," + keyBuilder.toString()
                + ")";
    }

    /*
     * Non-Kernel Methods ------------------------------
     */

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public void play(int keyPos, double time) {
        this.key(keyPos).setTime(time);
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public void tune(int keyPos, double pitch) {
        this.key(keyPos).setPitch(pitch);
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public Piano.Key[] activeKeys() {
        ArrayList<Piano.Key> activeKeys = new ArrayList<>();
        for (int i = 0; i < this.length(); i++) {
            Piano.Key key = this.key(i + this.offset());
            if (key.time() > 0) {
                activeKeys.add(key);
            }
        }
        return activeKeys.toArray(new Piano.Key[activeKeys.size()]);
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public void passTime(int milliseconds) {
        assert milliseconds >= 0;

        final double millisecondsToSecondsRate = 1000.0;
        this.setTime(this.time() + milliseconds / millisecondsToSecondsRate);
        for (int i = 0; i < this.length(); i++) {
            Piano.Key key = this.key(i + this.offset());
            if (key.time() > 0) {
                key.setTime(
                        key.time() - milliseconds / millisecondsToSecondsRate);
            }
            if (key.time() < 0) {
                key.setTime(0);
            }
        }
    }

}
