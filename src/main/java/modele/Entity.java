package modele;

import javafx.scene.image.Image;

public abstract class Entity {

    private int life;
    private int speed;
    private Hitbox hitbox;
    private Image image;
    public Entity(int vie, int vitesse, Hitbox hitbox, String path){
        this.life = vie;
        this.speed = vitesse;
        this.hitbox = hitbox;
        this.image = new Image(String.valueOf(getClass().getResource(path)));

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

    public void setImage(Image image) {
        this.image = image;
    }

    public int sideCollisions(Block block){
        if((hitbox.getY().intValue() > block.getHitY() && hitbox.getY().intValue() <= block.getHitY() + block.getTile().getHitbox().getHeight()) || (hitbox.getY().intValue() + hitbox.getHeight() > block.getHitY() && hitbox.getY().intValue() + hitbox.getHeight() <= block.getHitY() + block.getTile().getHitbox().getHeight())) {
            if (hitbox.getX().intValue() <= block.getHitX() + block.getTile().getHitbox().getWidth() && hitbox.getX().intValue() >= block.getHitX() + block.getTile().getHitbox().getWidth() - block.getInsideOffset()) { // cote droit d'un block
                hitbox.setX(block.getHitX() + block.getTile().getHitbox().getWidth() + 2);
                return -1; // Player blocked on left
            } else if (hitbox.getX().intValue() + hitbox.getWidth() >= block.getHitX() && hitbox.getX().intValue() + hitbox.getWidth() <= block.getHitX() + block.getInsideOffset()) { // cote gauche d'un block
                hitbox.setX(block.getHitX() - hitbox.getWidth() - 2);
                return 1; // Player blocked on right
            }
        }
        return 0;
    }

    public boolean isGrounded(Block block) {
        if(hitbox.getY().intValue() + hitbox.getHeight() >= block.getHitY() && hitbox.getY().intValue() + hitbox.getHeight() <= block.getHitY() + block.getInsideOffset())
            if((hitbox.getX().intValue() >= block.getHitX() && hitbox.getX().intValue() < block.getHitX() + block.getTile().getHitbox().getWidth()) || (hitbox.getX().intValue() + hitbox.getWidth() >= block.getHitX() && hitbox.getX().intValue() + hitbox.getWidth() < block.getHitX() + block.getTile().getHitbox().getWidth())){
                hitbox.getY().set(block.getHitY() - hitbox.getHeight());
                return true;
            }
        return false;
    }

    public void applyGrav(){
        hitbox.getY().set(hitbox.getY().getValue() + 9.81);
    }

    public abstract void movement(Player player, boolean leftCheck, boolean rightCheck);

    public int distanceToBlock(Block b) {
        double centerPX = this.hitbox.getX().intValue() + this.hitbox.getWidth()/2; //centre du joueur en x
        double centerPY = this.hitbox.getY().intValue() + this.hitbox.getHeight()/2; //centre du joueur en y
        double centerBX = b.getHitX() + b.getTile().getHitbox().getWidth()/2; //centre du block en x
        double centerBY = b.getHitY() + b.getTile().getHitbox().getHeight()/2; // centre du block en y
        double sqrt = Math.sqrt(Math.pow(centerBX - centerPX, 2.0) + Math.pow(centerBY - centerPY, 2.0));
        System.out.println("distance block : " + sqrt /32);
        return (int) sqrt /32; //distance euclidienne / 32 pour avoir une distance en blocks
    }
}
