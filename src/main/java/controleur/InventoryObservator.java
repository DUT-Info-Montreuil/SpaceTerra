package controleur;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import modele.Inventory;
import modele.Slot;
import vue.InventoryView;
import vue.SlotView;

public class InventoryObservator implements ListChangeListener<Slot> {


    private InventoryView inventoryView;

    private Inventory inventory;

    Pane panneauDeJeu;
    public InventoryObservator(InventoryView inventoryView, Inventory inventory, Pane panneauDeJeu) {
        super();
        this.inventoryView = inventoryView;
        this.inventory = inventory;
        this.panneauDeJeu = panneauDeJeu;
        initialize();
    }

    @Override
    public void onChanged(Change<? extends Slot> change) {
        while (change.next()) {
            if (change.wasReplaced()) {
                Slot slot = change.getList().get(change.getFrom());
                    if (slot.getItem() == null) {
                        inventoryView.getSlotViews().get(slot.getId()).setItemView(null);
                    } else {
                        inventoryView.getSlotViews().get(slot.getId()).setItemView(new ImageView(inventory.getItemFromSlot(slot.getId()).getTypeItem().getImage()));
                        inventoryView.getSlotViews().get(slot.getId()).getQuantityLabel().textProperty().bind(slot.itemQuantityProperty().asString());
                    }
            }
        }
    }

    public void initialize(){
        for(int i = 0; i < inventory.getSlots().size(); i++){
                try{
                    inventoryView.getSlotViews().add(new SlotView(i, new ImageView(inventory.getItemFromSlot(i).getTypeItem().getImage()), panneauDeJeu));
                    inventoryView.getSlotViews().get(i).getQuantityLabel().textProperty().bind(inventory.getSlot(i).itemQuantityProperty().asString());
                } catch(NullPointerException e){
                    inventoryView.getSlotViews().add(new SlotView(i, null, panneauDeJeu));
                }
                inventoryView.setSlotViewPosition(i);
        }
        inventoryView.displayAllSlotViews();
    }
}

