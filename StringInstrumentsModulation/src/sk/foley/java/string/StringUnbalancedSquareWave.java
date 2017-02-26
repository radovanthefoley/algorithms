package sk.foley.java.string;

import sk.foley.java.algorithm.StringAlgorithm;

public class StringUnbalancedSquareWave extends InstrumentStringImpl {

    public static final double ENERGY_DECAY_FACTOR = 0.996;
    public static final double AMPLITUDE = 0.15;

    public StringUnbalancedSquareWave(double frequency, StringAlgorithm algorithm) {
        super(frequency, ENERGY_DECAY_FACTOR, algorithm);
    }

    @Override
    public void pluck() {
        int capacity = buffer.getCapacity();
        for (int i = 0; i < capacity; i++) {
            buffer.dequeue();
            if (i < capacity / 3)
                buffer.enqueue(AMPLITUDE);
            else
                buffer.enqueue(-AMPLITUDE);
        }
    }

    public static void main(String[] args) {
        // canvas
        InstrumentStringImpl string = new StringUnbalancedSquareWave(440, null);
        string.drawPluck();
    }
}
