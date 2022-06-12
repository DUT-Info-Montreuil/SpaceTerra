package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Slot {
    private Item item;
    private IntegerProperty itemQuantity;

    private int typeItem;

    private int maxQuantity;

    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Slot(Item item, int itemQuantity, int id) {
        this.item = item;
        this.itemQuantity = new SimpleIntegerProperty(itemQuantity);
        this.id = id;
        try{
            this.typeItem = item.getId();
            this.maxQuantity = item.getMaxQuantity();
        }
        catch(NullPointerException e){
            this.typeItem = -1;
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

    public int getTypeItem() {
        return typeItem;
    }

    public void setTypeItem(int typeItem) {
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
        if(itemQuantity.getValue() > 0){
            this.itemQuantity.setValue(getItemQuantity() -  quantity);
        }
    }

    @Override
    public String toString() {
        return "Slot{" +
                "item=" + item +
                ", itemQuantity=" + itemQuantity +
                ", typeItem='" + typeItem + '\'' +
                ", maxQuantity=" + maxQuantity +
                ", id=" + id +
                '}';
    }

}
