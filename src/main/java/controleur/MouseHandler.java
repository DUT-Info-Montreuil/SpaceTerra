package controleur;

import javafx.scene.layout.Pane;

public class MouseHandler {

    private Pane pane;

    private int mouseX;

    private int mouseY;

    private boolean hasClicked;

    public MouseHandler(Pane pane) {

        this.pane = pane;
        hasClicked = false;
    }

    public void mouseManager() {
        pane.setOnMousePressed(e -> {
            mouseX = (int) e.getX();
            mouseY = (int) e.getY();
            hasClicked = true;
            System.out.println(hasClicked);
            System.out.println(mouseX);
            System.out.println(mouseY);
        });

        pane.setOnKeyReleased(e-> {
            hasClicked = false;
        });
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isHasClicked() {
        return hasClicked;
    }
}
