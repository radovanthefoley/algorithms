/**
 * @author Radovan Masaryk
 * 
 *         <i>Deque</i>. A double-ended queue or deque (pronounced "deck")is a
 *         generalization of a stack and a queue that supports inserting and
 *         removing items from either the front or the back of the data
 *         structure.
 */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node begin;
    private Node end;
    private int size;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    /**
     * Creates empty Deque.
     */
    public Deque() {
        begin = new Node();
        end = new Node();
        begin.next = end;
        end.previous = begin;
    } // construct an empty deque

    /**
     * Is Deque empty?
     * 
     * @return true or false
     */
    public boolean isEmpty() {
        return size() == 0;
    } // is the deque empty?

    /**
     * What is the number of elements within Deque?
     * 
     * @return size
     */
    public int size() {
        return size;
    } // return the number of items on the deque

    /**
     * Add item at the front.
     * 
     * @param item
     *            item to be added
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();

        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.next = begin.next;
        newFirst.previous = begin;
        newFirst.next.previous = newFirst;
        begin.next = newFirst;
        size++;
    } // insert the item at the front

    /**
     * Add item at the end.
     * 
     * @param item
     *            item to be added
     */
    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();

        Node newLast = new Node();
        newLast.item = item;
        newLast.next = end;
        newLast.previous = end.previous;
        newLast.previous.next = newLast;
        end.previous = newLast;
        size++;
    } // insert the item at the end

    /**
     * Removes item from begin.
     * 
     * @return first item
     */
    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        Node oldFirst = begin.next;
        begin.next = begin.next.next;
        oldFirst.next.previous = begin;
        oldFirst.next = null;
        oldFirst.previous = null;
        size--;

        return oldFirst.item;
    } // delete and return the item at the front

    /**
     * Removes item from end.
     * 
     * @return last item
     */
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        Node oldLast = end.previous;
        end.previous = end.previous.previous;
        oldLast.previous.next = end;
        oldLast.next = null;
        oldLast.previous = null;
        size--;

        return oldLast.item;
    } // delete and return the item at the end

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    } // return an iterator over items in order from front to end

    private class DequeIterator implements Iterator<Item> {

        private Node current = begin.next;

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return current != end;
        }

        @Override
        public Item next() {
            // TODO Auto-generated method stub
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;

            return item;
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
        System.out.println("empty Deque initialized");
        Deque<String> d = new Deque<String>();
        System.out.println("size() should return 0: " + d.size());
        System.out.println("isEmpty() should return true: " + d.isEmpty());
        d.addFirst("1");
        System.out.println("addFirst(\"1\")");
        System.out.println("size() should return 1: " + d.size());
        System.out.println("isEmpty() should return false: " + d.isEmpty());
        d.addLast("2");
        System.out.println("addLast(\"2\")");
        System.out.println("size() should return 2: " + d.size());
        System.out.println("isEmpty() should return false: " + d.isEmpty());

        System.out.println("removeFirst() Should return 1: " + d.removeFirst());
        System.out.println("size() should return 1: " + d.size());
        System.out.println("isEmpty() should return false: " + d.isEmpty());
        System.out.println("removeLast() Should return 2: " + d.removeLast());
        System.out.println("size() should return 0: " + d.size());
        System.out.println("isEmpty() should return true: " + d.isEmpty());

        d.addLast("second");
        d.addFirst("first");
        for (String s : d) {
            System.out.println(s);
        }
        System.out.println(d.removeFirst());
        System.out.println(d.removeFirst());
        for (String s : d) {
            System.out.println(s);
        }
        System.out.println("-----------------------------------------");
    }
}
