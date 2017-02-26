package sk.foley.java.string;

import java.util.Random;

import sk.foley.java.algorithm.StringAlgorithm;

public class StringNoisyUnbalancedSquareWave extends InstrumentStringImpl {

    public static final double ENERGY_DECAY_FACTOR = 0.996;
    public static final double AMPLITUDE = 0.13;
    public static final double NOISE_LEVEL = 0.075;

    private Random generator = new Random();

    public StringNoisyUnbalancedSquareWave(double frequency, StringAlgorithm algorithm) {
        super(frequency, ENERGY_DECAY_FACTOR, algorithm);
    }

    @Override
    public void pluck() {
        int capacity = buffer.getCapacity();
        for (int i = 0; i < capacity; i++) {
            buffer.dequeue();
            if (i < capacity / 3)
                buffer.enqueue(AMPLITUDE + generator.nextDouble() * NOISE_LEVEL);
            else
                buffer.enqueue(-AMPLITUDE + generator.nextDouble() * NOISE_LEVEL);
        }
    }

    public static void main(String[] args) {
        // canvas
        InstrumentStringImpl string = new StringNoisyUnbalancedSquareWave(
                440, null);
        string.drawPluck();
    }
}
