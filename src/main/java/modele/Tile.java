package modele;

import javafx.scene.image.Image;
import org.json.simple.JSONObject;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Tile {
    private int id;
    private Image image;
    private int tileWidth, tileHeight;

    public Tile(JSONObject tile){
        id = (((Long)tile.get("id")).intValue()) + 1; // On ajoute 1 parce que les id commence a 0 mais 0 = vide dans le data des layers
        tileWidth = ((Long)tile.get("imagewidth")).intValue();
        tileHeight = ((Long)tile.get("imageheight")).intValue();
        image = new Image(String.valueOf(getClass().getResource("/Sprites/TileSet/" + findTileFileName(tile))));
    }

    public String findTileFileName(JSONObject tile){
        Path p = Paths.get(((String) tile.get("image")).replace("\\", ""));
        return p.getFileName().toString();
    }

    public int getId(){
        return id;
    }

    public Image getImage(){
        return image;
    }

    public int getTileWidth(){
        return tileWidth;
    }
}
