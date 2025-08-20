package game;

import game.Enemies.Enemy;
import game.Items.*;
import game.StatusEffect.StatusEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;         // Nome
    private int healthPoints;    // Saúde
    private int maxHealthPoints; // Max. Saúde
    private int stamina;         // Stamina
    private int maxStamina;      // Max. Stamina
    private int staminaGain;
    private int mind;            // Mente
    private int maxMind;         // Max. Mente
    private int mindGain;
    private int level;           // Nível
    private int XP;           // Honra (EXP)
    private int defense;         // Defesa
    List<StatusEffect> effects = new ArrayList<>();
    List<Skill> skills = new ArrayList<>();
    Inventory inventory = new Inventory();
    Scanner sc = new Scanner(System.in);

    enum Location {
        VILLAGE,
        DUNGEON,
    }

    private Location location;


    public void ShowStats() {
        System.out.println(
                "=====|{ " + name.toUpperCase() +" }|=====\n" +
                        "Nível: " + level + "\n" +
                        "Honra: " + XP + "\n\n" +
                        "Saúde: " + healthPoints + " / " + maxHealthPoints + "\n" +
                        "Vigor: " + stamina + " / " + maxStamina + "\n" +
                        "Mente: " + mind + " / " + maxMind + "\n" +
                        "Defesa: " + defense + "\n"
        );
    }

    public Player(int maxhp, int maxsp, int maxmp, String name, int level){
        this.maxHealthPoints = maxhp;
        this.maxStamina = maxsp;
        this.maxMind = maxmp;
        this.name = name;
        this.level = level;
        this.healthPoints = maxhp;
        this.stamina = maxsp;
        this.mind = maxmp;
    }

    public void HarmPlayer(int damage) {
        int finalDamage = Math.max(damage - defense, 0);
        healthPoints = Math.max(healthPoints - finalDamage, 0);
    }

    public void HarmPlayerPiercing(int damage) {
        int finalDamage = Math.max(damage, 0);
        healthPoints = Math.max(healthPoints - finalDamage, 0);
    }

    public void HealPlayer(int heal) {
        int oldHP = healthPoints;
        healthPoints = Math.min(healthPoints + heal, maxHealthPoints);
        int healedHP = healthPoints - oldHP;
        if (healedHP > 0) {
            System.out.println(name + " recupera (+" + healedHP + ") de vida!");
        } else {
            System.out.println(name + " já está com a vida cheia!");
        }
    }

    public void SpendSecPoint(String sec, int qnt) {
        switch(sec){
            case "stamina":
                if (stamina >= qnt) {
                    stamina -= qnt;
                    return;
                }
                System.out.println("Sem Stamina suficiente!");
                return;
            case "mind":
                if (mind >= qnt) {
                    mind -= qnt;
                    return;
                }
                System.out.println("Sem Mente suficiente!");
        }
    }

    public void RecoverSecPoint(String sec, int qnt, boolean quiet) {
        switch(sec){
            case "stamina":
                int oldSP = stamina;
                stamina = Math.min(stamina + qnt, maxStamina);
                int SPgained = stamina - oldSP;
                if (!quiet) {
                    if (SPgained > 0) {
                        System.out.println(name + " recupera (+" + SPgained + ") de Stamina");
                    } else System.out.println(name + " Stamina já cheia!");
                }
                break;
            case "mind":
                int oldMP = mind;
                mind = Math.min(mind + qnt, maxMind);
                int MPgained = mind - oldMP;
                if (!quiet) {
                    if (MPgained > 0) {
                        System.out.println(name + " recupera (+" + MPgained + ") de Mente");
                    } else System.out.println(name + " Mente já cheia!");
                }
                break;
        }
    }

    public void ApplyEffect(StatusEffect effect) {
        effects.add(effect);
        System.out.println(name + " " + effect.getType().getMessage());
    }

    public void RemoveEffect(StatusEffect.Type type) {
        effects.removeIf(e -> e.getType() == type);
    }

    public void ProcessEffects() {
        List<StatusEffect> ended = new ArrayList<>();
        for (StatusEffect e : effects) {
            e.ApplyTurnEffect(this);
            if (e.IsEnded()) ended.add(e);
        }
        effects.removeAll(ended);
    }

    public void CheckLevel() {
        if (XP >= level*5){
            while (XP >= level*5) {
                XP -= level*5;
                level += 1;
                int oldHP = maxHealthPoints;
                int oldSP = maxStamina;
                int oldMP = maxMind;
                maxHealthPoints += 4;
                maxStamina += 1;
                maxMind += 1;
                System.out.println("Level up! (" + level + ")\n" +
                        "Saúde: " + oldHP + " -> " + maxHealthPoints + "\n" +
                        "Stamina: " + oldSP + " -> " + maxStamina + "\n" +
                        "Mente: " + oldMP + " -> " + maxMind);
            }
        }
    }

    public void TakeTurn(List<Enemy> enemies) {
        for (Enemy e: enemies) System.out.println("[" + e.getName()+ " (" + e.getHealthPoints() + "/" + e.getMaxHealthPoints() + ")]");
        System.out.println("Saúde: " + healthPoints + "/" + maxHealthPoints +
                "\nStamina: " + stamina + "/" + maxStamina +
                "\nMente: " + mind + "/" + maxMind);
        Sleep(250);
        System.out.println("------------------------------" +
                "\n1 - Atacar" +
                "\n2 - Habilidade" +
                "\n3 - Item" +
                "\n4 - Descansar");

        int opt = sc.nextInt();
        switch(opt) {
            case 1:
                Enemy target = ChooseEnemy(enemies);
                if (target != null) {AttackEnemy(target);}

                break;
            case 2:
                for (int i = 0; i < skills.size(); i++) {
                    Skill sk = skills.get(i);
                    System.out.println((i+1) + " - " + sk.getName());

                }
                skills.get(sc.nextInt()-1).useSkill(this, enemies);
                break;
            case 3:
                if (inventory.inv.isEmpty()) break;
                List<Item> keys = new ArrayList<>(inventory.inv.keySet());
                for (int i = 0; i < keys.size(); i++) {
                    Item it = keys.get(i);
                    System.out.println(i + " - " + it.getName() + " x" + inventory.inv.get(it));
                }
                int chosen = sc.nextInt();
                if (keys.get(chosen) instanceof CombatItem combatitem) {
                    if (combatitem.getUtility() == CombatItem.Utility.OFFENSIVE) {
                        combatitem.useItem(this, ChooseEnemy(enemies));
                        break;
                    }
                    else if (combatitem.getUtility() == CombatItem.Utility.DEFENSIVE) {
                        combatitem.useItem(this, null);
                        break;
                    }
                }
                if (keys.get(chosen) instanceof Weapon || keys.get(chosen) instanceof Armor || keys.get(chosen) instanceof Artifact){
                    inventory.EquipItem(keys.get(chosen), this);
                }
                break;
            case 4:
                if (stamina < maxStamina) RecoverSecPoint("stamina", Math.max(3, maxStamina), true);
                if (mind < maxMind) RecoverSecPoint("mind", Math.max(3, maxMind), true);
                HealPlayer(4);
                break;
        }

    }

    public Enemy ChooseEnemy(List<Enemy> enemies) {
        if (enemies.size() > 1) {
            System.out.println("Escolha o alvo:");
            for (int i = 0; i < enemies.size(); i++) {
                Enemy e = enemies.get(i);
                System.out.println((i + 1) + " - " + e.getName() + " (" + e.getHealthPoints() + " HP)");
            }
            int index = sc.nextInt() - 1;
            if (index < 0 || index >= enemies.size()) {
                System.out.println("Alvo inválido!");
                return null;
            }
            return enemies.get(index);
        }
        return enemies.getFirst();
    }

    public void AttackEnemy(Enemy target) {
        target.HarmEnemy(getDamage());
    }

    public void Sleep(int mili) {
        try {
            Thread.sleep(mili); // 1000 milissegundos = 1 segundos
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void GainSecs() {
        RecoverSecPoint("mind", mindGain, true);
        RecoverSecPoint("stamina", staminaGain, true);
    }

    // <editor-fold desc="Get e Set">
    public void GameOver(){
        System.out.println("Game Over!");
        location = Location.VILLAGE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = Math.min(healthPoints, maxHealthPoints);
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = Math.min(stamina, maxStamina);
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public int getStaminaGain() {
        return staminaGain;
    }

    public void setStaminaGain(int staminaGain) {
        this.staminaGain = staminaGain;
    }

    public int getMind() {
        return mind;
    }

    public void setMind(int mind) {
        this.mind = Math.min(mind, maxMind);
    }

    public int getMaxMind() {
        return maxMind;
    }

    public void setMaxMind(int maxMind) {
        this.maxMind = maxMind;
    }

    public int getMindGain() {
        return mindGain;
    }

    public void setMindGain(int mindGain) {
        this.mindGain = mindGain;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXP() {
        return XP;
    }

    public void gainXP(int XP) {
        this.XP += XP;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDamage() {
        if (inventory.artifact.getEffectType() == Artifact.EffectType.INC_DAMAGE)
            return inventory.weapon.getDamage() + inventory.artifact.getIntensity();
        return inventory.weapon.getDamage();
    }
    // </editor-fold>

}
