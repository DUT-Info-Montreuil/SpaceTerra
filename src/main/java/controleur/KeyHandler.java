package controleur;

import javafx.scene.layout.Pane;


public class KeyHandler {
    private boolean rightPressed, leftPressed, upPressed, downPressed,sprintPressed;
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
        slotTenTyped = false;
        slotNineTyped = false;
        slotEightTyped = false;
        slotSevenTyped = false;
        slotSixTyped = false;
        slotFiveTyped = false;
        slotFourTyped = false;
        slotThreeTyped = false;
        slotTwoTyped = false;
        slotOneTyped = false;
        this.pane = pane;
    }

    private void keyPressed() {
        pane.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case D -> rightPressed = true;
                case Q -> leftPressed = true;
                case S -> downPressed = true;
                case SPACE -> upPressed = true;
                case SHIFT -> sprintPressed = true;
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
                case SHIFT -> sprintPressed = false;
                case SPACE -> upPressed = false;
            }
        });
    }

    private void keyTyped(){
        pane.setOnKeyTyped(e -> {
           // System.out.println(e.getCharacter());
            switch (e.getCharacter()){
                case "&", "1" -> slotOneTyped = true;
                case "é", "2" -> slotTwoTyped = true;
                case "\"", "3" -> slotThreeTyped = true;
                case "\'", "4" -> slotFourTyped = true;
                case "(", "5" -> slotFiveTyped = true;
                case "-", "6" -> slotSixTyped = true;
                case "è", "7" -> slotSevenTyped = true;
                case "_", "8" -> slotEightTyped = true;
                case "ç", "9" -> slotNineTyped = true;
                case "à", "0" -> slotTenTyped = true;

            }
        });
    }



    public void keyManager(){
        keyPressed();
        keyReleased();
        keyTyped();
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isSlotOneTyped() {
        return slotOneTyped;
    }

    public boolean isSlotTwoTyped() {
        return slotTwoTyped;
    }

    public boolean isSlotThreeTyped() {
        return slotThreeTyped;
    }

    public boolean isSlotFourTyped() {
        return slotFourTyped;
    }

    public boolean isSlotFiveTyped() {
        return slotFiveTyped;
    }

    public boolean isSlotSixTyped() {
        return slotSixTyped;
    }

    public boolean isSlotSevenTyped() {
        return slotSevenTyped;
    }

    public boolean isSlotEightTyped() {
        return slotEightTyped;
    }

    public boolean isSlotNineTyped() {
        return slotNineTyped;
    }

    public boolean isSlotTenTyped() {
        return slotTenTyped;
    }

    public boolean isSprintPressed(){ return sprintPressed; }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public void setSlotOneTyped(boolean slotOneTyped) {
        this.slotOneTyped = slotOneTyped;
    }

    public void setSlotTwoTyped(boolean slotTwoTyped) {
        this.slotTwoTyped = slotTwoTyped;
    }

    public void setSlotThreeTyped(boolean slotThreeTyped) {
        this.slotThreeTyped = slotThreeTyped;
    }

    public void setSlotFourTyped(boolean slotFourTyped) {
        this.slotFourTyped = slotFourTyped;
    }

    public void setSlotFiveTyped(boolean slotFiveTyped) {
        this.slotFiveTyped = slotFiveTyped;
    }

    public void setSlotSixTyped(boolean slotSixTyped) {
        this.slotSixTyped = slotSixTyped;
    }

    public void setSlotSevenTyped(boolean slotSevenTyped) {
        this.slotSevenTyped = slotSevenTyped;
    }

    public void setSlotEightTyped(boolean slotEightTyped) {
        this.slotEightTyped = slotEightTyped;
    }

    public void setSlotNineTyped(boolean slotNineTyped) {
        this.slotNineTyped = slotNineTyped;
    }

    public void setSlotTenTyped(boolean slotTenTyped) {
        this.slotTenTyped = slotTenTyped;
    }
}
