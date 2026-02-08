package dk.viprogram.week11;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Pitch Preparation
 *
 * Fill in each field with information about YOUR exam project.
 * This class will serve as the basis for your 3-minute pitch in class.
 *
 * Run the tests to verify that your pitch is complete.
 */
public class ProjectPitch {

    // ==================== Project Identity ====================

    /**
     * Your project's title.
     * Should be clear and descriptive, e.g. "Personal Recipe Manager" or "Workout Tracker".
     *
     * TODO: Replace the empty string with your project title.
     */
    private final String projectTitle = ""; // TODO: Fill in your project title

    /**
     * A 2-3 sentence description of your project.
     * What does it do? Who is it for? What problem does it solve?
     *
     * TODO: Replace the empty string with your project description.
     */
    private final String description = ""; // TODO: Fill in your project description

    // ==================== Main Entities ====================

    /**
     * The main entities (data objects / records) your project will manage.
     * List at least 2 entities. For each, include its name and a brief description.
     *
     * Example entries:
     *   "Recipe - A cooking recipe with title, ingredients, and steps"
     *   "Ingredient - A single ingredient with name, amount, and unit"
     *
     * TODO: Add your entities to this list.
     */
    private final List<String> mainEntities = new ArrayList<>(List.of(
            // TODO: Add your entities here, e.g.:
            // "EntityName - Brief description of what it represents"
    ));

    // ==================== Core Interfaces ====================

    /**
     * The core interfaces that define your system's behavior contracts.
     * List at least 2 interfaces. For each, include its name and purpose.
     *
     * You need at minimum:
     *   - A View interface (handles user interaction)
     *   - A Repository interface (handles data persistence)
     *
     * Example entries:
     *   "RecipeView - Displays recipes and collects user input"
     *   "RecipeRepository - Stores and retrieves recipes"
     *   "ScalingStrategy - Scales recipe ingredients for different serving sizes"
     *
     * TODO: Add your interfaces to this list.
     */
    private final List<String> coreInterfaces = new ArrayList<>(List.of(
            // TODO: Add your interfaces here, e.g.:
            // "InterfaceName - Purpose of this interface"
    ));

    // ==================== Patterns Used ====================

    /**
     * The design patterns from weeks 3-9 that you plan to use.
     * For each pattern, briefly explain how it applies.
     *
     * Every project should use at least MVC and Repository.
     * Consider also: Strategy, Factory, Observer.
     *
     * Example entries:
     *   "MVC - RecipeView handles display, RecipeController coordinates logic"
     *   "Repository - RecipeRepository abstracts storage from business logic"
     *   "Strategy - ScalingStrategy allows different scaling algorithms"
     *
     * TODO: Add your patterns to this list.
     */
    private final List<String> patternsUsed = new ArrayList<>(List.of(
            // TODO: Add your patterns here, e.g.:
            // "PatternName - How you will apply it in your project"
    ));

    // ==================== Accessors ====================

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getMainEntities() {
        return List.copyOf(mainEntities);
    }

    public List<String> getCoreInterfaces() {
        return List.copyOf(coreInterfaces);
    }

    public List<String> getPatternsUsed() {
        return List.copyOf(patternsUsed);
    }

    // ==================== Formatted Output ====================

    /**
     * Returns a formatted version of your pitch, suitable for presentation notes.
     *
     * The output should include:
     *   - Project title
     *   - Description
     *   - List of entities
     *   - List of interfaces
     *   - List of patterns
     *
     * TODO: Implement this method to produce a readable summary of your pitch.
     *
     * Example output:
     *   === Personal Recipe Manager ===
     *   A recipe management application for home cooks who want to organize...
     *
     *   Entities:
     *     - Recipe - A cooking recipe with title, ingredients, and steps
     *     - Ingredient - A single ingredient with name, amount, and unit
     *
     *   Interfaces:
     *     - RecipeView - Displays recipes and collects user input
     *     - RecipeRepository - Stores and retrieves recipes
     *
     *   Patterns:
     *     - MVC - RecipeView handles display, RecipeController coordinates logic
     *     - Repository - RecipeRepository abstracts storage from business logic
     */
    public String getFormattedPitch() {
        // TODO: Implement this method
        // Hint: Use StringBuilder and iterate over the lists
        return ""; // Replace with your implementation
    }
}
