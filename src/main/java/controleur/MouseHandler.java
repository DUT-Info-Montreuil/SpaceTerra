package controleur;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class MouseHandler {

    private Pane pane;

    private int mouseX;

    private int mouseY;

    private boolean hasPressedLeft;
    private boolean hasPressedRight;

    private boolean hasClickedLeft;

    private boolean hasClickedRight;



    public MouseHandler(Pane pane) {

        this.pane = pane;
        hasPressedLeft = false;
        hasPressedRight = false;
        hasClickedLeft = false;
        hasClickedRight = false;
    }

    public void mouseManager() {
        pane.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseX = (int) e.getX();
                mouseY = (int) e.getY();
                hasPressedLeft = true;
            }
            else if(e.getButton() == MouseButton.SECONDARY){
                    mouseX = (int) e.getX();
                    mouseY = (int) e.getY();
                    hasPressedRight = true;
            }
        });

        pane.setOnMouseReleased(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseX = (int) e.getX();
                mouseY = (int) e.getY();
                hasPressedLeft = false;
            }
            else if(e.getButton() == MouseButton.SECONDARY){
                mouseX = (int) e.getX();
                mouseY = (int) e.getY();
                hasPressedRight = false;
            }
        });

        pane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseX = (int) e.getX();
                mouseY = (int) e.getY();
                hasClickedLeft = true;
            }
            else if(e.getButton() == MouseButton.SECONDARY){
                mouseX = (int) e.getX();
                mouseY = (int) e.getY();
                hasClickedRight = true;
            }
        });
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isHasPressedLeft() {
        return hasPressedLeft;
    }

    public boolean isHasPressedRight(){
        return hasPressedRight;
    }

    public void setHasPressedLeft(boolean hasPressedLeft) {
        this.hasPressedLeft = hasPressedLeft;
    }

    public void setHasPressedRight(boolean hasPressedRight) {
        this.hasPressedRight = hasPressedRight;
    }

    public boolean isHasClickedLeft() {
        return hasClickedLeft;
    }

    public void setHasClickedLeft(boolean hasClickedLeft) {
        this.hasClickedLeft = hasClickedLeft;
    }

    public boolean isHasClickedRight() {
        return hasClickedRight;
    }

    public void setHasClickedRight(boolean hasClickedRight) {
        this.hasClickedRight = hasClickedRight;
    }
}
