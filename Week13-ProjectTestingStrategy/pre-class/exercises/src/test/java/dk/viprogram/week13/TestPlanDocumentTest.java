package dk.viprogram.week13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to verify that the test plan document is complete and thorough.
 *
 * These tests check that you have planned a comprehensive test suite
 * for your exam project.
 */
class TestPlanDocumentTest {

    private TestPlanDocument plan;

    @BeforeEach
    void setUp() {
        plan = new TestPlanDocument();
    }

    // ==================== Test Categories ====================

    @Test
    @DisplayName("Plan defines at least 3 test categories")
    void hasTestCategories() {
        assertTrue(plan.getTestCategories().size() >= 3,
                "You should define at least 3 test categories " +
                "(e.g., Model, Repository, Controller). Found: " +
                plan.getTestCategories().size());
    }

    @Test
    @DisplayName("Each test category has a meaningful description")
    void testCategoriesAreDescribed() {
        for (String category : plan.getTestCategories()) {
            assertFalse(category.isBlank(),
                    "Test category should not be blank");
            assertTrue(category.length() >= 20,
                    "Test category '" + category + "' needs more detail. " +
                    "Describe what this category tests and why.");
        }
    }

    // ==================== Test Cases ====================

    @Test
    @DisplayName("At least 2 components have test cases")
    void hasTestCasesForComponents() {
        assertTrue(plan.getTestCasesPerComponent().size() >= 2,
                "You should list test cases for at least 2 components " +
                "(e.g., Repository and Controller). Found: " +
                plan.getTestCasesPerComponent().size());
    }

    @Test
    @DisplayName("Each component has at least 2 test cases")
    void componentsHaveTestCases() {
        for (var entry : plan.getTestCasesPerComponent().entrySet()) {
            String component = entry.getKey();
            List<String> cases = entry.getValue();
            assertFalse(component.isBlank(),
                    "Component name should not be blank");
            assertTrue(cases.size() >= 2,
                    "Component '" + component + "' should have at least 2 test cases. " +
                    "Found: " + cases.size());
        }
    }

    @Test
    @DisplayName("Test cases describe scenarios and expectations")
    void testCasesAreDescriptive() {
        for (var entry : plan.getTestCasesPerComponent().entrySet()) {
            for (String testCase : entry.getValue()) {
                assertFalse(testCase.isBlank(),
                        "Test case in '" + entry.getKey() + "' should not be blank");
                assertTrue(testCase.length() >= 15,
                        "Test case '" + testCase + "' in '" + entry.getKey() +
                        "' needs more detail. Describe the scenario and expected result.");
            }
        }
    }

    // ==================== Mock Strategy ====================

    @Test
    @DisplayName("Mock strategy is documented")
    void mockStrategyDocumented() {
        assertFalse(plan.getMockStrategy().isBlank(),
                "Mock strategy should describe your test doubles " +
                "(MockView, InMemoryRepository)");
    }

    @Test
    @DisplayName("Mock strategy is detailed")
    void mockStrategyIsDetailed() {
        assertTrue(plan.getMockStrategy().length() >= 100,
                "Mock strategy should describe internal state, programming methods, " +
                "and verification methods for each test double. " +
                "Found " + plan.getMockStrategy().length() + " characters, need at least 100.");
    }

    @Test
    @DisplayName("Mock strategy mentions key concepts")
    void mockStrategyMentionsKeyConcepts() {
        String strategy = plan.getMockStrategy().toLowerCase();
        assertTrue(strategy.contains("mock") || strategy.contains("inmemory") ||
                        strategy.contains("in-memory") || strategy.contains("queue") ||
                        strategy.contains("hashmap") || strategy.contains("map"),
                "Mock strategy should mention mock objects, in-memory implementations, " +
                "or the data structures used (queues, maps)");
    }

    // ==================== Test Infrastructure ====================

    @Test
    @DisplayName("Test infrastructure is documented")
    void testInfrastructureDocumented() {
        assertFalse(plan.getTestInfrastructure().isBlank(),
                "Test infrastructure should describe test class names and setup");
    }

    @Test
    @DisplayName("Test infrastructure is detailed")
    void testInfrastructureIsDetailed() {
        assertTrue(plan.getTestInfrastructure().length() >= 80,
                "Test infrastructure should list test classes and describe common setup. " +
                "Found " + plan.getTestInfrastructure().length() + " characters, need at least 80.");
    }

    // ==================== Test Count ====================

    @Test
    @DisplayName("Test counts are estimated")
    void testCountsEstimated() {
        assertFalse(plan.getEstimatedTestCounts().isEmpty(),
                "You should estimate how many tests per category");
    }

    @Test
    @DisplayName("Total estimated tests is at least 10")
    void totalTestCountIsReasonable() {
        int total = plan.getTotalEstimatedTests();
        assertTrue(total >= 10,
                "You should plan at least 10 tests total. " +
                "A well-tested project typically has 13-23 tests. " +
                "Currently estimated: " + total);
    }

    @Test
    @DisplayName("No category has zero tests")
    void noCategoryHasZeroTests() {
        for (var entry : plan.getEstimatedTestCounts().entrySet()) {
            assertTrue(entry.getValue() > 0,
                    "Category '" + entry.getKey() + "' should have at least 1 test");
        }
    }
}
