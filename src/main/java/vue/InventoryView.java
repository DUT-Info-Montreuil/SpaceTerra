package vue;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Inventory;
import modele.Slot;

import java.util.ArrayList;

public class InventoryView {
    public static Pane panneauDeJeu;

    public ArrayList<SlotView> getSlotViews() {
        return slotViews;
    }

    public void setSlotViews(ArrayList<SlotView> slotViews) {
        this.slotViews = slotViews;
    }

    private ArrayList<SlotView> slotViews;

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    private boolean show;

    public InventoryView(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
        this.slotViews = new ArrayList<>();
    }

    public void setSlotViewPosition(int numSlot){
        slotViews.get(numSlot).getXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * getWidthMult(numSlot)));
        slotViews.get(numSlot).getYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100 + getHeightMult(numSlot)));
    }

    public void displayAllSlotViews(){
        if(show){
            for(SlotView slotView : this.slotViews){
                slotView.displaySlot();
                System.out.println("x : " + slotView.getX());
                System.out.println("y : " + slotView.getY());
            }
        }
        else {
            for(SlotView slotView : this.slotViews){
                if(slotView.getId() < 10){
                    slotView.displaySlot();
                    //System.out.println("x : " + slotView.getX());
                    //
                    //System.out.println("y : " + slotView.getY());
                }else {
                    slotView.hideSlot();
                }

            }
        }

    }
/*
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
      //  System.out.println("id : " + slot.getId());
        ImageView fullSlotImageView = new ImageView();//(inventory.getItemFromSlot(slot.getId())).getTypeItem().getImage());
        //fullSlotImageView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32 * getWidthMult(slot)));
        //fullSlotImageView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100 + getHeightMult(slot)));
        fullSlotImageView.setId("fullSlot" + slot.getId());
        fullSlotImageView.setFitWidth(32);
        fullSlotImageView.setFitHeight(32);
        //System.out.println("r : " + fullSlotImageView.getId());
        panneauDeJeu.getChildren().add(fullSlotImageView);
        if(slot.getId() >= 10){
            fullSlotImageView.setVisible(false);
        }
    }

    public void displayLabelQuantity(Slot slot){
        Label quantityLabel = new Label();
        //quantityLabel.layoutXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(120 + 32 * getWidthMult(slot)));
        //quantityLabel.layoutYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(118 + getHeightMult(slot)));
        //quantityLabel.textProperty().bind(inventory.getSlots().get(slot.getId()).itemQuantityProperty().asString());
        quantityLabel.setTextFill(Color.WHITE);
        quantityLabel.setId("label" + slot.getId());
        quantityLabel.toFront();
        panneauDeJeu.getChildren().add(quantityLabel);
        if(slot.getId() >= 10){
            quantityLabel.setVisible(false);
        }
    }

 */

    private int getHeightMult(int numSlot) {

        int heightMult = numSlot/10;
        heightMult *= 32;

        return heightMult;
    }

    private int getWidthMult(int numSlot) {
        return numSlot % 10;
    }

/*
    public void hideEmptySlotRectangle(Slot slot) {
        try{
            panneauDeJeu.lookup("#emptySlot" + slot.getId()).setVisible(false);
        }catch (NullPointerException e){
           // System.out.println("slot null hide");
        }
    }
    public void hideLabelQuantity(Slot slot) {
        panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#label" + slot.getId()));
    }

    public void hideFullSlotImageView(Slot slot) {
        //System.out.println("r : " + slot.getId());
        panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#fullSlot" + slot.getId()));
    }


    public void showAllInventory(boolean showAll) {
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




