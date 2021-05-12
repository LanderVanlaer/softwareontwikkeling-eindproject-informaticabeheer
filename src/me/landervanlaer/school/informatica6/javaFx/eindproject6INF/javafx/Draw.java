package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Angle;
import me.landervanlaer.math.Coordinate;
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
        return new Coordinate(c.getX() - pos.getX(), c.getY() - pos.getY());
    }

    public static void fillOval(GraphicsContext context, Coordinate pos, double width, double height) {
        applySettings(context);
        final Coordinate coordinate = relativeCoordinate(pos);
        coordinate.setX(coordinate.getX() - width / 2D);
        coordinate.setY(coordinate.getY() - height / 2D);
        context.fillOval(coordinate.getX(), coordinate.getY(), width, height);
    }

    public static void fillTriangle(GraphicsContext context, Coordinate pos, Angle angle, double r) {
        applySettings(context);
        final Coordinate c = relativeCoordinate(pos);

        final Angle left = new Angle(angle.getRadians());
        left.setDegrees(left.getDegrees(true) + 120);

        final Angle right = new Angle(angle.getRadians());
        right.setDegrees(right.getDegrees(true) - 120);


        Coordinate p1 = new Coordinate(c.getX() + Math.cos(angle.getRadians()) * r, c.getY() + Math.sin(angle.getRadians()) * r);
        Coordinate p2 = new Coordinate(c.getX() + Math.cos(right.getRadians()) * r / 2, c.getY() + Math.sin(right.getRadians()) * r / 2);
        Coordinate p3 = new Coordinate(c.getX() + Math.cos(left.getRadians()) * r / 2, c.getY() + Math.sin(left.getRadians()) * r / 2);
        context.fillPolygon(new double[]{p1.getX(), p2.getX(), p3.getX()}, new double[]{p1.getY(), p2.getY(), p3.getY()}, 3);
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
