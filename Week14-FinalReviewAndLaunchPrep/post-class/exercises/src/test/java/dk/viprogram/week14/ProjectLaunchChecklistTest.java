package dk.viprogram.week14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to verify that the project launch checklist is complete.
 *
 * These tests check that you have properly set up your exam project
 * and are ready to begin the 2-week implementation period.
 */
class ProjectLaunchChecklistTest {

    private ProjectLaunchChecklist checklist;

    @BeforeEach
    void setUp() {
        checklist = new ProjectLaunchChecklist();
    }

    // ==================== Project Setup ====================

    @Test
    @DisplayName("Project has a name")
    void projectHasName() {
        assertFalse(checklist.getProjectName().isBlank(),
                "Project name (artifactId) should not be blank");
    }

    @Test
    @DisplayName("Base package is defined")
    void basePackageDefined() {
        assertFalse(checklist.getBasePackage().isBlank(),
                "Base package should not be blank (e.g., dk.viprogram.yourproject)");
        assertTrue(checklist.getBasePackage().contains("."),
                "Base package should be a valid Java package with dots");
    }

    // ==================== Files Created ====================

    @Test
    @DisplayName("Interface files are listed")
    void interfaceFilesListed() {
        assertFalse(checklist.getInterfaceFiles().isBlank(),
                "List the interface files you created in your exam project");
        assertTrue(checklist.getInterfaceFiles().length() >= 30,
                "You should have at least 2 interface files listed. " +
                "Include full package paths.");
    }

    @Test
    @DisplayName("Record files are listed")
    void recordFilesListed() {
        assertFalse(checklist.getRecordFiles().isBlank(),
                "List the record files you created in your exam project");
        assertTrue(checklist.getRecordFiles().length() >= 20,
                "You should have at least 2 record files listed.");
    }

    @Test
    @DisplayName("Test double files are listed")
    void testDoubleFilesListed() {
        assertFalse(checklist.getTestDoubleFiles().isBlank(),
                "List the test doubles (mocks, in-memory repos) you created");
        assertTrue(checklist.getTestDoubleFiles().length() >= 20,
                "You should have at least 1 mock and 1 InMemoryRepository listed.");
    }

    @Test
    @DisplayName("Test files are listed")
    void testFilesListed() {
        assertFalse(checklist.getTestFiles().isBlank(),
                "List the test files you created in your exam project");
    }

    // ==================== Compilation and Git ====================

    @Test
    @DisplayName("Compilation status is reported")
    void compilationStatusReported() {
        assertFalse(checklist.getCompilationStatus().isBlank(),
                "Report whether your exam project compiles with mvn clean compile");
    }

    @Test
    @DisplayName("Git status is reported")
    void gitStatusReported() {
        assertFalse(checklist.getGitStatus().isBlank(),
                "Report whether you have initialized a git repository");
        String status = checklist.getGitStatus().toLowerCase();
        assertTrue(status.contains("yes") || status.contains("git") ||
                        status.contains("init") || status.contains("commit"),
                "Git status should mention initialization or commit");
    }

    // ==================== Readiness ====================

    @Test
    @DisplayName("First implementation task is defined")
    void firstTaskDefined() {
        assertFalse(checklist.getFirstTask().isBlank(),
                "Describe the first thing you will implement");
        assertTrue(checklist.getFirstTask().length() >= 30,
                "First task description should be specific enough to start immediately. " +
                "Mention the class, the methods, and the tests to run.");
    }

    @Test
    @DisplayName("Confidence level is reported")
    void confidenceLevelReported() {
        assertFalse(checklist.getConfidenceLevel().isBlank(),
                "Rate your confidence (1-10) and explain briefly");
    }
}
