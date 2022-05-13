package vue;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Layer;
import modele.Terrain;
import modele.Tile;

import java.util.ArrayList;
import java.util.Iterator;

public class TerrainView {
    private Pane panneau;

    public TerrainView(Pane panneau) {
        this.panneau = panneau;
    }

    public void readMap(Terrain map) {
        for (int l = 0; l < map.getLayers().size(); ++l) {
            if (((Layer) map.getLayers().get(l)).getIsVisible()) {
                Layer currentLayer = (Layer) map.getLayers().get(l);
                int x = currentLayer.getxPos();
                int y = currentLayer.getyPos();
                int[] data = currentLayer.getData();
                int currentWidth = 0;

                try {
                    for (int t = 0; t < data.length; ++t) {
                        if (currentWidth >= currentLayer.getWidth()) {
                            x = currentLayer.getxPos();
                            y += 32;
                            currentWidth = 0;
                        }

                        Iterator var9 = map.getTileset().getTiles().iterator();

                        while (var9.hasNext()) {
                            Tile currTile = (Tile) var9.next();
                            if (data[t] == currTile.getId()) {
                                ImageView sprite = new ImageView(currTile.getImage());
                                sprite.setX((double) x);
                                sprite.setY((double) y);
                                this.panneau.getChildren().add(sprite);
                            }
                        }

                        x += 32;
                        ++currentWidth;
                    }
                } catch (NullPointerException var12) {
                    System.out.println("null");
                }
            }
        }
    }
}
