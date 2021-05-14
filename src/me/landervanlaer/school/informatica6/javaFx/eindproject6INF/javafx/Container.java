package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.objects.Updatable;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.GameLoop;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Entity;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Player;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Armor;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Backpack;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.Weapon;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.Shooter;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.HeavyMagazine;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.LightMagazine;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.MediumMagazine;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Container implements Initializable, Updatable {
    private static Container container;
    private final List<KeyCode> keys = new LinkedList<>();
    private final GameLoop gameLoop = new GameLoop();
    private final Coordinate cursor = new Coordinate(0, 0);

    @FXML
    private Label backpackCurrentMass;
    @FXML
    private Label backpackMaxMass;
    @FXML
    private Label magazineTotalLight;
    @FXML
    private Label magazineTotalMedium;
    @FXML
    private Label magazineTotalHeavy;
    @FXML
    private ChoiceBox<Armor> bodyChoice;
    @FXML
    private ChoiceBox<Weapon> handsChoice;
    @FXML
    private ChoiceBox<Backpack> backChoice;
    @FXML
    private TableView<TableItem> backpackTable;
    @FXML
    private TableView<TableItem> pickUpTable;
    @FXML
    private Label magazineTotal;
    @FXML
    private ProgressBar magazineBar;
    @FXML
    private Label magazineMax;
    @FXML
    private ProgressBar boostBar;
    @FXML
    private Canvas canvas;
    @FXML
    private MenuBar menuBar;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private ProgressBar armorBar;

    public static Container getInstance() {
        return container;
    }

    public static void applyColumnCellValueFactories(TableView<TableItem> view) {
        for(TableColumn<TableItem, ?> column : view.getColumns()) {
            switch(column.getText().toLowerCase()) {
                case "name" -> column.setCellValueFactory(new PropertyValueFactory<>("name"));
                case "mass" -> column.setCellValueFactory(new PropertyValueFactory<>("mass"));
                case "extra" -> column.setCellValueFactory(new PropertyValueFactory<>("extra"));
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void updateListToOtherList(List needToBeChanged, List items) {
        needToBeChanged.removeIf(o -> !items.contains(o));
        items.forEach(o -> {
            if(!needToBeChanged.contains(o)) {
                needToBeChanged.add(o);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Container.container = this;
        gameLoop.setStartNanoTime(System.nanoTime());
        gameLoop.start();

        Game.getInstance().initialize();

        applyColumnCellValueFactories(backpackTable);
        applyColumnCellValueFactories(pickUpTable);

        pickUpTable.setOnMouseClicked(event -> {
            if(pickUpTable.getSelectionModel().getSelectedIndex() < 0)
                return;

            final Item item = (Item) pickUpTable.getItems().get(pickUpTable.getSelectionModel().getSelectedIndex());

            try {
                Game.getInstance().getPlayer().addItemToBackpack(item);
                pickUpTable.getItems().remove(pickUpTable.getSelectionModel().getSelectedIndex());
            } catch(Entity.NoBackpack ignored) {
                showError("U heeft geen rugzak om dit item aan toe te voegen!");
            } catch(Backpack.MaxMassExceeded ignored) {
                showError("De rugzak zit vol!");
            }

            pickUpTable.getSelectionModel().clearSelection();
        });

        backpackTable.setOnMouseClicked(event -> {
            if(backpackTable.getSelectionModel().getSelectedIndex() < 0)
                return;

            final Item item = (Item) backpackTable.getItems().remove(backpackTable.getSelectionModel().getSelectedIndex());

            final Backpack backpack = Game.getInstance().getPlayer().getBackpack();
            if(backpack == null)
                return;

            try {
                backpack.dropItem(item);
            } catch(IndexOutOfBoundsException ignored) {
            }

            backpackTable.getSelectionModel().clearSelection();
        });

        final Player player = Game.getInstance().getPlayer();
        handsChoice.setOnAction((ActionEvent event) -> {
            Weapon item = handsChoice.getValue();
            if(item == null) return;

            player.getBackpack().removeItem(item);

            Item oldItem = player.setHand(item);
            if(oldItem == null) return;

            player.getBackpack().addItem(oldItem);
        });

        bodyChoice.setOnAction(event -> {
            Armor armor = bodyChoice.getValue();
            if(armor == null) return;

            player.getBackpack().removeItem(armor);

            Item oldItem = player.setArmor(armor);
            if(oldItem == null) return;

            player.getBackpack().addItem(oldItem);
        });

        backChoice.setOnAction(event -> {
            Backpack backpack = backChoice.getValue();

            if(backpack == null) return;

            player.changeBackpack(backpack);
        });
    }

    private void showError(String message) {
        // TODO: 14/05/2021 showError
        System.out.println(message);
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


        updateListToOtherList(pickUpTable.getItems(), player.getAllSurroundingItems());

        final Backpack backpack = player.getBackpack();
        if(backpack != null) {
            updateListToOtherList(handsChoice.getItems(), backpack.getItems().stream().filter(item -> item instanceof Weapon).collect(Collectors.toList()));
            updateListToOtherList(bodyChoice.getItems(), backpack.getItems().stream().filter(item -> item instanceof Armor).collect(Collectors.toList()));
            updateListToOtherList(backChoice.getItems(), backpack.getItems().stream().filter(item -> item instanceof Backpack).collect(Collectors.toList()));

            updateListToOtherList(backpackTable.getItems(), backpack.getItems());


            backpackCurrentMass.setText(String.valueOf(backpack.getMassInBackpack()));
            backpackMaxMass.setText(String.valueOf(backpack.getMaxMass()));

            int amountOfLightMz = 0;
            int amountOfMediumMz = 0;
            int amountOfHeavyMz = 0;
            for(Item item : backpack.getItems()) {
                if(item instanceof LightMagazine)
                    amountOfLightMz += ((LightMagazine) item).getAmount();
                else if(item instanceof MediumMagazine)
                    amountOfMediumMz += ((MediumMagazine) item).getAmount();
                else if(item instanceof HeavyMagazine)
                    amountOfHeavyMz += ((HeavyMagazine) item).getAmount();
            }

            magazineTotalLight.setText(String.valueOf(amountOfLightMz));
            magazineTotalMedium.setText(String.valueOf(amountOfMediumMz));
            magazineTotalHeavy.setText(String.valueOf(amountOfHeavyMz));
        } else {
            backpackCurrentMass.setText(null);
            backpackMaxMass.setText(null);
        }


        magazineBar.setProgress(0);
        magazineTotal.setText(String.valueOf(0));
        magazineMax.setText(String.valueOf(0));
        final Item handItem = player.getHand();
        if(handItem instanceof Weapon) {
            if(handItem instanceof Shooter) {
                if(((Shooter<?>) handItem).getMagazine() != null) {
                    magazineBar.setProgress(((Shooter<?>) handItem).getMagazine().getPercentageLoaded());
                    magazineTotal.setText(String.valueOf(((Shooter<?>) handItem).getMagazine().getAmount()));
                    magazineMax.setText(String.valueOf(((Shooter<?>) handItem).getMagazine().getMax()));
                }
            }
            if(!handsChoice.getItems().contains(handItem)) {
                handsChoice.getItems().add((Weapon) handItem);
            }
            handsChoice.getSelectionModel().select((Weapon) handItem);
        }

        final Armor bodyItem = player.getArmor();
        if(!bodyChoice.getItems().contains(bodyItem)) bodyChoice.getItems().add(bodyItem);
        bodyChoice.getSelectionModel().select(bodyItem);


    }

    public List<KeyCode> getKeys() {
        return keys;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public Coordinate getCursor() {
        return cursor;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void canvasClick(MouseEvent mouseEvent) {
        canvas.requestFocus();
    }
}
