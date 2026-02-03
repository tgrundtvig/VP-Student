package dk.ek.evu.vpf26.textadventure;

import java.util.Scanner;

public class CombatPlayerVSMonster {
    public static void PerformBattle(Player player, Monster monster, Scanner scanner) {
        while (player.isAlive() && monster.isAlive()) {
            while (true) {
                System.out.println(player.getName() + " has " + player.getHealth() + " health.");
                System.out.println(monster.getName() + " has " + monster.getHealth() + " health.");
                System.out.print("What would you like to do?>");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("run")) {
                    System.out.println(player.getName() + " runs from the battle.");
                    return;
                } else if (choice.equalsIgnoreCase("fight")) {
                    if (monster.getDamage() > player.getAttackPower()) {
                        System.out.println("The monster " + monster.getName() + " attacks the player " + player.getName() + " with " + monster.getDamage() + " damage!");
                        player.takeDamage(monster.getDamage());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (!player.isAlive()) {
                            System.out.println("The player " + player.getName() + " is dead!");
                            return;
                        }
                        System.out.println("The player " + player.getName() + " is attacking the monster " + monster.getName() + " with damage " + player.getAttackPower());
                        monster.takeDamage(player.getAttackPower());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (!monster.isAlive()) {
                            System.out.println("The monster " + monster.getName() + " is dead!");
                            return;
                        }
                    } else {
                        System.out.println("The player " + player.getName() + " is attacking the monster " + monster.getName() + " with damage " + player.getAttackPower());
                        monster.takeDamage(player.getAttackPower());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (!monster.isAlive()) {
                            System.out.println("The monster " + monster.getName() + " is dead!");
                            return;
                        }
                        System.out.println("The monster " + monster.getName() + " attacks the player " + player.getName() + " with " + monster.getDamage() + " damage!");
                        player.takeDamage(monster.getDamage());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (!player.isAlive()) {
                            System.out.println("The player " + player.getName() + " is dead!");
                            return;
                        }
                    }
                }
                else
                {
                    System.out.println(choice + " is not a valid choice. Choose run or fight. Try again:");
                }
            }
        }
    }
}
