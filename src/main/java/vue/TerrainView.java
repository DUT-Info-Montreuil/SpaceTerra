package vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import modele.*;

import java.util.HashMap;

public class TerrainView {
    private Pane panneau;
    private HashMap<Integer, Image> tileImages;

    public TerrainView(Pane panneau, HashMap<Integer, String> imagesPath) {
        this.panneau = panneau;
        initImages(imagesPath);
    }

    private void initImages(HashMap<Integer, String> imagesPath) {
        tileImages = new HashMap<>();
        imagesPath.forEach((k, v) -> {
            tileImages.put(k, new Image(v));
            System.out.println("loaded block image " + k + " : " + tileImages.get(k).getUrl());
        });
    }

    public void readMap(Terrain terrain) {
        int id = 0;
        for (Block block : terrain.getBlocks()) {
            try{
                ImageView imgView = new ImageView(tileImages.get(block.getDataId()));
                System.out.println("Image : " + tileImages.get(block.getDataId()).toString());
                imgView.setId(block.getId());
                imgView.setX(block.getGridX());
                imgView.setY(block.getGridY());
                //System.out.println(imgView.getId());
                panneau.getChildren().add(imgView);
                System.out.println("Added block image to view");
            } catch (NullPointerException e){
                //System.out.println("Image is null");
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
            ImageView imgView = new ImageView(tileImages.get(block.getDataId()));
            imgView.setId(block.getId());
            imgView.setX(block.getGridX());
            imgView.setY(block.getGridY());
            //System.out.println(imgView.getId());
            panneau.getChildren().add(imgView);
            imgView.toBack();
        }catch(NullPointerException e){

        }

    }

}
