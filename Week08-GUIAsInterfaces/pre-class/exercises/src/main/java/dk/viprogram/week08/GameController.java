package dk.viprogram.week08;

/**
 * The Controller for a number guessing game.
 *
 * The Controller:
 * - Receives input from the View
 * - Updates the Model based on input
 * - Tells the View what to display
 *
 * Notice: The Controller uses the GameView INTERFACE, not a concrete class.
 * This means we can use any implementation of GameView (console, GUI, mock).
 */
public class GameController {

    private final GameModel model;
    private final GameView view;

    /**
     * Creates a controller with the given model and view.
     *
     * @param model the game model (data and logic)
     * @param view  the game view (display and input)
     */
    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Starts the game and runs until completion.
     * This is the main game loop for console-style input.
     */
    public void startGame() {
        view.displayMessage("Welcome to the Number Guessing Game!");
        view.displayMessage("I'm thinking of a number. You have " +
                model.getMaxAttempts() + " attempts to guess it.");

        while (!model.isGameOver()) {
            playTurn();
        }

        view.displayGameOver(model.hasWon(), model.getSecretNumber(), model.getAttempts());
    }

    /**
     * Plays a single turn of the game.
     * Gets input from view, processes it, updates view.
     */
    public void playTurn() {
        view.displayAttempts(model.getAttempts());

        String input = view.promptForGuess(
                "Enter your guess (" + model.getRemainingAttempts() + " attempts remaining)");

        int guess;
        try {
            guess = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            view.displayError("Please enter a valid number.");
            return;
        }

        processGuess(guess);
    }

    /**
     * Processes a guess and updates the view accordingly.
     *
     * @param guess the player's guess
     */
    public void processGuess(int guess) {
        GameModel.GuessResult result = model.makeGuess(guess);

        switch (result) {
            case TOO_LOW -> view.displayHint("Too low! Try a higher number.");
            case TOO_HIGH -> view.displayHint("Too high! Try a lower number.");
            case CORRECT -> view.displayHint("Correct! You guessed it!");
            case GAME_OVER -> view.displayHint("Game over! No more attempts.");
        }
    }
}
