package game.Items;

import game.Skill;

public class Weapon extends Item{
    private final int damage;
    private Skill weaponSkill;

    public Weapon(String name, int price, int damage){
        super(name, price);
        this.damage = damage;
    }

    public Weapon(String name, int price, int damage, Skill skill){
        super(name, price);
        this.damage = damage;
        this.weaponSkill = skill;
    }

    @Override
    public String getType() {return "Weapon";}

    public int getDamage() {
        return damage;
    }
}
