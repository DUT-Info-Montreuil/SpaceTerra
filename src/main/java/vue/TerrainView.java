package vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.*;

import java.util.ArrayList;
import java.util.Iterator;

public class TerrainView {
    private Pane panneau;

    private ArrayList<Entite> entites;
    public TerrainView(Pane panneau) {

        this.panneau = panneau;
        this.entites = new ArrayList();
    }

    public void addEntite(Entite entite){
        this.entites.add(entite);
    }

    public void readMap(Terrain terrain) {
        for (Block block : terrain.getBlocks()) {
            ImageView imgView = new ImageView(block.getTile().getImage());
            imgView.setX(block.getX());
            imgView.setY(block.getY());
            panneau.getChildren().add(imgView);
        }
    }

    public void readEntite(){
        for (Entite entite : entites){
            ImageView imgView = new ImageView(entite.getImage());
            imgView.xProperty().bind(entite.getHitbox().getX());
            imgView.yProperty().bind(entite.getHitbox().getY());
            //imgView.setX(entite.getHitbox().getX());
            //imgView.setY(entite.getHitbox().getY());
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
