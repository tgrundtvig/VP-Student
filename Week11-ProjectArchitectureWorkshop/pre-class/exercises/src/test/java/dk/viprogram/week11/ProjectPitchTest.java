package dk.viprogram.week11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to verify that the project pitch is complete and well-structured.
 *
 * These tests check that you have filled in all required fields with
 * sufficient detail to present a coherent 3-minute pitch.
 */
class ProjectPitchTest {

    private ProjectPitch pitch;

    @BeforeEach
    void setUp() {
        pitch = new ProjectPitch();
    }

    // ==================== Project Identity ====================

    @Test
    @DisplayName("Project has a non-empty title")
    void projectHasTitle() {
        assertNotNull(pitch.getProjectTitle(), "Project title should not be null");
        assertFalse(pitch.getProjectTitle().isBlank(),
                "Project title should not be blank - give your project a name");
    }

    @Test
    @DisplayName("Project has a meaningful description")
    void projectHasDescription() {
        assertNotNull(pitch.getDescription(), "Description should not be null");
        assertFalse(pitch.getDescription().isBlank(),
                "Description should not be blank - explain what your project does");
        assertTrue(pitch.getDescription().length() >= 50,
                "Description should be at least 50 characters - be specific about " +
                "what your project does, who it is for, and what problem it solves");
    }

    // ==================== Entities ====================

    @Test
    @DisplayName("Project defines at least 2 main entities")
    void projectHasEntities() {
        assertNotNull(pitch.getMainEntities(), "Entity list should not be null");
        assertTrue(pitch.getMainEntities().size() >= 2,
                "You should define at least 2 main entities (records). " +
                "Most projects need 2-4 entities.");
    }

    @Test
    @DisplayName("Each entity has a name and description")
    void entitiesHaveDescriptions() {
        for (String entity : pitch.getMainEntities()) {
            assertFalse(entity.isBlank(),
                    "Entity entries should not be blank");
            assertTrue(entity.length() >= 10,
                    "Entity '" + entity + "' needs more detail. " +
                    "Include name and description, e.g. 'Recipe - A cooking recipe with...'");
        }
    }

    // ==================== Interfaces ====================

    @Test
    @DisplayName("Project defines at least 2 core interfaces")
    void projectHasInterfaces() {
        assertNotNull(pitch.getCoreInterfaces(), "Interface list should not be null");
        assertTrue(pitch.getCoreInterfaces().size() >= 2,
                "You should define at least 2 core interfaces. " +
                "At minimum you need a View interface and a Repository interface.");
    }

    @Test
    @DisplayName("Each interface has a name and purpose")
    void interfacesHaveDescriptions() {
        for (String iface : pitch.getCoreInterfaces()) {
            assertFalse(iface.isBlank(),
                    "Interface entries should not be blank");
            assertTrue(iface.length() >= 10,
                    "Interface '" + iface + "' needs more detail. " +
                    "Include name and purpose, e.g. 'RecipeView - Displays recipes...'");
        }
    }

    // ==================== Patterns ====================

    @Test
    @DisplayName("Project identifies at least 1 design pattern")
    void projectUsesPatterns() {
        assertNotNull(pitch.getPatternsUsed(), "Patterns list should not be null");
        assertTrue(pitch.getPatternsUsed().size() >= 1,
                "You should identify at least 1 design pattern you will use. " +
                "Every project should use MVC and Repository at minimum.");
    }

    @Test
    @DisplayName("Each pattern has an explanation of how it applies")
    void patternsHaveExplanations() {
        for (String pattern : pitch.getPatternsUsed()) {
            assertFalse(pattern.isBlank(),
                    "Pattern entries should not be blank");
            assertTrue(pattern.length() >= 15,
                    "Pattern '" + pattern + "' needs more detail. " +
                    "Explain how the pattern applies, e.g. 'MVC - TaskView handles display...'");
        }
    }

    // ==================== Formatted Output ====================

    @Test
    @DisplayName("Formatted pitch produces readable output")
    void formattedPitchIsNotEmpty() {
        String formatted = pitch.getFormattedPitch();
        assertNotNull(formatted, "Formatted pitch should not be null");
        assertFalse(formatted.isBlank(),
                "Formatted pitch should not be blank - implement getFormattedPitch()");
    }

    @Test
    @DisplayName("Formatted pitch contains the project title")
    void formattedPitchContainsTitle() {
        String formatted = pitch.getFormattedPitch();
        if (!pitch.getProjectTitle().isBlank()) {
            assertTrue(formatted.contains(pitch.getProjectTitle()),
                    "Formatted pitch should include the project title");
        }
    }

    @Test
    @DisplayName("Formatted pitch mentions entities")
    void formattedPitchMentionsEntities() {
        String formatted = pitch.getFormattedPitch().toLowerCase();
        assertTrue(formatted.contains("entit") || formatted.contains("record") ||
                        formatted.contains("model") || formatted.contains("data"),
                "Formatted pitch should include a section about entities");
    }

    @Test
    @DisplayName("Formatted pitch mentions interfaces")
    void formattedPitchMentionsInterfaces() {
        String formatted = pitch.getFormattedPitch().toLowerCase();
        assertTrue(formatted.contains("interface") || formatted.contains("contract") ||
                        formatted.contains("behavior"),
                "Formatted pitch should include a section about interfaces");
    }
}
