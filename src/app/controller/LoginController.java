package app.controller;

import app.database.DbConnect;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import app.model.modelLogin;



public class LoginController implements Initializable {

    @FXML
    private JFXTextField tf_username;

    @FXML
    private JFXPasswordField pf_password;

    @FXML
    void login2 (MouseEvent mouseEvent)throws IOException, SQLException {
        TrayNotification tray = new TrayNotification();


        String username = tf_username.getText();
        String password = pf_password.getText();

        Connection connection = DbConnect.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM admin WHERE username" +
                " = '" + username + "' and password = '" + password + "'");


        if (resultSet.next()) {

            tray.setNotificationType(NotificationType.CUSTOM);
            tray.setTitle("Login Success");
            tray.setMessage("Hello "+tf_username.getText()+", Welcome to KonfeksiApp.");
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(Duration.millis(2000));
            tray.setRectangleFill(Color.valueOf("#303030"));
            tray.setImage(new Image("/app/images/iconkonfeksiapp.png"));


            Parent root = FXMLLoader.load(getClass().getResource("/app/view/home.fxml"));
            Node node = (Node) mouseEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();

        }



    }

    public void value(){
        tf_username.getText();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    double x = 0, y = 0;

    public void dragged(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - x);
        stage.setY(mouseEvent.getScreenY() - y);


    }

    public void pressed(MouseEvent mouseEvent) {
        x = mouseEvent.getSceneX();
        y = mouseEvent.getSceneY();
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


    private void Login3(MouseEvent mouseEvent) throws IOException {
        TrayNotification tray = new TrayNotification();
        String username_text = tf_username.getText();
        String password_text = pf_password.getText();
        if (username_text.equals("") || password_text.equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan :");
            alert.setHeaderText("Silahkan masukkan username dan password terlebih dahulu !!\"");
            alert.showAndWait();

            tf_username.requestFocus();
        } else {
            modelLogin log = new modelLogin();
            log.setLogin(username_text, password_text);
            if (tf_username != null) {
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setTitle("Login Success");
                tray.setMessage("Hello " + log.getNama_lengkap() + ". Welcome to KonfeksiApp.");
                tray.setAnimationType(AnimationType.FADE);
                tray.showAndDismiss(Duration.millis(1500));
                tray.setRectangleFill(Color.valueOf("#4183D7"));
                tray.setImage(new Image("/img/icons8_Male_User_100px_2.png"));

                Parent root = FXMLLoader.load(getClass().getResource("/app/view/home.fxml"));
                Node node = (Node) mouseEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.centerOnScreen();

            } else {


                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error :");
                alert.setHeaderText("Username dan Password yang anda masukkan salah !!");
                alert.showAndWait();
                tf_username.setText("");
                pf_password.setText("");
                tf_username.requestFocus();
            }
        }
    }

    public void login(MouseEvent mouseEvent) throws IOException, SQLException {

        TrayNotification tray = new TrayNotification();
        String username = tf_username.getText();
        String password = pf_password.getText();
        if (username.equals("") || password.equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan :");
            alert.setHeaderText("Silahkan masukkan Username dan Password terlebih dahulu !!\"");
            alert.showAndWait();

            tf_username.requestFocus();

        } else {
            modelLogin log = new modelLogin();
            log.setLogin(username, password);
            Connection connection = DbConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM admin WHERE username" +
                    " = '" + username + "' and password = '" + password + "'");

            if (resultSet.next()) {
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setTitle("Login Success");
                tray.setMessage("Hello "+tf_username.getText()+" Welcome to KonfeksiApp.");
                tray.setAnimationType(AnimationType.FADE);
                tray.showAndDismiss(Duration.millis(2000));
                tray.setRectangleFill(Color.valueOf("#303030"));
                tray.setImage(new Image("/app/images/iconkonfeksiapp.png"));

                Parent root = FXMLLoader.load(getClass().getResource("/app/view/home.fxml"));
                Node node = (Node) mouseEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.centerOnScreen();

            } else {


                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error :");
                alert.setHeaderText("Username dan Password yang anda masukkan salah !!");
                alert.showAndWait();
                tf_username.setText("");
                pf_password.setText("");
                tf_username.requestFocus();
            }
        }

    }
}






