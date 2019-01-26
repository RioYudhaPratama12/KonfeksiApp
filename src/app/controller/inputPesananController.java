package app.controller;

import app.database.DbConnect;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.util.StringConverter;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class inputPesananController implements Initializable {

    @FXML
    private JFXComboBox<String> cbJenisProduk;
    ObservableList<String> list = FXCollections.observableArrayList("BAJU", "KEMEJA", "JERSEY", "JAKET");

    @FXML
    private JFXComboBox<String> cbStatusPesanan;
    ObservableList<String> listStatus = FXCollections.observableArrayList("PENDING", "PROSES", "SELESAI");




    @FXML
    private TextField tfTahunPemesanan;

    @FXML
    private TextField tfBulanPemesanan;


    @FXML
    private TextField tfTotalPembayaran;

    @FXML
    private TextField tf_DP;

    @FXML
    private TextField tf_sisaPembayaran;

    @FXML
    private TextField tfTanggalPemesanan;

    @FXML
    private TextField tfStatusPesanan;

    @FXML
    private TextField tfAdmin;

    @FXML
    private TextField tfKodeBahan;

    @FXML
    private TextField tfJenisBahan;

    @FXML
    private TextField tfHargaBahan;

    @FXML
    private ImageView imageView;

    @FXML
    private JFXTextField tfNoPesanan;

    @FXML
    private JFXTextField tfNamaPemesan;

    @FXML
    private JFXTextField tfNoTelp;

    @FXML
    private TextField tfAlamat;

    @FXML
    private TextField tfGambar;

    @FXML
    private String pathFile;

    @FXML
    private TextField tfJumlahProduk;

    @FXML
    private String namaFile;

    @FXML
    private JFXComboBox cbKodeBahan;
    ObservableList<String> oblistbahan = FXCollections.observableArrayList("kode_bahan");

    @FXML
    private JFXComboBox cbKodeSablon;
    ObservableList<String> oblistsablon = FXCollections.observableArrayList("kode_sablon");
    private Image Design;


    @FXML
    private ImageView previewDesign;

    @FXML
    private TextField tfUkuranSablon;


    @FXML
    private TextField tfKodeSablon;

    @FXML
    private TextField tfJenisSablon;

    @FXML
    private JFXTextField tfHargaSablon;

    @FXML
    private Spinner<Integer> sizeS;

    @FXML
    private Spinner<Integer> sizeM;

    @FXML
    private Spinner<Integer> sizeL;

    @FXML
    private Spinner<Integer> sizeXL;

    @FXML
    private Spinner<Integer> sizeXXL;





    @Override
    public void initialize(URL location, ResourceBundle resources) {


        combobox();
//        ukuran();
        showDate();
        showMonth();
        showYear();
        tampilcbKodeBahan();
        tampilcbKodeSablon();
        autovalue();


        // Set value cbKodeBahan
        cbKodeBahan.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                System.out.println(observable);
                System.out.println(oldValue);
                System.out.println(newValue);
                try {
                    fetchDataBahan(newValue);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });

        // Set value cbKodeSablon
        cbKodeSablon.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                System.out.println(observable);
                System.out.println(oldValue);
                System.out.println(newValue);
                try {
                    fetchDataSablon(newValue);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });





        //Size S
        SpinnerValueFactory<Integer> bayarValue = new SpinnerValueFactory.IntegerSpinnerValueFactory (0,10000,0);
        sizeS.setValueFactory (bayarValue);
        sizeS.setEditable (true);
        sizeS.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            totalProduk();
            totalBayar();

            System.out.println (newValue);

        });
        // useage in client code
        sizeS.focusedProperty().addListener((s, ov, nv) -> {
            if (nv) return;
            commitEditorText(sizeS);
        });



        // Size M
        SpinnerValueFactory<Integer> bayarValue2 = new SpinnerValueFactory.IntegerSpinnerValueFactory (0,10000,0);
        sizeM.setValueFactory (bayarValue2);
        sizeM.setEditable (true);
        sizeM.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            totalProduk();
            totalBayar();
            System.out.println (newValue);

        });

        // useage in client code
        sizeM.focusedProperty().addListener((s, ov, nv) -> {
            if (nv) return;
            commitEditorText(sizeM);
        });



        // Size L
        SpinnerValueFactory<Integer> bayarValue3 = new SpinnerValueFactory.IntegerSpinnerValueFactory (0,10000,0);
        sizeL.setValueFactory (bayarValue3);
        sizeL.setEditable (true);
        sizeL.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            totalProduk();
            totalBayar();
            System.out.println (newValue);

        });

        // useage in client code
        sizeL.focusedProperty().addListener((s, ov, nv) -> {
            if (nv) return;
            commitEditorText(sizeL);
        });



        // Size XL
        SpinnerValueFactory<Integer> bayarValue4 = new SpinnerValueFactory.IntegerSpinnerValueFactory (0,10000,0);
        sizeXL.setValueFactory (bayarValue4);
        sizeXL.setEditable (true);
        sizeXL.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            totalProduk();
            totalBayar();
            System.out.println (newValue);

        });

        // useage in client code
        sizeXL.focusedProperty().addListener((s, ov, nv) -> {
            if (nv) return;
            commitEditorText(sizeXL);
        });

        // Size XXL
        SpinnerValueFactory<Integer> bayarValue5 = new SpinnerValueFactory.IntegerSpinnerValueFactory (0,10000,0);
        sizeXXL.setValueFactory (bayarValue5);
        sizeXXL.setEditable (true);
        sizeXXL.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            totalProduk();
            totalBayar();
            System.out.println (newValue);

        });

        // useage in client code
        sizeXXL.focusedProperty().addListener((s, ov, nv) -> {
            if (nv) return;
            commitEditorText(sizeXXL);
        });

    }

    private <T> void commitEditorText(Spinner<T> spinner) {
        if (!spinner.isEditable()) return;
        String text = spinner.getEditor().getText();
        SpinnerValueFactory<T> valueFactory = spinner.getValueFactory();
        if (valueFactory != null) {
            StringConverter<T> converter = valueFactory.getConverter();
            if (converter != null) {
                T value = converter.fromString(text);
                valueFactory.setValue(value);
                totalProduk();
                totalBayar();
            }

        }
    }

    public void totalProduk() {
        tfJumlahProduk.setVisible (true);
        int nilai1 = Integer.parseInt ((String) sizeS.getValue ().toString ());
        int nilai2 = Integer.parseInt ((String) sizeM.getValue ().toString ());
        int nilai3 = Integer.parseInt ((String) sizeL.getValue ().toString ());
        int nilai4 = Integer.parseInt ((String) sizeXL.getValue ().toString ());
        int nilai5 = Integer.parseInt ((String) sizeXXL.getValue ().toString ());
        int hasil = nilai1 + nilai2 + nilai3 + nilai4 + nilai5;
        tfJumlahProduk.setText (String.valueOf (hasil));
    }

    public void totalBayar() {
        //Untuk total bayar

//        DecimalFormat formatter = new DecimalFormat("#,###");

        tfTotalPembayaran.setVisible (true);
        try {

            //Untuk total pembayaran
            int jumlahproduk = Integer.parseInt (tfJumlahProduk.getText ());
            System.out.println(jumlahproduk);
            int hargasablon = Integer.parseInt (tfHargaSablon.getText ());
            System.out.println(hargasablon);
            int hargabahan = Integer.parseInt (tfHargaBahan.getText ());
            System.out.println(hargabahan);
            int totalbayar = (jumlahproduk * ( hargabahan + hargasablon));
            tfTotalPembayaran.setText (String.valueOf (totalbayar));


            //Untuk DP 50%
            double v = 0.30;
            double dpbayar = (jumlahproduk * ( hargabahan + hargasablon)) * v ;
            tf_DP.setText (String.valueOf(dpbayar));
            System.out.println (dpbayar);

            //Untuk Sisa pembayaran
            double sisabayar = (jumlahproduk * ( hargabahan + hargasablon)) - dpbayar;
            tf_sisaPembayaran.setText (String.valueOf((sisabayar)));
            System.out.println (sisabayar);

        } catch (Exception e){
            System.out.println (e);
        }

    }

    public void combobox(){
        cbJenisProduk.setItems(list);
        cbStatusPesanan.setItems (listStatus);

    }


    public void showDate(){
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        tfTanggalPemesanan.setText(s.format(d));

    }


    public void showMonth(){
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("MMMMM");
        tfBulanPemesanan.setText(s.format(d));

    }

    public void showYear(){
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("YYYY");
        tfTahunPemesanan.setText(s.format(d));

    }



    public void tambahPesanan(MouseEvent mouseEvent) {
        Connection connection = DbConnect.getInstance().getConnection();

        try {

            String no_pesanan = tfNoPesanan.getText();
            String nama_pemesan = tfNamaPemesan.getText();
            String no_telp = tfNoTelp.getText();
            String alamat = tfAlamat.getText();
            String jenis_produk = cbJenisProduk.getValue();
            String jenis_bahan = tfJenisBahan.getText();
//            String kode_bahan = cbKodeBahan.getValue().toString().substring(0,6);
            String kode_bahan = tfKodeBahan.getText();
            String harga_bahan = tfHargaBahan.getText();
            String jml_produk = tfJumlahProduk.getText();
            String jml_ukuran1 = sizeS.getValue().toString();
            String jml_ukuran2 = sizeM.getValue().toString();
            String jml_ukuran3 = sizeL.getValue().toString();
            String jml_ukuran4 = sizeXL.getValue().toString();
            String jml_ukuran5 = sizeXXL.getValue().toString();
//            String kode_sablon = cbKodeSablon.getValue().toString().substring(0,7);
            String kode_sablon = tfKodeSablon.getText();
            String jenis_sablon = tfJenisSablon.getText();
            String ukuran_sablon = tfJenisSablon.getText();
            String harga_sablon = tfHargaSablon.getText();
            String gambar_desain = namaFile;
            String total_pembayaran = tfTotalPembayaran.getText();
            String bayar_dp = tf_DP.getText();
            String sisa_pembayaran  = tf_sisaPembayaran.getText();
            String tanggal_pemesanan = tfTanggalPemesanan.getText();
            String status_pesanan = cbStatusPesanan.getValue ();
            String username = tfAdmin.getText();
            String tahun  = tfTahunPemesanan.getText();
            String bulan = tfBulanPemesanan.getText();


            if (pathFile != null) {
                File source = new File(pathFile);
                String dest = System.getProperty("user.home") + File.separator + "ALL-DESIGN-KONFEKSIAPPS";
                new File(dest).mkdirs();
                copyFile(source, new File(dest + File.separator + source.getName()));
            }

            Statement statement = connection.createStatement();

            int status = statement.executeUpdate("insert into pemesanan (no_pesanan, nama_pemesan, no_telp, alamat, jenis_produk, " +
                    " jenis_bahan, kode_bahan, harga_bahan, jml_produk,jml_ukuran1," +
                    " jml_ukuran2, jml_ukuran3,jml_ukuran4, jml_ukuran5," +
                    " kode_sablon, jenis_sablon, ukuran_sablon, harga_sablon,  gambar_desain, total_pembayaran, bayar_dp," +
                    "sisa_pembayaran, tanggal_pemesanan, status_pesanan, username, tahun, bulan)" +
                    " values('" + no_pesanan + "','" + nama_pemesan + "','" + no_telp + "','" + alamat + "','" + jenis_produk
                    + "','" +jenis_bahan + "','" +kode_bahan + "','" +harga_bahan + "','" +jml_produk + "','" +jml_ukuran1 + "','" +jml_ukuran2 + "','" +jml_ukuran3
                    + "','" +jml_ukuran4 + "','" +jml_ukuran5+ "','" + kode_sablon
                    + "','" + jenis_sablon + "','" +ukuran_sablon+"','" + harga_sablon + "','" + gambar_desain
                    + "','" + total_pembayaran + "','" +bayar_dp + "','" + sisa_pembayaran + "','" + tanggal_pemesanan
                    + "','" + status_pesanan + "','" + username + "','" + tahun + "','" + bulan+ "')");



            if (status > 0) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText("Data Pesananan "+tfNamaPemesan.getText()+" Telah Ditambahkan.");
                alert.showAndWait();

                TrayNotification tray = new TrayNotification();
                tray.setTitle("Insert Data Pesanan Success");
                tray.setMessage("Data Pesanan "+tfNamaPemesan.getText()+" Telah Ditambahkan");
                tray.setAnimationType(AnimationType.FADE);
                tray.showAndDismiss(Duration.millis(2000));
                tray.setRectangleFill(Color.valueOf("#303030"));
                tray.setImage(new Image("/app/images/icons8_Autograph_96px.png"));


            }

        } catch (SQLException ex) {
            ex.printStackTrace();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void copyFile(File source, File dest) throws IOException {
        if (!dest.exists()) {
            dest.createNewFile();
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(source);
            out = new FileOutputStream(dest);

            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }


    public void uploadDesignFile2(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png")

        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            tfGambar.setText(selectedFile.getAbsolutePath());
            pathFile = selectedFile.getAbsolutePath();
            namaFile = selectedFile.getName();

            Image Design = new Image(selectedFile.toURI().toString(), 550, 550, true, true);
            imageView.setImage(Design);
            imageView = new ImageView(Design);
            imageView.setFitHeight(100);

            imageView.setPreserveRatio(true);




        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Peringatan");
            alert.setHeaderText("File tidak ditemukan !");
            alert.showAndWait();
        }
    }

    public void uploadDesignFile(ActionEvent event) {

        FileChooser fileChooser = new FileChooser ();
        fileChooser.getExtensionFilters ().addAll (
                new FileChooser.ExtensionFilter ("Image Files", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter ("PSD Files", "*.psd"),
                new FileChooser.ExtensionFilter ("CDR Files", "*.cdr")
        );
        File selectedFile = fileChooser.showOpenDialog (null);

        if (selectedFile != null) {

            tfGambar.setText (selectedFile.getAbsolutePath ());
            pathFile = selectedFile.getAbsolutePath ();
            namaFile = selectedFile.getName ();
            Image Design = new Image (selectedFile.toURI ().toString (), 550, 550, true, true);
            imageView.setImage (Design);
            imageView.setFitWidth (500);

            imageView.setPreserveRatio (true);


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Peringatan");
            alert.setHeaderText("File tidak ditemukan !");
            alert.showAndWait();
        }
    }




    void tampilcbKodeBahan() {
        oblistbahan.clear();

        try {

            Connection connection = DbConnect.getInstance().getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT kode_bahan, jenis_bahan FROM bahan order by kode_bahan asc ");
            while (resultSet.next()) {

                oblistbahan.add(new String(

                        resultSet.getString("kode_bahan") +  "/ " + resultSet.getString("jenis_bahan")));
//                resultSet.getString("kode_sablon")  +  "/ " + resultSet.getString("jenis_sablon")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(inputPesananController.class.getName()).log(Level.SEVERE, null, ex);


        }


        cbKodeBahan.setItems(oblistbahan);

    }





    void tampilcbKodeSablon() {
        oblistsablon.clear();

        try {

            Connection connection = DbConnect.getInstance().getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT kode_sablon,jenis_sablon,ukuran_sablon FROM sablon order by kode_sablon asc ");
            while (resultSet.next()) {

                oblistsablon.add(new String(

//                        resultSet.getString("kode_sablon")));
                        resultSet.getString("kode_sablon")  +  "/ " + resultSet.getString("jenis_sablon")  +  "/ " + resultSet.getString("ukuran_sablon")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(inputPesananController.class.getName()).log(Level.SEVERE, null, ex);


        }


        cbKodeSablon.setItems(oblistsablon);

    }

    //Mengambil data bahan
    public  void fetchDataBahan(String kodeBahan) throws SQLException {
        kodeBahan = kodeBahan.substring(0,6);
        Connection connection = DbConnect.getInstance().getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM bahan where kode_bahan='"+kodeBahan+"'");
        while (resultSet.next()) {

            tfJenisBahan.setText(resultSet.getString("jenis_bahan"));
            tfHargaBahan.setText(resultSet.getString("harga_bahan"));
            tfKodeBahan.setText(resultSet.getString("kode_bahan"));

        }

    }

    //Mengambil data sablon
    public  void fetchDataSablon(String kodeSablon) throws SQLException {
        kodeSablon = kodeSablon.substring(0,7);
        Connection connection = DbConnect.getInstance().getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM sablon where kode_sablon='"+kodeSablon+"'");
        while (resultSet.next()) {

            tfJenisSablon.setText(resultSet.getString("jenis_sablon"));
            tfUkuranSablon.setText(resultSet.getString("ukuran_sablon"));
            tfHargaSablon.setText(resultSet.getString("harga_sablon"));
            tfKodeSablon.setText(resultSet.getString("kode_sablon"));


        }

    }


    public void reset(MouseEvent mouseEvent) {

        tfNamaPemesan.setText("");
        tfNoTelp.setText("");
        tfAlamat.setText("");
        tfKodeBahan.setText("");
        tfJenisBahan.setText("");
        tfHargaBahan.setText("");
        tfJumlahProduk.setText("");
        tfKodeSablon.setText("");
        tfJenisSablon.setText("");
        tfJenisSablon.setText("");
        tfHargaSablon.setText("");
        namaFile.isEmpty();
        tfTotalPembayaran.setText("");
        tf_DP.setText("");
        tf_sisaPembayaran.setText("");

    }

    private void autovalue (){

        tfNoPesanan.setText("ORD0000");
//        tfStatusPesanan.setText("PENDING");
        cbStatusPesanan.getSelectionModel ().selectFirst ();
        tfAdmin.setText("admin");

    }





}






