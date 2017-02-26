package sk.foley.java.string;

import sk.foley.java.algorithm.StringAlgorithm;

public class StringHalfSine extends InstrumentStringImpl {

    public static final double ENERGY_DECAY_FACTOR = 0.998;
    public static final double AMPLITUDE = 0.35;
    public static final double HARMONICS_POWER_INCREASE = 0.7;

    public StringHalfSine(double frequency, StringAlgorithm algorithm) {
        super(frequency, ENERGY_DECAY_FACTOR, algorithm);
    }

    @Override
    public void pluck() {
        int capacity = buffer.getCapacity();
        for (double i = 0; i < capacity; i++) {
            buffer.dequeue();
            double sample = (Math.sin(Math.PI * (i / capacity)));
            sample = AMPLITUDE * sample;
            buffer.enqueue(sample);
        }
    }

    public static void main(String[] args) {
        // canvas
        InstrumentStringImpl string = new StringHalfSine(440, null);
        string.drawPluck();
    }
}
