package org.wl.email_box;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Scanner;

/**
 * @ClassName : EmailBoxApplication
 * @Description :运行
 * @Author : WL
 * @Date : 2024-04-27 15:45
 */
public class EmailBoxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("EmailBox");
        Pane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EmailBox.fxml")));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.show();
    }
}
