package dk.viprogram.week03;

/**
 * Exercise 5: Design Your Own Interface
 *
 * Remember Week 2's game mode problem with all the if-else statements?
 * This interface solves it!
 *
 * TODO: Study how this interface eliminates the need for if-else chains
 */
public interface IGameMode {
    /**
     * Initialize the game mode
     */
    void initialize();

    /**
     * Play one round of this game mode
     * @return true if the round was played, false if game is over
     */
    boolean playRound();

    /**
     * Check if this game mode is complete
     * @return true if the game is over
     */
    boolean isComplete();

    /**
     * Get the final result/score
     * @return description of the game result
     */
    String getResult();

    /**
     * Get the name of this game mode
     * @return mode name
     */
    String getModeName();
}