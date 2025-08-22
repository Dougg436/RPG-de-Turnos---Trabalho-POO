package game;

import game.Enemies.Enemy;
import game.Enemies.Oni;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CombatControl combat = new CombatControl();
        Player user = new Player(45, 45, 45, sc.nextLine(), 1);
        DungeonVillage dungeonVillage = new DungeonVillage();
        user.inventory.DefineInv();
        dungeonVillage.NewDay();
        dungeonVillage.Village(user, user.inventory);

        /*
        user.inventory.MenuGeneral(user);

        List<Enemy> enemies = new ArrayList<>();
        Oni e1 = new Oni();
        Oni e2 = new Oni();
        enemies.add(e1);
        enemies.add(e2);
        combat.PlayCombat(enemies, user, false);
         */














        //end
    }
}