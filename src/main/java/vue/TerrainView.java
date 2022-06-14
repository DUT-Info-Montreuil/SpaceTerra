package vue;

import controleur.Controleur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.*;

import java.util.ArrayList;

public class TerrainView {
    private Pane panneau;
    public TerrainView(Pane panneau) {
        this.panneau = panneau;
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

    public void deleteBlock(Block block) {
       // System.out.println("oui5");
        try{
            panneau.getChildren().remove(panneau.lookup("#" + block.getId()));
        }catch (Exception e){}
    }

    public void addBlock(Block block){
        try{
            ImageView imgView = new ImageView(block.getTile().getImage());
            imgView.setId(block.getId());
            imgView.setX(block.getX());
            imgView.setY(block.getY());
            //System.out.println(imgView.getId());
            panneau.getChildren().add(imgView);
            imgView.toBack();
        }catch(NullPointerException e){

        }

    }

}
