package dk.viprogram.week11;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Architecture Document for Your Exam Project
 *
 * This document captures the complete architecture of your project:
 * all interfaces with their method signatures, all records with their fields,
 * and the layer structure showing how components connect.
 *
 * Fill in each section based on feedback from the architecture workshop.
 * Run the tests to verify your document is complete.
 */
public class ArchitectureDocument {

    // ==================== Project Identity ====================

    /**
     * Your project name.
     *
     * TODO: Fill in your project name.
     */
    private final String projectName = ""; // TODO: Fill in

    /**
     * A one-paragraph summary of the project's purpose and scope.
     *
     * TODO: Write a clear summary.
     */
    private final String projectSummary = ""; // TODO: Fill in

    // ==================== Interfaces ====================

    /**
     * All interfaces in your project, mapped from interface name to method signatures.
     *
     * Each entry should be:
     *   Key:   "InterfaceName"
     *   Value: Multi-line string listing all methods with parameter types and return types
     *
     * Example:
     *   "RecipeView" ->
     *       "void showRecipeList(List<Recipe> recipes)\n" +
     *       "void showRecipeDetail(Recipe recipe)\n" +
     *       "Recipe promptForNewRecipe(List<Category> categories)\n" +
     *       "Optional<Recipe> promptSelectRecipe(List<Recipe> recipes)\n" +
     *       "void showSuccess(String message)\n" +
     *       "void showError(String message)"
     *
     *   "RecipeRepository" ->
     *       "void save(Recipe recipe)\n" +
     *       "Optional<Recipe> findById(String id)\n" +
     *       "List<Recipe> findAll()\n" +
     *       "List<Recipe> findByCategory(String categoryId)\n" +
     *       "List<Recipe> findFavorites()\n" +
     *       "void delete(String id)"
     *
     * TODO: Add all your interfaces with complete method signatures.
     */
    private final Map<String, String> interfaces = new LinkedHashMap<>() {{
        // TODO: Add your interfaces here, e.g.:
        // put("YourViewInterface", "void showList(List<Entity> items)\n...");
        // put("YourRepositoryInterface", "void save(Entity entity)\n...");
    }};

    // ==================== Records ====================

    /**
     * All records (entities) in your project, mapped from record name to field definitions.
     *
     * Each entry should be:
     *   Key:   "RecordName"
     *   Value: Multi-line string listing all fields with types and descriptions
     *
     * Example:
     *   "Recipe" ->
     *       "String id - unique identifier\n" +
     *       "String title - recipe name\n" +
     *       "String description - detailed description\n" +
     *       "int servings - number of servings\n" +
     *       "List<String> ingredientIds - references to Ingredient records\n" +
     *       "String categoryId - reference to Category record\n" +
     *       "boolean favorite - whether marked as favorite"
     *
     * TODO: Add all your records with complete field definitions.
     */
    private final Map<String, String> records = new LinkedHashMap<>() {{
        // TODO: Add your records here, e.g.:
        // put("YourEntity", "String id - unique identifier\n...");
    }};

    // ==================== Architecture Layers ====================

    /**
     * Describes the architecture layers of your project and what belongs in each layer.
     *
     * Format as a multi-line string showing each layer and its components.
     *
     * Example:
     *   "VIEW LAYER:\n" +
     *   "  RecipeView (interface) -> ConsoleRecipeView, JavaFXRecipeView, MockRecipeView\n" +
     *   "\n" +
     *   "CONTROLLER LAYER:\n" +
     *   "  RecipeController - coordinates View and Repository\n" +
     *   "\n" +
     *   "MODEL LAYER:\n" +
     *   "  Recipe (record), Ingredient (record), Category (record)\n" +
     *   "\n" +
     *   "REPOSITORY LAYER:\n" +
     *   "  RecipeRepository (interface) -> InMemoryRecipeRepository\n" +
     *   "  CategoryRepository (interface) -> InMemoryCategoryRepository"
     *
     * TODO: Define your architecture layers.
     */
    private final String architectureLayers = ""; // TODO: Fill in

    // ==================== Accessors ====================

    public String getProjectName() {
        return projectName;
    }

    public String getProjectSummary() {
        return projectSummary;
    }

    public Map<String, String> getInterfaces() {
        return Map.copyOf(interfaces);
    }

    public Map<String, String> getRecords() {
        return Map.copyOf(records);
    }

    public String getArchitectureLayers() {
        return architectureLayers;
    }

    // ==================== Formatted Output ====================

    /**
     * Returns a complete, formatted architecture document.
     *
     * The output should include all sections in a readable format:
     *   - Project name and summary
     *   - All interfaces with method signatures
     *   - All records with field definitions
     *   - Architecture layer diagram
     *
     * TODO: Implement this method to produce a comprehensive document.
     *
     * Hint: Use StringBuilder and iterate over the maps.
     */
    public String getFormattedDocument() {
        // TODO: Implement this method
        return ""; // Replace with your implementation
    }
}
