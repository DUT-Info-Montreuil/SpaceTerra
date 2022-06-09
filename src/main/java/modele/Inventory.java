package modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventory {
    private ObservableList<Slot> slots;
    private int currSlotNumber;
    private int maxInventorySize;
    private int currInventorySize;

    public ObservableList<Slot> getSlots() {
        return slots;
    }

    public Inventory() {
        this.slots = FXCollections.observableArrayList();
        this.currSlotNumber = 0;
        this.maxInventorySize = 50;
        this.currInventorySize = 0;
        for (int i = 0; i < maxInventorySize; i++) {
            slots.add(new Slot(null, 0, i));
        }
    }


    public int getCurrSlotNumber() {
        return currSlotNumber;
    }

    public Slot getCurrSlot(){
        return slots.get(currSlotNumber);
    }

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public Item getCurrItem() {
        try {
            //System.out.println("CUUR" + currSlotNumber);
            return slots.get(currSlotNumber).getItem();
        } catch (Exception e) {
            return null;
        }
    }

    public Item getItemFromSlot(int numSlot) {
        try {
            return slots.get(numSlot).getItem();
        } catch (Exception e) {
            return null;
        }
    }

    public int getNextEmptySlot() {
        int i = 0;
        while (i < slots.size() && slots.get(i).getItem() != null) {
            i++;
        }

        return i;

    }

    public void setMaxInventorySize(int maxInventorySize) {
        this.maxInventorySize = maxInventorySize;
    }

    public void setCurrSlotNumber(int currSlotNumber) {
        this.currSlotNumber = currSlotNumber;
    }

    public void incrementSlot() {
        if (this.currSlotNumber < this.maxInventorySize - 1) {
            currSlotNumber++;
        }
    }

    public void decrementSlot() {
        if (this.currSlotNumber > 0) {
            currSlotNumber--;
        }
    }

    public boolean isInventoryFull() {
        if (currInventorySize >= maxInventorySize) {
            return true;
        }
        return false;
    }

    public Item removeFromSlot() {
        try {
            Item item = slots.get(currSlotNumber).getItem();
            if(slots.get(currSlotNumber).getItemQuantity() > 1){
                getCurrSlot().decrementItemQuantity(1);
            }
            else {
                slots.set(currSlotNumber, new Slot(null, 0, currSlotNumber));
                currInventorySize--;
            }
            return item;

        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("slot vide !");
            return null;
        }
    }

    public void addIntoSlot(Item item) {
        if (!isInventoryFull()) {
            if (this.getNotFullSlotWithItem(item) != null) {
                this.getNotFullSlotWithItem(item).incrementItemQuantity(1);
            } else {
                slots.set(getNextEmptySlot(), new Slot(item, 1, getNextEmptySlot()));
                currInventorySize++;
            }
        } else{
            System.out.println("inventaire plein !");
        }
        //System.out.println(slots);
    }

    public Slot getNotFullSlotWithItem(Item item){
        for(Slot slot1 : slots){
            if(slot1.getItem() != null && slot1.getTypeItem() == item.getId() && slot1.getItemQuantity() < slot1.getMaxQuantity()){
                return slot1;
            }
        }
        return null;
    }

}


