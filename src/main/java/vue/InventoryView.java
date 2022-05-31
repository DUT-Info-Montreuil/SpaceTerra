package vue;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Inventory;
import modele.Item;

public class InventoryView {
    private Inventory inventory;
    private Pane panneauDeJeu;

    private Rectangle emptySlotRectangle;

    private ImageView fullSlotImageView;

    private Label nbItems;

    private StringProperty labelText;

    public InventoryView(Inventory inventory, Pane panneauDeJeu) {
        this.inventory = inventory;
        this.panneauDeJeu = panneauDeJeu;

        initialize();
    }

    public void initialize(){
        for(int i = 0; i < inventory.getMaxInventorySize(); i++){
            System.out.println(inventory.getItems());
            if(inventory.getItemFromSlot(i) == null){
                emptySlotRectangle = new Rectangle();
                emptySlotRectangle.setHeight(32);
                emptySlotRectangle.setWidth(32);
                emptySlotRectangle.setFill(Color.WHITE);
                emptySlotRectangle.setStroke(Color.BLACK);
                emptySlotRectangle.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32*i));
                emptySlotRectangle.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
                emptySlotRectangle.setId("slot"+i);
                panneauDeJeu.getChildren().add(emptySlotRectangle);
            }

        }
    }

    public void refreshPlace(){
        if(inventory.getCurrItem() != null) {
            if(!inventory.getItemsQuantities().containsKey(inventory.getCurrItem().getClass().toString())){
                panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#slot"+inventory.getNextEmptySlot()));
                fullSlotImageView = new ImageView(inventory.getCurrItem().getTile().getImage());
                fullSlotImageView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32*inventory.getCurrSlot()));
                fullSlotImageView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
                fullSlotImageView.setId("#slot"+inventory.getNextEmptySlot());
                panneauDeJeu.getChildren().add(fullSlotImageView);
            }


        }
        else {
            emptySlotRectangle = new Rectangle();
            emptySlotRectangle.setHeight(32);
            emptySlotRectangle.setWidth(32);
            emptySlotRectangle.setFill(Color.WHITE);
            emptySlotRectangle.setStroke(Color.BLACK);
            emptySlotRectangle.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32* inventory.getCurrSlot()));
            emptySlotRectangle.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
            panneauDeJeu.getChildren().add(emptySlotRectangle);
        }
    }

    public void refreshBreak(Item item){
        if(!inventory.getItemsQuantities().containsKey(item.getClass().toString())){
            panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#slot"+inventory.getNextEmptySlot()));
            fullSlotImageView = new ImageView(item.getTile().getImage());
            fullSlotImageView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32*inventory.getNextEmptySlot()));
            fullSlotImageView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
            panneauDeJeu.getChildren().add(fullSlotImageView);
        }
        else {
            nbItems = new Label();
            try{
                labelText = new SimpleStringProperty("x"+inventory.getItemsQuantities().get(item.getClass().toString()).toString());
                nbItems.textProperty().bind(labelText);
                nbItems.layoutXProperty().bind(fullSlotImageView.xProperty().add(fullSlotImageView.getFitWidth()));
                nbItems.layoutYProperty().bind(fullSlotImageView.yProperty().add(fullSlotImageView.getFitHeight()*3/4));
                nbItems.setTextFill(Color.WHITE);
                panneauDeJeu.getChildren().add(nbItems);
            } catch(NullPointerException e){
                System.out.println("NUL");
            }

        }

    }

}
