package vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.ParallelCamera;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameCam2D extends ParallelCamera{

    private Pane panneauDeJeu;
    private Timeline timeline;
    private boolean isActive = false;
    private boolean isBinded = false;
    private DoubleProperty targetX, targetY;

    public GameCam2D(Pane p){
        super();
        panneauDeJeu = p;
    }

    public void startTimeline(){
        timeline = new Timeline
                (new KeyFrame(Duration.millis(16.33), actionEvent -> {
                    if(targetX != null && targetY != null)
                        checkBounds();
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        isActive = true;
    }

    public void lookAt(DoubleProperty targetX, DoubleProperty targetY){
        try{
            if(this.targetX == targetX){
                System.out.println("Camera is already looking at this X.");
                if(this.targetY == targetY){
                    System.out.println("Camera is already looking at this Y.");
                }
            }
            else{
                this.layoutXProperty().bind(targetX.subtract(panneauDeJeu.getScene().getWidth() / 2));
                this.layoutYProperty().bind(targetY.subtract(panneauDeJeu.getScene().getHeight() / 2));
                this.targetX = targetX;
                this.targetY = targetY;
                isBinded = true;
                if(!isActive)
                    startTimeline();
            }
        }catch (NullPointerException e){
            System.out.println("GameCam has no scene !");
            System.out.println(e.getMessage());
        }
    }

    public void checkBounds(){// need to set at right spot when unbinds but still works after leaving and entering unbind spots again
        if (isBinded && this.getBoundsInLocal().getMinX() > targetX.getValue() - (panneauDeJeu.getScene().getWidth() / 2) - 10) {
            this.layoutXProperty().unbind();
            //need to set at right spot but still works after leaving and entering unbind spots again
            isBinded = false;
        } else if (isBinded && panneauDeJeu.getBoundsInLocal().getMaxX() < targetX.getValue() + (panneauDeJeu.getScene().getWidth() / 2) + 10) {
            this.layoutXProperty().unbind();
            isBinded = false;
        } else {
            this.layoutXProperty().bind(targetX.subtract(panneauDeJeu.getScene().getWidth() / 2));
            isBinded = true;
        }
        if (isBinded && this.getBoundsInLocal().getMinY() > targetY.getValue() - (panneauDeJeu.getScene().getHeight() / 2)) {
            this.layoutYProperty().unbind();
            isBinded = false;
        } else if (isBinded && panneauDeJeu.getBoundsInLocal().getMaxY() < targetY.getValue() + (panneauDeJeu.getScene().getHeight() / 2) + 20) {
            this.layoutYProperty().unbind();
            isBinded = false;
        } else {
            this.layoutYProperty().bind(targetY.subtract(panneauDeJeu.getScene().getHeight() / 2));
            isBinded = true;
        }
    }
}