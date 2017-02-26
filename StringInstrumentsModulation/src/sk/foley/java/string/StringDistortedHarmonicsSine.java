package sk.foley.java.string;

import sk.foley.java.algorithm.StringAlgorithm;

public class StringDistortedHarmonicsSine extends InstrumentStringImpl {

    public static final double ENERGY_DECAY_FACTOR = 0.998;
    public static final double AMPLITUDE = 0.15;
    public static final int NUMBER_OF_HARMONICS = 2;
    public static final double HARMONICS_POWER_INCREASE = 0.6;
    public static final double DISTORTION_TRESHOLD = 0.3;

    public StringDistortedHarmonicsSine(double frequency, StringAlgorithm algorithm) {
        super(frequency, ENERGY_DECAY_FACTOR, algorithm);
    }

    @Override
    public void pluck() {
        int capacity = buffer.getCapacity();
        for (double i = 0; i < capacity; i++) {
            buffer.dequeue();
            double sample = (Math.sin(2 * Math.PI * (i / capacity)));
            for (double h = 1; h <= NUMBER_OF_HARMONICS; h++) {
                sample += HARMONICS_POWER_INCREASE
                        * (Math.sin((h + 2) * Math.PI * (i / capacity)));
            }
            sample *= AMPLITUDE / (NUMBER_OF_HARMONICS + 1);
            if (sample > DISTORTION_TRESHOLD * AMPLITUDE)
                sample = DISTORTION_TRESHOLD * AMPLITUDE;
            else if (sample < DISTORTION_TRESHOLD * -AMPLITUDE)
                sample = DISTORTION_TRESHOLD * -AMPLITUDE;
            buffer.enqueue(sample);
        }
    }

    public static void main(String[] args) {
        // canvas
        InstrumentStringImpl string = new StringDistortedHarmonicsSine(440, null);
        string.drawPluck();
    }
}
