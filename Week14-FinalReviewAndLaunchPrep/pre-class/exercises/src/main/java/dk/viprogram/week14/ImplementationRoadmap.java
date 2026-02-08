package dk.viprogram.week14;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation Roadmap for Your Exam Project
 *
 * This document captures your complete implementation plan for the 2-week
 * implementation period. It breaks the work into phases, tasks, and priorities
 * so you can start coding with confidence.
 *
 * Fill in each section and run the tests to verify your plan is complete.
 */
public class ImplementationRoadmap {

    // ==================== Implementation Phases ====================

    /**
     * The phases of your implementation, in order.
     * Each phase should describe what gets implemented and what tests should pass.
     *
     * You should have at least 4 phases.
     *
     * Example entries:
     *   "Phase 1: Model Layer - Implement all records (Recipe, Category, Ingredient) " +
     *       "and enums (Priority). After this phase, model tests pass."
     *   "Phase 2: Repository Layer - Implement Repository interfaces and " +
     *       "InMemoryRecipeRepository, InMemoryCategoryRepository. After this phase, " +
     *       "repository tests pass."
     *   "Phase 3: Controller + MockView - Implement MockRecipeView and RecipeController " +
     *       "with all user operations. After this phase, controller tests pass."
     *   "Phase 4: Console View - Implement ConsoleRecipeView with Scanner-based I/O. " +
     *       "After this phase, the application runs in the terminal."
     *   "Phase 5: Integration - Create composition root (RecipeApp.main), seed sample data, " +
     *       "wire everything together. After this phase, the application is demo-ready."
     *   "Phase 6: Polish - Add JavaFX view, file-based repository, edge case handling, " +
     *       "and code cleanup. This is stretch goal territory."
     *
     * TODO: Add your implementation phases.
     */
    private final List<String> phases = new ArrayList<>(List.of(
            // TODO: Add your phases here
    ));

    // ==================== Task Breakdown ====================

    /**
     * Specific tasks for each phase.
     * Map from phase name to list of concrete tasks.
     *
     * Each task should be specific enough to be a single work session (1-3 hours).
     *
     * Example:
     *   "Phase 1: Model Layer" -> [
     *     "Create Recipe record with id, title, description, servings, ingredientIds, " +
     *         "categoryId, favorite fields and create()/markAsFavorite() methods",
     *     "Create Category record with id, name, color fields and create() method",
     *     "Create Ingredient record with id, name, amount, unit fields and create() method",
     *     "Create Priority enum with LOW, MEDIUM, HIGH values",
     *     "Run model tests - all should pass"
     *   ]
     *
     *   "Phase 2: Repository Layer" -> [
     *     "Create RecipeRepository interface with save, findById, findAll, delete, " +
     *         "findByCategory, findFavorites methods",
     *     "Create CategoryRepository interface with save, findById, findAll, delete methods",
     *     "Implement InMemoryRecipeRepository with HashMap storage",
     *     "Implement InMemoryCategoryRepository with HashMap storage",
     *     "Run repository tests - all should pass"
     *   ]
     *
     * TODO: Add tasks for each phase.
     */
    private final Map<String, List<String>> taskBreakdown = new LinkedHashMap<>() {{
        // TODO: Add your task breakdown here
    }};

    // ==================== Priority Order ====================

    /**
     * All tasks ranked by priority.
     * Map from priority level to list of tasks.
     *
     * Priority levels:
     *   "P1 - Must Have" = Core MVP, required for demo
     *   "P2 - Should Have" = Important but app works without them
     *   "P3 - Nice to Have" = Stretch goals, time permitting
     *
     * Example:
     *   "P1 - Must Have" -> [
     *     "All records implemented",
     *     "InMemoryRepository for main entity",
     *     "Controller with add, list, and delete operations",
     *     "ConsoleView with basic I/O",
     *     "Application runs and can be demonstrated"
     *   ]
     *
     *   "P2 - Should Have" -> [
     *     "All CRUD operations working",
     *     "Search/filter functionality",
     *     "Sample data seeding",
     *     "All tests passing",
     *     "Clean code with JavaDoc"
     *   ]
     *
     *   "P3 - Nice to Have" -> [
     *     "JavaFX View",
     *     "File-based Repository",
     *     "Additional features beyond MVP"
     *   ]
     *
     * TODO: Prioritize your tasks.
     */
    private final Map<String, List<String>> prioritizedTasks = new LinkedHashMap<>() {{
        // TODO: Add your prioritized tasks here
    }};

    // ==================== Time Estimates ====================

    /**
     * Estimated time for each phase (in hours).
     *
     * Be realistic: implementation always takes longer than expected.
     * A good rule of thumb: estimate, then multiply by 1.5.
     *
     * You have approximately 14 available days. If you work 2 hours per day
     * on weekdays and 4 hours on weekends, that is about 24-28 hours total.
     *
     * Example:
     *   "Phase 1: Model Layer" -> 2
     *   "Phase 2: Repository Layer" -> 3
     *   "Phase 3: Controller + MockView" -> 5
     *   "Phase 4: Console View" -> 4
     *   "Phase 5: Integration" -> 3
     *   "Phase 6: Polish" -> 5
     *   "Buffer" -> 4
     *
     * TODO: Estimate hours for each phase.
     */
    private final Map<String, Integer> timeEstimates = new LinkedHashMap<>() {{
        // TODO: Add your time estimates here
    }};

    // ==================== Demo Plan ====================

    /**
     * What you will demonstrate at the exam.
     * List the features you will show and the order you will show them.
     *
     * Example:
     *   "1. Start the application (show it launches without errors)\n" +
     *   "2. Show the main menu (list all operations available)\n" +
     *   "3. Add a new recipe (demonstrate the full add workflow)\n" +
     *   "4. List all recipes (show the display format)\n" +
     *   "5. Search by category (demonstrate filtering)\n" +
     *   "6. Mark as favorite (demonstrate entity modification)\n" +
     *   "7. Delete a recipe (demonstrate removal with confirmation)\n" +
     *   "8. Show the test suite (run mvn test, show all green)\n" +
     *   "9. Show the code structure (highlight interface/implementation separation)"
     *
     * TODO: Plan your demo.
     */
    public static final String DEMO_PLAN = ""; // TODO

    // ==================== Risk Assessment ====================

    /**
     * Identify what could go wrong and how you will handle it.
     *
     * Example:
     *   "Risk: JavaFX setup is more complex than expected\n" +
     *   "Mitigation: Console view is P1, JavaFX is P3. If JavaFX fails, " +
     *   "the console version still works.\n\n" +
     *   "Risk: Controller logic is more complex than designed\n" +
     *   "Mitigation: Start with simplest operations, add complexity gradually.\n\n" +
     *   "Risk: Running out of time\n" +
     *   "Mitigation: P1 tasks first. Anything not P1 can be cut."
     *
     * TODO: Identify your risks and mitigations.
     */
    public static final String RISK_ASSESSMENT = ""; // TODO

    // ==================== Accessors ====================

    public List<String> getPhases() {
        return List.copyOf(phases);
    }

    public Map<String, List<String>> getTaskBreakdown() {
        return Map.copyOf(taskBreakdown);
    }

    public Map<String, List<String>> getPrioritizedTasks() {
        return Map.copyOf(prioritizedTasks);
    }

    public Map<String, Integer> getTimeEstimates() {
        return Map.copyOf(timeEstimates);
    }

    public String getDemoPlan() {
        return DEMO_PLAN;
    }

    public String getRiskAssessment() {
        return RISK_ASSESSMENT;
    }

    /**
     * Returns the total estimated hours across all phases.
     */
    public int getTotalEstimatedHours() {
        return timeEstimates.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
