
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.VerticalAlignment;
import org.jfree.data.category.DefaultCategoryDataset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nghiacubu
 */
public class QuanLyThongKe {

    public void setDataToChart1(JPanel jpnItem) {
        List<nguoiCL> listNcl = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=project1;", "sa", "123");
            String sql = "SELECT NgayCachLi , COUNT(*) as so_luong FROM NguoiCachLi GROUP BY NgayCachLi;";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                nguoiCL ncl = new nguoiCL();
                ncl.setNgay(rs.getString("NgayCachLi"));
                ncl.setSoluong(rs.getInt("so_luong"));
                listNcl.add(ncl);
            }
            st.close();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       
        if (listNcl != null) {
            for (nguoiCL item : listNcl) {
                dataset.addValue(item.getSoluong(), "QUAN TAN PHU", item.getNgay());
                for (int i = 0; i <= listNcl.size(); i++) {
                     int n = (int) ((Math.random() + 1) * 30);
                     dataset.addValue(n, "TPHCM", item.getNgay());
                     
                }
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart("Biểu đồ thống kê".toUpperCase(), "So sánh người bệnh trong quận Tân Phú với toàn TPHCM", "Số lượng", dataset, PlotOrientation.VERTICAL, true, true, false);
        //Đổi màu background không chứa biểu đồ
        barChart.setBackgroundPaint(new Color(247,247,247));
        CategoryPlot cplot = (CategoryPlot) barChart.getPlot();
        //Đổi màu background chứa biểu đồ 
        cplot.setBackgroundPaint(new Color(194,213,236));//change background color
        //set  bar chart color
        ((BarRenderer) cplot.getRenderer()).setBarPainter(new StandardBarPainter());
        BarRenderer r = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        //Đổi màu cột 1 
        r.setSeriesPaint(0,new Color(0,49,231));
       //Đổi màu cột 2
        r.setSeriesPaint(1, new Color(255,51,51));
        TextTitle chartinfo = new TextTitle("Chú thích", new Font("Tahoma", Font.BOLD, 18), TextTitle.DEFAULT_TEXT_PAINT, RectangleEdge.BOTTOM, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, RectangleInsets.ZERO_INSETS);
//        chartinfo.setPosition(TextTitle.); // đặt tiêu đề bên dưới trục X
//        chartinfo.setHorizontalAlignment( TextTitle.LEFT ); // thiết lập căn lề ngang
        barChart.addSubtitle(chartinfo); // Đây là biểu đồ của JFreeChart     
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 421));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

}
