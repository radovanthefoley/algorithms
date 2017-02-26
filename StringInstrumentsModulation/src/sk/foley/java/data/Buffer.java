package sk.foley.java.data;

public interface Buffer<T> {
    
    void enqueue(T item);

    T dequeue();

    T peek();

    int getCapacity();
}
