package app.controller;

import app.database.DbConnect;
import app.model.modelTableBahan;
import app.model.modelTableUser;
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

public class tableBahanController implements Initializable {


    private PreparedStatement pst;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();

    }

    ///

    @FXML
    private TableView<modelTableBahan> table_bahan;

    @FXML
    private TableColumn<?, ?> col_bahan_kode_bahan;

    @FXML
    private TableColumn<?, ?> col_bahan_jenis_produk;

    @FXML
    private TableColumn<?, ?> col_bahan_jenis_bahan;

    @FXML
    private TableColumn<?, ?> col_bahan_harga_bahan;

    @FXML
    private TextField tf_filterDataTableBahan;

    @FXML
    private JFXTextField tfKodeBahan;

    @FXML
    private JFXTextField tfJenisProduk;

    @FXML
    private JFXTextField tfJenisBahan;

    @FXML
    private JFXTextField tfHargaBahan;

    ///


    @FXML
    ObservableList<modelTableBahan> oblist = FXCollections.observableArrayList();



    @FXML
    void resetCari(MouseEvent event) {

    }


    public void tambahBahan(MouseEvent mouseEvent) {
        Connection connection = DbConnect.getInstance().getConnection();

        try {

            String kode_bahan = tfKodeBahan.getText();
            String jenis_produk = tfJenisProduk.getText();
            String jenis_bahan = tfJenisBahan.getText();
            String harga_bahan = tfHargaBahan.getText();


            Statement statement = connection.createStatement();

            int status = statement.executeUpdate("insert into bahan (kode_bahan,jenis_produk,jenis_bahan,harga_bahan)" +
                    " values('" + kode_bahan + "','" + jenis_produk + "','" + jenis_bahan + "','" + harga_bahan + "')");

            if (status > 0) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText("Data Bahan Telah Ditambahkan.");
                alert.showAndWait();

                TrayNotification tray = new TrayNotification();
                tray.setTitle("Taambah Data Bahan Success");
                tray.setMessage("Data Bahan "+tfKodeBahan.getText()+" Telah Ditambahkan");
                tray.setAnimationType(AnimationType.FADE);
                tray.showAndDismiss(Duration.millis(2000));
                tray.setRectangleFill(Color.valueOf("#303030"));
                tray.setImage(new Image("/app/images/icons8_Jersey_96px_2.png"));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        refresh();


    }

    public void updateBahan(MouseEvent mouseEvent) {
        String query="update bahan set kode_bahan= ?, jenis_produk= ?, jenis_bahan= ?, harga_bahan=? where kode_bahan='"+tempkodebahan+"'";
        Connection connection = DbConnect.getInstance().getConnection();

        try {

            pst=connection.prepareStatement((query));
            pst.setString(1, tfKodeBahan.getText());
            pst.setString(2, tfJenisProduk.getText());
            pst.setString(3, tfJenisBahan.getText());
            pst.setString(4, tfHargaBahan.getText());
            pst.execute();
            pst.close();
            refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText("Data Bahan Telah Diperbarui.");
            alert.showAndWait();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Update Data Bahan Success");
            tray.setMessage("Data Bahan "+tfKodeBahan.getText()+" Telah Diperbarui");
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(Duration.millis(2000));
            tray.setRectangleFill(Color.valueOf("#303030"));
            tray.setImage(new Image("/app/images/icons8_Jersey_96px_2.png"));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        refresh();


    }




    // Reset atau membersihkan inputan
    public void resetBahan(MouseEvent mouseEvent) {

        tfKodeBahan.setText("");
        tfJenisProduk.setText("");
        tfJenisBahan.setText("");
        tfHargaBahan.setText("");

    }


    //Menampilkan data pada table dari database

    public void refreshTableBahan(MouseEvent mouseEvent) {
        oblist.clear();

        try {

            Connection connection = DbConnect.getInstance().getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM bahan ");
            while (resultSet.next()) {

                oblist.add(new modelTableBahan(
                        resultSet.getString("kode_bahan"),
                        resultSet.getString("jenis_produk"),
                        resultSet.getString("jenis_bahan"),
                        resultSet.getString("harga_bahan")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(tableBahanController.class.getName()).log(Level.SEVERE, null, ex);

        }


        col_bahan_kode_bahan.setCellValueFactory(new PropertyValueFactory<>("kode_bahan"));
        col_bahan_jenis_produk.setCellValueFactory(new PropertyValueFactory<>("jenis_produk"));
        col_bahan_jenis_bahan.setCellValueFactory(new PropertyValueFactory<>("jenis_bahan"));
        col_bahan_harga_bahan.setCellValueFactory(new PropertyValueFactory<>("harga_bahan"));

        table_bahan.setItems(oblist);

    }

    void refresh() {
        oblist.clear();

        try {

            Connection connection = DbConnect.getInstance().getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM bahan ");
            while (resultSet.next()) {

                oblist.add(new modelTableBahan(
                        resultSet.getString("kode_bahan"),
                        resultSet.getString("jenis_produk"),
                        resultSet.getString("jenis_bahan"),
                        resultSet.getString("harga_bahan")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(tableBahanController.class.getName()).log(Level.SEVERE, null, ex);


        }


        col_bahan_kode_bahan.setCellValueFactory(new PropertyValueFactory<>("kode_bahan"));
        col_bahan_jenis_produk.setCellValueFactory(new PropertyValueFactory<>("jenis_produk"));
        col_bahan_jenis_bahan.setCellValueFactory(new PropertyValueFactory<>("jenis_bahan"));
        col_bahan_harga_bahan.setCellValueFactory(new PropertyValueFactory<>("harga_bahan"));

        table_bahan.setItems(oblist);

    }



    // Filter Data Pada Table
    public void filterDataBahan(KeyEvent keyEvent) {
        FilteredList<modelTableBahan> filteredData = new FilteredList<>(oblist, e -> true);
        tf_filterDataTableBahan.setOnKeyReleased(e -> {
            tf_filterDataTableBahan.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super modelTableBahan>) bahan -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (bahan.getKode_bahan().contains(newValue)) {
                        return true;
                    } else if (bahan.getJenis_produk().contains(lowerCaseFilter)) {
                        return true;
                    } else if (bahan.getJenis_bahan().contains(lowerCaseFilter)) {
                        return true;
                    } else if (bahan.getHarga_bahan().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;
                });


            });
            SortedList<modelTableBahan> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table_bahan.comparatorProperty());
            table_bahan.setItems(sortedData);


        });


    }


    public void deleteItem(MouseEvent mouseEvent) throws SQLException {
        try {

            modelTableBahan bahan = (modelTableBahan) table_bahan.getSelectionModel().getSelectedItem();


            int selectedIndex = table_bahan.getSelectionModel().getSelectedIndex();
            String selectedItem = col_bahan_kode_bahan.getText();
            if (selectedIndex >= 0) {
                Connection connection = DbConnect.getInstance().getConnection();
                String query1 = "DELETE FROM bahan WHERE kode_bahan = ?";
                System.out.println(selectedItem);
                pst = connection.prepareStatement(query1);
                pst.setString(1, bahan.getKode_bahan());
                pst.executeUpdate();
                System.out.println(selectedItem);
                pst.close();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("KONFIRMASI :");
                alert.setHeaderText("Apakah Anda Yakin Akan Menghapus Data Ini ?");
                alert.showAndWait();

                TrayNotification tray = new TrayNotification();
                tray.setTitle("Delete Data Bahan Success");
                tray.setMessage("Data Bahan "+tfKodeBahan.getText()+" Telah Dihapus");
                tray.setAnimationType(AnimationType.FADE);
                tray.showAndDismiss(Duration.millis(2000));
                tray.setRectangleFill(Color.valueOf("#303030"));
                tray.setImage(new Image("/app/images/icons8_Delete_Bin_48px.png"));

                table_bahan.getItems().remove(selectedIndex);


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR:");
                alert.setHeaderText("No selection was made.");
                alert.setContentText("You have not selected an item to delete. Please try again.");
                alert.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(tableBahanController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void editData(MouseEvent mouseEvent) {


    }




    public void exportPdf(MouseEvent mouseEvent) {
    }

    public void exportExcel(MouseEvent mouseEvent) {
    }

    public void clearFilterTableBahan(MouseEvent mouseEvent) {
        tf_filterDataTableBahan.setText("");

    }


    static String tempkodebahan;

    public void showOnClick()
    {
        try {


            modelTableBahan bahan = (modelTableBahan) table_bahan.getSelectionModel().getSelectedItem();
            table_bahan.getSelectionModel().getSelectedIndex();

            Connection connection = DbConnect.getInstance().getConnection();
            String query = "select * FROM bahan WHERE kode_bahan = ?";

            pst = connection.prepareStatement(query);
            tempkodebahan=bahan.getKode_bahan();
            tfKodeBahan.setText(bahan.getKode_bahan());
            tfJenisProduk.setText(bahan.getJenis_produk());
            tfJenisBahan.setText(bahan.getJenis_bahan());
            tfHargaBahan.setText(bahan.getHarga_bahan());

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(tableBahanController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}



