package vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DeletedSlotView {

    private Rectangle deletedRectangle;

    public DeletedSlotView(Pane panneauDeJeu, PlayerInventoryView playerInventoryView){
        deletedRectangle = new Rectangle();
        deletedRectangle.xProperty().bind(playerInventoryView.getSlotViews().get(playerInventoryView.getSlotViews().size() - 1).getXProperty().add(32));
        deletedRectangle.yProperty().bind(playerInventoryView.getSlotViews().get(playerInventoryView.getSlotViews().size() - 1).getYProperty());
        deletedRectangle.setFill(Color.RED);
        deletedRectangle.setStroke(Color.BLACK);
        deletedRectangle.setWidth(32);
        deletedRectangle.setHeight(32);
        panneauDeJeu.getChildren().add(deletedRectangle);
        deletedRectangle.toFront();
        deletedRectangle.setVisible(false);

        ImageView trashCan = new ImageView(new Image("Sprites/Items/dirtItem.png")); // Image temp
        trashCan.xProperty().bind(deletedRectangle.xProperty().subtract(trashCan.getImage().getWidth()/2 - deletedRectangle.getWidth() / 2));
        trashCan.yProperty().bind(deletedRectangle.yProperty().subtract(trashCan.getImage().getHeight()/2 - deletedRectangle.getHeight() / 2));
        trashCan.visibleProperty().bind(deletedRectangle.visibleProperty());
        panneauDeJeu.getChildren().add(trashCan);
        trashCan.toFront();
    }

    public void display(boolean diplay){
        if(diplay){
            deletedRectangle.setVisible(true);
        }
        else {
            deletedRectangle.setVisible(false);
        }
    }
    public Rectangle getDeletedRectangle() {
        return deletedRectangle;
    }

}
