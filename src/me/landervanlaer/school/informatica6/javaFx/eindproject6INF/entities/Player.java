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
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Backpack;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.Weapon;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Container;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Player extends Entity {
    private static final double MAX_SURROUNDING_RADIUS = 100;

    public Player(int maxHp, Coordinate pos, double mass) {
        super(maxHp, pos, mass);
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setLineWidth(1);
        gc.setStroke(new Color(0, 0, 0, 0.1));
        Draw.strokeCircle(gc, getPos(), MAX_SURROUNDING_RADIUS * 2);

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

    public Item getHand() {
        return getAnchorPoints().get(AnchorPoint.HAND);
    }

    public Item setHand(Weapon weapon) {
        return getAnchorPoints().put(AnchorPoint.HAND, weapon);
    }

    public Armor getArmor() {
        final Item item = getAnchorPoints().get(AnchorPoint.BODY);
        if(item instanceof Armor)
            return (Armor) item;
        return null;
    }

    public Item setArmor(Armor armor) {
        armor.setHolder(this);
        return getAnchorPoints().put(AnchorPoint.BODY, armor);
    }

    @Override
    public void update() {
        applyForce(Game.getFriction(this));

        final List<KeyCode> keys = Container.getInstance().getKeys();

        Vector vector = new Vector();
        for(KeyCode key : keys) {
            switch(key) {
                case Z, W -> {
                    vector.add(new Vector(0, MOVEMENT_SPEED));
                    vector.setAngle(getAngle());
                }
                case S -> {
                    vector.add(new Vector(0, MOVEMENT_SPEED));
                    vector.setAngle(new Angle(getAngle().getRadians() + Math.PI));
                }
                case A, Q -> getAngle().setDegrees(getAngle().getDegrees() - Entity.ROTATION_SPEED);
                case D -> getAngle().setDegrees(getAngle().getDegrees() + Entity.ROTATION_SPEED);
            }
        }
        if(!vector.isZero())
            vector.setMag(Entity.MOVEMENT_SPEED);
        applyForce(vector);

        final Viewbox viewbox = Game.getInstance().getViewBox();
        viewbox.setPos(new Coordinate(getPos().getX() - viewbox.getWidth() / 2D, getPos().getY() - viewbox.getHeight() / 2D));

        super.update();
    }

    @Override
    public void useAttack() {
        // TODO: 27/04/2021
    }

    public void changeBackpack(Backpack backpack) {
        if(getBackpack() == backpack) return;

        final Backpack prevBackpack = (Backpack) setBackPack(backpack);

        final int size = prevBackpack.getItems().size();
        for(int i = 0; i < size; i++) {
            final Item item = prevBackpack.removeItem(0);
            if(item == backpack) continue;

            try {
                backpack.addItem(item);
            } catch(Backpack.MaxMassExceeded ignored) {
                item.drop();
            }
        }

        try {
            backpack.addItem(prevBackpack);
        } catch(Backpack.MaxMassExceeded ignored) {
            prevBackpack.drop();
        }
    }

    public Item setBackPack(Backpack backpack) {
        backpack.setHolder(this);
        return getAnchorPoints().put(AnchorPoint.BACK, backpack);
    }

    public Backpack getBackpack() {
        final Item item = getAnchorPoints().get(AnchorPoint.BACK);
        return item instanceof Backpack ? (Backpack) item : null;
    }

    public List<Item> getAllSurroundingItems() {
        return Game.getInstance().getItems().stream().filter(item ->
                item.getPos() != null
                        && getPos().getX() - MAX_SURROUNDING_RADIUS < getPos().getX()
                        && getPos().getX() + MAX_SURROUNDING_RADIUS > getPos().getX()
                        && getPos().getY() - MAX_SURROUNDING_RADIUS < getPos().getY()
                        && getPos().getY() + MAX_SURROUNDING_RADIUS > getPos().getY()
                        && item.getPos().getDistanceBetween(getPos()) < MAX_SURROUNDING_RADIUS).collect(Collectors.toCollection(LinkedList::new));
    }
}
