/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class bai1 extends Application {

    String user = "sa";
    String pass = "123";
    String url = "jdbc:sqlserver://localhost:1433;database=project1;";

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        Connection con = DriverManager.getConnection(url, user, pass);
//       String sql = "select count(*) as 'Size' from NguoiCachLi;";
//        java.sql.Statement st = con.prepareStatement(sql);
//        ResultSet rs = st.executeQuery(sql);
//        String sizeNclString = null;
//        while (rs.next()) {
//            sizeNclString = rs.getString("Size");
//        }
//        int sizeNclInt = Integer.parseInt(sizeNclString);
     
        JButton btn1 = new JButton();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Biểu đồ thông kê");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Percent");
        List<cachly> cl = new ArrayList<cachly>();

        // Tạo đối tượng BarChart
        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

        // Series 1 - Số liệu năm 2014
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.setName("Toàn TPhcm");

        dataSeries1.getData().add(new XYChart.Data<String, Number>("Người cách ly", 10));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Người tiếp xúc", 5));
//       dataSeries1.getData().add(new XYChart.Data<String, Number>("PHP", 2.792));

        // Series 2 - Số liệu năm 2015
        XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
        dataSeries2.setName("Quận Tân Phú");

        dataSeries2.getData().add(new XYChart.Data<String, Number>("Người cách ly", 15));
        dataSeries2.getData().add(new XYChart.Data<String, Number>("Người tiếp xúc", 3));
//       dataSeries2.getData().add(new XYChart.Data<String, Number>("PHP", 6.619));

        // Thêm Series vào BarChart
        barChart.getData().add(dataSeries1);
        barChart.getData().add(dataSeries2);

        barChart.setTitle("Biểu đồ so sánh số ca nhiễm của Quận Tân Phú so với số ca nhiễm của TPHCM");

        VBox vbox = new VBox(barChart);

        primaryStage.setTitle("");
        Scene scene = new Scene(vbox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(1000);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
        duan frame = new duan();
        frame.setDefaultCloseOperation(0);
        frame.setVisible(true);

    }

}
