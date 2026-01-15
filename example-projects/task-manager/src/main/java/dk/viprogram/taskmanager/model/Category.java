package dk.viprogram.taskmanager.model;

/**
 * A category for organizing tasks.
 * Implemented as a record since it's pure data.
 */
public record Category(
        String id,
        String name,
        String color
) {
    /**
     * Creates a category with just a name (generates ID, default color).
     */
    public static Category of(String name) {
        return new Category(
                java.util.UUID.randomUUID().toString().substring(0, 8),
                name,
                "#808080"
        );
    }

    /**
     * Returns a display-friendly string for use in dropdowns.
     */
    @Override
    public String toString() {
        return name;
    }
}
