package controleur;

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

    public void leftClick(Inventory inventory, InventoryView inventoryView, DeletedSlotView deletedSlotView){
        SlotView slotView = playerMouseView.getOnSlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView);
        if(slotView != null){
            verifIsDisplay(inventory, inventoryView, slotView);
            playerMouse.onSlotLeftClickedAction(inventory.getSlot(slotView.getId()), inventory);
        }
        if (playerMouseView.getOnDeletedSlotClicked(playerMouse.getX(), playerMouse.getY(), deletedSlotView)){
            playerMouse.onDeletedSLotLeftClicked();
        }
        playerMouseView.getItemQuantityLabel().toFront();
        playerMouseView.getItemView().toFront();
        setItemView();
    }

    private void verifIsDisplay(Inventory inventory, InventoryView inventoryView, SlotView slotView) {
        if(!inventoryView.isDisplay()){
            if(inventory instanceof PlayerInventory)
            ((PlayerInventory) inventory).setCurrSlotNumber(slotView.getId());
        }
    }

    public void leftPressed(Player player, Terrain terrain, InventoryView inventoryView){
        if(playerMouseView.getOnSlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView) == null){
            playerMouse.playerLeftPressedAction(player, terrain);
            setItemView();
        }

    }

    public void initialize(){

        playerMouseView.getItemQuantityLabel().layoutYProperty().bind(playerMouse.yProperty().add(20));
        playerMouseView.getItemQuantityLabel().layoutXProperty().bind(playerMouse.xProperty().add(20));
        playerMouseView.getItemView().layoutYProperty().bind(playerMouse.yProperty().add(10));
        playerMouseView.getItemView().layoutXProperty().bind(playerMouse.xProperty().add(10));
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

    public void rightClick(Inventory inventory, InventoryView inventoryView) {
        SlotView slotView = playerMouseView.getOnSlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView);
        if(slotView != null){
            verifIsDisplay(inventory,inventoryView,slotView);
            playerMouse.onSlotRightClicked(inventory.getSlot(slotView.getId()), inventory);
        }
        setItemView();
    }
    public void rightClickDeletedSlotView(DeletedSlotView deletedSlotView){

        if(playerMouseView.getOnDeletedSlotClicked(playerMouse.getX(), playerMouse.getY(), deletedSlotView)){

            if(playerMouseView.getOnDeletedSlotClicked(playerMouse.getX(), playerMouse.getY(), deletedSlotView)){
                playerMouse.onDeletedSLotRightClicked();
            }
        }
        setItemView();
    }

}
