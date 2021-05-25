package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Mover;
import me.landervanlaer.math.Vector;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.GameLoop;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

public class Bullet extends Mover {
    public static final long MAX_LIFE_TIME = 10L * GameLoop.ONE_SECOND_NANO;
    public static final double MASS = 1;
    private final int damage;
    private final long startTime;

    public Bullet(int damage, Coordinate pos, Vector vel) {
        super(pos, vel, Bullet.MASS);
        this.damage = damage;
        this.startTime = System.nanoTime();
    }

    public static double getMASS() {
        return MASS;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Draw.line(gc, getPos(), new Coordinate(getPos().getX() + getVel().getX(), getPos().getY() + getVel().getY()));
    }

    @Override
    public void update() {
        super.update();

        if(getStartTime() + MAX_LIFE_TIME < System.nanoTime()) {
            stop();
        }
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

    public long getStartTime() {
        return startTime;
    }
}
