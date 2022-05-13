package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Protagoniste {

    private int vie;
    private String main1;
    private String armure;

    private IntegerProperty xProperty;

    private IntegerProperty yProperty;

    public Protagoniste(){
        this.vie = 20;
        this.main1 = null;
        this.armure = null;
        xProperty = new SimpleIntegerProperty(0);
        yProperty = new SimpleIntegerProperty(0);
    }

    public final IntegerProperty getXProperty() {
        return xProperty;
    }

    public  final IntegerProperty getYProperty() {
        return yProperty;
    }

    public final void setXProperty(int nb) {
        xProperty.setValue(nb);
    }

    public final void setYProperty(int nb) {
        yProperty.setValue(nb);
    }

}
