package dk.viprogram.week03;

/**
 * Normal difficulty implementation.
 *
 * Normal mode values (all standard/baseline):
 * - Player damage multiplier: 1.0
 * - Enemy damage multiplier: 1.0
 * - Gold multiplier: 1.0
 * - XP multiplier: 1.0
 * - Enemy health multiplier: 1.0
 * - Can save game: true
 */
public class NormalDifficulty implements IDifficultyStrategy {

    @Override
    public String getName() {
        // TODO: Return "Normal"
        return null;
    }

    @Override
    public double getPlayerDamageMultiplier() {
        // TODO: Return the player damage multiplier for Normal difficulty
        return 0;
    }

    @Override
    public double getEnemyDamageMultiplier() {
        // TODO: Return the enemy damage multiplier for Normal difficulty
        return 0;
    }

    @Override
    public double getGoldMultiplier() {
        // TODO: Return the gold multiplier for Normal difficulty
        return 0;
    }

    @Override
    public double getXPMultiplier() {
        // TODO: Return the XP multiplier for Normal difficulty
        return 0;
    }

    @Override
    public double getEnemyHealthMultiplier() {
        // TODO: Return the enemy health multiplier for Normal difficulty
        return 0;
    }

    @Override
    public boolean canSaveGame() {
        // TODO: Return whether the game can be saved on Normal difficulty
        return false;
    }
}
