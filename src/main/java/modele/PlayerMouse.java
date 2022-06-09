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


    public void playerClickAction(Player player, Terrain terrain) {
        if (player.getInventory().getCurrItem() != null) {
            if(terrain.checkDistancePosition(player, this.getX(), this.getY())){
                player.getInventory().getCurrItem().use();

            }
        }
        else if (item != null){
            if(terrain.checkDistancePosition(player, this.getX(), this.getY())){
                item.use();
            }
        }

        else {
            Block b = terrain.getBlock(getX(), getY());
            if (b != null) {
                if (terrain.checkDistanceBlock(player, b)) {
                    b.setPvs(b.getPvs() - 1);
                    if (terrain.checkDestroyedBlock(b) && b.getRessource() != null && !player.getInventory().isInventoryFull()) {
                        player.pick(b.getRessource());
                    }

                }
            }
        }
    }

    public void onSlotLeftClickedAction(Slot slot, Inventory inventory) {

        if(slot.getItem() != null){
            if(item != null){
                if(slot.getItem().getTypeItem().equals(item.getTypeItem())) {
                try{
                    inventory.addIntoSlot(item, slot.getId(), currentItemQuantity.getValue());
                    currentItemQuantity.setValue(0);
                    maxItemQuantity = 0;
                    item = null;

                } catch (TooManyItemInSlotException e){
                        if(currentItemQuantity.getValue() > slot.getItemQuantity()){
                            int diff = currentItemQuantity.getValue() - slot.getItemQuantity();
                            slot.incrementItemQuantity(diff);
                            currentItemQuantity.setValue(getCurrentItemQuantity() - diff);
                        }
                    }
                }
                else {
                    Slot safeSlot = slot;
                    inventory.getSlots().set(slot.getId(), new Slot(null, 0, slot.getId()));
                    inventory.getSlots().set(slot.getId(), new Slot(item, currentItemQuantity.getValue(), slot.getId()));
                    System.out.println("ici");
                    currentItemQuantity.setValue(safeSlot.getItemQuantity());
                    maxItemQuantity = safeSlot.getMaxQuantity();
                    item = safeSlot.getItem();
                }

            }
            else {
                item = slot.getItem();
                maxItemQuantity = slot.getItem().getMaxQuantity();
                currentItemQuantity = slot.itemQuantityProperty();
                try{
                    inventory.removeFromSlot(slot.getId(), slot.getItemQuantity());
                } catch (NotEnoughItemInSlotException e){

                }
            }


        }
        else {
            if(item != null){
                try{
                    inventory.addIntoSlot(item, slot.getId(), currentItemQuantity.getValue());
                    currentItemQuantity.setValue(0);
                    maxItemQuantity = 0;
                    item = null;

                } catch (TooManyItemInSlotException e){

                }

            }
        }
    }

    public void decremente(int nbItem){
        if(currentItemQuantity.getValue() - nbItem > 1){
            currentItemQuantity.setValue(currentItemQuantity.getValue() - nbItem);
        }
        else {
            currentItemQuantity.setValue(0);
            item = null;
            maxItemQuantity = 0;
        }

    }
}
