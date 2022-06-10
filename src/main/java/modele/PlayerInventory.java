package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class PlayerInventory extends Inventory{

    private IntegerProperty currSlotNumber;

    public PlayerInventory(int maxInventorySize) {
        super(maxInventorySize);
        currSlotNumber = new SimpleIntegerProperty(0);
    }


    public int getCurrSlotNumber() {
        return currSlotNumber.getValue();
    }

    public IntegerProperty currSlotNumberProperty() {
        return currSlotNumber;
    }
    public Slot getCurrSlot(){
        return getSlots().get(getCurrSlotNumber());
    }


    public Item getCurrItem() {
        try {
            //System.out.println("CUUR" + currSlotNumber);
            return getSlots().get(getCurrSlotNumber()).getItem();
        } catch (Exception e) {
            return null;
        }
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


    public Item removeFromCurrSlot() {
        try {
            Item item = getSlots().get(getCurrSlotNumber()).getItem();
            if(getSlots().get(getCurrSlotNumber()).getItemQuantity() > 1){
                getCurrSlot().decrementItemQuantity(1);
            }
            else {
                getSlots().set(getCurrSlotNumber(), new Slot(null, 0, getCurrSlotNumber()));
                setCurrInventorySize(getCurrInventorySize() - 1);
            }
            return item;

        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("slot vide !");
            return null;
        }
    }
}


