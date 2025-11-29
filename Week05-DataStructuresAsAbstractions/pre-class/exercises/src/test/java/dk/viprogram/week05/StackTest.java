package dk.viprogram.week05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ArrayStack implementation.
 *
 * These tests verify that your ArrayStack correctly implements
 * the SimpleStack interface with LIFO (Last In, First Out) behavior.
 */
class StackTest {

    @Nested
    @DisplayName("Exercise 3a: Basic Stack Operations")
    class BasicOperations {

        @Test
        @DisplayName("New stack should be empty")
        void newStackIsEmpty() {
            SimpleStack<String> stack = new ArrayStack<>();
            assertTrue(stack.isEmpty());
            assertEquals(0, stack.size());
        }

        @Test
        @DisplayName("After pushing one element, stack should not be empty")
        void afterPushingOneElement() {
            SimpleStack<String> stack = new ArrayStack<>();
            stack.push("Hello");
            assertFalse(stack.isEmpty());
            assertEquals(1, stack.size());
        }

        @Test
        @DisplayName("Peek should return top element without removing it")
        void peekReturnsTopWithoutRemoving() {
            SimpleStack<String> stack = new ArrayStack<>();
            stack.push("First");
            stack.push("Second");

            assertEquals("Second", stack.peek());
            assertEquals("Second", stack.peek());  // Still there
            assertEquals(2, stack.size());
        }

        @Test
        @DisplayName("Pop should return and remove top element")
        void popReturnsAndRemoves() {
            SimpleStack<String> stack = new ArrayStack<>();
            stack.push("First");
            stack.push("Second");

            assertEquals("Second", stack.pop());
            assertEquals(1, stack.size());
            assertEquals("First", stack.peek());
        }
    }

    @Nested
    @DisplayName("Exercise 3b: LIFO Behavior")
    class LifoBehavior {

        @Test
        @DisplayName("Elements should come out in reverse order")
        void elementsInReverseOrder() {
            SimpleStack<Integer> stack = new ArrayStack<>();
            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.push(4);
            stack.push(5);

            assertEquals(5, stack.pop());
            assertEquals(4, stack.pop());
            assertEquals(3, stack.pop());
            assertEquals(2, stack.pop());
            assertEquals(1, stack.pop());
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("Interleaved push and pop operations")
        void interleavedPushPop() {
            SimpleStack<String> stack = new ArrayStack<>();

            stack.push("A");
            stack.push("B");
            assertEquals("B", stack.pop());

            stack.push("C");
            stack.push("D");
            assertEquals("D", stack.pop());
            assertEquals("C", stack.pop());
            assertEquals("A", stack.pop());

            assertTrue(stack.isEmpty());
        }
    }

    @Nested
    @DisplayName("Exercise 3c: Error Handling")
    class ErrorHandling {

        @Test
        @DisplayName("Pop on empty stack should throw NoSuchElementException")
        void popOnEmptyThrows() {
            SimpleStack<String> stack = new ArrayStack<>();

            NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> stack.pop()
            );
            assertEquals("Stack is empty", exception.getMessage());
        }

        @Test
        @DisplayName("Peek on empty stack should throw NoSuchElementException")
        void peekOnEmptyThrows() {
            SimpleStack<String> stack = new ArrayStack<>();

            NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> stack.peek()
            );
            assertEquals("Stack is empty", exception.getMessage());
        }

        @Test
        @DisplayName("After emptying stack, pop should throw")
        void afterEmptyingStackPopThrows() {
            SimpleStack<String> stack = new ArrayStack<>();
            stack.push("Only");
            stack.pop();

            assertThrows(NoSuchElementException.class, () -> stack.pop());
        }
    }

    @Nested
    @DisplayName("Exercise 3d: Growth and Edge Cases")
    class GrowthAndEdgeCases {

        @Test
        @DisplayName("Stack should grow beyond initial capacity")
        void growsBeyondInitialCapacity() {
            SimpleStack<Integer> stack = new ArrayStack<>();

            // Push more than 10 elements (default capacity)
            for (int i = 0; i < 25; i++) {
                stack.push(i);
            }

            assertEquals(25, stack.size());

            // Verify LIFO order when popping
            for (int i = 24; i >= 0; i--) {
                assertEquals(i, stack.pop());
            }
        }

        @Test
        @DisplayName("Stack should handle null elements")
        void handlesNullElements() {
            SimpleStack<String> stack = new ArrayStack<>();
            stack.push(null);
            stack.push("NotNull");
            stack.push(null);

            assertEquals(3, stack.size());
            assertNull(stack.pop());
            assertEquals("NotNull", stack.pop());
            assertNull(stack.pop());
        }

        @Test
        @DisplayName("Stack should work with different types")
        void worksWithDifferentTypes() {
            SimpleStack<Double> doubleStack = new ArrayStack<>();
            doubleStack.push(1.5);
            doubleStack.push(2.5);
            assertEquals(Double.valueOf(2.5), doubleStack.pop());

            SimpleStack<Character> charStack = new ArrayStack<>();
            charStack.push('A');
            charStack.push('B');
            assertEquals(Character.valueOf('B'), charStack.pop());
        }
    }
}
