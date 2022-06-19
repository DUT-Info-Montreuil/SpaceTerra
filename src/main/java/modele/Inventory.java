package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Inventory {
    private ObservableList<Slot> slots;
    private int maxInventorySize;

    public int getCurrInventorySize() {
        return currInventorySize;
    }

    public void setCurrInventorySize(int currInventorySize) {
        this.currInventorySize = currInventorySize;
    }

    private int currInventorySize;

    public ObservableList<Slot> getSlots() {
        return slots;
    }


    public Inventory(int maxInventorySize) {
        this.slots = FXCollections.observableArrayList();
        this.maxInventorySize = maxInventorySize;
        this.currInventorySize = 0;
        for (int i = 0; i < maxInventorySize; i++) {
            slots.add(new Slot(null, 0, i));
        }
    }


    public int getMaxInventorySize() {
        return maxInventorySize;
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


    public boolean isInventoryFull() {
        if (currInventorySize >= maxInventorySize) {
            return true;
        }
        return false;
    }


    public void removeFromSlot(int slotNum, int quantity) throws NotEnoughItemInSlotException {
        if(getItemFromSlot(slotNum) != null){
            if(slots.get(slotNum).getItemQuantity() > quantity){
                slots.get(slotNum).decrementItemQuantity(quantity);

            }
            else if (slots.get(slotNum).getItemQuantity() - quantity == 0){
                slots.set(slotNum, new Slot(null, 0, slotNum));
                currInventorySize--;
            }
            else {
                throw new NotEnoughItemInSlotException(slotNum);
            }

        }
    }

    public void removeFromSlot(int slotNum) {
        if(getItemFromSlot(slotNum) != null){
            if(slots.get(slotNum).getItemQuantity() > 1){
                slots.get(slotNum).decrementItemQuantity(1);
            }
            else {
                slots.set(slotNum, new Slot(null, 0, slotNum));
                currInventorySize--;
            }

        }
    }


    public void addIntoSlot(Item item, int slotNum, int quantity) throws TooManyItemInSlotException{
        if(slots.get(slotNum) != null){
            if(getItemFromSlot(slotNum) != null){
                if(getItemFromSlot(slotNum).getTypeItem().equals(item.getTypeItem())){
                    if(slots.get(slotNum).getItemQuantity() + quantity <= slots.get(slotNum).getMaxQuantity()){
                        slots.get(slotNum).incrementItemQuantity(quantity);
                    }
                    else {
                        throw new TooManyItemInSlotException(slotNum);
                    }
                }

            }
            else {
                if(quantity <= item.getMaxQuantity()){
                    slots.set(slotNum, new Slot(item, quantity, slotNum));
                    currInventorySize++;
                }
                else{
                    throw new TooManyItemInSlotException(slotNum);
                }
            }
        }

    }

    public void addIntoSlot(Item item, int slotNum) throws TooManyItemInSlotException{
        if(getItemFromSlot(slotNum).getTypeItem().equals(item.getTypeItem())){
            if(slots.get(slotNum) != null){
                if(slots.get(slotNum).getItemQuantity() + 1 <= slots.get(slotNum).getMaxQuantity()){
                    slots.get(slotNum).incrementItemQuantity(1);
                }
                else {
                    throw new TooManyItemInSlotException(slotNum);
                }
            }
            else {
                if(slots.get(slotNum).getItemQuantity() + 1 <= slots.get(slotNum).getMaxQuantity()){
                    slots.set(slotNum, new Slot(item, 1, slotNum));
                    currInventorySize++;
                }
                else{
                    throw new TooManyItemInSlotException(slotNum);
                }
            }

        }
    }
    public void addIntoNextEmptySlot(Item item) {
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
    }

    public void addIntoNextEmptySlot(Item item, int quantity) {
        if (!isInventoryFull()) {
            if (this.getNotFullSlotWithItem(item) != null) {
                if (this.getNotFullSlotWithItem(item).getItemQuantity() + quantity <=  this.getNotFullSlotWithItem(item).getMaxQuantity()){
                    this.getNotFullSlotWithItem(item).incrementItemQuantity(quantity);
                }
                else {
                    int diff = this.getNotFullSlotWithItem(item).getMaxQuantity() - this.getNotFullSlotWithItem(item).getItemQuantity();
                    quantity -= diff;
                    this.getNotFullSlotWithItem(item).incrementItemQuantity(diff);
                    addIntoNextEmptySlot(item, quantity);
                }
            } else {
                slots.set(getNextEmptySlot(), new Slot(item, quantity, getNextEmptySlot()));
                currInventorySize++;
            }
        } else{
            System.out.println("inventaire plein !");
        }
    }

    public Slot getNotFullSlotWithItem(Item item){
        for(Slot slot1 : slots){
            if(slot1.getItem() != null && slot1.getTypeItem() == item.getId() && slot1.getItemQuantity() < slot1.getMaxQuantity()){
                return slot1;
            }
        }
        return null;
    }

    public Slot getSlot(int i){
        return slots.get(i);
    }


}


