package controleur;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.beans.EventHandler;

public class KeyHandler {
    private static boolean rightPressed, leftPressed, upPressed, downPressed;
    private Scene scene;

    public KeyHandler(Scene scene) {
        rightPressed = false;
        leftPressed = false;
        upPressed = false;
        downPressed = false;
        this.scene = scene;

    }

    private void keyPressed() {
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.D) {
                System.out.println("Key Pressed: avancer : " + e.getCode());
                rightPressed = true;
            }
            else if (e.getCode() == KeyCode.Q) {
                System.out.println("Key Pressed: reculer : " + e.getCode());
                leftPressed = true;
            }
            else if (e.getCode() == KeyCode.Z) {
                System.out.println("Key Pressed: sauter : " + e.getCode());
                upPressed = true;
            } else if (e.getCode() == KeyCode.S) {
                System.out.println("Key Pressed: s'accroupir: " + e.getCode());
                downPressed = true;

            }
       });
   }

    private void keyReleased() {
        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.D) {
                System.out.println("Key Released: ne plus avancer : " + e.getCode());
                rightPressed = false;
            }
            else if (e.getCode() == KeyCode.Q) {
                System.out.println("Key Released: ne plus reculer : " + e.getCode());
                leftPressed = false;
            }
            else if (e.getCode() == KeyCode.Z) {
                System.out.println("Key Released: ne plus sauter : " + e.getCode());
                upPressed = false;
            } else if (e.getCode() == KeyCode.S) {
                System.out.println("Key Released: ne plus s'accroupir: " + e.getCode());
                downPressed = false;

            }

        });
    }

    public void keyWorking(){
        keyPressed();
        keyReleased();
    }

}
