package game;

import game.Enemies.Enemy;
import game.StatusEffect.StatusEffect;

import java.util.List;
import java.util.Objects;

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
        TERMINATE,

        DAMAGE_ALL,
        DEFEND,
        PIERCING

    }

    public SkillType skillType;
    private final StatusEffect effectType;

    public Skill(String name, String costType, int cost, int intensity, SkillType skillType) {
        this.name = name;
        this.costType = costType;
        this.cost = cost;
        this.intensity = intensity;
        this.skillType = skillType;
        this.effectType = null;
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
            case TERMINATE:
                Enemy e1 = player.ChooseEnemy(enemies);
                List<StatusEffect> ef = e1.getEffects();
                int harm = 0;
                for (StatusEffect effect : ef) {
                    if (effect.getType() == StatusEffect.Type.BLEED ||
                            effect.getType() == StatusEffect.Type.POISON ||
                            effect.getType() == StatusEffect.Type.BURN) {
                        harm += effect.getIntensity() * effect.getDuration();
                    }
                }
                e1.HarmEnemy(harm);
                break;
            // Armas
            case DAMAGE_ALL:
                for (Enemy e2 : enemies) e2.HarmEnemy(intensity);
                break;
            case DEFEND:
                player.setDefense(intensity + player.getDefense());
                break;
            case PIERCING:
                player.ChooseEnemy(enemies).HarmEnemyPiercing(intensity);
                break;


        }
        player.SpendSecPoint(costType, cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill)) return false;
        Skill skill = (Skill) o;
        return Objects.equals(this.name, skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public SkillType getSkillType() {
        return skillType;
    }
}
