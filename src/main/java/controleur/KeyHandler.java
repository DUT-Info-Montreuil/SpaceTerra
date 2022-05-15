package controleur;

import javafx.scene.layout.Pane;


public class KeyHandler {
    public static boolean rightPressed, leftPressed, upPressed, downPressed, saut;

    private Pane pane;

    public KeyHandler(Pane pane) {
        rightPressed = false;
        leftPressed = false;
        upPressed = false;
        downPressed = false;
        this.pane = pane;
    }

    private void keyPressed() {
        pane.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case D -> rightPressed = true;
                case Q -> leftPressed = true;
                case S -> downPressed = true;
                case Z -> upPressed = true;
            }
       });
   }

    private void keyReleased() {
        pane.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case D -> rightPressed = false;
                case Q -> leftPressed = false;
                case S -> downPressed = false;
                case Z -> upPressed = false;
            }
        });
    }



    public void keyManager(){
        keyPressed();
        keyReleased();
    }
}
