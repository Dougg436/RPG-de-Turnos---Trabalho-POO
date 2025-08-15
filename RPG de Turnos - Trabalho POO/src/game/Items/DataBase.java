package game.Items;

import game.Skill;
import game.StatusEffect.StatusEffect;

public class DataBase {
    // Armas
    public static Weapon BARE_HANDS = new Weapon("Punhos", 0, 1);
    public static Weapon KATANA = new Weapon("Katana", 5, 4);
    public static Weapon DAGGER = new Weapon("Adaga", 2, 3);

    // Armaduras
    public static Armor DIRTY_CLOTHES = new Armor("Roupas Sujas", 1, 0);
    public static Armor KIMONO = new Armor("Kimono", 4, 1);
    public static Armor CHAINMAIL = new Armor("Malha de Ferro", 10, 4);

    // Artefatos
    public static Artifact FLUTE = new Artifact("Flauta Simples", 12);
    public static Artifact BLOOD_STONE = new Artifact("Pedra de Sangue", 12);

    // Combate
    public static CombatItem BANDAGE = new CombatItem("Bandagem", 3, CombatItem.EffectType.REMOVE_BLEED, 3, CombatItem.Utility.DEFENSIVE);
    public static CombatItem KUNAI = new CombatItem("Kunai de Arremesso", 3, CombatItem.EffectType.DEAL_DAMAGE, 4, CombatItem.Utility.OFFENSIVE);


    // Efeitos
    public static StatusEffect BLEED = new StatusEffect(StatusEffect.Type.BLEED, 3, 1);
    public static StatusEffect POISON = new StatusEffect(StatusEffect.Type.POISON, 3, 1);
    public static StatusEffect BURN = new StatusEffect(StatusEffect.Type.BURN, 3, 1);
    public static StatusEffect FROST = new StatusEffect(StatusEffect.Type.FROST, 3, 1);
    public static StatusEffect HORROR = new StatusEffect(StatusEffect.Type.HORROR, 3, 1);

    // Habilidades
    public static Skill IMOLAR = new Skill("Imolar", "mind", 2, 4, Skill.SkillType.APPLY_EFFECT);

}
