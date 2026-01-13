package dk.viprogram.week08;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of SearchView for testing.
 *
 * Records all method calls for verification in tests.
 */
public class MockSearchView implements SearchView {

    private final List<String> messages = new ArrayList<>();
    private final List<String> errors = new ArrayList<>();
    private final List<List<?>> displayedResults = new ArrayList<>();
    private int lastResultCount = -1;
    private boolean noResultsDisplayed = false;
    private boolean loadingShown = false;
    private SearchHandler searchHandler;

    @Override
    public <T> void displayResults(List<T> results) {
        displayedResults.add(new ArrayList<>(results));
    }

    @Override
    public void displayNoResults() {
        noResultsDisplayed = true;
    }

    @Override
    public void displayResultCount(int count) {
        lastResultCount = count;
    }

    @Override
    public void displayMessage(String message) {
        messages.add(message);
    }

    @Override
    public void displayError(String error) {
        errors.add(error);
    }

    @Override
    public void showLoading(boolean loading) {
        loadingShown = loading;
    }

    @Override
    public void setSearchHandler(SearchHandler handler) {
        this.searchHandler = handler;
    }

    // --- Test helpers ---

    /**
     * Simulates a user performing a search.
     */
    public void simulateSearch(String query) {
        if (searchHandler != null) {
            searchHandler.onSearch(query);
        }
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }

    public boolean wasMessageDisplayed(String message) {
        return messages.stream().anyMatch(m -> m.contains(message));
    }

    public boolean wasErrorDisplayed(String error) {
        return errors.stream().anyMatch(e -> e.contains(error));
    }

    public int getLastResultCount() {
        return lastResultCount;
    }

    public boolean wasNoResultsDisplayed() {
        return noResultsDisplayed;
    }

    public List<List<?>> getDisplayedResults() {
        return displayedResults;
    }

    public int getResultDisplayCount() {
        return displayedResults.size();
    }

    public void reset() {
        messages.clear();
        errors.clear();
        displayedResults.clear();
        lastResultCount = -1;
        noResultsDisplayed = false;
        loadingShown = false;
    }
}
