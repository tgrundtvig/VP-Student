package dk.viprogram.week09.library;

import java.time.LocalDate;

/**
 * Represents a book loan.
 *
 * @param id         unique loan identifier
 * @param bookIsbn   the ISBN of the borrowed book
 * @param memberId   the ID of the borrowing member
 * @param borrowDate when the book was borrowed
 * @param dueDate    when the book should be returned
 * @param returned   whether the book has been returned
 */
public record Loan(
        String id,
        String bookIsbn,
        String memberId,
        LocalDate borrowDate,
        LocalDate dueDate,
        boolean returned
) implements Identifiable<String> {

    /**
     * Creates a new active loan (not yet returned).
     */
    public Loan(String id, String bookIsbn, String memberId,
                LocalDate borrowDate, LocalDate dueDate) {
        this(id, bookIsbn, memberId, borrowDate, dueDate, false);
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns a copy of this loan marked as returned.
     */
    public Loan markReturned() {
        return new Loan(id, bookIsbn, memberId, borrowDate, dueDate, true);
    }

    /**
     * Checks if this loan is overdue.
     */
    public boolean isOverdue() {
        return !returned && LocalDate.now().isAfter(dueDate);
    }
}
