package modele;

import java.util.ArrayList;

public class Player extends Entity {

    private final double walkSpeed = 10;
    private final int jumpHeight = 20;
    public int jumpCount = jumpHeight;
    private boolean isJumping;

    private Inventory inventory = new Inventory();


    public Player(int x, int y){
        super(20, 10, new Hitbox(20,38,x,y),"/Sprites/MC/MCSpace_Idle_right.gif");
        inventory = new Inventory();
    }

    public void movement(Player player, boolean left, boolean right) {
        if(this.getHitbox().getX().getValue() >= 10){
            if (left) {
                getHitbox().setX(this.getHitbox().getX().intValue() - walkSpeed);
            }
        }
        if(this.getHitbox().getX().getValue() <= 15970){
            if (right){
                getHitbox().setX(this.getHitbox().getX().intValue() + walkSpeed);
            }
        }

    }

    public void jump() {
        if(!isJumping){
            isJumping = true;
            getHitbox().setY(getHitbox().getY().intValue() - --jumpCount);
        }
        else{
            if(jumpCount <= 0){
                stopJump();
            }
            else{
                //System.out.println(jumpCount);
                getHitbox().setY(getHitbox().getY().intValue() - --jumpCount);
            }
        }

    }
    public void stopJump(){
        jumpCount = jumpHeight;
        isJumping = false;
    }
    public boolean isJumping() {
        return isJumping;
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
