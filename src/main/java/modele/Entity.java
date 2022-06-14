package modele;

import controleur.Controleur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import vue.DebugView;


public abstract class Entity {

    private IntegerProperty health;
    private int speed;
    private Hitbox hitbox;
    private Image image;
    private final int jumpHeight = 20;
    public int jumpCount = jumpHeight;
    private boolean isJumping = false;
    private boolean flying = false;
    public double gravity = 5;
    private Terrain terrain;
    private boolean canAttack;

    private String id;
    public int actionRange = 4;

    public static int compteur = 0;

    public Entity(int health, int speed, Hitbox hitbox, String path, Terrain terrain) {
        this.health = new SimpleIntegerProperty(health);
        this.speed = speed;
        this.hitbox = hitbox;
        this.image = new Image(String.valueOf((getClass().getResource(path))));
        this.terrain = terrain;
        this.canAttack = true;
        this.id = "E"+compteur;
        compteur++;

    }

    public String getId() {
        return id;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public int getActionRange() {
        return actionRange;
    }

    public Terrain getTerrain() {
        return terrain;
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

    public Image getImage() {
        return image;
    }

    public void setImage(String path) {
        this.image = new Image(String.valueOf(getClass().getResource(path)));
    }


    public boolean sideRightCollisions() {
        Block b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() + speed, hitbox.getY().intValue()); // rightUp
        if (b != null && b.getTile().getHitbox().isSolid()) {
            if (hitbox.getX().intValue() + hitbox.getWidth() >= b.getHitX() && hitbox.getX().intValue() + hitbox.getWidth() <= b.getHitX() + 10) {
                this.hitbox.setX(hitbox.getX().intValue() - ((hitbox.getX().intValue() + hitbox.getWidth()) - b.getHitX()));
                //DebugView.debugBlock(b, Color.VIOLET);
                //System.out.println("Right");
                return true;
            }
        }
        b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() + speed, hitbox.getY().intValue() + hitbox.getHeight() / 2); // rightMiddle
        if (b != null && b.getTile().getHitbox().isSolid()) {
            if (hitbox.getX().intValue() + hitbox.getWidth() >= b.getHitX() && hitbox.getX().intValue() + hitbox.getWidth() <= b.getHitX() + 10) {
                this.hitbox.setX(hitbox.getX().intValue() - ((hitbox.getX().intValue() + hitbox.getWidth()) - b.getHitX()));
                //DebugView.debugBlock(b, Color.BLUEVIOLET);
                //System.out.println("Right");
                return true;
            }
        }
        b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() + speed, hitbox.getY().intValue() + hitbox.getHeight() - 1); // rightDown
        if (b != null && b.getTile().getHitbox().isSolid()) {
            if (hitbox.getX().intValue() + hitbox.getWidth() >= b.getHitX() && hitbox.getX().intValue() + hitbox.getWidth() <= b.getHitX() + 10) {
                this.hitbox.setX(hitbox.getX().intValue() - ((hitbox.getX().intValue() + hitbox.getWidth()) - b.getHitX()));
                //DebugView.debugBlock(b, Color.DARKVIOLET);
                //System.out.println("Right");
                return true;
            }
        }
        return false;
    }

    public boolean sideLeftCollision() {
        Block b = terrain.getBlock(hitbox.getX().intValue() - speed, hitbox.getY().intValue()); // leftUp
        if (b != null && b.getTile().getHitbox().isSolid()) {
            if (hitbox.getX().intValue() <= b.getHitX() + b.getTile().getHitbox().getWidth() && hitbox.getX().intValue() >= b.getHitX() + b.getTile().getHitbox().getWidth() - 10) {
                this.hitbox.setX(hitbox.getX().intValue() + ((b.getHitX() + b.getTile().getHitbox().getWidth()) - hitbox.getX().intValue()));
                //System.out.println("LeftUp");
                //DebugView.debugBlock(b, Color.VIOLET);
                return true;
            }
        }
        b = terrain.getBlock(hitbox.getX().intValue() - speed, hitbox.getY().intValue() + hitbox.getHeight() / 2); // leftMiddle
        if (b != null && b.getTile().getHitbox().isSolid()) {
            if (hitbox.getX().intValue() <= b.getHitX() + b.getTile().getHitbox().getWidth() && hitbox.getX().intValue() >= b.getHitX() + b.getTile().getHitbox().getWidth() - 10) {
                this.hitbox.setX(hitbox.getX().intValue() + ((b.getHitX() + b.getTile().getHitbox().getWidth()) - hitbox.getX().intValue()));
                //System.out.println("LeftMiddle");
                //DebugView.debugBlock(b, Color.YELLOW);
                return true;
            }
        }
        b = terrain.getBlock(hitbox.getX().intValue() - speed, hitbox.getY().intValue() + hitbox.getHeight() - 1); // leftDown
        if (b != null && b.getTile().getHitbox().isSolid()) {
            if (hitbox.getX().intValue() <= b.getHitX() + b.getTile().getHitbox().getWidth() && hitbox.getX().intValue() >= b.getHitX() + b.getTile().getHitbox().getWidth() - 10) {
                this.hitbox.setX(hitbox.getX().intValue() + ((b.getHitX() + b.getTile().getHitbox().getWidth()) - hitbox.getX().intValue()));
                //System.out.println("LeftDown");
                //DebugView.debugBlock(b, Color.GREEN);
                return true;
            }
        }
        return false;
    }

    public boolean upCollisions() { // can allow clipping rn if player gets stuck innit
        Block b = terrain.getBlock(hitbox.getX().intValue() + 5, hitbox.getY().intValue() - jumpCount);// upLeft
        if (b != null && b.getTile().getHitbox().isSolid()) {
            if (hitbox.getY().intValue() <= b.getHitY() + b.getTile().getHitbox().getHeight() && hitbox.getY().intValue() >= b.getHitY() + b.getTile().getHitbox().getHeight() - 20) {
                this.hitbox.setY(hitbox.getY().intValue() + ((b.getHitY() + b.getTile().getHitbox().getHeight()) - hitbox.getY().intValue()));
                //DebugView.debugBlock(b, Color.DARKGRAY);
                //System.out.println("UpCol");
                return true;
            }
        }
        b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() / 2, hitbox.getY().intValue() - jumpCount);// upMiddle
        if (b != null && b.getTile().getHitbox().isSolid()) {
            if (hitbox.getY().intValue() <= b.getHitY() + b.getTile().getHitbox().getHeight() && hitbox.getY().intValue() >= b.getHitY() + b.getTile().getHitbox().getHeight() - 20) {
                this.hitbox.setY(hitbox.getY().intValue() + ((b.getHitY() + b.getTile().getHitbox().getHeight()) - hitbox.getY().intValue()));
                //DebugView.debugBlock(b, Color.DIMGRAY);
                //System.out.println("UpCol");
                return true;
            }
        }
        b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() - 5, hitbox.getY().intValue() - jumpCount);// upRight
        if (b != null && b.getTile().getHitbox().isSolid()) {
            if (hitbox.getY().intValue() <= b.getHitY() + b.getTile().getHitbox().getHeight() && hitbox.getY().intValue() >= b.getHitY() + b.getTile().getHitbox().getHeight() - 20) {
                this.hitbox.setY(hitbox.getY().intValue() + ((b.getHitY() + b.getTile().getHitbox().getHeight()) - hitbox.getY().intValue()));
                //DebugView.debugBlock(b, Color.SLATEGRAY);
                //System.out.println("UpCol");
                return true;
            }
        }
        return false;
    }

    public boolean isGrounded() {
        Block b = terrain.getBlock(hitbox.getX().intValue() + 5, hitbox.getY().intValue() + hitbox.getHeight() + (int) gravity);// downLeft
        if (b != null && b.getTile().getHitbox().isSolid()) {
            if (hitbox.getY().intValue() + hitbox.getHeight() >= b.getHitY() && hitbox.getY().intValue() + hitbox.getHeight() <= b.getHitY() + 10) {
                //DebugView.debugBlock(b, Color.RED);
                //System.out.println("isGrounded");
                this.hitbox.setY(hitbox.getY().intValue() - ((hitbox.getY().intValue() + hitbox.getHeight()) - b.getHitY()));
                return true;
            }
        }
        b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() / 2, hitbox.getY().intValue() + hitbox.getHeight() + (int) gravity);// downMiddle
        if (b != null && b.getTile().getHitbox().isSolid()) {
            if (hitbox.getY().intValue() + hitbox.getHeight() >= b.getHitY() && hitbox.getY().intValue() + hitbox.getHeight() <= b.getHitY() + 10) {
                //DebugView.debugBlock(b, Color.MEDIUMVIOLETRED);
                //System.out.println("isGrounded");
                this.hitbox.setY(hitbox.getY().intValue() - ((hitbox.getY().intValue() + hitbox.getHeight()) - b.getHitY()));
                return true;
            }
        }
        b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() - 5, hitbox.getY().intValue() + hitbox.getHeight() + (int) gravity);// downRight
        if (b != null && b.getTile().getHitbox().isSolid()) {
            if (hitbox.getY().intValue() + hitbox.getHeight() >= b.getHitY() && hitbox.getY().intValue() + hitbox.getHeight() <= b.getHitY() + 10) {
                //DebugView.debugBlock(b, Color.DARKRED);
                //System.out.println("isGrounded");
                this.hitbox.setY(hitbox.getY().intValue() - ((hitbox.getY().intValue() + hitbox.getHeight()) - b.getHitY()));
                return true;
            }
        }
        return false;
    }


    public void applyGrav() {
        hitbox.getY().set(hitbox.getY().getValue() + gravity);
        gravity += 0.05;
    }

    public abstract void movement(Player player, boolean leftCheck, boolean rightCheck);



    public int distanceToBlock(Block b) {
        double centerPX = this.hitbox.getX().intValue() + this.hitbox.getWidth() / 2; //centre du joueur en x
        double centerPY = this.hitbox.getY().intValue() + this.hitbox.getHeight() / 2; //centre du joueur en y
        double centerBX = b.getHitX() + b.getTile().getHitbox().getWidth() / 2; //centre du block en x
        double centerBY = b.getHitY() + b.getTile().getHitbox().getHeight() / 2; // centre du block en y
        double sqrt = Math.sqrt(Math.pow(centerBX - centerPX, 2.0) + Math.pow(centerBY - centerPY, 2.0));
        return (int) sqrt / 32; //distance euclidienne / 32 pour avoir une distance en blocks
    }

    public int distanceToPosition(int x, int y) {
        double centerPX = this.hitbox.getX().intValue() + this.hitbox.getWidth() / 2; //centre du joueur en x
        double centerPY = this.hitbox.getY().intValue() + this.hitbox.getHeight() / 2; //centre du joueur en y
        double sqrt = Math.sqrt(Math.pow(x - centerPX, 2.0) + Math.pow(y - centerPY, 2.0));
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
                getHitbox().setY(getHitbox().getY().intValue() - --jumpCount);
            }
        }

    }

    public boolean grimpableRight() {
        Block b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() + speed, hitbox.getY().intValue() + hitbox.getHeight() - 1);
        if (b != null) {
            Block b2 = terrain.getBlock(b.getX(), b.getY() - b.getTile().getHitbox().getHeight() / 2);
            Block b3 = terrain.getBlock(b.getX(), b.getY() - b.getTile().getHitbox().getHeight() * 2);
            if (this.isGrounded())
                if (!this.upCollisions())
                    if ((b2 == null || !b2.getTile().getHitbox().isSolid()) && (b3 == null || !b3.getTile().getHitbox().isSolid()))
                        return true;
        }

        return false;

    }

    public boolean grimpableLeft() {
        Block b = terrain.getBlock(hitbox.getX().intValue() - speed, hitbox.getY().intValue() + hitbox.getHeight() - 1);
        if (b != null) {
            Block b2 = terrain.getBlock(b.getX(), b.getY() - b.getTile().getHitbox().getHeight() / 2);
            Block b3 = terrain.getBlock(b.getX(), b.getY() - b.getTile().getHitbox().getHeight() * 2);
            if (this.isGrounded())
                if (!this.upCollisions())
                    if ((b2 == null || !b2.getTile().getHitbox().isSolid()) && (b3 == null || !b3.getTile().getHitbox().isSolid()))
                        return true;
        }

        return false;
    }

    public void grimper(int side) {
        if (side == 1) { //check droite
            Block b = terrain.getBlock(hitbox.getX().intValue() + hitbox.getWidth() + speed, hitbox.getY().intValue() + hitbox.getHeight() - 1);
            if (b != null) {
                hitbox.setY(b.getHitY() - hitbox.getHeight());
                hitbox.setX(this.getHitbox().getX().intValue() + getSpeed());
            }
        } else { //check gauche
            Block b = terrain.getBlock(hitbox.getX().intValue() - speed, hitbox.getY().intValue() + hitbox.getHeight() - 1);
            if (b != null) {
                hitbox.setY(b.getHitY() - hitbox.getHeight());
                hitbox.setX(this.getHitbox().getX().intValue() - getSpeed());
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


}
