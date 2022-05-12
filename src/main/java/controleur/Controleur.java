package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import modele.Map;

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
            int[] data = map.getLayers().get(i).getData();
            try{
                for(int j = 0; j < data.length; j++){
                    blocks.add(data[j]);

                }


            } catch (NullPointerException e){
                System.out.println("null");
            }
        }
        return blocks;
    }

    public void creerSprite(){

    }




}
