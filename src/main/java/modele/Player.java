package modele;

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
    private Image image;

    private final double walkSpeed = 10;
    private final double jumpForce = 100;
    private static double gravity = 9.81;
    private double fallMultiplier = 1.01;
    private double lowJumpMultiplier = 2.5;
    // v = pos - prevPos / diffTemp
    // this.setYProperty(gravity * fallMultiplier)

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
        if (isGrounded())
            this.setYProperty(this.yProperty.getValue() - jumpForce);
    }

    public void applyGrav(){
        if(!isGrounded())
            this.setYProperty(this.yProperty.getValue() + gravity);
    }

    public boolean isGrounded() {
        return this.yProperty.getValue() >= 80;
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
