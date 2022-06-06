package vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.scene.ParallelCamera;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class GameCam2D extends ParallelCamera{

    private Pane panneauDeJeu;
    private Timeline timeline;
    private boolean isActive = false;
    private boolean isBindedX = false;
    private boolean isBindedY = false;
    private DoubleProperty currTargetX, currTargetY;
    private boolean isInAnimation = false;

    public GameCam2D(Pane p){
        super();
        panneauDeJeu = p;
    }

    public void startTimeline(){
        timeline = new Timeline
                (new KeyFrame(Duration.millis(16.33), actionEvent -> {
                    if(currTargetX != null && currTargetY != null)
                        checkBounds();
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        isActive = true;
    }

    public void lookAt(DoubleProperty targetX, DoubleProperty targetY){
        try{
            if(this.currTargetX == targetX){
                // does nothing I just put that here so it doesn't spam binds.
                //System.out.println("Camera is already looking at this X.");
                if(this.currTargetY == targetY){
                    // also does nothing but I'm leaving it here for debugging.
                    //System.out.println("Camera is already looking at this Y.");
                }
            }
            else{
                if(isBindedX)
                    this.layoutXProperty().unbind();
                if(isBindedY)
                    this.layoutYProperty().unbind();
                this.layoutXProperty().bind(targetX.subtract(panneauDeJeu.getScene().getWidth() / 2));
                this.layoutYProperty().bind(targetY.subtract(panneauDeJeu.getScene().getHeight() / 2));
                this.currTargetX = targetX;
                this.currTargetY = targetY;
                isBindedX = true;
                isBindedY = true;
                if(!isActive)
                    startTimeline();
            }
        }catch (NullPointerException e){
            System.out.println("GameCam has no scene !");
            System.out.println(e.getMessage());
        }
    }

    public void checkBounds(){// need to set at right spot when unbinded but still works after leaving and entering unbind spots again
        checkXBounds();
        checkYBounds();
    }

    public void checkXBounds(){
        if(isBindedX){
            if (this.getBoundsInLocal().getMinX() > currTargetX.getValue() - (panneauDeJeu.getScene().getWidth() / 2)) {
                this.layoutXProperty().unbind();
                //need to set at right spot but still works after leaving and entering unbind spots again
                isBindedX = false;
            } else if (panneauDeJeu.getBoundsInLocal().getMaxX() < currTargetX.getValue() + (panneauDeJeu.getScene().getWidth() / 2)) {
                this.layoutXProperty().unbind();
                isBindedX = false;
            }
        } else {
            if(!(this.getBoundsInLocal().getMinX() > currTargetX.getValue() - (panneauDeJeu.getScene().getWidth() / 2))&&!(panneauDeJeu.getBoundsInLocal().getMaxX() < currTargetX.getValue() + (panneauDeJeu.getScene().getWidth() / 2))){
                this.layoutXProperty().bind(currTargetX.subtract(panneauDeJeu.getScene().getWidth() / 2));
                System.out.println("Binded X");
                isBindedX = true;
            }
        }
    }

    public void checkYBounds(){
        if(isBindedY){
            if (this.getBoundsInLocal().getMinY() > currTargetY.getValue() - (panneauDeJeu.getScene().getHeight() / 2)) {
                this.layoutYProperty().unbind();
                isBindedY = false;
            } else if (panneauDeJeu.getBoundsInLocal().getMaxY() < currTargetY.getValue() + (panneauDeJeu.getScene().getHeight() / 2)) {
                this.layoutYProperty().unbind();
                isBindedY = false;
            }
        } else {
            if(!(this.getBoundsInLocal().getMinY() > currTargetY.getValue() - (panneauDeJeu.getScene().getHeight() / 2))&&!(panneauDeJeu.getBoundsInLocal().getMaxY() < currTargetY.getValue() + (panneauDeJeu.getScene().getHeight() / 2))){
                this.layoutYProperty().bind(currTargetY.subtract(panneauDeJeu.getScene().getHeight() / 2));
                System.out.println("Binded Y");
                isBindedY = true;
            }
        }
    }

    public void translateTo(int targetX, int targetY, int durationMillis){
        if(!isInAnimation){
            TranslateTransition translation = new TranslateTransition(Duration.millis(durationMillis), this);
            translation.setToX((targetX - panneauDeJeu.getScene().getWidth() / 2) - this.getLayoutX());
            translation.setToY((targetY - panneauDeJeu.getScene().getHeight() / 2) - this.getLayoutY());
            translation.setCycleCount(1);
            translation.play();
            isInAnimation = true;
            translation.setOnFinished(e -> isInAnimation = false);
        }
        else{
            //do nothing
        }
    }

    public void tranlateToLook(DoubleProperty targetX, DoubleProperty targetY, int durationMillis){
        if(!isInAnimation){
            TranslateTransition translation = new TranslateTransition(Duration.millis(durationMillis), this);
            translation.setToX((targetX.intValue() - panneauDeJeu.getScene().getWidth() / 2) - this.getLayoutX());
            translation.setToY((targetY.intValue() - panneauDeJeu.getScene().getHeight() / 2) - this.getLayoutY());
            translation.setCycleCount(1);
            translation.play();
            isInAnimation = true;
            translation.setOnFinished(e -> {
                isInAnimation = false;
                lookAt(targetX, targetY);
            });
        }
        else{
            //do nothing
        }
    }
}