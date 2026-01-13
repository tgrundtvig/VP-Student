package dk.viprogram.week09.library;

/**
 * Represents a book in the library.
 *
 * @param isbn      the International Standard Book Number (unique identifier)
 * @param title     the book's title
 * @param author    the book's author
 * @param available whether the book is available for borrowing
 */
public record Book(
        String isbn,
        String title,
        String author,
        boolean available
) implements Identifiable<String> {

    /**
     * Creates a new available book.
     */
    public Book(String isbn, String title, String author) {
        this(isbn, title, author, true);
    }

    @Override
    public String getId() {
        return isbn;
    }

    /**
     * Returns a copy of this book marked as borrowed (not available).
     */
    public Book borrow() {
        return new Book(isbn, title, author, false);
    }

    /**
     * Returns a copy of this book marked as returned (available).
     */
    public Book returnBook() {
        return new Book(isbn, title, author, true);
    }
}
