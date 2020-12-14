package com.company.studio.main;

import com.company.studio.connection.Connect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        primaryStage.setTitle("Главное меню");
        root.setStyle("-fx-background-radius: 8;" /*+
                "-fx-background-insets: 0, 0 1 1 0;"*/);


        Scene sc = new Scene(root,601, 467, Color.TRANSPARENT);
        primaryStage.setScene(sc);
        sc.setOnMousePressed(event -> {
            xOffset = primaryStage.getX() - event.getScreenX();
            yOffset = primaryStage.getY() - event.getScreenY();
        });
        sc.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + xOffset);
            primaryStage.setY(event.getScreenY() + yOffset);
        });
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.println((new Connect()).isWork());
        launch(args);
    }
}
