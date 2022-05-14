package controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ParallelCamera;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import modele.Terrain;
import modele.Protagoniste;
import vue.TerrainView;

import javax.naming.PartialResultException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Pane panneauDeJeu;
    private TerrainView terrainView;
    private Terrain terrain;
    Timeline timeline;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        terrain = new Terrain("src/main/resources/Map/bigTest.json");
        terrainView = new TerrainView(panneauDeJeu);
        terrainView.readMap(terrain);
        creerJoueur();
        KeyHandler keyHandler = new KeyHandler(panneauDeJeu);
        keyHandler.keyWorking();

    }

    public void creerJoueur() {
        Protagoniste joueur = new Protagoniste();
        joueur.setXProperty(10);
        joueur.setYProperty(10);

        ImageView spriteJoueur = new ImageView(new Image(String.valueOf(getClass().getResource("/Sprites/MC/MCSpace_Idle_right.gif")))); // je laisse ça comme exemple qui utilise le dossier resources comme ça quand on gerera les animations on pourra faire le meme delire mais changer le nom de fichier.gif
        spriteJoueur.xProperty().bind(joueur.getXProperty());
        spriteJoueur.yProperty().bind(joueur.getYProperty());

        timeline = new Timeline(new KeyFrame(Duration.millis(16.33), new EventHandler<ActionEvent>() { // 16.33ms envion = 60fps
            @Override
            public void handle(ActionEvent actionEvent) {
                if(KeyHandler.rightPressed){
                    joueur.setXProperty(joueur.getXProperty().intValue() + 50);
                }
                if (KeyHandler.leftPressed){
                    joueur.setXProperty(joueur.getXProperty().intValue() - 50);
                }
                if (KeyHandler.upPressed){
                    joueur.setYProperty(joueur.getYProperty().intValue() - 50);
                }
                if (KeyHandler.downPressed){
                    joueur.setYProperty(joueur.getYProperty().intValue() + 50);
                }
                panneauDeJeu.getScene().getCamera().setLayoutX(joueur.getXProperty().intValue());
                panneauDeJeu.getScene().getCamera().setLayoutY(joueur.getYProperty().intValue());
            }
        }));
        panneauDeJeu.getChildren().add(spriteJoueur);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
