package vue;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import modele.Inventory;
import modele.Player;

public class InventoryView {
    private Inventory inventory;
    private Pane panneauDeJeu;

    private Rectangle emptySlotRectangle;

    public InventoryView(Inventory inventory, Pane panneauDeJeu) {
        this.inventory = inventory;
        this.panneauDeJeu = panneauDeJeu;
        initialize();
    }

    public void initialize(){
        for(int i = 0; i < inventory.getNbSlot(); i++){
            if(inventory.getItemFromSlot(i) == null){
                emptySlotRectangle = new Rectangle();
                emptySlotRectangle.setHeight(32);
                emptySlotRectangle.setWidth(32);
                emptySlotRectangle.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32*i));
                emptySlotRectangle.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
                panneauDeJeu.getChildren().add(emptySlotRectangle);
            }

        }
    }

}
