package sk.foley.java.string;

import sk.foley.java.algorithm.StringAlgorithm;

public class StringSine extends InstrumentStringImpl {

    public static final double ENERGY_DECAY_FACTOR = 0.998;
    public static final double AMPLITUDE = 0.35;
    public static final double HARMONICS_POWER_INCREASE = 0.7;

    public StringSine(double frequency, StringAlgorithm algorithm) {
        super(frequency, ENERGY_DECAY_FACTOR, algorithm);
    }

    @Override
    public void pluck() {
        int capacity = buffer.getCapacity();
        for (double i = 0; i < capacity; i++) {
            buffer.dequeue();
            double sample = (Math.sin(2 * Math.PI * (i / capacity)));
            sample = AMPLITUDE * sample;
            buffer.enqueue(sample);
        }
    }

    public static void main(String[] args) {
        // canvas
        InstrumentStringImpl string = new StringSine(440, null);
        string.drawPluck();
    }
}
