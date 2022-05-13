package vue;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Layer;
import modele.Terrain;

import java.util.ArrayList;

public class TerrainView {
    private Terrain terrain;
    private Pane panneau;

    public TerrainView(Terrain terrain, Pane panneau) {
        this.terrain = terrain;
        this.panneau = panneau;

    }

    public ArrayList<Integer> addBlockMap() {
        ArrayList<Integer> blocks = new ArrayList<>();

        for(int l = 0; l < terrain.getLayers().size(); l++){
            if(terrain.getLayers().get(l).getIsVisible()){
                Layer currentLayer = terrain.getLayers().get(l);
                int x = currentLayer.getxPos();
                int y = currentLayer.getyPos();
                int[] data = currentLayer.getData();
                int currentWidth = 0;
                try{
                    for(int t = 0; t < data.length; t++){
                        if(!(currentWidth < currentLayer.getWidth())){
                            x = currentLayer.getxPos();
                            y += 32;
                            currentWidth = 0;
                        }
                        Rectangle r = new Rectangle(x, y, 32, 32);
                        ImageView img = null;
                        switch(data[t]){
                            case 0:
                                r.setFill(Color.TRANSPARENT);
                                // r.setStroke(Color.RED);
                                break;
                            case 1:
                                r.setFill(Color.RED);
                                //  r.setStroke(Color.RED);
                                break;
                            case 2:
                                r.setFill(Color.YELLOW);
                                //  r.setStroke(Color.RED);
                                break;
                            case 3:
                                r.setFill(Color.PINK);
                                //  r.setStroke(Color.RED);
                                break;
                            case 4:
                                r.setFill(Color.GREEN);
                                //   r.setStroke(Color.RED);
                                break;
                            default:
                                //  r.setFill(Color.BLACK);
                        }

                        x += 32;
                        currentWidth++;
                        panneau.getChildren().add(r);
                    }
                } catch (NullPointerException e){
                    System.out.println("null");
                }
            }
        }
        return blocks;
    }
}
