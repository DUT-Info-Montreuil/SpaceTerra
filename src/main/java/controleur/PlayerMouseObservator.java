package controleur;

import javafx.scene.control.Label;
import modele.*;
import vue.*;

public class PlayerMouseObservator {
    private PlayerMouse playerMouse;
    private PlayerMouseView playerMouseView;

    public PlayerMouseObservator(PlayerMouse playerMouse, PlayerMouseView playerMouseView) {
        this.playerMouse = playerMouse;
        this.playerMouseView = playerMouseView;
        initialize();
    }

    public void leftClickInventory(Inventory inventory, InventoryView inventoryView, DeletedSlotView deletedSlotView){
        SlotView inventorySlotView = playerMouseView.getOnInventorySlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView);
        if(inventorySlotView != null){
            playerMouse.onInventorySlotLeftClickedAction(inventory.getSlot(inventorySlotView.getId()), inventory);
        }
        if (playerMouseView.getOnDeletedSlotClicked(playerMouse.getX(), playerMouse.getY(), deletedSlotView)){
            playerMouse.onDeletedSLotLeftClicked();
        }
        playerMouseView.getItemQuantityLabel().toFront();
        playerMouseView.getItemView().toFront();
        setItemView();
    }

    public void leftClickResultSlot(SlotView slotView, Slot slot, CraftInventory craftInventory){
        if(playerMouseView.getOnSlotClicked(playerMouse.getX(), playerMouse.getY(), slotView)){
            playerMouse.onResultSlotLeftClicked(slot, craftInventory);
        }
        setItemView();
    }

    public void changeCurrSlot(PlayerInventory playerInventory, PlayerInventoryView playerInventoryView) {
        SlotView inventorySlotView = playerMouseView.getOnInventorySlotClicked(playerMouse.getX(), playerMouse.getY(), playerInventoryView);
        if(inventorySlotView != null){
            playerInventory.setCurrSlotNumber(inventorySlotView.getId());
        }
    }

    public void leftPressed(Player player, Terrain terrain, InventoryView inventoryView){
        if(playerMouseView.getOnInventorySlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView) == null){
            playerMouse.playerLeftPressedAction(player, terrain);
            setItemView();
        }

    }

    public void inventoryclosed(Player player){
        if(playerMouse.getItem() != null){
            player.pick(playerMouse.getItem(), playerMouse.getCurrentItemQuantity());
            playerMouse.setCurrentItemQuantity(0);
            playerMouse.setMaxItemQuantity(0);
            playerMouse.setItem(null);
            setItemView();
        }

    }

    public void initialize(){

        playerMouseView.getItemQuantityLabel().layoutYProperty().bind(playerMouse.yProperty().add(20));
        playerMouseView.getItemQuantityLabel().layoutXProperty().bind(playerMouse.xProperty().add(20));
        playerMouseView.getItemView().layoutYProperty().bind(playerMouse.yProperty().add(10));
        playerMouseView.getItemView().layoutXProperty().bind(playerMouse.xProperty().add(10));
        playerMouseView.getItemNameLabel().layoutYProperty().bind(playerMouse.yProperty().add(10));
        playerMouseView.getItemNameLabel().layoutXProperty().bind(playerMouse.xProperty().add(10));
        setItemView();
    }



    public void setItemView(){
        try {
            playerMouseView.getItemView().setImage(playerMouse.getItem().getTypeItem().getImage());
            playerMouseView.displayItemQuantityLabel(true);
            playerMouseView.getItemQuantityLabel().setText(playerMouse.currentItemQuantityProperty().getValue().toString());
            playerMouseView.getItemQuantityLabel().toFront();
            playerMouseView.getItemView().toFront();
        }
        catch (NullPointerException e){
            playerMouseView.getItemView().setImage(null);
            playerMouseView.displayItemQuantityLabel(false);

        }

    }

    public void rightClickInventory(Inventory inventory, InventoryView inventoryView) {
        SlotView slotView = playerMouseView.getOnInventorySlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView);
        if(slotView != null){
            playerMouse.onInventorySlotRightClicked(inventory.getSlot(slotView.getId()), inventory);
        }
        setItemView();
    }

    public void rightClickResultSlot(SlotView slotView, Slot slot, CraftInventory craftInventory){
        if(playerMouseView.getOnSlotClicked(playerMouse.getX(), playerMouse.getY(), slotView)){
            playerMouse.onResultSlotRightClicked(slot, craftInventory);
        }
        setItemView();
    }
    public void rightClickDeletedSlotView(DeletedSlotView deletedSlotView){

        if(playerMouseView.getOnDeletedSlotClicked(playerMouse.getX(), playerMouse.getY(), deletedSlotView)){
                playerMouse.onDeletedSLotRightClicked();
        }
        setItemView();
    }

    public void displayItemName(Inventory inventory, InventoryView inventoryView){
        SlotView inventorySlotView = playerMouseView.getOnInventorySlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView);
        if(inventorySlotView != null){
            if(inventorySlotView.getItemView() != null && playerMouse.getItem() == null){
                playerMouseView.displayItemNameLabel(true);
                playerMouseView.getItemNameLabel().setText(inventory.getSlot(inventorySlotView.getId()).getItem().getTypeItem().getName());
                playerMouseView.getItemNameLabel().toFront();
            }
            else {
                playerMouseView.displayItemNameLabel(false);
            }
        }
        else  {
            playerMouseView.displayItemNameLabel(false);
        }

    }

}
