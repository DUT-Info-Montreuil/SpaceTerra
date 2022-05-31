package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private ArrayList<Item> items;

    private HashMap<String, Integer> itemsQuantities;
    private int currSlot;
    private int maxInventorySize;
    private int currInventorySize;

    public ArrayList<Item> getItems() {
        return items;
    }

    public Inventory() {
        this.items = new ArrayList<>(10);
        this.itemsQuantities = new HashMap<>(10);
        this.currSlot = 0;
        this.maxInventorySize = 10;
        this.currInventorySize = 0;
        for(int i = 0; i < maxInventorySize; i++){
            items.add(null);
        }
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
            while (i < items.size() && items.get(i) != null && itemsQuantities.get(items.get(i).getClass().toString()) > 0) {
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
        Item item = null;
        try {
            if(itemsQuantities.get(items.get(currSlot).getClass().toString()) > 1){
                item = items.get(currSlot);
                itemsQuantities.replace(items.get(currSlot).getClass().toString(), itemsQuantities.get(items.get(currSlot).getClass().toString()) - 1);
                return item;
            }
            else {
                item = items.remove(currSlot);
                items.add(currSlot, null);
                itemsQuantities.remove(item.getClass().toString());
                currInventorySize--;
                return item;
            }

        } catch (IndexOutOfBoundsException | NullPointerException e){
            System.out.println("slot vide !");
            return null;
        }
    }

    public void addIntoSlot(Item item){
        if(itemsQuantities.containsKey(item.getClass().toString())){
            if(itemsQuantities.get(item.getClass().toString()) < item.getMaxQuantity()){
                itemsQuantities.replace(item.getClass().toString(), itemsQuantities.get(item.getClass().toString()) + 1);
            }
            else{
                if(!isInventoryFull()){
                    items.set(getNextEmptySlot(), item);
                    currInventorySize++;
                    itemsQuantities.put(item.getClass().toString(), 1);
                    System.out.println(itemsQuantities);
                }
                else{
                    System.out.println("inventaire plein !");
                }
            }
        }
        else {
            if(!isInventoryFull()){
                items.set(getNextEmptySlot(), item);
                currInventorySize++;
                itemsQuantities.put(item.getClass().toString(), 1);
                System.out.println(itemsQuantities);
            }
            else{
                System.out.println("inventaire plein !");
            }
        }

    }
    public HashMap<String, Integer> getItemsQuantities() {
        return itemsQuantities;
    }

}
