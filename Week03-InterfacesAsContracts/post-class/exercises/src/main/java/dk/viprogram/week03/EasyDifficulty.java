package dk.viprogram.week03;

/**
 * Easy difficulty implementation.
 *
 * Each difficulty is a separate class - no if-else needed!
 *
 * Easy mode values:
 * - Player damage multiplier: 1.25 (player deals more damage)
 * - Enemy damage multiplier: 0.75 (enemies deal less damage)
 * - Gold multiplier: 2.0 (double gold rewards)
 * - XP multiplier: 1.0 (normal XP)
 * - Enemy health multiplier: 0.5 (enemies have half health)
 * - Can save game: true
 */
public class EasyDifficulty implements IDifficultyStrategy {

    @Override
    public String getName() {
        // TODO: Return "Easy"
        return null;
    }

    @Override
    public double getPlayerDamageMultiplier() {
        // TODO: Return the player damage multiplier for Easy difficulty
        return 0;
    }

    @Override
    public double getEnemyDamageMultiplier() {
        // TODO: Return the enemy damage multiplier for Easy difficulty
        return 0;
    }

    @Override
    public double getGoldMultiplier() {
        // TODO: Return the gold multiplier for Easy difficulty
        return 0;
    }

    @Override
    public double getXPMultiplier() {
        // TODO: Return the XP multiplier for Easy difficulty
        return 0;
    }

    @Override
    public double getEnemyHealthMultiplier() {
        // TODO: Return the enemy health multiplier for Easy difficulty
        return 0;
    }

    @Override
    public boolean canSaveGame() {
        // TODO: Return whether the game can be saved on Easy difficulty
        return false;
    }
}
