package dk.viprogram.week10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to verify the architecture analysis is complete.
 *
 * These tests check that:
 * 1. All answers are filled in (not empty)
 * 2. Answers have sufficient detail (minimum length)
 * 3. Some specific technical understanding is demonstrated
 */
class ArchitectureAnalysisTest {

    // ==================== Task Manager Analysis ====================

    @Test
    @DisplayName("Task Manager interfaces are listed")
    void taskManagerInterfacesListed() {
        assertNotBlank(ArchitectureAnalysis.TASK_MANAGER_INTERFACES,
                "TASK_MANAGER_INTERFACES");
        // Should list at least 3 interfaces
        String answer = ArchitectureAnalysis.TASK_MANAGER_INTERFACES.toLowerCase();
        assertTrue(answer.contains("repository") || answer.contains("taskrepository"),
                "Should mention Repository interface");
        assertTrue(answer.contains("view") || answer.contains("taskview"),
                "Should mention View interface");
    }

    @Test
    @DisplayName("Mock view purpose is explained")
    void mockViewPurposeExplained() {
        assertNotBlank(ArchitectureAnalysis.MOCK_VIEW_PURPOSE,
                "MOCK_VIEW_PURPOSE");
        assertMinLength(ArchitectureAnalysis.MOCK_VIEW_PURPOSE, 50,
                "MOCK_VIEW_PURPOSE should be at least 50 characters");
        // Should mention testing
        assertTrue(
                ArchitectureAnalysis.MOCK_VIEW_PURPOSE.toLowerCase().contains("test"),
                "Should explain that MockView is for testing");
    }

    @Test
    @DisplayName("Complete task data flow is traced")
    void completeTaskDataFlowTraced() {
        assertNotBlank(ArchitectureAnalysis.COMPLETE_TASK_DATA_FLOW,
                "COMPLETE_TASK_DATA_FLOW");
        assertMinLength(ArchitectureAnalysis.COMPLETE_TASK_DATA_FLOW, 100,
                "COMPLETE_TASK_DATA_FLOW should trace multiple steps");
    }

    @Test
    @DisplayName("Record benefits are explained")
    void recordBenefitsExplained() {
        assertNotBlank(ArchitectureAnalysis.WHY_TASK_IS_RECORD,
                "WHY_TASK_IS_RECORD");
        assertMinLength(ArchitectureAnalysis.WHY_TASK_IS_RECORD, 50,
                "WHY_TASK_IS_RECORD should give at least two benefits");
    }

    @Test
    @DisplayName("Storage swap is explained")
    void storageSwapExplained() {
        assertNotBlank(ArchitectureAnalysis.HOW_TO_SWAP_STORAGE,
                "HOW_TO_SWAP_STORAGE");
        // Should mention what class/file to change
        String answer = ArchitectureAnalysis.HOW_TO_SWAP_STORAGE.toLowerCase();
        assertTrue(
                answer.contains("taskmanagerapp") || answer.contains("main") ||
                answer.contains("composition root") || answer.contains("entry point"),
                "Should identify where to make the change");
    }

    // ==================== Quiz App Analysis ====================

    @Test
    @DisplayName("Quiz polymorphism is explained")
    void quizPolymorphismExplained() {
        assertNotBlank(ArchitectureAnalysis.QUIZ_POLYMORPHISM,
                "QUIZ_POLYMORPHISM");
        String answer = ArchitectureAnalysis.QUIZ_POLYMORPHISM.toLowerCase();
        assertTrue(
                answer.contains("question") || answer.contains("interface"),
                "Should mention the Question interface");
    }

    @Test
    @DisplayName("Scoring strategy is explained")
    void scoringStrategyExplained() {
        assertNotBlank(ArchitectureAnalysis.SCORING_STRATEGY_EXPLANATION,
                "SCORING_STRATEGY_EXPLANATION");
        String answer = ArchitectureAnalysis.SCORING_STRATEGY_EXPLANATION.toLowerCase();
        assertTrue(
                answer.contains("strategy") || answer.contains("scoring"),
                "Should mention strategy or scoring");
    }

    @Test
    @DisplayName("Quiz ID design is explained")
    void quizIdDesignExplained() {
        assertNotBlank(ArchitectureAnalysis.WHY_QUIZ_ID_NOT_QUIZ_OBJECT,
                "WHY_QUIZ_ID_NOT_QUIZ_OBJECT");
        assertMinLength(ArchitectureAnalysis.WHY_QUIZ_ID_NOT_QUIZ_OBJECT, 30,
                "WHY_QUIZ_ID_NOT_QUIZ_OBJECT should explain the benefit");
    }

    @Test
    @DisplayName("Adding question type is explained")
    void addingQuestionTypeExplained() {
        assertNotBlank(ArchitectureAnalysis.HOW_TO_ADD_QUESTION_TYPE,
                "HOW_TO_ADD_QUESTION_TYPE");
        String answer = ArchitectureAnalysis.HOW_TO_ADD_QUESTION_TYPE.toLowerCase();
        assertTrue(
                answer.contains("implement") || answer.contains("question") ||
                answer.contains("interface") || answer.contains("record"),
                "Should explain implementing the Question interface");
    }

    // ==================== Comparison ====================

    @Test
    @DisplayName("Common patterns are identified")
    void commonPatternsIdentified() {
        assertNotBlank(ArchitectureAnalysis.COMMON_PATTERNS,
                "COMMON_PATTERNS");
        // Should list at least 2 patterns
        String answer = ArchitectureAnalysis.COMMON_PATTERNS;
        int commaCount = answer.length() - answer.replace(",", "").length();
        assertTrue(commaCount >= 1,
                "Should list at least 2 patterns separated by commas");
    }

    @Test
    @DisplayName("Common mock features are identified")
    void commonMockFeaturesIdentified() {
        assertNotBlank(ArchitectureAnalysis.COMMON_MOCK_FEATURES,
                "COMMON_MOCK_FEATURES");
        assertMinLength(ArchitectureAnalysis.COMMON_MOCK_FEATURES, 30,
                "COMMON_MOCK_FEATURES should describe shared concepts");
    }

    @Test
    @DisplayName("Model structure difference is identified")
    void modelStructureDifferenceIdentified() {
        assertNotBlank(ArchitectureAnalysis.MODEL_STRUCTURE_DIFFERENCE,
                "MODEL_STRUCTURE_DIFFERENCE");
        assertMinLength(ArchitectureAnalysis.MODEL_STRUCTURE_DIFFERENCE, 40,
                "MODEL_STRUCTURE_DIFFERENCE should explain the difference");
    }

    // ==================== Your Project Planning ====================

    @Test
    @DisplayName("Project interfaces are proposed")
    void projectInterfacesProposed() {
        assertNotBlank(ArchitectureAnalysis.YOUR_PROJECT_INTERFACES,
                "YOUR_PROJECT_INTERFACES");
        assertMinLength(ArchitectureAnalysis.YOUR_PROJECT_INTERFACES, 20,
                "YOUR_PROJECT_INTERFACES should list interfaces with purposes");
    }

    @Test
    @DisplayName("Project entities are described")
    void projectEntitiesDescribed() {
        assertNotBlank(ArchitectureAnalysis.YOUR_PROJECT_ENTITIES,
                "YOUR_PROJECT_ENTITIES");
        assertMinLength(ArchitectureAnalysis.YOUR_PROJECT_ENTITIES, 20,
                "YOUR_PROJECT_ENTITIES should describe your project's data");
    }

    // ==================== Helper Methods ====================

    private void assertNotBlank(String value, String fieldName) {
        assertNotNull(value, fieldName + " should not be null");
        assertFalse(value.isBlank(), fieldName + " should not be blank - fill in your answer");
    }

    private void assertMinLength(String value, int minLength, String message) {
        assertTrue(value.length() >= minLength, message);
    }
}
