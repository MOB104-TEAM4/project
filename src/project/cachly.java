/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class cachly {

    private String macachly;
    private String hoten;
    private boolean gioitinh;
    private String dien;
    private String ngaysinh;
    private String ngaycachly;
    private String diachi;
    private String ghichu;
    private String sdt;
            

    public static List<cachly> findbyma(String MaCachLi) {
        List<cachly> ds = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=project", "sa", "123");
            String sql = "select * from NguoiCachLi where  MaCachLi like ? ";
            statement = con.prepareCall(sql);
            statement.setString(1, "%"+MaCachLi+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                cachly data = new cachly();
                rs.getString("MaCachLi");
                rs.getString("Ten");
                String gt = rs.getBoolean("GioiTinh") == true ? "Nam" : "Nữ";
                rs.getString("ngsinh");
                rs.getString("Sdt");
                rs.getString("DiaChi");
                rs.getString("NgayCachLi");
                rs.getString("Dien");
                rs.getString("GhiChu");
                // Thêm một dòng vào table model
                ds.add(data);
            }

        } catch (SQLException ex) {
            Logger.getLogger(cachly.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cachly.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cachly.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return ds;
    }
//     public static List<cachly> findbydien(String Dien) {
//        List<cachly> ds = new ArrayList<>();
//        Connection con = null;
//        PreparedStatement statement = null;
//        try {
//            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=project", "sa", "123");
//            String sql = "select * from NguoiCachLy where  Dien like ? ";
//            statement = con.prepareCall(sql);
//            statement.setString(1, "%"+ Dien+"%");
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                cachly data = new cachly();
//                rs.getString("MaCachLi");
//                rs.getString("Ten");
//                String gt = rs.getBoolean("GioiTinh") == true ? "Nam" : "Nữ";
//                rs.getString("ngsinh");
//                rs.getString("Sdt");
//                rs.getString("DiaChi");
//                rs.getString("NgayCachLi");
//                rs.getString("Dien");
//                rs.getString("GhiChu");
//                // Thêm một dòng vào table model
//                ds.add(data);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(cachly.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(cachly.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(cachly.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//        }
//        return ds;
//    }
    public cachly(String macachly, String hoten, boolean gioitinh, String dien, String ngaysinh, String ngaycachly, String diachi, String ghichu, String sdt) {
        this.macachly = macachly;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.dien = dien;
        this.ngaysinh = ngaysinh;
        this.ngaycachly = ngaycachly;
        this.diachi = diachi;
        this.ghichu = ghichu;
        this.sdt = sdt;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

  

    public cachly() {
    }

    public String getMacachly() {
        return macachly;
    }

    public void setMacachly(String macachly) {
        this.macachly = macachly;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public boolean getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDien() {
        return dien;
    }

    public void setDien(String dien) {
        this.dien = dien;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getNgaycachly() {
        return ngaycachly;
    }

    public void setNgaycachly(String ngaycachly) {
        this.ngaycachly = ngaycachly;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

}
