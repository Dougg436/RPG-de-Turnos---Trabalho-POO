package game.GameData;

import game.GameData.Enemies.*;
import game.GameData.Enemies.Boss.*;
import game.GameData.Items.*;
import game.GameControlView.Skill;
import game.GameData.StatusEffect.StatusEffect;

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
    public static Skill TERMINAR = new Skill("Terminar", "mind", 3, 5, Skill.SkillType.TERMINATE);
    public static Skill ESQUECER = new Skill("Esquecer", "mind", 3, 5, Skill.SkillType.REMOVE_EFFECT, HORROR);

    public static Skill CORTE_AMPLO = new Skill("Corte Amplo", "stamina", 3, 3, Skill.SkillType.DAMAGE_ALL);
    public static Skill PARTIR = new Skill("Partir", "stamina", 3, 5, Skill.SkillType.APPLY_EFFECT, BLEED);
    public static Skill P_FLECHA= new Skill("Flecha Perfurante", "stamina", 3, 4, Skill.SkillType.PIERCING);
    public static Skill CORTE_DIAGONAL= new Skill("Corte Diagonal", "stamina", 4, 6, Skill.SkillType.DAMAGE_ALL);
    public static Skill ESTOCADA= new Skill("Estocada", "stamina", 3, 6, Skill.SkillType.PIERCING);

    // Armas
    public static Weapon BARE_HANDS = new Weapon("Faixas p/ Punhos", 0, 1);
    public static Weapon KATANA = new Weapon("Katana", 5, 8, DataBase.CORTE_AMPLO);
    public static Weapon DAGGER = new Weapon("Adaga Tanto", 2, 4, DataBase.PARTIR);
    public static Weapon ONO = new Weapon("Machacinha Ono", 2, 5, DataBase.PARTIR);
    public static Weapon BOW = new Weapon("Arco Yumi", 4, 4, DataBase.P_FLECHA);
    public static Weapon NAGINATA = new Weapon("Arco Yumi", 5, 6, DataBase.ESTOCADA);
    public static Weapon ODACHI = new Weapon("Arco Yumi", 6, 8, DataBase.CORTE_DIAGONAL);
    public static Weapon KAMA = new Weapon("Arco Yumi", 3, 4, DataBase.PARTIR);

    // Armaduras
    public static Armor DIRTY_CLOTHES = new Armor("Roupas Sujas", 1, 0);
    public static Armor KIMONO = new Armor("Kimono", 4, 1);
    public static Armor CHAINMAIL = new Armor("Malha de Ferro", 10, 4);
    public static Armor O_YOROI = new Armor("Armadura Grande", 15, 6);

    // Artefatos
    public static Artifact FLUTE = new Artifact("Flauta Simples", 6, Artifact.EffectType.INC_SEC_GAIN, 1, "mind");
    public static Artifact BLOOD_STONE = new Artifact("Pedra de Sangue", 12, Artifact.EffectType.INC_HP, 5);
    public static Artifact WHETSTONE = new Artifact("Pedra de Amolar", 10, Artifact.EffectType.INC_DAMAGE, 4);
    public static Artifact STATUE = new Artifact("Estatueta Adornada", 12, Artifact.EffectType.INC_SEC, 4, "mind");
    public static Artifact NECKLACE = new Artifact("Colar Perdido", 12, Artifact.EffectType.INC_SEC_GAIN, 1, "stamina");
    public static Artifact INCENSE = new Artifact("Incenso Atomático", 12, Artifact.EffectType.INC_SEC_GAIN, 2, "mind");

    // Item de Combate
    public static CombatItem BANDAGE = new CombatItem("Bandagem", 2, CombatItem.EffectType.REMOVE_EFFECT, 3, CombatItem.Utility.DEFENSIVE, BLEED);
    public static CombatItem PURE_WATER = new CombatItem("Água Pura", 2, CombatItem.EffectType.REMOVE_EFFECT, 3, CombatItem.Utility.DEFENSIVE, POISON);
    public static CombatItem HERBAL_CREAM = new CombatItem("Pomada de Ervas", 3, CombatItem.EffectType.REMOVE_EFFECT, 3, CombatItem.Utility.DEFENSIVE, BURN);
    public static CombatItem SENCHA = new CombatItem("Chá Sencha", 2, CombatItem.EffectType.GIVE_MIND, 3, CombatItem.Utility.DEFENSIVE);
    public static CombatItem MATCHA = new CombatItem("Chá Matcha", 3, CombatItem.EffectType.GIVE_MIND, 5, CombatItem.Utility.DEFENSIVE);
    public static CombatItem SAKE_BOTTLE = new CombatItem("Garrafa de Sake", 2, CombatItem.EffectType.GIVE_STAMINA, 3, CombatItem.Utility.DEFENSIVE);
    public static CombatItem SOCHU_BOTTLE = new CombatItem("Garrafa de Sochu", 3, CombatItem.EffectType.GIVE_STAMINA, 5, CombatItem.Utility.DEFENSIVE);
    public static CombatItem KELP_SOUP = new CombatItem("Sopa de Algas", 2, CombatItem.EffectType.HEAL, 5, CombatItem.Utility.DEFENSIVE);
    public static CombatItem UMEBOSHI = new CombatItem("Ameixas em Conserva", 3, CombatItem.EffectType.HEAL, 8, CombatItem.Utility.DEFENSIVE);
    public static CombatItem GYUTAN = new CombatItem("Língua de Boi Grelhada", 3, CombatItem.EffectType.HEAL, 12, CombatItem.Utility.DEFENSIVE);

    public static CombatItem KUNAI = new CombatItem("Kunai de Arremesso", 3, CombatItem.EffectType.DEAL_DAMAGE, 4, CombatItem.Utility.OFFENSIVE);
    public static CombatItem SHURIKENS = new CombatItem("Shurikens", 3, CombatItem.EffectType.DEAL_DAMAGE, 4, CombatItem.Utility.OFFENSIVE);
    public static CombatItem POISONED_DARTS = new CombatItem("Dardos Envenenados", 3, CombatItem.EffectType.DEAL_DAMAGE, 4, CombatItem.Utility.OFFENSIVE);
    public static CombatItem EMBERS = new CombatItem("Brasas", 3, CombatItem.EffectType.DEAL_DAMAGE, 4, CombatItem.Utility.OFFENSIVE);


    // Listas
    public static List<Weapon> ALL_WEAPONS_R1 = new ArrayList<>(List.of(
            KATANA,
            DAGGER,
            ONO,
            BOW,
            NAGINATA,
            ODACHI,
            KAMA
    ));
    public static List<Armor> ALL_ARMORS_R1 = new ArrayList<>(List.of(
            KIMONO,
            CHAINMAIL,
            O_YOROI
    ));
    public static List<Artifact> ALL_ARTIFACTS_R1 = new ArrayList<>(List.of(
            FLUTE,
            BLOOD_STONE,
            STATUE,
            WHETSTONE,
            NECKLACE,
            INCENSE
    ));
    public static List<CombatItem> ALL_COMBATITEMS_R1 = new ArrayList<>(List.of(
            BANDAGE,
            KUNAI,
            PURE_WATER,
            HERBAL_CREAM,
            SENCHA,
            MATCHA,
            SAKE_BOTTLE,
            SOCHU_BOTTLE,
            KELP_SOUP,
            UMEBOSHI,
            GYUTAN,
            POISONED_DARTS,
            EMBERS,
            SHURIKENS
    ));

    public static List<Item> DesolatedTownItems = new ArrayList<>(List.of(
            BANDAGE,
            KUNAI,
            SENCHA,
            SOCHU_BOTTLE,
            UMEBOSHI

    ));

    public static List<Item> HauntedTempleItems = new ArrayList<>(List.of(
            BANDAGE,
            KUNAI,
            HERBAL_CREAM,
            PURE_WATER,
            SAKE_BOTTLE,
            KELP_SOUP,
            MATCHA
    ));

    public static List<Item> GladeItems = new ArrayList<>(List.of(
            BANDAGE,
            KUNAI,
            HERBAL_CREAM,
            SHURIKENS

    ));


    // Inimigos

    public static Oni ONI = new Oni();
    public static Tengu TENGU = new Tengu();
    public static Tsukumogami TSUKUMOGAMI = new Tsukumogami();
    public static UshiOni USHIONI = new UshiOni();
    public static Kappa KAPPA = new Kappa();
    public static Wanyudo WANYUDO = new Wanyudo();
    public static Yosuzume YOSUZUME = new Yosuzume();
    public static YukiOnna YUKIONNA = new YukiOnna();
    public static Mokumokuren MOKUMOKUREN = new Mokumokuren();

    // Bosses
    public static ToughOni TOUGH_ONI = new ToughOni();
    public static KarasuTengu KARASU_TENGU = new KarasuTengu();
    public static BakeKujira BAKE_KUJIRA = new BakeKujira();
    public static Umibozu UMIBOZU = new Umibozu();
    public static Gashadokuro GASHADOKURO = new Gashadokuro();
    public static YamataNoOrochi OROCHI = new YamataNoOrochi();


    public static List<Enemy> HauntedTempleEnemies = new ArrayList<>(List.of(
            TENGU,
            TOUGH_ONI,
            USHIONI,
            MOKUMOKUREN
    ));

    public static List<Enemy> DesolateTownEnemies = new ArrayList<>(List.of(
            TSUKUMOGAMI,
            ONI,
            KAPPA,
            YUKIONNA,
            WANYUDO
    ));

    public static List<Enemy> GladeEnemies = new ArrayList<>(List.of(
            TSUKUMOGAMI,
            ONI,
            TENGU,
            KAPPA,
            YOSUZUME
    ));
}
