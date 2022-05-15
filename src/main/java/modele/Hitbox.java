package modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Hitbox {

    private boolean isSolid;
    private int width;
    private int height;
    private int x, y;
    public Hitbox(JSONObject group) {
        isSolid = ((String) group.get("type")).equals("collisions");
        JSONObject jsonHitbox = (JSONObject) ((JSONArray) group.get("objects")).get(0);
        width = ((Long) jsonHitbox.get("width")).intValue();
        height = ((Long) jsonHitbox.get("height")).intValue();
        x = ((Long) jsonHitbox.get("x")).intValue();
        y = ((Long) jsonHitbox.get("y")).intValue();
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
