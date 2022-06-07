package modele;

import java.util.ArrayList;

public class Player extends Entity {
    private boolean isRunning = false;


    private Inventory inventory;

    public Player(int x, int y, Terrain terrain){
        super(20, 7, new Hitbox(20,38,x,y),"/Sprites/MC/MCSpace_Idle_right.gif", terrain);
        inventory = new Inventory();
    }

    @Override
    public void movement(Player player, boolean leftCheck, boolean rightCheck) {
        if(this.getHitbox().getX().getValue() >= 10){
            if (leftCheck) {
                if(!sideLeftCollision()){
                    getHitbox().setX(this.getHitbox().getX().intValue() - getSpeed());
                }
            }
        }

        if(this.getHitbox().getX().getValue() <= this.getTerrain().getWidth()*32-30){
            if (rightCheck){
                if(!sideRightCollisions()){
                    getHitbox().setX(this.getHitbox().getX().intValue() + getSpeed());
                }
            }
        }

    }
    // haut du block = block.getHitY(); bas du block = block.getHitY() + block.getTile().getHitbox().getHeight()
    // haut du personnage = yProperty.intValue(); bas du personnage = yProperty.intValue() + height


    public Item drop(){
        return this.inventory.removeFromSlot();
    }

    public void pick(Item item){
        this.inventory.addIntoSlot(item);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
