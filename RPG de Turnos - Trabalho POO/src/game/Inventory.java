package game;

import game.Items.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    HashMap<Item, Integer> inv = new HashMap<>();
    Weapon weapon;
    Armor armor;
    Artifact artifact;

    private int money;


    public void DefineInv(){
        weapon = DataBase.BARE_HANDS;
        armor = DataBase.DIRTY_CLOTHES;
        artifact = DataBase.FLUTE;

        inv.put(DataBase.BANDAGE, 3);
        inv.put(DataBase.KATANA, 1);

        money = 5;

    }

    public void ShowInventory() {
        System.out.println("======/Equipado/======" +
                "\nArma: " + weapon.getName() +
                "\nArmadura: " + armor.getName() +
                "\nArtefato: " + artifact.getName());

        List<Item> keys = new ArrayList<>(inv.keySet());
        for (int i = 0; i < keys.size(); i++) {
            Item it = keys.get(i);
            System.out.println((i+1) + " - " + it.getName() + " x" + inv.get(it));
        }
    }

    public Item PickItemByIndex(int index) {
        List<Item> keys = new ArrayList<>(inv.keySet());
        if (index >= 0 && index < keys.size()) {
            return keys.get(index);
        }
        return null;
    }

    public void MenuGeneral(Player player) {
        Scanner sc = new Scanner(System.in);
        int opt = -1;
        while (opt != 0) {
            System.out.println("0 - Sair\n" +
                    "1 - Equipar Item\n" +
                    "2 - Descartar Item\n" +
                    "3 - Ver Inventário");
            opt = sc.nextInt();
            switch (opt) {
                case 1:
                    ShowInventory();
                    int i = sc.nextInt()-1;

                    Item chosen1 = PickItemByIndex(i);
                    if (chosen1 != null) {
                        EquipItem(chosen1, player);
                        inv.remove(chosen1);
                    } else System.out.println("Índice inválido!");
                    break;
                case 2:
                    ShowInventory();
                    int qnt = 1;
                    int j = sc.nextInt()-1;
                    Item chosen2 = PickItemByIndex(j);
                    if (inv.get(chosen2) > 1) {
                        System.out.println("Quantos?");
                        qnt = sc.nextInt();
                    }
                    if (chosen2 != null) {
                        DiscardItem(chosen2, qnt);
                    } else System.out.println("Índice inválido!");
                    break;
                case 3:
                    ShowInventory();
                    break;
            }
        }
    }

    public void EquipItem(Item item, Player player) {
        if (item.equals(weapon) || item.equals(armor) || item.equals(artifact)) {
            System.out.println("Item já equipado!");
            return;
        }
        switch(item.getType()) {
            case "Weapon":
                Weapon oldWeapon = weapon;
                weapon = (Weapon) item;
                inv.put(oldWeapon, inv.getOrDefault(oldWeapon, 0) + 1);
                inv.remove(item);
                if (oldWeapon.getWeaponSkill() != null) player.skills.remove(oldWeapon.getWeaponSkill());
                if (weapon.getWeaponSkill() != null) player.skills.add(weapon.getWeaponSkill());
                System.out.println("Arma: " + oldWeapon.getName() + " --> " + weapon.getName());
                break;
            case "Armor":
                String oldArmor = armor.getName();
                inv.put(armor, inv.getOrDefault(armor, 0) + 1);
                armor = (Armor) item;
                inv.remove(item);
                System.out.println("Armadura: " + oldArmor + " --> " + armor.getName());
                break;
            case "Artifact":
                String oldArtifact = artifact.getName();
                artifact.OnUnequip(player);
                inv.put(artifact, inv.getOrDefault(artifact, 0) + 1);
                artifact = (Artifact) item;
                ((Artifact) item).OnEquip(player);
                inv.remove(item);
                System.out.println("Artefato: " + oldArtifact + " --> " + artifact.getName());
                break;
            default:
                System.out.println("Este item não pode ser equipado!");
        }
    }

    public void ReceiveItem(Item item, int qnt) {
        inv.put(item, inv.getOrDefault(item, 0) + qnt);
        System.out.println("Recebeu: " + qnt + "x " + item.getName());
    }

    public void DiscardItem(Item item, int qnt) {
        int currentqnt = inv.get(item);
        if (qnt >= currentqnt) inv.remove(item);
        else inv.put(item, currentqnt - qnt);
        System.out.println("Descartou: " + qnt + "x " + item.getName());
    }

    public int getMoney() {
        return money;
    }

    public void SpendMoney(int amount) {
        money = Math.max(money - amount, 0);
    }

    public void ReceiveMoney(int amount) {
        money += amount;
        System.out.println("Recebeu +" + amount + "Ryos!");
    }
}
