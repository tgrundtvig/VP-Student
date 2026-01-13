package dk.viprogram.week08;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX Application entry point for the Search application.
 *
 * Run this class to launch the GUI version.
 * The console version can be run with ConsoleSearchApp.
 */
public class SearchApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create MVC components
        SearchModel model = new SearchModel();
        JavaFXSearchView view = new JavaFXSearchView();
        SearchController controller = new SearchController(model, view);

        // Initialize the controller (loads initial data)
        controller.initialize();

        // Set up the stage
        Scene scene = new Scene(view.getRoot(), 500, 600);
        primaryStage.setTitle("Product Search");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
