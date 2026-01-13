package dk.viprogram.week08;

/**
 * Console application entry point for the Search application.
 *
 * Run this class to test the application without JavaFX.
 * Uses the SAME Model and Controller - only the View is different!
 */
public class ConsoleSearchApp {

    public static void main(String[] args) {
        // Create MVC components - same as GUI, different view!
        SearchModel model = new SearchModel();
        ConsoleSearchView view = new ConsoleSearchView();
        SearchController controller = new SearchController(model, view);

        // Initialize the controller (loads initial data)
        controller.initialize();

        // Run the console interaction loop
        view.runInteractiveLoop();
    }
}
