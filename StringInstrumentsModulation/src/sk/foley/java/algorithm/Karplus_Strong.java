package sk.foley.java.algorithm;

import sk.foley.java.data.Buffer;

public class Karplus_Strong implements StringAlgorithm {

    public void moveBuffer(Buffer<Double> buffer, double energyDecayFactor) {
        buffer.enqueue((buffer.dequeue() + buffer.peek()) * 0.5
                * energyDecayFactor);
    }

}
