package dk.viprogram.week09.library;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for library operations.
 *
 * This service contains business logic and uses repositories for persistence.
 * Notice how it doesn't know or care whether repositories are in-memory or file-based!
 *
 * Your task: Implement the TODO methods to complete the library functionality.
 */
public class LibraryService {

    private static final int LOAN_PERIOD_DAYS = 14;

    private final Repository<Book, String> bookRepository;
    private final Repository<Member, String> memberRepository;
    private final Repository<Loan, String> loanRepository;

    /**
     * Creates a new library service with the given repositories.
     *
     * @param bookRepository   repository for books
     * @param memberRepository repository for members
     * @param loanRepository   repository for loans
     */
    public LibraryService(
            Repository<Book, String> bookRepository,
            Repository<Member, String> memberRepository,
            Repository<Loan, String> loanRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
    }

    // ==================== Book Operations ====================

    /**
     * Adds a new book to the library.
     *
     * @return the saved book
     */
    public Book addBook(String isbn, String title, String author) {
        Book book = new Book(isbn, title, author);
        return bookRepository.save(book);
    }

    /**
     * Finds a book by its ISBN.
     */
    public Optional<Book> findBook(String isbn) {
        return bookRepository.findById(isbn);
    }

    /**
     * Returns all books in the library.
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Returns all available books (not currently borrowed).
     */
    public List<Book> getAvailableBooks() {
        return bookRepository.findAll().stream()
                .filter(Book::available)
                .toList();
    }

    // ==================== Member Operations ====================

    /**
     * Registers a new library member.
     *
     * @return the saved member
     */
    public Member registerMember(String name, String email) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Member member = new Member(id, name, email);
        return memberRepository.save(member);
    }

    /**
     * Finds a member by ID.
     */
    public Optional<Member> findMember(String memberId) {
        return memberRepository.findById(memberId);
    }

    /**
     * Returns all registered members.
     */
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // ==================== Loan Operations ====================

    /**
     * Borrows a book for a member.
     *
     * @param bookIsbn the ISBN of the book to borrow
     * @param memberId the ID of the member borrowing
     * @return the loan record
     * @throws IllegalArgumentException if book doesn't exist or is not available
     * @throws IllegalArgumentException if member doesn't exist
     */
    public Loan borrowBook(String bookIsbn, String memberId) {
        // TODO: Implement this method
        //
        // Steps:
        // 1. Find the book by ISBN
        //    - Throw IllegalArgumentException if not found: "Book not found: " + bookIsbn
        //
        // 2. Check if book is available
        //    - Throw IllegalArgumentException if not: "Book is not available: " + bookIsbn
        //
        // 3. Find the member by ID
        //    - Throw IllegalArgumentException if not found: "Member not found: " + memberId
        //
        // 4. Create a new Loan with:
        //    - id: UUID.randomUUID().toString().substring(0, 8)
        //    - bookIsbn: the book's ISBN
        //    - memberId: the member's ID
        //    - borrowDate: LocalDate.now()
        //    - dueDate: LocalDate.now().plusDays(LOAN_PERIOD_DAYS)
        //
        // 5. Save the loan using loanRepository.save()
        //
        // 6. Update the book to be not available:
        //    - bookRepository.save(book.borrow())
        //
        // 7. Return the saved loan
        //
        // Hint: Use Optional.orElseThrow() to get values and throw exceptions

        throw new UnsupportedOperationException("TODO: Implement borrowBook()");
    }

    /**
     * Returns a borrowed book.
     *
     * @param loanId the ID of the loan
     * @throws IllegalArgumentException if loan doesn't exist
     * @throws IllegalStateException    if book is already returned
     */
    public void returnBook(String loanId) {
        // TODO: Implement this method
        //
        // Steps:
        // 1. Find the loan by ID
        //    - Throw IllegalArgumentException if not found: "Loan not found: " + loanId
        //
        // 2. Check if already returned
        //    - Throw IllegalStateException if returned: "Book already returned"
        //
        // 3. Mark the loan as returned:
        //    - loanRepository.save(loan.markReturned())
        //
        // 4. Find the book and mark it as available:
        //    - Get book: bookRepository.findById(loan.bookIsbn())
        //    - Save updated: bookRepository.save(book.returnBook())

        throw new UnsupportedOperationException("TODO: Implement returnBook()");
    }

    /**
     * Gets all active (not returned) loans.
     */
    public List<Loan> getActiveLoans() {
        return loanRepository.findAll().stream()
                .filter(loan -> !loan.returned())
                .toList();
    }

    /**
     * Gets all overdue loans.
     */
    public List<Loan> getOverdueLoans() {
        return loanRepository.findAll().stream()
                .filter(Loan::isOverdue)
                .toList();
    }

    /**
     * Gets all loans for a specific member.
     */
    public List<Loan> getLoansForMember(String memberId) {
        return loanRepository.findAll().stream()
                .filter(loan -> loan.memberId().equals(memberId))
                .toList();
    }

    // ==================== Statistics ====================

    /**
     * Returns the total number of books in the library.
     */
    public long getTotalBooks() {
        return bookRepository.count();
    }

    /**
     * Returns the total number of registered members.
     */
    public long getTotalMembers() {
        return memberRepository.count();
    }

    /**
     * Returns the number of books currently on loan.
     */
    public long getBooksOnLoan() {
        return bookRepository.findAll().stream()
                .filter(book -> !book.available())
                .count();
    }
}
