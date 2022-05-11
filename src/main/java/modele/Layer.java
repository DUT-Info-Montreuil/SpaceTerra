package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Layer {

    private int[] data; // la taille ne change pas donc pas d'arrayList
    private int width, height; // je suis pas sur de a quoi ça sert vu que c'est a 0 0 mais peut etre que certains layers couvrent pas toute la map
    private int xPos, yPos;
    private boolean isVisibile;

    public Layer(JSONObject layer){
        if(((String)layer.get("type")).equals("tilelayer")){
            width = ((Long) layer.get("width")).intValue();
            height = ((Long) layer.get("height")).intValue();
            data = new int[width*height];
            fillData((JSONArray) layer.get("data"));
            isVisibile = (boolean) layer.get("visible");
            System.out.println("Layer loaded");
        }
    }

    public void fillData(JSONArray layerData){
        for (int j = 0; j < data.length; j++) {
            data[j] = ((Long) layerData.get(j)).intValue();
        }
        System.out.println("data filled");
    }
}
