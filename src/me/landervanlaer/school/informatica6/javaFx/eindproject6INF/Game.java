package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import javafx.scene.canvas.GraphicsContext;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Vector;
import me.landervanlaer.objects.Drawable;
import me.landervanlaer.objects.Updatable;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.BotFactory;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Entity;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Player;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.Bullet;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Container;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
// TODO: 27/04/2021 friction

public class Game implements Drawable, Updatable {
    public static final int PLAYER_MAX_HP = 100;
    public static final int PLAYER_MASS = 70;
    public static final int PLAYFIELD_HEIGHT = 10000;
    public static final int PLAYFIELD_WIDTH = 10000;
    public static final int AMOUNT_OF_BOTS = 20;
    public static final double MU = .95;

    private final static Game game = new Game();
    public final List<Entity> entities;
    private final List<Item> items;
    private final Viewbox viewBox;
    private final List<Bullet> bullets;
    private Playfield playField;
    private Player player;

    private Game() {
        this.items = new LinkedList<>();
        this.playField = new Playfield(PLAYFIELD_WIDTH, PLAYFIELD_HEIGHT);
        final Coordinate middle = new Coordinate(getPlayField().getWidth() / 2D, getPlayField().getHeight() / 2D);
        this.player = new Player(PLAYER_MAX_HP, middle, PLAYER_MASS);
        this.viewBox = new Viewbox(new Coordinate(middle.getX(), middle.getY()));
        this.bullets = new LinkedList<>();
        this.entities = new LinkedList<>();
    }


    public static Game getInstance() {
        return game;
    }

    public static Vector getFriction(Entity entity) {
        final Vector vector = entity.getVel().clone();
        vector.invert();
        vector.div(Container.getInstance().getGameLoop().getFrameTimeSeconds());
        vector.mult(Game.MU);
        return vector;
    }

    public void initialize() {
        this.playField.generateLoot();
    }

    @Override
    public void draw(GraphicsContext gc) {
        Draw.clear(gc);
        getPlayField().draw(gc);
        getItems().forEach(item -> {
            if(getViewBox().isVisible(item)) {
                item.draw(gc);
            }
        });
        getEntities().forEach(entity -> {
            if(getViewBox().isVisible(entity))
                entity.draw(gc);
        });
        getBullets().forEach(bullet -> {
            if(getViewBox().isVisible(bullet)) {
                bullet.draw(gc);
            }
        });
        getPlayer().draw(gc);
        // TODO: 27/04/2021
    }

    @Override
    public void update() {
        // TODO: 27/04/2021
        spawnBotsIfNeeded();

        getPlayer().update();
        final Iterator<Entity> entityIterator = getEntities().iterator();

        while(entityIterator.hasNext()) {
            final Entity entity = entityIterator.next();
            if(entity.isDead()) {
                entity.die();
                entityIterator.remove();
                continue;
            }
            entity.update();
        }

        getBullets().removeIf(Bullet::isDelete);
        getBullets().forEach(Bullet::update);
    }

    private void spawnBotsIfNeeded() {
        for(int i = getEntities().size(); i < Game.AMOUNT_OF_BOTS; i++) {
            entities.add(BotFactory.generateRandom());
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public Playfield getPlayField() {
        return playField;
    }

    public void setPlayField(Playfield playField) {
        this.playField = playField;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Viewbox getViewBox() {
        return viewBox;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
