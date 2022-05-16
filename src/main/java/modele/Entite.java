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

    public abstract void deplacement();
}
