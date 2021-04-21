package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import javafx.scene.canvas.GraphicsContext;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.objects.Drawable;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Entity;

public abstract class Item implements Drawable {
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
    public void draw(GraphicsContext gc) {
        // TODO: 21/04/2021 draw item
    }

    public Coordinate getPos() {
        return pos;
    }

    public void setPos(Coordinate pos) {
        this.pos = pos;
    }

    public double getMass() {
        return mass;
    }

    public Entity getHolder() {
        return holder;
    }

    public void setHolder(Entity holder) {
        this.holder = holder;
    }
}
