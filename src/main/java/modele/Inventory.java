package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Slot> slots;
    private IntegerProperty currSlotNumber;
    private int maxInventorySize;
    private int currInventorySize;

    public ObservableList<Slot> getSlots() {
        return slots;
    }


    public Inventory() {
        this.slots = FXCollections.observableArrayList();
        this.currSlotNumber = new SimpleIntegerProperty(0);
        this.maxInventorySize = 50;
        this.currInventorySize = 0;
        for (int i = 0; i < maxInventorySize; i++) {
            slots.add(new Slot(null, 0, i));
        }
    }


    public int getCurrSlotNumber() {
        return currSlotNumber.getValue();
    }

    public IntegerProperty currSlotNumberProperty() {
        return currSlotNumber;
    }
    public Slot getCurrSlot(){
        return slots.get(getCurrSlotNumber());
    }

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public Item getCurrItem() {
        try {
            //System.out.println("CUUR" + currSlotNumber);
            return slots.get(getCurrSlotNumber()).getItem();
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
        this.currSlotNumber.setValue(currSlotNumber);
    }

    public void incrementSlot() {
        if (getCurrSlotNumber() < 9) {
            setCurrSlotNumber(getCurrSlotNumber()+1);
        }
    }

    public void decrementSlot() {
        if (getCurrSlotNumber() > 0) {
            setCurrSlotNumber(getCurrSlotNumber()-1);
        }
    }

    public boolean isInventoryFull() {
        if (currInventorySize >= maxInventorySize) {
            return true;
        }
        return false;
    }

    public Item removeFromCurrSlot() {
        try {
            Item item = slots.get(getCurrSlotNumber()).getItem();
            if(slots.get(getCurrSlotNumber()).getItemQuantity() > 1){
                getCurrSlot().decrementItemQuantity(1);
            }
            else {
                slots.set(getCurrSlotNumber(), new Slot(null, 0, getCurrSlotNumber()));
                currInventorySize--;
            }
            return item;

        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("slot vide !");
            return null;
        }
    }

    public void removeFromSlot(int slotNum, int quantity) throws NotEnoughItemInSlotException {
        if(getItemFromSlot(slotNum) != null){
            if(slots.get(slotNum).getItemQuantity() > quantity){
                slots.get(slotNum).decrementItemQuantity(quantity);

            }
            else if (slots.get(slotNum).getItemQuantity() - quantity == 0){
                slots.set(getCurrSlotNumber(), new Slot(null, 0, slotNum));
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


