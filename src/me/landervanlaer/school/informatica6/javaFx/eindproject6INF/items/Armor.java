package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Number;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

import java.text.MessageFormat;

public class Armor extends Item {
    public static final int PROTECTION_MIN = 0;
    public static final int PROTECTION_MAX = 100;

    private int protection;

    public Armor(int protection, double weight) {
        super(weight);
        this.protection = protection;
    }

    public static boolean isValidProtection(int protection) {
        return protection >= PROTECTION_MIN && protection <= PROTECTION_MAX;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.setLineWidth(4);
        gc.setStroke(Color.BLACK);
        Draw.fillCircle(gc, getPos(), WIDTH);
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) throws IllegalArgumentException {
        if(!isValidProtection(protection))
            throw new IllegalArgumentException(MessageFormat.format("The given protection '{'{0}'}' is not valid", protection));
        this.protection = protection;
    }

    public int reduceProtection(int i) throws IllegalArgumentException {
        final int pro = getProtection() - i;

        setProtection(Number.constrain(pro, 0, Integer.MAX_VALUE));

        if(getProtection() == 0)
            return Math.abs(pro);

        return 0;
    }

    public double getProtectionPercentage() {
        return (double) getProtection() / (double) Armor.PROTECTION_MAX;
    }

    @Override
    public String toString() {
        return "%s (%d/%d)".formatted(super.toString(), getProtection(), PROTECTION_MAX);
    }

    @Override
    public String getExtra() {
        return String.valueOf(getProtection());
    }
}
