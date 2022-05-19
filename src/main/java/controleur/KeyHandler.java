package controleur;

import javafx.scene.layout.Pane;


public class KeyHandler {
    private boolean rightPressed, leftPressed, upPressed, downPressed;
    private boolean slotOneTyped, slotTwoTyped, slotThreeTyped, slotFourTyped, slotFiveTyped, slotSixTyped, slotSevenTyped, slotEightTyped, slotNineTyped, slotTenTyped;

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }


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
            //System.out.println(e.getCode());
            switch (e.getCode()) {
                case D -> rightPressed = false;
                case Q -> leftPressed = false;
                case S -> downPressed = false;
                case Z -> upPressed = false;
            }
        });
    }

    private void keyTyped(){
        pane.setOnKeyTyped(e -> {
            System.out.println(e.getCharacter());
            switch (e.getCharacter()){
                case "&" -> slotOneTyped = true;
                case "1" -> slotOneTyped = true;
                case "é" -> slotTwoTyped = true;
                case "2" -> slotTwoTyped = true;
                case "\"" -> slotThreeTyped = true;
                case "3" -> slotThreeTyped = true;
                case "\'" -> slotFourTyped = true;
                case "4" -> slotFourTyped = true;

            }
        });
    }



    public void keyManager(){
        keyPressed();
        keyReleased();
        keyTyped();
    }
}
