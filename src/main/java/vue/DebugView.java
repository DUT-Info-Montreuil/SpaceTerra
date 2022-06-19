package vue;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import modele.Block;
import modele.Entity;

public class DebugView {

    private static Pane panneau;

    public DebugView(Pane p){
        panneau = p;
    }

    public static void debugBlock(Block block, Color color){
        if(block != null){
            Rectangle r = new Rectangle();
            r.setFill(Color.TRANSPARENT);
            r.setStroke(color);
            r.setX(block.getGridX());
            r.setY(block.getGridY());
            r.setWidth(32);
            r.setHeight(32);
            panneau.getChildren().add(r);
        }
    }

    public static void debugBlockHitbox(Block block, Color color){
        Rectangle r = new Rectangle();
        r.setFill(Color.TRANSPARENT);
        r.setStroke(color);
        r.setX(block.getHitbox().getX());
        r.setY(block.getHitbox().getY());
        r.setWidth(block.getHitbox().getWidth());
        r.setHeight(block.getHitbox().getHeight());
        panneau.getChildren().add(r);
    }

    public static void debugPoint(int x, int y, Color color){
        Circle c = new Circle(3);
        c.setFill(color);
        c.setTranslateX(x);
        c.setTranslateY(y);
        panneau.getChildren().add(c);
    }

    public static void debugEntity(Entity ent){
        Rectangle r = new Rectangle(ent.getHitbox().xProperty().intValue(), ent.getHitbox().yProperty().intValue(), ent.getHitbox().getWidth(), ent.getHitbox().getHeight());
        r.xProperty().bind(ent.getHitbox().xProperty());
        r.yProperty().bind(ent.getHitbox().yProperty());
        r.setFill(Color.TRANSPARENT);
        r.setStroke(Color.RED);
        panneau.getChildren().add(r);
    }
}
