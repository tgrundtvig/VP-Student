package dk.viprogram.week10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to verify the project proposal is complete.
 */
class ProjectProposalTest {

    // ==================== Project Overview ====================

    @Test
    @DisplayName("Project has a title")
    void projectHasTitle() {
        assertNotBlank(ProjectProposal.PROJECT_TITLE, "PROJECT_TITLE");
    }

    @Test
    @DisplayName("Project description is detailed")
    void projectDescriptionDetailed() {
        assertNotBlank(ProjectProposal.PROJECT_DESCRIPTION, "PROJECT_DESCRIPTION");
        assertMinLength(ProjectProposal.PROJECT_DESCRIPTION, 100,
                "PROJECT_DESCRIPTION should be at least 100 characters");
    }

    @Test
    @DisplayName("Project motivation is explained")
    void projectMotivationExplained() {
        assertNotBlank(ProjectProposal.PROJECT_MOTIVATION, "PROJECT_MOTIVATION");
        assertMinLength(ProjectProposal.PROJECT_MOTIVATION, 50,
                "PROJECT_MOTIVATION should be at least 50 characters");
    }

    // ==================== Entity Definitions ====================

    @Test
    @DisplayName("Entities are defined")
    void entitiesAreDefined() {
        assertNotBlank(ProjectProposal.ENTITIES, "ENTITIES");
        assertMinLength(ProjectProposal.ENTITIES, 150,
                "ENTITIES should define multiple entities with properties");
    }

    @Test
    @DisplayName("Entity relationships are described")
    void entityRelationshipsDescribed() {
        assertNotBlank(ProjectProposal.ENTITY_RELATIONSHIPS, "ENTITY_RELATIONSHIPS");
        assertMinLength(ProjectProposal.ENTITY_RELATIONSHIPS, 50,
                "ENTITY_RELATIONSHIPS should describe how entities connect");
    }

    // ==================== Interface Specifications ====================

    @Test
    @DisplayName("View interface is specified")
    void viewInterfaceSpecified() {
        assertNotBlank(ProjectProposal.VIEW_INTERFACE, "VIEW_INTERFACE");
        assertMinLength(ProjectProposal.VIEW_INTERFACE, 100,
                "VIEW_INTERFACE should list multiple methods");
    }

    @Test
    @DisplayName("Repository interfaces are specified")
    void repositoryInterfacesSpecified() {
        assertNotBlank(ProjectProposal.REPOSITORY_INTERFACES, "REPOSITORY_INTERFACES");
        assertMinLength(ProjectProposal.REPOSITORY_INTERFACES, 80,
                "REPOSITORY_INTERFACES should include custom query methods");
    }

    @Test
    @DisplayName("Other interfaces are considered")
    void otherInterfacesConsidered() {
        assertNotBlank(ProjectProposal.OTHER_INTERFACES, "OTHER_INTERFACES");
        // Either defines interfaces or explains why none are needed
    }

    // ==================== User Operations ====================

    @Test
    @DisplayName("User operations are listed")
    void userOperationsListed() {
        assertNotBlank(ProjectProposal.USER_OPERATIONS, "USER_OPERATIONS");
        assertMinLength(ProjectProposal.USER_OPERATIONS, 300,
                "USER_OPERATIONS should describe at least 5 operations in detail");
    }

    // ==================== Architecture ====================

    @Test
    @DisplayName("Architecture is diagrammed")
    void architectureDiagrammed() {
        assertNotBlank(ProjectProposal.ARCHITECTURE_DIAGRAM, "ARCHITECTURE_DIAGRAM");
        assertMinLength(ProjectProposal.ARCHITECTURE_DIAGRAM, 150,
                "ARCHITECTURE_DIAGRAM should show layers and connections");
    }

    // ==================== Testing Strategy ====================

    @Test
    @DisplayName("Testing approach is described")
    void testingApproachDescribed() {
        assertNotBlank(ProjectProposal.TESTING_APPROACH, "TESTING_APPROACH");
        String answer = ProjectProposal.TESTING_APPROACH.toLowerCase();
        assertTrue(
                answer.contains("mock") || answer.contains("test") || answer.contains("memory"),
                "TESTING_APPROACH should mention mocks or test strategy");
    }

    // ==================== Scope and Timeline ====================

    @Test
    @DisplayName("MVP features are defined")
    void mvpFeaturesDefined() {
        assertNotBlank(ProjectProposal.MVP_FEATURES, "MVP_FEATURES");
        assertMinLength(ProjectProposal.MVP_FEATURES, 50,
                "MVP_FEATURES should list core features");
    }

    @Test
    @DisplayName("Stretch goals are listed")
    void stretchGoalsListed() {
        assertNotBlank(ProjectProposal.STRETCH_GOALS, "STRETCH_GOALS");
    }

    @Test
    @DisplayName("Risks are identified")
    void risksIdentified() {
        assertNotBlank(ProjectProposal.RISKS_AND_CHALLENGES, "RISKS_AND_CHALLENGES");
    }

    // ==================== Helper Methods ====================

    private void assertNotBlank(String value, String fieldName) {
        assertNotNull(value, fieldName + " should not be null");
        assertFalse(value.isBlank(),
                fieldName + " should not be blank - fill in your proposal");
    }

    private void assertMinLength(String value, int minLength, String message) {
        assertTrue(value.length() >= minLength, message);
    }
}
