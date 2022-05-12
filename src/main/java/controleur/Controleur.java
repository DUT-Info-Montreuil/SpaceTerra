package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Layer;
import modele.Map;
import modele.Tile;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    @FXML
    private Pane panneauDeJeu;
    private Map map;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map = new Map("src/main/resources/Map/Test.json");
        readMap(map);
    }
    public void readMap(Map map) {
        for(int l = 0; l < map.getLayers().size(); l++){
            if(map.getLayers().get(l).getIsVisible()){
                Layer currentLayer = map.getLayers().get(l);
                int x = currentLayer.getxPos();
                int y = currentLayer.getyPos();
                int[] data = currentLayer.getData();
                int currentWidth = 0;
                try{
                    for(int t = 0; t < data.length; t++){
                        if(!(currentWidth < currentLayer.getWidth())){
                            x = currentLayer.getxPos();
                            y += 32;
                            currentWidth = 0;
                        }
                        for(Tile currTile : map.getTileset().getTiles())
                            if(data[t] == currTile.getId()){
                                ImageView sprite = new ImageView(currTile.getImage());
                                sprite.setX(x);
                                sprite.setY(y);
                                panneauDeJeu.getChildren().add(sprite);
                            }
                        x += 32;
                        currentWidth++;
                    }
                } catch (NullPointerException e){
                    System.out.println("null");
                }
            }
        }
    }
}
