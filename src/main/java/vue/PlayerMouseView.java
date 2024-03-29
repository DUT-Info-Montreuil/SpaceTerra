package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;


public class PlayerMouseView {
    private Label itemQuantityLabel;

    private Label itemNameLabel;
    private ImageView itemView;

    Pane panneauDeJeu;


    public PlayerMouseView(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
        itemQuantityLabel = new Label();
        itemNameLabel = new Label();
        itemView = new ImageView();
        itemQuantityLabel.setTextFill(Color.BLACK);
        itemNameLabel.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5.0), new Insets(-5.0))));
        itemNameLabel.setTextFill(Color.WHITE);
        panneauDeJeu.getChildren().add(itemQuantityLabel);
        panneauDeJeu.getChildren().add(itemView);
        panneauDeJeu.getChildren().add(itemNameLabel);
    }

    public Label getItemQuantityLabel() {
        return itemQuantityLabel;
    }

    public Label getItemNameLabel() {
        return itemNameLabel;
    }

    public ImageView getItemView() {
        return itemView;
    }

    public SlotView getOnInventorySlotClicked(int x, int y, InventoryView inventoryView) {
        if (!inventoryView.isDisplay()) {
            if (inventoryView instanceof PlayerInventoryView) {
                for (int i = 0; i < 10; i++) {
                    if (inventoryView.getSlotViews().get(i).getEmptySlotRectangle().contains(x, y)) {
                        return inventoryView.getSlotViews().get(i);
                    }
                }
            }
        } else {
            for (int i = 0; i < inventoryView.getSlotViews().size(); i++) {
                if (inventoryView.getSlotViews().get(i).getEmptySlotRectangle().contains(x, y)) {
                    return inventoryView.getSlotViews().get(i);
                }
            }
        }

        return null;
    }

    public boolean getOnDeletedSlotClicked(int x, int y, DeletedSlotView deletedSlotView) {
        return deletedSlotView.getDeletedRectangle().contains(x, y);

    }

    public void displayItemQuantityLabel(boolean display) {
        if (display) {
            itemQuantityLabel.setVisible(true);
        } else {
            itemQuantityLabel.setVisible(false);
        }
    }

    public void displayItemNameLabel(boolean display) {
        if (display) {
            itemNameLabel.setVisible(true);
        } else {
            itemNameLabel.setVisible(false);
        }
    }

    public boolean getOnSlotClicked(int x, int y, SlotView slotView) {
        return slotView.getEmptySlotRectangle().contains(x, y);

    }
}
