package dk.viprogram.week02;

import java.util.ArrayList;
import java.util.Random;

/**
 * Exercise 3: Complex Battle System
 *
 * Try to create a battle system that handles multiple participants,
 * different damage types, and status effects.
 *
 * You'll discover how quickly the complexity grows and how hard it becomes to test!
 *
 * TODO: Implement a battle system that supports:
 * - Multiple participants (2v2, 3v3, etc.)
 * - Different damage types (physical, magical, true damage)
 * - Status effects (poison, stun, shield)
 * - Turn order based on speed
 */
public class ComplexBattle {
    private ArrayList<EnhancedCharacter> team1;
    private ArrayList<EnhancedCharacter> team2;
    private ArrayList<String> battleLog;
    private int turn;

    // Status effects - how do we track these per character?
    // This gets messy fast!
    private ArrayList<String> poisonedCharacters;
    private ArrayList<String> stunnedCharacters;
    private ArrayList<String> shieldedCharacters;

    public ComplexBattle() {
        this.team1 = new ArrayList<>();
        this.team2 = new ArrayList<>();
        this.battleLog = new ArrayList<>();
        this.poisonedCharacters = new ArrayList<>();
        this.stunnedCharacters = new ArrayList<>();
        this.shieldedCharacters = new ArrayList<>();
        this.turn = 0;
    }

    /**
     * Add a character to a team
     */
    public void addToTeam1(EnhancedCharacter character) {
        team1.add(character);
    }

    public void addToTeam2(EnhancedCharacter character) {
        team2.add(character);
    }

    /**
     * Process one round of battle
     *
     * TODO: Implement turn order, attacks, abilities, status effects
     * Notice: How complex does this method become?
     */
    public void processBattleRound() {
        turn++;
        battleLog.add("=== Turn " + turn + " ===");

        // TODO: Determine turn order (who goes first?)
        // TODO: For each character that isn't stunned:
        //   - Process poison damage if poisoned
        //   - Let them attack or use ability
        //   - Apply damage (considering shields)
        //   - Apply any status effects

        // This method will become HUGE!
        // How do we test all the combinations?
    }

    /**
     * Apply a status effect to a character
     *
     * TODO: Implement status effect application
     * Notice: How do we track duration? Stack effects? Remove them?
     */
    public void applyStatusEffect(String characterName, String effect, int duration) {
        // TODO: Apply the effect
        // How do we track duration?
        // What if the character already has this effect?
        // How do we remove it when duration expires?
    }

    /**
     * Calculate damage considering type and defenses
     *
     * TODO: Implement damage calculation
     * Notice: The complexity of handling different types
     */
    public int calculateDamage(EnhancedCharacter attacker,
                              EnhancedCharacter defender,
                              String damageType) {
        int damage = attacker.calculateAttackDamage();

        // TODO: Modify based on damage type
        if (damageType.equals("PHYSICAL")) {
            // Reduced by armor
        } else if (damageType.equals("MAGICAL")) {
            // Reduced by magic resistance
        } else if (damageType.equals("TRUE")) {
            // Ignores all defenses
        }

        // TODO: Check if defender has shield
        if (shieldedCharacters.contains(defender.getName())) {
            // Reduce damage
        }

        return damage;
    }

    /**
     * Check if the battle is over
     */
    public boolean isBattleOver() {
        // TODO: Check if all members of a team are defeated
        boolean team1Alive = false;
        boolean team2Alive = false;

        for (EnhancedCharacter c : team1) {
            if (c.getHealth() > 0) {
                team1Alive = true;
                break;
            }
        }

        for (EnhancedCharacter c : team2) {
            if (c.getHealth() > 0) {
                team2Alive = true;
                break;
            }
        }

        return !team1Alive || !team2Alive;
    }

    /**
     * Get the battle log
     */
    public ArrayList<String> getBattleLog() {
        return battleLog;
    }

    /**
     * Determine which team won
     */
    public String getWinner() {
        // TODO: Return "TEAM1", "TEAM2", or "DRAW"
        return "UNKNOWN";
    }

    // Consider: How would you test this class?
    // - How do you test poison damage over multiple turns?
    // - How do you test that stun prevents actions?
    // - How do you test turn order?
    // - How do you test all combinations of effects?
}