package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("container.fxml"));
        Parent root = loader.load();
        Container controller = loader.getController();

        Scene scene = new Scene(root);
        controller.initialize(scene);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(KeyCode.F11.equals(event.getCode())) {
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });
        primaryStage.setTitle("Eindproject 6INF");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
