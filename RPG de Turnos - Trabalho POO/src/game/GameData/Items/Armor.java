package game.GameData.Items;

import java.io.Serializable;

public class Armor extends Item implements Serializable {
    private int defense;

    public Armor(String name, int price, int defense){
        super(name, price);
        this.defense = defense;
    }

    @Override
    public String getType() {return "Armor";}
}
