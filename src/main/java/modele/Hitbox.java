package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Hitbox {

    private boolean isSolid;
    private int width;
    private int height;
    private int x, y;
    public Hitbox(JSONObject hitbox) {
        isSolid = ((String) hitbox.get("type")).equals("collisions");
        width = ((Long) hitbox.get("width")).intValue();
        height = ((Long) hitbox.get("height")).intValue();
        x = ((Long) hitbox.get("x")).intValue();
        y = ((Long) hitbox.get("y")).intValue();
    }
    public boolean isSolid() {
        return isSolid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
