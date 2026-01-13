package dk.viprogram.week10;

/**
 * Architecture Analysis Exercise
 *
 * Fill in the string constants below with your analysis of the example projects.
 * Your answers should be thoughtful and specific - reference actual class names
 * and explain WHY things are designed the way they are.
 *
 * Run the tests to verify your answers are complete.
 */
public class ArchitectureAnalysis {

    // ==================== Task Manager Analysis ====================

    /**
     * List all the interfaces in the Task Manager project.
     * Format: "Interface1, Interface2, Interface3"
     *
     * Hint: Look in repository/, view/, and model/ packages.
     */
    public static final String TASK_MANAGER_INTERFACES =
            ""; // TODO: Fill in your answer

    /**
     * Explain the purpose of MockTaskView.
     * Why does it exist? How is it used?
     */
    public static final String MOCK_VIEW_PURPOSE =
            ""; // TODO: Fill in your answer

    /**
     * Trace the data flow when a user completes a task.
     * List the steps from user input to confirmation message.
     * Format: "1. User... 2. View... 3. Controller... etc."
     */
    public static final String COMPLETE_TASK_DATA_FLOW =
            ""; // TODO: Fill in your answer

    /**
     * Why is Task implemented as a record instead of a class?
     * Give at least two benefits.
     */
    public static final String WHY_TASK_IS_RECORD =
            ""; // TODO: Fill in your answer

    /**
     * What would you change in TaskManagerApp.java to use file-based storage
     * instead of in-memory storage? Be specific about which lines.
     */
    public static final String HOW_TO_SWAP_STORAGE =
            ""; // TODO: Fill in your answer

    // ==================== Quiz App Analysis ====================

    /**
     * How does the Quiz App use polymorphism?
     * Explain which interface enables it and what implementations exist.
     */
    public static final String QUIZ_POLYMORPHISM =
            ""; // TODO: Fill in your answer

    /**
     * Explain the Strategy pattern as used in the Quiz App.
     * What is the strategy interface? What strategies exist?
     */
    public static final String SCORING_STRATEGY_EXPLANATION =
            ""; // TODO: Fill in your answer

    /**
     * Why does QuizResult store quizId (a String) instead of a Quiz object?
     * What are the benefits of this design?
     */
    public static final String WHY_QUIZ_ID_NOT_QUIZ_OBJECT =
            ""; // TODO: Fill in your answer

    /**
     * How could you add a new question type (e.g., FillInBlankQuestion)?
     * What would you need to create/modify?
     */
    public static final String HOW_TO_ADD_QUESTION_TYPE =
            ""; // TODO: Fill in your answer

    // ==================== Comparison ====================

    /**
     * List three design patterns that appear in BOTH projects.
     * Format: "Pattern1, Pattern2, Pattern3"
     */
    public static final String COMMON_PATTERNS =
            ""; // TODO: Fill in your answer

    /**
     * Both projects have a Mock view for testing.
     * What methods do both mocks have in common conceptually?
     */
    public static final String COMMON_MOCK_FEATURES =
            ""; // TODO: Fill in your answer

    /**
     * What is the main structural difference between how Task Manager
     * and Quiz App handle their domain models?
     * Hint: Think about Question vs Task.
     */
    public static final String MODEL_STRUCTURE_DIFFERENCE =
            ""; // TODO: Fill in your answer

    // ==================== Your Project Planning ====================

    /**
     * Based on what you learned from the examples, list 2-3 interfaces
     * your exam project might need.
     * Format: "Interface1 (purpose), Interface2 (purpose)"
     */
    public static final String YOUR_PROJECT_INTERFACES =
            ""; // TODO: Fill in your answer

    /**
     * What entity/entities will your project manage?
     * Briefly describe the main data your application will work with.
     */
    public static final String YOUR_PROJECT_ENTITIES =
            ""; // TODO: Fill in your answer
}
