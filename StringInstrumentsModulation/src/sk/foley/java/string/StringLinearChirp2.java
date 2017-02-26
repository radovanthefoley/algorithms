package sk.foley.java.string;

import sk.foley.java.algorithm.StringAlgorithm;

public class StringLinearChirp2 extends InstrumentStringImpl {

    public static final double ENERGY_DECAY_FACTOR = 0.9985;
    public static final double AMPLITUDE = 0.3;

    public StringLinearChirp2(double frequency, StringAlgorithm algorithm) {
        super(frequency, ENERGY_DECAY_FACTOR, algorithm);
    }

    @Override
    public void pluck() {
        int capacity = buffer.getCapacity();
        for (double i = 0; i < capacity; i++) {
            buffer.dequeue();
            buffer.enqueue(AMPLITUDE
                    * Math.sin(2 * Math.PI * (i / capacity + i * i * i / capacity)
                            / frequency * 0.045));
        }
    }

    public static void main(String[] args) {
        // canvas
        InstrumentStringImpl string = new StringLinearChirp2(
                440, null);
        string.drawPluck();
    }
}
