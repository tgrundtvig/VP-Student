package dk.viprogram.week14;

/**
 * Project Launch Checklist
 *
 * This class documents the state of your actual exam project setup.
 * Fill in each section to confirm your project is ready for the 2-week
 * implementation period.
 *
 * This is the final design exercise. After completing this, you are in
 * implementation mode.
 */
public class ProjectLaunchChecklist {

    // ==================== Project Setup ====================

    /**
     * The name of your exam project (Maven artifactId).
     * Example: "recipe-manager"
     *
     * TODO: Fill in your project name.
     */
    public static final String PROJECT_NAME = ""; // TODO

    /**
     * The base package for your project.
     * Example: "dk.viprogram.recipemanager"
     *
     * TODO: Fill in your base package.
     */
    public static final String BASE_PACKAGE = ""; // TODO

    // ==================== Files Created ====================

    /**
     * List all Java interface files you have created in your exam project.
     * Include the full class name (package + class).
     *
     * Example:
     *   "dk.viprogram.recipemanager.view.RecipeView\n" +
     *   "dk.viprogram.recipemanager.repository.RecipeRepository\n" +
     *   "dk.viprogram.recipemanager.repository.CategoryRepository"
     *
     * TODO: List your interface files.
     */
    public static final String INTERFACE_FILES = ""; // TODO

    /**
     * List all Java record files you have created in your exam project.
     *
     * Example:
     *   "dk.viprogram.recipemanager.model.Recipe\n" +
     *   "dk.viprogram.recipemanager.model.Category\n" +
     *   "dk.viprogram.recipemanager.model.Ingredient"
     *
     * TODO: List your record files.
     */
    public static final String RECORD_FILES = ""; // TODO

    /**
     * List all test double files (mocks, in-memory implementations).
     *
     * Example:
     *   "dk.viprogram.recipemanager.view.mock.MockRecipeView\n" +
     *   "dk.viprogram.recipemanager.repository.inmemory.InMemoryRecipeRepository\n" +
     *   "dk.viprogram.recipemanager.repository.inmemory.InMemoryCategoryRepository"
     *
     * TODO: List your test double files.
     */
    public static final String TEST_DOUBLE_FILES = ""; // TODO

    /**
     * List all test files you have created in your exam project.
     *
     * Example:
     *   "dk.viprogram.recipemanager.model.RecipeTest\n" +
     *   "dk.viprogram.recipemanager.repository.InMemoryRecipeRepositoryTest\n" +
     *   "dk.viprogram.recipemanager.controller.RecipeControllerTest"
     *
     * TODO: List your test files.
     */
    public static final String TEST_FILES = ""; // TODO

    // ==================== Compilation Status ====================

    /**
     * Does your exam project compile with `mvn clean compile`?
     * If not, what errors remain?
     *
     * Expected: "Yes, project compiles successfully"
     * Or: "Compiles with warnings about unused imports. No errors."
     * Or: "Controller tests do not compile yet because Controller class is not implemented"
     *
     * TODO: Report your compilation status.
     */
    public static final String COMPILATION_STATUS = ""; // TODO

    // ==================== Git Status ====================

    /**
     * Have you initialized a git repository for your exam project?
     * What was your initial commit message?
     *
     * Example: "Yes, initialized with commit: 'Initial project setup with interfaces, " +
     *          "records, tests, and mocks'"
     *
     * TODO: Report your git status.
     */
    public static final String GIT_STATUS = ""; // TODO

    // ==================== First Implementation Task ====================

    /**
     * What is the very first thing you will implement?
     * This should be the first task from Phase 1 of your roadmap.
     *
     * Example: "Implement the Recipe record with all fields, the create() factory method, " +
     *          "the markAsFavorite() method, and a custom toString(). " +
     *          "Then run RecipeTest to verify."
     *
     * TODO: Describe your first implementation task.
     */
    public static final String FIRST_TASK = ""; // TODO

    // ==================== Confidence Check ====================

    /**
     * On a scale of 1-10, how confident are you that you can complete your MVP
     * in the 2-week implementation period? Explain briefly.
     *
     * Example: "8/10 - My design is solid and I have clear tests to guide me. " +
     *          "The only uncertainty is the JavaFX view, which is P3 anyway."
     *
     * TODO: Rate your confidence and explain.
     */
    public static final String CONFIDENCE_LEVEL = ""; // TODO

    // ==================== Accessors ====================

    public String getProjectName() {
        return PROJECT_NAME;
    }

    public String getBasePackage() {
        return BASE_PACKAGE;
    }

    public String getInterfaceFiles() {
        return INTERFACE_FILES;
    }

    public String getRecordFiles() {
        return RECORD_FILES;
    }

    public String getTestDoubleFiles() {
        return TEST_DOUBLE_FILES;
    }

    public String getTestFiles() {
        return TEST_FILES;
    }

    public String getCompilationStatus() {
        return COMPILATION_STATUS;
    }

    public String getGitStatus() {
        return GIT_STATUS;
    }

    public String getFirstTask() {
        return FIRST_TASK;
    }

    public String getConfidenceLevel() {
        return CONFIDENCE_LEVEL;
    }
}
