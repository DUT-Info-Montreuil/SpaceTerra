package controleur;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;


public class KeyHandler {
    public static boolean rightPressed, leftPressed, upPressed, downPressed;

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
            }
       });
   }

    private void keyReleased() {
        pane.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case D -> rightPressed = false;
                case Q -> leftPressed = false;
                case S -> downPressed = false;
            }
        });
    }

    private void KeyTyped() {
        pane.setOnKeyTyped(e -> {
            if (e.getCharacter().equalsIgnoreCase("Z")) {
                upPressed = true;
            }
        });
    }

    public void keyWorking(){
        keyPressed();
        keyReleased();
        KeyTyped();
    }

}
