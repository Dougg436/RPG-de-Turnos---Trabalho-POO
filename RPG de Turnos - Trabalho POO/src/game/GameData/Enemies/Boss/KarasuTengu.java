package game.GameData.Enemies.Boss;

import game.GameControlView.Player;
import game.GameData.DataBase;
import game.GameData.Enemies.Enemy;
import game.GameData.Items.Item;

import java.util.Random;

public class KarasuTengu extends Enemy implements Boss{
    private static final Random random = new Random();
    public KarasuTengu() {
        super(30, "Karasu Tengu", 4, 4, 12);
    }

    enum Attacks {
        ATTACK,
        MEGAGRAZING,
        CURSE
    }

    @Override
    public void TakeTurn(Player target) {
        KarasuTengu.Attacks choice = KarasuTengu.Attacks.values()[random.nextInt(KarasuTengu.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case MEGAGRAZING:
                target.HarmPlayerPiercing(this.getDamage() + random.nextInt(3));
                System.out.println(this.getName() + " atacou " + target.getName() + " com Mega Razante! (Ignora Armadura) [/" + this.getDamage() + "/ de Dano]");
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
        return new KarasuTengu();
    }
}
