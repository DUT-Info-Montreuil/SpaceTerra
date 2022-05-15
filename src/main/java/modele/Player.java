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
    private final int height = 48;
    private final int width = 48;
    private final double walkSpeed = 10;
    private Image image;
    private final double gravite = 2;
    private final int jumpForce = 100;

    public Player(){
        this.vie = 20;
        this.main1 = null;
        this.armure = null;
        xProperty = new SimpleDoubleProperty(0);
        yProperty = new SimpleDoubleProperty(0);
        image = new Image(String.valueOf(getClass().getResource("/Sprites/MC/MCSpace_Idle_right.gif")));

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
        yProperty.setValue(yProperty.getValue() - jumpForce);
    }

    public void applyGrav(){
        yProperty.setValue(yProperty.getValue() + gravite);
    }


    public boolean isGrounded(int y, int x, int width) {
        if(this.yProperty.intValue() + this.height == y)
            return (xProperty.intValue() >= x) && (xProperty.intValue() < x + width);
        return false;
    }


    public int getHeight(){
        return height;
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
