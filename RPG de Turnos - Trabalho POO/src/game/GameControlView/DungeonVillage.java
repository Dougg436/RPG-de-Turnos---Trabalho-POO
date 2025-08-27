package game.GameControlView;

import game.GameData.Enemies.Boss.Gashadokuro;
import game.GameData.Enemies.Boss.Umibozu;
import game.GameData.Enemies.Enemy;
import game.GameData.Items.*;

import java.io.Serializable;
import java.util.*;

import static game.GameData.DataBase.*;

public class DungeonVillage implements Serializable {
    transient Scanner sc;
    HashMap<Item, Integer> smithWeapons = new HashMap<>();
    HashMap<Item, Integer> smithArmors = new HashMap<>();
    HashMap<Item, Integer> monkArtifacts = new HashMap<>();
    HashMap<Item, Integer> merchantCombatItems = new HashMap<>();

    private static final Random random = new Random();

    public DungeonVillage(Scanner sc) {this.sc = sc;}

    CombatControl combat = new CombatControl();

    enum DungeonType {
        GLADE,
        DESOLATED_TOWN,
        HAUNTED_TEMPLE
    }

    enum DungeonRoom {
        COMBAT,
        REWARD,
        SANCTUARY,
        BOSS,
    }

        DungeonRoom actualRoom;
        int steps;


    // Vila

    public void Village(Player player, Inventory inventory, boolean success){
        if (player.respect == 6) player.EndGame();
        int opt = -1;
        while (opt != 0) {
            System.out.println("""
                    1 - Ferreiro
                    2 - Monge
                    3 - Mercante
                    4 - Vender Itens
                    5 - Viajar""");
            opt = sc.nextInt();

            switch(opt) {
                case 1 -> NPC("Ferreiro", player, inventory, success);
                case 2 -> NPC("Monge", player, inventory, success);
                case 3 -> NPC("Mercador", player, inventory, success);
                case 4 -> Sell(inventory);
                case 5 -> {
                    DungeonType dungeonChosen = DungeonType.values()[player.respect];
                    System.out.println(dungeonChosen);
                    switch(player.progress) {
                        // Clareira Inéspita Bosses
                        case 3 -> StartDungeon(player, dungeonChosen, combat, TOUGH_ONI);
                        case 6 -> StartDungeon(player, dungeonChosen, combat, KARASU_TENGU);
                        // Vilarejo Desolado Bosses
                        case 9 -> StartDungeon(player, dungeonChosen, combat, BAKE_KUJIRA);
                        case 12 -> StartDungeon(player, dungeonChosen, combat, UMIBOZU);
                        // Templo Assombrado Bosses
                        case 15 -> StartDungeon(player, dungeonChosen, combat, GASHADOKURO);
                        case 18 -> StartDungeon(player, dungeonChosen, combat, OROCHI);
                        // Dungeons Comuns
                        default -> StartDungeon(player, dungeonChosen, combat, null);
                    }
                }
                case 6 -> player.ShowStats();
            }
        }
    }

    public void NPC(String name, Player player, Inventory inventory, boolean success) {
        int cont;
        switch(name){
            case "Ferreiro":
                while (true) {
                    Shop("Armas", smithWeapons, success);
                    Shop("Armaduras", smithArmors, success);
                    System.out.println("Seu Dinheiro: [" + inventory.getMoney() + " Ryos]");
                    System.out.println("""
                        =============================
                        1 - Comprar Armas
                        2 - Comprar Armaduras
                        3 - Ver inventário
                        4 - Sair""");
                    int opt = sc.nextInt();
                    switch(opt) {
                        case 1 -> Buy(smithWeapons, inventory, success);
                        case 2 -> Buy(smithArmors, inventory, success);
                        case 3 -> inventory.ShowInventory();
                        case 4 -> { return; }
                    }
                }
            case "Monge":
                while (true) {
                    Shop("Artefatos", monkArtifacts, success);
                    System.out.println("Seu Dinheiro: [" + inventory.getMoney() + " Ryos]");
                    System.out.println("""
                        =============================
                        1 - Comprar Artefatos
                        2 - Ver Inventário
                        3 - Sair""");
                    int opt = sc.nextInt();
                    switch(opt) {
                        case 1 -> Buy(monkArtifacts, inventory, success);
                        case 2 -> inventory.ShowInventory();
                        case 3 -> { return; }
                    }
                }
            case "Mercador":
                while (true) {
                    Shop("Itens de Combate", merchantCombatItems, success);
                    System.out.println("Seu Dinheiro: [" + inventory.getMoney() + " Ryos]");
                    System.out.println("""
                        =============================
                        1 - Comprar Itens de Combate
                        2 - Ver Inventário
                        3 - Sair""");
                    int opt = sc.nextInt();
                    switch(opt) {
                        case 1 -> Buy(merchantCombatItems, inventory, success);
                        case 2 -> inventory.ShowInventory();
                        case 3 -> { return; }
                    }
                }
        }
    }

    public void Shop(String name, HashMap<Item, Integer> shopItems, boolean success) {
        System.out.println("{" + name + "}");
        int cont = 1;
        if (!shopItems.isEmpty())
            for (Item i : shopItems.keySet()) {
                int price = success ? Math.max(i.getPrice() - 1, 1) : i.getPrice();
                System.out.println(cont + " - " + i.getName() + " (x" + shopItems.get(i) + ") [" + price + "]");
                cont++;
            }
        else System.out.println("/Sem estoque/");
    }

    public void Buy(HashMap<Item, Integer> list, Inventory inventory, boolean success) {
        int cont = 1;
        for (Item i : list.keySet()) {
            int price = success ? Math.max(i.getPrice() - 1, 1) : i.getPrice();
            System.out.println(cont + " - " + i.getName() + " (x" + list.get(i) + ") [" + price + "]");
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

    public void Sell(Inventory inventory) {
        inventory.ShowInventory();
        int i = sc.nextInt()-1;
        Item chosen = inventory.PickItemByIndex(i);
        if (chosen == null) {
            System.out.println("Índice inválido!");
            return;
        }
        System.out.println("Quantos?");
        int qnt = sc.nextInt();

        int currentAmount = inventory.inv.get(chosen);
        if (qnt > currentAmount) {
            System.out.println("Quantidade maior do que itens no inventário!");
            return;
        }
        int remaining = currentAmount - qnt;
        if (remaining > 0) {
            inventory.inv.put(chosen, remaining);
        } else {
            inventory.inv.remove(chosen);
        }
        inventory.ReceiveMoney(chosen.getPriceToSell() * qnt, true);
        System.out.println("Item " + chosen.getName() + " " + qnt + "x vendido! (+" + chosen.getPriceToSell()*qnt +" Ryos)");
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

        // Ferreiro
        for (int i = 0; i < 4; i++) {
            Weapon w = ALL_WEAPONS_R1.get(random.nextInt(ALL_WEAPONS_R1.size()));
            smithWeapons.put(w, 1);
        }

        for (int i = 0; i < 3; i++) {
            Armor a = ALL_ARMORS_R1.get(random.nextInt(ALL_ARMORS_R1.size()));
            smithArmors.put(a, 1);
        }

        // Monge
        for (int i = 0; i < 2; i++) {
            Artifact art = ALL_ARTIFACTS_R1.get(random.nextInt(ALL_ARTIFACTS_R1.size()));
            monkArtifacts.put(art, 1);
        }

        // Mercador
        for (int i = 0; i < 4; i++) {
            CombatItem ci = ALL_COMBATITEMS_R1.get(random.nextInt(ALL_COMBATITEMS_R1.size()));
            int amount = 1 + random.nextInt(1,4); // 1 a 3
            merchantCombatItems.put(ci, amount);
        }
    }



    public void ReturnToVillage(boolean success, Player player) {
        NewDay();
        SaveSystem.save(player, "save0.bin");
        Village(player, player.inventory, success);
    }


    // Dungeon

    public void StartDungeon(Player player, DungeonType dungeonType, CombatControl combat, Enemy boss) {
        int steps = 0;
        DungeonMenu(player, player.inventory, CreateDungeon(8, false, player), steps, dungeonType, combat, null);
    }

    public List<DungeonRoom> CreateDungeon(int lenght, boolean hasBoss, Player player) {
        List<DungeonRoom> Dungeon = new ArrayList<>();
        if (!hasBoss) {
            while (Dungeon.size() < lenght) {
                int room = random.nextInt(11);
                switch (room) {
                    case 0, 1, 2, 3, 4 -> Dungeon.add(DungeonRoom.COMBAT);
                    case 5, 6 -> Dungeon.add(DungeonRoom.SANCTUARY);
                    case 7, 8, 9, 10 -> Dungeon.add(DungeonRoom.REWARD);
                }
            }
            return Dungeon;
        }
        while (Dungeon.size() < 10) {
            int room = random.nextInt(11);
            switch (room) {
                case 0, 1, 2, 3 -> Dungeon.add(DungeonRoom.COMBAT);
                case 4, 5 -> Dungeon.add(DungeonRoom.SANCTUARY);
                case 6, 7, 8, 9, 10 -> Dungeon.add(DungeonRoom.REWARD);
            }
        }
        Dungeon.add(DungeonRoom.SANCTUARY);
        Dungeon.add(DungeonRoom.BOSS);
        return Dungeon;
    }

    public void DungeonMenu(Player player, Inventory inventory, List<DungeonRoom> dungeon, int steps, DungeonType dungeonType, CombatControl combat, Enemy boss){
        while (steps < dungeon.size()) {
            System.out.println("""
                    1 - Andar
                    2 - Ver Inventário
                    3 - Fugir""");
            int opt = sc.nextInt();
            switch (opt) {
                case 1:
                    Walk(dungeon, steps, dungeonType, combat, player, boss);
                    steps++;
                    break;
                case 2:
                    inventory.MenuGeneral(player);
                    break;
                case 3:
                    Flee(player);
                    break;
            }
        }
        System.out.println("Seu trabalho aqui terminou, você retorna a Vila");
        player.progress += 1;
        ReturnToVillage(true, player);

    }

    public void Walk(List<DungeonRoom> dungeon, int steps, DungeonType dungeonType, CombatControl combat, Player player, Enemy boss) {
        switch(dungeon.get(steps)) {
            case COMBAT:
                combat.PlayCombat(CreateEnemyPackage(random.nextInt(1,3), false, dungeonType, player, null), player, false);
                break;
            case REWARD:
                RewardRoom(dungeonType, player.inventory);
                break;
            case SANCTUARY:
                System.out.println("Você vê esculturas e uma estátua em uma sala, e faz uma pausa para uma adoração...");
                player.HealPlayer(player.getMaxHealthPoints());
                player.RecoverSecPoint("mind", player.getMaxMind(), false);
                player.RecoverSecPoint("stamina", player.getMaxStamina(), false);
                break;
            case BOSS:
                combat.PlayCombat(CreateEnemyPackage(0, true, dungeonType, player, boss), player, true);

        }
    }

    public void Flee(Player player){
        ReturnToVillage(false, player);
    }

    public void RewardRoom(DungeonType dungeonType, Inventory inventory) {
        int money = random.nextInt(2, 5);
        List<Item> item = switch(dungeonType) {
            case HAUNTED_TEMPLE -> HauntedTempleItems;
            case DESOLATED_TOWN -> DesolatedTownItems;
            case GLADE -> GladeItems;
        };
        System.out.println("Você chega em uma sala com objetos de valor...");
        inventory.ReceiveItem(item.get(random.nextInt(item.size())), random.nextInt(1, 3));
        inventory.ReceiveMoney(money, false);

    }

    public List<Enemy> CreateEnemyPackage(int lenght, boolean haveboss, DungeonType dungeonType, Player player, Enemy boss) {
        List<Enemy> enemyPackage = new ArrayList<>();
        if (!haveboss) {
            List<Enemy> Package = switch (dungeonType) {
                case HAUNTED_TEMPLE -> HauntedTempleEnemies;
                case DESOLATED_TOWN -> DesolateTownEnemies;
                case GLADE -> GladeEnemies;
            };
            for (int i = 0; i < lenght; i++) {
                Enemy enemy = Package.get(random.nextInt(Package.size()));
                enemyPackage.add(enemy.CloneEnemy());
            }
            return enemyPackage;
        } else {
            switch (boss.getName()) {
                case "Oni Encouraçado":
                    enemyPackage.add(boss.CloneEnemy());
                    break;
            }
            return enemyPackage;
        }
    }

}
