package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Angle;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Vector;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.AnchorPoint;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Viewbox;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Armor;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Container;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

import java.util.List;

public class Player extends Entity {
    public Player(int maxHp, Coordinate pos, double mass) {
        super(maxHp, pos, mass);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(2);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.DARKGRAY);
//        Draw.fillCircle(gc, getPos(), getMass() / 5);
        Draw.fillTriangle(gc, getPos(), getAngle(), getMass() / 2D);


        gc.setLineWidth(8);
        final Vector v = new Vector(Draw.relativeCoordinate(getPos()), Container.getInstance().getCursor());
        v.setMag(25);

        final Coordinate to = new Coordinate(getPos());
        to.add(v);

        Draw.line(gc, getPos(), to, false, false);

        gc.setLineWidth(2);
        gc.setStroke(new Color(0, 0, 0, 0.2));
        gc.setLineDashes(10);
        Draw.line(gc, getPos(), Container.getInstance().getCursor(), false, true);
        gc.setLineDashes(0);
    }

    public Armor getArmor() {
        final Item item = getAnchorPoints().get(AnchorPoint.BODY);
        if(item instanceof Armor)
            return (Armor) item;
        return null;
    }

    @Override
    public void update() {
        super.update();
        applyForce(Game.getFriction(this));

        final List<KeyCode> keys = Container.getInstance().getKeys();

        Vector vector = new Vector();
        for(KeyCode key : keys) {
            switch(key) {
                case UP -> {
                    vector.add(new Vector(0, MOVEMENT_SPEED));
                    vector.setAngle(getAngle());
                }
                case DOWN -> {
                    vector.add(new Vector(0, MOVEMENT_SPEED));
                    vector.setAngle(new Angle(getAngle().getRadians() + Math.PI));
                }
                case LEFT -> getAngle().setDegrees(getAngle().getDegrees() - Entity.ROTATION_SPEED);
                case RIGHT -> getAngle().setDegrees(getAngle().getDegrees() + Entity.ROTATION_SPEED);
            }
        }
        if(!vector.isZero())
            vector.setMag(Entity.MOVEMENT_SPEED);
        applyForce(vector);

        final Viewbox viewbox = Game.getInstance().getViewBox();
        viewbox.setPos(new Coordinate(getPos().getX() - viewbox.getWidth() / 2D, getPos().getY() - viewbox.getHeight() / 2D));
    }

    @Override
    public void useAttack() {
        // TODO: 27/04/2021
    }
}
