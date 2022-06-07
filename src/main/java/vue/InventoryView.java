package vue;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Inventory;
import modele.ItemBlock;
import modele.Slot;

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
        for (int i = 0; i < inventory.getMaxInventorySize(); i++) {
            System.out.println(inventory.getSlots());
            if (inventory.getItemFromSlot(i) == null) {
                emptySlotRectangle = new Rectangle();
                emptySlotRectangle.setHeight(32);
                emptySlotRectangle.setWidth(32);
                emptySlotRectangle.setFill(Color.WHITE);
                emptySlotRectangle.setStroke(Color.BLACK);
                emptySlotRectangle.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * getWidthMult(inventory.getSlots().get(i))));
                emptySlotRectangle.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100 + getHeightMult(inventory.getSlots().get(i))));
                emptySlotRectangle.setId("emptySlot" + inventory.getSlots().get(i).getId());
                panneauDeJeu.getChildren().add(emptySlotRectangle);
            }
            if(i >= 10){
                emptySlotRectangle.setVisible(false);
            }

        }
    }

    public void displayEmptySlotRectangle(Slot slot) {
        try{
            if(slot.getId() < 10){
                panneauDeJeu.lookup("#emptySlot" + slot.getId()).setVisible(true);
            }
        }catch (NullPointerException e){
            System.out.println("slot null display");
        }
    }

    public void displayFullSlotImageView(Slot slot){
        fullSlotImageView = new ImageView();
        System.out.println("id : " + slot.getId());
        fullSlotImageView = new ImageView(((ItemBlock) inventory.getItemFromSlot(slot.getId())).getTypeItemBlock().getImage());
        fullSlotImageView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * getWidthMult(slot)));
        fullSlotImageView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100 + getHeightMult(slot)));
        fullSlotImageView.setId("fullSlot" + slot.getId());
        fullSlotImageView.setFitWidth(32);
        fullSlotImageView.setFitHeight(32);
        System.out.println("r : " + fullSlotImageView.getId());
        panneauDeJeu.getChildren().add(fullSlotImageView);
        if(slot.getId() >= 10){
            fullSlotImageView.setVisible(false);
        }
    }

    public void displayLabelQuantity(Slot slot){
        quantityLabel = new Label();
        quantityLabel.layoutXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * getWidthMult(slot) + 20));
        quantityLabel.layoutYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add((100 + 18) + getHeightMult(slot)));
        quantityLabel.textProperty().bind(inventory.getSlots().get(slot.getId()).itemQuantityProperty().asString());
        quantityLabel.setTextFill(Color.WHITE);
        quantityLabel.setId("label" + slot.getId());
        panneauDeJeu.getChildren().add(quantityLabel);
        if(slot.getId() >= 10){
            quantityLabel.setVisible(false);
        }
    }

    private int getHeightMult(Slot slot) {

        int heightMult = slot.getId()/10;
        heightMult *= 32;

        return heightMult;
    }

    private int getWidthMult(Slot slot) {
        return slot.getId() % 10;
    }


    public void hideEmptySlotRectangle(Slot slot) {
        try{
            panneauDeJeu.lookup("#emptySlot" + slot.getId()).setVisible(false);
        }catch (NullPointerException e){
            System.out.println("slot null hide");
        }
    }
    public void hideLabelQuantity(Slot slot) {
        panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#label" + slot.getId()));
    }

    public void hideFullSlotImageView(Slot slot) {
        System.out.println("r : " + slot.getId());
        panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#fullSlot" + slot.getId()));
    }


    /*public void showAllInventory(boolean showAll) {
        for(int i = 10; i < inventory.getMaxInventorySize(); i++){
            if(showAll){
                try{
                    panneauDeJeu.lookup("#emptySlot" + i).setVisible(true);
                    panneauDeJeu.lookup("#fullSlot" + i).setVisible(true);
                    panneauDeJeu.lookup("#label" + i).setVisible(true);
                } catch (NullPointerException e){

                }

            }
            else {
                try{
                    panneauDeJeu.lookup("#emptySlot" + i).setVisible(false);
                    panneauDeJeu.lookup("#fullSlot" + i).setVisible(false);
                    panneauDeJeu.lookup("#label" + i).setVisible(false);
                }
                catch (NullPointerException e){

                }
            }
        }
    }

     */
}




