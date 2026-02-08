package dk.viprogram.week02;

/**
 * Difficulty management - the "painful" way.
 *
 * TEACHING NOTE: This demonstrates how difficulty settings
 * without interfaces lead to scattered if-else checks everywhere.
 *
 * Notice:
 * - Every method that cares about difficulty must check it
 * - Changes to difficulty logic require changes in multiple files
 * - No way to add new difficulty levels without modifying existing code
 * - Testing difficulty variations is hard
 */
public class DifficultyManager {

    // PAIN POINT: These modifiers are used in multiple places
    // Player.getEffectiveAttackPower(), Player.takeDamage(), Player.addGold(),
    // Monster spawning, etc. All must use consistent logic!

    public static double getPlayerDamageMultiplier(String difficulty) {
        // TODO: Return the player damage multiplier based on difficulty:
        //   "EASY"      -> 1.25
        //   "NORMAL"    -> 1.0
        //   "HARD"      -> 1.0
        //   "NIGHTMARE" -> 0.75
        //   default     -> 1.0
        return 1.0;
    }

    public static double getEnemyDamageMultiplier(String difficulty) {
        // TODO: Return the enemy damage multiplier based on difficulty:
        //   "EASY"      -> 0.75
        //   "NORMAL"    -> 1.0
        //   "HARD"      -> 1.25
        //   "NIGHTMARE" -> 1.5
        //   default     -> 1.0
        return 1.0;
    }

    public static double getGoldMultiplier(String difficulty) {
        // TODO: Return the gold multiplier based on difficulty:
        //   "EASY"      -> 2.0
        //   "NORMAL"    -> 1.0
        //   "HARD"      -> 1.0
        //   "NIGHTMARE" -> 0.5
        //   default     -> 1.0
        return 1.0;
    }

    public static double getXPMultiplier(String difficulty) {
        // TODO: Return the XP multiplier based on difficulty:
        //   "EASY"      -> 1.0
        //   "NORMAL"    -> 1.0
        //   "HARD"      -> 1.25
        //   "NIGHTMARE" -> 1.5
        //   default     -> 1.0
        return 1.0;
    }

    public static double getEnemyHealthMultiplier(String difficulty) {
        // TODO: Return the enemy health multiplier based on difficulty:
        //   "EASY"      -> 0.5
        //   "NORMAL"    -> 1.0
        //   "HARD"      -> 1.5
        //   "NIGHTMARE" -> 2.0
        //   default     -> 1.0
        return 1.0;
    }

    public static boolean canSaveGame(String difficulty) {
        // TODO: Return whether the game can be saved.
        // NIGHTMARE mode disables saving (return false).
        // All other modes allow saving (return true).
        return true;
    }

    // PAIN POINT: What if we want to add "CUSTOM" difficulty?
    // We'd have to modify every single method here!

    // PAIN POINT: What if difficulty should affect shop prices?
    // Add another method here AND modify Shop class!
}
