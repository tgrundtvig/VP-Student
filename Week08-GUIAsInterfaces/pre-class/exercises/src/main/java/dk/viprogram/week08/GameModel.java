package dk.viprogram.week08;

import java.util.Random;

/**
 * The Model for a number guessing game.
 *
 * The Model contains:
 * - Game data (secret number, attempts, game state)
 * - Business logic (checking guesses, determining win/loss)
 *
 * Notice: The Model knows NOTHING about how the game is displayed.
 * It has no references to View or any UI classes.
 */
public class GameModel {

    private final int secretNumber;
    private final int maxAttempts;
    private int attempts;
    private boolean gameOver;
    private boolean won;

    /**
     * Creates a new game with a random secret number.
     *
     * @param minNumber   the minimum possible number (inclusive)
     * @param maxNumber   the maximum possible number (inclusive)
     * @param maxAttempts the maximum number of guesses allowed
     */
    public GameModel(int minNumber, int maxNumber, int maxAttempts) {
        this(minNumber, maxNumber, maxAttempts, new Random());
    }

    /**
     * Creates a new game with a specific Random instance.
     * Useful for testing with a seeded random.
     */
    public GameModel(int minNumber, int maxNumber, int maxAttempts, Random random) {
        this.secretNumber = random.nextInt(maxNumber - minNumber + 1) + minNumber;
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.gameOver = false;
        this.won = false;
    }

    /**
     * Creates a new game with a known secret number.
     * Useful for testing.
     */
    public GameModel(int secretNumber, int maxAttempts) {
        this.secretNumber = secretNumber;
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.gameOver = false;
        this.won = false;
    }

    /**
     * Represents the result of a guess.
     */
    public enum GuessResult {
        TOO_LOW,
        TOO_HIGH,
        CORRECT,
        GAME_OVER
    }

    /**
     * Makes a guess and returns the result.
     *
     * @param guess the player's guess
     * @return the result of the guess
     */
    public GuessResult makeGuess(int guess) {
        if (gameOver) {
            return GuessResult.GAME_OVER;
        }

        attempts++;

        if (guess == secretNumber) {
            gameOver = true;
            won = true;
            return GuessResult.CORRECT;
        }

        if (attempts >= maxAttempts) {
            gameOver = true;
            won = false;
            return GuessResult.GAME_OVER;
        }

        return guess < secretNumber ? GuessResult.TOO_LOW : GuessResult.TOO_HIGH;
    }

    /**
     * Returns the number of attempts made so far.
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * Returns the maximum number of attempts allowed.
     */
    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * Returns the number of remaining attempts.
     */
    public int getRemainingAttempts() {
        return maxAttempts - attempts;
    }

    /**
     * Returns true if the game is over (won or lost).
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Returns true if the player won the game.
     */
    public boolean hasWon() {
        return won;
    }

    /**
     * Returns the secret number.
     * Only call this after the game is over!
     */
    public int getSecretNumber() {
        return secretNumber;
    }
}
