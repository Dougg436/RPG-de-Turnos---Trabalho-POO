package game;

import game.Items.*;

import java.util.*;

public class DungeonVillage {
    Scanner sc = new Scanner(System.in);
    HashMap<Item, Integer> smithWeapons = new HashMap<>();
    HashMap<Item, Integer> smithArmors = new HashMap<>();
    HashMap<Item, Integer> monkArtifacts = new HashMap<>();
    HashMap<Item, Integer> merchantCombatItems = new HashMap<>();

    private static final Random random = new Random();

    public void Village(Player player, Inventory inventory){
        int opt = -1;
        while (opt != 0) {
            System.out.println("""
                    1 - Ferreiro
                    2 - Monge
                    3 - Mercante
                    4 - Viajar""");
            opt = sc.nextInt();

            switch(opt) {
                case 1: NPC("Ferreiro", player, inventory); break;
                case 2: NPC("Monge", player, inventory); break;
                case 3: NPC("Mercador", player, inventory); break;
            }
        }
    }

    public void NPC(String name, Player player, Inventory inventory) {
        int cont = 1;
        switch(name){
            case "Ferreiro":
                while (true) {
                    System.out.println("\n {Armas}");
                    for (Item w : smithWeapons.keySet()) {
                        System.out.println(cont + " - " + w.getName() + " (x" + smithWeapons.get(w) + ") [" + w.getPrice() + "]");
                        cont++;
                    }
                    cont = 1;
                    System.out.println("\n {Armaduras}");
                    for (Item a : smithArmors.keySet()) {
                        System.out.println(cont + " - " + a.getName() + " (x" + smithArmors.get(a) + ") [" + a.getPrice() + "]");
                        cont++;
                    }
                    System.out.println("""
                            =============================
                            1 - Comprar Armas
                            2 - Comprar Armaduras
                            3 - Ver inventário
                            4 - Sair""");
                    int opt = sc.nextInt();
                    switch(opt) {
                        case 1: Buy(smithWeapons, inventory); break;
                        case 2: Buy(smithArmors, inventory); break;
                        case 3: inventory.ShowInventory(); break;
                        case 4: return;
                    }
                }

            case "Monge":
                while (true) {
                    System.out.println("\n {Artefatos}");
                    for (Item a : monkArtifacts.keySet()) {
                        System.out.println(cont + " - " + a.getName() + " (x" + monkArtifacts.get(a) + ") [" + a.getPrice() + "]");
                        cont++;
                    }
                    System.out.println("""
                            =============================
                            1 - Comprar Artefatos
                            2 - Ver Inventário
                            3 - Sair""");
                    int opt = sc.nextInt();
                    switch(opt) {
                        case 1: Buy(monkArtifacts, inventory); break;
                        case 2: inventory.ShowInventory(); break;
                        case 3: return;
                    }
                }

            case "Mercador":
                while (true) {
                    System.out.println("\n {Itens de Combate}");
                    for (Item c : merchantCombatItems.keySet()) {
                        System.out.println(cont + " - " + c.getName() + " (x" + merchantCombatItems.get(c) + ") [" + c.getPrice() + "]");
                        cont++;
                    }
                    System.out.println("""
                            =============================
                            1 - Comprar Itens de Combate
                            2 - Ver Inventário
                            3 - Sair""");
                    int opt = sc.nextInt();
                    switch(opt) {
                        case 1: Buy(merchantCombatItems, inventory); break;
                        case 2: inventory.ShowInventory(); break;
                        case 3: return;
                    }
                }
        }
    }

    public void Buy(HashMap<Item, Integer> list, Inventory inventory) {
        int cont = 1;
        for (Item i : list.keySet()) {
            System.out.println(cont + " - " + i.getName() + " (x" + list.get(i) + ")");
            cont++;
        }
        System.out.println("0 - Cancelar");
        int buy = sc.nextInt();
        if (buy == 0) return;
        Item item = PickBuyByIndex(buy, list);

        System.out.println("Quantos?");
        int qnt = sc.nextInt();
        if (qnt <= 0) {
            System.out.println("Quantidade inválida!");
            return;
        }
        if (qnt > list.get(item)) {
            System.out.println("Quantidade maior que estoque!");
            return;
        }

        if (inventory.getMoney() >= item.getPrice()*qnt) {

            int newstock = list.get(item) - qnt;
            if (newstock > 0) list.put(item, newstock);
            else list.remove(item);

            inventory.SpendMoney(item.getPrice()*qnt);
            inventory.ReceiveItem(item, qnt);
        } else System.out.println("Dinheiro insuficiente!");
    }

    public <T extends Item> T PickBuyByIndex(int index, HashMap<T, Integer> list) {
        List<T> keys = new ArrayList<>(list.keySet());
        if (index > 0 && index <= keys.size()) {
            return keys.get(index - 1);
        }
        return null;
    }

    public void NewDay() {
        Random random = new Random();

        smithWeapons.clear();
        smithArmors.clear();
        monkArtifacts.clear();
        merchantCombatItems.clear();

        for (int i = 0; i < 4; i++) {
            Weapon w = DataBase.ALL_WEAPONS.get(random.nextInt(DataBase.ALL_WEAPONS.size()));
            int amount = 1 + random.nextInt(4); // 0 a 3
            smithWeapons.put(w, amount);
        }

        for (int i = 0; i < 3; i++) {
            Armor a = DataBase.ALL_ARMORS.get(random.nextInt(DataBase.ALL_ARMORS.size()));
            int amount = 1 + random.nextInt(3); // 0 a 2
            smithArmors.put(a, amount);
        }

        for (int i = 0; i < 2; i++) {
            Artifact art = DataBase.ALL_ARTIFACTS.get(random.nextInt(DataBase.ALL_ARTIFACTS.size()));
            int amount = 1 + random.nextInt(2); // 0 a 1
            monkArtifacts.put(art, amount);
        }

        for (int i = 0; i < 4; i++) {
            CombatItem ci = DataBase.ALL_COMBATITEMS.get(random.nextInt(DataBase.ALL_COMBATITEMS.size()));
            int amount = 1 + random.nextInt(4); // 0 a 3
            merchantCombatItems.put(ci, amount);
        }
    }
}
