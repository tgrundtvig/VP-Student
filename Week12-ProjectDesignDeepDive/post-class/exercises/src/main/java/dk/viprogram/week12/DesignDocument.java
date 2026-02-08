package dk.viprogram.week12;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Complete Design Document for Your Exam Project
 *
 * This document captures the finalized interface design including complete JavaDoc,
 * workflow pseudo-code, mock implementation sketches, and edge case documentation.
 *
 * After completing this document, your design should be precise enough that someone
 * could write tests against your interfaces without any other information.
 *
 * Fill in each section and run the tests to verify completeness.
 */
public class DesignDocument {

    // ==================== Complete JavaDoc ====================

    /**
     * Complete JavaDoc for ALL interfaces in your project.
     * Map from interface name to its full JavaDoc text.
     *
     * Each JavaDoc entry should include:
     *   - Interface-level description (what the interface represents)
     *   - For each method:
     *     - Method signature
     *     - What the method does (contract, not implementation)
     *     - @param descriptions for each parameter
     *     - @return description (if applicable)
     *     - Edge case behavior (empty lists, null values, missing items)
     *
     * Example:
     *   "RecipeView" ->
     *       "Interface JavaDoc:\n" +
     *       "Handles all user interaction for recipe management.\n" +
     *       "Implementations may use console, JavaFX, or mock.\n\n" +
     *       "Method: void showRecipeList(List<Recipe> recipes)\n" +
     *       "Displays a list of recipes for browsing.\n" +
     *       "@param recipes - the recipes to display; never null, may be empty\n" +
     *       "Edge case: if empty, shows 'No recipes found' message\n\n" +
     *       "Method: Recipe promptForNewRecipe(List<Category> categories)\n" +
     *       "Collects all recipe data from the user.\n" +
     *       "@param categories - available categories for selection; never null\n" +
     *       "@return a new Recipe with all fields, or null if user cancels\n" +
     *       "Edge case: if categories is empty, shows error message"
     *
     * TODO: Add complete JavaDoc for all your interfaces.
     */
    private final Map<String, String> interfaceJavaDocs = new LinkedHashMap<>() {{
        // TODO: Add your interface JavaDocs here
    }};

    // ==================== Workflow Pseudo-Code ====================

    /**
     * Detailed workflow pseudo-code for each major user operation.
     * Each workflow should show the exact sequence of method calls
     * between Controller, View, and Repository.
     *
     * Format each workflow as:
     *   "OPERATION NAME:\n" +
     *   "1. [Layer] method call or action\n" +
     *   "2. [Layer] method call or action\n" +
     *   "..."
     *
     * Example:
     *   "ADD NEW RECIPE" ->
     *       "1. [Controller] calls categoryRepo.findAll()\n" +
     *       "2. [Controller] calls view.promptForNewRecipe(categories)\n" +
     *       "3. [View] displays input form, collects data, returns Recipe\n" +
     *       "4. [Controller] validates recipe (title not blank, servings > 0)\n" +
     *       "5. [Controller] calls recipeRepo.save(recipe)\n" +
     *       "6. [Controller] calls view.showSuccess('Recipe added')\n" +
     *       "ERROR PATH:\n" +
     *       "4a. [Controller] if validation fails, calls view.showError(message)\n" +
     *       "4b. Returns to step 2"
     *
     * TODO: Add at least 3 workflow descriptions.
     */
    private final Map<String, String> workflows = new LinkedHashMap<>() {{
        // TODO: Add your workflows here
    }};

    // ==================== Mock Implementation Sketches ====================

    /**
     * Describes how you would implement a mock for each interface.
     * This proves your interfaces are testable.
     *
     * For each interface, describe:
     *   - What queues/lists the mock needs internally
     *   - How "program" methods would work (queue responses)
     *   - How "verify" methods would work (check what was called)
     *
     * Example:
     *   "MockRecipeView" ->
     *       "Internal state:\n" +
     *       "  Queue<Recipe> queuedRecipes - for promptForNewRecipe responses\n" +
     *       "  Queue<Boolean> queuedConfirmations - for confirm prompts\n" +
     *       "  List<String> successMessages - records showSuccess calls\n" +
     *       "  List<String> errorMessages - records showError calls\n" +
     *       "  List<List<Recipe>> displayedLists - records showRecipeList calls\n\n" +
     *       "Programming methods:\n" +
     *       "  queueRecipe(Recipe r) - adds to queuedRecipes\n" +
     *       "  queueConfirmation(boolean c) - adds to queuedConfirmations\n\n" +
     *       "Verification methods:\n" +
     *       "  getSuccessMessages() - returns recorded success messages\n" +
     *       "  getLastDisplayedList() - returns last list shown"
     *
     * TODO: Add mock sketches for your interfaces.
     */
    private final Map<String, String> mockSketches = new LinkedHashMap<>() {{
        // TODO: Add your mock sketches here
    }};

    // ==================== Edge Case Documentation ====================

    /**
     * Documents edge cases for critical interface methods.
     * List the important edge cases and how your design handles them.
     *
     * Example:
     *   "Repository.findById with non-existent ID:\n" +
     *   "  Returns Optional.empty(). Controller should check and call\n" +
     *   "  view.showError('Item not found') if empty.\n\n" +
     *   "View.promptForNewRecipe with empty category list:\n" +
     *   "  View should show error that no categories exist.\n" +
     *   "  Controller should check for categories before calling.\n\n" +
     *   "Repository.delete with non-existent ID:\n" +
     *   "  Silently succeeds (no error). Idempotent operation.\n\n" +
     *   "View.showRecipeList with empty list:\n" +
     *   "  Shows 'No recipes found' message instead of empty display."
     *
     * TODO: Document edge cases for your project's interfaces.
     */
    public static final String EDGE_CASE_DOCUMENTATION = ""; // TODO

    // ==================== Accessors ====================

    public Map<String, String> getInterfaceJavaDocs() {
        return Map.copyOf(interfaceJavaDocs);
    }

    public Map<String, String> getWorkflows() {
        return Map.copyOf(workflows);
    }

    public Map<String, String> getMockSketches() {
        return Map.copyOf(mockSketches);
    }

    public String getEdgeCaseDocumentation() {
        return EDGE_CASE_DOCUMENTATION;
    }
}
