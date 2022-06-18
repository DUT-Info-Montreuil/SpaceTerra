package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BlockLoader {

    private int id;
    private Hitbox hitbox;
    private String ressource;


    public BlockLoader(JSONObject tile){
        id = (((Long)tile.get("id")).intValue()) + 1; // add 1 because it starts at 0
        hitbox = new Hitbox((JSONObject) ((JSONArray)((JSONObject) tile.get("objectgroup")).get("objects")).get(0));
        JSONArray properties = (JSONArray)tile.get("properties");
        JSONObject ressourceObject = (JSONObject) properties.get(0);
        ressource = (String) ressourceObject.get("value");
        System.out.println(ressourceObject.get("value"));
    }

    public String findTileFileName(JSONObject tile){
        Path p = Paths.get(((String) tile.get("image")).replace("\\", ""));
        return p.getFileName().toString();
    }

    public int getId(){
        return id;
    }


    public Hitbox getHitbox(){
        return hitbox;
    }
    public String getRessource() {
        return ressource;
    }
    @Override
    public String toString() {
        return "Tile{" +
                "id=" + id +
                ", hitbox=" + hitbox +
                ", ressource='" + ressource + '\'' +
                '}';
    }
}