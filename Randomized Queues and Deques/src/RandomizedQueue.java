/**
 * @author Radovan Masaryk
 * 
 *         <i>Randomized Queue</i>. A randomized queue is similar to a stack or
 *         queue, except that the item removed is chosen uniformly at random
 *         from items in the data structure.
 */

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int N;

    /**
     * Construct an empty randomized queue.
     */
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    } // construct an empty randomized queue

    /**
     * Is the queue empty?
     * 
     * @return true or false
     */
    public boolean isEmpty() {
        return size() == 0;
    } // is the queue empty?

    /**
     * Return the number of items on the queue.
     * 
     * @return size
     */
    public int size() {
        return N;
    } // return the number of items on the queue

    private void resize(int capacity) {
        Item[] resizedQueue = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            resizedQueue[i] = queue[i];
        queue = resizedQueue;
    }

    /**
     * Add the item.
     * 
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        if (N == queue.length)
            resize(2 * queue.length);
        queue[N++] = item;
    } // add the item

    /**
     * Delete and return a random item.
     * 
     * @return item
     */
    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int randomIndex = StdRandom.uniform(N);
        Item item = queue[randomIndex];
        queue[randomIndex] = queue[--N];
        queue[N] = null;
        if (N > 0 && N == queue.length / 4)
            resize(queue.length / 2);
        return item;
    } // delete and return a random item

    /**
     * Return (but do not delete) a random item.
     * 
     * @return item
     */
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        return queue[StdRandom.uniform(N)];
    } // return (but do not delete) a random item

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    } // return an independent iterator over items in random order

    private class RandomizedIterator implements Iterator<Item> {

        private Item[] shuffeledQueue;
        private int index;

        private RandomizedIterator() {
            shuffeledQueue = (Item[]) new Object[N];
            for (int i = 0; i < N; i++)
                shuffeledQueue[i] = queue[i];
            StdRandom.shuffle(shuffeledQueue);
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return index < N;
        }

        @Override
        public Item next() {
            // TODO Auto-generated method stub
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return shuffeledQueue[index++];
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            throw new java.lang.UnsupportedOperationException();
        }

    }

    /**
     * Set of unit tests.
     * 
     * @param args
     */
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++)
            q.enqueue(i);
        for (int i = 0; i < 10; i++)
            q.dequeue();
        for (int i : q)
            System.out.println(i);
        for (int i = 0; i < 10; i++)
            q.enqueue(i);
        System.out.println("-----------------");
        for (int i : q)
            System.out.println(i);
        System.out.println("-----------------");
        for (int i : q)
            System.out.println(i);
    }
}
