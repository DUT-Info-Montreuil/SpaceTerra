package controleur;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class MouseHandler {

    private Pane pane;

    private IntegerProperty mouseXP;

    private IntegerProperty mouseYP;

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
        mouseXP = new SimpleIntegerProperty();
        mouseYP = new SimpleIntegerProperty();
    }


    public void mouseManager() {
        pane.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseXP.setValue((int) e.getX());
                mouseYP.setValue((int) e.getY());
                hasPressedLeft = true;
            }
            else if(e.getButton() == MouseButton.SECONDARY){
                mouseXP.setValue((int) e.getX());
                mouseYP.setValue((int) e.getY());
                hasPressedRight = true;
            }
        });

        pane.setOnMouseReleased(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseXP.setValue((int) e.getX());
                mouseYP.setValue((int) e.getY());
                hasPressedLeft = false;
            }
            else if(e.getButton() == MouseButton.SECONDARY){
                mouseXP.setValue((int) e.getX());
                mouseYP.setValue((int) e.getY());
                hasPressedRight = false;
            }
        });

        pane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseXP.setValue((int) e.getX());
                mouseYP.setValue((int) e.getY());
                hasClickedLeft = true;
            }
            else if(e.getButton() == MouseButton.SECONDARY){
                mouseXP.setValue((int) e.getX());
                mouseYP.setValue((int) e.getY());
                hasClickedRight = true;
            }
        });
    }

    public int getMouseX() {
        return mouseXP.getValue();
    }

    public int getMouseY() {
        return mouseYP.getValue();
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

    public IntegerProperty getMouseXPProperty() {
        return mouseXP;
    }

    public IntegerProperty getMouseYPProperty() {
        return mouseYP;
    }
}
