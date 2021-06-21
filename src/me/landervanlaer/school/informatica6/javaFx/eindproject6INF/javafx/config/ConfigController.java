package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.config;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.config.ConfigHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigController implements Initializable {
    public TableView<ConfigTableRow> table;
    public TableColumn<ConfigTableRow, String> tableColumnType;
    public TableColumn<ConfigTableRow, String> tableColumnKey;
    public TableColumn<ConfigTableRow, String> tableColumnValue;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableColumnKey.setCellValueFactory(new PropertyValueFactory<>("key"));
        tableColumnValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        tableColumnValue.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnValue.setOnEditCommit(event -> table.getItems().get(event.getTablePosition().getRow()).setValue(event.getNewValue()));

        for(ConfigHandler.ConfigItem item : ConfigHandler.getConfigItems())
            table.getItems().add(new ConfigTableRow(item.key(), item.value(), item.type()));
    }

    public void shutdown() {
        for(ConfigTableRow item : table.getItems()) {
            try {
                ConfigHandler.set(item.getKey(), item.getValue(), item.getType());
            } catch(Exception ignored) {
            }
        }
    }
}
