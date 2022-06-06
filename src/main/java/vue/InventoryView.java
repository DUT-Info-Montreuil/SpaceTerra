package vue;

import javafx.scene.control.Label;
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

    private Label quantityLabel;

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    private boolean show;

    public InventoryView(Inventory inventory, Pane panneauDeJeu) {
        this.inventory = inventory;
        this.panneauDeJeu = panneauDeJeu;

        initialize();
    }

    public void initialize() {
        for (int i = 0; i < 10; i++) {
            System.out.println(inventory.getSlots());
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
        for(int i  = 0; i < 10; i++){
            if(inventory.getItemFromSlot(i) == null){
                panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#label" + i));
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
                fullSlotImageView = new ImageView(((ItemBlock) inventory.getItemFromSlot(i)).getTypeItemBlock().getImage());
                fullSlotImageView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * i));
                fullSlotImageView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
                fullSlotImageView.setId("#slot" + i);
                panneauDeJeu.getChildren().add(fullSlotImageView);

                quantityLabel = new Label();
                quantityLabel.layoutXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * i + 20));
                quantityLabel.layoutYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100 + 18));
                quantityLabel.textProperty().bind(inventory.getSlots().get(i).itemQuantityProperty().asString());
                quantityLabel.setTextFill(Color.WHITE);
                quantityLabel.setId("#label" + i);
                panneauDeJeu.getChildren().add(quantityLabel);

            }
        }
    }

    public void showAllInventory(boolean show){
        if(show){
            int heightMult = 0;
            int whidthMult = 0;
            for(int i  = 0; i < inventory.getMaxInventorySize(); i++){
                if(i>0 && i%10 == 0){
                    heightMult += 32;
                    whidthMult = 0;
                }
                if(inventory.getItemFromSlot(i) == null){
                    panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#label" + i));
                    panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#slot" + i));
                    emptySlotRectangle = new Rectangle();
                    emptySlotRectangle.setHeight(32);
                    emptySlotRectangle.setWidth(32);
                    emptySlotRectangle.setFill(Color.WHITE);
                    emptySlotRectangle.setStroke(Color.BLACK);
                    emptySlotRectangle.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * whidthMult));
                    emptySlotRectangle.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100 + heightMult));
                    emptySlotRectangle.setId("slot" + i);
                    panneauDeJeu.getChildren().add(emptySlotRectangle);
                }
                else {
                    panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#slot" + i));
                    fullSlotImageView = new ImageView(((ItemBlock) inventory.getItemFromSlot(i)).getTypeItemBlock().getImage());
                    fullSlotImageView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * whidthMult));
                    fullSlotImageView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100 + heightMult));
                    fullSlotImageView.setId("#slot" + i);
                    panneauDeJeu.getChildren().add(fullSlotImageView);
                    quantityLabel = new Label();
                    quantityLabel.layoutXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * whidthMult + 20));
                    quantityLabel.layoutYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add((100 + 18)  + heightMult));
                    quantityLabel.textProperty().bind(inventory.getSlots().get(i).itemQuantityProperty().asString());
                    quantityLabel.setTextFill(Color.WHITE);
                    quantityLabel.setId("#label" + i);
                    panneauDeJeu.getChildren().add(quantityLabel);

                }
                whidthMult++;
            }
        }else {
            for(int i  = 0; i < inventory.getMaxInventorySize(); i++){
                panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#slot" + i));
                panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#label" + i));
            }
            refreshInventory();
        }
    }


}
