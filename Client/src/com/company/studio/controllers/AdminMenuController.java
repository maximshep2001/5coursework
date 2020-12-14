package com.company.studio.controllers;

import com.company.studio.connection.Connect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView ExitImage;

    @FXML
    private Button closeButton;

    @FXML
    private ImageView BackImage;

    @FXML
    private ImageView UserManageImage;

    @FXML
    private ImageView CompanyManageImage;

    @FXML
    private Button UserManageButton;

    @FXML
    private Button CompanyManageButton;

    @FXML
    private ImageView winter1;

    @FXML
    private ImageView winter2;

    @FXML
    private ImageView snowImage1;

    @FXML
    private ImageView hat1;

    @FXML
    private ImageView colpak;

    private boolean isSnow1 = false;

    @FXML
    private Button hideButton;

    @FXML
    private Button backButton;

    @FXML
    void clickSnow1(MouseEvent event) {
        if(isSnow1) {
            winter1.setVisible(true);
            winter2.setVisible(true);
            hat1.setVisible(true);
            colpak.setVisible(true);
            isSnow1 = false;
        }
        else{
            winter1.setVisible(false);
            winter2.setVisible(false);
            hat1.setVisible(false);
            colpak.setVisible(false);
            isSnow1 = true;
        }
    }

    @FXML
    void initialize() {
        //String uid = Connect.get();

        closeButton.setOnMouseClicked(mouseEvent -> {
            Stage stages = (Stage) closeButton.getScene().getWindow();
            stages.close();
            Connect.send("exit");
        });

        hideButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.setIconified(true);
        });

            backButton.setOnMouseClicked(mouseEvent -> {
                backButton.getScene().getWindow().hide();

                Connect.send("back1");
                //Connect.send("back1");

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/company/studio/view/MainMenu.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
            });

        UserManageButton.setOnAction(actionEvent -> {
            try {
                Connect.send("user manage");
                UserManageButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/company/studio/view/UserManageMenu.fxml"));

                loader.load();

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Управление пользователями");
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        CompanyManageButton.setOnAction(actionEvent -> {
            try {
                Connect.send("company manage");

                CompanyManageButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/company/studio/view/CompanyManageMenu.fxml"));

                loader.load();

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Управление компанией");
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setResizable(false);
                stage.setScene(new Scene(root, 770, 570));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}