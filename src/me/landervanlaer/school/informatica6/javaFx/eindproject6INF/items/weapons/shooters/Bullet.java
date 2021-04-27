package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters;

import javafx.scene.canvas.GraphicsContext;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Mover;
import me.landervanlaer.math.Vector;

public class Bullet extends Mover {
    public static final double MASS = 1;
    public final int damage;

    public Bullet(int damage, Coordinate pos, Vector vel) {
        super(pos, vel, Bullet.MASS);
        this.damage = damage;
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO: 21/04/2021 bullet draw
    }

    public int getDamage() {
        return damage;
    }

    public boolean isMoving() {
        return !getVel().isZero();
    }

    public void stop() {
        getAcc().zero();
        getVel().zero();
    }
}
