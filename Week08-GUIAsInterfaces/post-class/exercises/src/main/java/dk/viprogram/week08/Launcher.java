package dk.viprogram.week08;

/**
 * Launcher class for running the JavaFX application from IntelliJ.
 *
 * JavaFX applications that extend Application directly can have issues
 * when run from IDEs. This launcher class works around that problem.
 *
 * Run this class instead of SearchApp when using IntelliJ's Run button.
 */
public class Launcher {
    public static void main(String[] args) {
        SearchApp.main(args);
    }
}
