package game.GameData.Enemies;

import game.GameControlView.Player;
import game.GameData.DataBase;

import java.util.Random;

public class UshiOni extends Enemy{
    private static final Random random = new Random();
    public UshiOni() {
        super(15, "UshiOni", 4, 1, 5);
    }

    enum Attacks {
        ATTACK,
        REAP,
        PUKE
    }

    @Override
    public void TakeTurn(Player target) {
        UshiOni.Attacks choice = UshiOni.Attacks.values()[random.nextInt(UshiOni.Attacks.values().length)];

        switch(choice) {
            case ATTACK:
                int harm1 = this.getDamage();
                target.HarmPlayer(harm1);
                System.out.println(this.getName() + " atacou " + target.getName() + " [/" + harm1 + "/ de Dano]");
                break;
            case REAP:
                int harm2 = (this.getDamage()+(random.nextInt(1, 3)));
                target.HarmPlayerPiercing(harm2);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Ceifar! (Ignora Armadura) [/" + harm2 + "/ de Dano]");
                break;
            case PUKE:
                int harm3 = this.getDamage() - 1;
                target.HarmPlayer(harm3);
                target.ApplyEffect(DataBase.POISON);
                System.out.println(this.getName() + " atacou " + target.getName() + " com Vomitar! [/" + harm3 + "/ de Dano]");
        }
    }

    @Override
    public Enemy CloneEnemy() {
        return new UshiOni();
    }
}
