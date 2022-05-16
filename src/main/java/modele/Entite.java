package modele;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;

public abstract class Entite {

    private int vie;
    private int vitesse;
    private Hitbox hitbox;
    private Image image;
    public Entite(int vie, int vitesse, Hitbox hitbox, String path){
        this.vie = vie;
        this.vitesse = vitesse;
        this.hitbox = hitbox;
        this.image = new Image(String.valueOf(getClass().getResource(path)));

    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
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
                hitbox.setX(block.getHitX() + block.getTile().getHitbox().getWidth() + 1);
                return -1; // joueur bloque a gauche
            } else if (hitbox.getX().intValue() + hitbox.getWidth() >= block.getHitX() && hitbox.getX().intValue() + hitbox.getWidth() <= block.getHitX() + block.getInsideOffset()) { // cote gauche d'un block
                hitbox.setX(block.getHitX() - hitbox.getWidth() - 1);
                return 1; // joueur bloque a droite
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

    public abstract void deplacement(Player player, boolean leftCheck, boolean rightCheck);
}
