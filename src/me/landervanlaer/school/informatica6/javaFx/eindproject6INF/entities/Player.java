package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import javafx.scene.canvas.GraphicsContext;
import me.landervanlaer.math.Coordinate;

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

    @Override
    public void update() {
        super.update();
    }
}
