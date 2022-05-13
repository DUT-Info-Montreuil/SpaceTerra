package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Protagoniste {

    private int vie;
    private String main1;
    private String armure;

    private IntegerProperty x;

    private IntegerProperty y;

    public Protagoniste(){
        this.vie = 20;
        this.main1 = null;
        this.armure = null;
        x = new SimpleIntegerProperty(0);
        y = new SimpleIntegerProperty(0);
    }

}
