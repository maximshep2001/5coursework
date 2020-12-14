package com.company.studio.controllers;

import com.company.studio.connection.Connect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AgreenmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TextArea Information;

    @FXML
    private RadioButton AcceptRadio;

    @FXML
    private RadioButton NoAcceptRadio;

    @FXML
    private Button SetButton;

    @FXML
    void initialize() {

        Connect.send("getCompanyInfo");
        initCompanyInfo();

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RegistrationMenuController.class.getResource("/com/company/studio/view/RegistrationMenu.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Меню регистарции");
            stage.setScene(new Scene(root, 593, 458));
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        });

            SetButton.setOnAction(actionEvent -> {

                    if(AcceptRadio.isSelected() && !NoAcceptRadio.isSelected())
                    try {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Удачно всё-таки!");
                        alert.setHeaderText("Какие Вы умненькие! Так держать!");
                        alert.showAndWait();

                        SetButton.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/company/studio/view/RegistrationMenu.fxml"));

                        loader.load();

                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setTitle("Меню регистрации");
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setResizable(false);
                        stage.setScene(new Scene(root, 593, 458));
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    else if (AcceptRadio.isSelected() && NoAcceptRadio.isSelected()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ошибка");
                        alert.setHeaderText("Смешно что-ли? Я бы не радовался:)");
                        alert.showAndWait();
                    }

                    else if (!AcceptRadio.isSelected() && !NoAcceptRadio.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Так ведь не выбран вариант! Стоит быть внимательнее в жизни!");
                    alert.showAndWait();
                    }

                    else if (!AcceptRadio.isSelected() && NoAcceptRadio.isSelected()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Ошибка");
                        alert.setHeaderText("А стоит бы прочесть эти условия, ну либо же просто принять, и продолжать работать)");
                        alert.showAndWait();
                    }
            });
    }

    private void initCompanyInfo(){
        String info;
        info = Connect.get();
        System.out.println(info);
        Information.setText(info);
    }
}