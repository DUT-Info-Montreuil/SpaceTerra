package modele;

import controleur.Controleur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public abstract class  Entity {

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


    public boolean sideRightCollisions() {

        Block b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth(), hitbox.getY().intValue()); // upRight
        if(b != null && b.getTile().getHitbox().isSolid()){
            Controleur.debugger.debugBlock(b, Color.VIOLET);
            return true;
        }
        b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth(), hitbox.getY().intValue() + hitbox.getHeight() / 2); //MiddleRight
        if(b != null && b.getTile().getHitbox().isSolid()){
            Controleur.debugger.debugBlock(b, Color.BLUEVIOLET);
            return true;
        }
        b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth(), hitbox.getY().intValue() + hitbox.getHeight() - 1); //DownRight
        if(b != null && b.getTile().getHitbox().isSolid()){
            Controleur.debugger.debugBlock(b, Color.DARKVIOLET);
            return true;
        }
        return false;
    }

    public boolean sideLeftCollision(){
        Block b = terrain.getBlock(hitbox.getX().intValue(), hitbox.getY().intValue()); // upRight
        if(b != null && b.getTile().getHitbox().isSolid()){
            Controleur.debugger.debugBlock(b, Color.VIOLET);
            return true;
        }
        b = terrain.getBlock(hitbox.getX().intValue(), hitbox.getY().intValue() + hitbox.getHeight() / 2); // upRight
        if(b != null && b.getTile().getHitbox().isSolid()){
            Controleur.debugger.debugBlock(b, Color.YELLOW);
            return true;
        }
        b = terrain.getBlock(hitbox.getX().intValue(), hitbox.getY().intValue() + hitbox.getHeight() - 1); // upRight
        if(b != null && b.getTile().getHitbox().isSolid()){
            Controleur.debugger.debugBlock(b, Color.GREEN);
            return true;
        }
        return false;
    }

    public boolean upCollisions(){
        return false;
    }

    public boolean isGrounded() {
        Block b = terrain.getBlock(hitbox.getX().intValue(), hitbox.getY().intValue() + hitbox.getHeight());// upRight
        if(b != null && b.getTile().getHitbox().isSolid()){
            Controleur.debugger.debugBlock(b, Color.RED);
            this.hitbox.setY(hitbox.getY().intValue() - ((hitbox.getY().intValue() + hitbox.getHeight()) - b.getHitY()));
            return true;
        }
        return false;
    }


    public void applyGrav() {
        hitbox.getY().set(hitbox.getY().getValue() + g);
        System.out.println(g);
        g += 0.05;
    }

    public abstract void movement(Player player, boolean leftCheck, boolean rightCheck);

    public int distanceToBlock(Block b) {
        double centerPX = this.hitbox.getX().intValue() + this.hitbox.getWidth() / 2; //centre du joueur en x
        double centerPY = this.hitbox.getY().intValue() + this.hitbox.getHeight() / 2; //centre du joueur en y
        double centerBX = b.getHitX() + b.getTile().getHitbox().getWidth() / 2; //centre du block en x
        double centerBY = b.getHitY() + b.getTile().getHitbox().getHeight() / 2; // centre du block en y
        double sqrt = Math.sqrt(Math.pow(centerBX - centerPX, 2.0) + Math.pow(centerBY - centerPY, 2.0));
        System.out.println("distance block : " + sqrt / 32);
        return (int) sqrt / 32; //distance euclidienne / 32 pour avoir une distance en blocks
    }

    public void jump() {
        if (!this.isJumping()) {
            this.setJumping(true);
            getHitbox().setY(getHitbox().getY().intValue() - --jumpCount);
        } else {
            if (jumpCount <= 0) {
                stopJump();
            } else {
                //System.out.println(jumpCount);
                getHitbox().setY(getHitbox().getY().intValue() - --jumpCount);
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
}
