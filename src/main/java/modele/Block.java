package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;

public class Block {
    private int x;
    private int y;

    private Tile tile;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tile getTile() {
        return tile;
    }

    public Block(Tile tile, int x, int y) {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }



}
