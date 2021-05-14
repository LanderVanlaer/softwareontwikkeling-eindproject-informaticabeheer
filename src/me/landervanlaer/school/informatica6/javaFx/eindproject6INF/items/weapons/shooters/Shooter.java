package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.Weapon;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.Magazine;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

public abstract class Shooter<M extends Magazine> extends Weapon {
    private final BulletGenerator bulletGenerator;
    private M magazine;

    public Shooter(BulletGenerator bulletGenerator, double weight) {
        super(weight);
        this.bulletGenerator = bulletGenerator;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.setLineWidth(4);
        gc.setStroke(Color.BLACK);
        Draw.fillCircle(gc, getPos(), WIDTH);
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(M magazine) {
        this.magazine = magazine;
    }

    public BulletGenerator getBulletGenerator() {
        return bulletGenerator;
    }

    public Bullet getBullet(Coordinate heading) throws Magazine.EmptyMagazineException {
        try {
            return getBulletGenerator().generate(this, heading);
        } catch(BulletGenerator.FiringTooFast ignored) {
            return null;
        }
    }
}
