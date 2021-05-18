package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx;

import javafx.scene.canvas.GraphicsContext;
import me.landervanlaer.math.Angle;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;

public abstract class Draw {
    public static void clear(GraphicsContext context) {
        context.clearRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
        context.clearRect(0, 0, 400, 400);
    }

    public static void fillCircle(GraphicsContext context, Coordinate pos, double width) {
        fillOval(context, pos, width, width);
    }

    public static void strokeCircle(GraphicsContext context, Coordinate pos, double width) {
        strokeOval(context, pos, width, width);
    }

    public static Coordinate relativeCoordinate(Coordinate c) {
        final Coordinate pos = Game.getInstance().getViewBox().getPos();
        return new Coordinate(c.getX() - pos.getX(), c.getY() - pos.getY());
    }

    public static void fillOval(GraphicsContext context, Coordinate pos, double width, double height) {
        final Coordinate coordinate = relativeCoordinate(pos);
        coordinate.setX(coordinate.getX() - width / 2D);
        coordinate.setY(coordinate.getY() - height / 2D);
        context.fillOval(coordinate.getX(), coordinate.getY(), width, height);
    }

    public static void strokeOval(GraphicsContext context, Coordinate pos, double width, double height) {
        final Coordinate coordinate = relativeCoordinate(pos);
        coordinate.setX(coordinate.getX() - width / 2D);
        coordinate.setY(coordinate.getY() - height / 2D);
        context.strokeOval(coordinate.getX(), coordinate.getY(), width, height);
    }

    public static void fillTriangle(GraphicsContext context, Coordinate pos, Angle angle, double r) {
        final Coordinate c = relativeCoordinate(pos);

        final Angle left = new Angle(angle.getRadians());
        left.setDegrees(left.getDegrees(false) + 120);

        final Angle right = new Angle(angle.getRadians());
        right.setDegrees(right.getDegrees(false) - 120);


        Coordinate p1 = new Coordinate(c.getX() + Math.cos(angle.getRadians()) * r, c.getY() + Math.sin(angle.getRadians()) * r);
        Coordinate p2 = new Coordinate(c.getX() + Math.cos(right.getRadians()) * r / 2, c.getY() + Math.sin(right.getRadians()) * r / 2);
        Coordinate p3 = new Coordinate(c.getX() + Math.cos(left.getRadians()) * r / 2, c.getY() + Math.sin(left.getRadians()) * r / 2);
        context.fillPolygon(new double[]{p1.getX(), p2.getX(), p3.getX()}, new double[]{p1.getY(), p2.getY(), p3.getY()}, 3);
    }

    public static void line(GraphicsContext context, Coordinate pos1, Coordinate pos2) {
        line(context, pos1, pos2, false, false);
    }

    public static void text(GraphicsContext context, String text, Coordinate pos) {
        context.fillText(text, pos.getX(), pos.getY());
    }

    public static void line(GraphicsContext context, Coordinate pos1, Coordinate pos2, boolean pos1RelativeToCanvas, boolean pos2RelativeToCanvas) {
        final Coordinate c1 = pos1RelativeToCanvas ? pos1 : relativeCoordinate(pos1);
        final Coordinate c2 = pos2RelativeToCanvas ? pos2 : relativeCoordinate(pos2);

        context.beginPath();
        context.moveTo(c1.getX(), c1.getY());
        context.lineTo(c2.getX(), c2.getY());
        context.stroke();
        context.closePath();
    }
}
