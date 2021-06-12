package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Mover;
import me.landervanlaer.math.Vector;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.GameLoop;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Bot;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Entity;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

public class Bullet extends Mover {
    public static final long MAX_LIFE_TIME = 10L * GameLoop.ONE_SECOND_NANO;
    public static final double MASS = 1;
    private final int damage;
    private final long startTime;
    private boolean delete = false;

    public Bullet(int damage, Coordinate pos, Vector vel) {
        super(pos, vel, Bullet.MASS);
        this.damage = damage;
        this.startTime = System.nanoTime();
    }

    public static double getMASS() {
        return MASS;
    }

    /**
     * @see <a href="https://math.stackexchange.com/a/2035466">https://math.stackexchange.com/a/2035466</a>
     */
    public static boolean checkCollision(Coordinate prevPos, Coordinate currPos, Bot bot) {
        double ax = prevPos.getX() - bot.getPos().getX();
        double ay = prevPos.getY() - bot.getPos().getY();

        double bx = currPos.getX() - bot.getPos().getX();
        double by = currPos.getY() - bot.getPos().getY();

        double a = Math.pow(bx - ax, 2) + Math.pow(by - ay, 2);
        double b = 2 * (ax * (bx - ax) + ay * (by - ay));
        double c = Math.pow(ax, 2) + Math.pow(ay, 2) - Math.pow(bot.getRadius(), 2);

        double disc = Math.pow(b, 2) - 4 * a * c;

        if(disc <= 0) return false;

        double sqrtdisc = Math.sqrt(disc);
        double t1 = (-b + sqrtdisc) / (2 * a);
        double t2 = (-b - sqrtdisc) / (2 * a);

        return 0 < t1 && t1 < 1 || 0 < t2 && t2 < 1;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Draw.line(gc, getPos(), new Coordinate(getPos().getX() + getVel().getX(), getPos().getY() + getVel().getY()));
    }

    @Override
    public void update() {
        if(!Game.getInstance().getPlayField().isBetweenBorders(getPos())) {
            stop();
            return;
        }

        final Coordinate prevPos = new Coordinate(getPos());
        super.update();

        for(Entity bot : Game.getInstance().getEntities()) {
            if(bot instanceof Bot)
                if(checkCollision(prevPos, getPos(), (Bot) bot)) {
                    bot.hit(this);
                }
        }

        if(getStartTime() + MAX_LIFE_TIME < System.nanoTime()) {
            stop();
        }
    }

    public int getDamage() {
        return damage;
    }

    public void stop() {
        setDelete(true);
    }

    public long getStartTime() {
        return startTime;
    }

    public boolean isDelete() {
        return delete;
    }

    private void setDelete(boolean delete) {
        this.delete = delete;
    }
}
