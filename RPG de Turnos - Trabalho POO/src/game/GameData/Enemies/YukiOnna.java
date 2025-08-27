package game.GameData.Enemies;

import game.GameControlView.Player;
import game.GameData.DataBase;

import java.util.Random;

public class YukiOnna extends Enemy{
    private static final Random random = new Random();
    public YukiOnna() {
        super(12, "Yuki-Onna", 4, 0, 8);
    }

    enum Attacks {
        ATTACK,
        BREEZE,
        REFROST
    }

    @Override
    public void TakeTurn(Player target) {
        YukiOnna.Attacks choice = YukiOnna.Attacks.values()[random.nextInt(YukiOnna.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case BREEZE:
                int harm2 = (this.getDamage()+(random.nextInt(1, 4)));
                target.HarmPlayer(harm2);
                target.ApplyEffect(DataBase.FROST);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Geada! [/" + harm2 + "/ de Dano]");
                break;
            case REFROST:
                System.out.println(this.getName() + " usou Recongelar e tentou se curar!");
                HealEnemy(random.nextInt(1, 3));
        }
    }

    @Override
    public Enemy CloneEnemy() {
        return new YukiOnna();
    }
}
