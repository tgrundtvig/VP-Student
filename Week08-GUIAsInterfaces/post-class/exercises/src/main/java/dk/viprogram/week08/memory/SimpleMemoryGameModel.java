package dk.viprogram.week08.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A simple implementation of the Memory game model.
 *
 * This class manages the game state including:
 * - The grid of cards
 * - Which cards are currently flipped
 * - Match checking logic
 * - Game statistics (moves, matches, time)
 */
public class SimpleMemoryGameModel implements MemoryGameModel {

    /**
     * Pool of symbols for card faces.
     * Uses numbers 1-99 for maximum compatibility.
     * Supports up to 99 unique pairs.
     */
    private static final String[] SYMBOL_POOL;

    static {
        SYMBOL_POOL = new String[99];
        for (int i = 0; i < 99; i++) {
            SYMBOL_POOL[i] = String.valueOf(i + 1);
        }
    }

    private List<List<Card>> grid;
    private int rows;
    private int cols;
    private List<Position> flippedCards;
    private int moves;
    private int matchesFound;
    private long startTime;
    private final Random random;

    /**
     * Creates a new game model with default random.
     */
    public SimpleMemoryGameModel() {
        this(new Random());
    }

    /**
     * Creates a new game model with the specified random.
     * Useful for testing with a seeded random.
     */
    public SimpleMemoryGameModel(Random random) {
        this.random = random;
        this.flippedCards = new ArrayList<>();
    }

    @Override
    public void newGame(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive");
        }
        if ((rows * cols) % 2 != 0) {
            throw new IllegalArgumentException("Grid size must be even to create pairs");
        }

        int numPairs = (rows * cols) / 2;
        if (numPairs > SYMBOL_POOL.length) {
            throw new IllegalArgumentException(
                    "Grid too large. Maximum " + (SYMBOL_POOL.length * 2) + " cards supported.");
        }

        this.rows = rows;
        this.cols = cols;
        this.moves = 0;
        this.matchesFound = 0;
        this.flippedCards = new ArrayList<>();
        this.startTime = System.currentTimeMillis();

        // Select random symbols for this game
        List<String> availableSymbols = new ArrayList<>(List.of(SYMBOL_POOL));
        Collections.shuffle(availableSymbols, random);
        List<String> selectedSymbols = availableSymbols.subList(0, numPairs);

        // Create pairs of cards
        List<Card> allCards = new ArrayList<>();
        int cardId = 0;
        for (String symbol : selectedSymbols) {
            // Create two cards with the same symbol
            allCards.add(new Card(cardId++, symbol, false, false));
            allCards.add(new Card(cardId++, symbol, false, false));
        }

        // Shuffle and arrange into grid
        Collections.shuffle(allCards, random);
        grid = new ArrayList<>();
        int index = 0;
        for (int r = 0; r < rows; r++) {
            List<Card> row = new ArrayList<>();
            for (int c = 0; c < cols; c++) {
                row.add(allCards.get(index++));
            }
            grid.add(row);
        }
    }

    @Override
    public Card getCard(Position pos) {
        if (!pos.isValid(rows, cols)) {
            throw new IndexOutOfBoundsException("Position out of bounds: " + pos);
        }
        return grid.get(pos.row()).get(pos.col());
    }

    @Override
    public List<List<Card>> getGrid() {
        return Collections.unmodifiableList(grid);
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public boolean flipCard(Position pos) {
        // Validate position
        if (!pos.isValid(rows, cols)) {
            return false;
        }

        // Can't flip more than 2 cards at once
        if (flippedCards.size() >= 2) {
            return false;
        }

        Card card = getCard(pos);

        // Can't flip already face-up or matched cards
        if (card.faceUp() || card.matched()) {
            return false;
        }

        // Flip the card
        setCard(pos, card.flip());
        flippedCards.add(pos);

        return true;
    }

    @Override
    public List<Position> getFlippedCards() {
        return new ArrayList<>(flippedCards);
    }

    @Override
    public MatchResult checkMatch() {
        // TODO: Implement this method!
        //
        // This method should:
        // 1. Return NEED_MORE_CARDS if less than 2 cards are flipped
        // 2. Get both flipped cards and compare their symbols
        // 3. If symbols match:
        //    - Mark both cards as matched (use card.markMatched())
        //    - Increment matchesFound
        //    - Clear flippedCards list
        //    - Increment moves
        //    - Return MATCH
        // 4. If symbols don't match:
        //    - Flip both cards back down (use card.flipDown())
        //    - Clear flippedCards list
        //    - Increment moves
        //    - Return NO_MATCH
        //
        // Hint: Use setCard(position, card) to update cards in the grid
        // Hint: Use getCard(position) to retrieve cards from the grid

        throw new UnsupportedOperationException("TODO: Implement checkMatch()");
    }

    @Override
    public int getMoves() {
        return moves;
    }

    @Override
    public int getMatchesFound() {
        return matchesFound;
    }

    @Override
    public int getTotalPairs() {
        return (rows * cols) / 2;
    }

    @Override
    public boolean isGameOver() {
        return matchesFound == getTotalPairs();
    }

    @Override
    public long getElapsedTimeSeconds() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    /**
     * Helper method to update a card in the grid.
     */
    private void setCard(Position pos, Card card) {
        grid.get(pos.row()).set(pos.col(), card);
    }
}
