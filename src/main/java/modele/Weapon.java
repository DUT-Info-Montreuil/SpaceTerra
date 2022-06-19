package modele;

public class Weapon extends Item{
    private int damage;
    public Weapon(int id, int damage) {
        super(id, 1);
        this.damage = damage;
    }

    @Override
    public void use() {
        // attack
    }
}
