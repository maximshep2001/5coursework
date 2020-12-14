package com.company.studio.controllers;

import com.company.studio.animations.Shake;
import com.company.studio.behavior.Role;
import com.company.studio.behavior.User;
import com.company.studio.connection.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView BackgroundImage;

    @FXML
    private ImageView SmallFon;

    @FXML
    private ImageView POETIC;

    @FXML
    private Button ConfirmRegistrationButton;

    @FXML
    private Button hideButton;

    @FXML
    private TextField RegistrationNameField;

    @FXML
    private ImageView ExitImage;

    @FXML
    private TextField RegistrationSurNameField;

    @FXML
    private TextField RegistrationPhoneField;

    @FXML
    private TextField RegistrationEmailField;

    @FXML
    private PasswordField RegistrationPickPasswordField;

    @FXML
    private PasswordField RegistrationConfirmPasswordField;

    @FXML
    private ImageView Model_1Image;

    @FXML
    private ImageView Model_2Image;

    @FXML
    private CheckBox CheckButton;

    @FXML
    private Text SoglashenieField;

    @FXML
    private ImageView BackImage;

    @FXML
    private Button backButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Button AgreenmentButton;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpSurname;

    @FXML
    private TextField signUpPhone;

    @FXML
    private TextField signUpEmail;

    @FXML
    private TextField signUpPassword;

    @FXML
    private TextField signUpRepass;

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
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        });
        AgreenmentButton.setOnAction(event -> {

            AgreenmentButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/company/studio/view/Agreenment.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Правила использования");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        });

        signUpButton.setOnAction(event -> {

            boolean key = true;

            String name = signUpName.getText();
            String surname = signUpSurname.getText();
            String phone = signUpPhone.getText();
            String email = signUpEmail.getText();
            String password = signUpPassword.getText();
            String repass = signUpRepass.getText();

            if (name.isEmpty() || name.length() < 2 || name.length() > 12 || !checkName(name)) {
                key = false;
                Shake NameAnim = new Shake(RegistrationNameField);
                NameAnim.playAnim();
            }
            if (surname.isEmpty() || surname.length() < 2 || surname.length() > 12 || !checkName(surname)) {
                key = false;
                Shake SurNameAnim = new Shake(RegistrationSurNameField);
                SurNameAnim.playAnim();
            }
            if (phone.isEmpty() || phone.length() < 12 || phone.length() > 14 || !checkPhone(phone)) {
                key = false;
                Shake EmailAnim = new Shake(RegistrationEmailField);
                EmailAnim.playAnim();
            }
            if (email.isEmpty() || email.length() < 4 || email.length() > 32 || !checkMail(email)) {
                key = false;
                Shake PhoneAnim = new Shake(RegistrationPhoneField);
                PhoneAnim.playAnim();
            }
            if (password.isEmpty() || password.length() < 4 || password.length() > 12) {
                key = false;
                Shake PickPasswordAnim = new Shake(RegistrationPickPasswordField);
                PickPasswordAnim.playAnim();
            }
            if (!repass.equals(password)) {
                key = false;
                Shake ConfirmPasswordAnim = new Shake(RegistrationConfirmPasswordField);
                ConfirmPasswordAnim.playAnim();
            }
            if (CheckButton.isSelected()) {
                key = true;
            } else {
                key = false;
            }
            if (key) {
                Connect.send("registration");
                User user = new User(name, surname, phone, email, password, Role.USER);
                Connect.send(user);

                try {
                    openScene("/com/company/studio/view/MainMenu.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*signUpButton.getScene().getWindow().hide();
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
                stage.setTitle("Главное меню");

                stage.show();*/
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Заполните поля корректно!");
                alert.showAndWait();
            }
        });
    };

    private void openScene(String directory) throws IOException {
        signUpButton.getScene().getWindow().hide();

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

    private boolean checkName(String source) {
        Pattern pattern = Pattern.compile("^([А-Я][а-яё]+)$");
        Matcher matcher = pattern.matcher(source);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkPhone(String source) {
        Pattern pattern = Pattern.compile("[0-9]{12,12}");//\\d{12}
        Matcher matcher = pattern.matcher(source);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }
}




