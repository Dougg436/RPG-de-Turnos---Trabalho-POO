package game.GameData.Enemies;

import game.GameControlView.Player;
import game.GameData.DataBase;

import java.util.Random;

public class Yosuzume extends Enemy{
    private static final Random random = new Random();
    public Yosuzume() {
        super(6, "Yosuzume", 2, 0, 2);
    }

    enum Attacks {
        ATTACK,
        GRAZING,
        TEAR
    }

    @Override
    public void TakeTurn(Player target) {
        Yosuzume.Attacks choice = Yosuzume.Attacks.values()[random.nextInt(Yosuzume.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case GRAZING:
                int harm2 = (this.getDamage()+(random.nextInt(1, 4)));
                target.HarmPlayer(harm2);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Rasante! [/" + harm2 + "/ de Dano]");
                break;
            case TEAR:
                int harm3 = this.getDamage();
                target.HarmPlayer(harm3);
                target.ApplyEffect(DataBase.BLEED);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Rasgar! [/" + harm3 + "/ de Dano]");
        }
    }

    @Override
    public Enemy CloneEnemy() {
        return new Yosuzume();
    }
}
