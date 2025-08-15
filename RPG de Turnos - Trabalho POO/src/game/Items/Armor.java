package game.Items;

public class Armor extends Item{
    private int defense;

    public Armor(String name, int price, int defense){
        super(name, price);
        this.defense = defense;
    }

    @Override
    public String getType() {return "Armor";}
}
