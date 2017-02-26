package sk.foley.java.data;
/**
 * @author rmasaryk
 * 
 * buffer implemented using cyclic wrap-around strategy
 *
 */
public class RingBuffer implements Buffer<Double>{

    private double[] buffer;
    private int capacity;
    private int size = 0;
    private int first = 0;
    private int next = 0;

    public RingBuffer(int capacity) { // create an empty ring buffer, with given max
                               // capacity
        this.capacity = capacity;
        buffer = new double[capacity];
    }

    public void enqueue(Double x) { // add item x to the end
        if (isFull())
            throw new java.util.NoSuchElementException("full buffer");

        size++;

        buffer[next++] = x;
        if (next == capacity)
            next = 0;
    }

    public Double dequeue() { // delete and return item from the front
        if (isEmpty())
            throw new java.util.NoSuchElementException("empty buffer");

        size--;

        double x = buffer[first++];
        if (first == capacity)
            first = 0;
        return x;
    }

    public Double peek() { // return (but do not delete) item from the front
        if (isEmpty())
            throw new java.util.NoSuchElementException("empty buffer");

        return buffer[first];
    }

    public int getCapacity() {
        return capacity;
    }
    
    public int getSize() { // return number of items currently in the buffer
        return size;
    }

    boolean isEmpty() { // is the buffer empty (size equals zero)?
        return getSize() == 0;
    }

    boolean isFull() { // is the buffer full (size equals capacity)?
        return getSize() == capacity;
    }
}
