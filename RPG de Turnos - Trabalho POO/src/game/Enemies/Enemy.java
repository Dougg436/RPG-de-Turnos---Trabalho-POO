package game.Enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import game.Player;
import game.StatusEffect.StatusEffect;

public abstract class Enemy {
    int healthPoints;
    int maxHealthPoints;
    String name;
    int damage;
    int defense;
    List<StatusEffect> effects = new ArrayList<>();
    private static final Random random = new Random();

    public Enemy(int maxHealthPoints, String name, int damage, int defense) {
        this.maxHealthPoints = maxHealthPoints;
        this.healthPoints = maxHealthPoints;
        this.name = name;
        this.damage = damage;
        this.defense = defense;
    }

    public void Attack(Player target) {
        int harm = this.damage - target.getDefense();
        target.HarmPlayer(harm);
    }

    public void EspecialAttack(Player target) {
        int harm = this.damage - target.getDefense();
        target.HarmPlayer(harm);
    }

    public int PickAttack(int numAttacks) {
        return random.nextInt(numAttacks-1);
    }

    public boolean HarmEnemy(int damage) {
        int finalDamage = Math.max(damage - defense, 0);
        healthPoints = Math.max(healthPoints - finalDamage, 0);
        System.out.println(name + " recebe /" + finalDamage + "/ de dano!");
        return isDead();
    }

    public boolean isDead() {
        return healthPoints <= 0;
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
            e.ApplyTurnEffectEnemy(this);
            if (e.IsEnded()) ended.add(e);
        }
        effects.removeAll(ended);
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
