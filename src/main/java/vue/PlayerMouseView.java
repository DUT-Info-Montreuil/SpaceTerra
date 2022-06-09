package vue;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class PlayerMouseView {
    private Label itemQuantityLabel;
    private ImageView itemView;

    Pane panneauDeJeu;



    public PlayerMouseView(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
        itemQuantityLabel = new Label();
        itemView = new ImageView();
        itemQuantityLabel.setTextFill(Color.BLACK);
        panneauDeJeu.getChildren().add(itemQuantityLabel);
        panneauDeJeu.getChildren().add(itemView);
    }

    public Label getItemQuantityLabel() {
        return itemQuantityLabel;
    }

    public ImageView getItemView() {
        return itemView;
    }

    public SlotView getOnSlotClicked(int x, int y, InventoryView inventoryView){
        for (int i = 0; i < 10; i++){
            if(inventoryView.getSlotViews().get(i).getEmptySlotRectangle().contains(x, y)){
                return inventoryView.getSlotViews().get(i);
            }
        }
        return null;
    }

    public void displayItemQuantityLabel(boolean display){
        if(display){
            itemQuantityLabel.setVisible(true);
        }
        else {
            itemQuantityLabel.setVisible(false);
        }
    }

}
