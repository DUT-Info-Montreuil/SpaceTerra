package modele;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    private int currSlot;
    private int maxInventorySize;
    private int currInventorySize;

    public Inventory() {
        this.items = new ArrayList<>(10);
        this.currSlot = 0;
        this.maxInventorySize = 10;
        this.currInventorySize = 0;
    }


    public int getCurrSlot() {
        return currSlot;
    }

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public Item getCurrItem(){
        try{
            return items.get(currSlot);
        } catch (Exception e){
            return null;
        }
    }

    public Item getItemFromSlot(int numSlot){
        try{
            return items.get(numSlot);
        } catch (Exception e){
            return null;
        }
    }

    public int getNextEmptySlot(){
            int i = 0;
            while (i < items.size() && items.get(i) != null) {
                i++;
            }
            return i;

    }
    public void setMaxInventorySize(int maxInventorySize){
        this.maxInventorySize = maxInventorySize;
    }

    public void setCurrSlot(int currSlot){
        this.currSlot = currSlot;
    }

    public void incrementSlot(){
        if(this.currSlot < this.maxInventorySize-1){
            currSlot++;
        }
    }
    public void decrementSlot(){
        if(this.currSlot > 0){
            currSlot--;
        }
    }
    public boolean isInventoryFull(){
        if(currInventorySize >= maxInventorySize){
            return true;
        }
        return false;
    }
    public Item removeFromSlot(){
        try {
            items.get(currSlot);
            Item item = items.remove(currSlot);
            items.add(currSlot, null);
            currInventorySize--;
            return item;
        } catch (IndexOutOfBoundsException e){
            System.out.println("slot vide !");
            return null;
        }
    }

    public void addIntoSlot(Item item){
        if(!isInventoryFull()){
            int i = getNextEmptySlot();
            items.add(i, item);
            currInventorySize++;
        }
        else{
            System.out.println("inventaire plein !");
        }
    }

}
