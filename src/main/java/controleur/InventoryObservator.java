package controleur;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import modele.ItemBlock;
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
                    inventoryView.deleteFullSlotImageView(slot);
                    inventoryView.deleteLabelQuantity(slot);
                    inventoryView.displayEmptySlotRectangle(slot);
                } else {
                    inventoryView.deleteEmptySlotRectangle(slot);
                    inventoryView.displayFullSlotImageView(slot);
                    inventoryView.displayLabelQuantity(slot);
                }
            }
        }
    }
}

