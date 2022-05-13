package controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ParallelCamera;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import modele.Terrain;
import modele.Protagoniste;
import vue.TerrainView;

import javax.naming.PartialResultException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Pane panneauDeJeu;
    private TerrainView terrainView;
    private Terrain terrain;

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
        joueur.setXProperty(200);
        joueur.setYProperty(200);

        Rectangle rectangle = new Rectangle(joueur.getXProperty().intValue(), joueur.getYProperty().intValue(), 48, 48);
        rectangle.setFill(Color.PINK);
        rectangle.setStroke(Color.BLACK);

        rectangle.xProperty().bind(joueur.getXProperty());
        rectangle.yProperty().bind(joueur.getYProperty());

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(40), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(KeyHandler.rightPressed){
                    joueur.setXProperty(joueur.getXProperty().intValue() + 1);
                }
                if (KeyHandler.leftPressed){
                    joueur.setXProperty(joueur.getXProperty().intValue() - 1);
                }
                if (KeyHandler.upPressed){
                    joueur.setYProperty(joueur.getYProperty().intValue() - 1);
                }
                if (KeyHandler.downPressed){
                    joueur.setYProperty(joueur.getYProperty().intValue() + 1);
                }
                panneauDeJeu.getScene().getCamera().setLayoutX(joueur.getXProperty().intValue());
                panneauDeJeu.getScene().getCamera().setLayoutY(joueur.getYProperty().intValue());
            }
        }));
        panneauDeJeu.getChildren().add(rectangle);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
