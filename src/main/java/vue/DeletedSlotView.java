package vue;

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
