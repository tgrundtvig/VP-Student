package dk.viprogram.week08.memory;

import java.util.List;

/**
 * The Model interface for a Memory card game.
 *
 * The Model contains:
 * - Game data (grid of cards, score, time)
 * - Game logic (flipping cards, checking matches)
 *
 * Notice: The Model knows NOTHING about how the game is displayed.
 * It works the same whether the View is console, JavaFX, or a test mock.
 */
public interface MemoryGameModel {

    // ==================== Game Setup ====================

    /**
     * Starts a new game with the specified grid size.
     * The grid will contain (rows * cols / 2) pairs of cards.
     *
     * @param rows number of rows (must be positive)
     * @param cols number of columns (must be positive)
     * @throws IllegalArgumentException if rows * cols is odd (can't make pairs)
     */
    void newGame(int rows, int cols);

    // ==================== Grid Access ====================

    /**
     * Returns the card at the specified position.
     *
     * @param pos the position to query
     * @return the card at that position
     * @throws IndexOutOfBoundsException if position is invalid
     */
    Card getCard(Position pos);

    /**
     * Returns the entire grid of cards.
     * The outer list is rows, inner list is columns.
     *
     * @return the grid as a list of lists
     */
    List<List<Card>> getGrid();

    /**
     * Returns the number of rows in the current game.
     */
    int getRows();

    /**
     * Returns the number of columns in the current game.
     */
    int getCols();

    // ==================== Card Actions ====================

    /**
     * Attempts to flip the card at the given position.
     *
     * A flip is valid if:
     * - The position is within the grid
     * - The card is not already face up
     * - The card is not already matched
     * - There are less than 2 cards currently flipped
     *
     * @param pos the position of the card to flip
     * @return true if the flip was successful, false otherwise
     */
    boolean flipCard(Position pos);

    /**
     * Returns the positions of currently flipped (face up, unmatched) cards.
     *
     * @return list of 0, 1, or 2 positions
     */
    List<Position> getFlippedCards();

    /**
     * Checks if the two currently flipped cards match.
     * If they match, marks them as matched.
     * If they don't match, flips them back face down.
     *
     * @return the result of the match check
     */
    MatchResult checkMatch();

    /**
     * The result of checking for a match.
     */
    enum MatchResult {
        /** Less than 2 cards are flipped - can't check yet */
        NEED_MORE_CARDS,
        /** The two flipped cards match! */
        MATCH,
        /** The two flipped cards don't match */
        NO_MATCH
    }

    // ==================== Game State ====================

    /**
     * Returns the number of moves (pairs of flips) made.
     */
    int getMoves();

    /**
     * Returns the number of pairs successfully matched.
     */
    int getMatchesFound();

    /**
     * Returns the total number of pairs in the game.
     */
    int getTotalPairs();

    /**
     * Returns true if all pairs have been matched.
     */
    boolean isGameOver();

    /**
     * Returns the elapsed time in seconds since the game started.
     */
    long getElapsedTimeSeconds();
}
