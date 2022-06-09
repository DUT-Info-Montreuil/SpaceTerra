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
    public TerrainView(Pane panneau, ArrayList<Entity> ent) {

        this.panneau = panneau;
        this.entities = ent;
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

    public void deleteBlock(Block block) {
       // System.out.println("oui5");
        try{
            panneau.getChildren().remove(panneau.lookup("#" + block.getId()));
        }catch (Exception e){}
    }

    public void addBlock(Block block){
        try{
            ImageView imgView = new ImageView(block.getTile().getImage());
            System.out.println(block.getTile());
            imgView.setId(block.getId());
            imgView.setX(block.getX());
            imgView.setY(block.getY());
            imgView.toBack();
            //System.out.println(imgView.getId());
            panneau.getChildren().add(imgView);
        }catch(NullPointerException e){

        }

    }

}
