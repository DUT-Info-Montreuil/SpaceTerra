package vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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

}
