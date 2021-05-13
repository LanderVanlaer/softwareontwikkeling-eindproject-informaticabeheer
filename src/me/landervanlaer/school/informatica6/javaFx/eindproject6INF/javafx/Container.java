package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.objects.Updatable;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.GameLoop;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Player;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Armor;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.assaultRifles.Ak47;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class Container implements Initializable, Updatable {
    private static Container container;
    private final List<KeyCode> keys = new LinkedList<>();
    private final GameLoop gameLoop = new GameLoop();
    @FXML
    private Canvas canvas;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private ProgressBar armorBar;

    public static Container getInstance() {
        return container;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Container.container = this;
        gameLoop.setStartNanoTime(System.nanoTime());
        gameLoop.start();

        //TEST
        final Coordinate coordinate = new Coordinate(Game.getInstance().getViewBox().getPos());
        Ak47 ak47 = new Ak47();
        ak47.setPos(coordinate);

        Game.getInstance().getItems().add(ak47);
    }

    @Override
    public void update() {
        Game.getInstance().update();
    }

    public void draw() {
        final Player player = Game.getInstance().getPlayer();
        healthBar.setProgress(player.getHpPercentage());

        final Armor armor = player.getArmor();
        if(armor != null) armorBar.setProgress(armor.getProtectionPercentage());

        Game.getInstance().draw(canvas.getGraphicsContext2D());
    }

    public List<KeyCode> getKeys() {
        return keys;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }
}
