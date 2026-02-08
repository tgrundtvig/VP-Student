package dk.viprogram.week12;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Interface Specification for Your Exam Project
 *
 * This class captures the complete, detailed specification of every interface
 * and record in your project. Each method should have a full signature with
 * types and a JavaDoc-style description.
 *
 * Fill in each section with YOUR project's specific interfaces.
 * Run the tests to verify completeness.
 */
public class InterfaceSpecification {

    // ==================== View Interface ====================

    /**
     * The name of your View interface.
     * Example: "RecipeView"
     *
     * TODO: Fill in your View interface name.
     */
    public static final String VIEW_INTERFACE_NAME = ""; // TODO

    /**
     * Complete method signatures for your View interface.
     * Each entry should be a complete method signature with a description.
     *
     * Format: "returnType methodName(paramType paramName) - description"
     *
     * Examples:
     *   "void showRecipeList(List<Recipe> recipes) - displays all recipes in a browsable format"
     *   "Recipe promptForNewRecipe(List<Category> categories) - collects recipe data from user"
     *   "Optional<Recipe> promptSelectRecipe(List<Recipe> recipes) - user selects one recipe"
     *   "boolean promptConfirmDelete(Recipe recipe) - asks user to confirm deletion"
     *   "void showSuccess(String message) - displays a success notification"
     *   "void showError(String message) - displays an error notification"
     *
     * TODO: Add at least 3 method signatures for your View interface.
     */
    private final List<String> viewMethods = new ArrayList<>(List.of(
            // TODO: Add your View methods here
    ));

    /**
     * JavaDoc description for your View interface.
     * Describe what the interface represents and how implementations should behave.
     *
     * Example:
     *   "Handles all user interaction for the recipe management application. " +
     *   "Implementations may use any UI technology (console, JavaFX, mock). " +
     *   "Display methods show data to the user. Prompt methods collect input. " +
     *   "All implementations must handle empty lists gracefully."
     *
     * TODO: Write the JavaDoc for your View interface.
     */
    public static final String VIEW_JAVADOC = ""; // TODO

    // ==================== Repository Interface(s) ====================

    /**
     * The name of your primary Repository interface.
     * Example: "RecipeRepository"
     *
     * TODO: Fill in your Repository interface name.
     */
    public static final String REPOSITORY_INTERFACE_NAME = ""; // TODO

    /**
     * Complete method signatures for your Repository interface.
     * Include both standard CRUD methods and domain-specific queries.
     *
     * Standard CRUD examples:
     *   "void save(Recipe recipe) - persists recipe; updates if ID exists, creates if new"
     *   "Optional<Recipe> findById(String id) - retrieves recipe by ID, empty if not found"
     *   "List<Recipe> findAll() - retrieves all recipes, empty list if none"
     *   "void delete(String id) - removes recipe with given ID"
     *
     * Domain-specific examples:
     *   "List<Recipe> findByCategory(String categoryId) - recipes in a category"
     *   "List<Recipe> searchByTitle(String keyword) - recipes matching keyword"
     *   "List<Recipe> findFavorites() - all favorited recipes"
     *
     * TODO: Add at least 4 method signatures for your Repository interface.
     */
    private final List<String> repositoryMethods = new ArrayList<>(List.of(
            // TODO: Add your Repository methods here
    ));

    /**
     * JavaDoc description for your Repository interface.
     *
     * TODO: Write the JavaDoc for your Repository interface.
     */
    public static final String REPOSITORY_JAVADOC = ""; // TODO

    // ==================== Additional Interfaces ====================

    /**
     * Any additional interfaces (Strategy, Service, etc.) with their methods.
     * Map from interface name to list of method signatures.
     *
     * If you have no additional interfaces, leave this empty but explain why
     * in ADDITIONAL_INTERFACES_RATIONALE below.
     *
     * Example:
     *   "ScalingStrategy" -> ["Recipe scale(Recipe original, int newServings) - returns scaled recipe"]
     *
     * TODO: Add any additional interfaces, or leave empty.
     */
    private final Map<String, List<String>> additionalInterfaces = new LinkedHashMap<>() {{
        // TODO: Add additional interfaces if applicable
    }};

    /**
     * If you have additional interfaces, explain why they are needed.
     * If you don't, explain why not.
     *
     * TODO: Provide rationale.
     */
    public static final String ADDITIONAL_INTERFACES_RATIONALE = ""; // TODO

    // ==================== Record Specifications ====================

    /**
     * Complete record definitions for your project.
     * Map from record name to a list of field specifications.
     *
     * Each field specification should include: type, name, and description.
     *
     * Example:
     *   "Recipe" -> [
     *     "String id - unique identifier, generated by UUID",
     *     "String title - the recipe's display name, must not be blank",
     *     "String description - detailed preparation instructions",
     *     "int servings - number of servings, must be positive",
     *     "List<String> ingredientIds - IDs of required Ingredient records",
     *     "String categoryId - ID of the Category this belongs to",
     *     "boolean favorite - whether user marked as favorite"
     *   ]
     *
     * TODO: Add at least 2 complete record specifications.
     */
    private final Map<String, List<String>> recordSpecifications = new LinkedHashMap<>() {{
        // TODO: Add your records here
    }};

    // ==================== Workflow Descriptions ====================

    /**
     * Step-by-step workflow descriptions showing how your interfaces interact.
     * Describe at least 2 user operations, showing which methods are called.
     *
     * Example:
     *   "ADD NEW RECIPE:\n" +
     *   "1. User selects 'Add Recipe' from menu\n" +
     *   "2. Controller calls categoryRepo.findAll() to get available categories\n" +
     *   "3. Controller calls view.promptForNewRecipe(categories)\n" +
     *   "4. View collects title, description, servings, ingredients, category from user\n" +
     *   "5. View returns a new Recipe object\n" +
     *   "6. Controller calls recipeRepo.save(recipe)\n" +
     *   "7. Controller calls view.showSuccess('Recipe added successfully')\n" +
     *   "\n" +
     *   "SEARCH BY CATEGORY:\n" +
     *   "1. Controller calls categoryRepo.findAll() to get categories\n" +
     *   "2. Controller calls view.promptSelectCategory(categories)\n" +
     *   "3. View returns selected Category\n" +
     *   "4. Controller calls recipeRepo.findByCategory(category.id())\n" +
     *   "5. Controller calls view.showRecipeList(matchingRecipes)"
     *
     * TODO: Describe at least 2 workflows.
     */
    public static final String WORKFLOW_DESCRIPTIONS = ""; // TODO

    // ==================== Accessors ====================

    public String getViewInterfaceName() {
        return VIEW_INTERFACE_NAME;
    }

    public List<String> getViewMethods() {
        return List.copyOf(viewMethods);
    }

    public String getViewJavadoc() {
        return VIEW_JAVADOC;
    }

    public String getRepositoryInterfaceName() {
        return REPOSITORY_INTERFACE_NAME;
    }

    public List<String> getRepositoryMethods() {
        return List.copyOf(repositoryMethods);
    }

    public String getRepositoryJavadoc() {
        return REPOSITORY_JAVADOC;
    }

    public Map<String, List<String>> getAdditionalInterfaces() {
        return Map.copyOf(additionalInterfaces);
    }

    public String getAdditionalInterfacesRationale() {
        return ADDITIONAL_INTERFACES_RATIONALE;
    }

    public Map<String, List<String>> getRecordSpecifications() {
        return Map.copyOf(recordSpecifications);
    }

    public String getWorkflowDescriptions() {
        return WORKFLOW_DESCRIPTIONS;
    }
}
