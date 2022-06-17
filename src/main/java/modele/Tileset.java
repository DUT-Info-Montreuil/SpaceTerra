package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Tileset {

    private int tileCount;
    private ArrayList<BlocLoader> blocLoaders;

    public Tileset(JSONObject tileSet){
        tileCount = ((Long)tileSet.get("tilecount")).intValue();
        parseTiles(tileSet);
    }

    public void parseTiles(JSONObject tileSet){
        blocLoaders = new ArrayList<>();
        JSONArray tilesData = (JSONArray) tileSet.get("tiles");
        Iterator<JSONObject> tilesIterator = tilesData.iterator();
        while(tilesIterator.hasNext()){
            JSONObject currentTile = tilesIterator.next();
            blocLoaders.add(new BlocLoader(currentTile));
        }
        System.out.println("All Tiles loaded");
    }

    public ArrayList<BlocLoader> getTiles(){
        return blocLoaders;
    }
}
