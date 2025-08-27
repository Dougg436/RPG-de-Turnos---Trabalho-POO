package game.GameData.Enemies.Boss;

import game.GameControlView.Player;
import game.GameData.DataBase;
import game.GameData.Enemies.Enemy;
import game.GameData.Items.Item;

import java.util.Random;

public class BakeKujira extends Enemy implements Boss {
    private static final Random random = new Random();
    public BakeKujira() {
        super(45, "Karasu Tengu", 5, 5, 15);
    }

    enum Attacks {
        ATTACK,
        BONE_REAP,
        FOG
    }

    @Override
    public void TakeTurn(Player target) {
        BakeKujira.Attacks choice = BakeKujira.Attacks.values()[random.nextInt(BakeKujira.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case BONE_REAP:
                target.HarmPlayerPiercing(this.getDamage());
                System.out.println(this.getName() + " atacou " + target.getName() + " com Ceifa de Ossos! (Ignora Armadura) [/" + this.getDamage() + "/ de Dano]");
                break;
            case FOG:
                int harm3 = this.getDamage();
                target.HarmPlayer(harm3);
                target.ApplyEffect(DataBase.FROST);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Nevoeiro! [/" + harm3 + "/ de Dano]");
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
        return new BakeKujira();
    }
}
