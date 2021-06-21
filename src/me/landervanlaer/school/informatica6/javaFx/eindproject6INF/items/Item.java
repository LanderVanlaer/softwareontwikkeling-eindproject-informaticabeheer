package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Number;
import me.landervanlaer.objects.Drawable;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.config.ConfigHandler;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Entity;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.TableItem;

public abstract class Item implements Drawable, TableItem {
    /**
     * The mass of the element
     */
    private final double mass;
    /**
     * The hero who owns the item
     */
    private Entity holder;

    private Coordinate pos;

    public Item(double mass, Coordinate pos) {
        if(mass < 0) mass = 0;
        this.mass = mass;
        this.pos = pos;
    }

    public Item(double mass) {
        this(mass, null);
    }

    @Override
    public String getName() {
        final String name = getClass().getSimpleName();
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public double getMass() {
        return mass;
    }

    @Override
    public String toString() {
        return getName();
    }

    public void drop() {
        if(getHolder() != null)
            drop(getHolder().getPos());
    }

    public void drop(Coordinate pos) {
        final int SPAWN_DEVIATION_PX = ConfigHandler.getInt("PlayField.SPAWN_DEVIATION_PX");

        if(pos == null)
            return;

        final int x = (int) pos.getX();
        final int y = (int) pos.getY();
        setPos(new Coordinate(
                Number.getRandom(x - SPAWN_DEVIATION_PX, x + SPAWN_DEVIATION_PX),
                Number.getRandom(y - SPAWN_DEVIATION_PX, y + SPAWN_DEVIATION_PX)));

        Game.getInstance().getItems().add(this);
    }

    public boolean canBeDrawn() {
        return getHolder() == null && getPos() != null;
    }

    public Coordinate getPos() {
        return pos;
    }

    public void setPos(Coordinate pos) {
        this.pos = pos;
        this.holder = null;
    }

    public Entity getHolder() {
        return holder;
    }

    public void setHolder(Entity holder) {
        this.holder = holder;
        this.pos = null;
    }
}
