package dk.viprogram.week08.memory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Memory Card Game.
 *
 * These tests demonstrate how interface-first design enables:
 * - Testing Model logic without any View
 * - Testing Controller with a mock View
 * - Fast, reliable, deterministic tests
 */
class MemoryGameTest {

    // ==================== Card Tests ====================

    @Nested
    @DisplayName("Card Tests")
    class CardTests {

        @Test
        @DisplayName("Card displays symbol when face up")
        void cardDisplaysFaceUp() {
            Card card = new Card(1, "🍎", true, false);
            assertEquals("🍎", card.display());
        }

        @Test
        @DisplayName("Card displays back when face down")
        void cardDisplaysFaceDown() {
            Card card = new Card(1, "🍎", false, false);
            assertEquals(Card.CARD_BACK, card.display());
        }

        @Test
        @DisplayName("Card displays symbol when matched (styled differently)")
        void cardDisplaysMatched() {
            Card card = new Card(1, "🍎", true, true);
            assertEquals("🍎", card.display());
        }

        @Test
        @DisplayName("flip() returns face-up card")
        void flipReturnsFaceUp() {
            Card card = new Card(1, "🍎", false, false);
            Card flipped = card.flip();
            assertTrue(flipped.faceUp());
            assertEquals("🍎", flipped.symbol());
        }

        @Test
        @DisplayName("flipDown() returns face-down card")
        void flipDownReturnsFaceDown() {
            Card card = new Card(1, "🍎", true, false);
            Card flipped = card.flipDown();
            assertFalse(flipped.faceUp());
        }

        @Test
        @DisplayName("markMatched() returns matched card")
        void markMatchedReturnsMatched() {
            Card card = new Card(1, "🍎", true, false);
            Card matched = card.markMatched();
            assertTrue(matched.matched());
        }
    }

    // ==================== Position Tests ====================

    @Nested
    @DisplayName("Position Tests")
    class PositionTests {

        @Test
        @DisplayName("Valid position is recognized")
        void validPosition() {
            Position pos = new Position(1, 2);
            assertTrue(pos.isValid(4, 4));
        }

        @Test
        @DisplayName("Invalid position (negative row) is recognized")
        void invalidNegativeRow() {
            Position pos = new Position(-1, 2);
            assertFalse(pos.isValid(4, 4));
        }

        @Test
        @DisplayName("Invalid position (row too large) is recognized")
        void invalidLargeRow() {
            Position pos = new Position(4, 2);
            assertFalse(pos.isValid(4, 4));
        }

        @Test
        @DisplayName("toString shows row and col")
        void toStringFormat() {
            Position pos = new Position(2, 3);
            assertEquals("(2, 3)", pos.toString());
        }
    }

    // ==================== Model Tests ====================

    @Nested
    @DisplayName("SimpleMemoryGameModel Tests")
    class ModelTests {

        private SimpleMemoryGameModel model;

        @BeforeEach
        void setUp() {
            // Use seeded random for deterministic tests
            model = new SimpleMemoryGameModel(new Random(42));
        }

        @Test
        @DisplayName("newGame creates correct grid size")
        void newGameCreatesCorrectSize() {
            model.newGame(4, 4);
            assertEquals(4, model.getRows());
            assertEquals(4, model.getCols());
            assertEquals(8, model.getTotalPairs());
        }

        @Test
        @DisplayName("newGame throws on odd total")
        void newGameThrowsOnOddTotal() {
            assertThrows(IllegalArgumentException.class, () ->
                    model.newGame(3, 3));
        }

        @Test
        @DisplayName("newGame throws on negative dimensions")
        void newGameThrowsOnNegative() {
            assertThrows(IllegalArgumentException.class, () ->
                    model.newGame(-1, 4));
        }

        @Test
        @DisplayName("All cards start face down")
        void allCardsFaceDown() {
            model.newGame(2, 2);
            for (int r = 0; r < 2; r++) {
                for (int c = 0; c < 2; c++) {
                    Card card = model.getCard(new Position(r, c));
                    assertFalse(card.faceUp(), "Card at (" + r + "," + c + ") should be face down");
                    assertFalse(card.matched());
                }
            }
        }

        @Test
        @DisplayName("Grid contains pairs of symbols")
        void gridContainsPairs() {
            model.newGame(2, 2);
            List<List<Card>> grid = model.getGrid();

            // Collect all symbols
            java.util.Map<String, Integer> symbolCount = new java.util.HashMap<>();
            for (List<Card> row : grid) {
                for (Card card : row) {
                    symbolCount.merge(card.symbol(), 1, Integer::sum);
                }
            }

            // Each symbol should appear exactly twice
            for (int count : symbolCount.values()) {
                assertEquals(2, count, "Each symbol should appear twice");
            }
        }

        @Test
        @DisplayName("flipCard flips face-down card")
        void flipCardFlipsFaceDown() {
            model.newGame(2, 2);
            Position pos = new Position(0, 0);

            boolean result = model.flipCard(pos);

            assertTrue(result);
            assertTrue(model.getCard(pos).faceUp());
        }

        @Test
        @DisplayName("flipCard returns false for face-up card")
        void flipCardFailsForFaceUp() {
            model.newGame(2, 2);
            Position pos = new Position(0, 0);

            model.flipCard(pos);  // First flip succeeds
            boolean result = model.flipCard(pos);  // Second flip should fail

            assertFalse(result);
        }

        @Test
        @DisplayName("Cannot flip more than 2 cards")
        void cannotFlipMoreThanTwo() {
            model.newGame(2, 2);

            assertTrue(model.flipCard(new Position(0, 0)));
            assertTrue(model.flipCard(new Position(0, 1)));
            assertFalse(model.flipCard(new Position(1, 0)));

            assertEquals(2, model.getFlippedCards().size());
        }

        @Test
        @DisplayName("getFlippedCards tracks flipped positions")
        void getFlippedCardsTracksPositions() {
            model.newGame(2, 2);

            model.flipCard(new Position(0, 0));
            assertEquals(1, model.getFlippedCards().size());

            model.flipCard(new Position(1, 1));
            assertEquals(2, model.getFlippedCards().size());
        }

        @Test
        @DisplayName("checkMatch returns NEED_MORE_CARDS with one card")
        void checkMatchNeedsMoreCards() {
            model.newGame(2, 2);
            model.flipCard(new Position(0, 0));

            assertEquals(MemoryGameModel.MatchResult.NEED_MORE_CARDS,
                    model.checkMatch());
        }

        @Test
        @DisplayName("Initial state has zero moves and matches")
        void initialStateZeroMoves() {
            model.newGame(2, 2);
            assertEquals(0, model.getMoves());
            assertEquals(0, model.getMatchesFound());
            assertFalse(model.isGameOver());
        }

        @Test
        @DisplayName("getCard throws for invalid position")
        void getCardThrowsForInvalid() {
            model.newGame(2, 2);
            assertThrows(IndexOutOfBoundsException.class, () ->
                    model.getCard(new Position(5, 5)));
        }
    }

    // ==================== Integration Tests (Model + Controller + MockView) ====================

    @Nested
    @DisplayName("Integration Tests with MockView")
    class IntegrationTests {

        private SimpleMemoryGameModel model;
        private MockMemoryGameView view;
        private MemoryGameController controller;

        @BeforeEach
        void setUp() {
            model = new SimpleMemoryGameModel(new Random(42));
            view = new MockMemoryGameView();
            controller = new MemoryGameController(model, view);
        }

        @Test
        @DisplayName("New game displays grid and stats")
        void newGameDisplaysGridAndStats() {
            view.simulateNewGame(2, 2);

            assertFalse(view.getDisplayedGrids().isEmpty());
            assertFalse(view.getStatsHistory().isEmpty());
        }

        @Test
        @DisplayName("Card click updates display")
        void cardClickUpdatesDisplay() {
            view.simulateNewGame(2, 2);
            int gridCountBefore = view.getDisplayedGrids().size();

            view.simulateCardClick(new Position(0, 0));

            assertTrue(view.getDisplayedGrids().size() > gridCountBefore);
        }

        @Test
        @DisplayName("Mock view records all messages")
        void mockViewRecordsMessages() {
            view.simulateNewGame(2, 2);

            assertFalse(view.getDisplayedMessages().isEmpty());
        }
    }
}
