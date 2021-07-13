/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Admin
 */
public class bai11 extends Application{
    @Override
   public void start(Stage primaryStage) throws Exception {
 
       CategoryAxis xAxis = new CategoryAxis();
       xAxis.setLabel("Programming Language");
 
       NumberAxis yAxis = new NumberAxis();
       yAxis.setLabel("Percent");
 
  
       // Tạo đối tượng BarChart
       BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
 
  
       // Series 1 - Số liệu năm 2014
       XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
       dataSeries1.setName("2014");
 
       dataSeries1.getData().add(new XYChart.Data<String, Number>("Java", 20.973));
       dataSeries1.getData().add(new XYChart.Data<String, Number>("C#", 4.429));
       dataSeries1.getData().add(new XYChart.Data<String, Number>("PHP", 2.792));
 
  
       // Series 2 - Số liệu năm 2015
       XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
       dataSeries2.setName("2015");
 
       dataSeries2.getData().add(new XYChart.Data<String, Number>("Java", 26.983));
       dataSeries2.getData().add(new XYChart.Data<String, Number>("C#", 6.569));
       dataSeries2.getData().add(new XYChart.Data<String, Number>("PHP", 6.619));
 
  
       // Thêm Series vào BarChart
       barChart.getData().add(dataSeries1);
       barChart.getData().add(dataSeries2);
 
       barChart.setTitle("Some Programming Languages");
 
  
       // Thay đổi dữ liệu ngẫu nhiên sau mỗi 1 giây.
       Timeline timeline = new Timeline();
       timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
           public void handle(ActionEvent actionEvent) {
               for (XYChart.Series<String, Number> series : barChart.getData()) {
                   for (XYChart.Data<String, Number> data : series.getData()) {
                       Number yValue = data.getYValue();
                       Number randomValue = yValue.doubleValue() * (0.2 + Math.random());
                       data.setYValue(randomValue);
                   }
               }
           }
       }));
  
       // Lặp vô thời hạn cho tới khi phương thức stop() được gọi.
       timeline.setCycleCount(Animation.INDEFINITE);
       timeline.setAutoReverse(true);
       timeline.play();
 
       VBox vbox = new VBox(barChart);
 
       primaryStage.setTitle("JavaFX BarChart (o7planning.org)");
       Scene scene = new Scene(vbox, 400, 200);
 
       primaryStage.setScene(scene);
       primaryStage.setHeight(300);
       primaryStage.setWidth(400);
 
       primaryStage.show();
   }
 
   public static void main(String[] args) {
       Application.launch(args);
   }

   
}
