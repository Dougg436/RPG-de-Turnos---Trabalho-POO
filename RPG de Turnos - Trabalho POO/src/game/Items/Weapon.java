package game.Items;

public class Weapon extends Item{
    private final int damage;

    public Weapon(String name, int price, int damage){
        super(name, price);
        this.damage = damage;
    }

    @Override
    public String getType() {return "Weapon";}

    public int getDamage() {
        return damage;
    }
}
