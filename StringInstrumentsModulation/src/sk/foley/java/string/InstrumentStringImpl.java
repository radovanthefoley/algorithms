package sk.foley.java.string;

import edu.princeton.stdlib.StdDraw;
import sk.foley.java.algorithm.StringAlgorithm;
import sk.foley.java.data.Buffer;
import sk.foley.java.data.RingBuffer;

public abstract class InstrumentStringImpl implements InstrumentString {
    
    public static final int SAMPLING_RATE = 44100;
    
    Buffer<Double> buffer;
    double energyDecayFactor;
    StringAlgorithm algorithm;
    double frequency;
    
    InstrumentStringImpl(double frequency, double energyDecayFactor, StringAlgorithm algorithm) {
        // create a guitar string of the given frequency, using a sampling rate
        // of 44,100
        buffer = new RingBuffer((int) (SAMPLING_RATE / frequency) + 1);
        for (int i = 0; i < buffer.getCapacity(); i++) {
            buffer.enqueue(0.0);
        }
        
        this.energyDecayFactor = energyDecayFactor;
        this.algorithm = algorithm;
        this.frequency = frequency;
    }
    
    @Override
    public void tic() {
        algorithm.moveBuffer(buffer, energyDecayFactor);
    }

    @Override
    public final double sample() {
        return buffer.peek();
    }
    
    @Override
    public void stop() {
        for (int i = 0; i < buffer.getCapacity(); i++) {
            buffer.dequeue();
            buffer.enqueue(0d);
        }
    }
    
    @Override
    public void drawPluck() {
        pluck();

        StdDraw.setXscale(0, this.buffer.getCapacity());
        StdDraw.setYscale(-0.4, 0.4);
        StdDraw.setPenRadius(.003);

        for (int i = 0; i < this.buffer.getCapacity(); i++) {
            StdDraw.point(i, this.buffer.dequeue());
        }
    }

}
