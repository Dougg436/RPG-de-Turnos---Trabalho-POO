package game.GameData.Enemies.Boss;

import game.GameControlView.Player;
import game.GameData.DataBase;
import game.GameData.Enemies.Enemy;
import game.GameData.Items.Item;

import java.util.Random;

public class Gashadokuro extends Enemy implements Boss{
    private static final Random random = new Random();
    public Gashadokuro() {
        super(60, "Gashadokuro", 8, 8, 35);
    }

    enum Attacks {
        ATTACK,
        DEVOUR,
        GIANT_PUNCH
    }

    @Override
    public void TakeTurn(Player target) {
        Gashadokuro.Attacks choice = Gashadokuro.Attacks.values()[random.nextInt(Gashadokuro.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case DEVOUR:
                target.HarmPlayer(this.getDamage());
                HealEnemy(random.nextInt(3));
                System.out.println(this.getName() + " atacou " + target.getName() + " com Devorar! [/" + this.getDamage() + "/ de Dano]");
                break;
            case GIANT_PUNCH:
                int harm3 = this.getDamage() + 2;
                target.HarmPlayer(harm3);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Soco Gigante! [/" + harm3 + "/ de Dano]");
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
        return new Gashadokuro();
    }
}
