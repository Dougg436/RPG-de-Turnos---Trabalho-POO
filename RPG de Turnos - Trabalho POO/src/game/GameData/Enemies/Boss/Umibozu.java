package game.GameData.Enemies.Boss;

import game.GameControlView.Player;
import game.GameData.DataBase;
import game.GameData.Enemies.Enemy;
import game.GameData.Items.Item;

import java.util.Random;

public class Umibozu extends Enemy implements Boss{
    private static final Random random = new Random();
    public Umibozu() {
        super(50, "Umibozu", 8, 4, 20);
    }

    enum Attacks {
        ATTACK,
        OVERTHROWN,
        CURSE
    }

    @Override
    public void TakeTurn(Player target) {
        Umibozu.Attacks choice = Umibozu.Attacks.values()[random.nextInt(Umibozu.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case OVERTHROWN:
                target.HarmPlayer(this.getDamage() + random.nextInt(-4, 2));
                System.out.println(this.getName() + " atacou " + target.getName() + " com Derrubada![/" + this.getDamage() + "/ de Dano]");
                break;
            case CURSE:
                int harm3 = this.getDamage();
                target.HarmPlayer(harm3);
                target.ApplyEffect(DataBase.HORROR);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Praguejar! [/" + harm3 + "/ de Dano]");
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
        return new Umibozu();
    }
}
