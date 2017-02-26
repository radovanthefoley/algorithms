package sk.foley.java.instrument;

import sk.foley.java.algorithm.StringAlgorithm;
import sk.foley.java.string.InstrumentStringImpl;
import edu.princeton.stdlib.StdAudio;
import edu.princeton.stdlib.StdDraw;

public abstract class StringedKeyboardInstrument {

    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/'";

    final int NUMBER_OF_KEYS = KEYBOARD.length();
    InstrumentStringImpl[] strings;

    abstract void setupStrings(StringAlgorithm algorithm);

    void play(boolean draw) {
        
        if (null == strings) {
            throw new RuntimeException("No strings has been setup!");
        }

        // canvas
        int xMax = (int) (InstrumentStringImpl.SAMPLING_RATE / 440 * 20) + 1;
        StdDraw.setXscale(0, xMax);
        StdDraw.setYscale(-1, 1);
        StdDraw.setPenRadius(.003);
        StdDraw.show(0);
        int x = 0;

        while (true) {

            // check if the user has typed a key; if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();

                // quit safely (sound card care)
                if (key == '1') {
                    StdAudio.play(0);
                    System.exit(0);
                }

                if (key == ' ') {
                    for (int i = 0; i < NUMBER_OF_KEYS; i++) {
                        strings[i].stop();
                    }
                }
                for (int i = 0; i < NUMBER_OF_KEYS; i++) {
                    if (key == KEYBOARD.charAt(i)) {
                        strings[i].pluck();
                    }
                }
            }

            // compute the superposition of samples
            double sample = 0.0;
            for (int i = 0; i < NUMBER_OF_KEYS; i++) {
                sample += strings[i].sample();
            }

            // play the sample on standard audio
            StdAudio.play(sample);

            // draw
            if (draw) {
                StdDraw.point(x, sample);
                x++;
                if (x > xMax) {
                    x = 0;
                    StdDraw.show(0);
                    StdDraw.clear();
                }
            }

            // advance the simulation of each guitar string by one step
            for (int i = 0; i < NUMBER_OF_KEYS; i++) {
                strings[i].tic();
            }
        }
    }

    void setupStrings() {
        // TODO Auto-generated method stub
        
    }
}
