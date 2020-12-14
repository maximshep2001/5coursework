package com.company.studio.controllers;


import com.company.studio.collections.CollectionUsers;
import com.company.studio.behavior.UserInformation;
import com.company.studio.connection.Connect;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManageController {

    @FXML
    private ImageView BackgroundImage;

    @FXML
    private Label dateTime;

    @FXML
    private Button backButton;

    @FXML
    private TextField SearchParametrField;

    @FXML
    private TableView<UserInformation> UserTable;

    @FXML
    private TableColumn<UserInformation, Integer> ColumnID;

    @FXML
    private TableColumn<UserInformation, String> ColumnName;

    @FXML
    private TableColumn<UserInformation, String> ColumnSurname;

    @FXML
    private TableColumn<UserInformation, String> ColumnPhone;

    @FXML
    private TableColumn<UserInformation, String> ColumnEmail;

    @FXML
    private TableColumn<UserInformation, String> ColumnPassword;

    @FXML
    private TableColumn<UserInformation, String> ColumnRole;

    @FXML
    private Button closeButton;

    @FXML
    private Button hideButton;

    @FXML
    void searchUser(ActionEvent event) {
        FilteredList<UserInformation> filterUsers;
        filterUsers = new FilteredList<>(CollectionUsers.getInstance().getUsers(), e -> true);
        SearchParametrField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filterUsers.setPredicate((UserInformation user) -> {

                String newVal = newValue.toLowerCase();
                return user.getSurname().toLowerCase().contains(newVal)
                        || user.getName().toLowerCase().contains(newVal)
                        || user.getPhone().toLowerCase().contains(newVal)
                        || user.getEmail().toLowerCase().contains(newVal)
                        || user.getPassword().toLowerCase().contains(newVal);
                });
            UserTable.setItems(filterUsers);
        });
    }

    @FXML
    void deleteUser(ActionEvent event) {
        UserInformation selectedUser = (UserInformation) UserTable.getSelectionModel().getSelectedItem();
        CollectionUsers.getInstance().delete(selectedUser);
        Connect.send("deleteUser");
        Connect.send(selectedUser.getId());
    }

    @FXML
    void initialize() {

        initClock();

        userInTable();

        closeButton.setOnMouseClicked(mouseEvent -> {
            Stage stages = (Stage) closeButton.getScene().getWindow();
            stages.close();
            Connect.send("back");
            Connect.send("exit");
        });

        hideButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) hideButton.getScene().getWindow();
            stage.setIconified(true);
        });

        backButton.setOnMouseClicked(mouseEvent -> {
            backButton.getScene().getWindow().hide();

            Connect.send("back");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/company/studio/view/AdminMenu.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Меню администратора");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        });
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void userInTable() {
        CollectionUsers.getInstance().fillData();
        ColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        ColumnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        ColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColumnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        ColumnRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        UserTable.setItems(CollectionUsers.getInstance().getUsers());
    }

    private boolean checkNum(String source) {
       Pattern pattern = Pattern.compile("^([0-9]+)$");
       Matcher matcher = pattern.matcher(source);

       if (!matcher.matches()) {
           return false;
       } else {
           return true;
       }
    }

    private boolean checkWord(String source) {
        Pattern pattern = Pattern.compile("^([А-Я][а-я]+)$");
        Matcher matcher = pattern.matcher(source);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }


}