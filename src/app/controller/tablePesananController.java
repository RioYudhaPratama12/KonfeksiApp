package app.controller;

import app.database.DbConnect;
import app.model.modelStatusPesanan;
import app.model.modelTablePesanan;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

//import javafx.scene.image.Image;

public class tablePesananController implements Initializable {




    private PreparedStatement pst;

    public tablePesananController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbStatusPesanan.setItems (listStatus);
        initClock();
        tampilStatusPesanan();
        tampilDataPesanan();
        statusorderTotal();

    }


    @FXML
    private Label lblTotalOrder2;

    @FXML
    private Label lblOrderanSelesai;

    @FXML
    private Label lblOrderanProses;

    @FXML
    private Label lblOrderanPending;

    @FXML
    private Label lblTotalPelanggan;


    //tabel Pesanan
    @FXML
    ObservableList<modelTablePesanan> oblist = FXCollections.observableArrayList();

    @FXML
    private TextField tf_filterDataTablePesanan;

    @FXML
    private TableView<modelTablePesanan> table_pesanan;

    @FXML
    private TableColumn<?, ?> col_no_pesanan;

    @FXML
    private TableColumn<?, ?> col_nama_pemesan;

    @FXML
    private TableColumn<?, ?> col_no_telp;

    @FXML
    private TableColumn<?, ?> col_alamat;

    @FXML
    private TableColumn<?, ?> col_jenis_produk;

    @FXML
    private TableColumn<?, ?> col_jenis_bahan;

    @FXML
    private TableColumn<?, ?> col_kode_bahan;

    @FXML
    private TableColumn<?, ?> col_harga_bahan;

    @FXML
    private TableColumn<?, ?> col_kode_ukuran1;

    @FXML
    private TableColumn<?, ?> col_kode_ukuran2;

    @FXML
    private TableColumn<?, ?> col_kode_ukuran3;

    @FXML
    private TableColumn<?, ?> col_kode_ukuran4;

    @FXML
    private TableColumn<?, ?> col_kode_ukuran5;

    @FXML
    private TableColumn<?, ?> col_jumlah_produk;

    @FXML
    private TableColumn<?, ?> col_jumlah_ukuran1;

    @FXML
    private TableColumn<?, ?> col_jumlah_ukuran2;

    @FXML
    private TableColumn<?, ?> col_jumlah_ukuran3;

    @FXML
    private TableColumn<?, ?> col_jumlah_ukuran4;

    @FXML
    private TableColumn<?, ?> col_jumlah_ukuran5;

    @FXML
    private TableColumn<?, ?> col_kode_sablon;

    @FXML
    private TableColumn<?, ?> col_jenis_sablon;

    @FXML
    private TableColumn<?, ?> col_ukuran_sablon;

    @FXML
    private TableColumn<?, ?> col_harga_sablon;

    @FXML
    private TableColumn<?, ?> col_gambar_desain;

    @FXML
    private TableColumn<?, ?> col_harga_pembayaran;

    @FXML
    private TableColumn<?, ?> col_dp;

    @FXML
    private TableColumn<?, ?> col_sisa_pembayaran;

    @FXML
    private TableColumn<?, ?> col_tanggal_pesanan;

    @FXML
    private TableColumn<?, ?> col_status_pesanan;

    @FXML
    private TableColumn<?, ?> col_username;

    @FXML
    private TableColumn<?, ?> col_tahun;

    @FXML
    private TableColumn<?, ?> col_bulan;


    @FXML
    private ImageView previewDesign;




    //table status Pesanan
    @FXML
    private TableView<modelStatusPesanan> table_statusPesanan;

    @FXML
    private TableColumn<?, ?> col_noPesanan;

    @FXML
    private TableColumn<?, ?> col_namaPesanan;

    @FXML
    private TableColumn<?, ?> col_statusPesanan;

    @FXML
    private TableColumn<?, ?> col_desainPesanan;

    @FXML
    private TableColumn<?, ?> col_jenisProduk;

    @FXML
    private TableColumn<?, ?> col_jumlahProduk;


    @FXML
    private JFXComboBox<String> cbStatusPesanan;
    ObservableList<String> listStatus = FXCollections.observableArrayList("PENDING", "PROSES", "SELESAI");

    @FXML
    private JFXTextField tf_editNoPesanan;

    @FXML
    private JFXTextField tf_editNamaPemesan;

    @FXML
    private JFXTextField tf_editStatusPesanan;


    @FXML
    ObservableList<modelStatusPesanan> oblist2 = FXCollections.observableArrayList();


    @FXML
    private Label lblTanggalStatus;



    public void exportExcel(MouseEvent mouseEvent) {
    }

    public void exportPdf(MouseEvent mouseEvent) {
    }



    //Delete Data Tabel Pesanan
    public void deleteItem(MouseEvent mouseEvent) {

        try {

            modelTablePesanan pesanan =(modelTablePesanan) table_pesanan.getSelectionModel().getSelectedItem();

            int selectedIndex = table_pesanan.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Connection connection = DbConnect.getInstance().getConnection();
                String query1 = "DELETE FROM pemesanan WHERE no_pesanan = ?";
                pst = connection.prepareStatement(query1);
                pst.setString(1, pesanan.getNo_pesanan());
                pst.executeUpdate();
                pst.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("KONFIRMASI :");
                alert.setHeaderText("Apakah Anda Yakin Akan Menghapus Data Ini ?");
                alert.showAndWait();

                TrayNotification tray = new TrayNotification();
                tray.setTitle("Delete Data Pesanan Success");
                tray.setMessage("Data Pesanan "+tf_editNamaPemesan.getText()+" Telah Dihapus");
                tray.setAnimationType(AnimationType.FADE);
                tray.showAndDismiss(Duration.millis(2000));
                tray.setRectangleFill(Color.valueOf("#303030"));
                tray.setImage(new Image("/app/images/icons8_Delete_Bin_48px.png"));

                table_pesanan.getItems().remove(selectedIndex);


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR:");
                alert.setHeaderText("No selection was made.");
                alert.setContentText("You have not selected an item to delete. Please try again.");
                alert.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(tablePesananController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void filterDataPesanan(KeyEvent keyEvent) {
        FilteredList<modelStatusPesanan> filteredData = new FilteredList<>(oblist2, e -> true);
        tf_filterDataTablePesanan.setOnKeyReleased(e -> {
            tf_filterDataTablePesanan.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super modelStatusPesanan>) pesanan -> {


                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (pesanan.getNo_pesanan().contains(newValue)) {
                        return true;

                    }

                    return false;
                });


            });

            SortedList<modelStatusPesanan> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table_statusPesanan.comparatorProperty());
            table_statusPesanan.setItems(sortedData);

        });

    }


    private void tampilDataPesanan(){
        oblist.clear();

        try {

            Connection connection = DbConnect.getInstance().getConnection();

            Statement statement = connection.createStatement();




            ResultSet resultSet = statement.executeQuery("SELECT * FROM pemesanan ");
            while (resultSet.next()) {

                oblist.add(new modelTablePesanan(

                        resultSet.getString("no_pesanan"),
                        resultSet.getString("nama_pemesan"),
                        resultSet.getString("no_telp"),
                        resultSet.getString("alamat"),
                        resultSet.getString("jenis_produk"),
                        resultSet.getString("jenis_bahan"),
                        resultSet.getString("kode_bahan"),
                        resultSet.getString("kode_sablon"),
                        resultSet.getString("jenis_sablon"),
                        resultSet.getString("ukuran_sablon"),
                        resultSet.getString("tanggal_pemesanan"),
                        resultSet.getString("status_pesanan"),
                        resultSet.getString("username"),
                        resultSet.getString("tahun"),
                        resultSet.getString("bulan"),
                        resultSet.getInt("harga_bahan"),
                        resultSet.getInt("jml_produk"),
                        resultSet.getInt("jml_ukuran1"),
                        resultSet.getInt("jml_ukuran2"),
                        resultSet.getInt("jml_ukuran3"),
                        resultSet.getInt("jml_ukuran4"),
                        resultSet.getInt("jml_ukuran5"),
                        resultSet.getInt("harga_sablon"),
                        resultSet.getInt("total_pembayaran"),
                        resultSet.getInt("bayar_dp"),
                        resultSet.getInt("sisa_pembayaran"),
                        resultSet.getBytes("gambar_desain")));


            }

        } catch (SQLException ex) {

            Logger.getLogger(tablePesananController.class.getName()).log(Level.SEVERE, null, ex);

        }



        col_no_pesanan.setCellValueFactory(new PropertyValueFactory<>("no_pesanan"));
        col_no_telp.setCellValueFactory(new PropertyValueFactory<>("no_telp"));
        col_nama_pemesan.setCellValueFactory(new PropertyValueFactory<>("nama_pemesan"));
        col_alamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        col_jenis_produk.setCellValueFactory(new PropertyValueFactory<>("jenis_produk"));
        col_jenis_bahan.setCellValueFactory(new PropertyValueFactory<>("jenis_bahan"));
        col_kode_bahan.setCellValueFactory(new PropertyValueFactory<>("kode_bahan"));
        col_kode_sablon.setCellValueFactory(new PropertyValueFactory<>("kode_sablon"));
        col_jenis_sablon.setCellValueFactory(new PropertyValueFactory<>("jenis_sablon"));
        col_ukuran_sablon.setCellValueFactory(new PropertyValueFactory<>("ukuran_sablon"));
        col_gambar_desain.setCellValueFactory(new PropertyValueFactory<>("gambar_desain"));
        col_tanggal_pesanan.setCellValueFactory(new PropertyValueFactory<>("tanggal_pemesanan"));
        col_status_pesanan.setCellValueFactory(new PropertyValueFactory<>("status_pesanan"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_tahun.setCellValueFactory(new PropertyValueFactory<>("tahun"));
        col_bulan.setCellValueFactory(new PropertyValueFactory<>("bulan"));
        col_harga_bahan.setCellValueFactory(new PropertyValueFactory<>("harga_bahan"));
        col_jumlah_produk.setCellValueFactory(new PropertyValueFactory<>("jml_produk"));
        col_jumlah_ukuran1.setCellValueFactory(new PropertyValueFactory<>("jml_ukuran1"));
        col_jumlah_ukuran2.setCellValueFactory(new PropertyValueFactory<>("jml_ukuran2"));
        col_jumlah_ukuran3.setCellValueFactory(new PropertyValueFactory<>("jml_ukuran3"));
        col_jumlah_ukuran4.setCellValueFactory(new PropertyValueFactory<>("jml_ukuran4"));
        col_jumlah_ukuran5.setCellValueFactory(new PropertyValueFactory<>("jml_ukuran5"));
        col_harga_sablon.setCellValueFactory(new PropertyValueFactory<>("harga_sablon"));
        col_harga_pembayaran.setCellValueFactory(new PropertyValueFactory<>("total_pembayaran"));
        col_dp.setCellValueFactory(new PropertyValueFactory<>("bayar_dp"));
        col_sisa_pembayaran.setCellValueFactory(new PropertyValueFactory<>("sisa_pembayaran"));

        table_pesanan.setItems(oblist);

    }


    public void clearFilterTablePesanan(MouseEvent mouseEvent) {
        tf_filterDataTablePesanan.setText("");


    }

    private void statusorderTotal( ) {
        Connection connection = DbConnect.getInstance().getConnection();

        try {

            String query = "select sum(jml_produk) from pemesanan ";
            PreparedStatement PS = connection.prepareStatement(query);
            ResultSet result = PS.executeQuery();
            result.next();
            lblTotalOrder2.setText(result.getString(1));
            PS.close();
            result.close();
            //           connection.close();

            String query2 = "select sum(jml_produk) from pemesanan where status_pesanan =\"SELESAI\" ";
            PreparedStatement PS2 = connection.prepareStatement(query2);
            ResultSet result2 = PS2.executeQuery();
            result2.next();
            lblOrderanSelesai.setText(result2.getString(1));
            PS2.close();
            result2.close();
            //           connection.close();

            String query3 = "select sum(jml_produk) from pemesanan where status_pesanan =\"PROSES\" ";
            PreparedStatement PS3 = connection.prepareStatement(query3);
            ResultSet result3 = PS3.executeQuery();
            result3.next();
            lblOrderanProses.setText(result3.getString(1));
            PS3.close();
            result3.close();
            //           connection.close();

            String query4 = "select sum(jml_produk) from pemesanan where status_pesanan =\"PENDING\" ";
            PreparedStatement PS4 = connection.prepareStatement(query4);
            ResultSet result4 = PS4.executeQuery();
            result4.next();
            lblOrderanPending.setText(result4.getString(1));
            PS4.close();
            result4.close();
            //           connection.close();

            String query5 = "select count(no_pesanan) from pemesanan";
            PreparedStatement PS5 = connection.prepareStatement(query5);
            ResultSet result5 = PS5.executeQuery();
            result5.next();
            lblTotalPelanggan.setText(result5.getString(1));
            PS5.close();
            result5.close();
//            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD MMMM YYYY");
            lblTanggalStatus.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    static String tempstatuspesanan;


    public void showOnClickpesanan()
    {
        try {


            modelStatusPesanan statuspesan = (modelStatusPesanan) table_statusPesanan.getSelectionModel().getSelectedItem();
            table_statusPesanan.getSelectionModel().getSelectedIndex();

            Connection connection = DbConnect.getInstance().getConnection();
            String query = "select * FROM pemesanan WHERE no_pesanan = ?";

            pst = connection.prepareStatement(query);
            tempstatuspesanan=statuspesan.getNo_pesanan();
            tf_editNoPesanan.setText(statuspesan.getNo_pesanan());
            tf_editNamaPemesan.setText(statuspesan.getNama_pemesan());
            cbStatusPesanan.setValue (statuspesan.getStatus_pesanan());

            // Untuk tampil gambar
            String dest = System.getProperty("user.home") + File.separator + "ALL-DESIGN-KONFEKSIAPPS" + File.separator;
            File imageFile = new File(dest + statuspesan.getGambar_desain ());
            System.out.println(imageFile.getAbsolutePath());
            if (imageFile.exists()) {

                Image image = new Image(imageFile.toURI().toString());
                previewDesign.setImage(image);
                previewDesign.setFitWidth (360);

                previewDesign.setPreserveRatio (true);
//                previewDesign.setFitHeight ();
            }

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(tablePesananController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Update Data Pada status Pesanan
    @FXML
    public void updateStatusPesanan(MouseEvent mouseEvent) throws SQLException{

        String query="update pemesanan set no_pesanan= ?, nama_pemesan= ?, status_pesanan= ? where no_pesanan='"+tempstatuspesanan+"'";
        Connection connection = DbConnect.getInstance().getConnection();

        try {

            pst=connection.prepareStatement((query));
            pst.setString(1, tf_editNoPesanan.getText());
            pst.setString(2, tf_editNamaPemesan.getText());
            pst.setString(3, cbStatusPesanan.getValue());
//            System.out.println (gambar_desain);

//            pst.setString (4, tf);

            pst.execute();
            pst.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText("Status Pesanan Telah Diperbarui.");
            alert.showAndWait();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Update Data Pesanan Success");
            tray.setMessage("Data Pesanan "+tf_editNamaPemesan.getText()+" Telah Diperbarui");
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(Duration.millis(2000));
            tray.setRectangleFill(Color.valueOf("#303030"));
            tray.setImage(new Image("/app/images/icons8_Autograph_96px.png"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        tampilStatusPesanan();
        statusorderTotal();


    }

    //Delete pada Table Status Pesanan
    @FXML
    public void deletePesanan(MouseEvent event) {

        try {

            modelStatusPesanan statusPesanan = (modelStatusPesanan) table_statusPesanan.getSelectionModel().getSelectedItem();


            int selectedIndex = table_statusPesanan.getSelectionModel().getSelectedIndex();
            String selectedItem = col_noPesanan.getText();
            if (selectedIndex >= 0) {
                Connection connection = DbConnect.getInstance().getConnection();
                String query1 = "DELETE FROM pemesanan WHERE no_pesanan = ?";
                //    System.out.println(selectedItem);
                pst = connection.prepareStatement(query1);
                pst.setString(1, statusPesanan.getNo_pesanan());
                pst.executeUpdate();
                //    System.out.println(selectedItem);
                pst.close();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("KONFIRMASI :");
                alert.setHeaderText("Apakah Anda Yakin Akan Menghapus Data Ini ?");
                alert.showAndWait();

                TrayNotification tray = new TrayNotification();
                tray.setTitle("Delete Data Pesanan Success");
                tray.setMessage("Data Pesanan "+tf_editNamaPemesan.getText()+" Telah Dihapus");
                tray.setAnimationType(AnimationType.FADE);
                tray.showAndDismiss(Duration.millis(2000));
                tray.setRectangleFill(Color.valueOf("#303030"));
                tray.setImage(new Image("/app/images/icons8_Delete_Bin_48px.png"));



                table_statusPesanan.getItems().remove(selectedIndex);


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR:");
                alert.setHeaderText("No selection was made.");
                alert.setContentText("You have not selected an item to delete. Please try again.");
                alert.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(tablePesananController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tampilStatusPesanan();
        statusorderTotal();

    }



    private void tampilStatusPesanan(){
        oblist2.clear();

        try {


            Connection connection = DbConnect.getInstance().getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM pemesanan ");
            while (resultSet.next()) {

                oblist2.add(new modelStatusPesanan(
                        resultSet.getString("no_pesanan"),
                        resultSet.getString("nama_pemesan"),
                        resultSet.getString("jenis_produk"),
                        resultSet.getInt ("jml_produk"),
                        resultSet.getString("status_pesanan"),
                        resultSet.getString ("gambar_desain")));



            }

        } catch (SQLException ex) {
            Logger.getLogger(tablePesananController.class.getName()).log(Level.SEVERE, null, ex);

        }

        col_noPesanan.setCellValueFactory(new PropertyValueFactory<>("no_pesanan"));
        col_namaPesanan.setCellValueFactory(new PropertyValueFactory<>("nama_pemesan"));
        col_jenisProduk.setCellValueFactory(new PropertyValueFactory<>("jenis_produk"));
        col_jumlahProduk.setCellValueFactory(new PropertyValueFactory<>("jml_produk"));
        col_statusPesanan.setCellValueFactory(new PropertyValueFactory<>("status_pesanan"));
        col_desainPesanan.setCellValueFactory(new PropertyValueFactory<>("gambar_desain"));

        table_statusPesanan.setItems(oblist2);

    }

    public void refreshTablePesanan(MouseEvent mouseEvent) {
        tampilDataPesanan();
        tampilStatusPesanan();
        statusorderTotal();
    }


}
