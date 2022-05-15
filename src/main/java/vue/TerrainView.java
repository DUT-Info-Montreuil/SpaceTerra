package vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.*;

import java.util.Iterator;

public class TerrainView {
    private Pane panneau;

    public TerrainView(Pane panneau) {
        this.panneau = panneau;
    }

    public void readMap(Terrain terrain) {
        for (Block block : terrain.getBlocks()) {
            ImageView imgView = new ImageView(block.getTile().getImage());
            imgView.setX(block.getX());
            imgView.setY(block.getY());
            panneau.getChildren().add(imgView);
        }
    }

    public void displayCollision(boolean display, Terrain terrain, Player player){
        if(display){
            for (Block block : terrain.getBlocks()) {
                Rectangle r = new Rectangle(block.getHitX(), block.getHitY(), block.getTile().getHitbox().getWidth(), block.getTile().getHitbox().getHeight());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BLACK);
                panneau.getChildren().add(r);
            }
            Rectangle r = new Rectangle(player.getXProperty().intValue(), player.getYProperty().intValue(), player.getWidth(), player.getHeight());
            r.yProperty().bind(player.getYProperty());
            r.xProperty().bind(player.getXProperty());
            r.setFill(Color.TRANSPARENT);
            r.setStroke(Color.BLACK);
            panneau.getChildren().add(r);

        }
    }

}
