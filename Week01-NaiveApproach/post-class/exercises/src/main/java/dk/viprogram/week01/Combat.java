package dk.viprogram.week01;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles combat between a player and a monster.
 *
 * Combat proceeds in rounds. Each round:
 * 1. The player attacks the monster (dealing player's attack power as damage)
 * 2. If the monster survives, it attacks the player
 * 3. Combat ends when either the player or monster reaches 0 health
 *
 * If the player wins, they receive the monster's gold and experience rewards.
 *
 * YOUR TASKS:
 * 1. Implement the constructor to set up combat state
 * 2. Implement executeRound() for one round of combat
 * 3. Implement runFullCombat() to run rounds until combat ends
 * 4. Implement grantRewards() to give player gold and XP on victory
 * 5. Implement getter methods for combat state
 */
public class Combat {
    private Player player;
    private Monster monster;
    private List<String> combatLog;
    private boolean combatEnded;
    private boolean playerWon;

    /**
     * Creates a new Combat encounter between a player and a monster.
     *
     * @param player  the player in combat
     * @param monster the monster being fought
     */
    public Combat(Player player, Monster monster) {
        // TODO: Initialize all fields
        // - Store the player and monster references
        // - Initialize combatLog as a new ArrayList
        // - Set combatEnded to false
        // - Set playerWon to false
        this.player = null;
        this.monster = null;
        this.combatLog = new ArrayList<>();
        this.combatEnded = false;
        this.playerWon = false;
    }

    /**
     * Executes one round of combat.
     *
     * A round consists of:
     * 1. If combat has already ended, do nothing (return immediately)
     * 2. Player attacks monster: deal player.getAttackPower() damage to monster
     *    - Add a log entry: "{playerName} attacks {monsterName} for {damage} damage!"
     * 3. Check if monster is dead:
     *    - If dead: set combatEnded=true, playerWon=true, log it, call grantRewards(), return
     * 4. Monster attacks player: deal monster.getAttackPower() damage to player
     *    - Add a log entry: "{monsterName} attacks {playerName} for {damage} damage!"
     * 5. Check if player is dead:
     *    - If dead: set combatEnded=true, playerWon=false, log it
     */
    public void executeRound() {
        // TODO: Implement one round of combat as described in the JavaDoc
        // Remember: player attacks first, then monster (if still alive)
        // Use combatLog.add(...) to record what happens each step
    }

    /**
     * Runs combat until one side is defeated.
     * Executes rounds in a loop until combatEnded is true.
     * Includes a safety limit of 100 rounds to prevent infinite loops.
     */
    public void runFullCombat() {
        // TODO: Implement full combat
        // Step 1: Add opening log entries (e.g., "=== COMBAT BEGINS ===")
        // Step 2: Loop while !combatEnded and round < 100:
        //         - Increment round counter
        //         - Add round header to log (e.g., "--- Round {n} ---")
        //         - Call executeRound()
        // Step 3: Add closing log entry (e.g., "=== COMBAT ENDS ===")
    }

    /**
     * Grants the player rewards for defeating the monster.
     * Adds the monster's gold drop and experience drop to the player.
     * Also logs the reward amounts.
     */
    private void grantRewards() {
        // TODO: Give the player gold and experience from the defeated monster
        // - player.addGold(monster.getGoldDrop())
        // - player.addExperience(monster.getExperienceDrop())
        // - Add a log entry about the rewards received
    }

    // =========================================================================
    // Getters
    // =========================================================================

    /**
     * @return true if combat has ended (one side defeated)
     */
    public boolean isCombatEnded() {
        // TODO: Return combatEnded
        return false;
    }

    /**
     * @return true if the player won the combat
     */
    public boolean didPlayerWin() {
        // TODO: Return playerWon
        return false;
    }

    /**
     * Returns a copy of the combat log.
     *
     * @return a list of all combat event descriptions
     */
    public List<String> getCombatLog() {
        // TODO: Return a new ArrayList containing the combat log entries
        return new ArrayList<>();
    }

    /**
     * Returns the last entry in the combat log, or empty string if no entries.
     *
     * @return the most recent log entry
     */
    public String getLastLogEntry() {
        if (combatLog.isEmpty()) {
            return "";
        }
        return combatLog.get(combatLog.size() - 1);
    }
}
