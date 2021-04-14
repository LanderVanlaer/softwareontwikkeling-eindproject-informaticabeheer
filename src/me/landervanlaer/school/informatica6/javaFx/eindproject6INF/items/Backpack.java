package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import me.landervanlaer.util.lists.LinkedList;

import java.util.List;

public class Backpack extends Item {
    private final List<Item> items;
    private final double maxWeight;

    public Backpack(double maxWeight, double weight, Item... items) {
        this(maxWeight, weight);
        for(Item item : items) if(item != null) this.items.add(item);
    }

    public Backpack(double maxWeight, double weight) {
        super(weight);
        this.items = new LinkedList<>();
        this.maxWeight = maxWeight <= 1 ? 1 : maxWeight;
    }

    @Override
    protected boolean canHaveIdentification(long identification) {
        return identification > 0;
    }

    @Override
    public double getWeight() {
        return super.getWeight() + getWeightInBackpack();
    }

    public double getWeightInBackpack() {
        return getItems().stream().mapToDouble(Item::getWeight).sum();
    }

    public boolean canAddItem(Item item) {
        return getWeightInBackpack() + item.getWeight() < getMaxWeight();
    }

    public Item takeItem(int i) throws IndexOutOfBoundsException {
        if(!isValidIndexOfItems(i)) throw new IndexOutOfBoundsException();
        return getItems().remove(i);
    }

    public Item getItem(int i) throws IndexOutOfBoundsException {
        if(!isValidIndexOfItems(i)) throw new IndexOutOfBoundsException();
        return getItems().get(i);
    }

    public void addItem(Item item) throws NullPointerException, MaxWeightExceeded {
        if(item == null)
            throw new NullPointerException();
        if(!canAddItem(item))
            throw new MaxWeightExceeded();
        getItems().add(item);
    }

    public Item getItemByIdentification(long id) throws ItemNotFound {
        for(Item item : getItems())
            if(item.getIdentification() == id)
                return item;
        throw new ItemNotFound();
    }

    public boolean hasItemWithIdentification(long id) {
        for(Item item : getItems()) if(item.getIdentification() == id) return true;
        return false;
    }

    public boolean isValidIndexOfItems(int i) {
        return i >= 0 && i < getItems().size();
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    private List<Item> getItems() {
        return items;
    }

    public static class ItemNotFound extends IllegalArgumentException {
    }


    public static class MaxWeightExceeded extends IllegalArgumentException {
    }
}
