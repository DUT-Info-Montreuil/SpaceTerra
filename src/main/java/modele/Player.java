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

    private boolean isgrounded; //= joueur.getYProperty().getValue()==80;

    private DoubleProperty xProperty;

    private DoubleProperty yProperty;

    private final double walkSpeed = 10;

    private Image image;

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
            this.setXProperty(this.getXProperty().doubleValue() - walkSpeed);
        }
        else {
            this.setXProperty(this.getXProperty().doubleValue() + walkSpeed);
        }
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
