package dk.viprogram.week03;

/**
 * Exercise 6: Implementing a Game Mode
 *
 * This class implements the IGameMode interface for survival mode.
 * Notice how there's NO if-else checking what mode we're in!
 *
 * TODO: Complete the implementation
 */
public class SurvivalMode implements IGameMode {
    private int currentWave;
    private int maxWave;
    private boolean playerAlive;
    private int score;

    public SurvivalMode(int maxWave) {
        this.maxWave = maxWave;
    }

    @Override
    public void initialize() {
        // TODO: Set up survival mode
        // Reset wave counter, player is alive, score is 0
        currentWave = 0;
        playerAlive = true;
        score = 0;
    }

    @Override
    public boolean playRound() {
        // TODO: Play one wave of survival
        // - Increment wave
        // - Add score (wave * 100)
        // - Simulate combat (for now, just random survival)
        // - Return false if player dies or max wave reached

        if (!playerAlive || currentWave >= maxWave) {
            return false;
        }

        currentWave++;
        score += currentWave * 100;

        // Simulate survival (gets harder each wave)
        double survivalChance = 1.0 - (currentWave * 0.1);
        playerAlive = Math.random() < survivalChance;

        return playerAlive && currentWave < maxWave;
    }

    @Override
    public boolean isComplete() {
        // TODO: Check if survival mode is over
        return !playerAlive || currentWave >= maxWave;
    }

    @Override
    public String getResult() {
        // TODO: Return survival results
        if (currentWave >= maxWave) {
            return String.format("Victory! Survived all %d waves! Score: %d",
                maxWave, score);
        } else {
            return String.format("Defeated on wave %d. Score: %d",
                currentWave, score);
        }
    }

    @Override
    public String getModeName() {
        return "Survival Mode";
    }

    // Getters for testing
    public int getCurrentWave() {
        return currentWave;
    }

    public int getScore() {
        return score;
    }
}