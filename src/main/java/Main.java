import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("vueMap.fxml"));
        stage.setScene(root.getScene());
        stage.setResizable(false);
        stage.setTitle("Meilleur jeu du monde");
        stage.getIcons().add(new Image("/Sprites/Enemies/YinYang.png"));
        stage.show();
        root.requestFocus();
    }
}