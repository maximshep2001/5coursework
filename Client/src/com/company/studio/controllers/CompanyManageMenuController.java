package com.company.studio.controllers;

import com.company.studio.animations.Shake;
import com.company.studio.collections.StatisticsCollection;
//import com.company.studio.collections.CollectionProducts;

import com.company.studio.behavior.CatalogInformation;
import com.company.studio.behavior.ProductInformation;
import com.company.studio.behavior.Statistics;
import com.company.studio.collections.CollectionCatalog;
import com.company.studio.collections.CollectionProduct;
import com.company.studio.collections.CollectionProduct;
import com.company.studio.behavior.Product;
import com.company.studio.collections.StatisticsCollection;
import com.company.studio.connection.Connect;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.studio.collections.StatisticsCollection.statisticMap;


public class CompanyManageMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane ButtonPane;

    @FXML
    private ImageView BackButtonImage;

    @FXML
    private Button backButton;

    @FXML
    private Button CatalogButton;

    @FXML
    private Button GraphicButton;

    @FXML
    private Button ProductButton;

    @FXML
    private Button TaskButton;

    @FXML
    private Pane GraphicPane;

    @FXML
    private Pane CatalogPane;

    @FXML
    private Pane ProductPane;

    @FXML
    private TableView<ProductInformation> Table_Products;

    @FXML
    private TableColumn<ProductInformation, Integer> Number_Product;

    @FXML
    private TableColumn<ProductInformation, String> ProductName;

    @FXML
    private TableColumn<ProductInformation, String> Material;

    @FXML
    private TableColumn<ProductInformation, Integer> Cost_Price;

    @FXML
    private TableColumn<ProductInformation, Integer> Sale_Value;

    @FXML
    private TableColumn<ProductInformation, Integer> Production_Time;

    @FXML
    private Button SearchPrButton;

    @FXML
    private Button AddtoTaskButton;

    @FXML
    private Button DeleteCatalogButton;

    @FXML
    private Button addProdToCatalogButton;

    @FXML
    private TextField productName_field;

    @FXML
    private TextField MaterialField;

    @FXML
    private TextField CostPriceField;

    @FXML
    private TextField SaleValueField;

    @FXML
    private TextField ProductionTimeField;

    @FXML
    private TextField searchPrField;

    @FXML
    private Button AddProductButton;

    @FXML
    private Button DeleteProductButton;

    @FXML
    private ImageView reloadImage;

    @FXML
    private Pane TaskPane;

    @FXML
    private Pane InfoPane;

    @FXML
    private TextField PrognozBlago;

    @FXML
    private TextField PrognozFirmyBlago;

    @FXML
    private TextField PrognozFirmyNeblago;

    @FXML
    private TextField AmountPr1;

    @FXML
    private TextField AmountPr3;

    @FXML
    private TextField AmountPr2;

    @FXML
    private TableView<CatalogInformation> TaskPrTable;

    @FXML
    private TableColumn<CatalogInformation, Integer> TaskPrNumber;

    @FXML
    private TableColumn<CatalogInformation, String> TaskPrName;

    @FXML
    private TableColumn<CatalogInformation, String> TaskPrMaterial;

    @FXML
    private TableColumn<CatalogInformation, Integer> TaskPrCost_Price;

    @FXML
    private TableColumn<CatalogInformation, Integer> TaskPrSale_value;

    @FXML
    private TableView<CatalogInformation> ResultTable;

    @FXML
    private TableColumn<CatalogInformation, Integer> ResultPrNumber;

    @FXML
    private TableColumn<CatalogInformation, String> ResultPrName;

    @FXML
    private TableColumn<CatalogInformation, String> ResultPrMaterial;

    @FXML
    private TableColumn<CatalogInformation, Integer> ResultPrCost_Price;

    @FXML
    private TableColumn<CatalogInformation, Integer> ResultPrSale_value;

    @FXML
    private TableView<ProductInformation> TableProdToCatalog;

    @FXML
    private TableColumn<ProductInformation, String> NameProdToCatalog;

    @FXML
    private TableColumn<ProductInformation, String> MaterialProdToCatalog;

    @FXML
    private TableColumn<ProductInformation, Integer> Cost_PriceProdToCatalog;

    @FXML
    private TableView<CatalogInformation> TableCatalog;

    @FXML
    private TableColumn<CatalogInformation, Integer> NumberProdInCatalog;

    @FXML
    private TableColumn<CatalogInformation, String> NameProdInCatalog;

    @FXML
    private TableColumn<CatalogInformation, String> MaterialProdInCatalog;

    @FXML
    private TableColumn<CatalogInformation, Integer> Cost_PriceProdInCatalog;

    @FXML
    private TableColumn<CatalogInformation, Integer> Sale_ValueProdInCatalog;

    @FXML
    private TextField ProductSaleField;

    @FXML
    private TextField searchInCatalogField;

    @FXML
    private TableView<Statistics> stat_table;

    @FXML
    private TableColumn<Statistics, Integer> year_column;

    @FXML
    private TableColumn<Statistics, Integer> revenue_column;

    @FXML
    private TableColumn<Statistics, Integer> expenses_column;

    @FXML
    private TableColumn<Statistics, Integer> profit_column;

    @FXML
    private Button ResultButton;

    @FXML
    private Button TreeButton;

    @FXML
    private Label DateLabel;

    @FXML
    private Button addProductToCatalogButton;

    @FXML
    private Button hideButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button addFromCatalogToTask;

    @FXML
    private ImageView Snow1;

    @FXML
    private ImageView Snow2;

    @FXML
    private ImageView Snow3;

    @FXML
    private PieChart Pie1;

    @FXML
    private PieChart Pie2;

    @FXML
    private PieChart Pie3;

    @FXML
    private ImageView Snow4;

    @FXML
    private Text question1;

    @FXML
    private Text question2;

    @FXML
    private Text question3;

    @FXML
    private Button GetOprosButton;

    @FXML
    private Button Btn1;

    @FXML
    private Button InfoButton;

    @FXML
    private ImageView Img1;

    @FXML
    private ImageView Img2;

    @FXML
    private ImageView ram1;

    @FXML
    private ImageView ram2;

    @FXML
    private Pane RentPane;

    @FXML
    private Button RentButton;

    @FXML
    private ImageView ram3;

    @FXML
    private TextArea paragraph;

    @FXML
    private TextField year;

    @FXML
    private TextField firstKvIncome;

    @FXML
    private TextField secondKvIncome;

    @FXML
    private TextField thirdKvIncome;

    @FXML
    private TextField firstKvExpences;

    @FXML
    private TextField secondKvExpences;

    @FXML
    private TextField thirdKvExpences;

    @FXML
    private TextField fourthKvIncome;

    @FXML
    private TextField fourthKvExpences;


    @FXML
    void getOpros(ActionEvent event) throws InterruptedException {
        ObservableList<PieChart.Data> opros1 = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> opros2 = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> opros3 = FXCollections.observableArrayList();
        double YES1, NO1, res1, YES2, NO2, res2, YES3, NO3, res3;
        Connect.send("getOpros");
        JSONObject counter = new JSONObject(Connect.get());
        System.out.println(counter);

        YES1 = counter.getDouble("yes1");
        NO1 = counter.getDouble("no1");
        res1 = YES1 + NO1;
        YES1 = (YES1 * 100) / res1;
        NO1 = (NO1 * 100) / res1;
        YES2 = counter.getDouble("yes2");
        NO2 = counter.getDouble("no2");
        res2 = YES2 + NO2;
        YES2 = (YES2 * 100) / res2;
        NO2 = (NO2 * 100) / res2;
        YES3 = counter.getDouble("yes3");
        NO3 = counter.getDouble("no3");
        res3 = YES3 + NO3;
        YES3 = (YES3 * 100) / res3;
        NO3 = (NO3 * 100) / res3;

        PieChart.Data sector1 = new PieChart.Data("yes", YES1);
        PieChart.Data sector2 = new PieChart.Data("no", NO1);
        opros1.add(sector1);
        opros1.add(sector2);
        Pie1.setData(opros1);
        Pie1.setVisible(true);
        question1.setVisible(true);

        PieChart.Data sector3 = new PieChart.Data("yes", YES2);
        PieChart.Data sector4 = new PieChart.Data("no", NO2);
        opros2.add(sector3);
        opros2.add(sector4);
        Pie2.setData(opros2);
        Pie2.setVisible(true);
        question2.setVisible(true);

        PieChart.Data sector5 = new PieChart.Data("yes", YES3);
        PieChart.Data sector6 = new PieChart.Data("no", NO3);
        opros3.add(sector5);
        opros3.add(sector6);

        Pie3.setData(opros3);
        Pie3.setVisible(true);
        question3.setVisible(true);
        GetOprosButton.setVisible(false);
        Img1.setVisible(false);
        Img2.setVisible(false);
        Btn1.setVisible(false);
        ram1.setVisible(true);
        ram2.setVisible(true);
        ram3.setVisible(true);
        reloadImage.setVisible(true);
    }

    @FXML
    void getRentResult(ActionEvent event) {
        boolean key = true;
        JSONObject projectJson = new JSONObject();
        Statistics statistic;
        if( year.getText().isEmpty() ){key = false;}
        else {projectJson.put("year", year.getText().trim()); }

        int resultRevenue1 = Integer.parseInt(firstKvIncome.getText());
        int resultExpenses1 = Integer.parseInt( firstKvExpences.getText());
        int resultProfit1 = resultRevenue1 - resultExpenses1;

        int resultRevenue2 = Integer.parseInt(secondKvIncome.getText());
        int resultExpenses2 = Integer.parseInt( secondKvExpences.getText());
        int resultProfit2 = resultRevenue2 - resultExpenses2;

        int resultRevenue3 = Integer.parseInt(thirdKvIncome.getText());
        int resultExpenses3 = Integer.parseInt( thirdKvExpences.getText());
        int resultProfit3 = resultRevenue3 - resultExpenses3;

        int resultRevenue4 = Integer.parseInt(fourthKvIncome.getText());
        int resultExpenses4 = Integer.parseInt( fourthKvExpences.getText());
        int resultProfit4 = resultRevenue4 - resultExpenses4;

        int resultRevenue = resultRevenue1 + resultRevenue2 + resultRevenue3 + resultRevenue4;
        int resultExpenses = resultExpenses1 + resultExpenses2 + resultExpenses3 + resultExpenses4;
        int resultProfit = resultProfit1 + resultProfit2 + resultProfit3 + resultProfit4;


        String revenue = Integer.toString(resultRevenue);
        projectJson.put("revenue", revenue);
        String expenses = Integer.toString(resultExpenses);
        projectJson.put("expenses", expenses);
        String profit = Integer.toString(resultProfit);
        projectJson.put("profit", profit);

        if(key) {
            Connect.send("getStatistics");
            Connect.send( projectJson.toString() );

            StatisticsCollection.getInstance().fillNewData();

            year_column.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("year"));
            revenue_column.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("revenue"));
            expenses_column.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("expenses"));
            profit_column.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("profit"));
            stat_table.setItems(StatisticsCollection.getInstance().getStat());

        }
        else {
            Alert alert = new Alert(  Alert.AlertType.WARNING);
            alert.setTitle( "Ошибка" );
            alert.setHeaderText( "Поля введены некорректно!" );
            alert.showAndWait();
        }
    }


    @FXML
    public void buildGraph()
    {
        Stage stage = new Stage();
        ObservableList<XYChart.Series> seriesList = FXCollections.observableArrayList();
        List<XYChart.Data> listData = new ArrayList<>();
        for(Map.Entry<Integer, Integer> item : statisticMap.entrySet()) {
            listData.add(new XYChart.Data(item.getKey(), item.getValue()));
        }

        ObservableList<XYChart.Data> aList = FXCollections.observableArrayList(listData);
        seriesList.add(new XYChart.Series("Прибыль ", aList));
        Axis xAxis = new NumberAxis("Год", 2010, 2020, 1);
        Axis yAxis = new NumberAxis("Ден.ед.", -50000, 150000, 20000);
        LineChart statistics_graph = new LineChart(xAxis, yAxis, seriesList);
        Button exitBtn = new Button("Назад");
        exitBtn.setOnAction(event -> stage.hide());
        FlowPane root = new FlowPane(Orientation.VERTICAL, statistics_graph,exitBtn);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void SolutionTree(ActionEvent event) throws IOException {

        CollectionCatalog.getInstance().deleteCollRes();

        boolean key = true;
        JSONObject taskJson = new JSONObject();

        if (AmountPr1.getText().isEmpty() || AmountPr1.getText() == null || !checkNum(AmountPr1.getText())) {
            key = false;
            Shake AmountPr1Anim = new Shake(AmountPr1);
            AmountPr1Anim.playAnim();
        } else {
            taskJson.put("AmountPr1", AmountPr1.getText().trim());
        }

        if (AmountPr2.getText().isEmpty() || AmountPr2.getText() == null || !checkNum(AmountPr2.getText())) {
            key = false;
            Shake AmountPr2Anim = new Shake(AmountPr2);
            AmountPr2Anim.playAnim();
        } else {
            taskJson.put("AmountPr2", AmountPr2.getText().trim());
        }

        if (AmountPr3.getText().isEmpty() || AmountPr3.getText() == null || !checkNum(AmountPr3.getText())) {
            key = false;
            Shake AmountPr3Anim = new Shake(AmountPr3);
            AmountPr3Anim.playAnim();
        } else {
            taskJson.put("AmountPr3", AmountPr3.getText().trim());
        }

        if (PrognozFirmyBlago.getText().isEmpty() || PrognozFirmyBlago.getText() == null || !checkNum1(PrognozFirmyBlago.getText())) {
            key = false;
            Shake PrognozFirmyBlagoAnim = new Shake(PrognozFirmyBlago);
            PrognozFirmyBlagoAnim.playAnim();
        } else {
            taskJson.put("PrognozFirmyBlago", PrognozFirmyBlago.getText().trim());
        }

        if (PrognozFirmyNeblago.getText().isEmpty() || PrognozFirmyNeblago.getText() == null || !checkNum1(PrognozFirmyNeblago.getText())) {
            key = false;
            Shake PrognozFirmyNeblagoAnim = new Shake(PrognozFirmyNeblago);
            PrognozFirmyNeblagoAnim.playAnim();
        } else {
            taskJson.put("PrognozFirmyNeblago", PrognozFirmyNeblago.getText().trim());
        }

        if (PrognozBlago.getText().isEmpty() || PrognozBlago.getText() == null || !checkNum1(PrognozBlago.getText())) {
            key = false;
            Shake PrognozBlagoAnim = new Shake(PrognozBlago);
            PrognozBlagoAnim.playAnim();
        } else {
            taskJson.put("PrognozBlago", PrognozBlago.getText().trim());
        }

        if (CollectionCatalog.getInstance().getSelectedCatalogCatalog().isEmpty() ||
                CollectionCatalog.getInstance().getSelectedCatalogCatalog().size() != 3) {
            key = false;
        }

        if (key) {
            Connect.send("getSingleSolutionTree");
            Connect.send(taskJson.toString());

            JSONObject task1Json = new JSONObject();

            task1Json.put("TaskData", CollectionCatalog.getInstance().getSelectedCatalogCatalog());
            Connect.send(task1Json.toString());

            CollectionCatalog.getInstance().fillTaskData();
            CollectionCatalog.getInstance().fillTaskData();

            ResultPrNumber.setCellValueFactory(new PropertyValueFactory<CatalogInformation, Integer>("idcatalog"));
            ResultPrName.setCellValueFactory(new PropertyValueFactory<CatalogInformation, String>("productname"));
            ResultPrMaterial.setCellValueFactory(new PropertyValueFactory<CatalogInformation, String>("material"));
            ResultPrCost_Price.setCellValueFactory(new PropertyValueFactory<CatalogInformation, Integer>("cost_price"));
            ResultPrSale_value.setCellValueFactory(new PropertyValueFactory<CatalogInformation, Integer>("sale_value"));
            ResultTable.setItems(CollectionCatalog.getInstance().getResCatalogs());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/company/studio/view/Tree.fxml"));

            loader.load();

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(true);
            stage.show();
            stage.setTitle("Дерево решения");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Заполните все поля корректно!");
            alert.showAndWait();
        }
    }

    @FXML
    void handleClicks(ActionEvent event) {

        if (event.getSource() == ProductButton) {
            ProductPane.toFront();
        } else if (event.getSource() == TaskButton) {
            TaskPane.toFront();
        } else if (event.getSource() == GraphicButton) {
            GraphicPane.toFront();
        } else if (event.getSource() == CatalogButton) {
            CatalogPane.toFront();
        } else if (event.getSource() == RentButton) {
            RentPane.toFront();
        }
            else if (event.getSource() == InfoButton) {
                InfoPane.toFront();
        } else if (event.getSource() == backButton) {

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
        } else if (event.getSource() == closeButton) {
            closeButton.setOnMouseClicked(mouseEvent -> {
                Stage stages = (Stage) closeButton.getScene().getWindow();
                stages.close();
                Connect.send("back");
                Connect.send("exit");
            });
        } else if (event.getSource() == hideButton) {
            hideButton.setOnMouseClicked(mouseEvent -> {
                Stage stage = (Stage) hideButton.getScene().getWindow();
                stage.setIconified(true);
            });
        }
    }

    @FXML
    void singleSolution(ActionEvent event) throws IOException {

        CollectionCatalog.getInstance().deleteCollRes();

        boolean key = true;
        JSONObject taskJson = new JSONObject();

        if (AmountPr1.getText().isEmpty() || AmountPr1.getText() == null || !checkNum(AmountPr1.getText())) {
            key = false;
            Shake AmountPr1Anim = new Shake(AmountPr1);
            AmountPr1Anim.playAnim();
        } else {
            taskJson.put("AmountPr1", AmountPr1.getText().trim());
        }

        if (AmountPr2.getText().isEmpty() || AmountPr2.getText() == null || !checkNum(AmountPr2.getText())) {
            key = false;
            Shake AmountPr2Anim = new Shake(AmountPr2);
            AmountPr2Anim.playAnim();
        } else {
            taskJson.put("AmountPr2", AmountPr2.getText().trim());
        }

        if (AmountPr3.getText().isEmpty() || AmountPr3.getText() == null || !checkNum(AmountPr3.getText())) {
            key = false;
            Shake AmountPr3Anim = new Shake(AmountPr3);
            AmountPr3Anim.playAnim();
        } else {
            taskJson.put("AmountPr3", AmountPr3.getText().trim());
        }

        if (PrognozFirmyBlago.getText().isEmpty() || PrognozFirmyBlago.getText() == null || !checkNum1(PrognozFirmyBlago.getText())) {
            key = false;
            Shake PrognozFirmyBlagoAnim = new Shake(PrognozFirmyBlago);
            PrognozFirmyBlagoAnim.playAnim();
        } else {
            taskJson.put("PrognozFirmyBlago", PrognozFirmyBlago.getText().trim());
        }

        if (PrognozFirmyNeblago.getText().isEmpty() || PrognozFirmyNeblago.getText() == null || !checkNum1(PrognozFirmyNeblago.getText())) {
            key = false;
            Shake PrognozFirmyNeblagoAnim = new Shake(PrognozFirmyNeblago);
            PrognozFirmyNeblagoAnim.playAnim();
        } else {
            taskJson.put("PrognozFirmyNeblago", PrognozFirmyNeblago.getText().trim());
        }

        if (PrognozBlago.getText().isEmpty() || PrognozBlago.getText() == null || !checkNum1(PrognozBlago.getText())) {
            key = false;
            Shake PrognozBlagoAnim = new Shake(PrognozBlago);
            PrognozBlagoAnim.playAnim();
        } else {
            taskJson.put("PrognozBlago", PrognozBlago.getText().trim());
        }

        if (CollectionCatalog.getInstance().getSelectedCatalogCatalog().isEmpty() ||
                CollectionCatalog.getInstance().getSelectedCatalogCatalog().size() != 3) {
            key = false;
        }

        if (key) {
            Connect.send("getSingleSolution");
            Connect.send(taskJson.toString());

            JSONObject task1Json = new JSONObject();

            task1Json.put("TaskData", CollectionCatalog.getInstance().getSelectedCatalogCatalog());
            Connect.send(task1Json.toString());

            CollectionCatalog.getInstance().fillTaskData();
            CollectionCatalog.getInstance().fillTaskData();

            ResultPrNumber.setCellValueFactory(new PropertyValueFactory<CatalogInformation, Integer>("idproduct"));
            ResultPrName.setCellValueFactory(new PropertyValueFactory<CatalogInformation, String>("productname"));
            ResultPrMaterial.setCellValueFactory(new PropertyValueFactory<CatalogInformation, String>("material"));
            ResultPrCost_Price.setCellValueFactory(new PropertyValueFactory<CatalogInformation, Integer>("cost_price"));
            ResultPrSale_value.setCellValueFactory(new PropertyValueFactory<CatalogInformation, Integer>("sale_value"));
            ResultTable.setItems(CollectionCatalog.getInstance().getResCatalogs());
        }
    }

    //Из таблицы каталога выводится в таблицу таск выбранный продукт
    public void selectedCatalogInTable() {
        TaskPrNumber.setCellValueFactory(new PropertyValueFactory<CatalogInformation, Integer>("idcatalog"));
        TaskPrName.setCellValueFactory(new PropertyValueFactory<CatalogInformation, String>("productname"));
        TaskPrMaterial.setCellValueFactory(new PropertyValueFactory<CatalogInformation, String>("material"));
        TaskPrCost_Price.setCellValueFactory(new PropertyValueFactory<CatalogInformation, Integer>("cost_price"));
        TaskPrSale_value.setCellValueFactory(new PropertyValueFactory<CatalogInformation, Integer>("sale_value"));
        TaskPrTable.setItems(CollectionCatalog.getInstance().getSelectedCatalogCatalog());
    }

    //Из таблицы каталога добавляется в таблицу таск выбранный продукт
    @FXML
    void addCatalogToTask(ActionEvent event) {
        boolean key = true;
        CatalogInformation selectedCatalog = (CatalogInformation) TableCatalog.getSelectionModel().getSelectedItem();
        CollectionCatalog.getInstance().selectCatalog(selectedCatalog);
        selectedCatalogInTable();
        if (key) {
            //fillInTableNewCatalog();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Действие совершено");
            alert.setHeaderText("Каталог добавлен в задачу!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Каталог не добавлен в задачу!");
            alert.showAndWait();
        }
    }

    @FXML
    void delAllFromTask(ActionEvent event) {
        CollectionProduct.getInstance().deleteCollRes();
        CollectionProduct.getInstance().deleteAllPrTask();
        AmountPr1.clear();
        AmountPr2.clear();
        AmountPr3.clear();
        PrognozBlago.clear();
        PrognozFirmyBlago.clear();
        PrognozFirmyNeblago.clear();
        CollectionCatalog.getInstance().deleteAllFromTask();
    }

    @FXML
    void deleteCatalogFromTask(ActionEvent event) {
        CatalogInformation selectedCatalog = (CatalogInformation) TaskPrTable.getSelectionModel().getSelectedItem();
        CollectionCatalog.getInstance().deleteCatalogFromTask( selectedCatalog );
    }

    @FXML
    void initialize() {
        question1.setVisible(false);
        question2.setVisible(false);
        question3.setVisible(false);
        ram1.setVisible(false);
        ram2.setVisible(false);
        ram3.setVisible(false);
        reloadImage.setVisible(false);

        Table_Products.setEditable(true);
        productInTable();
        catalogInTable();
        initCompanyInfo();
        statInTable();
        initClock();
        closeButton.setOnMouseClicked(mouseEvent -> {
            Stage stages = (Stage) closeButton.getScene().getWindow();
            stages.close();
            Connect.send("back");
            Connect.send("exit");
            //Connect.send("exit");
        });

        hideButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.setIconified(true);
        });

        backButton.setOnMouseClicked(mouseEvent -> {
            backButton.getScene().getWindow().hide();

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

    void statInTable(){
        StatisticsCollection.getInstance().fillData();
        year_column.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("year"));
        revenue_column.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("revenue"));
        expenses_column.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("expenses"));
        profit_column.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("profit"));
        stat_table.setItems(StatisticsCollection.getInstance().getStat());
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss\nyyyy-MM-dd");
            DateLabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void initCompanyInfo(){
        String pf;
        pf = Connect.get();
        System.out.println(pf);
        paragraph.setText(pf);
    }

    @FXML
    public void deleteProduct() {
        ProductInformation selectedProduct = (ProductInformation) Table_Products.getSelectionModel().getSelectedItem();
        CollectionProduct.getInstance().delete(selectedProduct);
        Connect.send("deleteProduct");
        Connect.send(selectedProduct.getIdproduct());
    }

    @FXML
    public void deleteCatalog() {
        CatalogInformation selectedCatalog = (CatalogInformation) TableCatalog.getSelectionModel().getSelectedItem();
        CollectionCatalog.getInstance().delete(selectedCatalog);
        Connect.send("deleteCatalog");
        Connect.send(selectedCatalog.getIdcatalog());
    }

    @FXML
    void searchProduct() {
        FilteredList<ProductInformation> filterProduct;
        filterProduct = new FilteredList<>(CollectionProduct.getInstance().getProduct(), e->true);
        searchPrField.textProperty().addListener((observableValue, oldValue, newValue)->{
            filterProduct.setPredicate((ProductInformation prod)->{

                String newVal = newValue.toLowerCase();
                return  prod.getProductname().toLowerCase().contains(newVal)
                        || prod.getMaterial().toLowerCase().contains(newVal);
            });
            Table_Products.setItems(filterProduct);
        });
    }

    @FXML
    void searchInCatalog() {
        FilteredList<CatalogInformation> filterCatalogs;
        filterCatalogs = new FilteredList<>(CollectionCatalog.getInstance().getCatalogs(), e->true);
        searchInCatalogField.textProperty().addListener((observableValue, oldValue, newValue)->{
            filterCatalogs.setPredicate((CatalogInformation prod)->{
                String newVal = newValue.toLowerCase();
                return  prod.getProductname().toLowerCase().contains(newVal)
                        || prod.getMaterial().toLowerCase().contains(newVal);
            });
            TableCatalog.setItems(filterCatalogs);
        });
    }

    @FXML
    void addProduct(ActionEvent event) {
        boolean key = true;

        String productname = productName_field.getText();
        String material = MaterialField.getText();
        String cost_price = CostPriceField.getText();

        if( productName_field.getText().isEmpty() || productName_field.getText()==null
                || !checkWord(productName_field.getText()))
        {key = false;
            Shake productName_fieldAnim = new Shake(productName_field);
            productName_fieldAnim.playAnim();}

        if( MaterialField.getText().isEmpty() || MaterialField.getText()==null
                || !checkWord(MaterialField.getText())) ////////////////////////////////////////////////////////////////////////////////dopisal
        {key = false;
            Shake MaterialFieldAnim = new Shake(MaterialField);
            MaterialFieldAnim.playAnim();}
        if( CostPriceField.getText().isEmpty() || CostPriceField.getText()==null
                || !checkNum(CostPriceField.getText()))
        {key = false;
            Shake CostPriceFieldAnim = new Shake(CostPriceField);
            CostPriceFieldAnim.playAnim();}

        if (key) {
            Connect.send("addProduct");
            Product pr = new Product(productname, material, cost_price);
            Connect.send(pr);
            fillInTableNewProduct();

            productName_field.clear();
            MaterialField.clear();
            CostPriceField.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Действие совершено");
            alert.setHeaderText("Продукт успешно добавлен!");
            alert.showAndWait();

            ProductName.setCellValueFactory(new PropertyValueFactory<>("productname"));
            Material.setCellValueFactory(new PropertyValueFactory<>("material"));
            Cost_Price.setCellValueFactory(new PropertyValueFactory<>("cost_price"));

            NameProdToCatalog.setCellValueFactory(new PropertyValueFactory<>("productname"));
            MaterialProdToCatalog.setCellValueFactory(new PropertyValueFactory<>("material"));
            Cost_PriceProdToCatalog.setCellValueFactory(new PropertyValueFactory<>("cost_price"));
        }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Продукт не добавлен!");
                alert.showAndWait();
            }

    }

    void fillInTableNewProduct(){
        Connect.send("getNewProduct");
        CollectionProduct.getInstance().fillNewData();
        setProductInTable();
    }

    void productInTable(){
        CollectionProduct.getInstance().fillData();
        setProductInTable();
    }

    public void setProductInTable(){
        Number_Product.setCellValueFactory(new PropertyValueFactory<>("idproduct"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("productname"));
        Material.setCellValueFactory(new PropertyValueFactory<>("material"));
        Cost_Price.setCellValueFactory(new PropertyValueFactory<>("cost_price"));
        Table_Products.setItems(CollectionProduct.getInstance().getProduct());

        NameProdToCatalog.setCellValueFactory(new PropertyValueFactory<>("productname"));
        MaterialProdToCatalog.setCellValueFactory(new PropertyValueFactory<>("material"));
        Cost_PriceProdToCatalog.setCellValueFactory(new PropertyValueFactory<>("cost_price"));
        TableProdToCatalog.setItems(CollectionProduct.getInstance().getProduct());
    }

    @FXML
    void updateCompanyInfo(ActionEvent event) {
        Connect.send("updateCompanyInfo");

        String pf;
        pf = paragraph.getText();

        Connect.send(pf);
    }

    @FXML
    void addProdToCatalog(ActionEvent event) {
        boolean key = true;
        ProductInformation selectedProduct = (ProductInformation) TableProdToCatalog.getSelectionModel().getSelectedItem();
        String productIdproduct = String.valueOf(selectedProduct.getIdproduct());
        String sale_value = ProductSaleField.getText();
        if (sale_value.isEmpty()) {
            Shake Sale_Value_Anim = new Shake(ProductSaleField);
            Sale_Value_Anim.playAnim();
            key = false;
        }
        if(key){
            Connect.send("addProductToCatalog");
            Connect.send(sale_value);
            Connect.send(productIdproduct);

            fillInTableNewCatalog();

            ProductSaleField.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Действие совершено");
            alert.setHeaderText("Продукт добавлен в каталог!");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Продукт не добавлен в каталог!");
            alert.showAndWait();
        }
    }

    void fillInTableNewCatalog(){
        Connect.send("getNewCatalog");
        CollectionCatalog.getInstance().fillNewData();
        setCatalogInTable();
    }

    void setCatalogInTable(){
        NumberProdInCatalog.setCellValueFactory(new PropertyValueFactory<>("idcatalog"));
        NameProdInCatalog.setCellValueFactory(new PropertyValueFactory<>("productname"));
        MaterialProdInCatalog.setCellValueFactory(new PropertyValueFactory<>("material"));
        Cost_PriceProdInCatalog.setCellValueFactory(new PropertyValueFactory<>("cost_price"));
        Sale_ValueProdInCatalog.setCellValueFactory(new PropertyValueFactory<>("sale_value"));
        TableCatalog.setItems(CollectionCatalog.getInstance().getCatalogs());
    }

    void catalogInTable(){
        CollectionCatalog.getInstance().fillData();
        setCatalogInTable();
    }

    @FXML
    void editProduct(ActionEvent event) {
        Connect.send( "changeProduct" );
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

        Pattern pattern = Pattern.compile("^([А-Я][а-яё]+)$");
        Matcher matcher = pattern.matcher(source);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkNum1(String source) {

        Pattern pattern = Pattern.compile("^[0-9]{1,2}");
        Matcher matcher = pattern.matcher(source);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

}
