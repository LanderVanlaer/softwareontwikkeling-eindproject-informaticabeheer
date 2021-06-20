package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

import java.util.LinkedList;
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
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.PURPLE);
        gc.setLineWidth(4);
        gc.setStroke(Color.BLACK);
        Draw.fillCircle(gc, getPos(), WIDTH);
    }

    @Override
    public double getMass() {
        return super.getMass() + getMassInBackpack();
    }

    @Override
    public String toString() {
        return "%s (%s)".formatted(getName(), getMaxMass());
    }

    @Override
    public void drop(Coordinate pos) {
        super.drop(pos);
        while(getItems().size() > 0) {
            dropItem(0);
        }
    }

    @Override
    public String getExtra() {
        return getMassInBackpack() + " / " + getMaxMass();
    }

    public void dropItem(int i) {
        if(!isValidIndexOfItems(i))
            throw new IndexOutOfBoundsException();

        final Item item = getItems().remove(i);
        if(getHolder() == null)
            item.drop(getPos());
        else
            item.drop(getHolder().getPos());
    }

    public void dropItem(Item item) {
        dropItem(getItems().indexOf(item));
    }

    public double getMassInBackpack() {
        if(getItems().size() > 0)
            return getItems().stream().mapToDouble(Item::getMass).sum();
        return 0;
    }

    public boolean canAddItem(Item item) {
        return getMassInBackpack() + item.getMass() <= getMaxMass();
    }

    public Item takeItem(int i) throws IndexOutOfBoundsException {
        if(!isValidIndexOfItems(i)) throw new IndexOutOfBoundsException();
        return getItems().remove(i);
    }

    public void removeItem(Item item) {
        getItems().remove(item);
    }

    public Item removeItem(int i) {
        return getItems().remove(i);
    }

    public Item getItem(int i) throws IndexOutOfBoundsException {
        if(!isValidIndexOfItems(i)) throw new IndexOutOfBoundsException();
        return getItems().get(i);
    }

    public void addItem(Item item) throws NullPointerException, IllegalArgumentException {
        if(item == null)
            throw new NullPointerException();
        if(!canAddItem(item))
            throw new MaxMassExceeded();
        if(item == this)
            throw new IllegalArgumentException("Cannot put the backpack inside itself");

        item.setHolder(getHolder());
        getItems().add(item);
    }

    public boolean isValidIndexOfItems(int i) {
        return i >= 0 && i < getItems().size();
    }

    public double getMaxMass() {
        return maxMass;
    }

    public List<Item> getItems() {
        return items;
    }

    public static class MaxMassExceeded extends IllegalArgumentException {
    }
}
