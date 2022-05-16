package vue;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.*;

public class TerrainView {
    private Pane panneau;

    public TerrainView(Pane panneau) {
        this.panneau = panneau;
    }

    public void readMap(Terrain terrain) {
        int id = 0;
        for (Block block : terrain.getBlocks()) {
            ImageView imgView = new ImageView(block.getTile().getImage());
            imgView.setId(block.getId());
            imgView.setX(block.getX());
            imgView.setY(block.getY());
            System.out.println(imgView.getId());
            panneau.getChildren().add(imgView);
        }
    }

    public void displayCollision(boolean display, Terrain terrain, Player player){
        if(display){
            for (Block block : terrain.getBlocks()) {
                Rectangle r = new Rectangle(block.getHitX(), block.getHitY(), block.getTile().getHitbox().getWidth(), block.getTile().getHitbox().getHeight());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BLACK);
                panneau.getChildren().add(r);
            }
            Rectangle r = new Rectangle(player.xProperty().intValue(), player.yProperty().intValue(), player.getWidth(), player.getHeight());
            r.yProperty().bind(player.yProperty());
            r.xProperty().bind(player.xProperty());
            r.setFill(Color.TRANSPARENT);
            r.setStroke(Color.BLACK);
            panneau.getChildren().add(r);

        }
    }


    public void deleteBlock(Block block) {
        System.out.println("oui5");
        panneau.getChildren().remove(panneau.lookup("#" + block.getId()));
    }

    public void deleteSolidBlock(Block block) {
        panneau.getChildren().remove(panneau.lookup("#" + block.getId()));
        System.out.println("oui6");
    }
}
