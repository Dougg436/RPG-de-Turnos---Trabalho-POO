package game.Enemies;

import game.Player;
import java.util.Random;

public class Oni extends Enemy{
    private static final Random random = new Random();
    public Oni() {
        super(15, "Oni", 3, 1);
    }

    enum Attacks {
        ATTACK,
        SPECIAL,
        CRUSH
    }

    @Override
    public void TakeTurn(Player target) {
        Oni.Attacks choice = Oni.Attacks.values()[random.nextInt(Enemy.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage() - target.getDefense();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case SPECIAL:
                int harm2 = (this.getDamage()+(random.nextInt(1, 4))) - target.getDefense();
                target.HarmPlayer(harm2);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Ataque Especial! [/" + harm2 + "/ de Dano]");
                break;
            case CRUSH:
                target.HarmPlayer(this.getDamage());
                System.out.println(this.getName() + " atacou " + target.getName() + " com Esmagar! (Ignora Armadura) [/" + this.getDamage() + "/ de Dano]");
        }
    }
}
