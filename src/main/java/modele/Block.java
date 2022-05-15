package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;

public class Block {
    private int x;
    private int y;
    private int hitX;
    private int hitY;

    private Tile tile;

    public Block(Tile tile, int x, int y) {
        this.x = x;
        this.y = y;
        this.tile = tile;
        hitX = x + tile.getHitbox().getX();
        hitY = y + tile.getHitbox().getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tile getTile() {
        return tile;
    }

    public int getHitX() {
        return hitX;
    }

    public int getHitY() {
        return hitY;
    }
}
