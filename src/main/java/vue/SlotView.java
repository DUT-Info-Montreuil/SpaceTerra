package vue;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SlotView {
    private DoubleProperty x;
    private DoubleProperty y;
    private Rectangle emptySlotRectangle;
    private ImageView itemView;
    private Label quantityLabel;
    private Pane panneauDeJeu;
    int id;


    public SlotView(int id, double x, double y, ImageView itemView, Pane panneauDeJeu) {
        this.id = id;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.emptySlotRectangle = new Rectangle();
        this.itemView = itemView;
        this.quantityLabel = new Label();
        this.panneauDeJeu = panneauDeJeu;
        initialize();
    }

    public SlotView(int id, ImageView itemView, Pane panneauDeJeu) {
        this.id = id;
        this.x = new SimpleDoubleProperty(0);
        this.y = new SimpleDoubleProperty(0);
        this.emptySlotRectangle = new Rectangle();
        this.itemView = itemView;
        this.quantityLabel = new Label();
        this.panneauDeJeu = panneauDeJeu;
        initialize();
    }

    public void initialize(){
        emptySlotRectangle.xProperty().bind(this.x);
        emptySlotRectangle.yProperty().bind(this.y);
        emptySlotRectangle.setStroke(Color.BLACK);
        emptySlotRectangle.setFill(Color.WHITE);
        emptySlotRectangle.setHeight(32);
        emptySlotRectangle.setWidth(32);
        panneauDeJeu.getChildren().add(emptySlotRectangle);
        emptySlotRectangle.toFront();

        quantityLabel.layoutXProperty().bind(emptySlotRectangle.xProperty().add(20));
        quantityLabel.layoutYProperty().bind(emptySlotRectangle.yProperty().add(18));
        quantityLabel.setTextFill(Color.BLACK);
        panneauDeJeu.getChildren().add(quantityLabel);
        quantityLabel.setVisible(false);
        quantityLabel.toFront();
    }

    public void hideSlot(){
        try{
            itemView.setVisible(false);
        }catch (NullPointerException e){
        }
        emptySlotRectangle.setVisible(false);
        quantityLabel.setVisible(false);
    }

    public void displaySlot(){
        try{
            itemView.setVisible(true);
        }catch (NullPointerException e){

        }
        emptySlotRectangle.setVisible(true);
    }

    public void diplayEmptySlot(){
        emptySlotRectangle.setVisible(true);
    }

    public void displayLabel(){
        quantityLabel.setVisible(true);
    }

    public void hideItemView(){
        try{
            itemView.setVisible(false);
        }catch (NullPointerException e){}
    }
    
    public void setX(double x){
        this.x.setValue(x);
    }
    public void setY(double y){
        this.x.setValue(y);
    }

    public double getX(){
        return this.x.getValue();
    }

    public double getY(){
        return this.y.getValue();
    }

    public DoubleProperty getXProperty(){
        return this.x;
    }

    public DoubleProperty getYProperty(){
        return this.y;
    }

    public Rectangle getEmptySlotRectangle() {
        return emptySlotRectangle;
    }

    public void setEmptySlotRectangle(Rectangle emptySlotRectangle) {
        this.emptySlotRectangle = emptySlotRectangle;
    }

    public ImageView getItemView() {
        return itemView;
    }

    public void setItemView(ImageView itemView) {
        try{
            itemView.xProperty().bind(emptySlotRectangle.xProperty().subtract(itemView.getImage().getWidth()/2 - emptySlotRectangle.getWidth()/2));
            itemView.yProperty().bind(emptySlotRectangle.yProperty().subtract(itemView.getImage().getHeight()/2 - emptySlotRectangle.getHeight()/2));
            itemView.toFront();
            panneauDeJeu.getChildren().add(itemView);
            quantityLabel.setVisible(true);
        }catch (NullPointerException e){
            panneauDeJeu.getChildren().remove(this.itemView);
            quantityLabel.setVisible(false);
        }
        this.itemView = itemView;
    }

    public Label getQuantityLabel() {
        return quantityLabel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
