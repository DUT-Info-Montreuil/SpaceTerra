package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Map;

import javax.swing.text.html.ImageView;
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
        blockMap(map);
    }
    public ArrayList<Integer> blockMap(Map map) {
        ArrayList<Integer> blocks = new ArrayList<>();

        for(int i = 0; i < map.getLayers().size(); i++){
            if(map.getLayers().get(i).getIsVisible()){
                int x = map.getLayers().get(i).getxPos();
                int y = map.getLayers().get(i).getyPos();;
                int[] data = map.getLayers().get(i).getData();
                try{
                    int currentWidth = 0;
                    for(int j = 0; j < data.length; j++){
                        if(currentWidth < map.getLayers().get(i).getWidth()){
                            Rectangle r = new Rectangle(x, y, 32, 32);
                            x += 32;
                            currentWidth++;
                            switch(data[j]){
                                case 0:
                                    r.setFill(Color.TRANSPARENT);
                                    r.setStroke(Color.RED);
                                    break;
                                case 1:
                                    r.setFill(Color.RED);
                                    r.setStroke(Color.RED);
                                    break;
                                case 2:
                                    r.setFill(Color.YELLOW);
                                    r.setStroke(Color.RED);
                                    break;
                                case 3:
                                    r.setFill(Color.PINK);
                                    r.setStroke(Color.RED);
                                    break;
                                case 4:
                                    r.setFill(Color.GREEN);
                                    r.setStroke(Color.RED);
                                    break;
                                default:
                                    r.setFill(Color.BLACK);
                            }
                            panneauDeJeu.getChildren().add(r);
                        } else {
                            x = map.getLayers().get(i).getxPos();
                            y += 32;
                            currentWidth = 0;
                        }
                    }


                } catch (NullPointerException e){
                    System.out.println("null");
                }
            }

        }
        return blocks;
    }





}
