package dk.viprogram.week08;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * A console-based implementation of SearchView.
 *
 * This implementation is provided as a reference.
 * Study it to understand how a View implements the interface.
 */
public class ConsoleSearchView implements SearchView {

    private final Scanner scanner;
    private final PrintStream out;
    private SearchHandler searchHandler;

    public ConsoleSearchView() {
        this(new Scanner(System.in), System.out);
    }

    public ConsoleSearchView(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    @Override
    public <T> void displayResults(List<T> results) {
        out.println("\n--- Results ---");
        for (int i = 0; i < results.size(); i++) {
            out.printf("%d. %s%n", i + 1, results.get(i));
        }
        out.println("---------------\n");
    }

    @Override
    public void displayNoResults() {
        out.println("\nNo results found.\n");
    }

    @Override
    public void displayResultCount(int count) {
        out.printf("Found %d item(s)%n", count);
    }

    @Override
    public void displayMessage(String message) {
        out.println(message);
    }

    @Override
    public void displayError(String error) {
        out.println("ERROR: " + error);
    }

    @Override
    public void showLoading(boolean loading) {
        if (loading) {
            out.println("Searching...");
        }
    }

    @Override
    public void setSearchHandler(SearchHandler handler) {
        this.searchHandler = handler;
    }

    /**
     * Runs an interactive search loop.
     * This is the console-specific interaction pattern.
     */
    public void runInteractiveLoop() {
        out.println("Type a search query and press Enter. Type 'quit' to exit.\n");

        while (true) {
            out.print("Search: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) {
                out.println("Goodbye!");
                break;
            }

            if (searchHandler != null) {
                searchHandler.onSearch(input);
            }
        }
    }
}
