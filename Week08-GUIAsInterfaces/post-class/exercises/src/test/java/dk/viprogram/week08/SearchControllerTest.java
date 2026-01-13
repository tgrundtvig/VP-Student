package dk.viprogram.week08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for SearchController using MockSearchView.
 *
 * These tests verify the Controller logic without any GUI.
 */
@DisplayName("SearchController")
class SearchControllerTest {

    private SearchModel model;
    private MockSearchView mockView;
    private SearchController controller;

    @BeforeEach
    void setUp() {
        model = new SearchModel();
        mockView = new MockSearchView();
        controller = new SearchController(model, mockView);
    }

    @Nested
    @DisplayName("Initialization")
    class InitializationTests {

        @Test
        @DisplayName("displays welcome message on initialize")
        void displaysWelcomeMessage() {
            controller.initialize();

            assertTrue(mockView.wasMessageDisplayed("Welcome"));
        }

        @Test
        @DisplayName("shows all products on initialize")
        void showsAllProductsOnInitialize() {
            controller.initialize();

            assertEquals(model.getProductCount(), mockView.getLastResultCount());
            assertEquals(1, mockView.getResultDisplayCount());
        }
    }

    @Nested
    @DisplayName("Search Handling")
    class SearchHandlingTests {

        @Test
        @DisplayName("search returns matching products")
        void searchReturnsMatching() {
            controller.initialize();
            mockView.reset();

            mockView.simulateSearch("laptop");

            assertTrue(mockView.getLastResultCount() > 0);
            assertFalse(mockView.wasNoResultsDisplayed());
        }

        @Test
        @DisplayName("search shows no results for non-matching query")
        void searchShowsNoResults() {
            controller.initialize();
            mockView.reset();

            mockView.simulateSearch("xyznonexistent");

            assertTrue(mockView.wasNoResultsDisplayed());
        }

        @Test
        @DisplayName("empty search returns all products")
        void emptySearchReturnsAll() {
            controller.initialize();
            mockView.reset();

            mockView.simulateSearch("");

            assertEquals(model.getProductCount(), mockView.getLastResultCount());
        }

        @Test
        @DisplayName("search is case insensitive")
        void searchIsCaseInsensitive() {
            controller.initialize();
            mockView.reset();

            mockView.simulateSearch("LAPTOP");

            assertTrue(mockView.getLastResultCount() > 0);
        }
    }

    @Nested
    @DisplayName("View Updates")
    class ViewUpdateTests {

        @Test
        @DisplayName("displays result count")
        void displaysResultCount() {
            controller.initialize();
            mockView.reset();

            mockView.simulateSearch("keyboard");

            assertTrue(mockView.getLastResultCount() >= 0);
        }
    }
}
