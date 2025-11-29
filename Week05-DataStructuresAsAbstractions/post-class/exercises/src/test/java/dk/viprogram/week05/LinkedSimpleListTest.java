package dk.viprogram.week05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the LinkedSimpleList implementation.
 *
 * These tests verify that your LinkedSimpleList correctly implements
 * the SimpleList interface using a linked structure.
 */
class LinkedSimpleListTest {

    @Nested
    @DisplayName("Exercise 1a: Basic Operations")
    class BasicOperations {

        @Test
        @DisplayName("New list should be empty")
        void newListIsEmpty() {
            SimpleList<String> list = new LinkedSimpleList<>();
            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("After adding one element, list should not be empty")
        void afterAddingOneElement() {
            SimpleList<String> list = new LinkedSimpleList<>();
            list.add("Hello");
            assertFalse(list.isEmpty());
            assertEquals(1, list.size());
        }

        @Test
        @DisplayName("Should be able to get element by index")
        void getElementByIndex() {
            SimpleList<String> list = new LinkedSimpleList<>();
            list.add("First");
            list.add("Second");
            list.add("Third");

            assertEquals("First", list.get(0));
            assertEquals("Second", list.get(1));
            assertEquals("Third", list.get(2));
        }

        @Test
        @DisplayName("Should throw exception for invalid index")
        void invalidIndexThrowsException() {
            SimpleList<String> list = new LinkedSimpleList<>();
            list.add("Only");

            assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(100));
        }
    }

    @Nested
    @DisplayName("Exercise 1b: Contains and Remove")
    class ContainsAndRemove {

        @Test
        @DisplayName("Contains should find existing elements")
        void containsFindsElements() {
            SimpleList<String> list = new LinkedSimpleList<>();
            list.add("Apple");
            list.add("Banana");
            list.add("Cherry");

            assertTrue(list.contains("Apple"));
            assertTrue(list.contains("Banana"));
            assertTrue(list.contains("Cherry"));
            assertFalse(list.contains("Date"));
        }

        @Test
        @DisplayName("Remove first element (head)")
        void removeFirstElement() {
            SimpleList<String> list = new LinkedSimpleList<>();
            list.add("First");
            list.add("Second");
            list.add("Third");

            String removed = list.remove(0);

            assertEquals("First", removed);
            assertEquals(2, list.size());
            assertEquals("Second", list.get(0));
            assertEquals("Third", list.get(1));
        }

        @Test
        @DisplayName("Remove middle element")
        void removeMiddleElement() {
            SimpleList<String> list = new LinkedSimpleList<>();
            list.add("First");
            list.add("Second");
            list.add("Third");

            String removed = list.remove(1);

            assertEquals("Second", removed);
            assertEquals(2, list.size());
            assertEquals("First", list.get(0));
            assertEquals("Third", list.get(1));
        }

        @Test
        @DisplayName("Remove last element")
        void removeLastElement() {
            SimpleList<String> list = new LinkedSimpleList<>();
            list.add("First");
            list.add("Second");
            list.add("Third");

            String removed = list.remove(2);

            assertEquals("Third", removed);
            assertEquals(2, list.size());
            assertEquals("First", list.get(0));
            assertEquals("Second", list.get(1));
        }

        @Test
        @DisplayName("Remove only element")
        void removeOnlyElement() {
            SimpleList<String> list = new LinkedSimpleList<>();
            list.add("Only");

            String removed = list.remove(0);

            assertEquals("Only", removed);
            assertTrue(list.isEmpty());
        }
    }

    @Nested
    @DisplayName("Exercise 1c: Clear and Many Elements")
    class ClearAndManyElements {

        @Test
        @DisplayName("Clear should remove all elements")
        void clearRemovesAll() {
            SimpleList<String> list = new LinkedSimpleList<>();
            list.add("One");
            list.add("Two");
            list.add("Three");

            list.clear();

            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("Should handle many elements")
        void manyElements() {
            SimpleList<Integer> list = new LinkedSimpleList<>();

            for (int i = 0; i < 100; i++) {
                list.add(i);
            }

            assertEquals(100, list.size());
            for (int i = 0; i < 100; i++) {
                assertEquals(i, list.get(i));
            }
        }

        @Test
        @DisplayName("Should handle many operations correctly")
        void manyOperations() {
            SimpleList<String> list = new LinkedSimpleList<>();

            // Add many elements
            for (int i = 0; i < 50; i++) {
                list.add("Element" + i);
            }
            assertEquals(50, list.size());

            // Remove from various positions
            list.remove(0);   // Remove first
            list.remove(48);  // Remove last (was index 49, now 48)
            list.remove(25);  // Remove middle
            assertEquals(47, list.size());

            // Check contains
            assertFalse(list.contains("Element0"));
            assertTrue(list.contains("Element1"));
            assertFalse(list.contains("Element49"));

            // Clear
            list.clear();
            assertTrue(list.isEmpty());
        }
    }

    @Nested
    @DisplayName("Exercise 1d: Edge Cases")
    class EdgeCases {

        @Test
        @DisplayName("Should handle null elements")
        void handlesNullElements() {
            SimpleList<String> list = new LinkedSimpleList<>();
            list.add(null);
            list.add("NotNull");
            list.add(null);

            assertEquals(3, list.size());
            assertNull(list.get(0));
            assertEquals("NotNull", list.get(1));
            assertNull(list.get(2));
            assertTrue(list.contains(null));
        }

        @Test
        @DisplayName("Contains should use equals() for comparison")
        void containsUsesEquals() {
            SimpleList<String> list = new LinkedSimpleList<>();
            list.add(new String("Test"));

            // Different String object but same content
            assertTrue(list.contains(new String("Test")));
        }

        @Test
        @DisplayName("Get on empty list should throw")
        void getOnEmptyListThrows() {
            SimpleList<String> list = new LinkedSimpleList<>();
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        }

        @Test
        @DisplayName("Remove on empty list should throw")
        void removeOnEmptyListThrows() {
            SimpleList<String> list = new LinkedSimpleList<>();
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        }
    }
}
