package controleur;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class MouseHandler {

    private Pane pane;

    private int mouseX;

    private int mouseY;

    private boolean hasClickedLeft;
    private boolean hasClickedRight;

    public void setHasClickedLeft(boolean hasClickedLeft) {
        this.hasClickedLeft = hasClickedLeft;
    }

    public void setHasClickedRight(boolean hasClickedRight) {
        this.hasClickedRight = hasClickedRight;
    }

    public MouseHandler(Pane pane) {

        this.pane = pane;
        hasClickedLeft = false;
        hasClickedRight = false;
    }

    public void mouseManager() {
        pane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseX = (int) e.getX();
                mouseY = (int) e.getY();
                hasClickedLeft = true;
                System.out.println(hasClickedLeft);
                System.out.println(mouseX);
                System.out.println(mouseY);
            }
            else if(e.getButton() == MouseButton.SECONDARY){
                    mouseX = (int) e.getX();
                    mouseY = (int) e.getY();
                    hasClickedRight = true;
                    System.out.println(hasClickedRight);
                    System.out.println(mouseX);
                    System.out.println(mouseY);
            }
        });
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isHasClickedLeft() {
        return hasClickedLeft;
    }

    public boolean isHasClickedRight(){
        return hasClickedRight;
    }
}
