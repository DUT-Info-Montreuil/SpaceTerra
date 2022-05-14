package modele;

import controleur.KeyHandler;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

public class Player {

    private int vie;
    private String main1;
    private String armure;
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private final double walkSpeed = 10;
    private Image image;
    private double vitesseY;
    private static double g = 0.1;
    private double gravite = 9.81;

    public Player(){
        this.vie = 20;
        this.main1 = null;
        this.armure = null;
        xProperty = new SimpleDoubleProperty(0);
        yProperty = new SimpleDoubleProperty(0);
        image = new Image(String.valueOf(getClass().getResource("/Sprites/MC/MCSpace_Idle_right.gif")));
        vitesseY = 5;

    }

    public void horizontalMovement(boolean left, boolean right) {
        if (left) {
            this.setXProperty(this.xProperty.getValue() - walkSpeed);
        }
        else {
            this.setXProperty(this.xProperty.getValue() + walkSpeed);
        }
    }

    public void jump() {
        this.setYProperty(this.yProperty.getValue() - vitesseY);
        if(vitesseY > 0)
            vitesseY -= g;
    }


    public boolean isGrounded() {
        return this.yProperty.getValue() >= 80;
    }

    public void setVitesseY(double vitesseY) {
        this.vitesseY = vitesseY;
    }

    public Image getImage() {
        return image;
    }

    public final DoubleProperty getXProperty() {
        return xProperty;
    }

    public  final DoubleProperty getYProperty() {
        return yProperty;
    }

    public final void setXProperty(double nb) {
        xProperty.setValue(nb);
    }

    public final void setYProperty(double nb) {
        yProperty.setValue(nb);
    }


}
