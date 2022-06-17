package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class JsonGameLoader {

    private JSONObject map;
    private HashMap<Integer, String> tileImages;
    private ArrayList<Layer> layers;
    private ArrayList<BlocLoader> tiles;
    private int height, width;
    private int tileWidth, tileHeight;


    public JsonGameLoader(String mapPath){
        loadMap(mapPath);
        parseLayers();
        parseTiles();
        height = ((Long) map.get("height")).intValue();
        width = ((Long) map.get("width")).intValue();
        tileHeight = ((Long) map.get("tileheight")).intValue();
        tileWidth = ((Long) map.get("tilewidth")).intValue();
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

    public void parseTiles(){
        tiles = new ArrayList<>();
        tileImages = new HashMap<>();
        JSONObject jTileSet = (JSONObject) ((JSONArray) map.get("tilesets")).get(0);
        JSONArray tilesData = (JSONArray) jTileSet.get("tiles");
        Iterator<JSONObject> tilesIterator = tilesData.iterator();
        while(tilesIterator.hasNext()){
            JSONObject currentTile = tilesIterator.next();
            tileImages.put((((Long)currentTile.get("id")).intValue()) + 1, String.valueOf(getClass().getResource("/Sprites/TileSet/" + findTileFileName(currentTile))));
            tiles.add(new BlocLoader(currentTile));
        }
        System.out.println("All Tiles loaded");
    }

    public String findTileFileName(JSONObject tile){
        Path p = Paths.get(((String) tile.get("image")).replace("\\", ""));
        return p.getFileName().toString();
    }

    public HashMap<Integer, String> getTileImages() {
        return tileImages;
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public ArrayList<BlocLoader> getTiles() {
        return tiles;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }
}
