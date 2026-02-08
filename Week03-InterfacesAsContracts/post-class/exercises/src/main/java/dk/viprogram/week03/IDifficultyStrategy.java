package dk.viprogram.week03;

/**
 * Strategy pattern for difficulty settings.
 *
 * Each difficulty is its own implementation - no if-else chains!
 */
public interface IDifficultyStrategy {
    String getName();
    double getPlayerDamageMultiplier();
    double getEnemyDamageMultiplier();
    double getGoldMultiplier();
    double getXPMultiplier();
    double getEnemyHealthMultiplier();
    boolean canSaveGame();
}
