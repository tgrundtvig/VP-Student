package dk.viprogram.week13;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Test Plan Document for Your Exam Project
 *
 * This document captures your complete testing strategy: what to test,
 * how to test it, and what test doubles (mocks, in-memory implementations)
 * you need to create.
 *
 * Fill in each section based on your finalized interface design from Week 12.
 * Run the tests to verify your plan is complete.
 */
public class TestPlanDocument {

    // ==================== Test Categories ====================

    /**
     * The categories of tests you plan to write.
     * Each category should describe a test grouping and its purpose.
     *
     * You should have at least 3 categories.
     *
     * Example entries:
     *   "Model Tests - verify record creation, factory methods, modification " +
     *       "methods, and toString for Recipe, Ingredient, and Category"
     *   "Repository Tests - verify CRUD operations and domain queries using " +
     *       "InMemoryRecipeRepository and InMemoryCategoryRepository"
     *   "Controller Tests - verify that RecipeController correctly coordinates " +
     *       "MockRecipeView and InMemoryRepositories for all user operations"
     *
     * TODO: Add at least 3 test categories.
     */
    private final List<String> testCategories = new ArrayList<>(List.of(
            // TODO: Add your test categories here
    ));

    // ==================== Test Cases Per Interface ====================

    /**
     * Specific test cases grouped by interface or component.
     * Map from component name to list of test case descriptions.
     *
     * Each test case should describe:
     *   - What is being tested (scenario)
     *   - What the expected result is
     *
     * Example:
     *   "RecipeRepository" -> [
     *     "save new recipe then findById returns it",
     *     "save existing recipe (same ID) then findById returns updated version",
     *     "findById with non-existent ID returns Optional.empty()",
     *     "findAll on empty repository returns empty list",
     *     "findAll returns all saved recipes",
     *     "findByCategory returns only recipes in that category",
     *     "findByCategory with no matches returns empty list",
     *     "delete removes the recipe, findById returns empty",
     *     "delete non-existent ID does not throw"
     *   ]
     *
     *   "RecipeController" -> [
     *     "addRecipe: valid recipe is saved to repository and success shown",
     *     "addRecipe: user cancels (null return) and repository is unchanged",
     *     "listRecipes: all recipes from repository shown via view",
     *     "listRecipes: empty repository shows empty list",
     *     "deleteRecipe: user confirms, recipe removed from repository",
     *     "deleteRecipe: user declines, recipe remains in repository",
     *     "searchByCategory: filtered results shown via view"
     *   ]
     *
     * TODO: Add test cases for each component.
     */
    private final Map<String, List<String>> testCasesPerComponent = new LinkedHashMap<>() {{
        // TODO: Add your test cases here
    }};

    // ==================== Mock Strategy ====================

    /**
     * Describes your approach to creating test doubles.
     *
     * For each interface that needs a test double, describe:
     *   - The mock/in-memory class name
     *   - What internal state it maintains (queues, lists, maps)
     *   - How tests will program it (queue methods)
     *   - How tests will verify it (getter methods)
     *
     * Example:
     *   "MockRecipeView:\n" +
     *   "  Internal: Queue<Recipe> recipesToReturn, Queue<Boolean> confirmations,\n" +
     *   "    List<String> successMessages, List<String> errorMessages,\n" +
     *   "    List<List<Recipe>> displayedLists\n" +
     *   "  Programming: queueRecipeInput(Recipe), queueConfirmation(boolean)\n" +
     *   "  Verification: getSuccessMessages(), getErrorMessages(),\n" +
     *   "    getDisplayedLists()\n\n" +
     *   "InMemoryRecipeRepository:\n" +
     *   "  Internal: Map<String, Recipe> storage = new HashMap<>()\n" +
     *   "  All interface methods operate on the HashMap\n" +
     *   "  findByCategory uses stream().filter()"
     *
     * TODO: Describe your mock strategy.
     */
    public static final String MOCK_STRATEGY = ""; // TODO

    // ==================== Test Infrastructure ====================

    /**
     * Describes how your test classes will be organized.
     *
     * List the test class names and what each tests, plus the
     * common @BeforeEach setup they will use.
     *
     * Example:
     *   "Test classes:\n" +
     *   "  RecipeTest - model tests for Recipe record\n" +
     *   "  CategoryTest - model tests for Category record\n" +
     *   "  InMemoryRecipeRepositoryTest - repository CRUD and queries\n" +
     *   "  RecipeControllerTest - integration tests with mocks\n\n" +
     *   "Common setup (RecipeControllerTest @BeforeEach):\n" +
     *   "  mockView = new MockRecipeView()\n" +
     *   "  recipeRepo = new InMemoryRecipeRepository()\n" +
     *   "  categoryRepo = new InMemoryCategoryRepository()\n" +
     *   "  controller = new RecipeController(mockView, recipeRepo, categoryRepo)"
     *
     * TODO: Describe your test infrastructure.
     */
    public static final String TEST_INFRASTRUCTURE = ""; // TODO

    // ==================== Estimated Test Count ====================

    /**
     * How many tests do you expect to write per category?
     * Map from category name to estimated count.
     *
     * Guideline:
     *   - Model tests: 3-5
     *   - Repository tests: 5-8
     *   - Controller tests: 5-10
     *   - Total: at least 13
     *
     * TODO: Estimate your test counts.
     */
    private final Map<String, Integer> estimatedTestCounts = new LinkedHashMap<>() {{
        // TODO: Add your estimates here, e.g.:
        // put("Model Tests", 5);
        // put("Repository Tests", 7);
        // put("Controller Tests", 8);
    }};

    // ==================== Accessors ====================

    public List<String> getTestCategories() {
        return List.copyOf(testCategories);
    }

    public Map<String, List<String>> getTestCasesPerComponent() {
        return Map.copyOf(testCasesPerComponent);
    }

    public String getMockStrategy() {
        return MOCK_STRATEGY;
    }

    public String getTestInfrastructure() {
        return TEST_INFRASTRUCTURE;
    }

    public Map<String, Integer> getEstimatedTestCounts() {
        return Map.copyOf(estimatedTestCounts);
    }

    /**
     * Returns the total estimated number of tests across all categories.
     */
    public int getTotalEstimatedTests() {
        return estimatedTestCounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
