package game.StatusEffect;

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
