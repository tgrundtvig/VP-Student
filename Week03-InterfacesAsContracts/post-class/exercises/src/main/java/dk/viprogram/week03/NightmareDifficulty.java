package dk.viprogram.week03;

/**
 * Nightmare difficulty implementation.
 *
 * Notice how easy it is to add new difficulty levels!
 * Just create a new class - no modifications to existing code.
 *
 * Nightmare mode values:
 * - Player damage multiplier: 0.75 (player deals less damage)
 * - Enemy damage multiplier: 1.5 (enemies deal much more damage)
 * - Gold multiplier: 0.5 (half gold rewards)
 * - XP multiplier: 1.5 (bonus XP for surviving)
 * - Enemy health multiplier: 2.0 (enemies have double health)
 * - Can save game: false (no saving in nightmare mode!)
 */
public class NightmareDifficulty implements IDifficultyStrategy {

    @Override
    public String getName() {
        // TODO: Return "Nightmare"
        return null;
    }

    @Override
    public double getPlayerDamageMultiplier() {
        // TODO: Return the player damage multiplier for Nightmare difficulty
        return 0;
    }

    @Override
    public double getEnemyDamageMultiplier() {
        // TODO: Return the enemy damage multiplier for Nightmare difficulty
        return 0;
    }

    @Override
    public double getGoldMultiplier() {
        // TODO: Return the gold multiplier for Nightmare difficulty
        return 0;
    }

    @Override
    public double getXPMultiplier() {
        // TODO: Return the XP multiplier for Nightmare difficulty
        return 0;
    }

    @Override
    public double getEnemyHealthMultiplier() {
        // TODO: Return the enemy health multiplier for Nightmare difficulty
        return 0;
    }

    @Override
    public boolean canSaveGame() {
        // TODO: Return whether the game can be saved on Nightmare difficulty
        // Hint: Nightmare mode disables saving!
        return false;
    }
}
