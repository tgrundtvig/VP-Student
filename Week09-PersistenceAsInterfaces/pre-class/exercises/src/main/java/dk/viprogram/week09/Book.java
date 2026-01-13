package dk.viprogram.week09;

/**
 * A simple Book entity for demonstrating the Repository pattern.
 *
 * This record implements Identifiable with ISBN as the unique identifier.
 * ISBN (International Standard Book Number) is a natural unique key for books.
 *
 * @param isbn   the International Standard Book Number (unique identifier)
 * @param title  the book's title
 * @param author the book's author
 */
public record Book(
        String isbn,
        String title,
        String author
) implements Identifiable<String> {

    @Override
    public String getId() {
        return isbn;
    }
}
