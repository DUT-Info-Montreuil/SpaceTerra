package vue;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jdk.jshell.spi.SPIResolutionException;
import modele.Entity;

import java.awt.*;

public class HPBarView {
    private Pane panneauDeJeu;
    private Rectangle pvBar;
    private Rectangle pvBarOutLine;

    private Entity ent;

    private int x;
    private int y;

    private int maxHp;


    public HPBarView(Pane panneauDeJeu, int x, int y, Entity ent, int maxHp) {
        this.panneauDeJeu = panneauDeJeu;
        pvBarOutLine = new Rectangle();
        pvBar = new Rectangle();
        this.x = x;
        this.y = y;
        this.ent = ent;
        this.maxHp = maxHp;
    }

    public void initialize(){
        pvBarOutLine.layoutXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(x-1));
        pvBarOutLine.layoutYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(y-3));
        pvBarOutLine.setWidth(maxHp*8.1);
        pvBarOutLine.setHeight(20);
        pvBarOutLine.setFill(Color.WHITE);
        pvBarOutLine.setStroke(Color.BLACK);
        panneauDeJeu.getChildren().add(pvBarOutLine);

        pvBar.layoutXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(x));
        pvBar.layoutYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(y));
        pvBar.widthProperty().bind(ent.getHp().multiply(8));
        pvBar.setHeight(15);
        pvBar.setFill(Color.RED);
        panneauDeJeu.getChildren().add(pvBar);

    }



}
