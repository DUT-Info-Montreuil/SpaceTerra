package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Map {

    private ArrayList<Layer> layers;
    private JSONObject map;

    public Map(){

    }

    public void loadMap(){
    //JSON parser object to parse read file
    JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/Map/map.json")) {
        //Read JSON file
        Object obj = jsonParser.parse(reader);

        JSONObject map = (JSONObject) obj;
        System.out.println(map);

        //Iterate over employee array
        parseMap(map);

    } catch (
    FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
}



    private static void parseMap(JSONObject map) {
        //Get employee object within list
        JSONArray layersData = (JSONArray) map.get("layers");
        JSONObject layer = (JSONObject)layersData.get(0); //gives data of layer 0
        JSONArray layerData = (JSONArray) layer.get("data");
        System.out.println(layerData.get(1));

        ArrayList<Integer> data = new ArrayList<>();

        for (int j = 0; j < data.size(); j++) {
            data.add((Integer) layerData.get(j));
        }
    }
}
