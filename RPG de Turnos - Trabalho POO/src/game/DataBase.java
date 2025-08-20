package game;

import game.Items.Armor;
import game.Items.Artifact;
import game.Items.CombatItem;
import game.Items.Weapon;
import game.StatusEffect.StatusEffect;

import java.util.ArrayList;
import java.util.List;

public class DataBase {



    // Efeitos
    public static StatusEffect BLEED = new StatusEffect(StatusEffect.Type.BLEED, 3, 1);
    public static StatusEffect POISON = new StatusEffect(StatusEffect.Type.POISON, 3, 1);
    public static StatusEffect BURN = new StatusEffect(StatusEffect.Type.BURN, 3, 1);
    public static StatusEffect FROST = new StatusEffect(StatusEffect.Type.FROST, 3, 1);
    public static StatusEffect HORROR = new StatusEffect(StatusEffect.Type.HORROR, 3, 1);

    // Habilidades
    public static Skill IMOLAR = new Skill("Imolar", "mind", 3, 4, Skill.SkillType.APPLY_EFFECT, BURN);
    public static Skill REGENERAR = new Skill("Regenerar", "mind", 3, 5, Skill.SkillType.HEAL);

    public static Skill CORTE_AMPLO = new Skill("Corte Amplo", "stamina", 3, 3, Skill.SkillType.DAMAGE_ALL);
    public static Skill PARTIR = new Skill("Partir", "stamina", 3, 5, Skill.SkillType.APPLY_EFFECT, BLEED);

    // Armas
    public static Weapon BARE_HANDS = new Weapon("Faixas p/ Punhos", 0, 1);
    public static Weapon KATANA = new Weapon("Katana", 5, 4, DataBase.CORTE_AMPLO);
    public static Weapon DAGGER = new Weapon("Adaga", 2, 3, DataBase.PARTIR);


    // Armaduras
    public static Armor DIRTY_CLOTHES = new Armor("Roupas Sujas", 1, 0);
    public static Armor KIMONO = new Armor("Kimono", 4, 1);
    public static Armor CHAINMAIL = new Armor("Malha de Ferro", 10, 4);

    // Artefatos
    public static Artifact FLUTE = new Artifact("Flauta Simples", 12, Artifact.EffectType.INC_SEC_GAIN, 1, "mind");
    public static Artifact BLOOD_STONE = new Artifact("Pedra de Sangue", 12, Artifact.EffectType.INC_HP, 5);

    // Combate
    public static CombatItem BANDAGE = new CombatItem("Bandagem", 3, CombatItem.EffectType.REMOVE_BLEED, 3, CombatItem.Utility.DEFENSIVE);
    public static CombatItem KUNAI = new CombatItem("Kunai de Arremesso", 3, CombatItem.EffectType.DEAL_DAMAGE, 4, CombatItem.Utility.OFFENSIVE);

    // Listas
    public static List<Weapon> ALL_WEAPONS = new ArrayList<>(List.of(
            BARE_HANDS,
            KATANA,
            DAGGER
    ));
    public static List<Armor> ALL_ARMORS = new ArrayList<>(List.of(
            KIMONO,
            CHAINMAIL
    ));
    public static List<Artifact> ALL_ARTIFACTS = new ArrayList<>(List.of(
            FLUTE,
            BLOOD_STONE
    ));
    public static List<CombatItem> ALL_COMBATITEMS = new ArrayList<>(List.of(
            BANDAGE,
            KUNAI
    ));
}
