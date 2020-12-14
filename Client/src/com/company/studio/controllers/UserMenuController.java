package com.company.studio.controllers;

import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.company.studio.behavior.CatalogInformation;
import com.company.studio.collections.CollectionCatalog;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.JSONObject;

public class UserMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane ButtonPane;

    @FXML
    private Pane RentPane;

    @FXML
    private ImageView BackButtonImage;

    @FXML
    private Button backButton;

    @FXML
    private Button CatalogButton;

    @FXML
    private Button OprosButton;

    @FXML
    private Button CabinetButton;

    @FXML
    private Button CorzinaButton;

    @FXML
    private Button hideButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label DateLabel;

    @FXML
    private Button HelpButton;

    @FXML
    private Pane OprosPane;

    @FXML
    private ImageView Snow2;

    @FXML
    private CheckBox YesCheckBox;

    @FXML
    private CheckBox NoCheckBox;

    @FXML
    private Button sendOprosButton;

    @FXML
    private CheckBox YesCheckBox1;

    @FXML
    private CheckBox NoCheckBox1;

    @FXML
    private CheckBox YesCheckBox2;

    @FXML
    private CheckBox NoCheckBox2;

    @FXML
    private Pane CorzinaPane;

    @FXML
    private ImageView Snow4;

    @FXML
    private Button BuyAllFromCorzinaButton;

    @FXML
    private DatePicker DateInCorzina;

    @FXML
    private Pane CatalogPane;

    @FXML
    private TableView<CatalogInformation> Table_UserCatalog;

    @FXML
    private TableColumn<CatalogInformation, String> Name_UserCatalog;

    @FXML
    private TableColumn<CatalogInformation, String> Material_UserCatalog;

    @FXML
    private TableColumn<CatalogInformation, Integer> Sale_Value_UserCatalog;

    @FXML
    private TableView<CatalogInformation> Table_UserCorzina;

    @FXML
    private TableColumn<CatalogInformation, String> Name_UserCorzina;

    @FXML
    private TableColumn<CatalogInformation, String> Material_UserCorzina;

    @FXML
    private TableColumn<CatalogInformation, Integer> Sale_Value_UserCorzina;

    @FXML
    private TextField searchCatalogField;

    @FXML
    private TableView<CatalogInformation> HistoryTable;

    @FXML
    private TableColumn<CatalogInformation, String> HistoryProductName;

    @FXML
    private TableColumn<CatalogInformation, String> HistoryMaterial;

    @FXML
    private TableColumn<CatalogInformation, Integer> HistorySale_Value;

    @FXML
    private TableColumn<CatalogInformation, Date> HistorySale_Date;

    @FXML
    private ImageView Snow3;

    @FXML
    private Button BuyNowButton;

    @FXML
    private Button RentButton;

    @FXML
    private DatePicker DateOfSale;

    @FXML
    private Button ToCorzinaButton;

    @FXML
    private Pane CabinetPane;

    @FXML
    private Pane HistoryPane;

    @FXML
    private ImageView Snow1;

    @FXML
    private Label LabelForName;

    @FXML
    private Label LabelForPhone;

    @FXML
    private Label LabelForEmail;

    @FXML
    private Label LabelForSurname;

    @FXML
    private Label LabelForOstatok;

    @FXML
    private Button deleteOneFromCorzina;

    @FXML
    private Button updateCashButton;

    @FXML
    private TextField ToAddCash;

    @FXML
    void handleClicks(ActionEvent event) {
        if(event.getSource()==CabinetButton){
            CabinetPane.toFront();
        }
        else if(event.getSource()==CorzinaButton){
            CorzinaPane.toFront();
        }
        else if(event.getSource()==CatalogButton){
            CatalogPane.toFront();
        }
        else if(event.getSource()==OprosButton){
            OprosPane.toFront();
        }
        else if(event.getSource()==HelpButton){
            HistoryPane.toFront();
        }
        else if(event.getSource()==backButton) {

            backButton.setOnMouseClicked(mouseEvent -> {
                backButton.getScene().getWindow().hide();

                Connect.send("back");

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
        }
        else if (event.getSource()==closeButton){
            closeButton.setOnMouseClicked(mouseEvent -> {
                Stage stages = (Stage) closeButton.getScene().getWindow();
                stages.close();
                Connect.send("exit");
            });
        }
        else if (event.getSource()==hideButton) {
            hideButton.setOnMouseClicked(mouseEvent -> {
                Stage stage = (Stage) hideButton.getScene().getWindow();
                stage.setIconified(true);
            });
        }
    }

    @FXML
    void searchCatalog(ActionEvent event) {
        FilteredList<CatalogInformation> filterCatalogs;
        filterCatalogs = new FilteredList<>(CollectionCatalog.getInstance().getCatalogs(), e->true);
        searchCatalogField.textProperty().addListener((observableValue, oldValue, newValue)->{
            filterCatalogs.setPredicate((CatalogInformation prod)->{

                String newVal = newValue.toLowerCase();
                return  prod.getProductname().toLowerCase().contains(newVal)
                        || prod.getMaterial().toLowerCase().contains(newVal);
            });
            Table_UserCatalog.setItems(filterCatalogs);
        });
    }

    @FXML
    void sendOpros(ActionEvent event) {
    String res;
    boolean key = true;
        JSONObject opros = new JSONObject();
        if (YesCheckBox.isSelected() && !NoCheckBox.isSelected()){
            opros.put("option1", "yes");
        }
        else if (NoCheckBox.isSelected() && !YesCheckBox.isSelected()){
            opros.put("option1", "no");
        }
        else if (NoCheckBox.isSelected() && YesCheckBox.isSelected()){
            key = false;
        }
        else if (!NoCheckBox.isSelected() && !YesCheckBox.isSelected()){
            key = false;
        }
        if (YesCheckBox1.isSelected() && !NoCheckBox1.isSelected()){
            opros.put("option2", "yes");
        }
        else if (NoCheckBox1.isSelected() && !YesCheckBox1.isSelected()){
            opros.put("option2", "no");
        }
        else if (NoCheckBox1.isSelected() && YesCheckBox1.isSelected()){
            key = false;
        }
        else if (!NoCheckBox1.isSelected() && !YesCheckBox1.isSelected()){
            key = false;
        }
        if (YesCheckBox2.isSelected() && !NoCheckBox2.isSelected()){
            opros.put("option3", "yes");
        }
        else if (NoCheckBox2.isSelected() && !YesCheckBox2.isSelected()){
            opros.put("option3", "no");
        }
        else if (NoCheckBox2.isSelected() && YesCheckBox2.isSelected()){
            key = false;
        }
        else if (!NoCheckBox2.isSelected() && !YesCheckBox2.isSelected()){
            key = false;
        }
        if (key){
            Connect.send("opros");
            Connect.send(opros);
        }
    }

    @FXML
    void updateCash(ActionEvent event) {
        if(!checkWord(ToAddCash.getText())) {
            Integer addCash = Integer.parseInt(ToAddCash.getText());
            if (addCash + Integer.parseInt(LabelForOstatok.getText()) >= 0) {
                Connect.send("addCash");
                Connect.send(LabelForPhone.getText());
                String id = Connect.get();
                if (!id.equals("wrong")) {
                    Connect.send(id);
                    Connect.send(addCash);
                    String newCash = Connect.get();
                    if (!newCash.equals("wrong"))
                        LabelForOstatok.setText(newCash);
                }
            }
            else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Вы не сможете снять больше средств,\nчем имеется на счёте!");
                    alert.showAndWait();
            }
            ToAddCash.clear();
        }
    }

    @FXML
    void initialize() {

        String uid = Connect.get();
        Connect.send(uid);

        initClock();
        ProfileData();
        catalogInTable();
        historyInTable();

        closeButton.setOnMouseClicked(mouseEvent -> {
            Stage stages = (Stage) closeButton.getScene().getWindow();
            stages.close();
            Connect.send("back");
        });

        hideButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.setIconified(true);
        });
    }

    @FXML
    void BuyNow (ActionEvent event) {

        Connect.send("buyGoods");
        Connect.send(LabelForOstatok.getText());
        CatalogInformation selectedCatalog = (CatalogInformation) Table_UserCatalog.getSelectionModel().getSelectedItem();
        CollectionCatalog.getInstance().catalogToCorzina(selectedCatalog);
        selectedCatalogInCorzina();

        LocalDate cDate = DateOfSale.getValue();
        String sessionDate = cDate.toString();
        JSONObject buy = new JSONObject();
        buy.put("goods",CollectionCatalog.getInstance().getCorzina());
        Connect.send(buy);
        Connect.send(sessionDate);
        //toTheHist();
        CollectionCatalog.getInstance().deleteAllFromCorzina();
        String result = Connect.get();
        if (result.equals("limit")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Вы не можете купить все\nпродукты из корзины в связи\nс недостатком средств на счёте!");
            alert.showAndWait();
        }
        else if (result.equals("ok")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText("Вы успешно произвели покупку!");
            alert.showAndWait();
            Integer cash = Integer.parseInt(Connect.get());
            LabelForOstatok.setText(cash.toString());
        }
    }

    void toTheHist(){
        HistoryProductName.setCellValueFactory(new PropertyValueFactory<CatalogInformation, String>("productname"));
        HistoryMaterial.setCellValueFactory(new PropertyValueFactory<CatalogInformation, String>("material"));
        HistorySale_Value.setCellValueFactory(new PropertyValueFactory<CatalogInformation, Integer>("sale_value"));
        HistorySale_Date.setCellValueFactory(new PropertyValueFactory<CatalogInformation, Date>("sale_date"));
        HistoryTable.setItems(CollectionCatalog.getInstance().getCorzina());
    }

    @FXML
    void BuyAllFromCorzina (ActionEvent event) throws NoSuchFieldException {
        boolean key = true;
        if (CollectionCatalog.getInstance().getCorzina().isEmpty()) {
            key = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Так корзина ведь пуста!");
            alert.showAndWait();
        }
        if (key){
            Connect.send("buyGoods");
            Connect.send(LabelForOstatok.getText());

            LocalDate cDate = DateInCorzina.getValue();
            String sessionDateCorz = cDate.toString();
            JSONObject buy = new JSONObject();
            buy.put("goods",CollectionCatalog.getInstance().getCorzina());
            Connect.send(buy);
            Connect.send(sessionDateCorz);
        }
        String result = Connect.get();
        if (result.equals("limit")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Вы не можете купить все\nпродукты из корзины в связи\nс недостатком средств на счёте!");
            alert.showAndWait();
        }
        else if (result.equals("ok")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Успех");
                alert.setHeaderText("Вы успешно произвели покупку!");
                alert.showAndWait();
            Integer cash = Integer.parseInt(Connect.get());
            LabelForOstatok.setText(cash.toString());
        }
    }

    @FXML
    void addCatalogToCorzina (ActionEvent event){
        boolean key = true;
        CatalogInformation selectedCatalog = (CatalogInformation) Table_UserCatalog.getSelectionModel().getSelectedItem();
        CollectionCatalog.getInstance().catalogToCorzina(selectedCatalog);
        selectedCatalogInCorzina();
        if(key)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Действие совершено");
            alert.setHeaderText("Продукт добавлен в корзину!");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Продукт не добавлен в корзину!");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteAllFromCorzina (ActionEvent event){
        CollectionCatalog.getInstance().deleteAllFromCorzina();

    }

    public void selectedCatalogInCorzina() {
        Name_UserCorzina.setCellValueFactory(new PropertyValueFactory<CatalogInformation, String>("productname"));
        Material_UserCorzina.setCellValueFactory(new PropertyValueFactory<CatalogInformation, String>("material"));
        Sale_Value_UserCorzina.setCellValueFactory(new PropertyValueFactory<CatalogInformation, Integer>("sale_value"));
        Table_UserCorzina.setItems(CollectionCatalog.getInstance().getCorzina());
    }

    void ProfileData(){
        String name = Connect.get();
        String surname = Connect.get();
        String phone = Connect.get();
        String email = Connect.get();
        String solv = Connect.get();

        LabelForName.setText(name);
        LabelForSurname.setText(surname);
        LabelForPhone.setText(phone);
        LabelForEmail.setText(email);
        LabelForOstatok.setText(solv);
        DateInCorzina.setValue(LocalDate.parse("2020-12-14"));
        DateOfSale.setValue(LocalDate.parse("2020-12-14"));
    }

    void catalogInTable(){
        CollectionCatalog.getInstance().fillData();
        setCatalogInTable();
    }

    void historyInTable(){
        CollectionCatalog.getInstance().fillHistoryData();
        setHistoryInTable();
    }

    void setCatalogInTable(){
        Name_UserCatalog.setCellValueFactory(new PropertyValueFactory<>("productname"));
        Material_UserCatalog.setCellValueFactory(new PropertyValueFactory<>("material"));
        Sale_Value_UserCatalog.setCellValueFactory(new PropertyValueFactory<>("sale_value"));
        Table_UserCatalog.setItems(CollectionCatalog.getInstance().getCatalogs());
    }

    void setHistoryInTable(){
        HistoryProductName.setCellValueFactory(new PropertyValueFactory<>("productname"));
        HistoryMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        HistorySale_Value.setCellValueFactory(new PropertyValueFactory<>("sale_value"));
        HistorySale_Date.setCellValueFactory(new PropertyValueFactory<>("sale_date"));
        HistoryTable.setItems(CollectionCatalog.getInstance().getHistory());
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd\nHH:mm:ss");
            DateLabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
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

        Pattern pattern = Pattern.compile("^([а-яё]+)$");
        Matcher matcher = pattern.matcher(source);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean check2(String textFromTextField){
        return (Integer.valueOf(textFromTextField) > 0);
    }

}