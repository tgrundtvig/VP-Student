package dk.viprogram.week09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the InMemoryRepository implementation.
 *
 * These tests verify that your repository correctly implements
 * all CRUD operations using the Book entity as a test case.
 */
class InMemoryRepositoryTest {

    private Repository<Book, String> repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryRepository<>();
    }

    // ==================== Save Tests ====================

    @Nested
    @DisplayName("save() tests")
    class SaveTests {

        @Test
        @DisplayName("save() stores a new entity")
        void saveStoresNewEntity() {
            Book book = new Book("978-0-13-468599-1", "Clean Code", "Robert Martin");

            Book saved = repository.save(book);

            assertEquals(book, saved);
            assertEquals(1, repository.count());
        }

        @Test
        @DisplayName("save() returns the saved entity")
        void saveReturnsEntity() {
            Book book = new Book("978-0-13-468599-1", "Clean Code", "Robert Martin");

            Book saved = repository.save(book);

            assertNotNull(saved);
            assertEquals("Clean Code", saved.title());
        }

        @Test
        @DisplayName("save() updates existing entity with same ID")
        void saveUpdatesExistingEntity() {
            Book original = new Book("978-0-13-468599-1", "Clean Code", "Robert Martin");
            Book updated = new Book("978-0-13-468599-1", "Clean Code: Updated Edition", "Robert Martin");

            repository.save(original);
            repository.save(updated);

            assertEquals(1, repository.count());
            Optional<Book> found = repository.findById("978-0-13-468599-1");
            assertTrue(found.isPresent());
            assertEquals("Clean Code: Updated Edition", found.get().title());
        }
    }

    // ==================== FindById Tests ====================

    @Nested
    @DisplayName("findById() tests")
    class FindByIdTests {

        @Test
        @DisplayName("findById() returns entity when found")
        void findByIdReturnsEntityWhenFound() {
            Book book = new Book("978-0-13-468599-1", "Clean Code", "Robert Martin");
            repository.save(book);

            Optional<Book> found = repository.findById("978-0-13-468599-1");

            assertTrue(found.isPresent());
            assertEquals("Clean Code", found.get().title());
        }

        @Test
        @DisplayName("findById() returns empty when not found")
        void findByIdReturnsEmptyWhenNotFound() {
            Optional<Book> found = repository.findById("nonexistent");

            assertFalse(found.isPresent());
        }
    }

    // ==================== FindAll Tests ====================

    @Nested
    @DisplayName("findAll() tests")
    class FindAllTests {

        @Test
        @DisplayName("findAll() returns empty list when repository is empty")
        void findAllReturnsEmptyListWhenEmpty() {
            List<Book> all = repository.findAll();

            assertNotNull(all);
            assertTrue(all.isEmpty());
        }

        @Test
        @DisplayName("findAll() returns all saved entities")
        void findAllReturnsAllEntities() {
            repository.save(new Book("1", "Book One", "Author A"));
            repository.save(new Book("2", "Book Two", "Author B"));
            repository.save(new Book("3", "Book Three", "Author C"));

            List<Book> all = repository.findAll();

            assertEquals(3, all.size());
        }
    }

    // ==================== Delete Tests ====================

    @Nested
    @DisplayName("delete() tests")
    class DeleteTests {

        @Test
        @DisplayName("delete() removes entity")
        void deleteRemovesEntity() {
            Book book = new Book("978-0-13-468599-1", "Clean Code", "Robert Martin");
            repository.save(book);

            repository.delete(book);

            assertEquals(0, repository.count());
            assertFalse(repository.existsById("978-0-13-468599-1"));
        }

        @Test
        @DisplayName("deleteById() removes entity by ID")
        void deleteByIdRemovesEntity() {
            repository.save(new Book("1", "Book One", "Author A"));
            repository.save(new Book("2", "Book Two", "Author B"));

            repository.deleteById("1");

            assertEquals(1, repository.count());
            assertFalse(repository.existsById("1"));
            assertTrue(repository.existsById("2"));
        }

        @Test
        @DisplayName("deleteById() does nothing for nonexistent ID")
        void deleteByIdDoesNothingForNonexistent() {
            repository.save(new Book("1", "Book One", "Author A"));

            repository.deleteById("nonexistent");

            assertEquals(1, repository.count());
        }
    }

    // ==================== ExistsById Tests ====================

    @Nested
    @DisplayName("existsById() tests")
    class ExistsByIdTests {

        @Test
        @DisplayName("existsById() returns true when entity exists")
        void existsByIdReturnsTrueWhenExists() {
            repository.save(new Book("1", "Book One", "Author A"));

            assertTrue(repository.existsById("1"));
        }

        @Test
        @DisplayName("existsById() returns false when entity doesn't exist")
        void existsByIdReturnsFalseWhenNotExists() {
            assertFalse(repository.existsById("nonexistent"));
        }
    }

    // ==================== Count Tests ====================

    @Nested
    @DisplayName("count() tests")
    class CountTests {

        @Test
        @DisplayName("count() returns 0 for empty repository")
        void countReturnsZeroWhenEmpty() {
            assertEquals(0, repository.count());
        }

        @Test
        @DisplayName("count() returns correct number of entities")
        void countReturnsCorrectNumber() {
            repository.save(new Book("1", "Book One", "Author A"));
            repository.save(new Book("2", "Book Two", "Author B"));
            repository.save(new Book("3", "Book Three", "Author C"));

            assertEquals(3, repository.count());
        }

        @Test
        @DisplayName("count() updates after delete")
        void countUpdatesAfterDelete() {
            repository.save(new Book("1", "Book One", "Author A"));
            repository.save(new Book("2", "Book Two", "Author B"));

            repository.deleteById("1");

            assertEquals(1, repository.count());
        }
    }
}
