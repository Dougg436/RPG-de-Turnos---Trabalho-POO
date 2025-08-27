package game.GameControlView;

import game.GameData.Enemies.Enemy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CombatControl implements Serializable {
    private static final Random random = new Random();

    public void PlayCombat(List<Enemy> enemies, Player player, boolean isBossFight) {
        System.out.println("Combate!");
        int xp = 1;
        for (Enemy e : enemies) {
            xp += e.getXPgained();
        }
        while (!enemies.isEmpty() && player.getHealthPoints() > 0) {
            Sleep(1000);
            System.out.println("-{Seu Turno}-");
            player.ProcessEffects();
            player.GainSecs();
            player.TakeTurn(enemies);
            enemies.removeIf(Enemy::isDead);
            Sleep(1500);
            System.out.println("-{Turno dos Inimigos}-");
            Sleep(1000);
            for (Enemy e : new ArrayList<>(enemies)) {
                e.ProcessEffects();
                if (e.isDead()) continue;
                e.TakeTurn(player);
                Sleep(1000);
            }
            enemies.removeIf(Enemy::isDead);
        }
        if (player.getHealthPoints() == 0) {
            player.GameOver();
            return;
        }

        System.out.println("Vitória! \n" +
                "(+" + xp + " de XP!)");
        player.gainXP(xp);
        player.CheckLevel();

        if (isBossFight) {
            player.respect += 1;
            System.out.println("Seu respeito na Vila aumentará");
        }

    }

    public void Sleep(int mili) {
        try {
            Thread.sleep(mili); // 1000 milissegundos = 1 segundos
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
