package controleur;

import modele.*;
import vue.DeletedSlotView;
import vue.InventoryView;
import vue.PlayerMouseView;
import vue.SlotView;

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
            if(!inventoryView.isShow()){
                inventory.setCurrSlotNumber(slotView.getId());
            }
            playerMouse.onSlotLeftClickedAction(inventory.getSlot(slotView.getId()), inventory);
        }
        if (playerMouseView.getOnDeletedSlotClicked(playerMouse.getX(), playerMouse.getY(), deletedSlotView)){
            playerMouse.onDeletedSLotLeftClicked();
        }
        playerMouseView.getItemQuantityLabel().toFront();
        playerMouseView.getItemView().toFront();
        setItemView();
    }

    public void leftPressed(Player player, Terrain terrain, InventoryView inventoryView){
        if(playerMouseView.getOnSlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView) == null){
            playerMouse.playerLeftPressedAction(player, terrain);
            setItemView();
            playerMouseView.getItemView().toFront();
        }

    }

    public void initialize(){

        playerMouseView.getItemQuantityLabel().layoutYProperty().bind(playerMouse.yProperty().add(20));
        playerMouseView.getItemQuantityLabel().layoutXProperty().bind(playerMouse.xProperty().add(20));
        playerMouseView.getItemView().layoutYProperty().bind(playerMouse.yProperty().add(10));
        playerMouseView.getItemView().layoutXProperty().bind(playerMouse.xProperty().add(10));
        playerMouseView.getItemQuantityLabel().toFront();
        playerMouseView.getItemView().toFront();
        setItemView();
    }



    public void setItemView(){
        try {
            playerMouseView.getItemView().setImage(playerMouse.getItem().getTypeItem().getImage());
            playerMouseView.displayItemQuantityLabel(true);
            playerMouseView.getItemQuantityLabel().setText(playerMouse.currentItemQuantityProperty().getValue().toString());
        }
        catch (NullPointerException e){
            playerMouseView.getItemView().setImage(null);
            playerMouseView.displayItemQuantityLabel(false);

        }

    }

    public void rightClick(Inventory inventory, InventoryView inventoryView, DeletedSlotView deletedSlotView) {
        SlotView slotView = playerMouseView.getOnSlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView);
        if(slotView != null){
            if(!inventoryView.isShow()){
                inventory.setCurrSlotNumber(slotView.getId());
            }
            playerMouse.onSlotRightClicked(inventory.getSlot(slotView.getId()), inventory);
        }
        if(playerMouseView.getOnDeletedSlotClicked(playerMouse.getX(), playerMouse.getY(), deletedSlotView)){
            playerMouse.onDeletedSLotRightClicked();
        }
        playerMouseView.getItemQuantityLabel().toFront();
        playerMouseView.getItemView().toFront();
        setItemView();
    }
}
