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

    public void readMap(Terrain terrain) {
        int id = 0;
        for (Block block : terrain.getBlocks()) {
            try{
                ImageView imgView = new ImageView(block.getTile().getImage());
                imgView.setId(block.getId());
                imgView.setX(block.getX());
                imgView.setY(block.getY());
                //System.out.println(imgView.getId());
                panneau.getChildren().add(imgView);
            } catch (NullPointerException e){

            }

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


    public void deleteBlock(Block block) {
       // System.out.println("oui5");
        panneau.getChildren().remove(panneau.lookup("#" + block.getId()));
    }

    public void addBlock(Terrain terrain, Block block){
        ImageView imgView = new ImageView(block.getTile().getImage());
        imgView.setId(block.getId());
        imgView.setX(block.getX());
        imgView.setY(block.getY());
        //System.out.println(imgView.getId());
        panneau.getChildren().add(imgView);
    }

}
