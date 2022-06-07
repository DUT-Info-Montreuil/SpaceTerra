package controleur;

import javafx.collections.ListChangeListener;
import modele.Slot;
import vue.InventoryView;

public class InventoryObservator implements ListChangeListener<Slot> {


    private InventoryView inventoryView;
    public InventoryObservator(InventoryView inventoryView) {
        super();
        this.inventoryView = inventoryView;
    }

    @Override
    public void onChanged(Change<? extends Slot> change) {
        while (change.next()) {
            if (change.wasReplaced()) {
                Slot slot = change.getList().get(change.getFrom());
                    if (slot.getItem() == null) {
                        inventoryView.hideFullSlotImageView(slot);
                        inventoryView.hideLabelQuantity(slot);
                        inventoryView.displayEmptySlotRectangle(slot);
                    } else {
                        inventoryView.hideEmptySlotRectangle(slot);
                        inventoryView.displayFullSlotImageView(slot);
                        inventoryView.displayLabelQuantity(slot);
                    }
            }
        }
    }
}

