package game.Enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import game.Player;
import game.StatusEffect.StatusEffect;

public abstract class Enemy {
    private int healthPoints;
    private int maxHealthPoints;
    private final String name;
    private int damage;
    private int defense;
    private int honorgained;
    private List<StatusEffect> effects = new ArrayList<>();
    private static final Random random = new Random();

    enum Attacks {
        ATTACK,
        SPECIAL
    }

    public Enemy(int maxHealthPoints, String name, int damage, int defense) {
        this.maxHealthPoints = maxHealthPoints;
        this.healthPoints = maxHealthPoints;
        this.name = name;
        this.damage = damage;
        this.defense = defense;
    }

    public void HarmEnemy(int damage) {
        int finalDamage = Math.max(damage - defense, 0);
        healthPoints = Math.max(healthPoints - finalDamage, 0);
        System.out.println(name + " recebe /" + finalDamage + "/ de dano!");
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
                int harm1 = this.damage - target.getDefense();
                target.HarmPlayer(harm1);
                System.out.println(name + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case SPECIAL:
                int harm2 = (this.damage*2) - target.getDefense();
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

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public int getHonorgained() {
        return honorgained;
    }

    public int getDamage() {
        return damage;
    }
}
