package sk.foley.java.instrument;

import sk.foley.java.algorithm.Karplus_Strong;
import sk.foley.java.algorithm.StringAlgorithm;
import sk.foley.java.string.InstrumentStringImpl;
import sk.foley.java.string.StringWhiteNoise;

public class Synthetizer extends StringedKeyboardInstrument {

    public static final double CONCERT_A = 440.0;

    @Override
    void setupStrings(StringAlgorithm algorithm) {
        strings = new InstrumentStringImpl[NUMBER_OF_KEYS];

        for (int i = 0; i < NUMBER_OF_KEYS; i++) {
            strings[i] = new StringWhiteNoise(CONCERT_A
                    * Math.pow(1.05956, i - 24), algorithm);
        }
    }

    public static void main(String[] args) {
        StringedKeyboardInstrument instrument = new Synthetizer();
        instrument.setupStrings(new Karplus_Strong());
        instrument.play(true);
    }
}
