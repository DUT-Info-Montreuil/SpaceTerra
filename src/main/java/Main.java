import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {//coucou c'est moi
        Pane root = FXMLLoader.load(getClass().getResource("vueMap.fxml"));
        stage.setScene(root.getScene());
        stage.setResizable(false);
        stage.setTitle("Meilleur jeu du monde");
        stage.getIcons().add(new Image("/Sprites/Enemies/Bingus/Bingus_idle.gif"));
        stage.show();
        root.requestFocus();
    }
}