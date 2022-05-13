package controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import modele.Layer;
import modele.Map;
import modele.Protagoniste;
import vue.TerrainView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Pane panneauDeJeu;
    private TerrainView terrainView;

    boolean rightPressed, leftPressed, upPressed, downPressed;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        terrainView = new TerrainView(new Map("src/main/resources/Map/Test.json"), panneauDeJeu);
        terrainView.addBlockMap();
        creerJoueur();

    }

    public void creerJoueur() {
        Protagoniste joueur = new Protagoniste();
        joueur.setXProperty(200);
        joueur.setYProperty(200);

        Rectangle rectangle = new Rectangle(joueur.getXProperty().intValue(), joueur.getYProperty().intValue(), 48, 48);
        rectangle.setFill(Color.PINK);
        rectangle.setStroke(Color.BLACK);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(40), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                joueur.setXProperty(joueur.getXProperty().intValue() + 1);
                rectangle.xProperty().bind(joueur.getXProperty());
                //rectangle.yProperty().bind(joueur.getYProperty());
            }
        }));
        panneauDeJeu.getChildren().add(rectangle);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


    }

}
