package vue;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.*;

import java.util.ArrayList;

public class TerrainView {
    private Pane panneau;

    private ArrayList<Entity> entities;
    public TerrainView(Pane panneau) {

        this.panneau = panneau;
        this.entities = new ArrayList();
    }

    public void addEntite(Entity entity){
        this.entities.add(entity);
    }

    public void readMap(TerrainData terrainData) {
        int id = 0;
        for (Block block : terrainData.getBlocks()) {
            ImageView imgView = new ImageView(block.getTile().getImage());
            imgView.setId(block.getId());
            imgView.setX(block.getX());
            imgView.setY(block.getY());
            System.out.println(imgView.getId());
            panneau.getChildren().add(imgView);
        }
    }



    public void readEntity(){
        for (Entity entity : entities){
            ImageView imgView = new ImageView(entity.getImage());
            imgView.xProperty().bind(entity.getHitbox().getX().subtract(entity.getImage().getWidth()/2 - entity.getHitbox().getWidth()/2));
            imgView.yProperty().bind(entity.getHitbox().getY().subtract(entity.getImage().getHeight() - entity.getHitbox().getHeight()));
            panneau.getChildren().add(imgView);
        }
    }

    public void displayCollision(boolean blocks, boolean entities, boolean playerColl, TerrainData terrainData, Player player){
        if(blocks) {
            for (Block block : terrainData.getBlocks()) {
                Rectangle r = new Rectangle(block.getHitX(), block.getHitY(), block.getTile().getHitbox().getWidth(), block.getTile().getHitbox().getHeight());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BLACK);
                panneau.getChildren().add(r);
            }
        }
        if(entities) {
            for (Entity ent : this.entities) {
                Rectangle r = new Rectangle(ent.getHitbox().getX().intValue(), ent.getHitbox().getY().intValue(), ent.getHitbox().getWidth(), ent.getHitbox().getHeight());
                r.xProperty().bind(ent.getHitbox().getX());
                r.yProperty().bind(ent.getHitbox().getY());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.RED);
                panneau.getChildren().add(r);
            }
        }
        if(playerColl){
            Rectangle r = new Rectangle(player.getHitbox().getX().intValue(), player.getHitbox().getY().intValue(), player.getHitbox().getWidth(), player.getHitbox().getHeight());
            r.yProperty().bind(player.getHitbox().getY());
            r.xProperty().bind(player.getHitbox().getX());
            r.setFill(Color.TRANSPARENT);
            r.setStroke(Color.GREEN);
            panneau.getChildren().add(r);
        }
    }

    public void debugChunks(Terrain terrain){
        for(Chunk chunk : terrain.getChunks()){
            Rectangle r = new Rectangle(chunk.getHitbox().getX().intValue(), chunk.getHitbox().getY().intValue(), chunk.getHitbox().getWidth(), chunk.getHitbox().getHeight());
            r.setFill(Color.TRANSPARENT);
            r.setStroke(Color.BLACK);
            panneau.getChildren().add(r);
        }
    }

    public void debugBlocksChunk(Terrain terrain){
        for(Chunk chunk : terrain.getChunks()) {
            for (Block b : chunk.getBlocks()){
                Rectangle r = new Rectangle(b.getX(), b.getY(), b.getTile().getTileWidth(), b.getTile().getTileHeight());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BLUEVIOLET);
                panneau.getChildren().add(r);
            }
        }
    }

    public void deleteBlock(Block block) {
       // System.out.println("oui5");
        panneau.getChildren().remove(panneau.lookup("#" + block.getId()));
    }

}
