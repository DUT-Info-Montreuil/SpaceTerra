package modele;

import java.util.ArrayList;

public class Player extends Entity {

    private final double walkSpeed = 5;
    private final int jumpHeight = 20;
    public int jumpCount = jumpHeight;
    private boolean isJumping;

    private Inventory inventory = new Inventory();

    public Player(int x, int y, Terrain terrain){
        super(20, 10, new Hitbox(20,38,x,y),"/Sprites/MC/MCSpace_Idle_right.gif", terrain);
        inventory = new Inventory();
    }

    @Override
    public void movement(Player player, boolean leftCheck, boolean rightCheck) {
        if(this.getHitbox().getX().getValue() >= 10){
            if (leftCheck) {
                getHitbox().setX(this.getHitbox().getX().intValue() - walkSpeed);
            }
        }

        if(this.getHitbox().getX().getValue() <= this.getTerrain().getWidth()*32-30){
            if (rightCheck){
                getHitbox().setX(this.getHitbox().getX().intValue() + walkSpeed);
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



}
