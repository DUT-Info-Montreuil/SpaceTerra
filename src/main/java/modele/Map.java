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

    public Map(String mapPath){
        loadMap(mapPath);
        parseLayers(this.map);
    }


    public void loadMap(String mapPath){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        //  src/main/resources/Map/map.json
        try (FileReader reader = new FileReader(mapPath)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            map = (JSONObject) obj;
            System.out.println(map);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseLayers(JSONObject map) {
        layers = new ArrayList<>();
        //Get employee object within list
        JSONArray layersData = (JSONArray) map.get("layers");

        Iterator<JSONObject> layersIterator =  layersData.iterator();

        ArrayList<Integer> data = new ArrayList<>();
        while(layersIterator.hasNext()){
            JSONObject currentLayer = layersIterator.next();
            layers.add(new Layer(currentLayer));
        }
    }
}
