package game.GameData.Enemies;

import game.GameControlView.Player;
import game.GameData.DataBase;

import java.util.Random;

public class Wanyudo extends Enemy {
    private static final Random random = new Random();
    public Wanyudo() {
        super(5, "Wanyudo", 2, 1, 2);
    }

    enum Attacks {
        ATTACK,
        ROLL,
        FLAMED_ROLL
    }

    @Override
    public void TakeTurn(Player target) {
        Wanyudo.Attacks choice = Wanyudo.Attacks.values()[random.nextInt(Wanyudo.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case ROLL:
                int harm2 = (this.getDamage()+(random.nextInt(1, 4)));
                target.HarmPlayer(harm2);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Rolagem Violenta! [/" + harm2 + "/ de Dano]");
                break;
            case FLAMED_ROLL:
                target.HarmPlayerPiercing(this.getDamage());
                target.ApplyEffect(DataBase.BURN);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Rolagem Flamejante! [/" + this.getDamage() + "/ de Dano]");
        }
    }

    @Override
    public Enemy CloneEnemy() {
        return new Wanyudo();
    }
}
