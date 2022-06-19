package vue;

import javafx.beans.property.IntegerProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class InventoryView {

    public static Pane panneauDeJeu;
    private boolean display;
    private ArrayList<SlotView> slotViews;



    public void setDisplay(boolean display) {
        this.display = display;
        displayAllSlotViews();
    }


    public InventoryView(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
        this.slotViews = new ArrayList<>();
    }


    public static Pane getPanneauDeJeu() {
        return panneauDeJeu;
    }

    public ArrayList<SlotView> getSlotViews() {
        return slotViews;
    }

    public abstract void setSlotViewPosition(int numSlot);

    public abstract void displayAllSlotViews();

    public boolean isDisplay() {
        return display;
    }

    public abstract int getHeightMult(int numSlot);

    public abstract int getWidthMult(int numSlot);
}




