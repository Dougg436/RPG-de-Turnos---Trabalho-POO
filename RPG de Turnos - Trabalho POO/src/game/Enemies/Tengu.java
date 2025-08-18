package game.Enemies;

import game.DataBase;
import game.Player;

import java.util.Random;

public class Tengu extends Enemy{
    private static final Random random = new Random();
    public Tengu() {
        super(8, "Tengu", 3, 0);
    }

    enum Attacks {
        ATTACK,
        SPECIAL,
        TEAR
    }

    @Override
    public void TakeTurn(Player target) {
        Tengu.Attacks choice = Tengu.Attacks.values()[random.nextInt(Enemy.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage() - target.getDefense();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case SPECIAL:
                int harm2 = (this.getDamage()+(random.nextInt(1, 2))) - target.getDefense();
                target.HarmPlayer(harm2);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Ataque Especial! [/" + harm2 + "/ de Dano]");
                break;
            case TEAR:
                int harm3 = this.getDamage() - target.getDefense();
                target.HarmPlayer(harm3);
                target.ApplyEffect(DataBase.BLEED);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Rasgar! [/" + harm3 + "/ de Dano]");
        }
    }
}
