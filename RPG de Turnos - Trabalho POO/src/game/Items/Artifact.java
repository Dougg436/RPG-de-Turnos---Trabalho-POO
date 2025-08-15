package game.Items;

public class Artifact extends Item{

    public Artifact(String name, int price){
        super(name, price);
    }

    @Override
    public String getType() {return "Artifact";}
}
