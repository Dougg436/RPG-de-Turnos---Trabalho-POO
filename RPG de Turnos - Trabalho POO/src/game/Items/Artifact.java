package game.Items;

import game.Player;

public class Artifact extends Item{
    public enum EffectType {
        INC_HP,
        INC_SEC,
        INC_SEC_GAIN,
        INC_DEF,
        INC_DAMAGE,
    }

    private final EffectType effectType;
    private final int intensity;
    private String sec;

    public Artifact(String name, int price, EffectType type, int intensity) {
        super(name, price);
        this.effectType = type;
        this.intensity = intensity;
    }

    public Artifact(String name, int price, EffectType type, int intensity, String sec) {
        super(name, price);
        this.effectType = type;
        this.intensity = intensity;
        this.sec = sec;
    }


    public void OnEquip(Player player) {
        switch (effectType){
            case INC_HP:
                player.setMaxHealthPoints(player.getMaxHealthPoints() + intensity);
                break;
            case INC_DEF:
                player.setDefense(player.getDefense() + intensity);
                break;
            case INC_SEC:
                if (sec.equals("mind")) player.setMaxMind(player.getMaxMind() + intensity);
                else if (sec.equals("stamina")) player.setMaxStamina(player.getMaxStamina() + intensity);
            case INC_SEC_GAIN:
                if (sec.equals("mind")) player.setMindGain(player.getMindGain() + intensity);
                else if (sec.equals("stamina")) player.setStaminaGain(player.getStaminaGain() + intensity);
        }
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public int getIntensity() {
        return intensity;
    }

    @Override
    public String getType() {return "Artifact";}
}
