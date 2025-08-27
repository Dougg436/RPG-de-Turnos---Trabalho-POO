package game.GameData.Enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import game.GameControlView.Player;
import game.GameData.StatusEffect.StatusEffect;

public abstract class Enemy {
    private int healthPoints;
    private int maxHealthPoints;
    private final String name;
    private int damage;
    private int defense;
    private int XPgained;
    private List<StatusEffect> effects = new ArrayList<>();
    private static final Random random = new Random();

    enum Attacks {
        ATTACK,
        SPECIAL
    }

    public Enemy(int maxHealthPoints, String name, int damage, int defense, int xp) {
        this.maxHealthPoints = maxHealthPoints;
        this.healthPoints = maxHealthPoints;
        this.name = name;
        this.damage = damage;
        this.defense = defense;
        this.XPgained = xp;
    }

    public void HarmEnemy(int damage) {
        int finalDamage = Math.max(damage - defense, 0);
        healthPoints = Math.max(healthPoints - finalDamage, 0);
        System.out.println(name + " recebe /" + finalDamage + "/ de dano!");
    }
    public void HarmEnemyPiercing(int damage) {
        int finalDamage = Math.max(damage, 0);
        healthPoints = Math.max(healthPoints - finalDamage, 0);
        System.out.println(name + " recebe /" + finalDamage + "/ de dano!");
    }

    public void HealEnemy(int heal) {
        int oldHP = healthPoints;
        healthPoints = Math.min(healthPoints + heal, maxHealthPoints);
        int healedHP = healthPoints - oldHP;
        if (healedHP > 0) {
            System.out.println(name + " recupera (+" + healedHP + ") de vida!");
        } else {
            System.out.println(name + " já está com a vida cheia!");
        }
    }

    public boolean isDead() {
        return healthPoints <= 0;
    }

    public void ApplyEffect(StatusEffect effect, int damage) {
        effects.add(effect);
        HarmEnemy(damage);
        System.out.println(name + " " + effect.getType().getMessage());
    }

    public void TakeTurn(Player target) {
        Attacks choice = Attacks.values()[random.nextInt(Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.damage;
                target.HarmPlayer(harm1);
                System.out.println(name + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case SPECIAL:
                int harm2 = (this.damage*2);
                target.HarmPlayer(harm2);
                System.out.println(name + " atacou " + target.getName() + " com Ataque Especial! [/" + harm2 + "/ de Dano]");
                break;
        }
    }

    public void RemoveEffect(StatusEffect.Type type) {
        effects.removeIf(e -> e.getType() == type);
    }

    public void ProcessEffects() {
        List<StatusEffect> ended = new ArrayList<>();
        for (StatusEffect e : effects) {
            e.ApplyTurnEffectEnemy(this);
            if (e.IsEnded()) ended.add(e);
        }
        effects.removeAll(ended);
    }

    public abstract Enemy CloneEnemy();

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public int getXPgained() {
        return XPgained;
    }

    public int getDamage() {
        return damage;
    }

    public List<StatusEffect> getEffects() {
        return effects;
    }
}
