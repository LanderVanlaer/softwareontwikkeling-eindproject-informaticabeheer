package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("container.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Container controller = loader.getController();

        scene.setOnKeyPressed(event -> {
            controller.getKeys().put(event.getCode(), true);
        });
        scene.setOnKeyReleased(event -> {
//            controller.getKeys().put(event.getCode(), false);
            controller.getKeys().remove(event.getCode());
        });

        primaryStage.setTitle("Eindproject 6INF");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
