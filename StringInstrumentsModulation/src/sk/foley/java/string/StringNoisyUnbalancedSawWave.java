package sk.foley.java.string;

import java.util.Random;

import sk.foley.java.algorithm.StringAlgorithm;

public class StringNoisyUnbalancedSawWave extends InstrumentStringImpl {

    public static final double ENERGY_DECAY_FACTOR = 0.996;
    public static final double AMPLITUDE = 0.2;
    public static final double NOISE_LEVEL = 0.075;
    
    private Random generator = new Random();

    public StringNoisyUnbalancedSawWave(double frequency, StringAlgorithm algorithm) {
        super(frequency, ENERGY_DECAY_FACTOR, algorithm);
    }

    @Override
    public void pluck() {
        int capacity = buffer.getCapacity();
        for (double i = 0; i < capacity; i++) {
            buffer.dequeue();
            if (i < capacity / 4)
                buffer.enqueue(4 * AMPLITUDE * i / capacity
                        + generator.nextDouble() * NOISE_LEVEL);
            else if (i < capacity * 3 / 4)
                buffer.enqueue(2 * AMPLITUDE * (1 - 2 * i / capacity)
                        + generator.nextDouble() * NOISE_LEVEL);
            else
                buffer.enqueue(-4 * AMPLITUDE * (1 - i / capacity)
                        + generator.nextDouble() * NOISE_LEVEL);
        }
    }

    public static void main(String[] args) {
        // canvas
        InstrumentStringImpl string = new StringNoisyUnbalancedSawWave(440, null);
        string.drawPluck();
    }
}
