package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines;

import com.sun.jdi.InternalException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Number;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

import java.text.MessageFormat;

public abstract class Magazine extends Item {
    private int amount;

    public Magazine() {
        super(1);

        if(getMax() <= 0)
            throw new InternalException();

        this.amount = getMax();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = Number.constrain(amount, 0, getMax());
    }

    public double getPercentageLoaded() {
        return (double) getAmount() / (double) getMax();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.LIGHTGRAY);
        gc.setLineWidth(4);
        gc.setStroke(Color.BLACK);
        Draw.fillCircle(gc, getPos(), WIDTH);
    }

    @Override
    public String getExtra() {
        return MessageFormat.format("{0} / {1}", getAmount(), getMax());
    }

    public void fireBullet() {
        setAmount(getAmount() - 1);
    }

    public boolean isEmpty() {
        return getAmount() <= 0;
    }

    public abstract int getMax();

    public abstract String getName();

    public static class EmptyMagazineException extends Exception {
    }
}
