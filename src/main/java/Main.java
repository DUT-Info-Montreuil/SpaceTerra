import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.KeyHandler2;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("vueMap.fxml"));
        Scene scene = new Scene(root, 32*10,32*10, Color.LIGHTSKYBLUE);
        stage.setScene(scene);
        stage.show();
        KeyHandler2.keyTyped(scene);
    }
}