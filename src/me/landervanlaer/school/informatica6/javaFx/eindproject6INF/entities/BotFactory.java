package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Number;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Playfield;

public class BotFactory {
    public static final int BOT_MAX_HP = 200;
    public static final int BOT_MIN_HP = 50;

    public static Bot generateRandom() {
        final int hp = randomHp();
        return new Bot(hp, BotFactory.generateRandomCoordinateOnPlayfield(Game.getInstance().getPlayField()), BotFactory.hpToMass(hp));
    }

    public static int randomHp() {
        return Number.getRandom(BOT_MIN_HP, BOT_MAX_HP);
    }

    public static double hpToMass(int maxHp) {
        return maxHp * 1.2;
    }

    public static Coordinate generateRandomCoordinateOnPlayfield(Playfield playfield) {
        return new Coordinate(Number.getRandom(0, playfield.getWidth()), Number.getRandom(0, playfield.getHeight()));
    }
}
