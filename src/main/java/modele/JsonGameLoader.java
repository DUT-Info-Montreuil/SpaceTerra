package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class JsonGameLoader {

    private JSONObject map;
    private HashMap<Integer, String> tileImages;
    private ArrayList<Layer> layers;
    private ArrayList<Tile> tileSet;

    public JsonGameLoader(String mapPath){
        tileImages = new HashMap<>();
        layers = new ArrayList<>();
    }

    public void loadMap(String mapPath){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        //  src/main/resources/Map/map.json
        try (FileReader reader = new FileReader(mapPath)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            map = (JSONObject) obj;
            System.out.println("Map loaded");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseLayers() {
        layers = new ArrayList<>();
        JSONArray layersData = (JSONArray) map.get("layers");

        Iterator<JSONObject> layersIterator = layersData.iterator();

        while (layersIterator.hasNext()) {
            JSONObject currentLayer = layersIterator.next();
            layers.add(new Layer(currentLayer));
        }
        System.out.println("All layers loaded");
    }

    public void parseTiles(JSONObject tileSet){
        tileSet = new ArrayList<>();
        JSONArray tilesData = (JSONArray) tileSet.get("tiles");
        Iterator<JSONObject> tilesIterator = tilesData.iterator();
        while(tilesIterator.hasNext()){
            JSONObject currentTile = tilesIterator.next();
            tileSet.add(new Tile(currentTile));
        }
        System.out.println("All Tiles loaded");
    }
}
