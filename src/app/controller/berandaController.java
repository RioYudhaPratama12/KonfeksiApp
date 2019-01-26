package app.controller;

import app.database.DbConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class berandaController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        totalorder();
        refresh();


    }

    @FXML
    private BarChart<String, Double> barChart;

    @FXML
    private LineChart<String, Double> lineChart;

    @FXML
    private AreaChart<String, Double> areaChart;

    @FXML
    private Button btnLoad;


    @FXML
    private Label lblTotalOrder;

    @FXML
    private Label lblTotalOrderPlg;

    @FXML
    private Label lblOrderBaju;

    @FXML
    private Label lblOrderBajuPlg;

    @FXML
    private Label lblOrderKemeja;

    @FXML
    private Label lblOrderKemejaPlg;

    @FXML
    private Label lblOrderJersey;

    @FXML
    private Label lblOrderJerseyPlg;

    @FXML
    private Label lblOrderanLain;

    @FXML
    private Label lblOrderanLainPlg;





    public void loadChart(MouseEvent mouseEvent) throws SQLException {

        Connection connection = DbConnect.getInstance().getConnection();

        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();
        XYChart.Series<String, Double> series3 = new XYChart.Series<>();
        try {

            Connection connection2 = DbConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT bulan, sum(jml_produk) FROM pemesanan GROUP BY BULAN order by tanggal_pemesanan asc ");
            while (resultSet.next()) {
                series1.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getDouble(2)));
                series2.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getDouble(2)));
                series3.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getDouble(2)));
            }
            barChart.getData().add(series1);
            lineChart.getData().add(series2);
            areaChart.getData().add(series3);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GAGAL");
        }

        totalorder();

    }

    void refresh() {
        Connection connection = DbConnect.getInstance().getConnection();

        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();
        XYChart.Series<String, Double> series3 = new XYChart.Series<>();
        try {

            Connection connection2 = DbConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT bulan, sum(jml_produk) FROM pemesanan GROUP BY BULAN order by tanggal_pemesanan asc");
            while (resultSet.next()) {
                series1.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getDouble(2)));
                series2.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getDouble(2)));
                series3.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getDouble(2)));
            }
    //        barChart.getData().add(series1);
    //        lineChart.getData().add(series2);
    //        areaChart.getData().add(series3);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GAGAL");
        }



    }

    void totalorder( ) {
        Connection connection = DbConnect.getInstance().getConnection();

        try {

           String query = "select sum(jml_produk) from pemesanan ";
            PreparedStatement PS = connection.prepareStatement(query);
            ResultSet result = PS.executeQuery();
            result.next();
            lblTotalOrder.setText(result.getString(1));
        //    lblTotalOrder.setText("0");

            String query2 = "select sum(jml_produk) from pemesanan where jenis_produk =\"BAJU\" ";
            PreparedStatement PS2 = connection.prepareStatement(query2);
            ResultSet result2 = PS2.executeQuery();
            result2.next();
            lblOrderBaju.setText(result2.getString(1));
        //    lblOrderBaju.setText("0");

            String query3 = "select sum(jml_produk) from pemesanan where jenis_produk =\"KEMEJA\" ";
            PreparedStatement PS3 = connection.prepareStatement(query3);
            ResultSet result3 = PS3.executeQuery();
            result3.next();
            lblOrderKemeja.setText(result3.getString(1));
        //    lblOrderKemeja.setText("0");

            String query4 = "select sum(jml_produk) from pemesanan where jenis_produk =\"JERSEY\" ";
            PreparedStatement PS4 = connection.prepareStatement(query4);
            ResultSet result4 = PS4.executeQuery();
            result4.next();
         //   lblOrderJersey.setText("0");
            lblOrderJersey.setText(result4.getString(1));



            String query5 = "select sum(jml_produk) from pemesanan where jenis_produk <> \"JERSEY\" AND jenis_produk <> \"BAJU\" AND jenis_produk <> \"KEMEJA\"";
            PreparedStatement PS5 = connection.prepareStatement(query5);
            ResultSet result5 = PS5.executeQuery();
            result5.next();
        //    lblOrderanLain.setText("0");
            lblOrderanLain.setText(result5.getString(1));


            String query6 = "select count(no_pesanan) from pemesanan ";
            PreparedStatement PS6 = connection.prepareStatement(query6);
            ResultSet result6 = PS6.executeQuery();
            result6.next();
            lblTotalOrderPlg.setText(result6.getString(1));
            //    lblTotalOrder.setText("0");

            String query7 = "select count(no_pesanan) from pemesanan where jenis_produk =\"BAJU\" ";
            PreparedStatement PS7 = connection.prepareStatement(query7);
            ResultSet result7 = PS7.executeQuery();
            result7.next();
            lblOrderBajuPlg.setText(result7.getString(1));
            //    lblOrderBaju.setText("0");

            String query8 = "select count(no_pesanan) from pemesanan where jenis_produk =\"KEMEJA\" ";
            PreparedStatement PS8 = connection.prepareStatement(query8);
            ResultSet result8 = PS8.executeQuery();
            result8.next();
            lblOrderKemejaPlg.setText(result8.getString(1));
            //    lblOrderKemeja.setText("0");

            String query9 = "select count(no_pesanan) from pemesanan where jenis_produk =\"JERSEY\" ";
            PreparedStatement PS9 = connection.prepareStatement(query9);
            ResultSet result9 = PS9.executeQuery();
            result9.next();
            //   lblOrderJersey.setText("0");
            lblOrderJerseyPlg.setText(result9.getString(1));



            String query10 = "select count(no_pesanan) from pemesanan where jenis_produk <> \"JERSEY\" AND jenis_produk <> \"BAJU\" AND jenis_produk <> \"KEMEJA\"";
            PreparedStatement PS10 = connection.prepareStatement(query10);
            ResultSet result10 = PS10.executeQuery();
            result10.next();
            //    lblOrderanLain.setText("0");
            lblOrderanLainPlg.setText(result10.getString(1));


        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

}
