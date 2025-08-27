package game.GameData.Enemies.Boss;

import game.GameData.DataBase;
import game.GameData.Enemies.Enemy;
import game.GameData.Items.Item;
import game.GameControlView.Player;

import java.util.Random;

public class ToughOni extends Enemy implements Boss {
    private static final Random random = new Random();
    public ToughOni() {
        super(30, "Oni Encouraçado", 4, 4, 12);
    }

    enum Attacks {
        ATTACK,
        MEGACRUSH,
        TEAR
    }

    @Override
    public void TakeTurn(Player target) {
        ToughOni.Attacks choice = ToughOni.Attacks.values()[random.nextInt(ToughOni.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case MEGACRUSH:
                target.HarmPlayerPiercing(this.getDamage() + random.nextInt(3));
                System.out.println(this.getName() + " atacou " + target.getName() + " com Mega Esmagar! (Ignora Armadura) [/" + this.getDamage() + "/ de Dano]");
                break;
            case TEAR:
                int harm3 = this.getDamage();
                target.HarmPlayer(harm3);
                target.ApplyEffect(DataBase.BLEED);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Rasgar! [/" + harm3 + "/ de Dano]");
        }
    }

    @Override
    public void EntraceText() {

    }

    @Override
    public Item getDropItem() {
        return DataBase.KATANA;
    }

    @Override
    public int getDropMoney() {
        return 10;
    }

    @Override
    public Enemy CloneEnemy() {
        return new ToughOni();
    }
}
