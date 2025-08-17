package game;

import game.Enemies.Enemy;
import game.StatusEffect.StatusEffect;

import java.util.List;

public class Skill {
    private String name;
    private int cost;
    private String costType;
    private final int intensity;

    public enum SkillType {
        DAMAGE,
        APPLY_EFFECT,
        HEAL,
        REMOVE_EFFECT,

        DAMAGE_ALL
    }

    public SkillType skillType;
    private StatusEffect effectType;

    public Skill(String name, String costType, int cost, int intensity, SkillType skillType) {
        this.name = name;
        this.costType = costType;
        this.cost = cost;
        this.intensity = intensity;
        this.skillType = skillType;
    }

    public Skill(String name, String costType, int cost, int intensity, SkillType skillType, StatusEffect effect) {
        this.name = name;
        this.costType = costType;
        this.cost = cost;
        this.intensity = intensity;
        this.skillType = skillType;
        this.effectType = effect;
    }

    public void useSkill(Player player, List<Enemy> enemies) {
        // Habilidades
        switch(skillType) {
            // Magias
            case HEAL:
                player.HealPlayer(intensity);
                break;
            case DAMAGE:
                player.ChooseEnemy(enemies).HarmEnemy(intensity);
                break;
            case APPLY_EFFECT:
                player.ChooseEnemy(enemies).ApplyEffect(effectType, intensity);
                break;
            case REMOVE_EFFECT:
                player.RemoveEffect(effectType.getType());
                break;
            // Armas
            case DAMAGE_ALL:
                for (Enemy e : enemies) e.HarmEnemy(intensity);
                break;
        }
        player.SpendSecPoint(costType, cost);
    }

    public String getName() {
        return name;
    }

    public SkillType getSkillType() {
        return skillType;
    }
}
