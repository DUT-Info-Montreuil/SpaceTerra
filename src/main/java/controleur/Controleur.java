package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Layer;
import modele.Map;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    @FXML
    private Pane panneauDeJeu;
    private Map map;

    boolean rightPressed, leftPressed, upPressed, downPressed;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map = new Map("src/main/resources/Map/Test.json");
        blockMap(map);

    }
    public ArrayList<Integer> blockMap(Map map) {
        ArrayList<Integer> blocks = new ArrayList<>();

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
                        Rectangle r = new Rectangle(x, y, 32, 32);
                        ImageView img = null;
                            switch(data[t]){
                                case 0:
                                    r.setFill(Color.TRANSPARENT);
                                   // r.setStroke(Color.RED);
                                    break;
                                case 1:
                                    r.setFill(Color.RED);
                                  //  r.setStroke(Color.RED);
                                    break;
                                case 2:
                                    r.setFill(Color.YELLOW);
                                  //  r.setStroke(Color.RED);
                                    break;
                                case 3:
                                    r.setFill(Color.PINK);
                                  //  r.setStroke(Color.RED);
                                    break;
                                case 4:
                                    r.setFill(Color.GREEN);
                                 //   r.setStroke(Color.RED);
                                    break;
                                default:
                                  //  r.setFill(Color.BLACK);
                            }

                        x += 32;
                        currentWidth++;

                            panneauDeJeu.getChildren().add(r);
                    }
                } catch (NullPointerException e){
                    System.out.println("null");
                }
            }

        }
        return blocks;
    }



}
