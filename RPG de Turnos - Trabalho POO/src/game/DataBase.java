package game;

import game.Enemies.Enemy;
import game.Enemies.Oni;
import game.Enemies.Tengu;
import game.Enemies.Tsukumogami;
import game.Items.*;
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
    public static Skill APODRECER = new Skill("Apodrecer", "mind", 3, 5, Skill.SkillType.APPLY_EFFECT, POISON);
    public static Skill REGENERAR = new Skill("Regenerar", "mind", 3, 5, Skill.SkillType.HEAL);
    public static Skill COAGULAR = new Skill("Coagular", "mind", 3, 4, Skill.SkillType.REMOVE_EFFECT, POISON);
    public static Skill CICATRIZAR = new Skill("Cicatrizar", "mind", 3, 5, Skill.SkillType.REMOVE_EFFECT, BLEED);
    public static Skill ESQUECER = new Skill("Esquecer", "mind", 3, 5, Skill.SkillType.REMOVE_EFFECT, HORROR);


    public static Skill CORTE_AMPLO = new Skill("Corte Amplo", "stamina", 3, 3, Skill.SkillType.DAMAGE_ALL);
    public static Skill PARTIR = new Skill("Partir", "stamina", 3, 5, Skill.SkillType.APPLY_EFFECT, BLEED);
    public static Skill P_ARROW = new Skill("Flecha Perfurante", "stamina", 3, 5, Skill.SkillType.PIERCING);

    // Armas
    public static Weapon BARE_HANDS = new Weapon("Faixas p/ Punhos", 0, 1);
    public static Weapon KATANA = new Weapon("Katana", 5, 8, DataBase.CORTE_AMPLO);
    public static Weapon DAGGER = new Weapon("Adaga Tanto", 2, 4, DataBase.PARTIR);
    public static Weapon ONO = new Weapon("Machacinha Ono", 2, 5, DataBase.PARTIR);
    public static Weapon BOW = new Weapon("Arco Yumi", 2, 4, DataBase.PARTIR);



    // Armaduras
    public static Armor DIRTY_CLOTHES = new Armor("Roupas Sujas", 1, 0);
    public static Armor KIMONO = new Armor("Kimono", 4, 1);
    public static Armor CHAINMAIL = new Armor("Malha de Ferro", 10, 4);

    // Artefatos
    public static Artifact FLUTE = new Artifact("Flauta Simples", 12, Artifact.EffectType.INC_SEC_GAIN, 1, "mind");
    public static Artifact BLOOD_STONE = new Artifact("Pedra de Sangue", 12, Artifact.EffectType.INC_HP, 5);

    // Combate
    public static CombatItem BANDAGE = new CombatItem("Bandagem", 2, CombatItem.EffectType.REMOVE_BLEED, 3, CombatItem.Utility.DEFENSIVE);
    public static CombatItem PURE_WATER = new CombatItem("Água Pura", 2, CombatItem.EffectType.REMOVE_POISON, 3, CombatItem.Utility.DEFENSIVE);
    public static CombatItem HERBAL_CREAM = new CombatItem("Pomada de Ervas", 3, CombatItem.EffectType.REMOVE_BURN, 3, CombatItem.Utility.DEFENSIVE);
    public static CombatItem SENCHA = new CombatItem("Chá Sencha", 2, CombatItem.EffectType.GIVE_MIND, 3, CombatItem.Utility.DEFENSIVE);
    public static CombatItem MATCHA = new CombatItem("Chá Matcha", 3, CombatItem.EffectType.GIVE_MIND, 5, CombatItem.Utility.DEFENSIVE);
    public static CombatItem SAKE_BOTTLE = new CombatItem("Garrafa de Sake", 2, CombatItem.EffectType.GIVE_STAMINA, 3, CombatItem.Utility.DEFENSIVE);
    public static CombatItem SOCHU_BOTTLE = new CombatItem("Garrafa de Sochu", 3, CombatItem.EffectType.GIVE_STAMINA, 5, CombatItem.Utility.DEFENSIVE);
    public static CombatItem KELP_SOUP = new CombatItem("Sopa de Algas", 2, CombatItem.EffectType.HEAL, 5, CombatItem.Utility.DEFENSIVE);
    public static CombatItem UMEBOSHI = new CombatItem("Ameixas em Conserva", 3, CombatItem.EffectType.HEAL, 8, CombatItem.Utility.DEFENSIVE);
    public static CombatItem GYUTAN = new CombatItem("Línua de Boi Grelhada", 3, CombatItem.EffectType.HEAL, 12, CombatItem.Utility.DEFENSIVE);

    public static CombatItem KUNAI = new CombatItem("Kunai de Arremesso", 3, CombatItem.EffectType.DEAL_DAMAGE, 4, CombatItem.Utility.OFFENSIVE);
    public static CombatItem SHURIKENS = new CombatItem("Shurikens", 3, CombatItem.EffectType.DEAL_DAMAGE, 4, CombatItem.Utility.OFFENSIVE);
    public static CombatItem POISONED_DARTS = new CombatItem("Dardos Envenenados", 3, CombatItem.EffectType.DEAL_DAMAGE, 4, CombatItem.Utility.OFFENSIVE);
    public static CombatItem EMBERS = new CombatItem("Brasas", 3, CombatItem.EffectType.DEAL_DAMAGE, 4, CombatItem.Utility.OFFENSIVE);


    // Listas
    public static List<Weapon> ALL_WEAPONS_R1 = new ArrayList<>(List.of(
            KATANA,
            DAGGER
    ));
    public static List<Armor> ALL_ARMORS_R1 = new ArrayList<>(List.of(
            KIMONO,
            CHAINMAIL
    ));
    public static List<Artifact> ALL_ARTIFACTS_R1 = new ArrayList<>(List.of(
            FLUTE,
            BLOOD_STONE
    ));
    public static List<CombatItem> ALL_COMBATITEMS_R1 = new ArrayList<>(List.of(
            BANDAGE,
            KUNAI
    ));

    public static List<Item> DesolatedTownItems = new ArrayList<>(List.of(
            BANDAGE,
            KUNAI

    ));

    public static List<Item> HauntedTempleItems = new ArrayList<>(List.of(
            BANDAGE,
            KUNAI

    ));


    // Inimigos

    public static Oni ONI = new Oni();
    public static Tengu TENGU = new Tengu();
    public static Tsukumogami TSUKUGAMI = new Tsukumogami();

    public static List<Enemy> HauntedTempleEnemies = new ArrayList<>(List.of(
            TSUKUGAMI,
            ONI
    ));

    public static List<Enemy> DesolateTownEnemies = new ArrayList<>(List.of(
            TSUKUGAMI,
            TENGU
    ));
}
