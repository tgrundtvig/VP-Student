package dk.viprogram.week09.library;

/**
 * Represents a library member.
 *
 * @param id    the unique member ID
 * @param name  the member's name
 * @param email the member's email address
 */
public record Member(
        String id,
        String name,
        String email
) implements Identifiable<String> {

    @Override
    public String getId() {
        return id;
    }
}
