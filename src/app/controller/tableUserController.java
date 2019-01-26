package app.controller;

import app.database.DbConnect;
import app.model.modelTableUser;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tableUserController implements Initializable {


    private PreparedStatement pst;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
        validation();
    }



    @FXML
    private TableView<modelTableUser> table_user;

    @FXML
    private TableColumn<?, ?> col_user_username;

    @FXML
    private TableColumn<?, ?> col_user_password;

    @FXML
    private TableColumn<?, ?> col_user_namalengkap;

    @FXML
    private TableColumn<?, ?> col_user_jabatan;

    @FXML
    ObservableList<modelTableUser> oblist = FXCollections.observableArrayList();

    @FXML
    private TextField tf_filterDataTableUser;

    @FXML
    private JFXTextField tfUsername;

    @FXML
    private JFXPasswordField pfPassword;

    @FXML
    private JFXTextField tfNamaLengkap;

    @FXML
    private JFXTextField tfJabatan;


    public void tambahUser(MouseEvent mouseEvent) {
        Connection connection = DbConnect.getInstance().getConnection();

        try {

            String username = tfUsername.getText();
            String password = pfPassword.getText();
            String nama_lengkap = tfNamaLengkap.getText();
            String jabatan = tfJabatan.getText();


            Statement statement = connection.createStatement();

            int status = statement.executeUpdate("insert into admin (username,password,nama_lengkap,jabatan)" +
                    " values('" + username + "','" + password + "','" + nama_lengkap + "','" + jabatan + "')");

            if (status > 0) {


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText("Data User Telah Ditambahkan.");
                alert.showAndWait();

                TrayNotification tray = new TrayNotification();
                tray.setTitle("Tambah Data User Success");
                tray.setMessage("Data User "+tfNamaLengkap.getText()+" Telah Ditambahkan");
                tray.setAnimationType(AnimationType.FADE);
                tray.showAndDismiss(Duration.millis(2000));
                tray.setRectangleFill(Color.valueOf("#303030"));
                tray.setImage(new Image("/app/images/icons8_Add_User_Group_Man_Man_96px.png"));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        refresh();


    }


    public void updateUser(MouseEvent mouseEvent) {
        String query="update admin set username= ?, password= ?, nama_lengkap= ?, jabatan=? where username='"+tempUsername+"'";
        Connection connection = DbConnect.getInstance().getConnection();

        try {

            pst=connection.prepareStatement((query));
            pst.setString(1, tfUsername.getText());
            pst.setString(2, pfPassword.getText());
            pst.setString(3, tfNamaLengkap.getText());
            pst.setString(4, tfJabatan.getText());
            pst.execute();
            pst.close();
            refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText("Data User Telah Diperbarui.");
            alert.showAndWait();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Update Data User Success");
            tray.setMessage("Data User "+tfNamaLengkap.getText()+" Telah Diperbarui");
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(Duration.millis(2000));
            tray.setRectangleFill(Color.valueOf("#303030"));
            tray.setImage(new Image("/app/images/icons8_Add_User_Group_Man_Man_96px.png"));



        } catch (SQLException e) {
            e.printStackTrace();
        }

        refresh();


    }



    // Reset atau membersihkan inputan
    public void resetUser(MouseEvent mouseEvent) {

        tfUsername.setText("");
        pfPassword.setText("");
        tfNamaLengkap.setText("");
        tfJabatan.setText("");

    }


    //Menampilkan data user pada table dari database

    public void refreshTableUser(MouseEvent mouseEvent) {
        oblist.clear();

        try {

            Connection connection = DbConnect.getInstance().getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM admin ");
            while (resultSet.next()) {

                oblist.add(new modelTableUser(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("nama_lengkap"),
                        resultSet.getString("jabatan")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(tableUserController.class.getName()).log(Level.SEVERE, null, ex);

        }


        col_user_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_user_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_user_namalengkap.setCellValueFactory(new PropertyValueFactory<>("nama_lengkap"));
        col_user_jabatan.setCellValueFactory(new PropertyValueFactory<>("jabatan"));

        table_user.setItems(oblist);

    }

    void refresh() {
        oblist.clear();

        try {

            Connection connection = DbConnect.getInstance().getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM admin ");
            while (resultSet.next()) {

                oblist.add(new modelTableUser(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("nama_lengkap"),
                        resultSet.getString("jabatan")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(tableUserController.class.getName()).log(Level.SEVERE, null, ex);


        }


        col_user_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_user_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_user_namalengkap.setCellValueFactory(new PropertyValueFactory<>("nama_lengkap"));
        col_user_jabatan.setCellValueFactory(new PropertyValueFactory<>("jabatan"));

        table_user.setItems(oblist);

    }


    public void clearFilterTableUser(MouseEvent mouseEvent) {
        tf_filterDataTableUser.setText("");

    }

    // Filter Data Pada Table
    public void filterDataUser(KeyEvent keyEvent) {
        FilteredList<modelTableUser> filteredData = new FilteredList<>(oblist, e -> true);
        tf_filterDataTableUser.setOnKeyReleased(e -> {
            tf_filterDataTableUser.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super modelTableUser>) user -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (user.getUsername().contains(newValue)) {
                        return true;
                    } else if (user.getPassword().contains(lowerCaseFilter)) {
                        return true;
                    } else if (user.getNama_lengkap().contains(lowerCaseFilter)) {
                        return true;
                    } else if (user.getJabatan().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;
                });


            });
            SortedList<modelTableUser> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table_user.comparatorProperty());
            table_user.setItems(sortedData);


        });


    }


    public void deleteItem(MouseEvent mouseEvent) throws SQLException {
        try {


            modelTableUser user = (modelTableUser) table_user.getSelectionModel().getSelectedItem();


            int selectedIndex = table_user.getSelectionModel().getSelectedIndex();
            String selectedItem = col_user_username.getText();
            if (selectedIndex >= 0) {
                Connection connection = DbConnect.getInstance().getConnection();
                String query1 = "DELETE FROM admin WHERE username = ?";
                System.out.println(selectedItem);
                pst = connection.prepareStatement(query1);
                pst.setString(1, user.getUsername());
                pst.executeUpdate();
                System.out.println(selectedItem);
                pst.close();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("KONFIRMASI :");
                alert.setHeaderText("Apakah Anda Yakin Akan Menghapus Data Ini ?");
                alert.showAndWait();


                TrayNotification tray = new TrayNotification();
                tray.setTitle("Delete Data User Success");
                tray.setMessage("Data User "+tfNamaLengkap.getText()+" Telah Dihapus");
                tray.setAnimationType(AnimationType.FADE);
                tray.showAndDismiss(Duration.millis(2000));
                tray.setRectangleFill(Color.valueOf("#303030"));
                tray.setImage(new Image("/app/images/icons8_Delete_Bin_48px.png"));

                table_user.getItems().remove(selectedIndex);


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR:");
                alert.setHeaderText("No selection was made.");
                alert.setContentText("You have not selected an item to delete. Please try again.");
                alert.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(tableUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void exportPdf(MouseEvent mouseEvent) {
    }

    public void exportExcel(MouseEvent mouseEvent) {
    }


    public void validation() {

        if (tfUsername.getText() == null || tfUsername.getText().trim().isEmpty()) {
            // your code here
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Belum Isi");
            alert.setHeaderText("TIdak Ada Data yang Dipilih");
            alert.setContentText("Mohon Pilih Salah Satu Data Pada Tabel.");

        }

    }


    static String tempUsername;

    public void showOnClick()
    {
        try {


            modelTableUser user = (modelTableUser) table_user.getSelectionModel().getSelectedItem();
            table_user.getSelectionModel().getSelectedIndex();

                Connection connection = DbConnect.getInstance().getConnection();
                String query1 = "select * FROM admin WHERE username = ?";

                pst = connection.prepareStatement(query1);
                tempUsername=user.getUsername();
                tfUsername.setText(user.getUsername());
                pfPassword.setText(user.getPassword());
                tfNamaLengkap.setText(user.getNama_lengkap());
                tfJabatan.setText(user.getJabatan());

                pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(tableUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void resetCari(MouseEvent mouseEvent) {
    }
}









