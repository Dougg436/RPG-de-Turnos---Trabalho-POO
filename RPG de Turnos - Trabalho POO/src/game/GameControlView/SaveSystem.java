package game.GameControlView;

import java.io.*;

public class SaveSystem {
    public static void save(Object obj, String fileName) { // Salva o Objeto (player)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
            System.out.println("Jogo salvo: " + fileName);
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public static Object load(String fileName) { // Carrega o Objeto (player)
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            System.out.println("Jogo carregado: " + fileName);
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
            return null;
        }
    }
}
