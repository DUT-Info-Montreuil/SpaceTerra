package modele;

import java.util.ArrayList;

public abstract class Animal extends Entity{

    private int direction = 0;
    private int idleCooldown = 50;
    private boolean canMove = true;
    public Animal(int health, int speed, Hitbox hitbox, Terrain terrain, int jumpHeight, boolean flying, ArrayList<String> actions) {
            //public Entity(int health, int speed, Hitbox hitbox, Terrain terrain, int jumpHeight, boolean flying, ArrayList<String> actions)
            super(health, speed, hitbox, terrain, jumpHeight, flying, actions);
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
