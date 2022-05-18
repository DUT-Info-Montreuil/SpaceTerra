package modele;

public class Weapon extends Item{
    private int damage;
    public Weapon(String id, int maxQuantity, int damage, Tile tile) {
        super(id, maxQuantity, tile);
        this.damage = damage;
    }

    @Override
    public void use() {

    }
}
