package dk.viprogram.week08;

import java.util.List;

/**
 * The View interface for a search application.
 *
 * This interface defines what a search view can do:
 * - Display search results
 * - Show messages and errors
 * - Capture user input
 *
 * Different implementations can provide console or GUI interfaces.
 */
public interface SearchView {

    /**
     * Displays a list of search results.
     *
     * @param results the results to display
     * @param <T>     the type of results
     */
    <T> void displayResults(List<T> results);

    /**
     * Displays a message indicating no results were found.
     */
    void displayNoResults();

    /**
     * Displays the total count of results.
     *
     * @param count the number of results
     */
    void displayResultCount(int count);

    /**
     * Displays a general message.
     *
     * @param message the message to display
     */
    void displayMessage(String message);

    /**
     * Displays an error message.
     *
     * @param error the error message
     */
    void displayError(String error);

    /**
     * Displays a loading indicator.
     *
     * @param loading true to show loading, false to hide
     */
    void showLoading(boolean loading);

    /**
     * Sets the handler to call when the user submits a search.
     * This is the event-driven pattern for GUI applications.
     *
     * @param handler the search handler
     */
    void setSearchHandler(SearchHandler handler);

    /**
     * Functional interface for handling search submissions.
     */
    @FunctionalInterface
    interface SearchHandler {
        void onSearch(String query);
    }
}
