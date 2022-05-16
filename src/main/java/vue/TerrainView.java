package vue;

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
            imgView.xProperty().bind(entite.getHitbox().getX().subtract(entite.getHitbox().getWidth()/2));
            imgView.yProperty().bind(entite.getHitbox().getY().subtract(entite.getHitbox().getHeight()/2));
            panneau.getChildren().add(imgView);
        }
    }

    public void displayCollision(boolean blocks, boolean entities, boolean playerColl, Terrain terrain, Player player){
        if(blocks) {
            for (Block block : terrain.getBlocks()) {
                Rectangle r = new Rectangle(block.getHitX(), block.getHitY(), block.getTile().getHitbox().getWidth(), block.getTile().getHitbox().getHeight());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BLACK);
                panneau.getChildren().add(r);
            }
        }
        if(entities) {
            for (Entite ent : entites) {
                Rectangle r = new Rectangle(ent.getHitbox().getX().intValue(), ent.getHitbox().getY().intValue(), ent.getHitbox().getWidth(), ent.getHitbox().getHeight());
                r.xProperty().bind(ent.getHitbox().getX());
                r.yProperty().bind(ent.getHitbox().getY());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.RED);
                panneau.getChildren().add(r);
            }
        }
        if(playerColl){
            Rectangle r = new Rectangle(player.getHitbox().getX().intValue(), player.getHitbox().getY().intValue(), player.getWidth(), player.getHeight());
            r.yProperty().bind(player.getHitbox().getY());
            r.xProperty().bind(player.getHitbox().getX());
            r.setFill(Color.TRANSPARENT);
            r.setStroke(Color.GREEN);
            panneau.getChildren().add(r);
        }
    }

}
