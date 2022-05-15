package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;

public abstract class Block {
    private String name;
    private int id;
    private boolean isSolid;
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;

    public Block(String name, int id, boolean isSolid) {
        this.name = name;
        this.id = id;
        this.isSolid = isSolid;
        this.xProperty = new SimpleDoubleProperty(0);
        this.yProperty = new SimpleDoubleProperty(0);
    }

    public final DoubleProperty getXProperty() {
        return xProperty;
    }

    public  final DoubleProperty getYProperty() {
        return yProperty;
    }

    public final void setXProperty(double nb) {
        xProperty.setValue(nb);
    }

    public final void setYProperty(double nb) {
        yProperty.setValue(nb);
    }

    public boolean isSolid(){
        return  this.isSolid;
    }
}
