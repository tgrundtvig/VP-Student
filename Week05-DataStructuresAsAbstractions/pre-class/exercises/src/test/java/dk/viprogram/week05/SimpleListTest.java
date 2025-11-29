package dk.viprogram.week05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ArraySimpleList implementation.
 *
 * These tests verify that your ArraySimpleList correctly implements
 * the SimpleList interface.
 */
class SimpleListTest {

    @Nested
    @DisplayName("Exercise 2a: Basic Operations")
    class BasicOperations {

        @Test
        @DisplayName("New list should be empty")
        void newListIsEmpty() {
            SimpleList<String> list = new ArraySimpleList<>();
            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("After adding one element, list should not be empty")
        void afterAddingOneElement() {
            SimpleList<String> list = new ArraySimpleList<>();
            list.add("Hello");
            assertFalse(list.isEmpty());
            assertEquals(1, list.size());
        }

        @Test
        @DisplayName("Should be able to get element by index")
        void getElementByIndex() {
            SimpleList<String> list = new ArraySimpleList<>();
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
            SimpleList<String> list = new ArraySimpleList<>();
            list.add("Only");

            assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(100));
        }
    }

    @Nested
    @DisplayName("Exercise 2b: Contains and Remove")
    class ContainsAndRemove {

        @Test
        @DisplayName("Contains should find existing elements")
        void containsFindsElements() {
            SimpleList<String> list = new ArraySimpleList<>();
            list.add("Apple");
            list.add("Banana");
            list.add("Cherry");

            assertTrue(list.contains("Apple"));
            assertTrue(list.contains("Banana"));
            assertTrue(list.contains("Cherry"));
            assertFalse(list.contains("Date"));
        }

        @Test
        @DisplayName("Remove should return and remove element")
        void removeReturnsAndRemoves() {
            SimpleList<String> list = new ArraySimpleList<>();
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
        @DisplayName("Remove should shift elements correctly")
        void removeShiftsElements() {
            SimpleList<Integer> list = new ArraySimpleList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);

            list.remove(0);  // Remove first

            assertEquals(4, list.size());
            assertEquals(2, list.get(0));
            assertEquals(3, list.get(1));
            assertEquals(4, list.get(2));
            assertEquals(5, list.get(3));
        }
    }

    @Nested
    @DisplayName("Exercise 2c: Clear and Capacity")
    class ClearAndCapacity {

        @Test
        @DisplayName("Clear should remove all elements")
        void clearRemovesAll() {
            SimpleList<String> list = new ArraySimpleList<>();
            list.add("One");
            list.add("Two");
            list.add("Three");

            list.clear();

            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("Should grow when adding more than initial capacity")
        void growsBeyondInitialCapacity() {
            SimpleList<Integer> list = new ArraySimpleList<>();

            // Add more than 10 elements (default capacity)
            for (int i = 0; i < 25; i++) {
                list.add(i);
            }

            assertEquals(25, list.size());
            for (int i = 0; i < 25; i++) {
                assertEquals(i, list.get(i));
            }
        }

        @Test
        @DisplayName("Should handle many operations correctly")
        void manyOperations() {
            SimpleList<String> list = new ArraySimpleList<>();

            // Add many elements
            for (int i = 0; i < 100; i++) {
                list.add("Element" + i);
            }
            assertEquals(100, list.size());

            // Remove some
            list.remove(50);
            list.remove(25);
            list.remove(0);
            assertEquals(97, list.size());

            // Check contains
            assertFalse(list.contains("Element0"));
            assertTrue(list.contains("Element1"));
            assertTrue(list.contains("Element99"));

            // Clear
            list.clear();
            assertTrue(list.isEmpty());
        }
    }

    @Nested
    @DisplayName("Exercise 2d: Edge Cases")
    class EdgeCases {

        @Test
        @DisplayName("Should handle null elements")
        void handlesNullElements() {
            SimpleList<String> list = new ArraySimpleList<>();
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
            SimpleList<String> list = new ArraySimpleList<>();
            list.add(new String("Test"));

            // Different String object but same content
            assertTrue(list.contains(new String("Test")));
        }

        @Test
        @DisplayName("Should work with different types")
        void worksWithDifferentTypes() {
            SimpleList<Integer> intList = new ArraySimpleList<>();
            intList.add(1);
            intList.add(2);
            assertEquals(Integer.valueOf(1), intList.get(0));

            SimpleList<Double> doubleList = new ArraySimpleList<>();
            doubleList.add(1.5);
            doubleList.add(2.5);
            assertEquals(Double.valueOf(1.5), doubleList.get(0));
        }
    }
}
