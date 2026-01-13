package dk.viprogram.week09.library;

import org.junit.jupiter.api.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for JsonFileRepository.
 *
 * These tests verify that your file-based repository correctly persists
 * data to JSON files. Notice how the tests are very similar to the
 * InMemoryRepository tests - same interface, same behavior!
 */
class JsonFileRepositoryTest {

    private static final String TEST_FILE = "test-books.json";
    private JsonFileRepository<Book, String> repository;

    @BeforeEach
    void setUp() {
        repository = new JsonFileRepository<>(TEST_FILE, Book.class);
        repository.deleteFile();  // Start clean
    }

    @AfterEach
    void tearDown() {
        repository.deleteFile();  // Clean up
    }

    // ==================== Save Tests ====================

    @Nested
    @DisplayName("save() tests")
    class SaveTests {

        @Test
        @DisplayName("save() stores entity to file")
        void saveStoresToFile() {
            Book book = new Book("978-0-13-468599-1", "Clean Code", "Robert Martin");

            repository.save(book);

            assertTrue(Files.exists(Path.of(TEST_FILE)));
            assertEquals(1, repository.count());
        }

        @Test
        @DisplayName("save() updates existing entity")
        void saveUpdatesExisting() {
            Book original = new Book("1", "Original Title", "Author");
            Book updated = new Book("1", "Updated Title", "Author");

            repository.save(original);
            repository.save(updated);

            assertEquals(1, repository.count());
            assertEquals("Updated Title", repository.findById("1").get().title());
        }

        @Test
        @DisplayName("save() persists data between instances")
        void savePersistesBetweenInstances() {
            repository.save(new Book("1", "Persistent Book", "Author"));

            // Create new repository instance pointing to same file
            JsonFileRepository<Book, String> newRepo =
                    new JsonFileRepository<>(TEST_FILE, Book.class);

            Optional<Book> found = newRepo.findById("1");
            assertTrue(found.isPresent());
            assertEquals("Persistent Book", found.get().title());
        }
    }

    // ==================== FindById Tests ====================

    @Nested
    @DisplayName("findById() tests")
    class FindByIdTests {

        @Test
        @DisplayName("findById() returns entity when found")
        void findByIdReturnsWhenFound() {
            repository.save(new Book("1", "Test Book", "Author"));

            Optional<Book> found = repository.findById("1");

            assertTrue(found.isPresent());
            assertEquals("Test Book", found.get().title());
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
        @DisplayName("findAll() returns empty list when file doesn't exist")
        void findAllReturnsEmptyWhenNoFile() {
            List<Book> all = repository.findAll();

            assertNotNull(all);
            assertTrue(all.isEmpty());
        }

        @Test
        @DisplayName("findAll() returns all entities")
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
        @DisplayName("delete() removes entity from file")
        void deleteRemovesFromFile() {
            Book book = new Book("1", "To Delete", "Author");
            repository.save(book);

            repository.delete(book);

            assertEquals(0, repository.count());
            assertFalse(repository.existsById("1"));
        }

        @Test
        @DisplayName("deleteById() removes entity by ID")
        void deleteByIdRemoves() {
            repository.save(new Book("1", "Book One", "Author"));
            repository.save(new Book("2", "Book Two", "Author"));

            repository.deleteById("1");

            assertEquals(1, repository.count());
            assertFalse(repository.existsById("1"));
        }
    }

    // ==================== ExistsById Tests ====================

    @Nested
    @DisplayName("existsById() tests")
    class ExistsByIdTests {

        @Test
        @DisplayName("existsById() returns true when exists")
        void existsByIdReturnsTrueWhenExists() {
            repository.save(new Book("1", "Book", "Author"));

            assertTrue(repository.existsById("1"));
        }

        @Test
        @DisplayName("existsById() returns false when not exists")
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
        @DisplayName("count() returns correct number")
        void countReturnsCorrectNumber() {
            repository.save(new Book("1", "Book One", "Author"));
            repository.save(new Book("2", "Book Two", "Author"));

            assertEquals(2, repository.count());
        }
    }

    // ==================== File Content Tests ====================

    @Nested
    @DisplayName("File Content")
    class FileContentTests {

        @Test
        @DisplayName("File contains valid JSON")
        void fileContainsValidJson() throws Exception {
            repository.save(new Book("1", "Test Book", "Test Author"));

            String content = Files.readString(Path.of(TEST_FILE));

            assertTrue(content.contains("\"isbn\""));
            assertTrue(content.contains("\"title\""));
            assertTrue(content.contains("\"author\""));
            assertTrue(content.contains("Test Book"));
        }
    }
}
