package dk.viprogram.week08.memory;

/**
 * Launcher class for running MemoryApp from IntelliJ IDEA.
 *
 * IntelliJ has issues with classes that extend Application directly.
 * This launcher works around that by delegating to MemoryApp.main().
 *
 * Usage:
 *   Run this class from IntelliJ instead of MemoryApp.
 */
public class MemoryLauncher {

    public static void main(String[] args) {
        MemoryApp.main(args);
    }
}
