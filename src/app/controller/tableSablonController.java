package app.controller;

import app.database.DbConnect;
import app.model.modelTableSablon;
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

public class tableSablonController<modelTablesablon> implements Initializable {


    private PreparedStatement pst;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();

    }

    ///

    @FXML
    private TableView<modelTableSablon> table_sablon;

    @FXML
    private TableColumn<?, ?> col_sablon_kode_sablon;

    @FXML
    private TableColumn<?, ?> col_sablon_jenis_sablon;

    @FXML
    private TableColumn<?, ?> col_sablon_ukuran_sablon;

    @FXML
    private TableColumn<?, ?> col_sablon_harga_sablon;

    @FXML
    private TextField tf_filterDataTableSablon;

    @FXML
    private JFXTextField tfKodeSablon;

    @FXML
    private JFXTextField tfJenisSablon;

    @FXML
    private JFXTextField tfUkuranSablon;

    @FXML
    private JFXTextField tfHargaSablon;

    ///


    @FXML
    ObservableList<modelTableSablon> oblist = FXCollections.observableArrayList();



    @FXML
    void resetCari(MouseEvent event) {

    }


    public void tambahSablon(MouseEvent mouseEvent) {
        Connection connection = DbConnect.getInstance().getConnection();

        try {

            String kode_sablon = tfKodeSablon.getText();
            String jenis_sablon = tfJenisSablon.getText();
            String ukuran_sablon = tfUkuranSablon.getText();
            String harga_sablon = tfHargaSablon.getText();


            Statement statement = connection.createStatement();

            int status = statement.executeUpdate("insert into sablon (kode_sablon,jenis_sablon,ukuran_sablon,harga_sablon)" +
                    " values('" + kode_sablon + "','" + jenis_sablon+ "','" + ukuran_sablon+ "','" + harga_sablon + "')");

            if (status > 0) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText("Data Sablon Telah Ditambahkan.");
                alert.showAndWait();

                TrayNotification tray = new TrayNotification();
                tray.setTitle("Tambah Data Sablon Success");
                tray.setMessage("Data Sablon "+tfKodeSablon.getText()+" Telah Ditambahkan");
                tray.setAnimationType(AnimationType.FADE);
                tray.showAndDismiss(Duration.millis(2000));
                tray.setRectangleFill(Color.valueOf("#303030"));
                tray.setImage(new Image("/app/images/icons8_Paint_Palette_96px_1.png"));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        refresh();


    }


    public void updateSablon(MouseEvent mouseEvent) {
        String query="update sablon set kode_sablon= ?, jenis_sablon= ?, ukuran_sablon= ?, harga_sablon=? where kode_sablon='"+tempkodesablon+"'";
        Connection connection = DbConnect.getInstance().getConnection();

        try {

            pst=connection.prepareStatement((query));
            pst.setString(1, tfKodeSablon.getText());
            pst.setString(2, tfJenisSablon.getText());
            pst.setString(3, tfUkuranSablon.getText());
            pst.setString(4, tfHargaSablon.getText());
            pst.execute();
            pst.close();
            refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText("Data Sablon Telah Diperbarui.");
            alert.showAndWait();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Update Data Sablon Success");
            tray.setMessage("Data Sablon "+tfKodeSablon.getText()+" Telah Diperbarui");
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(Duration.millis(2000));
            tray.setRectangleFill(Color.valueOf("#303030"));
            tray.setImage(new Image("/app/images/icons8_Paint_Palette_96px_1.png"));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        refresh();


    }



    // Reset atau membersihkan inputan
    public void resetsablon(MouseEvent mouseEvent) {

        tfKodeSablon.setText("");
        tfJenisSablon.setText("");
        tfUkuranSablon.setText("");
        tfHargaSablon.setText("");

    }


    //Menampilkan data pada table dari database

    public void refreshTableSablon(MouseEvent mouseEvent) {
        oblist.clear();

        try {

            Connection connection = DbConnect.getInstance().getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM sablon ");
            while (resultSet.next()) {

                oblist.add(new modelTableSablon(
                        resultSet.getString("kode_sablon"),
                        resultSet.getString("jenis_sablon"),
                        resultSet.getString("ukuran_sablon"),
                        resultSet.getString("harga_sablon")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(tableSablonController.class.getName()).log(Level.SEVERE, null, ex);

        }


        col_sablon_kode_sablon.setCellValueFactory(new PropertyValueFactory<>("kode_sablon"));
        col_sablon_jenis_sablon.setCellValueFactory(new PropertyValueFactory<>("jenis_sablon"));
        col_sablon_ukuran_sablon.setCellValueFactory(new PropertyValueFactory<>("ukuran_sablon"));
        col_sablon_harga_sablon.setCellValueFactory(new PropertyValueFactory<>("harga_sablon"));

        table_sablon.setItems(oblist);

    }

    void refresh() {
        oblist.clear();

        try {

            Connection connection = DbConnect.getInstance().getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM sablon ");
            while (resultSet.next()) {

                oblist.add(new modelTableSablon(
                        resultSet.getString("kode_sablon"),
                        resultSet.getString("jenis_sablon"),
                        resultSet.getString("ukuran_sablon"),
                        resultSet.getString("harga_sablon")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(tableSablonController.class.getName()).log(Level.SEVERE, null, ex);


        }


        col_sablon_kode_sablon.setCellValueFactory(new PropertyValueFactory<>("kode_sablon"));
        col_sablon_jenis_sablon.setCellValueFactory(new PropertyValueFactory<>("jenis_sablon"));
        col_sablon_ukuran_sablon.setCellValueFactory(new PropertyValueFactory<>("ukuran_sablon"));
        col_sablon_harga_sablon.setCellValueFactory(new PropertyValueFactory<>("harga_sablon"));

        table_sablon.setItems(oblist);

    }



    // Filter Data Pada Table
    public void filterDataSablon(KeyEvent keyEvent) {
        FilteredList<modelTableSablon> filteredData = new FilteredList<>(oblist, e -> true);
        tf_filterDataTableSablon.setOnKeyReleased(e -> {
            tf_filterDataTableSablon.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super modelTableSablon>) sablon -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (sablon.getKode_sablon().contains(newValue)) {
                        return true;
                    } else if (sablon.getJenis_sablon().contains(lowerCaseFilter)) {
                        return true;
                    } else if (sablon.getUkuran_sablon().contains(lowerCaseFilter)) {
                        return true;
                    } else if (sablon.getHarga_sablon().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;
                });


            });
            SortedList<modelTableSablon> sortedData = new SortedList<modelTableSablon>(filteredData);
            sortedData.comparatorProperty().bind(table_sablon.comparatorProperty());
            table_sablon.setItems(sortedData);


        });


    }


    public void deleteItem(MouseEvent mouseEvent) throws SQLException {
        try {

            modelTableSablon sablon =(modelTableSablon) table_sablon.getSelectionModel().getSelectedItem();


            int selectedIndex = table_sablon.getSelectionModel().getSelectedIndex();
            String selectedItem = col_sablon_kode_sablon.getText();
            if (selectedIndex >= 0) {
                Connection connection = DbConnect.getInstance().getConnection();
                String query1 = "DELETE FROM sablon WHERE kode_sablon = ?";
                System.out.println(selectedItem);
                pst = connection.prepareStatement(query1);
                pst.setString(1, sablon.getKode_sablon());
                pst.executeUpdate();
                System.out.println(selectedItem);
                pst.close();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("KONFIRMASI :");
                alert.setHeaderText("Apakah Anda Yakin Akan Menghapus Data Ini ?");
                alert.showAndWait();

                TrayNotification tray = new TrayNotification();
                tray.setTitle("Delete Data Sablon Success");
                tray.setMessage("Data Sablon "+tfKodeSablon.getText()+" Telah Dihapus");
                tray.setAnimationType(AnimationType.FADE);
                tray.showAndDismiss(Duration.millis(2000));
                tray.setRectangleFill(Color.valueOf("#303030"));
                tray.setImage(new Image("/app/images/icons8_Delete_Bin_48px.png"));

                table_sablon.getItems().remove(selectedIndex);


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR:");
                alert.setHeaderText("No selection was made.");
                alert.setContentText("You have not selected an item to delete. Please try again.");
                alert.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(tableSablonController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void editData(MouseEvent mouseEvent) {


    }




    public void exportPdf(MouseEvent mouseEvent) {
    }

    ///

    public void exportExcel(MouseEvent mouseEvent) {



    }




    public void clearFilterTableSablon(MouseEvent mouseEvent) {
        tf_filterDataTableSablon.setText("");

    }

    public void resetSablon(MouseEvent mouseEvent) {
        tfKodeSablon.setText("");
        tfJenisSablon.setText("");
        tfUkuranSablon.setText("");
        tfHargaSablon.setText("");

    }


    static String tempkodesablon;

    public void showOnClick()
    {
        try {


            modelTableSablon bahan = (modelTableSablon) table_sablon.getSelectionModel().getSelectedItem();
            table_sablon.getSelectionModel().getSelectedIndex();

            Connection connection = DbConnect.getInstance().getConnection();
            String query = "select * FROM sablon WHERE kode_sablon = ?";

            pst = connection.prepareStatement(query);
            tempkodesablon=bahan.getKode_sablon();
            tfKodeSablon.setText(bahan.getKode_sablon());
            tfJenisSablon.setText(bahan.getJenis_sablon());
            tfUkuranSablon.setText(bahan.getUkuran_sablon());
            tfHargaSablon.setText(bahan.getHarga_sablon());

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(tableSablonController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}



