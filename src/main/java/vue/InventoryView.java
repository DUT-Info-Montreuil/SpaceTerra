package vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Inventory;
import modele.Item;
import modele.ItemBlock;

public class InventoryView {
    private Inventory inventory;
    private Pane panneauDeJeu;

    private Rectangle emptySlotRectangle;

    private ImageView fullSlotImageView;

    public InventoryView(Inventory inventory, Pane panneauDeJeu) {
        this.inventory = inventory;
        this.panneauDeJeu = panneauDeJeu;

        initialize();
    }

    public void initialize() {
        for (int i = 0; i < inventory.getMaxInventorySize(); i++) {
            System.out.println(inventory.getItems());
            if (inventory.getItemFromSlot(i) == null) {
                emptySlotRectangle = new Rectangle();
                emptySlotRectangle.setHeight(32);
                emptySlotRectangle.setWidth(32);
                emptySlotRectangle.setFill(Color.WHITE);
                emptySlotRectangle.setStroke(Color.BLACK);
                emptySlotRectangle.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * i));
                emptySlotRectangle.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
                emptySlotRectangle.setId("slot" + i);
                panneauDeJeu.getChildren().add(emptySlotRectangle);
            }

        }
    }

    /*public void refreshPlace() {
        if (inventory.getCurrItem() != null) {
            panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#slot" + inventory.getNextEmptySlot()));
            fullSlotImageView = new ImageView(((ItemBlock) inventory.getCurrItem()).getTypeItemBlock().getImage());
            fullSlotImageView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * inventory.getCurrSlot()));
            fullSlotImageView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
            fullSlotImageView.setId("#slot" + inventory.getNextEmptySlot());
            panneauDeJeu.getChildren().add(fullSlotImageView);

        } else {
            emptySlotRectangle = new Rectangle();
            emptySlotRectangle.setHeight(32);
            emptySlotRectangle.setWidth(32);
            emptySlotRectangle.setFill(Color.WHITE);
            emptySlotRectangle.setStroke(Color.BLACK);
            emptySlotRectangle.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * inventory.getCurrSlot()));
            emptySlotRectangle.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
            panneauDeJeu.getChildren().add(emptySlotRectangle);
        }
    }

    public void refreshBreak(Item item) {
            panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#slot" + inventory.getNextEmptySlot()));
            fullSlotImageView = new ImageView(((ItemBlock) item).getTypeItemBlock().getImage());
            fullSlotImageView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * inventory.getNextEmptySlot()));
            fullSlotImageView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
            panneauDeJeu.getChildren().add(fullSlotImageView);
    }
*/
    public void refreshInventory(){
        for(int i  = 0; i < inventory.getItems().size(); i++){
            if(inventory.getItems().get(i) == null){
                panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#slot" + i));
                emptySlotRectangle = new Rectangle();
                emptySlotRectangle.setHeight(32);
                emptySlotRectangle.setWidth(32);
                emptySlotRectangle.setFill(Color.WHITE);
                emptySlotRectangle.setStroke(Color.BLACK);
                emptySlotRectangle.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * i));
                emptySlotRectangle.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
                emptySlotRectangle.setId("slot" + i);
                panneauDeJeu.getChildren().add(emptySlotRectangle);
            }
            else {
                panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#slot" + i));
                fullSlotImageView = new ImageView(((ItemBlock) inventory.getItems().get(i)).getTypeItemBlock().getImage());
                fullSlotImageView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * i));
                fullSlotImageView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
                fullSlotImageView.setId("#slot" + i);
                panneauDeJeu.getChildren().add(fullSlotImageView);
            }
        }
    }


}
