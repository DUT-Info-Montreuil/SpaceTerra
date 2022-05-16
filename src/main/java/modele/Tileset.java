package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Tileset {

    private int tileCount;
    private ArrayList<Tile> tiles;

    public Tileset(JSONObject tileSet){
        tileCount = ((Long)tileSet.get("tilecount")).intValue();
        parseTiles(tileSet);
    }

    public void parseTiles(JSONObject tileSet){
        tiles = new ArrayList<>();
        JSONArray tilesData = (JSONArray) tileSet.get("tiles");
        Iterator<JSONObject> tilesIterator = tilesData.iterator();
        while(tilesIterator.hasNext()){
            JSONObject currentTile = tilesIterator.next();
            tiles.add(new Tile(currentTile));
        }
        System.out.println("All Tiles loaded");
    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }
}
