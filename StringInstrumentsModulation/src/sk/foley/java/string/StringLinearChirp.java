package sk.foley.java.string;

import sk.foley.java.algorithm.StringAlgorithm;

public class StringLinearChirp extends InstrumentStringImpl {

    public static final double ENERGY_DECAY_FACTOR = 0.998;
    public static final double AMPLITUDE = 0.25;

    public StringLinearChirp(double frequency, StringAlgorithm algorithm) {
        super(frequency, ENERGY_DECAY_FACTOR, algorithm);
    }

    @Override
    public void pluck() {
        int capacity = buffer.getCapacity();
        for (double i = 0; i < capacity; i++) {
            buffer.dequeue();
            buffer.enqueue(AMPLITUDE
                    * Math.sin(2 * Math.PI * (i / capacity + i * i / capacity)
                            / frequency * 4.4));
        }
    }

    public static void main(String[] args) {
        // canvas
        InstrumentStringImpl string = new StringLinearChirp(
                440, null);
        string.drawPluck();
    }
}
