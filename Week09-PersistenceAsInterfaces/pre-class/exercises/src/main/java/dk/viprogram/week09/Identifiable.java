package dk.viprogram.week09;

/**
 * Interface for entities that have a unique identifier.
 *
 * Every entity stored in a repository must be identifiable so that:
 * - We can find it by ID
 * - We can update it (same ID = same entity)
 * - We can delete it
 *
 * @param <ID> the type of the identifier (e.g., String, Long, UUID)
 */
public interface Identifiable<ID> {

    /**
     * Returns the unique identifier for this entity.
     *
     * @return the entity's ID (must not be null for saved entities)
     */
    ID getId();
}
