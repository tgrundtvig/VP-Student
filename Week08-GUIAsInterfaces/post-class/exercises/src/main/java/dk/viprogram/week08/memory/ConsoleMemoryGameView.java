package dk.viprogram.week08.memory;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A console-based implementation of the Memory game view.
 *
 * This view displays the game in text mode, showing:
 * - A grid of cards with row/column labels
 * - Game statistics
 * - Prompts for user input
 *
 * Example display for a 4x4 grid:
 *
 *     0  1  2  3
 *   +--+--+--+--+
 * 0 |🂠|🂠|🂠|🂠|
 *   +--+--+--+--+
 * 1 |🂠|🍎|🍎|🂠|
 *   +--+--+--+--+
 * 2 |🂠|🂠|🂠|🂠|
 *   +--+--+--+--+
 * 3 |🂠|🂠|🂠|🂠|
 *   +--+--+--+--+
 *
 * Moves: 1 | Matches: 1/8 | Time: 0:15
 */
public class ConsoleMemoryGameView implements MemoryGameView {

    private final PrintStream out;
    private final Scanner scanner;
    private CardClickHandler cardClickHandler;
    private NewGameHandler newGameHandler;
    private boolean running;
    private int currentRows;
    private int currentCols;

    /**
     * Creates a console view with default System streams.
     */
    public ConsoleMemoryGameView() {
        this(System.out, new Scanner(System.in));
    }

    /**
     * Creates a console view with custom streams.
     * Useful for testing.
     */
    public ConsoleMemoryGameView(PrintStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
        this.running = false;
    }

    @Override
    public void displayGrid(List<List<Card>> grid) {
        // TODO: Implement this method!
        //
        // Display the grid like this example (for a 4x3 grid):
        //
        //     0  1  2  3
        //   +--+--+--+--+
        // 0 |🂠|🂠|🍎|🂠|
        //   +--+--+--+--+
        // 1 |🂠|🍎|🂠|🂠|
        //   +--+--+--+--+
        // 2 |🂠|🂠|🂠|🂠|
        //   +--+--+--+--+
        //
        // Steps:
        // 1. Print column headers (   0  1  2  3)
        // 2. For each row:
        //    a. Print separator line (+--+--+--+--+)
        //    b. Print row number and cards (0 |🂠|🂠|🍎|🂠|)
        // 3. Print final separator line
        //
        // Hints:
        // - Use card.display() to get what to show for each card
        // - grid.get(row).get(col) gives you the card at that position
        // - grid.size() gives the number of rows
        // - grid.get(0).size() gives the number of columns
        // - Use out.print() for same line, out.println() for new line

        throw new UnsupportedOperationException("TODO: Implement displayGrid()");
    }

    @Override
    public void displayStats(int moves, int matches, int totalPairs, long elapsedSeconds) {
        long minutes = elapsedSeconds / 60;
        long seconds = elapsedSeconds % 60;
        out.printf("Moves: %d | Matches: %d/%d | Time: %d:%02d%n",
                moves, matches, totalPairs, minutes, seconds);
    }

    @Override
    public void displayMessage(String message) {
        out.println(message);
    }

    @Override
    public void displayGameOver(int moves, long elapsedSeconds) {
        out.println();
        out.println("╔════════════════════════════════════╗");
        out.println("║       🎉 CONGRATULATIONS! 🎉       ║");
        out.println("╠════════════════════════════════════╣");
        long minutes = elapsedSeconds / 60;
        long seconds = elapsedSeconds % 60;
        out.printf("║  You won in %d moves!              ║%n", moves);
        out.printf("║  Time: %d:%02d                       ║%n", minutes, seconds);
        out.println("╚════════════════════════════════════╝");
        out.println();
    }

    @Override
    public void setCardClickHandler(CardClickHandler handler) {
        this.cardClickHandler = handler;
    }

    @Override
    public void setNewGameHandler(NewGameHandler handler) {
        this.newGameHandler = handler;
    }

    @Override
    public void start() {
        running = true;
        showWelcome();

        // Prompt for game size and start
        promptForNewGame();

        // Main game loop
        while (running) {
            promptForCardSelection();
        }
    }

    @Override
    public void scheduleAction(long delayMs, Runnable action) {
        // For console, we use a simple Timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                action.run();
                timer.cancel();
            }
        }, delayMs);
    }

    /**
     * Stops the game loop.
     */
    public void stop() {
        running = false;
    }

    // ==================== Private Helper Methods ====================

    private void showWelcome() {
        out.println();
        out.println("╔════════════════════════════════════╗");
        out.println("║       🎴 MEMORY CARD GAME 🎴       ║");
        out.println("╚════════════════════════════════════╝");
        out.println();
    }

    private void promptForNewGame() {
        out.println("Select grid size:");
        out.println("  1. 2x2 (Easy - 2 pairs)");
        out.println("  2. 4x4 (Medium - 8 pairs)");
        out.println("  3. 6x6 (Hard - 18 pairs)");
        out.println("  4. Custom size");
        out.print("Choice: ");

        String choice = scanner.nextLine().trim();
        int rows, cols;

        switch (choice) {
            case "1":
                rows = 2;
                cols = 2;
                break;
            case "2":
                rows = 4;
                cols = 4;
                break;
            case "3":
                rows = 6;
                cols = 6;
                break;
            case "4":
                rows = promptForInt("Rows (2-10): ", 2, 10);
                cols = promptForInt("Columns (2-10): ", 2, 10);
                // Ensure even total
                if ((rows * cols) % 2 != 0) {
                    out.println("Total cells must be even. Adding one column.");
                    cols++;
                }
                break;
            default:
                out.println("Invalid choice. Using 4x4.");
                rows = 4;
                cols = 4;
        }

        currentRows = rows;
        currentCols = cols;

        if (newGameHandler != null) {
            newGameHandler.onNewGame(rows, cols);
        }
    }

    private void promptForCardSelection() {
        out.println();
        out.print("Enter card position (row col) or 'q' to quit, 'n' for new game: ");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("q")) {
            running = false;
            out.println("Thanks for playing!");
            return;
        }

        if (input.equals("n")) {
            promptForNewGame();
            return;
        }

        try {
            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                out.println("Please enter two numbers: row col");
                return;
            }

            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            if (row < 0 || row >= currentRows || col < 0 || col >= currentCols) {
                out.println("Position out of bounds. Please try again.");
                return;
            }

            if (cardClickHandler != null) {
                cardClickHandler.onCardClick(new Position(row, col));
            }
        } catch (NumberFormatException e) {
            out.println("Please enter valid numbers.");
        }
    }

    private int promptForInt(String prompt, int min, int max) {
        while (true) {
            out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                out.printf("Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                out.println("Please enter a valid number.");
            }
        }
    }
}
