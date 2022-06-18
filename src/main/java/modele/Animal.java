package modele;

public abstract class Animal extends Entity{

    private int direction = 0;
    private int idleCooldown = 50;
    private boolean canMove = true;
    public Animal(int health, int speed, Hitbox hitbox, String path, Terrain terrain) {
        super(health, speed, hitbox, path, terrain);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getIdleCooldown() {
        return idleCooldown;
    }

    public void setIdleCooldown(int idleCooldown) {
        this.idleCooldown = idleCooldown;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
