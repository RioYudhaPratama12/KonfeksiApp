package app.controller;

import app.model.modelTitleBar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class titleBarController implements Initializable {


    @FXML
    private Button textTanggal;

    @FXML
    private Label lblAdmin;

    @FXML
    private Label lblAdmin2;

    modelTitleBar model = new modelTitleBar();
    LoginController lc = new LoginController();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initClock();
        setValueModel();



    }

    public void min(MouseEvent mouseEvent) {
        Node node= (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);

    }

    public void max(MouseEvent mouseEvent) {
        Node node= (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setFullScreen(true);
    }

    public void close(MouseEvent mouseEvent) {
        Node node= (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }


    public void logout(MouseEvent mouseEvent) throws IOException {
        TrayNotification tray = new TrayNotification();


        tray.setNotificationType(NotificationType.CUSTOM);
        tray.setTitle("LogOut Success");
        tray.setMessage("Anda Telah Keluar dari KonfeksiApp.");
        tray.setAnimationType(AnimationType.FADE);
        tray.showAndDismiss(Duration.millis(2000));
        tray.setRectangleFill(Color.valueOf("#303030"));
        tray.setImage(new Image("/app/images/iconkonfeksiapp.png"));


        Parent root = FXMLLoader.load(getClass().getResource("/app/view/login.fxml"));
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD MMMM YYYY HH:mm:ss");
            textTanggal.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    private void setValueModel(){
        model.setValue(lblAdmin2.getText());
        lblAdmin.setText(model.getUsername());
   //     lc.value();
        lblAdmin.setText("admin");
    //    model.setValue(idUser.getText());
  //      lblAdmin.setText(model.getUsername());
   //     emailUser.setText(model.getEmail());
  //      namaUser.setText(model.getNama());
    //    levelUser.setText(model.getLevel());

      //  setMenu();
    }

}
