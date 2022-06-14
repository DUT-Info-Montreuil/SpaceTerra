package modele;

import controleur.MouseHandler;
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
        try {
            maxItemQuantity = item.getMaxQuantity();
        } catch (NullPointerException e) {
            maxItemQuantity = 0;
        }
    }


    void leftPressed(Player player, Terrain terrain, MouseHandler mouseHandler) {

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


    public void playerLeftPressedAction(Player player, Terrain terrain) {
        if (player.getPlayerInventory().getCurrItem() != null) {
            player.getPlayerInventory().getCurrItem().use();
        } else if (item != null) {
            item.use();
        } else {
            player.breakBlock(getX(), getY(), 2, 2);
        }
    }

    public void onInventorySlotLeftClickedAction(Slot slot, Inventory inventory) {

        if (slot.getItem() != null) {
            if (item != null) {
                if (slot.getItem().getTypeItem().equals(item.getTypeItem())) {
                    try {
                        inventory.addIntoSlot(item, slot.getId(), currentItemQuantity.getValue());
                        currentItemQuantity.setValue(0);
                        maxItemQuantity = 0;
                        item = null;

                    } catch (TooManyItemInSlotException e) {

                        int diff = maxItemQuantity - slot.getItemQuantity();
                        slot.incrementItemQuantity(diff);
                        currentItemQuantity.setValue(getCurrentItemQuantity() - diff);

                    }
                } else {
                    Slot safeSlot = slot;
                    inventory.getSlots().set(slot.getId(), new Slot(null, 0, slot.getId()));
                    inventory.getSlots().set(slot.getId(), new Slot(item, currentItemQuantity.getValue(), slot.getId()));
                    currentItemQuantity.setValue(safeSlot.getItemQuantity());
                    maxItemQuantity = safeSlot.getMaxQuantity();
                    item = safeSlot.getItem();
                }

            } else {
                item = slot.getItem();
                maxItemQuantity = slot.getItem().getMaxQuantity();
                currentItemQuantity = slot.itemQuantityProperty();
                try {
                    inventory.removeFromSlot(slot.getId(), slot.getItemQuantity());
                } catch (NotEnoughItemInSlotException e) {

                }
            }


        } else {
            if (item != null) {
                try {
                    inventory.addIntoSlot(item, slot.getId(), currentItemQuantity.getValue());
                    currentItemQuantity.setValue(0);
                    maxItemQuantity = 0;
                    item = null;

                } catch (TooManyItemInSlotException e) {

                }

            }
        }
    }

    public void decrementeItemQuantity(int nbItem) {
        if (currentItemQuantity.getValue() - nbItem > 0) {
            currentItemQuantity.setValue(currentItemQuantity.getValue() - nbItem);
        } else {
            currentItemQuantity.setValue(0);
            item = null;
            maxItemQuantity = 0;
        }

    }

    public void incrementItemQuantity(Item itemAdd, int nbItem) {
        if (currentItemQuantity.getValue() + nbItem <= maxItemQuantity) {
            currentItemQuantity.setValue(currentItemQuantity.getValue() + nbItem);
        } else if (currentItemQuantity.getValue() == 0) {
            currentItemQuantity.setValue(currentItemQuantity.getValue() + nbItem);
            item = itemAdd;
            maxItemQuantity = item.getMaxQuantity();
        }
    }

    public void onInventorySlotRightClicked(Slot slot, Inventory inventory) {

        if (currentItemQuantity.getValue() > 0) {
            if (currentItemQuantity.getValue() < maxItemQuantity) {
                try {
                    if (slot.getItemQuantity() > 1) {
                        if (item.getTypeItem().equals(slot.getItem().getTypeItem())) {
                            incrementItemQuantity(slot.getItem(), 1);
                            slot.decrementItemQuantity(1);
                        }
                    } else if (slot.getItemQuantity() == 1) {
                        incrementItemQuantity(slot.getItem(), 1);
                        inventory.getSlots().set(slot.getId(), new Slot(null, 0, slot.getId()));
                    } else {
                        inventory.getSlots().set(slot.getId(), new Slot(item, 1, slot.getId()));
                        decrementeItemQuantity(1);

                    }


                } catch (NullPointerException e) {

                }
            }
            else if (currentItemQuantity.getValue() == maxItemQuantity){
                if(slot.getItemQuantity() == 0){
                    inventory.getSlots().set(slot.getId(), new Slot(item, 1, slot.getId()));
                    decrementeItemQuantity(1);
                }

            }


        }

        else {

            if (slot.getItemQuantity() > 1) {
                incrementItemQuantity(slot.getItem(), 1);
                slot.decrementItemQuantity(1);
            } else if (slot.getItemQuantity() == 1) {
                incrementItemQuantity(slot.getItem(), 1);
                inventory.getSlots().set(slot.getId(), new Slot(null, 0, slot.getId()));
            }
        }
    }


    public void onDeletedSLotLeftClicked() {
        item = null;
        maxItemQuantity = 0;
        currentItemQuantity.setValue(0);
    }

    public void onDeletedSLotRightClicked() {
        decrementeItemQuantity(1);
    }

    public void onResultSlotLeftClicked(Slot slot, CraftInventory craftInventory) {
        if (slot.getItem() != null) {
            if (item == null) {
                incrementItemQuantity(slot.getItem(), slot.getItemQuantity());
                craftInventory.decrementResultSlot(slot.getItemQuantity());
                slot.decrementItemQuantity(slot.getItemQuantity());
            }
        }
    }

    public void onResultSlotRightClicked(Slot slot, CraftInventory craftInventory) {
        if (slot.getItem() != null) {
            if (currentItemQuantity.getValue() > 0) {
                if (slot.getItem().getTypeItem().equals(item.getTypeItem())) {
                    incrementItemQuantity(slot.getItem(), 1);
                    slot.decrementItemQuantity(1);
                    craftInventory.decrementResultSlot(1);
                }
            } else {
                incrementItemQuantity(slot.getItem(), 1);
                slot.decrementItemQuantity(1);
                craftInventory.decrementResultSlot(1);
            }
        }

    }
}




