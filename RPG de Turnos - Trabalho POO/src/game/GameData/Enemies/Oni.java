package game.GameData.Enemies;

import game.GameControlView.Player;
import java.util.Random;

public class Oni extends Enemy{
    private static final Random random = new Random();
    public Oni() {
        super(10, "Oni", 3, 1, 4);
    }

    enum Attacks {
        ATTACK,
        PUNCH,
        CRUSH
    }

    @Override
    public void TakeTurn(Player target) {
        Oni.Attacks choice = Oni.Attacks.values()[random.nextInt(Oni.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case PUNCH:
                int harm2 = (this.getDamage()+(random.nextInt(1, 4)));
                target.HarmPlayer(harm2);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Socão! [/" + harm2 + "/ de Dano]");
                break;
            case CRUSH:
                target.HarmPlayerPiercing(this.getDamage() - 1);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Esmagar! (Ignora Armadura) [/" + this.getDamage() + "/ de Dano]");
        }
    }

    @Override
    public Enemy CloneEnemy() {
        return new Oni();
    }
}
