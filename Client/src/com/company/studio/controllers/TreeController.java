package com.company.studio.controllers;

import com.company.studio.behavior.Tree;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class TreeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView Poetic;

    @FXML
    private GridPane prognozfirmyblago1;

    @FXML
    private GridPane neprognozfirmyblago1;

    @FXML
    private GridPane prognozfirmyblago2;

    @FXML
    private GridPane neprognozfirmyblago2;

    @FXML
    private GridPane prognozfirmyblago3;

    @FXML
    private GridPane neprognozfirmyblago3;

    @FXML
    private GridPane neprognozfirmyneblago1;

    @FXML
    private GridPane prognozfirmyneblago1;

    @FXML
    private GridPane neprognozfirmyneblago2;

    @FXML
    private GridPane prognozfirmyneblago2;

    @FXML
    private GridPane neprognozfirmyneblago3;

    @FXML
    private GridPane prognozfirmyneblago3;

    @FXML
    private GridPane blago11;

    @FXML
    private GridPane neblago11;

    @FXML
    private GridPane blago21;

    @FXML
    private GridPane neblago21;

    @FXML
    private GridPane blago31;

    @FXML
    private GridPane neblago31;

    @FXML
    private GridPane blago12;

    @FXML
    private GridPane neblago12;

    @FXML
    private GridPane blago22;

    @FXML
    private GridPane neblago22;

    @FXML
    private GridPane blago32;

    @FXML
    private GridPane neblago32;

    @FXML
    private GridPane a4Pane;

    @FXML
    private GridPane a5Pane;

    @FXML
    private GridPane a6Pane;

    @FXML
    private GridPane a7Pane;

    @FXML
    private GridPane a8Pane;

    @FXML
    private GridPane a9Pane;

    @FXML
    private GridPane max3Pane;

    @FXML
    private GridPane max2Pane;

    @FXML
    private GridPane MAXPane;

    @FXML
    private GridPane prognozblagoPane;

    @FXML
    private GridPane neprognozblagoPane;
    @FXML
    void initialize()  {
        Tree array = new Tree();
        JSONObject object = new JSONObject(array);

        Float prognozfirmyblago = object.getFloat("prognozfirmyblago");
        Float neprognozfirmyblago = object.getFloat("neprognozfirmyblago");
        Float neprognozfirmyneblago = object.getFloat("neprognozfirmyneblago");
        Float prognozfirmyneblago = object.getFloat("prognozfirmyneblago");
        Integer blago1 = object.getInt("blago1");
        Integer blago2 = object.getInt("blago2");
        Integer blago3 = object.getInt("blago3");
        Integer neblago1 = object.getInt("neblago1");
        Integer neblago2 = object.getInt("neblago2");
        Integer neblago3 = object.getInt("neblago3");
        Float a4 = object.getFloat("a4");
        Float a5 = object.getFloat("a5");
        Float a6 = object.getFloat("a6");
        Float a7 = object.getFloat("a7");
        Float a8 = object.getFloat("a8");
        Float a9 = object.getFloat("a9");
        Float max2 = object.getFloat("max2");
        Float max3 = object.getFloat("max3");
        Float prognozblago = object.getFloat("prognozblago");
        Float neprognozblago = object.getFloat("neprognozblago");
        Float max = object.getFloat("max");

        String sprognozfirmyblago = prognozfirmyblago.toString();
        String sneprognozfirmyblago = neprognozfirmyblago.toString();
        String sneprognozfirmyneblago = neprognozfirmyneblago.toString();
        String sprognozfirmyneblago = prognozfirmyneblago.toString();
        String sa4 = a4.toString();
        String sa5 = a5.toString();
        String sa6 = a6.toString();
        String sa7 = a7.toString();
        String sa8 = a8.toString();
        String sa9 = a9.toString();
        String smax2 = max2.toString();
        String smax3 = max3.toString();
        String sprognozblago = prognozblago.toString();
        String sneprognozblago = neprognozblago.toString();
        String smax = max.toString();

        Label label1 = new Label(String.format("%.6s", prognozfirmyblago));
        label1.setFont(new Font("Arial", 14));
        prognozfirmyblago1.add(label1, 0, 0);

        Label label2 = new Label(String.format("%.6s", prognozfirmyblago));
        label2.setFont(new Font("Arial", 14));
        prognozfirmyblago2.add(label2, 0, 0);

        Label label3 = new Label(String.format("%.6s", prognozfirmyblago));
        label3.setFont(new Font("Arial", 14));
        prognozfirmyblago3.add(label3, 0, 0);

        Label label4 = new Label(String.format("%.6s", prognozfirmyneblago));
        label4.setFont(new Font("Arial", 14));
        prognozfirmyneblago1.add(label4, 0, 0);

        Label label5 = new Label(String.format("%.6s", prognozfirmyneblago));
        label5.setFont(new Font("Arial", 14));
        prognozfirmyneblago2.add(label5, 0, 0);

        Label label6 = new Label(String.format("%.6s", prognozfirmyneblago));
        label6.setFont(new Font("Arial", 14));
        prognozfirmyneblago3.add(label6, 0, 0);

        Label label7 = new Label(String.format("%.5s", neprognozfirmyblago));
        label7.setFont(new Font("Arial", 14));
        neprognozfirmyblago1.add(label7, 0, 0);

        Label label8 = new Label(String.format("%.5s", neprognozfirmyblago));
        label8.setFont(new Font("Arial", 14));
        neprognozfirmyblago2.add(label8, 0, 0);

        Label label9 = new Label(String.format("%.5s", neprognozfirmyblago));
        label9.setFont(new Font("Arial", 14));
        neprognozfirmyblago3.add(label9, 0, 0);

        Label label10 = new Label(String.format("%.5s", neprognozfirmyneblago));
        label10.setFont(new Font("Arial", 14));
        neprognozfirmyneblago1.add(label10, 0, 0);

        Label label11 = new Label(String.format("%.5s", neprognozfirmyneblago));
        label11.setFont(new Font("Arial", 14));
        neprognozfirmyneblago2.add(label11, 0, 0);

        Label label12 = new Label(String.format("%.5s", neprognozfirmyneblago));
        label12.setFont(new Font("Arial", 14));
        neprognozfirmyneblago3.add(label12, 0, 0);

        Label label13 = new Label(String.format("%d", blago1));
        label13.setFont(new Font("Arial", 14));
        blago11.add(label13, 0, 0);

        Label label14 = new Label(String.format("%d", blago2));
        label14.setFont(new Font("Arial", 14));
        blago21.add(label14, 0, 0);

        Label label15 = new Label(String.format("%d", blago3));
        label15.setFont(new Font("Arial", 14));
        blago31.add(label15, 0, 0);

        Label label16 = new Label(String.format("%d", neblago1));
        label16.setFont(new Font("Arial", 14));
        neblago11.add(label16, 0, 0);

        Label label17 = new Label(String.format("%d", neblago2));
        label17.setFont(new Font("Arial", 14));
        neblago21.add(label17, 0, 0);

        Label label18 = new Label(String.format("%d", neblago3));
        label18.setFont(new Font("Arial", 14));
        neblago31.add(label18, 0, 0);

        Label label19 = new Label(String.format("%d", blago1));
        label19.setFont(new Font("Arial", 14));
        blago12.add(label19, 0, 0);

        Label label20 = new Label(String.format("%d", blago2));
        label20.setFont(new Font("Arial", 14));
        blago22.add(label20, 0, 0);

        Label label21 = new Label(String.format("%d", blago3));
        label21.setFont(new Font("Arial", 14));
        blago32.add(label21, 0, 0);

        Label label22 = new Label(String.format("%d", neblago1));
        label22.setFont(new Font("Arial", 14));
        neblago12.add(label22, 0, 0);

        Label label23 = new Label(String.format("%.4s", neblago2));
        label23.setFont(new Font("Arial", 14));
        neblago22.add(label23, 0, 0);

        Label label24 = new Label(String.format("%.4s", neblago3));
        label24.setFont(new Font("Arial", 14));
        neblago32.add(label24, 0, 0);

        Label label25 = new Label(String.format("%.10s", a4));
        label25.setFont(new Font("Arial", 14));
        a4Pane.add(label25, 0, 0);

        Label label26 = new Label(String.format("%.10s", a5));
        label26.setFont(new Font("Arial", 14));
        a5Pane.add(label26, 0, 0);

        Label label27 = new Label(String.format("%.10s", a6));
        label27.setFont(new Font("Arial", 14));
        a6Pane.add(label27, 0, 0);

        Label label28 = new Label(String.format("%.10s", a7));
        label28.setFont(new Font("Arial", 14));
        a7Pane.add(label28, 0, 0);

        Label label29 = new Label(String.format("%.10s", a8));
        label29.setFont(new Font("Arial", 14));
        a8Pane.add(label29, 0, 0);

        Label label30 = new Label(String.format("%.10s", a9));
        label30.setFont(new Font("Arial", 14));
        a9Pane.add(label30, 0, 0);

        Label label31 = new Label(String.format("%.5s", prognozblago));
        label31.setFont(new Font("Arial", 14));
        prognozblagoPane.add(label31, 0, 0);

        Label label32 = new Label(String.format("%.5s", neprognozblago));
        label32.setFont(new Font("Arial", 14));
        neprognozblagoPane.add(label32, 0, 0);

        Label label33 = new Label(String.format("%.10s", max));
        label33.setFont(new Font("Arial", 14));
        MAXPane.add(label33, 0, 0);

        Label label34 = new Label(String.format("%.10s", max2));
        label34.setFont(new Font("Arial", 14));
        max2Pane.add(label34, 0, 0);

        Label label35 = new Label(String.format("%.10s", max3));
        label35.setFont(new Font("Arial", 14));
        max3Pane.add(label35, 0, 0);



    animat an = new animat(Poetic);
    an.playAnim();
    }
    public class animat {
        private TranslateTransition down;
        private RotateTransition rot;
        private ScaleTransition sc;
        private SequentialTransition seq;
        public animat (Node node) {

            down = new TranslateTransition(Duration.seconds(6), node);
            down.setByY(0f);
            down.setByX(470f);
            down.setAutoReverse(true);
            down.setCycleCount(2);
            rot = new RotateTransition(Duration.seconds(6), node);
            rot.setByAngle(720f);
            rot.setCycleCount(2);
            rot.setAutoReverse(true);
            sc = new ScaleTransition(Duration.seconds(4), node);
            sc.setByX(300f);
            sc.setByY(300f);

            seq = new SequentialTransition();
            seq.getChildren().addAll(down, rot, sc);
            seq.setCycleCount(74);
            seq.setAutoReverse(true);

        }
        public void playAnim() {
        seq.playFromStart();
        }
    }
}
