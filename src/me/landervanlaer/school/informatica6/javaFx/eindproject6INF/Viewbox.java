package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Mover;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;

public class Viewbox {
    public final int DEVIATION = 10;
    private int width;
    private int height;
    private Coordinate pos;

    public Viewbox(int width, int height) {
        this(width, height, new Coordinate(0, 0));
    }

    public Viewbox(int width, int height, Coordinate coordinate) {
        this.width = width;
        this.height = height;
        this.pos = coordinate;
    }

    public boolean isVisible(Mover mover) {
        return containsCoordinate(mover.getPos());
    }

    public boolean isVisible(Item item) {
        return item.canBeDrawn() && containsCoordinate(item.getPos());
    }

    public boolean containsCoordinate(Coordinate coordinate) {
        final double left = getPos().getX() - DEVIATION;
        final double right = getPos().getX() + DEVIATION;
        final double top = getPos().getY() - DEVIATION;
        final double bottom = getPos().getY() + DEVIATION;

        return left < coordinate.getX() && coordinate.getX() < right
                && top < coordinate.getY() && coordinate.getY() < bottom;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Coordinate getPos() {
        return pos;
    }

    public void setPos(Coordinate pos) {
        this.pos = pos;
    }
}
