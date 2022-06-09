package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PlayerMouse {
    Item item;
    int maxItemQuantity;
    IntegerProperty currentItemQuantity;
    IntegerProperty x;
    IntegerProperty y;

    public PlayerMouse(Item item) {
        this.item = item;
        this.x = new SimpleIntegerProperty(0);
        this.y = new SimpleIntegerProperty(0);
        currentItemQuantity = new SimpleIntegerProperty(0);
        try{
            maxItemQuantity = item.getMaxQuantity();
        } catch (NullPointerException e){
            maxItemQuantity = 0;
        }
    }
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getMaxItemQuantity() {
        return maxItemQuantity;
    }

    public void setMaxItemQuantity(int maxItemQuantity) {
        this.maxItemQuantity = maxItemQuantity;
    }

    public int getCurrentItemQuantity() {
        return currentItemQuantity.getValue();
    }

    public IntegerProperty currentItemQuantityProperty() {
        return currentItemQuantity;
    }

    public void setCurrentItemQuantity(int currentItemQuantity) {
        this.currentItemQuantity.setValue(currentItemQuantity);
    }
    public int getX() {
        return x.getValue();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public int getY() {
        return y.getValue();
    }

    public IntegerProperty yProperty() {
        return y;
    }

}
