package vue;

import javafx.scene.layout.Pane;


public class CraftInventoryView extends InventoryView{


    public CraftInventoryView(Pane panneauDeJeu) {
        super(panneauDeJeu);
    }

    public void setSlotViewPosition(int numSlot) {
        getSlotViews().get(numSlot).getXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * getWidthMult(numSlot)));
        getSlotViews().get(numSlot).getYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(600 + getHeightMult(numSlot)));
    }

    @Override
    public void displayAllSlotViews() {
        if (isDisplay()) {
            for (SlotView slotView : getSlotViews()) {
                slotView.displaySlot();
                if (slotView.getItemView() != null) {
                    slotView.displayLabel();
                }
            }
        } else {
            for (SlotView slotView : getSlotViews()) {
                slotView.hideSlot();
            }
        }
    }

    public int getHeightMult(int numSlot) {

        int heightMult = numSlot / 3;
        heightMult *= 32;

        return heightMult;
    }


    public int getWidthMult(int numSlot) {
        return numSlot % 3;
    }
}
