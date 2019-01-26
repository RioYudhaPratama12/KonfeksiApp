package app.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnInput;

    @FXML
    private JFXButton btnStatus;

    @FXML
    private JFXButton btnLaporan;

    @FXML
    private JFXButton btnSablon;

    @FXML
    private JFXButton btnBahan;

    @FXML
    private JFXButton btnUser;

    @FXML
    private JFXButton btnTentang;

    @FXML
    private AnchorPane pnHome;

    @FXML
    private AnchorPane pnInput;

    @FXML
    private AnchorPane pnStatus;

    @FXML
    private AnchorPane pnUser;

    @FXML
    private AnchorPane pnTentang;

    @FXML
    private AnchorPane pnBahan;

    @FXML
    private AnchorPane pnSablon;

    @FXML
    private AnchorPane pnLaporan;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
     //   tampilTanggal();

    }


    public void handleClick(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnHome) {
            pnHome.toFront();
        }
        if (actionEvent.getSource() == btnInput) {
            pnInput.toFront();
        }
        if (actionEvent.getSource() == btnStatus) {
            pnStatus.toFront();
        }

        if (actionEvent.getSource() == btnSablon) {
            pnSablon.toFront();
        }
        if (actionEvent.getSource() == btnBahan) {
            pnBahan.toFront();
        }
        if (actionEvent.getSource() == btnLaporan) {
            pnLaporan.toFront();
        }
        if (actionEvent.getSource() == btnUser) {
            pnUser.toFront();
        }
        if (actionEvent.getSource() == btnTentang) {
            pnTentang.toFront();
        }


    }





    double x=0,y=0;
    public void dragged(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - x);
        stage.setY(mouseEvent.getScreenY()- y);
    }

    public void pressed(MouseEvent mouseEvent) {
        x=mouseEvent.getSceneX();
        y=mouseEvent.getSceneY();
    }







}
