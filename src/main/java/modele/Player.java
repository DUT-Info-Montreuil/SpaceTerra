package modele;

import java.util.ArrayList;

public class Player extends Entity {

    private final double walkSpeed = 10;
    private final int jumpHeight = 20;
    public int jumpCount = jumpHeight;
    private boolean isJumping;

    private int inventoryMaxSize;

    private ArrayList<Item> inventory = new ArrayList<>(5);


    public Player(int x,int y){
        super(20, 10, new Hitbox(24,38,x,y),"/Sprites/MC/MCSpace_Idle_right.gif");

    }

    public void movement(Player player, boolean left, boolean right) {
        if (left) {
            getHitbox().setX(this.getHitbox().getX().intValue() - walkSpeed);
        }
        else if (right){
            getHitbox().setX(this.getHitbox().getX().intValue() + walkSpeed);
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
                System.out.println(jumpCount);
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

    public boolean isInventoryFull(){
        if(inventory.size() >= inventoryMaxSize){
            return true;
        }
        return false;
    }

    public void drop(Item item){
        if(inventory.contains(item)){
            inventory.remove(item);
        }


    }

    public void pick(Item item){
        if(!isInventoryFull()){
            inventory.add(item);
        }
    }


}
