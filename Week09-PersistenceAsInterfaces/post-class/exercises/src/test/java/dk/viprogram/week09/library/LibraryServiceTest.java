package dk.viprogram.week09.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for LibraryService using in-memory repositories.
 *
 * Notice how we test all the business logic without any files or databases!
 * This is the power of the Repository pattern - fast, isolated tests.
 */
class LibraryServiceTest {

    private LibraryService service;
    private InMemoryRepository<Book, String> bookRepo;
    private InMemoryRepository<Member, String> memberRepo;
    private InMemoryRepository<Loan, String> loanRepo;

    @BeforeEach
    void setUp() {
        bookRepo = new InMemoryRepository<>();
        memberRepo = new InMemoryRepository<>();
        loanRepo = new InMemoryRepository<>();
        service = new LibraryService(bookRepo, memberRepo, loanRepo);
    }

    // ==================== Book Tests ====================

    @Nested
    @DisplayName("Book Operations")
    class BookTests {

        @Test
        @DisplayName("Can add a book")
        void canAddBook() {
            Book book = service.addBook("978-0-13-468599-1", "Clean Code", "Robert Martin");

            assertNotNull(book);
            assertEquals("Clean Code", book.title());
            assertTrue(book.available());
        }

        @Test
        @DisplayName("Can find book by ISBN")
        void canFindBookByIsbn() {
            service.addBook("978-0-13-468599-1", "Clean Code", "Robert Martin");

            Optional<Book> found = service.findBook("978-0-13-468599-1");

            assertTrue(found.isPresent());
            assertEquals("Clean Code", found.get().title());
        }

        @Test
        @DisplayName("Returns empty for nonexistent book")
        void returnsEmptyForNonexistentBook() {
            Optional<Book> found = service.findBook("nonexistent");

            assertFalse(found.isPresent());
        }

        @Test
        @DisplayName("Can get all books")
        void canGetAllBooks() {
            service.addBook("1", "Book One", "Author A");
            service.addBook("2", "Book Two", "Author B");
            service.addBook("3", "Book Three", "Author C");

            List<Book> all = service.getAllBooks();

            assertEquals(3, all.size());
        }

        @Test
        @DisplayName("Can get available books")
        void canGetAvailableBooks() {
            service.addBook("1", "Book One", "Author A");
            service.addBook("2", "Book Two", "Author B");

            // Make book 1 unavailable
            Book book1 = service.findBook("1").get();
            bookRepo.save(book1.borrow());

            List<Book> available = service.getAvailableBooks();

            assertEquals(1, available.size());
            assertEquals("Book Two", available.get(0).title());
        }
    }

    // ==================== Member Tests ====================

    @Nested
    @DisplayName("Member Operations")
    class MemberTests {

        @Test
        @DisplayName("Can register a member")
        void canRegisterMember() {
            Member member = service.registerMember("John Doe", "john@example.com");

            assertNotNull(member);
            assertNotNull(member.id());
            assertEquals("John Doe", member.name());
            assertEquals("john@example.com", member.email());
        }

        @Test
        @DisplayName("Can find member by ID")
        void canFindMemberById() {
            Member registered = service.registerMember("John Doe", "john@example.com");

            Optional<Member> found = service.findMember(registered.id());

            assertTrue(found.isPresent());
            assertEquals("John Doe", found.get().name());
        }

        @Test
        @DisplayName("Can get all members")
        void canGetAllMembers() {
            service.registerMember("John Doe", "john@example.com");
            service.registerMember("Jane Smith", "jane@example.com");

            List<Member> all = service.getAllMembers();

            assertEquals(2, all.size());
        }
    }

    // ==================== Loan Tests ====================

    @Nested
    @DisplayName("Loan Operations")
    class LoanTests {

        private Book book;
        private Member member;

        @BeforeEach
        void setUpLoanTest() {
            book = service.addBook("978-0-13-468599-1", "Clean Code", "Robert Martin");
            member = service.registerMember("John Doe", "john@example.com");
        }

        @Test
        @DisplayName("Can borrow a book")
        void canBorrowBook() {
            Loan loan = service.borrowBook(book.isbn(), member.id());

            assertNotNull(loan);
            assertEquals(book.isbn(), loan.bookIsbn());
            assertEquals(member.id(), loan.memberId());
            assertFalse(loan.returned());
        }

        @Test
        @DisplayName("Borrowed book becomes unavailable")
        void borrowedBookBecomesUnavailable() {
            service.borrowBook(book.isbn(), member.id());

            Book updatedBook = service.findBook(book.isbn()).get();
            assertFalse(updatedBook.available());
        }

        @Test
        @DisplayName("Cannot borrow nonexistent book")
        void cannotBorrowNonexistentBook() {
            assertThrows(IllegalArgumentException.class, () ->
                    service.borrowBook("nonexistent", member.id()));
        }

        @Test
        @DisplayName("Cannot borrow unavailable book")
        void cannotBorrowUnavailableBook() {
            service.borrowBook(book.isbn(), member.id());

            Member member2 = service.registerMember("Jane Smith", "jane@example.com");

            assertThrows(IllegalArgumentException.class, () ->
                    service.borrowBook(book.isbn(), member2.id()));
        }

        @Test
        @DisplayName("Cannot borrow with nonexistent member")
        void cannotBorrowWithNonexistentMember() {
            assertThrows(IllegalArgumentException.class, () ->
                    service.borrowBook(book.isbn(), "nonexistent"));
        }

        @Test
        @DisplayName("Can return a book")
        void canReturnBook() {
            Loan loan = service.borrowBook(book.isbn(), member.id());

            service.returnBook(loan.id());

            Book updatedBook = service.findBook(book.isbn()).get();
            assertTrue(updatedBook.available());
        }

        @Test
        @DisplayName("Cannot return already returned book")
        void cannotReturnAlreadyReturnedBook() {
            Loan loan = service.borrowBook(book.isbn(), member.id());
            service.returnBook(loan.id());

            assertThrows(IllegalStateException.class, () ->
                    service.returnBook(loan.id()));
        }

        @Test
        @DisplayName("Cannot return nonexistent loan")
        void cannotReturnNonexistentLoan() {
            assertThrows(IllegalArgumentException.class, () ->
                    service.returnBook("nonexistent"));
        }

        @Test
        @DisplayName("Can get active loans")
        void canGetActiveLoans() {
            Book book2 = service.addBook("2", "Book Two", "Author B");
            Loan loan1 = service.borrowBook(book.isbn(), member.id());
            Loan loan2 = service.borrowBook(book2.isbn(), member.id());

            service.returnBook(loan1.id());

            List<Loan> active = service.getActiveLoans();
            assertEquals(1, active.size());
            assertEquals(loan2.id(), active.get(0).id());
        }

        @Test
        @DisplayName("Can get loans for member")
        void canGetLoansForMember() {
            Book book2 = service.addBook("2", "Book Two", "Author B");
            Member member2 = service.registerMember("Jane Smith", "jane@example.com");

            service.borrowBook(book.isbn(), member.id());
            service.borrowBook(book2.isbn(), member2.id());

            List<Loan> memberLoans = service.getLoansForMember(member.id());
            assertEquals(1, memberLoans.size());
        }
    }

    // ==================== Statistics Tests ====================

    @Nested
    @DisplayName("Statistics")
    class StatisticsTests {

        @Test
        @DisplayName("Total books count")
        void totalBooksCount() {
            service.addBook("1", "Book One", "Author A");
            service.addBook("2", "Book Two", "Author B");

            assertEquals(2, service.getTotalBooks());
        }

        @Test
        @DisplayName("Total members count")
        void totalMembersCount() {
            service.registerMember("John Doe", "john@example.com");
            service.registerMember("Jane Smith", "jane@example.com");
            service.registerMember("Bob Wilson", "bob@example.com");

            assertEquals(3, service.getTotalMembers());
        }

        @Test
        @DisplayName("Books on loan count")
        void booksOnLoanCount() {
            Book book1 = service.addBook("1", "Book One", "Author A");
            Book book2 = service.addBook("2", "Book Two", "Author B");
            service.addBook("3", "Book Three", "Author C");
            Member member = service.registerMember("John Doe", "john@example.com");

            service.borrowBook(book1.isbn(), member.id());
            service.borrowBook(book2.isbn(), member.id());

            assertEquals(2, service.getBooksOnLoan());
        }
    }
}
