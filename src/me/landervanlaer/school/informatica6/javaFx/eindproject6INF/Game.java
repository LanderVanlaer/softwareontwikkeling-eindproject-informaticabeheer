package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import javafx.scene.canvas.GraphicsContext;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Vector;
import me.landervanlaer.objects.Drawable;
import me.landervanlaer.objects.Updatable;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.config.ConfigHandler;
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

public class Game implements Drawable, Updatable {
    private static Game game = new Game();
    public final List<Entity> entities;
    private final List<Item> items;
    private final Viewbox viewBox;
    private final List<Bullet> bullets;
    private Playfield playField;
    private Player player;
    private boolean pause;

    private Game() {
        this.items = new LinkedList<>();
        this.playField = new Playfield(ConfigHandler.getInt("Game.PLAYFIELD_WIDTH"), ConfigHandler.getInt("Game.PLAYFIELD_HEIGHT"));
        final Coordinate middle = new Coordinate(getPlayField().getWidth() / 2D, getPlayField().getHeight() / 2D);
        this.player = new Player(ConfigHandler.getInt("Game.PLAYER_MAX_HP"), middle, ConfigHandler.getInt("Game.PLAYER_MASS"));
        this.viewBox = new Viewbox(new Coordinate(middle.getX(), middle.getY()));
        this.bullets = new LinkedList<>();
        this.entities = new LinkedList<>();
        this.pause = false;
    }


    public static void startNewGame() {
        Game.game = new Game();
    }

    public static Game getInstance() {
        return game;
    }

    public static Vector getFriction(Entity entity) {
        final Vector vector = entity.getVel().clone();
        vector.invert();
        vector.div(Container.getInstance().getGameLoop().getFrameTimeSeconds());
        vector.mult(ConfigHandler.getDouble("Game.DEFAULT_FRICTION_MU"));
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
        if(getPlayer().isDead())
            gameOver();
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

    private void gameOver() {
        Container.getInstance().showError("GAME OVER");
    }

    private void spawnBotsIfNeeded() {
        for(int i = getEntities().size(); i < ConfigHandler.getInt("Game.AMOUNT_OF_BOTS"); i++) {
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

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
