import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("vueMap.fxml"));
        Scene scene = new Scene(root, 600,600, Color.LIGHTSKYBLUE);
        stage.setScene(scene);
        stage.show();
    }
}