package sk.foley.java.instrument;

import sk.foley.java.algorithm.Karplus_Strong;
import sk.foley.java.algorithm.StringAlgorithm;
import sk.foley.java.string.InstrumentStringImpl;
import sk.foley.java.string.StringLinearChirp;
import sk.foley.java.string.StringLinearChirp2;
import sk.foley.java.string.StringNoisyUnbalancedSquareWave;

public class Piano extends StringedKeyboardInstrument {

    public static final double CONCERT_A = 440.0;

    @Override
    void setupStrings(StringAlgorithm algorithm) {
        strings = new InstrumentStringImpl[NUMBER_OF_KEYS];

        for (int i = 0; i < NUMBER_OF_KEYS; i++) {
            if (i < NUMBER_OF_KEYS * 3 / 5)
                strings[i] = new StringNoisyUnbalancedSquareWave(CONCERT_A
                        * Math.pow(1.05956, i - 24), algorithm);
            else if (i < NUMBER_OF_KEYS * 4 / 5)
                strings[i] = new StringLinearChirp(CONCERT_A
                        * Math.pow(1.05956, i - 24), algorithm);
            else
                strings[i] = new StringLinearChirp2(CONCERT_A
                        * Math.pow(1.05956, i - 24), algorithm);
        }
    }

    public static void main(String[] args) {
        StringedKeyboardInstrument instrument = new Piano();
        instrument.setupStrings(new Karplus_Strong());
        instrument.play(true);
    }
}
