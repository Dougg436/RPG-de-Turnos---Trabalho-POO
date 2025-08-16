package game;

import game.Enemies.Enemy;

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
            System.out.println("Seu Turno");
            player.ProcessEffects();
            player.TakeTurn(enemies);
            System.out.println("Turno dos Inimigos");
            for (Enemy e : enemies) e.TakeTurn(player);
        }

        System.out.println("Vitória! \n" +
                "(+" + xp + " de Honra)");
    }
}
