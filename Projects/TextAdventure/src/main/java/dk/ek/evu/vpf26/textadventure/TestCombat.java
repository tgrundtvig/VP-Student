package dk.ek.evu.vpf26.textadventure;

import java.util.Scanner;

public class TestCombat
{
    public static void main(String[] args)
    {
        Player player = new Player("Sir Lancelot", 10);
        Monster monster = new Monster("Dragon", 500, 25);
        CombatPlayerVSMonster.PerformBattle(player, monster, new Scanner(System.in));
    }
}
