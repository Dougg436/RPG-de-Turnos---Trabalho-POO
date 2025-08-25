package game.Enemies;

import game.Player;

import java.util.Random;

public class Tsukumogami extends Enemy{
    private static final Random random = new Random();
    public Tsukumogami() {
        super(3+ random.nextInt(1, 3), "Tsukumogami", 3, 1, 2);
    }

    enum Attacks {
        ATTACK,
        STROKE,
        HAUNTING
    }

    @Override
    public void TakeTurn(Player target) {
        Tsukumogami.Attacks choice = Tsukumogami.Attacks.values()[random.nextInt(Tsukumogami.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case STROKE:
                int harm2 = (this.getDamage() + 2);
                target.HarmPlayer(harm2);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Batida! [/" + harm2 + "/ de Dano]");
                break;
            case HAUNTING:
                target.SpendSecPoint("mind", 3);
                System.out.println(this.getName() + " assombrou " + target.getName() + "! [/-3/ de Mente]");
        }
    }

    @Override
    public Enemy CloneEnemy() {
        return new Tsukumogami();
    }
}
