package com.company.studio.controllers;

import com.company.studio.animations.Shake;
import com.company.studio.connection.Connect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView BackgroundImage;

    @FXML
    private ImageView POETIC;

    @FXML
    private ImageView ExitImage;

    @FXML
    private Button closeButton;

    @FXML
    private ImageView BackImage;

    @FXML
    private Button BackButton;

    @FXML
    private TextField EmailField;

    @FXML
    private Button SendEmailButton;

    @FXML
    private Button backButton;

    @FXML
    private Button hideButton;

    @FXML
    void initialize() {

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

            //Connect.send("back");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/company/studio/view/MainMenu.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Главное меню");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        });

        SendEmailButton.setOnAction(event -> {

            boolean key = true;
            String mail = "";
            if (EmailField.getText().isEmpty() || EmailField.getText() == null
                    || EmailField.getText().length() < 4 || EmailField.getText().length() > 32
                    || checkMail(EmailField.getText()) == false) {
                key = false;
                Shake MailAnim = new Shake(EmailField);
                MailAnim.playAnim();
            }
            else {
                mail = EmailField.getText();
            }

            if (key == true) {

                try {
                    Connect.send("forgot password");

                    Connect.send(mail);
                    //System.out.println(mail);

                    String status = Connect.get();

                    if (!status.equals("nobody")) {
                        //System.out.println("Check your mail");

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Успех");
                        alert.setHeaderText("На ваш email отправлен пароль!");
                        alert.showAndWait();

                        SendEmailButton.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/company/studio/view/MainMenu.fxml"));

                        loader.load();

                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setResizable(false);
                        stage.setTitle("Главное меню");

                        stage.show();
                    }
                    else {
                        System.out.println("Повторите ввод");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка");
                        alert.setHeaderText("Такой email не зарегистрирован!");
                        alert.showAndWait();
                    }

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private boolean checkMail(String source) {
        Pattern pattern = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$",
                Pattern.CASE_INSENSITIVE);  //“\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}”
        Matcher matcher = pattern.matcher(source);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }
}