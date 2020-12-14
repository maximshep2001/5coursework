package com.company.studio.controllers;


import com.company.studio.connection.Connect;
import com.company.studio.behavior.Role;
import com.company.studio.behavior.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signInButton;

    @FXML
    private Button RegistrationButton;

    @FXML
    private TextField phoneField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView ExitImage;

    @FXML
    private ImageView LocationImage;

    @FXML
    private ImageView SupportImage;

    @FXML
    private Button closeButton;

    @FXML
    private Button hideButton;

    @FXML
    private Button ForgotPasswordButton;

    @FXML
    private ImageView winter;

    @FXML
    private Button ConfirmRegistrationButton;

    @FXML
    private ImageView snowImage;

    @FXML
    private ImageView hat;

    private boolean isSnow = false;

    @FXML
    private Text errorText;

    public void signIn(ActionEvent actionEvent) throws IOException {
        String login = phoneField.getText();
        String password = passwordField.getText();
        String role;

        Connect.send("authorization");
        Connect.send(login);
        Connect.send(password);
        role = Connect.get();

        if (role != null && role.equals("ADMIN")) {
            openScene("/com/company/studio/view/AdminMenu.fxml");
        }
        if (role != null && role.equals("USER")) {
            openScene("/com/company/studio/view/UserMenu.fxml");
        }
        if (role != null && role.equals("incorrectly")) {
            errorText.setVisible(true);
            errorText.setText("Неверно введен логин или пароль!");
        }
    }

    private void openScene(String directory) throws IOException {
        signInButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(directory));

        loader.load();

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
//        stage.setX(110);
//        stage.setY(40);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void clickSnow(MouseEvent event) {
        if(isSnow) {
            winter.setVisible(true);
            hat.setVisible(true);
            isSnow = false;
        }
        else{
            winter.setVisible(false);
            hat.setVisible(false);
            isSnow = true;
        }
    }

    @FXML
    void initialize() {

        errorText.setVisible(false);

        closeButton.setOnMouseClicked(mouseEvent -> {
            Stage stages = (Stage) closeButton.getScene().getWindow();
            stages.close();
            Connect.send("exit");
        });

        hideButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.setIconified(true);
        });

        RegistrationButton.setOnAction(actionEvent -> {
            try {
                openScene("/com/company/studio/view/RegistrationMenu.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ForgotPasswordButton.setOnAction(actionEvent -> {
            try {
                openScene("/com/company/studio/view/ForgotPassword.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean checkMail(String source) {
        Pattern pattern = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(source);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkPhone(String source) {
        Pattern pattern = Pattern.compile("^((\\+375)(29|44|33|25))[\\d]{7}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(source);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkName(String source) {
        Pattern pattern = Pattern.compile("^([А-Я][а-я]+)$");
        Matcher matcher = pattern.matcher(source);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkUserName(String source) {
        Pattern pattern = Pattern.compile("^([A-z0-9_\\.-]+)$");
        Matcher matcher = pattern.matcher(source);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean phoneExist(String source) {
        Connect.send("phoneExist");
        Connect.send(source);
        if (Objects.equals(Connect.get(), "incorrectly")) {
            return true;
        } else {
            return false;
        }
    }

}