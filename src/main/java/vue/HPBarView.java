package vue;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import jdk.jshell.spi.SPIResolutionException;
import modele.Entity;

public class HPBarView {
    private Pane panneauDeJeu;
    private ProgressBar progressBar;

    private Entity ent;

    private int x;
    private int y;

    private int maxHp;


    public HPBarView(Pane panneauDeJeu, int x, int y, Entity ent, int maxHp) {
        this.panneauDeJeu = panneauDeJeu;
        progressBar = new ProgressBar();
        this.x = x;
        this.y = y;
        this.ent = ent;
        this.maxHp = maxHp;
    }

    public void initialize(){
        //progressBar.layoutXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(x));
        //progressBar.layoutYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(y));
        progressBar.layoutXProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty());
        progressBar.layoutYProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(500));
        progressBar.setPrefWidth(100);
        progressBar.setPrefHeight(20);
        //progressBar.progressProperty().bind(ent.getHp().divide(maxHp));
        panneauDeJeu.getChildren().add(progressBar);
        System.out.println(progressBar.getLayoutX());
        System.out.println(progressBar.getLayoutY());
    }



}
