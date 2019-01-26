package app.controller;

import app.database.DbConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class reportController implements Initializable {

    @FXML
    private LineChart<String, Double> lineChart;

    @FXML
    private LineChart<String, Double> lineChart2;

    @FXML
    private LineChart<String, Double> lineChart3;

    @FXML
    private LineChart<String, Double> lineChart4;

    @FXML
    private LineChart<String, Double> lineChart5;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadChartTotal();
        loadChartBaju();
        loadChartKemeja();
        loadChartJersey();
        loadChartLainnya();

    }

    private void loadChartTotal() {
        Connection connection = DbConnect.getInstance().getConnection();

        XYChart.Series<String, Double> series1 = new XYChart.Series<>();

        try {
            Connection connection2 = DbConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT tanggal_pemesanan, sum(jml_produk) FROM pemesanan GROUP BY tanggal_pemesanan order by tanggal_pemesanan");
            while (resultSet.next()) {
                series1.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getDouble(2)));
            }
            lineChart.getData().add(series1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GAGAL");
        }
    }

    private void loadChartBaju() {
        Connection connection = DbConnect.getInstance().getConnection();

        XYChart.Series<String, Double> series1 = new XYChart.Series<>();

        try {
            Connection connection2 = DbConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT tanggal_pemesanan, sum(jml_produk) FROM pemesanan where jenis_produk=\"BAJU\" GROUP BY tanggal_pemesanan order by tanggal_pemesanan");
            while (resultSet.next()) {
                series1.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getDouble(2)));
            }
            lineChart2.getData().add(series1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GAGAL");
        }
    }

    private void loadChartKemeja() {
        Connection connection = DbConnect.getInstance().getConnection();

        XYChart.Series<String, Double> series1 = new XYChart.Series<>();

        try {
            Connection connection2 = DbConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT tanggal_pemesanan, sum(jml_produk) FROM pemesanan where jenis_produk=\"KEMEJA\" GROUP BY tanggal_pemesanan order by tanggal_pemesanan");
            while (resultSet.next()) {
                series1.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getDouble(2)));
            }
            lineChart3.getData().add(series1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GAGAL");
        }
    }

    private void loadChartJersey() {
        Connection connection = DbConnect.getInstance().getConnection();

        XYChart.Series<String, Double> series1 = new XYChart.Series<>();

        try {
            Connection connection2 = DbConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT tanggal_pemesanan, sum(jml_produk) FROM pemesanan where jenis_produk=\"JERSEY\" GROUP BY tanggal_pemesanan order by tanggal_pemesanan");
            while (resultSet.next()) {
                series1.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getDouble(2)));
            }
            lineChart4.getData().add(series1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GAGAL");
        }
    }


    private void loadChartLainnya() {
        Connection connection = DbConnect.getInstance().getConnection();

        XYChart.Series<String, Double> series1 = new XYChart.Series<>();

        try {
            Connection connection2 = DbConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT tanggal_pemesanan, sum(jml_produk) FROM pemesanan where jenis_produk <> \"JERSEY\" AND jenis_produk <> \"BAJU\" AND jenis_produk <> \"KEMEJA\"GROUP BY tanggal_pemesanan order by tanggal_pemesanan");
            while (resultSet.next()) {
                series1.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getDouble(2)));
            }
            lineChart5.getData().add(series1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GAGAL");
        }
    }





    public void exportExcel(MouseEvent mouseEvent) {
    }

    public void exportPdf(MouseEvent mouseEvent) {
    }

    public void filterDataPesanan(KeyEvent keyEvent) {
    }

    public void refreshTablePesanan(MouseEvent mouseEvent) {
    }

    public void clearFilterTablePesanan(MouseEvent mouseEvent) {
    }

    public void refreshChart(MouseEvent mouseEvent) {
        loadChartTotal();
        loadChartBaju();
        loadChartKemeja();
        loadChartJersey();
        loadChartLainnya();

    }

    public void loadChart(MouseEvent mouseEvent) {
    }
}
