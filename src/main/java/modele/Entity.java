package modele;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.GenericArrayType;
import java.net.CookieHandler;

public abstract class Entity {

    private int life;
    private int speed;
    private Hitbox hitbox;
    private Image image;
    private final int jumpHeight = 20;
    public int jumpCount = jumpHeight;
    private boolean isJumping = false;

    public static double g = 5;

    public Terrain getTerrain() {
        return terrain;
    }

    private Terrain terrain;

    public Entity(int vie, int vitesse, Hitbox hitbox, String path, Terrain terrain) {
        this.life = vie;
        this.speed = vitesse;
        this.hitbox = hitbox;
        this.image = new Image(String.valueOf(getClass().getResource(path)));
        this.terrain = terrain;

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String path) {
        this.image = new Image(String.valueOf(getClass().getResource(path)));
    }

    public int upCollisions(){

        Block b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth()/2, hitbox.getY().intValue() - hitbox.getHeight()/2);
        if (b != null) {
            if (b.getTile().getHitbox().isSolid()) {
                System.out.println("solide");
                //hitbox.setY(b.getHitY() + 15);
                System.out.println("collisions du haut");
                return 1;
            }
        }

        return  0;

    }

    public int sideCollisions() {

        Block blockVerifBottomLeft = terrain.getBlock(hitbox.getX().intValue(), hitbox.getY().intValue() + hitbox.getHeight() - 10);
        Block blockVerifBottomRight = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth(), hitbox.getY().intValue() + hitbox.getHeight() - 10);
        Block blockVerifUpLeft = terrain.getBlock(hitbox.getX().intValue(), hitbox.getY().intValue() + 10);
        Block blockVerifUpRight = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth(), hitbox.getY().intValue() + 10);


        if(blockVerifBottomLeft != null){
            if(blockVerifBottomLeft.getTile().getHitbox().isSolid()){
                //hitbox.setX(blockVerifBottomLeft.getHitX() + blockVerifBottomLeft.getTile().getHitbox().getWidth() + 2);
                System.out.println("bas gauche");
                return -1;
            }
        }
        if(blockVerifUpLeft != null){
            if(blockVerifUpLeft.getTile().getHitbox().isSolid()){
               //hitbox.setX(blockVerifUpLeft.getHitX() + blockVerifUpLeft.getTile().getHitbox().getWidth() + 2);
                System.out.println("haut gauche");
                return -1;
            }
        }

        if(blockVerifBottomRight != null){
            if(blockVerifBottomRight.getTile().getHitbox().isSolid()){
                hitbox.setX(blockVerifBottomRight.getHitX() - hitbox.getWidth());
                System.out.println("bas droit");
                return 1;
            }
        }
        if(blockVerifUpRight != null){
            if(blockVerifUpRight.getTile().getHitbox().isSolid()){
                hitbox.setX(blockVerifUpRight.getHitX() - hitbox.getWidth());
                System.out.println("haut droit");
                return 1;
            }
        }
        return 0;

    }

    public boolean isGrounded() {

        Block blockVerifBottomLeft = terrain.getBlock(hitbox.getX().intValue() + 3, hitbox.getY().intValue() + hitbox.getHeight());
        Block blockVerifBottomRight = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() - 3, hitbox.getY().intValue() + hitbox.getHeight());

        if (blockVerifBottomLeft != null) {
            if(blockVerifBottomLeft.getTile().getHitbox().isSolid()){
                hitbox.getY().set(blockVerifBottomLeft.getHitY() - hitbox.getHeight());
                return true;
            }
        }
        if(blockVerifBottomRight != null){
            if(blockVerifBottomRight.getTile().getHitbox().isSolid()){
                hitbox.getY().set(blockVerifBottomRight.getHitY() - hitbox.getHeight());
                return true;
            }
        }
        return false;
    }

    public void applyGrav() {
        hitbox.getY().set(hitbox.getY().getValue() + g);
        g += 0.05;
        //System.out.println(g);
    }


    public int distanceToBlock(Block b) {
        double centerPX = this.hitbox.getX().intValue() + this.hitbox.getWidth() / 2; //centre du joueur en x
        double centerPY = this.hitbox.getY().intValue() + this.hitbox.getHeight() / 2; //centre du joueur en y
        double centerBX = b.getHitX() + b.getTile().getHitbox().getWidth() / 2; //centre du block en x
        double centerBY = b.getHitY() + b.getTile().getHitbox().getHeight() / 2; // centre du block en y
        double sqrt = Math.sqrt(Math.pow(centerBX - centerPX, 2.0) + Math.pow(centerBY - centerPY, 2.0));
        //System.out.println("distance block : " + sqrt / 32);
        return (int) sqrt / 32; //distance euclidienne / 32 pour avoir une distance en blocks
    }

    public void jump(boolean checkUpBlock) {
        if (checkUpBlock) {
            if (!this.isJumping()) {
                this.setJumping(true);
                getHitbox().setY(getHitbox().getY().intValue() - --jumpCount);
            } else {
                if (jumpCount <= 0) {
                    stopJump();
                } else {
                    System.out.println(jumpCount);
                    getHitbox().setY(getHitbox().getY().intValue() - --jumpCount);
                }
            }
        }
    }

    public void stopJump() {
        this.setJumpCount(this.getJumpHeight());
        this.setJumping(false);
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public int getJumpCount() {
        return jumpCount;
    }

    public void setJumpCount(int jumpCount) {
        this.jumpCount = jumpCount;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public abstract void movement(Player player, boolean leftCheck, boolean rightCheck);

}
