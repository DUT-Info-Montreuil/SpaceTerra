package vue;

import javafx.beans.property.IntegerProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class PlayerInventoryView extends InventoryView{

    private Rectangle currentSlotView;

    public PlayerInventoryView(Pane panneauDeJeu) {
        super(panneauDeJeu);
        currentSlotView = new Rectangle(0,0, 34, 34);
        currentSlotView.setFill(Color.TRANSPARENT);
        currentSlotView.setStroke(Color.YELLOW);
        panneauDeJeu.getChildren().add(currentSlotView);
    }

    public void setSlotViewPosition(int numSlot){
        getSlotViews().get(numSlot).getXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * getWidthMult(numSlot)));
        getSlotViews().get(numSlot).getYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100 + getHeightMult(numSlot)));
    }

    public void setCurrentSlotViewPosition(IntegerProperty xMult){
        currentSlotView.toFront();
    }

    @Override
    public void displayAllSlotViews() {
        if(isDisplay()){
            for(SlotView slotView : getSlotViews()){
                slotView.displaySlot();
                if(slotView.getItemView() != null){
                    slotView.displayLabel();
                }
            }
        }
        else {
            for(SlotView slotView :getSlotViews()){
                if(slotView.getId() < 10){
                    slotView.displaySlot();
                    if(slotView.getItemView() != null) {
                        slotView.displayLabel();
                    }
                }else {
                    slotView.hideSlot();
                }
            }
        }
    }

    public int getHeightMult(int numSlot) {

        int heightMult = numSlot/10;
        heightMult *= 32;

        return heightMult;
    }


    public int getWidthMult(int numSlot) {
        return numSlot % 10;
    }

    public Rectangle getcurrentSlotView() {
        return currentSlotView;
    }
}




