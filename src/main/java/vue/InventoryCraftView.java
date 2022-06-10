package vue;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class InventoryCraftView {
    private ArrayList<SlotView> slotViews;
    private Pane panneauDeJeu;

    public InventoryCraftView(Pane panneauDeJeu) {
        panneauDeJeu = panneauDeJeu;
        slotViews = new ArrayList<>();
    }

    public void setSlotViewPosition(int numSlot) {
        slotViews.get(numSlot).getXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(200 + 32 * getWidthMult(numSlot)));
        slotViews.get(numSlot).getYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(200 + getHeightMult(numSlot)));
    }

    public ArrayList<SlotView> getSlotViews() {
        return slotViews;
    }

    public void display(boolean display) {
        if (display) {
            for (SlotView slotView : this.slotViews) {
                slotView.displaySlot();
                if (slotView.getItemView() != null) {
                    slotView.displayLabel();
                }
            }
        } else {
            for (SlotView slotView : this.slotViews) {
                slotView.hideSlot();
            }

        }
    }

    private int getHeightMult(int numSlot) {

        int heightMult = numSlot / 3;
        heightMult *= 32;

        return heightMult;
    }

    private int getWidthMult(int numSlot) {
        return numSlot % 3;
    }
}
