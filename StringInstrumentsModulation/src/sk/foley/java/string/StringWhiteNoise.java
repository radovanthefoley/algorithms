package sk.foley.java.string;

import java.util.Random;

import sk.foley.java.algorithm.StringAlgorithm;

public class StringWhiteNoise extends InstrumentStringImpl {

    public static final double ENERGY_DECAY_FACTOR = 0.9975;
    public static final double AMPLITUDE = 0.6;
    
    private Random generator = new Random();

    public StringWhiteNoise(double frequency, StringAlgorithm algorithm) {
        super(frequency, ENERGY_DECAY_FACTOR, algorithm);
    }

    @Override
    public void pluck() {
        for (int i = 0; i < buffer.getCapacity(); i++) {
            buffer.dequeue();
            buffer.enqueue((generator.nextDouble() - 0.5) * AMPLITUDE);
        }
    }
    
    public static void main(String[] args) {
        // canvas
        InstrumentStringImpl string = new StringWhiteNoise(440, null);
        string.drawPluck();
    }
}
