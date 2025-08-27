package game.GameData.Enemies.Boss;

import game.GameControlView.Player;
import game.GameData.DataBase;
import game.GameData.Enemies.Enemy;
import game.GameData.Items.Item;

import java.util.Random;

public class YamataNoOrochi extends Enemy implements Boss{
    private static final Random random = new Random();
    public YamataNoOrochi() {
        super(100, "Yamata No Orochi", 12, 8, 30);
    }

    enum Attacks {
        ATTACK,
        EIGHT_BITES,
        EIGHT_WHIPS,
        RENAISSANCE
    }

    @Override
    public void TakeTurn(Player target) {
        YamataNoOrochi.Attacks choice = YamataNoOrochi.Attacks.values()[random.nextInt(YamataNoOrochi.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case EIGHT_BITES:
                target.HarmPlayerPiercing(this.getDamage() + random.nextInt(3));
                target.ApplyEffect(DataBase.BLEED);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Oito Mordidas! (Ignora Armadura) [/" + this.getDamage() + "/ de Dano]");
                break;
            case EIGHT_WHIPS:
                int harm3 = this.getDamage() + 5;
                target.HarmPlayer(harm3);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Oitos Laços! [/" + harm3 + "/ de Dano]");
            case RENAISSANCE:
                System.out.println(this.getName() + " usou Renascença e tentou se curar!");
                HealEnemy(random.nextInt(4));
        }
    }

    @Override
    public void EntraceText() {

    }

    @Override
    public Item getDropItem() {
        return DataBase.WHETSTONE;
    }

    @Override
    public int getDropMoney() {
        return 10;
    }

    @Override
    public Enemy CloneEnemy() {
        return new YamataNoOrochi();
    }
}