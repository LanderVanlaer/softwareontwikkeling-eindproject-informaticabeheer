package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Number;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;

public abstract class Draw {
    private static Color fill = Color.GRAY;
    private static Color stroke = Color.BLACK;
    private static double lineWidth = 1;

    public static void clear(GraphicsContext context) {
        context.clearRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
        context.clearRect(0, 0, 400, 400);
    }

    public static void applySettings(GraphicsContext context) {
        context.setFill(getFill());
        context.setStroke(getStroke());
        context.setLineWidth(getLineWidth());
    }

    public static void fillCircle(GraphicsContext context, Coordinate pos, double width) {
        fillOval(context, pos, width, width);
    }

    public static Coordinate relativeCoordinate(Coordinate c) {
        final Coordinate pos = Game.getInstance().getViewBox().getPos();
        return new Coordinate(Number.constrain(c.getX() - pos.getX(), 0, 450), Number.constrain(c.getY() - pos.getY(), 0, 450));
    }

    public static void fillOval(GraphicsContext context, Coordinate pos, double width, double height) {
        applySettings(context);
        final Coordinate coordinate = relativeCoordinate(pos);
        context.fillOval(coordinate.getX(), coordinate.getY(), width, height);
    }

    public static void line(GraphicsContext context, Coordinate pos1, Coordinate pos2) {
        applySettings(context);
        final Coordinate c1 = relativeCoordinate(pos1);
        final Coordinate c2 = relativeCoordinate(pos2);
        context.beginPath();
        context.moveTo(c1.getX(), c1.getY());
        context.lineTo(c2.getX(), c2.getY());
        context.stroke();
        context.closePath();
    }

    public static Color getFill() {
        return fill;
    }

    public static void setFill(Color fill) {
        if(fill != null)
            Draw.fill = fill;
    }

    public static Color getStroke() {
        return stroke;
    }

    public static void setStroke(Color stroke) {
        if(stroke != null)
            Draw.stroke = stroke;
    }

    public static double getLineWidth() {
        return lineWidth;
    }

    public static void setLineWidth(double lineWidth) {
        if(lineWidth > 0)
            Draw.lineWidth = lineWidth;
    }
}
