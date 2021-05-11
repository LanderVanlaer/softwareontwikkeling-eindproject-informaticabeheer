package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Vector;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.AnchorPoint;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Viewbox;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Armor;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Container;

import java.util.EnumMap;

public class Player extends Entity {
    public Player(int maxHp, Coordinate pos, double mass) {
        super(maxHp, pos, mass);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO: 27/04/2021  
    }

    @Override
    public void useAttack() {
        // TODO: 27/04/2021
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

        final EnumMap<KeyCode, Boolean> keys = Container.getInstance().getKeys();

        Vector vector = new Vector();
        keys.forEach((keyCode, aBoolean) -> {
            switch(keyCode) {
                case LEFT -> vector.add(new Vector(-MOVEMENT_SPEED, 0));
                case RIGHT -> vector.add(new Vector(MOVEMENT_SPEED, 0));
                case UP -> vector.add(new Vector(0, -MOVEMENT_SPEED));
                case DOWN -> vector.add(new Vector(0, MOVEMENT_SPEED));
            }
        });
        if(!vector.isZero())
            vector.setMag(Entity.MOVEMENT_SPEED);
        getPos().add(vector);

        final Viewbox viewbox = Game.getInstance().getViewBox();
        viewbox.setPos(new Coordinate(getPos().getX() - viewbox.getWidth() / 2D, getPos().getY() - viewbox.getHeight() / 2D));
    }
}