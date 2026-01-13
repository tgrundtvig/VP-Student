package dk.viprogram.week07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the KeyValueStore implementation.
 * Run these tests to verify your SimpleKeyValueStore implementation.
 */
@DisplayName("KeyValueStore")
class KeyValueStoreTest {

    private KeyValueStore<String, Player> store;

    @BeforeEach
    void setUp() {
        store = new SimpleKeyValueStore<>();
    }

    @Nested
    @DisplayName("Basic Operations")
    class BasicOperations {

        @Test
        @DisplayName("new store is empty")
        void newStoreIsEmpty() {
            assertTrue(store.isEmpty());
            assertEquals(0, store.size());
        }

        @Test
        @DisplayName("put adds a new entry")
        void putAddsEntry() {
            store.put("p1", new Player("Alice", 100));

            assertFalse(store.isEmpty());
            assertEquals(1, store.size());
        }

        @Test
        @DisplayName("put returns empty for new key")
        void putReturnsEmptyForNewKey() {
            Optional<Player> previous = store.put("p1", new Player("Alice", 100));

            assertTrue(previous.isEmpty());
        }

        @Test
        @DisplayName("get retrieves stored value")
        void getRetrievesValue() {
            Player alice = new Player("Alice", 100);
            store.put("p1", alice);

            Optional<Player> result = store.get("p1");

            assertTrue(result.isPresent());
            assertEquals(alice, result.get());
        }

        @Test
        @DisplayName("get returns empty for missing key")
        void getReturnsEmptyForMissingKey() {
            Optional<Player> result = store.get("nonexistent");

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Put Behavior")
    class PutBehavior {

        @Test
        @DisplayName("put replaces existing value")
        void putReplacesExistingValue() {
            Player alice = new Player("Alice", 100);
            Player aliceUpdated = new Player("Alice", 150);

            store.put("p1", alice);
            store.put("p1", aliceUpdated);

            assertEquals(1, store.size());  // Still only one entry
            assertEquals(aliceUpdated, store.get("p1").orElseThrow());
        }

        @Test
        @DisplayName("put returns previous value when replacing")
        void putReturnsPreviousValue() {
            Player alice = new Player("Alice", 100);
            Player aliceUpdated = new Player("Alice", 150);

            store.put("p1", alice);
            Optional<Player> previous = store.put("p1", aliceUpdated);

            assertTrue(previous.isPresent());
            assertEquals(alice, previous.get());
        }

        @Test
        @DisplayName("multiple entries are stored separately")
        void multipleEntriesStoredSeparately() {
            store.put("p1", new Player("Alice", 100));
            store.put("p2", new Player("Bob", 85));
            store.put("p3", new Player("Charlie", 90));

            assertEquals(3, store.size());
            assertEquals("Alice", store.get("p1").orElseThrow().name());
            assertEquals("Bob", store.get("p2").orElseThrow().name());
            assertEquals("Charlie", store.get("p3").orElseThrow().name());
        }
    }

    @Nested
    @DisplayName("ContainsKey")
    class ContainsKeyTests {

        @Test
        @DisplayName("containsKey returns true for existing key")
        void containsKeyReturnsTrueForExisting() {
            store.put("p1", new Player("Alice", 100));

            assertTrue(store.containsKey("p1"));
        }

        @Test
        @DisplayName("containsKey returns false for missing key")
        void containsKeyReturnsFalseForMissing() {
            assertFalse(store.containsKey("nonexistent"));
        }

        @Test
        @DisplayName("containsKey returns false after removal")
        void containsKeyReturnsFalseAfterRemoval() {
            store.put("p1", new Player("Alice", 100));
            store.remove("p1");

            assertFalse(store.containsKey("p1"));
        }
    }

    @Nested
    @DisplayName("Remove")
    class RemoveTests {

        @Test
        @DisplayName("remove returns the removed value")
        void removeReturnsValue() {
            Player alice = new Player("Alice", 100);
            store.put("p1", alice);

            Optional<Player> removed = store.remove("p1");

            assertTrue(removed.isPresent());
            assertEquals(alice, removed.get());
        }

        @Test
        @DisplayName("remove returns empty for missing key")
        void removeReturnsEmptyForMissing() {
            Optional<Player> removed = store.remove("nonexistent");

            assertTrue(removed.isEmpty());
        }

        @Test
        @DisplayName("remove decreases size")
        void removeDecreasesSize() {
            store.put("p1", new Player("Alice", 100));
            store.put("p2", new Player("Bob", 85));

            assertEquals(2, store.size());

            store.remove("p1");

            assertEquals(1, store.size());
        }
    }

    @Nested
    @DisplayName("Keys and Values")
    class KeysAndValuesTests {

        @Test
        @DisplayName("keys returns all keys")
        void keysReturnsAllKeys() {
            store.put("p1", new Player("Alice", 100));
            store.put("p2", new Player("Bob", 85));

            var keys = store.keys();

            assertEquals(2, keys.size());
            assertTrue(keys.contains("p1"));
            assertTrue(keys.contains("p2"));
        }

        @Test
        @DisplayName("values returns all values")
        void valuesReturnsAllValues() {
            Player alice = new Player("Alice", 100);
            Player bob = new Player("Bob", 85);
            store.put("p1", alice);
            store.put("p2", bob);

            var values = store.values();

            assertEquals(2, values.size());
            assertTrue(values.contains(alice));
            assertTrue(values.contains(bob));
        }

        @Test
        @DisplayName("keys returns empty set for empty store")
        void keysReturnsEmptySetForEmptyStore() {
            assertTrue(store.keys().isEmpty());
        }
    }

    @Nested
    @DisplayName("Clear")
    class ClearTests {

        @Test
        @DisplayName("clear removes all entries")
        void clearRemovesAllEntries() {
            store.put("p1", new Player("Alice", 100));
            store.put("p2", new Player("Bob", 85));

            store.clear();

            assertTrue(store.isEmpty());
            assertEquals(0, store.size());
            assertFalse(store.containsKey("p1"));
            assertFalse(store.containsKey("p2"));
        }

        @Test
        @DisplayName("clear on empty store does nothing")
        void clearOnEmptyStoreDoesNothing() {
            store.clear();

            assertTrue(store.isEmpty());
        }
    }
}
