package dk.viprogram.week03;

/**
 * Hard difficulty implementation.
 *
 * Hard mode values:
 * - Player damage multiplier: 1.0 (normal player damage)
 * - Enemy damage multiplier: 1.25 (enemies deal more damage)
 * - Gold multiplier: 1.0 (normal gold rewards)
 * - XP multiplier: 1.25 (bonus XP for the challenge)
 * - Enemy health multiplier: 1.5 (enemies have more health)
 * - Can save game: true
 */
public class HardDifficulty implements IDifficultyStrategy {

    @Override
    public String getName() {
        // TODO: Return "Hard"
        return null;
    }

    @Override
    public double getPlayerDamageMultiplier() {
        // TODO: Return the player damage multiplier for Hard difficulty
        return 0;
    }

    @Override
    public double getEnemyDamageMultiplier() {
        // TODO: Return the enemy damage multiplier for Hard difficulty
        return 0;
    }

    @Override
    public double getGoldMultiplier() {
        // TODO: Return the gold multiplier for Hard difficulty
        return 0;
    }

    @Override
    public double getXPMultiplier() {
        // TODO: Return the XP multiplier for Hard difficulty
        return 0;
    }

    @Override
    public double getEnemyHealthMultiplier() {
        // TODO: Return the enemy health multiplier for Hard difficulty
        return 0;
    }

    @Override
    public boolean canSaveGame() {
        // TODO: Return whether the game can be saved on Hard difficulty
        return false;
    }
}
