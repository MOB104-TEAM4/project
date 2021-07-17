/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author nghiacubu
 */
public class addImgNv {
    public static void main(String[] args) {
        String user = "sa";
        String pass = "nhom03ud16316";
        String url = "jdbc:sqlserver://database-1.czbehjnea854.us-east-2.rds.amazonaws.com:1433;databaseName=project";
        FileInputStream fis = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "Update HinhAnh set images = ? where MaUser = 'NV001' ";
            PreparedStatement st = con.prepareStatement(sql);
            File file = new File("admin.jpg");
            fis = new FileInputStream(file);
            st.setBinaryStream(1, fis, (int) file.length());
            st.executeUpdate();
            con.commit();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
