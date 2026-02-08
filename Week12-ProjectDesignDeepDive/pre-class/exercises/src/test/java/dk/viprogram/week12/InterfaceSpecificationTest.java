package dk.viprogram.week12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to verify that the interface specification is complete and detailed.
 *
 * These tests check that you have fully specified your project's interfaces
 * with method signatures, JavaDoc, record definitions, and workflows.
 */
class InterfaceSpecificationTest {

    private InterfaceSpecification spec;

    @BeforeEach
    void setUp() {
        spec = new InterfaceSpecification();
    }

    // ==================== View Interface ====================

    @Test
    @DisplayName("View interface has a name")
    void viewInterfaceHasName() {
        assertFalse(spec.getViewInterfaceName().isBlank(),
                "View interface should have a name (e.g., RecipeView, TaskView)");
    }

    @Test
    @DisplayName("View interface has at least 3 method signatures")
    void viewInterfaceHasMethods() {
        List<String> methods = spec.getViewMethods();
        assertTrue(methods.size() >= 3,
                "View interface should have at least 3 methods. " +
                "You need display methods, input methods, and feedback methods. " +
                "Found: " + methods.size());
    }

    @Test
    @DisplayName("View methods have return types and descriptions")
    void viewMethodsAreDetailed() {
        for (String method : spec.getViewMethods()) {
            assertFalse(method.isBlank(),
                    "Method signature should not be blank");
            assertTrue(method.length() >= 20,
                    "Method '" + method + "' needs more detail. " +
                    "Include return type, method name, parameters, and description.");
            assertTrue(method.contains("(") && method.contains(")"),
                    "Method '" + method + "' should include parameters in parentheses, " +
                    "e.g., 'void showList(List<Recipe> recipes) - displays recipes'");
        }
    }

    @Test
    @DisplayName("View interface has JavaDoc")
    void viewInterfaceHasJavadoc() {
        assertFalse(spec.getViewJavadoc().isBlank(),
                "View interface should have a JavaDoc description");
        assertTrue(spec.getViewJavadoc().length() >= 50,
                "View JavaDoc should describe the interface's purpose and " +
                "how implementations should behave (at least 50 characters)");
    }

    // ==================== Repository Interface ====================

    @Test
    @DisplayName("Repository interface has a name")
    void repositoryInterfaceHasName() {
        assertFalse(spec.getRepositoryInterfaceName().isBlank(),
                "Repository interface should have a name (e.g., RecipeRepository)");
    }

    @Test
    @DisplayName("Repository interface has at least 4 method signatures")
    void repositoryInterfaceHasMethods() {
        List<String> methods = spec.getRepositoryMethods();
        assertTrue(methods.size() >= 4,
                "Repository interface should have at least 4 methods " +
                "(save, findById, findAll, delete + domain queries). " +
                "Found: " + methods.size());
    }

    @Test
    @DisplayName("Repository methods have return types and descriptions")
    void repositoryMethodsAreDetailed() {
        for (String method : spec.getRepositoryMethods()) {
            assertFalse(method.isBlank(),
                    "Method signature should not be blank");
            assertTrue(method.length() >= 20,
                    "Method '" + method + "' needs more detail. " +
                    "Include return type, method name, parameters, and description.");
        }
    }

    @Test
    @DisplayName("Repository interface has JavaDoc")
    void repositoryInterfaceHasJavadoc() {
        assertFalse(spec.getRepositoryJavadoc().isBlank(),
                "Repository interface should have a JavaDoc description");
        assertTrue(spec.getRepositoryJavadoc().length() >= 50,
                "Repository JavaDoc should describe the interface's purpose (at least 50 characters)");
    }

    // ==================== Additional Interfaces ====================

    @Test
    @DisplayName("Additional interfaces rationale is provided")
    void additionalInterfacesRationaleProvided() {
        assertFalse(spec.getAdditionalInterfacesRationale().isBlank(),
                "Either define additional interfaces or explain why none are needed");
    }

    // ==================== Record Specifications ====================

    @Test
    @DisplayName("At least 2 records are specified")
    void hasRecordSpecifications() {
        assertTrue(spec.getRecordSpecifications().size() >= 2,
                "You should specify at least 2 records (entities). " +
                "Found: " + spec.getRecordSpecifications().size());
    }

    @Test
    @DisplayName("Each record has at least 3 fields")
    void recordsHaveFields() {
        for (var entry : spec.getRecordSpecifications().entrySet()) {
            String recordName = entry.getKey();
            List<String> fields = entry.getValue();
            assertFalse(recordName.isBlank(),
                    "Record name should not be blank");
            assertTrue(fields.size() >= 3,
                    "Record '" + recordName + "' should have at least 3 fields " +
                    "(including id). Found: " + fields.size());
        }
    }

    @Test
    @DisplayName("Record fields include types and descriptions")
    void recordFieldsAreDetailed() {
        for (var entry : spec.getRecordSpecifications().entrySet()) {
            for (String field : entry.getValue()) {
                assertTrue(field.length() >= 10,
                        "Field '" + field + "' in record '" + entry.getKey() +
                        "' needs more detail. Include type, name, and description.");
            }
        }
    }

    // ==================== Workflow Descriptions ====================

    @Test
    @DisplayName("Workflow descriptions are provided")
    void workflowDescriptionsProvided() {
        assertFalse(spec.getWorkflowDescriptions().isBlank(),
                "Workflow descriptions should not be blank. " +
                "Describe at least 2 user operations step by step.");
    }

    @Test
    @DisplayName("Workflow descriptions are detailed")
    void workflowDescriptionsAreDetailed() {
        assertTrue(spec.getWorkflowDescriptions().length() >= 200,
                "Workflow descriptions should be detailed, showing which interface " +
                "methods are called in what order. At least 200 characters expected.");
    }

    @Test
    @DisplayName("Workflows mention interface methods")
    void workflowsMentionInterfaceMethods() {
        String workflows = spec.getWorkflowDescriptions().toLowerCase();
        assertTrue(workflows.contains("view") || workflows.contains("controller") ||
                        workflows.contains("repository") || workflows.contains("repo"),
                "Workflows should reference the layers (view, controller, repository)");
    }
}
