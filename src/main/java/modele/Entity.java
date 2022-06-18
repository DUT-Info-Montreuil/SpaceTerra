package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.util.ArrayList;


public abstract class  Entity {

    private IntegerProperty health;
    private int speed;
    private Hitbox hitbox;
    private final int jumpHeight = 20;
    public int jumpCount = jumpHeight;
    private boolean isJumping = false;
    private boolean flying = false;
    public double gravity = 5;
    private ArrayList<String> actions;
    private StringProperty action = new SimpleStringProperty("idle");

    public int getActionRange() {
        return actionRange;
    }

    public int actionRange = 4;

    public Terrain getTerrain() {
        return terrain;
    }

    private Terrain terrain;

    public Entity(int health, int speed, Hitbox hitbox, Terrain terrain, ArrayList<String> actions) {
        this.health = new SimpleIntegerProperty(health);
        this.speed = speed;
        this.hitbox = hitbox;
        this.terrain = terrain;
        this.actions = actions;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public IntegerProperty getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health.setValue(health);
    }

    public void decreaseHealth(int health) {
        this.health.subtract(health);
    }

    public void increaseHealth(int health) {
        this.health.add(health);
    }

    public Hitbox getHitbox() {
        return hitbox;
    }


    public boolean sideRightCollisions() {
        Block b = terrain.getBlock((int)hitbox.getX() + hitbox.getWidth() + speed, (int)hitbox.getY()); // rightUp
        if(b != null && b.getHitbox().isSolid()){
            if(hitbox.getX() + hitbox.getWidth() >= b.getHitbox().getX() && hitbox.getX() + hitbox.getWidth() <= b.getHitbox().getX() + 10){
                this.hitbox.setX(hitbox.getX() - ((hitbox.getX() + hitbox.getWidth()) - b.getHitbox().getX()));
                //DebugView.debugBlock(b, Color.VIOLET);
                //System.out.println("Right");
                return true;
            }
        }
        b = terrain.getBlock((int)hitbox.getX() + hitbox.getWidth() + speed, (int)hitbox.getY() + hitbox.getHeight() / 2); // rightMiddle
        if(b != null && b.getHitbox().isSolid()){
            if(hitbox.getX() + hitbox.getWidth() >= b.getHitbox().getX() && hitbox.getX() + hitbox.getWidth() <= b.getHitbox().getX() + 10){
                this.hitbox.setX(hitbox.getX() - ((hitbox.getX() + hitbox.getWidth()) - b.getHitbox().getX()));
                //DebugView.debugBlock(b, Color.BLUEVIOLET);
                //System.out.println("Right");
                return true;
            }
        }
        b = terrain.getBlock((int)hitbox.getX() + hitbox.getWidth() + speed, (int)hitbox.getY() + hitbox.getHeight() - 1); // rightDown
        if(b != null && b.getHitbox().isSolid()){
            if(hitbox.getX() + hitbox.getWidth() >= b.getHitbox().getX() && hitbox.getX() + hitbox.getWidth() <= b.getHitbox().getX() + 10){
                this.hitbox.setX(hitbox.getX() - ((hitbox.getX() + hitbox.getWidth()) - b.getHitbox().getX()));
                //DebugView.debugBlock(b, Color.DARKVIOLET);
                //System.out.println("Right");
                return true;
            }
        }
        return false;
    }

    public boolean sideLeftCollision(){
        Block b = terrain.getBlock((int)hitbox.getX() - speed, (int)hitbox.getY()); // leftUp
        if(b != null && b.getHitbox().isSolid()){
            if(hitbox.getX() <= b.getHitbox().getX() + b.getHitbox().getWidth() && hitbox.getX() >= b.getHitbox().getX() + b.getHitbox().getWidth() - 10){
                this.hitbox.setX(hitbox.getX() + ((b.getHitbox().getX() + b.getHitbox().getWidth()) - hitbox.getX()));
                //System.out.println("LeftUp");
                //DebugView.debugBlock(b, Color.VIOLET);
                return true;
            }
        }
        b = terrain.getBlock((int)hitbox.getX() - speed, (int)hitbox.getY() + hitbox.getHeight() / 2); // leftMiddle
        if(b != null && b.getHitbox().isSolid()){
            if(hitbox.getX() <= b.getHitbox().getX() + b.getHitbox().getWidth() && hitbox.getX() >= b.getHitbox().getX() + b.getHitbox().getWidth() - 10){
                this.hitbox.setX(hitbox.getX() + ((b.getHitbox().getX() + b.getHitbox().getWidth()) - hitbox.getX()));
                //System.out.println("LeftMiddle");
                //DebugView.debugBlock(b, Color.YELLOW);
                return true;
            }
        }
        b = terrain.getBlock((int)hitbox.getX() - speed, (int)hitbox.getY() + hitbox.getHeight() - 1); // leftDown
        if(b != null && b.getHitbox().isSolid()){
            if(hitbox.getX() <= b.getHitbox().getX() + b.getHitbox().getWidth() && hitbox.getX() >= b.getHitbox().getX() + b.getHitbox().getWidth() - 10){
                this.hitbox.setX(hitbox.getX() + ((b.getHitbox().getX() + b.getHitbox().getWidth()) - hitbox.getX()));
                //System.out.println("LeftDown");
                //DebugView.debugBlock(b, Color.GREEN);
                return true;
            }
        }
        return false;
    }

    public boolean upCollisions(){ // can allow clipping rn if player gets stuck innit
        Block b = terrain.getBlock((int)hitbox.getX() + 5, (int)hitbox.getY() - jumpCount);// upLeft
        if(b != null && b.getHitbox().isSolid()){
            if(hitbox.getY() <= b.getHitbox().getY() + b.getHitbox().getHeight() && hitbox.getY() >= b.getHitbox().getY() + b.getHitbox().getHeight() - 20){
                this.hitbox.setY(hitbox.getY() + ((b.getHitbox().getY() + b.getHitbox().getHeight()) - hitbox.getY()));
                //DebugView.debugBlock(b, Color.DARKGRAY);
                //System.out.println("UpCol");
                return true;
            }
        }
        b = terrain.getBlock((int)hitbox.getX() + hitbox.getWidth() / 2, (int)hitbox.getY() - jumpCount);// upMiddle
        if(b != null && b.getHitbox().isSolid()){
            if(hitbox.getY() <= b.getHitbox().getY() + b.getHitbox().getHeight() && hitbox.getY() >= b.getHitbox().getY() + b.getHitbox().getHeight() - 20){
                this.hitbox.setY(hitbox.getY() + ((b.getHitbox().getY() + b.getHitbox().getHeight()) - hitbox.getY()));
                //DebugView.debugBlock(b, Color.DIMGRAY);
                //System.out.println("UpCol");
                return true;
            }
        }
        b = terrain.getBlock((int)hitbox.getX() + hitbox.getWidth() - 5, (int)hitbox.getY() - jumpCount);// upRight
        if(b != null && b.getHitbox().isSolid()){
            if(hitbox.getY() <= b.getHitbox().getY() + b.getHitbox().getHeight() && hitbox.getY() >= b.getHitbox().getY() + b.getHitbox().getHeight() - 20){
                this.hitbox.setY(hitbox.getY() + ((b.getHitbox().getY() + b.getHitbox().getHeight()) - hitbox.getY()));
                //DebugView.debugBlock(b, Color.SLATEGRAY);
                //System.out.println("UpCol");
                return true;
            }
        }
        return false;
    }

    public boolean isGrounded() {
        Block b = terrain.getBlock((int)hitbox.getX() + 5, (int)hitbox.getY() + hitbox.getHeight() + (int)gravity);// downLeft
        if(b != null && b.getHitbox().isSolid()){
            if(hitbox.getY() + hitbox.getHeight() >= b.getHitbox().getY() && hitbox.getY() + hitbox.getHeight() <= b.getHitbox().getY() + 10){
                //DebugView.debugBlock(b, Color.RED);
                //System.out.println("isGrounded");
                this.hitbox.setY(hitbox.getY() - ((hitbox.getY() + hitbox.getHeight()) - b.getHitbox().getY()));
                return true;
            }
        }
        b = terrain.getBlock((int)hitbox.getX() + hitbox.getWidth() / 2, (int)hitbox.getY() + hitbox.getHeight() + (int)gravity);// downMiddle
        if(b != null && b.getHitbox().isSolid()){
            if(hitbox.getY() + hitbox.getHeight() >= b.getHitbox().getY() && hitbox.getY() + hitbox.getHeight() <= b.getHitbox().getY() + 10){
                //DebugView.debugBlock(b, Color.MEDIUMVIOLETRED);
                //System.out.println("isGrounded");
                this.hitbox.setY(hitbox.getY() - ((hitbox.getY() + hitbox.getHeight()) - b.getHitbox().getY()));
                return true;
            }
        }
        b = terrain.getBlock((int)hitbox.getX() + hitbox.getWidth() - 5, (int)hitbox.getY() + hitbox.getHeight() + (int)gravity);// downRight
        if(b != null && b.getHitbox().isSolid()){
            if(hitbox.getY() + hitbox.getHeight() >= b.getHitbox().getY() && hitbox.getY() + hitbox.getHeight() <= b.getHitbox().getY() + 10){
                //DebugView.debugBlock(b, Color.DARKRED);
                //System.out.println("isGrounded");
                this.hitbox.setY(hitbox.getY() - ((hitbox.getY() + hitbox.getHeight()) - b.getHitbox().getY()));
                return true;
            }
        }
        return false;
    }


    public void applyGrav() {
        hitbox.yProperty().set(hitbox.getY() + gravity);
        gravity += 0.05;
    }

    public abstract void movement(Player player, boolean leftCheck, boolean rightCheck);

    public int distanceToBlock(Block b) {
        double centerPX = this.hitbox.getX() + this.hitbox.getWidth() / 2; //centre du joueur en x
        double centerPY = this.hitbox.getY() + this.hitbox.getHeight() / 2; //centre du joueur en y
        double centerBX = b.getHitbox().getX() + b.getHitbox().getWidth() / 2; //centre du block en x
        double centerBY = b.getHitbox().getY() + b.getHitbox().getHeight() / 2; // centre du block en y
        double sqrt = Math.sqrt(Math.pow(centerBX - centerPX, 2.0) + Math.pow(centerBY - centerPY, 2.0));
        return (int) sqrt / 32; //distance euclidienne / 32 pour avoir une distance en blocks
    }

    public int distanceToPosition(int x, int y){
        double centerPX = this.hitbox.getX() + this.hitbox.getWidth() / 2; //centre du joueur en x
        double centerPY = this.hitbox.getY() + this.hitbox.getHeight() / 2; //centre du joueur en y
        double sqrt = Math.sqrt(Math.pow(x - centerPX, 2.0) + Math.pow(y - centerPY, 2.0));
        return (int) sqrt / 32; //distance euclidienne / 32 pour avoir une distance en blocks
    }

    public void jump() {
        if (!this.isJumping()) {
            this.setJumping(true);
            getHitbox().setY(getHitbox().getY() - --jumpCount);
        } else {
            if (jumpCount <= 0) {
                stopJump();
            } else {
                getHitbox().setY(getHitbox().getY() - --jumpCount);
            }
        }

    }

    public boolean grimpableRight(){
        Block b = terrain.getBlock((int)hitbox.getX() + hitbox.getWidth() + speed, (int)hitbox.getY() + hitbox.getHeight() - 1);
        if(b != null) {
            Block b2 = terrain.getBlock(b.getGridX(), b.getGridY() - b.getHitbox().getHeight() / 2);
            Block b3 = terrain.getBlock(b.getGridX(), b.getGridY() - b.getHitbox().getHeight()*2);
            if (this.isGrounded())
                if (!this.upCollisions())
                    if ((b2 == null || !b2.getHitbox().isSolid()) && (b3 == null || !b3.getHitbox().isSolid()))
                        return true;
        }

        return false;

    }

    public boolean grimpableLeft(){
        Block b = terrain.getBlock((int)hitbox.getX() - speed, (int)hitbox.getY() + hitbox.getHeight() - 1);
        if(b != null) {
            Block b2 = terrain.getBlock(b.getGridX(), b.getGridY() - b.getHitbox().getHeight() / 2);
            Block b3 = terrain.getBlock(b.getGridX(), b.getGridY() - b.getHitbox().getHeight()*2);
            if (this.isGrounded())
                if (!this.upCollisions())
                    if ((b2 == null || !b2.getHitbox().isSolid()) && (b3 == null || !b3.getHitbox().isSolid()))
                        return true;
        }

        return false;
    }

    public void grimper(int side){
        if (side == 1){ //check droite
            Block b = terrain.getBlock((int)hitbox.getX() + hitbox.getWidth() + speed, (int)hitbox.getY() + hitbox.getHeight() - 1);
            if(b != null) {
                hitbox.setY(b.getHitbox().getY() - hitbox.getHeight());
                hitbox.setX(this.getHitbox().getX() + getSpeed());
            }
        }

        else{ //check gauche
            Block b = terrain.getBlock((int)hitbox.getX() - speed, (int)hitbox.getY() + hitbox.getHeight() - 1);
            if(b != null){
                hitbox.setY(b.getHitbox().getY() - hitbox.getHeight());
                hitbox.setX(this.getHitbox().getX() - getSpeed());
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

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public String getAction(){
        return action.getValue();
    }

    public StringProperty actionProperty(){
        return action;}

    public void setAction(String a){
        action.setValue(a);
    }
}
