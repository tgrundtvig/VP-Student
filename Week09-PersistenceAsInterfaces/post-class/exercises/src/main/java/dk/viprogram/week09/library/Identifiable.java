package dk.viprogram.week09.library;

/**
 * Interface for entities that have a unique identifier.
 *
 * @param <ID> the type of the identifier
 */
public interface Identifiable<ID> {
    ID getId();
}
