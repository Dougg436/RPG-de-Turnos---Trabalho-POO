package game.GameData.Items;

import java.io.Serializable;

public abstract class Item implements Serializable {
    protected String name;
    protected int price;

    public Item(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPriceToSell() {
        return Math.round(Math.max(price - (price/2), 1));
    }

    public abstract String getType();
}
