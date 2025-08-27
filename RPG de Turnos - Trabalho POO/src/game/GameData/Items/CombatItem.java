package game.GameData.Items;

import game.GameData.Enemies.Enemy;
import game.GameControlView.Player;
import game.GameData.StatusEffect.StatusEffect;

import java.io.Serializable;

public class CombatItem extends Item implements Serializable {
    public enum EffectType {
        HEAL,
        GIVE_STAMINA,
        GIVE_MIND,
        REMOVE_EFFECT,
        APPLY_EFFECT,
        DEAL_DAMAGE
    }

    public enum Utility {
        OFFENSIVE,
        DEFENSIVE,
    }




    private final EffectType effectType;
    private final int effectValue;
    private final Utility type;
    private StatusEffect statusEffect;

    public CombatItem(String name, int price, EffectType effectType, int effectValue, Utility type) {
        super(name, price);
        this.effectType = effectType;
        this.effectValue = effectValue;
        this.type = type;
    }

    public CombatItem(String name, int price, EffectType effectType, int effectValue, Utility type, StatusEffect statusEffect) {
        super(name, price);
        this.effectType = effectType;
        this.effectValue = effectValue;
        this.type = type;
        this.statusEffect = statusEffect;
    }

    public void useItem(Player p, Enemy e) {
        switch(effectType) {
            case HEAL:
                p.HealPlayer(effectValue);
                break;
            case DEAL_DAMAGE:
                e.HarmEnemy(effectValue);
                break;
            case GIVE_MIND:
                p.RecoverSecPoint("mind", effectValue, false);
                break;
            case GIVE_STAMINA:
                p.RecoverSecPoint("stamina", effectValue, false);
                break;
            case APPLY_EFFECT:
                e.ApplyEffect(statusEffect, effectValue);
                break;
            case REMOVE_EFFECT:
                p.RemoveEffect(statusEffect.getType());
                p.HealPlayer(effectValue);
        }
    }

    public Utility getUtility() {
        return type;
    }

    @Override
    public String getType() {
        return "Combat Item";
    }
}
