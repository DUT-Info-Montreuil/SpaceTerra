package modele;

public class Weapon extends Item{
    private int damage;
    public Weapon(int id, int maxQuantity, int damage) {
        super(id, maxQuantity);
        this.damage = damage;
    }

    @Override
    public void use() {

    }
}
