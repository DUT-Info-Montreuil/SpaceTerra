package vue;

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
            panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#slot"+inventory.getNextEmptySlot()));
            fullSlotImageView = new ImageView(inventory.getCurrItem().getImage());
            fullSlotImageView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32*inventory.getCurrSlot()));
            fullSlotImageView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
            fullSlotImageView.setId("#slot"+inventory.getNextEmptySlot());
            panneauDeJeu.getChildren().add(fullSlotImageView);

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
            panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#slot"+inventory.getNextEmptySlot()));
            fullSlotImageView = new ImageView(item.getImage());
            fullSlotImageView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(100 + 32*inventory.getNextEmptySlot()));
            fullSlotImageView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(100));
            panneauDeJeu.getChildren().add(fullSlotImageView);

    }

}
