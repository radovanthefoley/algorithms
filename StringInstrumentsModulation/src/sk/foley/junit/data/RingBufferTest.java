package sk.foley.junit.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import sk.foley.java.data.RingBuffer;

@RunWith(value = org.junit.runners.JUnit4.class)
public class RingBufferTest {

    private static final int SIZE = 5;

    RingBuffer buffer;

    @Before
    public void init() {
        buffer = new RingBuffer(SIZE);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testPeekEmptyBuffer() {
        buffer.peek();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testDequeEmptyBuffer() {
        buffer.dequeue();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testEnqueFullBuffer() {
        makeBufferFull();
        buffer.enqueue(1.0);
    }

    @Test
    public void testSize() {
        assertEquals(0, buffer.getSize());
        buffer.enqueue(1.0);
        assertEquals("one element buffer size test", 1, buffer.getSize());
        buffer.peek();
        buffer.dequeue();
        buffer.enqueue(1.0);
        assertEquals(
                "one element buffer size test after peek-deque-enque sequence",
                1, buffer.getSize());
        makeBufferFull();
        assertEquals("full buffer size test", SIZE, buffer.getSize());
        buffer.peek();
        buffer.dequeue();
        buffer.enqueue(1.0);
        assertEquals("full buffer size test after peek-deque-enque sequence",
                SIZE, buffer.getSize());
        makeBufferEmpty();
        assertEquals("empty buffer again test", 0, buffer.getSize());
    }

    @Test
    public void testCorrectness() {
        buffer.enqueue(0.0);
        assertTrue(buffer.peek() == 0);
        assertTrue(buffer.dequeue() == 0);
        buffer.enqueue(0.0);
        buffer.enqueue(1.0);
        assertTrue(buffer.peek() == 0);
        assertTrue(buffer.dequeue() == 0);
        assertTrue(buffer.peek() == 1);
        assertTrue(buffer.dequeue() == 1);
        buffer.enqueue(0.0);
        buffer.enqueue(1.0);
        buffer.enqueue(2.0);
        buffer.enqueue(3.0);
        buffer.enqueue(4.0);
        assertTrue(buffer.peek() == 0);
        assertTrue(buffer.dequeue() == 0);
        assertTrue(buffer.peek() == 1);
        assertTrue(buffer.dequeue() == 1);
        assertTrue(buffer.peek() == 2);
        assertTrue(buffer.dequeue() == 2);
        assertTrue(buffer.peek() == 3);
        assertTrue(buffer.dequeue() == 3);
        assertTrue(buffer.peek() == 4);
        assertTrue(buffer.dequeue() == 4);
    }

    private void makeBufferFull() {
        for (int i = 0; i < SIZE; i++) {
            try {
                buffer.enqueue(1.0);
            } catch (java.util.NoSuchElementException e) {
            }
        }
    }

    private void makeBufferEmpty() {
        for (int i = 0; i < SIZE; i++) {
            try {
                buffer.dequeue();
            } catch (java.util.NoSuchElementException e) {
            }
        }
    }
}
