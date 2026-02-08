package dk.viprogram.week13;

/**
 * Test Suite Report for Your Exam Project
 *
 * This class captures the actual test code you have written for your project.
 * Each section contains test code as strings that you will later transfer to
 * real Java test files in your exam project.
 *
 * Also includes mock and in-memory implementation code, and reflections on
 * what the testing process revealed about your design.
 *
 * Fill in each section and run the tests to verify completeness.
 */
public class TestSuiteReport {

    // ==================== Model Tests ====================

    /**
     * JUnit test code for your model (records).
     * Write at least 2 complete test methods.
     *
     * Example:
     *   "@Test\n" +
     *   "@DisplayName(\"Recipe.create generates a unique ID\")\n" +
     *   "void createGeneratesUniqueId() {\n" +
     *   "    Recipe r1 = Recipe.create(\"Cake\", \"Delicious\", 4, List.of(), \"c1\");\n" +
     *   "    Recipe r2 = Recipe.create(\"Soup\", \"Warm\", 2, List.of(), \"c2\");\n" +
     *   "    assertNotEquals(r1.id(), r2.id());\n" +
     *   "}\n\n" +
     *   "@Test\n" +
     *   "@DisplayName(\"markAsFavorite returns new instance with favorite=true\")\n" +
     *   "void markAsFavoriteReturnsNewInstance() {\n" +
     *   "    Recipe original = Recipe.create(\"Cake\", \"\", 4, List.of(), \"c1\");\n" +
     *   "    Recipe favorite = original.markAsFavorite();\n" +
     *   "    assertFalse(original.favorite());\n" +
     *   "    assertTrue(favorite.favorite());\n" +
     *   "    assertEquals(original.id(), favorite.id());\n" +
     *   "}"
     *
     * TODO: Write your model test code.
     */
    public static final String MODEL_TEST_CODE = ""; // TODO

    // ==================== Repository Tests ====================

    /**
     * JUnit test code for your InMemoryRepository.
     * Write at least 3 complete test methods.
     *
     * Example:
     *   "@Test\n" +
     *   "@DisplayName(\"Saved recipe can be found by ID\")\n" +
     *   "void savedRecipeCanBeFoundById() {\n" +
     *   "    Recipe recipe = Recipe.create(\"Cake\", \"\", 4, List.of(), \"c1\");\n" +
     *   "    repo.save(recipe);\n" +
     *   "    Optional<Recipe> found = repo.findById(recipe.id());\n" +
     *   "    assertTrue(found.isPresent());\n" +
     *   "    assertEquals(\"Cake\", found.get().title());\n" +
     *   "}\n\n" +
     *   "@Test\n" +
     *   "@DisplayName(\"findById returns empty for non-existent ID\")\n" +
     *   "void findByIdReturnsEmptyForMissingId() {\n" +
     *   "    assertTrue(repo.findById(\"nope\").isEmpty());\n" +
     *   "}\n\n" +
     *   "@Test\n" +
     *   "@DisplayName(\"findByCategory returns only matching recipes\")\n" +
     *   "void findByCategoryFilters() {\n" +
     *   "    repo.save(Recipe.create(\"Cake\", \"\", 4, List.of(), \"dessert\"));\n" +
     *   "    repo.save(Recipe.create(\"Soup\", \"\", 2, List.of(), \"main\"));\n" +
     *   "    assertEquals(1, repo.findByCategory(\"dessert\").size());\n" +
     *   "}"
     *
     * TODO: Write your repository test code.
     */
    public static final String REPOSITORY_TEST_CODE = ""; // TODO

    // ==================== Controller Tests ====================

    /**
     * JUnit test code for your Controller (using MockView + InMemoryRepo).
     * Write at least 3 complete test methods.
     *
     * Example:
     *   "@BeforeEach\n" +
     *   "void setUp() {\n" +
     *   "    mockView = new MockRecipeView();\n" +
     *   "    recipeRepo = new InMemoryRecipeRepository();\n" +
     *   "    categoryRepo = new InMemoryCategoryRepository();\n" +
     *   "    controller = new RecipeController(mockView, recipeRepo, categoryRepo);\n" +
     *   "}\n\n" +
     *   "@Test\n" +
     *   "@DisplayName(\"addRecipe saves to repository and shows success\")\n" +
     *   "void addRecipeSavesAndConfirms() {\n" +
     *   "    Recipe recipe = Recipe.create(\"Cake\", \"\", 4, List.of(), \"c1\");\n" +
     *   "    mockView.queueRecipeInput(recipe);\n" +
     *   "    controller.addRecipe();\n" +
     *   "    assertTrue(recipeRepo.findById(recipe.id()).isPresent());\n" +
     *   "    assertEquals(1, mockView.getSuccessMessages().size());\n" +
     *   "}\n\n" +
     *   "@Test\n" +
     *   "@DisplayName(\"addRecipe cancelled does not save\")\n" +
     *   "void addRecipeCancelledDoesNotSave() {\n" +
     *   "    mockView.queueRecipeInput(null);  // User cancels\n" +
     *   "    controller.addRecipe();\n" +
     *   "    assertTrue(recipeRepo.findAll().isEmpty());\n" +
     *   "}"
     *
     * TODO: Write your controller test code.
     */
    public static final String CONTROLLER_TEST_CODE = ""; // TODO

    // ==================== Mock View Implementation ====================

    /**
     * The complete code for your MockView class.
     *
     * Should include:
     *   - Class declaration implementing your View interface
     *   - Internal queues for prompt return values
     *   - Internal lists for recording display calls
     *   - Programming methods (queueXxx)
     *   - Verification methods (getXxxMessages, getDisplayedLists)
     *   - Implementation of all interface methods
     *
     * TODO: Write your MockView implementation code.
     */
    public static final String MOCK_VIEW_CODE = ""; // TODO

    // ==================== InMemory Repository Implementation ====================

    /**
     * The complete code for your InMemoryRepository class.
     *
     * Should include:
     *   - Class declaration implementing your Repository interface
     *   - Map<String, Entity> storage field
     *   - Implementation of all CRUD methods
     *   - Implementation of all domain query methods
     *
     * TODO: Write your InMemoryRepository implementation code.
     */
    public static final String IN_MEMORY_REPO_CODE = ""; // TODO

    // ==================== Design Changes ====================

    /**
     * Reflection: did writing tests reveal any problems with your interface design?
     *
     * Common discoveries:
     *   - "I needed an extra View method for selecting items from a list"
     *   - "My Repository needed a count() method for verification"
     *   - "The Controller needed the Repository to return a value from save()"
     *   - "I realized I needed to pass categories to the View for adding items"
     *
     * If no changes were needed, explain why your design was already complete.
     *
     * TODO: Document any design changes or confirm no changes needed.
     */
    public static final String DESIGN_CHANGES = ""; // TODO

    // ==================== Accessors ====================

    public String getModelTestCode() {
        return MODEL_TEST_CODE;
    }

    public String getRepositoryTestCode() {
        return REPOSITORY_TEST_CODE;
    }

    public String getControllerTestCode() {
        return CONTROLLER_TEST_CODE;
    }

    public String getMockViewCode() {
        return MOCK_VIEW_CODE;
    }

    public String getInMemoryRepoCode() {
        return IN_MEMORY_REPO_CODE;
    }

    public String getDesignChanges() {
        return DESIGN_CHANGES;
    }
}
