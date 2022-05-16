import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import controleur.KeyHandler;
import modele.Player;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("vueMap.fxml"));
        Scene scene = new Scene(root, 1000,1000, Color.DARKBLUE);
        ParallelCamera camera = new ParallelCamera();
        scene.setCamera(camera);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Meilleur jeu du monde");
        stage.show();
        root.requestFocus();
    }
}