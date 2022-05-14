package controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import modele.Terrain;
import modele.Protagoniste;
import vue.TerrainView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Pane panneauDeJeu;
    private TerrainView terrainView;
    private Terrain terrain;

    private double vmarche;
    private double vitesseY;

    private static double timerSaut;

    private static double g = 0.1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        terrain = new Terrain("src/main/resources/Map/Test.json");
        terrainView = new TerrainView(panneauDeJeu);
        terrainView.readMap(terrain);
        creerJoueur();
        KeyHandler keyHandler = new KeyHandler(panneauDeJeu);
        keyHandler.keyWorking();
        vitesseY = 5;
        vmarche = 1;
    }

    public void creerJoueur() {
        Protagoniste joueur = new Protagoniste();
        joueur.setXProperty(80);
        joueur.setYProperty(80);
        System.out.println(panneauDeJeu.getBoundsInLocal().getWidth());
        System.out.println(panneauDeJeu.getBoundsInLocal().getHeight());

        ImageView imageView = new ImageView(new Image(new File("Sprites/MC/MCSpace_Idle_right.gif").toString()));

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(40), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(KeyHandler.rightPressed){
                    joueur.setXProperty(joueur.getXProperty().doubleValue() + vmarche);
                }
                if (KeyHandler.leftPressed){
                    joueur.setXProperty(joueur.getXProperty().doubleValue() - vmarche);
                }
                if (KeyHandler.upPressed){
                    joueur.setYProperty(joueur.getYProperty().doubleValue() - vitesseY);
                    vitesseY -= g;
                }
                if (KeyHandler.downPressed){
                    joueur.setYProperty(joueur.getYProperty().doubleValue() + vmarche);
                }
                if (joueur.getYProperty().doubleValue() > 80) {
                    vitesseY = 0;
                }
                imageView.xProperty().bind(joueur.getXProperty());
                imageView.yProperty().bind(joueur.getYProperty());

            }
        }));
        panneauDeJeu.getChildren().add(imageView);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


    }

}
