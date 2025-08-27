package game.GameControlView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player user = null;
        System.out.println("1 - Novo Jogo\n" +
                "2 - Continuar");
        int opt = sc.nextInt();
        sc.nextLine();
        if(opt == 1) {
            user = new Player(20, 3, 3, null, 1, 0, 0, sc);
        } else if(opt == 2) {
            user = (Player) SaveSystem.load("save0.bin");
            if (user == null) {
                user = new Player(15, 3, 3, null, 1, 0, 0, sc);
            }
        }
        DungeonVillage dungeonVillage = new DungeonVillage(sc);
        user.sc = sc;
        user.inventory.DefineInv(sc);
        user.setDungeonVillage(dungeonVillage);

        user.StartGame();
    }
}