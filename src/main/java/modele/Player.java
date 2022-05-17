package modele;

import javafx.scene.image.Image;

public class Player extends Entity {

    private final double walkSpeed = 10;
    private final double gravite = 9.81;
    private final int jumpForce = 20;
    public int jumpCount = 0;
    private boolean isJumping;


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
            getHitbox().setY(getHitbox().getY().intValue() - ++jumpCount);
        }
        else{
            if(jumpCount >= jumpForce){
                System.out.println("over 100");
                stopJump();
            }
            else{
                System.out.println(jumpCount);
                getHitbox().setY(getHitbox().getY().intValue() - ++jumpCount);
            }
        }

    }
    public void stopJump(){
        System.out.println("Stopped func");
        jumpCount = 0;
        isJumping = false;
    }
    public boolean isJumping() {
        return isJumping;
    }
    // haut du block = block.getHitY(); bas du block = block.getHitY() + block.getTile().getHitbox().getHeight()
    // haut du personnage = yProperty.intValue(); bas du personnage = yProperty.intValue() + height
}
