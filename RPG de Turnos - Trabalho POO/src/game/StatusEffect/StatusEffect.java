package game.StatusEffect;

import game.Enemies.Enemy;
import game.Player;

public class StatusEffect {
    public enum Type {
        BLEED("está sangrando!"),
        POISON("está envenenado!"),
        BURN("está queimando!"),
        FROST("está congelando! -1 de Stamina/Turno"),
        HORROR("está horrorizado! -1 de Mente/Turno");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
    private final Type type;
    private int duration;
    private final int intensity;

    public StatusEffect(Type type, int duration, int intensity) {
        this.type = type;
        this.duration = duration;
        this.intensity = intensity;
    }

    public void ApplyTurnEffect(Player target) {
        switch(type) {
            case BLEED:
            case POISON:
            case BURN:
                target.HarmPlayerPiercing(intensity);
                break;
            case FROST:
                System.out.println(target.getName() + " está congelado e não recupera Stamina!");
                target.SpendSecPoint("stamina", 1);
                break;

            case HORROR:
                System.out.println(target.getName() + " está em horror e não recupera Mente!");
                target.SpendSecPoint("mind", 1);
                break;

        }
        duration--;
    }

    public void ApplyTurnEffectEnemy(Enemy target) {
        switch(type) {
            case BLEED:
            case POISON:
            case BURN:
                target.HarmEnemyPiercing(intensity);
                break;
            case FROST:
                System.out.println(target.getName() + " está congelado e não recupera Stamina!");
                // target.SpendSecPoint("stamina", 1);
                break;

            case HORROR:
                System.out.println(target.getName() + " está em horror e não recupera Mente!");
                // target.SpendSecPoint("mind", 1);
                break;

        }
        duration--;
    }

    public boolean IsEnded() {return duration <= 0;}

    public int getDuration() {
        return duration;
    }

    public int getIntensity() {
        return intensity;
    }

    public Type getType() {return type;}
}
