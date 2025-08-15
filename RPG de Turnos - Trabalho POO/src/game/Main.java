package game;

import game.Items.DataBase;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();
        Player user = new Player(5, 1, 1, sc.nextLine(), 1);
        user.ApplyEffect(DataBase.BLEED);














        //end
    }
}