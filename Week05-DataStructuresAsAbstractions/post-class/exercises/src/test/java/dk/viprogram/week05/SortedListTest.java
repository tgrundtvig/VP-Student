package dk.viprogram.week05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ArraySortedList implementation.
 *
 * These tests verify that your ArraySortedList correctly implements
 * the SortedSimpleList interface, maintaining elements in sorted order.
 */
class SortedListTest {

    @Nested
    @DisplayName("Exercise 3a: Basic Sorted Operations")
    class BasicOperations {

        @Test
        @DisplayName("New list should be empty")
        void newListIsEmpty() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("Adding elements keeps them sorted")
        void addingKeepsSorted() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(3);
            list.add(1);
            list.add(4);
            list.add(1);
            list.add(5);
            list.add(9);
            list.add(2);

            // Should be: 1, 1, 2, 3, 4, 5, 9
            assertEquals(7, list.size());
            assertEquals(1, list.get(0));
            assertEquals(1, list.get(1));
            assertEquals(2, list.get(2));
            assertEquals(3, list.get(3));
            assertEquals(4, list.get(4));
            assertEquals(5, list.get(5));
            assertEquals(9, list.get(6));
        }

        @Test
        @DisplayName("Elements added in order stay in order")
        void elementsInOrderStayInOrder() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(1);
            list.add(2);
            list.add(3);

            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
        }

        @Test
        @DisplayName("Elements added in reverse order get sorted")
        void elementsInReverseGetSorted() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(5);
            list.add(4);
            list.add(3);
            list.add(2);
            list.add(1);

            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
            assertEquals(4, list.get(3));
            assertEquals(5, list.get(4));
        }
    }

    @Nested
    @DisplayName("Exercise 3b: Min, Max, and IndexOf")
    class MinMaxIndexOf {

        @Test
        @DisplayName("getMin returns smallest element")
        void getMinReturnsSmallest() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(5);
            list.add(3);
            list.add(7);
            list.add(1);

            assertEquals(1, list.getMin());
        }

        @Test
        @DisplayName("getMax returns largest element")
        void getMaxReturnsLargest() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(5);
            list.add(3);
            list.add(7);
            list.add(1);

            assertEquals(7, list.getMax());
        }

        @Test
        @DisplayName("getMin on empty list throws")
        void getMinOnEmptyThrows() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            assertThrows(NoSuchElementException.class, () -> list.getMin());
        }

        @Test
        @DisplayName("getMax on empty list throws")
        void getMaxOnEmptyThrows() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            assertThrows(NoSuchElementException.class, () -> list.getMax());
        }

        @Test
        @DisplayName("indexOf finds existing elements")
        void indexOfFindsElements() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(10);
            list.add(20);
            list.add(30);
            list.add(40);
            list.add(50);

            assertEquals(0, list.indexOf(10));
            assertEquals(1, list.indexOf(20));
            assertEquals(2, list.indexOf(30));
            assertEquals(3, list.indexOf(40));
            assertEquals(4, list.indexOf(50));
        }

        @Test
        @DisplayName("indexOf returns -1 for missing elements")
        void indexOfReturnsNegativeForMissing() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(10);
            list.add(30);
            list.add(50);

            assertEquals(-1, list.indexOf(20));
            assertEquals(-1, list.indexOf(5));
            assertEquals(-1, list.indexOf(100));
        }

        @Test
        @DisplayName("indexOf finds first occurrence of duplicate")
        void indexOfFindsDuplicate() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(1);
            list.add(2);
            list.add(2);
            list.add(2);
            list.add(3);

            int index = list.indexOf(2);
            assertTrue(index >= 1 && index <= 3, "Should find one of the 2s");
            assertEquals(2, list.get(index));
        }
    }

    @Nested
    @DisplayName("Exercise 3c: Remove and Contains")
    class RemoveAndContains {

        @Test
        @DisplayName("Remove maintains sorted order")
        void removeMaintainsOrder() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);

            list.remove(2);  // Remove the 3

            assertEquals(4, list.size());
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(4, list.get(2));
            assertEquals(5, list.get(3));
        }

        @Test
        @DisplayName("Contains uses sorted structure")
        void containsWorks() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(10);
            list.add(30);
            list.add(50);
            list.add(20);
            list.add(40);

            assertTrue(list.contains(10));
            assertTrue(list.contains(20));
            assertTrue(list.contains(30));
            assertTrue(list.contains(40));
            assertTrue(list.contains(50));
            assertFalse(list.contains(15));
            assertFalse(list.contains(100));
        }

        @Test
        @DisplayName("Clear empties the list")
        void clearEmptiesList() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(1);
            list.add(2);
            list.add(3);

            list.clear();

            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
        }
    }

    @Nested
    @DisplayName("Exercise 3d: Strings and Edge Cases")
    class StringsAndEdgeCases {

        @Test
        @DisplayName("Works with String elements")
        void worksWithStrings() {
            SortedSimpleList<String> list = new ArraySortedList<>();
            list.add("Charlie");
            list.add("Alice");
            list.add("Bob");
            list.add("David");

            assertEquals("Alice", list.get(0));
            assertEquals("Bob", list.get(1));
            assertEquals("Charlie", list.get(2));
            assertEquals("David", list.get(3));
        }

        @Test
        @DisplayName("Works with many elements")
        void worksWithManyElements() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();

            // Add in random order
            for (int i = 50; i > 0; i--) {
                list.add(i);
            }
            for (int i = 51; i <= 100; i++) {
                list.add(i);
            }

            assertEquals(100, list.size());

            // Should be sorted 1-100
            for (int i = 0; i < 100; i++) {
                assertEquals(i + 1, list.get(i));
            }

            assertEquals(1, list.getMin());
            assertEquals(100, list.getMax());
        }

        @Test
        @DisplayName("Single element list works correctly")
        void singleElementList() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(42);

            assertEquals(1, list.size());
            assertEquals(42, list.get(0));
            assertEquals(42, list.getMin());
            assertEquals(42, list.getMax());
            assertEquals(0, list.indexOf(42));
            assertTrue(list.contains(42));
        }

        @Test
        @DisplayName("Handles duplicates correctly")
        void handlesDuplicates() {
            SortedSimpleList<Integer> list = new ArraySortedList<>();
            list.add(5);
            list.add(5);
            list.add(5);

            assertEquals(3, list.size());
            assertEquals(5, list.get(0));
            assertEquals(5, list.get(1));
            assertEquals(5, list.get(2));
            assertEquals(5, list.getMin());
            assertEquals(5, list.getMax());
        }
    }
}
