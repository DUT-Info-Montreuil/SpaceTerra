package vue;

import controleur.Controleur;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Inventory;
import modele.Slot;

import java.util.ArrayList;

public class InventoryView {
    public static Pane panneauDeJeu;

    private ArrayList<SlotView> slotViews;

    private Rectangle currentSlotView;

    private boolean show;

    public InventoryView(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
        this.slotViews = new ArrayList<>();
        currentSlotView = new Rectangle(0,0, 34, 34);
        currentSlotView.setFill(Color.TRANSPARENT);
        currentSlotView.setStroke(Color.YELLOW);
        panneauDeJeu.getChildren().add(currentSlotView);
    }

    public void setCurrentSlotViewPosition(IntegerProperty xMult){
        currentSlotView.toFront();
    }
    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    public ArrayList<SlotView> getSlotViews() {
        return slotViews;
    }

    public void setSlotViewPosition(int numSlot){
        slotViews.get(numSlot).getXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * getWidthMult(numSlot)));
        slotViews.get(numSlot).getYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100 + getHeightMult(numSlot)));
    }

    public void displayAllSlotViews(){
        if(show){
            for(SlotView slotView : this.slotViews){
                slotView.displaySlot();
                System.out.println("x : " + slotView.getX());
                System.out.println("y : " + slotView.getY());
            }
        }
        else {
            for(SlotView slotView : this.slotViews){
                if(slotView.getId() < 10){
                    slotView.displaySlot();
                }else {
                    slotView.hideSlot();
                }

            }
        }

    }

    private int getHeightMult(int numSlot) {

        int heightMult = numSlot/10;
        heightMult *= 32;

        return heightMult;
    }

    private int getWidthMult(int numSlot) {
        return numSlot % 10;
    }

    public Rectangle getcurrentSlotView() {
        return currentSlotView;
    }
}




