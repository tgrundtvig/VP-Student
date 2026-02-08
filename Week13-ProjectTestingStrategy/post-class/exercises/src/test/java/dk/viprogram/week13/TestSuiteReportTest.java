package dk.viprogram.week13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to verify that the test suite report is complete.
 *
 * These tests check that you have written actual test code and mock
 * implementations for your exam project.
 */
class TestSuiteReportTest {

    private TestSuiteReport report;

    @BeforeEach
    void setUp() {
        report = new TestSuiteReport();
    }

    // ==================== Model Tests ====================

    @Test
    @DisplayName("Model test code is written")
    void modelTestCodeWritten() {
        assertFalse(report.getModelTestCode().isBlank(),
                "Model test code should not be blank. " +
                "Write at least 2 test methods for your records.");
    }

    @Test
    @DisplayName("Model test code contains test annotations")
    void modelTestCodeContainsAnnotations() {
        String code = report.getModelTestCode();
        assertTrue(code.contains("@Test") || code.contains("@test") ||
                        code.contains("void") || code.contains("assert"),
                "Model test code should contain JUnit annotations and assertions");
    }

    @Test
    @DisplayName("Model test code is substantial")
    void modelTestCodeIsSubstantial() {
        assertTrue(report.getModelTestCode().length() >= 150,
                "Model test code should contain at least 2 complete test methods. " +
                "Found " + report.getModelTestCode().length() + " characters, need at least 150.");
    }

    // ==================== Repository Tests ====================

    @Test
    @DisplayName("Repository test code is written")
    void repositoryTestCodeWritten() {
        assertFalse(report.getRepositoryTestCode().isBlank(),
                "Repository test code should not be blank. " +
                "Write at least 3 test methods for your Repository.");
    }

    @Test
    @DisplayName("Repository test code contains test logic")
    void repositoryTestCodeContainsLogic() {
        String code = report.getRepositoryTestCode();
        assertTrue(code.contains("@Test") || code.contains("assert") ||
                        code.contains("save") || code.contains("find"),
                "Repository test code should contain JUnit tests with " +
                "save/find operations and assertions");
    }

    @Test
    @DisplayName("Repository test code is substantial")
    void repositoryTestCodeIsSubstantial() {
        assertTrue(report.getRepositoryTestCode().length() >= 200,
                "Repository test code should contain at least 3 complete test methods. " +
                "Found " + report.getRepositoryTestCode().length() + " characters, need at least 200.");
    }

    // ==================== Controller Tests ====================

    @Test
    @DisplayName("Controller test code is written")
    void controllerTestCodeWritten() {
        assertFalse(report.getControllerTestCode().isBlank(),
                "Controller test code should not be blank. " +
                "Write at least 3 test methods using MockView and InMemoryRepository.");
    }

    @Test
    @DisplayName("Controller test code mentions mock")
    void controllerTestCodeMentionsMock() {
        String code = report.getControllerTestCode().toLowerCase();
        assertTrue(code.contains("mock") || code.contains("queue") ||
                        code.contains("controller") || code.contains("setup"),
                "Controller test code should use mock objects and test " +
                "the controller's coordination logic");
    }

    @Test
    @DisplayName("Controller test code is substantial")
    void controllerTestCodeIsSubstantial() {
        assertTrue(report.getControllerTestCode().length() >= 200,
                "Controller test code should contain at least 3 complete test methods " +
                "plus setup. Found " + report.getControllerTestCode().length() +
                " characters, need at least 200.");
    }

    // ==================== Mock View ====================

    @Test
    @DisplayName("Mock View implementation is written")
    void mockViewCodeWritten() {
        assertFalse(report.getMockViewCode().isBlank(),
                "Mock View implementation should not be blank. " +
                "Write the complete MockView class.");
    }

    @Test
    @DisplayName("Mock View code is substantial")
    void mockViewCodeIsSubstantial() {
        assertTrue(report.getMockViewCode().length() >= 200,
                "Mock View should include queues, lists, interface methods, and " +
                "helper methods. Found " + report.getMockViewCode().length() +
                " characters, need at least 200.");
    }

    @Test
    @DisplayName("Mock View mentions key patterns")
    void mockViewMentionsPatterns() {
        String code = report.getMockViewCode().toLowerCase();
        assertTrue(code.contains("queue") || code.contains("list") ||
                        code.contains("implements") || code.contains("override"),
                "Mock View should show the queue/list pattern and " +
                "implement the View interface");
    }

    // ==================== InMemory Repository ====================

    @Test
    @DisplayName("InMemory Repository implementation is written")
    void inMemoryRepoCodeWritten() {
        assertFalse(report.getInMemoryRepoCode().isBlank(),
                "InMemory Repository implementation should not be blank. " +
                "Write the complete InMemoryRepository class.");
    }

    @Test
    @DisplayName("InMemory Repository code is substantial")
    void inMemoryRepoCodeIsSubstantial() {
        assertTrue(report.getInMemoryRepoCode().length() >= 150,
                "InMemory Repository should include the Map storage and all " +
                "interface method implementations. Found " +
                report.getInMemoryRepoCode().length() + " characters, need at least 150.");
    }

    @Test
    @DisplayName("InMemory Repository mentions storage")
    void inMemoryRepoMentionsStorage() {
        String code = report.getInMemoryRepoCode().toLowerCase();
        assertTrue(code.contains("map") || code.contains("hashmap") ||
                        code.contains("storage") || code.contains("put") ||
                        code.contains("get"),
                "InMemory Repository should use a Map for storage");
    }

    // ==================== Design Changes ====================

    @Test
    @DisplayName("Design changes are documented")
    void designChangesDocumented() {
        assertFalse(report.getDesignChanges().isBlank(),
                "Document what writing tests revealed about your design. " +
                "If no changes needed, explain why.");
    }

    @Test
    @DisplayName("Design changes reflection is thoughtful")
    void designChangesAreThoughtful() {
        assertTrue(report.getDesignChanges().length() >= 50,
                "Design changes reflection should be at least 50 characters. " +
                "Describe specific changes or explain why your design was complete.");
    }
}
