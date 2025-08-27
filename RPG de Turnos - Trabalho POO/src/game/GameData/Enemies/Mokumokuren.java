package game.GameData.Enemies;

import game.GameControlView.Player;
import game.GameData.DataBase;

import java.util.Random;

public class Mokumokuren extends Enemy{
    private static final Random random = new Random();
    public Mokumokuren() {
        super(10, "Mokumokuren", 2, 1, 3);
    }

    enum Attacks {
        ATTACK,
        HAUNTING,
        OBSERVE
    }

    @Override
    public void TakeTurn(Player target) {
        Mokumokuren.Attacks choice = Mokumokuren.Attacks.values()[random.nextInt(Mokumokuren.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case HAUNTING:
                target.SpendSecPoint("mind", 3);
                System.out.println(this.getName() + " assombrou " + target.getName() + "! [/-3/ de Mente]");
                break;
            case OBSERVE:
                int harm2 = this.getDamage();
                target.HarmPlayer(harm2);
                target.ApplyEffect(DataBase.HORROR);
                System.out.println(this.getName() + " usou Observar!");
        }
    }

    @Override
    public Enemy CloneEnemy() {
        return new Mokumokuren();
    }
}
