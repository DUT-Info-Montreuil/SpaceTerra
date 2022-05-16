package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;

public class Player {

    private int vie;
    private String main1;
    private String armure;
    private DoubleProperty x;
    private DoubleProperty y;
    private final int height = 48;
    private final int width = 48;
    private final double walkSpeed = 10;
    private Image image;
    private final double gravite = 9.81;
    private final int jumpForce = 20;
    public int jumpCount = 0;
    private boolean isJumping;


    public Player(){
        this.vie = 20;
        this.main1 = null;
        this.armure = null;
        x = new SimpleDoubleProperty(0);
        y = new SimpleDoubleProperty(0);
        image = new Image(String.valueOf(getClass().getResource("/Sprites/MC/MCSpace_Idle_right.gif")));
    }

    public void horizontalMovement(boolean left, boolean right) {
        if (left) {
            this.setX(this.x.getValue() - walkSpeed);
        }
        else if (right){
            this.setX(this.x.getValue() + walkSpeed);
        }
    }

    public void jump() {
        if(!isJumping){
            isJumping = true;
            y.setValue(y.getValue() - ++jumpCount);
        }
        else{
            if(jumpCount >= jumpForce){
                System.out.println("over 100");
                stopJump();
            }
            else{
                System.out.println(jumpCount);
                y.setValue(y.getValue() - ++jumpCount);
            }
        }

    }
    public void stopJump(){
        System.out.println("Stopped func");
        jumpCount = 0;
        isJumping = false;
    }
    public boolean isJumping() {
        return isJumping;
    }

    public void applyGrav(){
        y.setValue(y.getValue() + gravite);
    }


    public boolean isGrounded(Block block) {
        if(this.y.intValue() + this.height >= block.getHitY() && this.y.intValue() + this.height <= block.getHitY() + block.getInsideOffset())
            if((x.intValue() >= block.getHitX() && x.intValue() < block.getHitX() + block.getTile().getHitbox().getWidth()) || (x.intValue() + width >= block.getHitX() && x.intValue() + width < block.getHitX() + block.getTile().getHitbox().getWidth())){
                setY(block.getHitY() - height);
                return true;
            }
        return false;
    }


    // haut du block = block.getHitY(); bas du block = block.getHitY() + block.getTile().getHitbox().getHeight()
    // haut du personnage = yProperty.intValue(); bas du personnage = yProperty.intValue() + height

    public int sideCollisions(Block block){
        if((y.intValue() > block.getHitY() && y.intValue() <= block.getHitY() + block.getTile().getHitbox().getHeight()) || (y.intValue() + height > block.getHitY() && y.intValue() + height <= block.getHitY() + block.getTile().getHitbox().getHeight())) {
            if (x.intValue() <= block.getHitX() + block.getTile().getHitbox().getWidth() && x.intValue() >= block.getHitX() + block.getTile().getHitbox().getWidth() - block.getInsideOffset()) { // cote droit d'un block
                setX(block.getHitX() + block.getTile().getHitbox().getWidth() + 1);
                return -1; // joueur bloque a gauche
            } else if (x.intValue() + width >= block.getHitX() && x.intValue() + width <= block.getHitX() + block.getInsideOffset()) { // cote gauche d'un block
                setX(block.getHitX() - width - 1);
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

    public final DoubleProperty xProperty() {
        return x;
    }

    public  final DoubleProperty yProperty() {
        return y;
    }

    public final void setX(double nb) {
        x.setValue(nb);
    }

    public final void setY(double nb) {
        y.setValue(nb);
    }
}
