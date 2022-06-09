package controleur;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class MouseHandler {

    private Pane pane;

    private IntegerProperty mouseX;

    private IntegerProperty mouseY;

    private boolean hasPressedLeft;
    private boolean hasPressedRight;

    private boolean hasClickedLeft;

    private boolean hasClickedRight;

    private boolean hasScrollUp;

    private boolean hasScrollDown;


    public MouseHandler(Pane pane) {

        this.pane = pane;
        hasPressedLeft = false;
        hasPressedRight = false;
        hasClickedLeft = false;
        hasClickedRight = false;
        mouseX = new SimpleIntegerProperty();
        mouseY = new SimpleIntegerProperty();
    }


    public void setHasScrollUp(boolean hasScrollUp) {
        this.hasScrollUp = hasScrollUp;
    }

    public void setHasScrollDown(boolean hasScrollDown) {
        this.hasScrollDown = hasScrollDown;
    }

    public void mouseManager() {

        pane.setOnMouseDragged(e -> {
            mouseX.setValue((int) e.getX());
            mouseY.setValue((int) e.getY());
            System.out.println((int) e.getX());
            System.out.println((int) e.getY());

        });
        pane.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseX.setValue((int) e.getX());
                mouseY.setValue((int) e.getY());
                hasPressedLeft = true;
            }
            else if(e.getButton() == MouseButton.SECONDARY){
                mouseX.setValue((int) e.getX());
                mouseY.setValue((int) e.getY());
                hasPressedRight = true;
            }
        });

        pane.setOnMouseReleased(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseX.setValue((int) e.getX());
                mouseY.setValue((int) e.getY());
                hasPressedLeft = false;
            }
            else if(e.getButton() == MouseButton.SECONDARY){
                mouseX.setValue((int) e.getX());
                mouseY.setValue((int) e.getY());
                hasPressedRight = false;
            }
        });

        pane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseX.setValue((int) e.getX());
                mouseY.setValue((int) e.getY());
                hasClickedLeft = true;
            }
            else if(e.getButton() == MouseButton.SECONDARY){
                mouseX.setValue((int) e.getX());
                mouseY.setValue((int) e.getY());
                hasClickedRight = true;
            }
        });

        pane.setOnScroll(e -> {
            if(e.getDeltaY() < 0){
                hasScrollDown = true;
                hasScrollUp = false;
            }
            else if (e.getDeltaY() > 0){
                hasScrollUp = true;
                hasScrollDown = false;
            }
            else {
                hasScrollUp = false;
                hasScrollDown = false;
            }
        });

    }

    public int getMouseX() {
        return mouseX.getValue();
    }

    public int getMouseY() {
        return mouseY.getValue();
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

    public IntegerProperty getMouseXProperty() {
        return mouseX;
    }

    public IntegerProperty getMouseYProperty() {
        return mouseY;
    }
    public boolean isHasScrollUp() {
        return hasScrollUp;
    }

    public boolean isHasScrollDown() {
        return hasScrollDown;
    }
}
