package controleur;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;


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
        pane.setOnKeyReleased(e -> {
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

    public static boolean isRightPressed() {
        return rightPressed;
    }

    public static boolean isLeftPressed() {
        return leftPressed;
    }

    public static boolean isUpPressed() {
        return upPressed;
    }

    public static boolean isDownPressed() {
        return downPressed;
    }

}
