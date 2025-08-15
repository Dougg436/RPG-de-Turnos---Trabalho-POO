package game.Items;

public abstract class Item {
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

    public abstract String getType();
}
