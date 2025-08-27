package game.GameData.Enemies;

import game.GameControlView.Player;
import game.GameData.DataBase;

import java.util.Random;

public class Kappa extends Enemy{
    private static final Random random = new Random();
    public Kappa() {
        super(8, "Kappa", 4, 2, 3);
    }

    enum Attacks {
        ATTACK,
        SWIM,
        TEAR
    }

    @Override
    public void TakeTurn(Player target) {
        Kappa.Attacks choice = Kappa.Attacks.values()[random.nextInt(Kappa.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case SWIM:
                System.out.println(this.getName() + " usou Nadar e tentou se curar!");
                HealEnemy(random.nextInt(1, 4));
                break;
            case TEAR:
                int harm2 = this.getDamage();
                target.HarmPlayer(harm2);
                target.ApplyEffect(DataBase.BLEED);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Rasgar! [/" + harm2 + "/ de Dano]");
        }
    }

    @Override
    public Enemy CloneEnemy() {
        return new Kappa();
    }
}
