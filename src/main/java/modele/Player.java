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
    private final double gravite = 9.81;
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
        else if (right){
            this.setXProperty(this.xProperty.getValue() + walkSpeed);
        }
    }

    public void jump() {
        yProperty.setValue(yProperty.getValue() - jumpForce);
    }

    public void applyGrav(){
        yProperty.setValue(yProperty.getValue() + gravite);
    }


    public boolean isGrounded(Block block) {
        if(this.yProperty.intValue() + this.height >= block.getHitY() && this.yProperty.intValue() + this.height <= block.getHitY() + block.getInsideOffset())
            if((xProperty.intValue() >= block.getHitX() && xProperty.intValue() < block.getHitX() + block.getTile().getHitbox().getWidth()) || (xProperty.intValue() + width >= block.getHitX() && xProperty.intValue() + width < block.getHitX() + block.getTile().getHitbox().getWidth())){
                setYProperty(block.getHitY() - height);
                return true;
            }
        return false;
    }


    // haut du block = block.getHitY(); bas du block = block.getHitY() + block.getTile().getHitbox().getHeight()
    // haut du personnage = yProperty.intValue(); bas du personnage = yProperty.intValue() + height

    public int sideCollisions(Block block){
        if((yProperty.intValue() > block.getHitY() && yProperty.intValue() <= block.getHitY() + block.getTile().getHitbox().getHeight()) || (yProperty.intValue() + height > block.getHitY() && yProperty.intValue() + height <= block.getHitY() + block.getTile().getHitbox().getHeight())) {
            if (xProperty.intValue() <= block.getHitX() + block.getTile().getHitbox().getWidth() && xProperty.intValue() >= block.getHitX() + block.getTile().getHitbox().getWidth() - block.getInsideOffset()) { // cote droit d'un block
                setXProperty(block.getHitX() + block.getTile().getHitbox().getWidth() + 1);
                return -1; // joueur bloque a gauche
            } else if (xProperty.intValue() + width >= block.getHitX() && xProperty.intValue() + width <= block.getHitX() + block.getInsideOffset()) { // cote gauche d'un block
                setXProperty(block.getHitX() - width - 1);
                return 1; // joueur bloque a droite
            }
        }
        return 0;
    }


    public int getHeight(){
        return height;
    }

    public int getWidth() {
        return width;
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
