package game;

import game.Enemies.Enemy;
import game.Enemies.Oni;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Player user = new Player(45, 45, 45, sc.nextLine(), 1);
        DungeonVillage dungeonVillage = new DungeonVillage();
        user.inventory.DefineInv();
        dungeonVillage.UnlockDungeon("Vilarejo Desolado");
        dungeonVillage.NewDay();

        dungeonVillage.Village(user, user.inventory, false);














        //end
    }
}