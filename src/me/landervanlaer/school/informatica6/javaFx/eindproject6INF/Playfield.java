package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Vector;
import me.landervanlaer.objects.Drawable;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

public class Playfield implements Drawable {
    private final int width;
    private final int height;

    public Playfield(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void stayInBoundaries(Coordinate pos) {
        if(pos.getX() < 0)
            pos.add(new Vector(pos, new Coordinate(0, pos.getY())));
        else if(pos.getX() > getWidth())
            pos.add(new Vector(pos, new Coordinate(getWidth(), pos.getY())));

        if(pos.getY() < 0)
            pos.add(new Vector(pos, new Coordinate(pos.getX(), 0)));
        else if(pos.getX() > getHeight())
            pos.add(new Vector(pos, new Coordinate(pos.getX(), getHeight())));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(new Color(0, 0, 0, .1));
        gc.setLineWidth(1);

        final Viewbox viewbox = Game.getInstance().getViewBox();
        for(int i = 0; i <= getWidth(); i += 40) {
            if(viewbox.getPos().getX() < i) {
                Coordinate top = new Coordinate(i, 0);
                Coordinate bottom = new Coordinate(i, getHeight());
                Draw.line(gc, top, bottom);
            }
        }
        for(int i = 0; i <= getHeight(); i += 40) {
            if(viewbox.getPos().getY() < i) {
                Coordinate left = new Coordinate(0, i);
                Coordinate right = new Coordinate(getWidth(), i);
                Draw.line(gc, left, right);
            }
        }
    }
}
