package dk.viprogram.week14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to verify that the implementation roadmap is complete and realistic.
 *
 * These tests check that you have a clear plan for the 2-week implementation period.
 */
class ImplementationRoadmapTest {

    private ImplementationRoadmap roadmap;

    @BeforeEach
    void setUp() {
        roadmap = new ImplementationRoadmap();
    }

    // ==================== Phases ====================

    @Test
    @DisplayName("Roadmap has at least 4 implementation phases")
    void hasPhases() {
        assertTrue(roadmap.getPhases().size() >= 4,
                "You should define at least 4 implementation phases. " +
                "Found: " + roadmap.getPhases().size());
    }

    @Test
    @DisplayName("Each phase has a meaningful description")
    void phasesAreDescribed() {
        for (String phase : roadmap.getPhases()) {
            assertFalse(phase.isBlank(),
                    "Phase description should not be blank");
            assertTrue(phase.length() >= 30,
                    "Phase '" + phase + "' needs more detail. " +
                    "Describe what gets implemented and what tests should pass.");
        }
    }

    // ==================== Task Breakdown ====================

    @Test
    @DisplayName("At least 3 phases have task breakdowns")
    void hasTaskBreakdowns() {
        assertTrue(roadmap.getTaskBreakdown().size() >= 3,
                "You should break down tasks for at least 3 phases. " +
                "Found: " + roadmap.getTaskBreakdown().size());
    }

    @Test
    @DisplayName("Each phase has at least 2 tasks")
    void phasesHaveTasks() {
        for (var entry : roadmap.getTaskBreakdown().entrySet()) {
            String phase = entry.getKey();
            List<String> tasks = entry.getValue();
            assertTrue(tasks.size() >= 2,
                    "Phase '" + phase + "' should have at least 2 tasks. " +
                    "Found: " + tasks.size());
        }
    }

    @Test
    @DisplayName("Tasks are specific")
    void tasksAreSpecific() {
        for (var entry : roadmap.getTaskBreakdown().entrySet()) {
            for (String task : entry.getValue()) {
                assertFalse(task.isBlank(),
                        "Task in '" + entry.getKey() + "' should not be blank");
                assertTrue(task.length() >= 15,
                        "Task '" + task + "' in '" + entry.getKey() +
                        "' should be more specific. Mention the class or file to create.");
            }
        }
    }

    // ==================== Priority Order ====================

    @Test
    @DisplayName("Tasks are prioritized")
    void tasksArePrioritized() {
        assertFalse(roadmap.getPrioritizedTasks().isEmpty(),
                "You should prioritize your tasks into P1/P2/P3 levels");
    }

    @Test
    @DisplayName("Has P1 (must have) tasks")
    void hasP1Tasks() {
        boolean hasP1 = roadmap.getPrioritizedTasks().keySet().stream()
                .anyMatch(key -> key.toLowerCase().contains("p1") ||
                        key.toLowerCase().contains("must"));
        assertTrue(hasP1,
                "You should have P1 (Must Have) tasks defined");
    }

    @Test
    @DisplayName("P1 tasks include core functionality")
    void p1TasksIncludeCore() {
        for (var entry : roadmap.getPrioritizedTasks().entrySet()) {
            if (entry.getKey().toLowerCase().contains("p1") ||
                    entry.getKey().toLowerCase().contains("must")) {
                assertTrue(entry.getValue().size() >= 3,
                        "P1 (Must Have) should include at least 3 essential tasks. " +
                        "Found: " + entry.getValue().size());
            }
        }
    }

    // ==================== Time Estimates ====================

    @Test
    @DisplayName("Time estimates are provided")
    void hasTimeEstimates() {
        assertFalse(roadmap.getTimeEstimates().isEmpty(),
                "You should estimate hours for each phase");
    }

    @Test
    @DisplayName("Total estimated time is realistic")
    void totalTimeIsRealistic() {
        int total = roadmap.getTotalEstimatedHours();
        assertTrue(total >= 10,
                "Total estimated time should be at least 10 hours. " +
                "You have 2 weeks to implement. Current estimate: " + total + " hours.");
        assertTrue(total <= 60,
                "Total estimated time should not exceed 60 hours. " +
                "Be realistic about available time. Current estimate: " + total + " hours.");
    }

    @Test
    @DisplayName("No phase has zero hours")
    void noPhaseHasZeroHours() {
        for (var entry : roadmap.getTimeEstimates().entrySet()) {
            assertTrue(entry.getValue() > 0,
                    "Phase '" + entry.getKey() + "' should have a non-zero time estimate");
        }
    }

    // ==================== Demo Plan ====================

    @Test
    @DisplayName("Demo plan is provided")
    void demoPlanProvided() {
        assertFalse(roadmap.getDemoPlan().isBlank(),
                "Demo plan should describe what you will show at the exam");
    }

    @Test
    @DisplayName("Demo plan is detailed")
    void demoPlanIsDetailed() {
        assertTrue(roadmap.getDemoPlan().length() >= 100,
                "Demo plan should list the features you will demonstrate in order. " +
                "Found " + roadmap.getDemoPlan().length() + " characters, need at least 100.");
    }

    // ==================== Risk Assessment ====================

    @Test
    @DisplayName("Risk assessment is provided")
    void riskAssessmentProvided() {
        assertFalse(roadmap.getRiskAssessment().isBlank(),
                "Risk assessment should identify potential problems and mitigations");
    }

    @Test
    @DisplayName("Risk assessment is detailed")
    void riskAssessmentIsDetailed() {
        assertTrue(roadmap.getRiskAssessment().length() >= 80,
                "Risk assessment should describe at least 2 risks with mitigations. " +
                "Found " + roadmap.getRiskAssessment().length() + " characters, need at least 80.");
    }
}
