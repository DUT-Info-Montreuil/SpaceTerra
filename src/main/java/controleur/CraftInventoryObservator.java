package controleur;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import modele.CraftInventory;
import vue.CraftInventoryView;

public class CraftInventoryObservator extends InventoryObservator {


    public CraftInventoryObservator(CraftInventoryView craftInventoryView, CraftInventory craftInventory, Pane panneauDeJeu) {
        super(craftInventoryView, craftInventory, panneauDeJeu);

    }

    public void updateResultSlotView(CraftInventory craftInventory, CraftInventoryView craftInventoryView, ResultSlotObservator resultSlotObservator){
        craftInventory.verifCraft();
        if(craftInventoryView.isDisplay()){
            if(craftInventory.getResultSlot().getItem() == null){
                resultSlotObservator.getResultSlotView().diplayEmptySlot();
                resultSlotObservator.getResultSlotView().setItemView(null);

            }
            else {
                resultSlotObservator.getResultSlotView().displaySlot();
                resultSlotObservator.resultSlotView.getQuantityLabel().setText(String.valueOf(craftInventory.getResultSlot().getItemQuantity()));
                resultSlotObservator.getResultSlotView().displayLabel();
                if(resultSlotObservator.getResultSlotView().getItemView() == null){
                    resultSlotObservator.getResultSlotView().setItemView(new ImageView(craftInventory.getResultSlot().getItem().getTypeItem().getImage()));
                }
            }

        }
        else {
            resultSlotObservator.getResultSlotView().hideSlot();
            craftInventory.transferInventoryToPlayer(Controleur.player);
        }
    }

}

