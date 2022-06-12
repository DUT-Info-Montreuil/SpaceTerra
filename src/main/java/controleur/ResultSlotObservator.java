package controleur;

import javafx.scene.layout.Pane;
import modele.CraftInventory;
import modele.Slot;
import vue.CraftInventoryView;
import vue.SlotView;


public class ResultSlotObservator {
    Slot resultSlot;
    SlotView resultSlotView;


    public ResultSlotObservator(int id, CraftInventory craftInventory, Pane panneauDeJeu, CraftInventoryView craftInventoryView){
        resultSlot = craftInventory.getResultSlot();
        resultSlotView = new SlotView(id, null, panneauDeJeu);
        resultSlotView.getXProperty().bind(craftInventoryView.getSlotViews().get(id-1).getXProperty().add(64));
        resultSlotView.getYProperty().bind(craftInventoryView.getSlotViews().get(id-1).getYProperty());
        resultSlotView.hideSlot();
    }

    public SlotView getResultSlotView() {
        return resultSlotView;
    }

    public void setResultSlotView(SlotView resultSlotView) {
        this.resultSlotView = resultSlotView;
    }
}