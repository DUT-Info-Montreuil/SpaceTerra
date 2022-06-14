package modele;

public abstract class Animal extends Entity{

    public Animal(int health, int speed, Hitbox hitbox, String path, Terrain terrain) {
        super(health, speed, hitbox, path, terrain);
    }
}
