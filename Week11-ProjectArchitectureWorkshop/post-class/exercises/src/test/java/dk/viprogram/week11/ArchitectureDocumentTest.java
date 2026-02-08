package dk.viprogram.week11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to verify that the architecture document is complete and well-structured.
 *
 * These tests check that all sections of the document contain sufficient detail
 * for someone to understand your system's architecture.
 */
class ArchitectureDocumentTest {

    private ArchitectureDocument doc;

    @BeforeEach
    void setUp() {
        doc = new ArchitectureDocument();
    }

    // ==================== Project Identity ====================

    @Test
    @DisplayName("Document has a project name")
    void documentHasProjectName() {
        assertNotNull(doc.getProjectName(), "Project name should not be null");
        assertFalse(doc.getProjectName().isBlank(),
                "Project name should not be blank");
    }

    @Test
    @DisplayName("Document has a project summary")
    void documentHasProjectSummary() {
        assertNotNull(doc.getProjectSummary(), "Project summary should not be null");
        assertFalse(doc.getProjectSummary().isBlank(),
                "Project summary should not be blank");
        assertTrue(doc.getProjectSummary().length() >= 50,
                "Project summary should be at least 50 characters - describe purpose and scope");
    }

    // ==================== Interfaces ====================

    @Test
    @DisplayName("Document defines at least 2 interfaces")
    void documentHasInterfaces() {
        assertNotNull(doc.getInterfaces(), "Interfaces map should not be null");
        assertTrue(doc.getInterfaces().size() >= 2,
                "You should define at least 2 interfaces (View and Repository at minimum). " +
                "Found: " + doc.getInterfaces().size());
    }

    @Test
    @DisplayName("Each interface has method signatures")
    void interfacesHaveMethodSignatures() {
        for (var entry : doc.getInterfaces().entrySet()) {
            assertFalse(entry.getKey().isBlank(),
                    "Interface name should not be blank");
            assertFalse(entry.getValue().isBlank(),
                    "Interface '" + entry.getKey() + "' should have method signatures");
            assertTrue(entry.getValue().length() >= 30,
                    "Interface '" + entry.getKey() + "' needs more method signatures. " +
                    "Include return types and parameter types for each method.");
        }
    }

    @Test
    @DisplayName("At least one interface looks like a View")
    void hasViewInterface() {
        boolean hasView = doc.getInterfaces().keySet().stream()
                .anyMatch(name -> name.toLowerCase().contains("view"));
        assertTrue(hasView,
                "You should have at least one View interface (e.g., RecipeView, TaskView)");
    }

    @Test
    @DisplayName("At least one interface looks like a Repository")
    void hasRepositoryInterface() {
        boolean hasRepo = doc.getInterfaces().keySet().stream()
                .anyMatch(name -> name.toLowerCase().contains("repository") ||
                        name.toLowerCase().contains("repo"));
        assertTrue(hasRepo,
                "You should have at least one Repository interface (e.g., RecipeRepository)");
    }

    // ==================== Records ====================

    @Test
    @DisplayName("Document defines at least 2 records")
    void documentHasRecords() {
        assertNotNull(doc.getRecords(), "Records map should not be null");
        assertTrue(doc.getRecords().size() >= 2,
                "You should define at least 2 records (entities). Found: " + doc.getRecords().size());
    }

    @Test
    @DisplayName("Each record has field definitions")
    void recordsHaveFields() {
        for (var entry : doc.getRecords().entrySet()) {
            assertFalse(entry.getKey().isBlank(),
                    "Record name should not be blank");
            assertFalse(entry.getValue().isBlank(),
                    "Record '" + entry.getKey() + "' should have field definitions");
            assertTrue(entry.getValue().length() >= 20,
                    "Record '" + entry.getKey() + "' needs more field definitions. " +
                    "Include type and description for each field.");
        }
    }

    // ==================== Architecture Layers ====================

    @Test
    @DisplayName("Architecture layers are defined")
    void architectureLayersDefined() {
        assertNotNull(doc.getArchitectureLayers(), "Architecture layers should not be null");
        assertFalse(doc.getArchitectureLayers().isBlank(),
                "Architecture layers should not be blank");
        assertTrue(doc.getArchitectureLayers().length() >= 100,
                "Architecture layers should describe all layers with their components. " +
                "Include View, Controller, Model, and Repository layers.");
    }

    @Test
    @DisplayName("Architecture mentions key layers")
    void architectureMentionsKeyLayers() {
        String layers = doc.getArchitectureLayers().toLowerCase();
        assertTrue(layers.contains("view") || layers.contains("ui"),
                "Architecture should mention the View/UI layer");
        assertTrue(layers.contains("controller") || layers.contains("service"),
                "Architecture should mention the Controller/Service layer");
        assertTrue(layers.contains("model") || layers.contains("record") || layers.contains("entity"),
                "Architecture should mention the Model/Entity layer");
        assertTrue(layers.contains("repository") || layers.contains("persistence"),
                "Architecture should mention the Repository/Persistence layer");
    }

    // ==================== Formatted Document ====================

    @Test
    @DisplayName("Formatted document is not empty")
    void formattedDocumentIsNotEmpty() {
        String formatted = doc.getFormattedDocument();
        assertNotNull(formatted, "Formatted document should not be null");
        assertFalse(formatted.isBlank(),
                "Formatted document should not be blank - implement getFormattedDocument()");
    }

    @Test
    @DisplayName("Formatted document contains project name")
    void formattedDocumentContainsProjectName() {
        String formatted = doc.getFormattedDocument();
        if (!doc.getProjectName().isBlank()) {
            assertTrue(formatted.contains(doc.getProjectName()),
                    "Formatted document should include the project name");
        }
    }

    @Test
    @DisplayName("Formatted document is comprehensive")
    void formattedDocumentIsComprehensive() {
        String formatted = doc.getFormattedDocument();
        assertTrue(formatted.length() >= 200,
                "Formatted document should be comprehensive. " +
                "Include all interfaces, records, and architecture layers.");
    }
}
