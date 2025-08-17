package game;

import game.Enemies.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CombatControl {
    private static final Random random = new Random();

    public void PlayCombat(List<Enemy> enemies, Player player, boolean isBossFight) {
        System.out.println("Combate!");
        int xp = 1;
        for (Enemy e : enemies) {
            xp += e.getHonorgained();
        }
        while (!enemies.isEmpty()) {
            Sleep(2000);
            System.out.println("-{Seu Turno}-");
            player.ProcessEffects();
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

        System.out.println("Vitória! \n" +
                "(+" + xp + " de Honra)");
    }

    public void Sleep(int mili) {
        try {
            Thread.sleep(mili); // 2000 milissegundos = 2 segundos
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


}
