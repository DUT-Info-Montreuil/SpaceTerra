package controleur;

import javafx.scene.layout.Pane;
import modele.Inventory;
import modele.PlayerInventory;
import vue.CraftInventoryView;

public class CraftInventoryObservator extends InventoryObservator {

    public CraftInventoryObservator(CraftInventoryView craftInventoryView, Inventory inventory, Pane panneauDeJeu) {
        super(craftInventoryView, inventory, panneauDeJeu);
    }

}

