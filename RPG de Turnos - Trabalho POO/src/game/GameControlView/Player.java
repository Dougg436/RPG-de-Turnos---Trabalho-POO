package game.GameControlView;

import game.GameData.Enemies.Enemy;
import game.GameData.Items.*;
import game.GameData.StatusEffect.StatusEffect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player implements Serializable {
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
    public int progress;
    public int respect;
    private static final Random random = new Random();
    List<StatusEffect> effects = new ArrayList<>();
    List<Skill> skills = new ArrayList<>();

    transient Scanner sc;
    Inventory inventory;

    private DungeonVillage dungeonVillage;

    public void ShowStats() {
        System.out.println(
                "=====|{ " + name.toUpperCase() +" }|=====\n" +
                        "Nível: " + level + "\n" +
                        "Honra: " + XP + "\n\n" +
                        "Saúde: " + healthPoints + " / " + maxHealthPoints + "\n" +
                        "Vigor: " + stamina + " / " + maxStamina + "\n" +
                        "Mente: " + mind + " / " + maxMind + "\n" +
                        "Defesa: " + defense + "\n" +
                        "Progresso: " + progress
        );
    }

    public Player(int maxhp, int maxsp, int maxmp, String name, int level, int progress, int respect, Scanner sc){
        this.maxHealthPoints = maxhp;
        this.maxStamina = maxsp;
        this.maxMind = maxmp;
        this.name = name;
        this.level = level;
        this.healthPoints = maxhp;
        this.stamina = maxsp;
        this.mind = maxmp;
        this.progress = progress;
        this.respect = respect;
        this.sc = sc;
        this.inventory = new Inventory(sc);
    }

    public void StartGame() {
        System.out.println("Você entra no barco, junto de um homem...");
        Sleep(1000);
        System.out.println("Homem Estranho: Quem você seria?");
        Sleep(1000);
        System.out.println("Você: Eu não tenho nome");
        Sleep(750);
        System.out.println("Homem Estranho: Ah, mas é claro que tem!");
        Sleep(1000);
        System.out.println("Crie seu novo Nome:");
        this.name = sc.nextLine();
        dungeonVillage.ReturnToVillage(false, this);
    }

    public void HarmPlayer(int damage) { // Dano no Player
        int finalDamage = Math.max(damage - defense, 0);
        healthPoints = Math.max(healthPoints - finalDamage, 0);
    }

    public void HarmPlayerPiercing(int damage) { // Dano que ignora Defense no Player
        int finalDamage = Math.max(damage, 0);
        healthPoints = Math.max(healthPoints - finalDamage, 0);
    }

    public void HealPlayer(int heal) { // Cura no Player
        int oldHP = healthPoints;
        healthPoints = Math.min(healthPoints + heal, maxHealthPoints);
        int healedHP = healthPoints - oldHP;
        if (healedHP > 0) {
            System.out.println(name + " recupera (+" + healedHP + ") de vida!");
        } else {
            System.out.println(name + " já está com a vida cheia!");
        }
    }

    public void SpendSecPoint(String sec, int qnt) { // Gastar Mente ou Stamina
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

    public void RecoverSecPoint(String sec, int qnt, boolean quiet) { // Recuperar Mente ou Stamina
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

    public void ApplyEffect(StatusEffect effect) { // Aplica StatusEffect no Player
        effects.add(effect);
        System.out.println(name + " " + effect.getType().getMessage());
    }

    public void RemoveEffect(StatusEffect.Type type) { // Remove Status Effect no Player
        effects.removeIf(e -> e.getType() == type);
    }

    public void ProcessEffects() { // Processa os StatusEffects do Player
        List<StatusEffect> ended = new ArrayList<>();
        for (StatusEffect e : effects) {
            e.ApplyTurnEffect(this);
            if (e.IsEnded()) ended.add(e);
        }

        effects.removeAll(ended);
    }

    public void CheckLevel() { // Checa e melhora o Nível do Player
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

    public void TakeTurn(List<Enemy> enemies) { // Turno de Combate do PLayer
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
            case 1: // Escolher e causar Dano
                Enemy target = ChooseEnemy(enemies);
                if (target != null) {AttackEnemy(target);}
                break;

            case 2: // Skills
                if (skills.isEmpty()) {
                    System.out.println("Nenhuma habilidade disponível!");
                    break;
                }
                for (int i = 0; i < skills.size(); i++) {
                    Skill sk = skills.get(i);
                    System.out.println((i + 1) + " - " + sk.getName());
                }
                int skillChoice = sc.nextInt() - 1;
                if (skillChoice < 0 || skillChoice >= skills.size()) {
                    System.out.println("Escolha inválida!");
                    break;
                }
                skills.get(skillChoice).useSkill(this, enemies);
                break;


            case 3: // Inventário
                if (inventory.inv.isEmpty()) {
                    System.out.println("Inventário vazio!");
                    break;
                }
                List<Item> keys = new ArrayList<>(inventory.inv.keySet());
                for (int i = 0; i < keys.size(); i++) {
                    Item it = keys.get(i);
                    System.out.println((i + 1) + " - " + it.getName() + " x" + inventory.inv.get(it));
                }
                int chosen = sc.nextInt() - 1;
                if (chosen < 0 || chosen >= keys.size()) {
                    System.out.println("Escolha inválida!");
                    break;
                }
                Item selectedItem = keys.get(chosen);
                if (selectedItem instanceof CombatItem combatitem) {
                    if (combatitem.getUtility() == CombatItem.Utility.OFFENSIVE) {
                        combatitem.useItem(this, ChooseEnemy(enemies));
                        break;
                    } else if (combatitem.getUtility() == CombatItem.Utility.DEFENSIVE) {
                        combatitem.useItem(this, null);
                        break;
                    }
                }
                if (selectedItem instanceof Weapon || selectedItem instanceof Armor || selectedItem instanceof Artifact) {
                    inventory.EquipItem(selectedItem, this);
                }
                break;

            case 4: // Recuperar HP, SP e MP
                if (stamina < maxStamina) RecoverSecPoint("stamina", Math.max(3, maxStamina), true);
                if (mind < maxMind) RecoverSecPoint("mind", Math.max(3, maxMind), true);
                HealPlayer(4);
                break;
        }

    }

    public Enemy ChooseEnemy(List<Enemy> enemies) { // Escolhe um Enemy
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

    public void AttackEnemy(Enemy target) { // Causa Dano num Enemy, com chance de Critar
        int crit = random.nextInt(1, 21);
        int dmg = random.nextInt(-2, 3);
        if (crit!=20) {
            target.HarmEnemy(getDamage()+dmg);
        }
        else {
            target.HarmEnemy(getDamage()*2);
            System.out.println("Crítico! (x2 de Dano)");
        }
    }

    public void Sleep(int mili) { // Apenas atrasa o código para efeitos visuais
        try {
            Thread.sleep(mili); // 1000 milissegundos = 1 segundos
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void GainSecs() { // Recupera os Secs por rodada
        RecoverSecPoint("mind", mindGain, true);
        RecoverSecPoint("stamina", staminaGain, true);
    }

    public void EndGame() {
        System.out.println("Você conseguiu! Derrotou todas as Forças da Natureza que pertubavam a Ilha!");

    }

    // <editor-fold desc="Get/Set e Outros">
    public void GameOver(){
        System.out.println(name + " foi derrotado...\n");
        Sleep(1000);
        System.out.println("Seu corpo cai sobre um riacho, e você acorda na fonte da Vila");
        healthPoints = maxHealthPoints;
        stamina = maxStamina;
        mind = maxMind;
        dungeonVillage.ReturnToVillage(false, this);
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

    public void setDungeonVillage(DungeonVillage dungeonVillage) {
        this.dungeonVillage = dungeonVillage;
    }

    // </editor-fold>

}
