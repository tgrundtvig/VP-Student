package dk.viprogram.week08.memory;

/**
 * Console version of the Memory Card Game.
 *
 * This demonstrates the View-as-interface pattern:
 * - Same Model and Controller work with Console View
 * - No GUI dependencies required
 *
 * Run with:
 *   mvn compile exec:java -Dexec.mainClass="dk.viprogram.week08.memory.ConsoleMemoryApp"
 */
public class ConsoleMemoryApp {

    public static void main(String[] args) {
        // Create Model, View, Controller
        MemoryGameModel model = new SimpleMemoryGameModel();
        ConsoleMemoryGameView view = new ConsoleMemoryGameView();
        MemoryGameController controller = new MemoryGameController(model, view);

        // Start the game - view will prompt for grid size
        view.start();
    }
}
