package game.Items;

import game.Enemies.Enemy;
import game.Player;
import game.StatusEffect.StatusEffect;

public class CombatItem extends Item{
    public enum EffectType {
        HEAL,
        GIVE_STAMINA,
        GIVE_MIND,
        REMOVE_BLEED,
        REMOVE_POISON,
        DEAL_DAMAGE
    }

    public enum Utility {
        OFFENSIVE,
        DEFENSIVE,
    }




    private final EffectType effectType;
    private final int effectValue;
    private final Utility type;

    public CombatItem(String name, int price, EffectType effectType, int effectValue, Utility type) {
        super(name, price);
        this.effectType = effectType;
        this.effectValue = effectValue;
        this.type = type;
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
            case REMOVE_BLEED:
                p.RemoveEffect(StatusEffect.Type.BLEED);
                p.HealPlayer(effectValue);
                break;
            case REMOVE_POISON:
                p.RemoveEffect(StatusEffect.Type.POISON);
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
