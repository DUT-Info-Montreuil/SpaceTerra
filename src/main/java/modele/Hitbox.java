package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Hitbox {

    private boolean isSolid;
    private int width;
    private int height;
    private DoubleProperty x, y;

    public Hitbox(JSONObject hitbox) {
        isSolid = ((String) hitbox.get("type")).equals("collisions");
        width = ((Long) hitbox.get("width")).intValue();
        height = ((Long) hitbox.get("height")).intValue();
        x = new SimpleDoubleProperty();
        y = new SimpleDoubleProperty();
        x.setValue(((Long) hitbox.get("x")).intValue());
        y.setValue(((Long) hitbox.get("y")).intValue());
    }

    public Hitbox(int width, int height, double x, double y){
        this.isSolid = false;
        this.width = width;
        this.height = height;
        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();
        this.x.setValue(x);
        this.y.setValue(y);
    }

    public int getCenterXPos(){
        return x.intValue() + width/2;
    }

    public int getCenterYPos(){
        return y.intValue() + height/2;
    }

    public boolean isArroundX(int targetX){
        return targetX > x.intValue() && targetX < x.intValue() + width;
    }

    public boolean isArroundY(int targetY){
        return targetY > y.intValue() && targetY < y.intValue() + height;
    }

    public boolean isArround(int targetX, int targetY){
        return isArroundX(targetX) && isArroundY(targetY);
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

    public DoubleProperty getX() {
        return x;
    }

    public DoubleProperty getY() {
        return y;
    }

    public void setX(double x) {
        this.x.setValue(x);
    }

    public void setY(double y) {
        this.y.setValue(y);
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
