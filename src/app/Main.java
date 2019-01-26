package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("view/home.fxml"));
        primaryStage.setTitle("Konfeksi App");
        primaryStage.setScene(new Scene(root));
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        javafx.scene.image.Image icon = new Image(getClass().getResourceAsStream("/app/images/icons8_Yarn_100px_1.png"));
        primaryStage.getIcons().add(icon);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
