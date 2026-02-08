package dk.viprogram.week12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to verify that the design document is complete and detailed.
 *
 * These tests check that all sections contain sufficient detail for
 * someone to write tests against your interfaces.
 */
class DesignDocumentTest {

    private DesignDocument doc;

    @BeforeEach
    void setUp() {
        doc = new DesignDocument();
    }

    // ==================== Interface JavaDocs ====================

    @Test
    @DisplayName("At least 2 interfaces have complete JavaDoc")
    void interfacesHaveJavaDocs() {
        assertTrue(doc.getInterfaceJavaDocs().size() >= 2,
                "You should provide JavaDoc for at least 2 interfaces " +
                "(View and Repository at minimum). Found: " + doc.getInterfaceJavaDocs().size());
    }

    @Test
    @DisplayName("Interface JavaDocs are detailed")
    void interfaceJavaDocsAreDetailed() {
        for (var entry : doc.getInterfaceJavaDocs().entrySet()) {
            assertFalse(entry.getKey().isBlank(),
                    "Interface name should not be blank");
            assertTrue(entry.getValue().length() >= 100,
                    "JavaDoc for '" + entry.getKey() + "' should be detailed. " +
                    "Include interface description and method-level documentation. " +
                    "Found " + entry.getValue().length() + " characters, need at least 100.");
        }
    }

    @Test
    @DisplayName("JavaDocs mention methods")
    void javaDocsMentionMethods() {
        for (var entry : doc.getInterfaceJavaDocs().entrySet()) {
            String javadoc = entry.getValue().toLowerCase();
            assertTrue(javadoc.contains("method") || javadoc.contains("void") ||
                            javadoc.contains("list") || javadoc.contains("optional") ||
                            javadoc.contains("@param") || javadoc.contains("@return"),
                    "JavaDoc for '" + entry.getKey() + "' should document individual methods " +
                    "with signatures, @param, and @return annotations");
        }
    }

    // ==================== Workflows ====================

    @Test
    @DisplayName("At least 3 workflows are documented")
    void hasWorkflows() {
        assertTrue(doc.getWorkflows().size() >= 3,
                "You should document at least 3 user operation workflows. " +
                "Found: " + doc.getWorkflows().size());
    }

    @Test
    @DisplayName("Workflows are step-by-step")
    void workflowsAreStepByStep() {
        for (var entry : doc.getWorkflows().entrySet()) {
            assertFalse(entry.getKey().isBlank(),
                    "Workflow name should not be blank");
            assertTrue(entry.getValue().length() >= 100,
                    "Workflow '" + entry.getKey() + "' should be detailed, showing " +
                    "each step with the responsible layer. " +
                    "Found " + entry.getValue().length() + " characters, need at least 100.");
        }
    }

    @Test
    @DisplayName("Workflows reference layers")
    void workflowsReferenceLayers() {
        for (var entry : doc.getWorkflows().entrySet()) {
            String workflow = entry.getValue().toLowerCase();
            assertTrue(workflow.contains("controller") || workflow.contains("view") ||
                            workflow.contains("repository") || workflow.contains("repo"),
                    "Workflow '" + entry.getKey() + "' should reference architectural layers " +
                    "(controller, view, repository)");
        }
    }

    // ==================== Mock Sketches ====================

    @Test
    @DisplayName("At least 1 mock implementation is sketched")
    void hasMockSketches() {
        assertTrue(doc.getMockSketches().size() >= 1,
                "You should sketch at least 1 mock implementation " +
                "(typically the MockView). Found: " + doc.getMockSketches().size());
    }

    @Test
    @DisplayName("Mock sketches describe internal state")
    void mockSketchesDescribeState() {
        for (var entry : doc.getMockSketches().entrySet()) {
            assertTrue(entry.getValue().length() >= 80,
                    "Mock sketch for '" + entry.getKey() + "' should describe internal " +
                    "state (queues, lists) and how methods use them. " +
                    "Found " + entry.getValue().length() + " characters, need at least 80.");
        }
    }

    // ==================== Edge Cases ====================

    @Test
    @DisplayName("Edge cases are documented")
    void edgeCasesDocumented() {
        assertFalse(doc.getEdgeCaseDocumentation().isBlank(),
                "Edge case documentation should not be blank. " +
                "Document what happens with empty lists, missing IDs, null values, etc.");
    }

    @Test
    @DisplayName("Edge case documentation is detailed")
    void edgeCaseDocumentationIsDetailed() {
        assertTrue(doc.getEdgeCaseDocumentation().length() >= 100,
                "Edge case documentation should cover multiple scenarios. " +
                "Found " + doc.getEdgeCaseDocumentation().length() + " characters, need at least 100.");
    }
}
