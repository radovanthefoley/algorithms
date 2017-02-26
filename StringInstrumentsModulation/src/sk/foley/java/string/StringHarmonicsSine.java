package sk.foley.java.string;

import sk.foley.java.algorithm.StringAlgorithm;

public class StringHarmonicsSine extends InstrumentStringImpl {

    public static final double ENERGY_DECAY_FACTOR = 0.998;
    public static final double AMPLITUDE = 0.35;
    public static final int NUMBER_OF_HARMONICS = 3;
    public static final double HARMONICS_POWER_INCREASE = 0.7;

    public StringHarmonicsSine(double frequency, StringAlgorithm algorithm) {
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
            sample = AMPLITUDE / (NUMBER_OF_HARMONICS + 1) * sample;
            buffer.enqueue(sample);
        }
    }

    public static void main(String[] args) {
        // canvas
        InstrumentStringImpl string = new StringHarmonicsSine(440, null);
        string.drawPluck();
    }
}
