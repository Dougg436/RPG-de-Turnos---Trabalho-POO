package game;

import game.Items.*;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class DungeonVillage {
    Scanner sc = new Scanner(System.in);
    HashMap<Weapon, Integer> smithWeapons = new HashMap<>();
    HashMap<Armor, Integer> smithArmors = new HashMap<>();
    HashMap<Artifact, Integer> monkArtifacts = new HashMap<>();
    HashMap<CombatItem, Integer> merchantCombatItems = new HashMap<>();

    private static final Random random = new Random();

    public void Village(Player player){
        int opt = -1;
        while (opt != 0) {
            System.out.println("""
                    1 - Ferreiro
                    2 - Monge
                    3 - Mercante
                    4 - Viajar""");
            opt = sc.nextInt();

            switch(opt) {
                case 1:

            }
        }
    }

    public void NPC(String name) {
        String saudation = name + ": Olá!";
        int cont = 1;
        switch(name){
            case "Ferreiro":
                System.out.println(saudation + "\n {Armas}");
                for (Weapon w : smithWeapons.keySet()) {
                    System.out.println(cont + " - " + w.getName());
                }
                System.out.println(saudation + "\n {Armaduras}");
                for (Armor a : smithArmors.keySet()) {
                    System.out.println(cont + " - " + a.getName());
                }
                System.out.println("1 + N - Comprar");

        }
    }

    public void NewDay() {
        Random random = new Random();

        smithWeapons.clear();
        smithArmors.clear();
        monkArtifacts.clear();
        merchantCombatItems.clear();

        for (int i = 0; i < 4; i++) {
            Weapon w = DataBase.ALL_WEAPONS.get(random.nextInt(DataBase.ALL_WEAPONS.size()));
            int amount = random.nextInt(4); // 0 a 3
            smithWeapons.put(w, amount);
        }

        for (int i = 0; i < 3; i++) {
            Armor a = DataBase.ALL_ARMORS.get(random.nextInt(DataBase.ALL_ARMORS.size()));
            int amount = random.nextInt(3); // 0 a 2
            smithArmors.put(a, amount);
        }

        for (int i = 0; i < 2; i++) {
            Artifact art = DataBase.ALL_ARTIFACTS.get(random.nextInt(DataBase.ALL_ARTIFACTS.size()));
            int amount = random.nextInt(2); // 0 a 1
            monkArtifacts.put(art, amount);
        }

        for (int i = 0; i < 4; i++) {
            CombatItem ci = DataBase.ALL_COMBATITEMS.get(random.nextInt(DataBase.ALL_COMBATITEMS.size()));
            int amount = random.nextInt(4); // 0 a 3
            merchantCombatItems.put(ci, amount);
        }
    }
}
