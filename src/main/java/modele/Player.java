package modele;

import java.util.ArrayList;

public class Player extends Entity {
    private final int jumpHeight = 20;
    public int jumpCount = jumpHeight;
    private boolean isJumping;
    private boolean isRunning;

    private Inventory inventory = new Inventory();

    public Player(int x, int y){
        super(20, 10, new Hitbox(20,38,x,y),"/Sprites/MC/MCSpace_Idle_right.gif");
        inventory = new Inventory();
    }

    @Override
    public void movement(Player player, boolean leftCheck, boolean rightCheck, Terrain terrain) {
        if(this.getHitbox().getX().getValue() >= 10){
            if (leftCheck) {
                getHitbox().setX(this.getHitbox().getX().intValue() - this.getSpeed());
            }
        }

        if(this.getHitbox().getX().getValue() <= terrain.getWidth()*32-30){
            if (rightCheck){
                getHitbox().setX(this.getHitbox().getX().intValue() + this.getSpeed());
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
