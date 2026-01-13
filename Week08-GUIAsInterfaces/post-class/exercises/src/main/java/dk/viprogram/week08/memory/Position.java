package dk.viprogram.week08.memory;

/**
 * Represents a position on the game grid.
 *
 * @param row the row index (0-based)
 * @param col the column index (0-based)
 */
public record Position(int row, int col) {

    /**
     * Checks if this position is valid for a grid of the given size.
     */
    public boolean isValid(int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
