package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import me.landervanlaer.util.lists.LinkedList;

import java.util.List;

public class Backpack extends Item {
    private final List<Item> items;
    private final double maxMass;

    public Backpack(double maxMass, double weight, Item... items) {
        this(maxMass, weight);
        for(Item item : items) if(item != null) this.items.add(item);
    }

    public Backpack(double maxMass, double weight) {
        super(weight);
        this.items = new LinkedList<>();
        this.maxMass = maxMass <= 1 ? 1 : maxMass;
    }

    @Override
    public double getMass() {
        return super.getMass() + getMassInBackpack();
    }

    public double getMassInBackpack() {
        return getItems().stream().mapToDouble(Item::getMass).sum();
    }

    public boolean canAddItem(Item item) {
        return getMassInBackpack() + item.getMass() < getMaxMass();
    }

    public Item takeItem(int i) throws IndexOutOfBoundsException {
        if(!isValidIndexOfItems(i)) throw new IndexOutOfBoundsException();
        return getItems().remove(i);
    }

    public Item getItem(int i) throws IndexOutOfBoundsException {
        if(!isValidIndexOfItems(i)) throw new IndexOutOfBoundsException();
        return getItems().get(i);
    }

    public void addItem(Item item) throws NullPointerException, MaxMassExceeded {
        if(item == null)
            throw new NullPointerException();
        if(!canAddItem(item))
            throw new MaxMassExceeded();
        getItems().add(item);
    }

    public boolean isValidIndexOfItems(int i) {
        return i >= 0 && i < getItems().size();
    }

    public double getMaxMass() {
        return maxMass;
    }

    private List<Item> getItems() {
        return items;
    }

    public static class MaxMassExceeded extends IllegalArgumentException {
    }
}
