package controleur;

import javafx.scene.layout.Pane;
import modele.PlayerInventory;
import vue.PlayerInventoryView;

public class PlayerInventoryObservator extends InventoryObservator {

    public PlayerInventoryObservator(PlayerInventoryView playerInventoryView, PlayerInventory playerInventory, Pane panneauDeJeu) {
        super(playerInventoryView, playerInventory, panneauDeJeu);
    }

    public void refreshCurrentSlotView(){
        PlayerInventory playerInventory = (PlayerInventory) getInventory();
        PlayerInventoryView playerInventoryView = (PlayerInventoryView) getInventoryView();
        playerInventoryView.getcurrentSlotView().xProperty().setValue(getPanneauDeJeu().getScene().getCamera().layoutXProperty().getValue() + 99 + 32 * playerInventory.getCurrSlotNumber());
        playerInventoryView.getcurrentSlotView().yProperty().setValue(getPanneauDeJeu().getScene().getCamera().layoutYProperty().getValue() + 99);
        playerInventoryView.getcurrentSlotView().toFront();
    }

}

