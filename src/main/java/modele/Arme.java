package modele;

public class Arme extends Item{
    private int damage;
    public Arme(int id, int maxQuantity, int damage) {
        super(id, maxQuantity);
        this.damage = damage;
    }

    @Override
    public void use() {

    }
}
