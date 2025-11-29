package dk.viprogram.week05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the LinkedQueue implementation.
 *
 * These tests verify that your LinkedQueue correctly implements
 * the SimpleQueue interface with FIFO (First In, First Out) behavior.
 */
class QueueTest {

    @Nested
    @DisplayName("Exercise 2a: Basic Queue Operations")
    class BasicOperations {

        @Test
        @DisplayName("New queue should be empty")
        void newQueueIsEmpty() {
            SimpleQueue<String> queue = new LinkedQueue<>();
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("After enqueueing one element, queue should not be empty")
        void afterEnqueueingOneElement() {
            SimpleQueue<String> queue = new LinkedQueue<>();
            queue.enqueue("Hello");
            assertFalse(queue.isEmpty());
            assertEquals(1, queue.size());
        }

        @Test
        @DisplayName("Peek should return front element without removing it")
        void peekReturnsFrontWithoutRemoving() {
            SimpleQueue<String> queue = new LinkedQueue<>();
            queue.enqueue("First");
            queue.enqueue("Second");

            assertEquals("First", queue.peek());
            assertEquals("First", queue.peek());  // Still there
            assertEquals(2, queue.size());
        }

        @Test
        @DisplayName("Dequeue should return and remove front element")
        void dequeueReturnsAndRemoves() {
            SimpleQueue<String> queue = new LinkedQueue<>();
            queue.enqueue("First");
            queue.enqueue("Second");

            assertEquals("First", queue.dequeue());
            assertEquals(1, queue.size());
            assertEquals("Second", queue.peek());
        }
    }

    @Nested
    @DisplayName("Exercise 2b: FIFO Behavior")
    class FifoBehavior {

        @Test
        @DisplayName("Elements should come out in order added")
        void elementsInOrder() {
            SimpleQueue<Integer> queue = new LinkedQueue<>();
            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
            queue.enqueue(4);
            queue.enqueue(5);

            assertEquals(1, queue.dequeue());
            assertEquals(2, queue.dequeue());
            assertEquals(3, queue.dequeue());
            assertEquals(4, queue.dequeue());
            assertEquals(5, queue.dequeue());
            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("Interleaved enqueue and dequeue operations")
        void interleavedEnqueueDequeue() {
            SimpleQueue<String> queue = new LinkedQueue<>();

            queue.enqueue("A");
            queue.enqueue("B");
            assertEquals("A", queue.dequeue());

            queue.enqueue("C");
            queue.enqueue("D");
            assertEquals("B", queue.dequeue());
            assertEquals("C", queue.dequeue());
            assertEquals("D", queue.dequeue());

            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("Queue maintains FIFO across many operations")
        void maintainsFifoAcrossManyOperations() {
            SimpleQueue<Integer> queue = new LinkedQueue<>();

            // Add 1-5, remove 1-3, add 6-10, remove all
            for (int i = 1; i <= 5; i++) queue.enqueue(i);

            assertEquals(1, queue.dequeue());
            assertEquals(2, queue.dequeue());
            assertEquals(3, queue.dequeue());

            for (int i = 6; i <= 10; i++) queue.enqueue(i);

            // Should now get 4, 5, 6, 7, 8, 9, 10
            assertEquals(4, queue.dequeue());
            assertEquals(5, queue.dequeue());
            for (int i = 6; i <= 10; i++) {
                assertEquals(i, queue.dequeue());
            }

            assertTrue(queue.isEmpty());
        }
    }

    @Nested
    @DisplayName("Exercise 2c: Error Handling")
    class ErrorHandling {

        @Test
        @DisplayName("Dequeue on empty queue should throw NoSuchElementException")
        void dequeueOnEmptyThrows() {
            SimpleQueue<String> queue = new LinkedQueue<>();

            NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> queue.dequeue()
            );
            assertEquals("Queue is empty", exception.getMessage());
        }

        @Test
        @DisplayName("Peek on empty queue should throw NoSuchElementException")
        void peekOnEmptyThrows() {
            SimpleQueue<String> queue = new LinkedQueue<>();

            NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> queue.peek()
            );
            assertEquals("Queue is empty", exception.getMessage());
        }

        @Test
        @DisplayName("After emptying queue, dequeue should throw")
        void afterEmptyingQueueDequeueThrows() {
            SimpleQueue<String> queue = new LinkedQueue<>();
            queue.enqueue("Only");
            queue.dequeue();

            assertThrows(NoSuchElementException.class, () -> queue.dequeue());
        }
    }

    @Nested
    @DisplayName("Exercise 2d: Edge Cases")
    class EdgeCases {

        @Test
        @DisplayName("Queue should handle many elements")
        void handlesManyElements() {
            SimpleQueue<Integer> queue = new LinkedQueue<>();

            for (int i = 0; i < 100; i++) {
                queue.enqueue(i);
            }

            assertEquals(100, queue.size());

            for (int i = 0; i < 100; i++) {
                assertEquals(i, queue.dequeue());
            }

            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("Queue should handle null elements")
        void handlesNullElements() {
            SimpleQueue<String> queue = new LinkedQueue<>();
            queue.enqueue(null);
            queue.enqueue("NotNull");
            queue.enqueue(null);

            assertEquals(3, queue.size());
            assertNull(queue.dequeue());
            assertEquals("NotNull", queue.dequeue());
            assertNull(queue.dequeue());
        }

        @Test
        @DisplayName("Queue should work after being emptied and refilled")
        void worksAfterEmptyAndRefill() {
            SimpleQueue<String> queue = new LinkedQueue<>();

            // Fill
            queue.enqueue("A");
            queue.enqueue("B");

            // Empty
            queue.dequeue();
            queue.dequeue();
            assertTrue(queue.isEmpty());

            // Refill
            queue.enqueue("C");
            queue.enqueue("D");

            assertEquals("C", queue.dequeue());
            assertEquals("D", queue.dequeue());
            assertTrue(queue.isEmpty());
        }
    }
}
