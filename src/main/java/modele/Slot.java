package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Slot {
    private Item item;
    private IntegerProperty itemQuantity;
    private String typeItem;

    private int maxQuantity;



    public Slot(Item item, int itemQuantity) {
        this.item = item;
        this.itemQuantity = new SimpleIntegerProperty(itemQuantity);
        try{
            this.typeItem = item.getClass().toString();
            this.maxQuantity = item.getMaxQuantity();
        }
        catch(NullPointerException e){
            this.typeItem = null;
            this.maxQuantity = 0;
        }

    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getItemQuantity() {
        return itemQuantity.getValue();
    }

    public IntegerProperty itemQuantityProperty(){
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity.setValue(itemQuantity);
    }

    public String getTypeItem() {
        return typeItem;
    }

    public void setTypeItem(String typeItem) {
        this.typeItem = typeItem;
    }
    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public void incrementItemQuantity(int quantity){
        this.itemQuantity.setValue(getItemQuantity() + quantity);
    }

    public void decrementItemQuantity(int quantity){
        this.itemQuantity.setValue(getItemQuantity() -  quantity);
    }

    @Override
    public String toString() {
        return "Slot{" +
                "item=" + item +
                ", itemQuantity=" + itemQuantity +
                ", TypeItem='" + typeItem + '\'' +
                ", maxQuantity=" + maxQuantity +
                '}';
    }
}
