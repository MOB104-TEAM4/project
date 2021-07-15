/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class demo extends javax.swing.JFrame {

    
    /**
     * Creates new form demo
     */
    public demo() {
        initComponents();

    }
     public void start(Stage primaryStage) throws Exception {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Biểu đồ thông kê");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Percent");

        // Tạo đối tượng BarChart
        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

        // Series 1 - Số liệu năm 2014
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.setName("Toàn TPhcm");

        dataSeries1.getData().add(new XYChart.Data<String, Number>("Người cách ly", 15000));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Người tiếp xúc", 50000));
//       dataSeries1.getData().add(new XYChart.Data<String, Number>("PHP", 2.792));

        // Series 2 - Số liệu năm 2015
        XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
        dataSeries2.setName("Quận Tân Phú");

        dataSeries2.getData().add(new XYChart.Data<String, Number>("Người cách ly", 600));
        dataSeries2.getData().add(new XYChart.Data<String, Number>("Người tiếp xúc", 300));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        bieudo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bieudoLayout = new javax.swing.GroupLayout(bieudo);
        bieudo.setLayout(bieudoLayout);
        bieudoLayout.setHorizontalGroup(
            bieudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bieudoLayout.setVerticalGroup(
            bieudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bieudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 819, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bieudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new demo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bieudo;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}