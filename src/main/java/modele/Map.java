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

    private JSONObject map;
    private ArrayList<Layer> layers;
    private Tileset tileSet;
    private int height, width;

    public Map(String mapPath){
        loadMap(mapPath);
        parseLayers(this.map);
        height = ((Long) map.get("height")).intValue();
        width = ((Long) map.get("width")).intValue();
        tileSet = new Tileset((JSONObject) ((JSONArray) map.get("tilesets")).get(0));
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
            System.out.println("Map loaded");

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
        JSONArray layersData = (JSONArray) map.get("layers");

        Iterator<JSONObject> layersIterator =  layersData.iterator();

        while(layersIterator.hasNext()){
            JSONObject currentLayer = layersIterator.next();
            layers.add(new Layer(currentLayer));
        }
        System.out.println("All layers loaded");
    }

    public ArrayList<Layer> getLayers(){
        return layers;
    }

    public Tileset getTileset(){
        return tileSet;
    }
}
