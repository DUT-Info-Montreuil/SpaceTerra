package modele;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile {

    private int width;
    private int height;
    private Image img;
    private int xPos;
    private int yPos;

    public Tile(String path){
        this.img = new Image(path);
        this.width = (int) img.getWidth();
        this.height = (int) img.getHeight();

    }

}
