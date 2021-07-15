/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelTmp.tmpImage;
import modelTmp.tmpNTX;

/**
 *
 * @author Admin
 */
public class duan extends javax.swing.JFrame {

    List<cachly> cl = new ArrayList<cachly>();
    int index = 0;
    String user = "sa";
    String pass = "123";
    String url = "jdbc:sqlserver://localhost:1433;database=project;";
    DefaultTableModel dtm;
    DefaultTableModel dtm1;
    DefaultTableModel dtm2;
    DefaultTableModel dtm3;
    DefaultTableModel dtm4;
    List<tmpImage> ImageNvList = new ArrayList<>();

    /**
     * Creates new form duan
     */
    public duan() throws ClassNotFoundException, SQLException {
        initComponents();
        filltable();
        filltable1();
        filltable2(index);
        filltable3();
        filltableNhanVien();
        getImgNhanvien();
    }

    public void fillcontrol(int index) {
//        student ds = sv.get(index);
        txt_macl.setText(tbl_ncl.getValueAt(index, 0).toString());
        txt_hoten.setText(tbl_ncl.getValueAt(index, 1).toString());
        String gt = tbl_ncl.getValueAt(index, 2).toString();
        if (gt.equals("Nam")) {
            rdonam.setSelected(true);
            rdonu.setSelected(false);
        } else {
            rdonu.setSelected(true);
            rdonam.setSelected(false);
        }
        txt_ngaysinh.setText(tbl_ncl.getValueAt(index, 3).toString());
        txt_sdt1.setText(tbl_ncl.getValueAt(index, 4).toString());
        txt_diachi.setText(tbl_ncl.getValueAt(index, 5).toString());
        txt_ngaycl.setText(tbl_ncl.getValueAt(index, 6).toString());
        txt_dien1.setText(tbl_ncl.getValueAt(index, 7).toString());
        txt_ghichu.setText(tbl_ncl.getValueAt(index, 8).toString());
       
    }

    public void fillcontrol1(int index) {
//        student ds = sv.get(index);
        txt_matx.setText(tbl_tx.getValueAt(index, 0).toString());
        txt_macl1.setText(tbl_tx.getValueAt(index, 1).toString());
        txt_hotentx.setText(tbl_tx.getValueAt(index, 2).toString());
        String gt = tbl_tx.getValueAt(index, 3).toString();
        if (gt.equals("Nam")) {
            rdo_namtx.setSelected(true);
            rdo_nutx.setSelected(false);
        } else {
            rdo_nutx.setSelected(true);
            rdo_namtx.setSelected(false);
        }
        txt_ngaysinhtx.setText(tbl_tx.getValueAt(index, 4).toString());

        txt_diachitx.setText(tbl_tx.getValueAt(index, 5).toString());

        txt_dien2.setText(tbl_tx.getValueAt(index, 6).toString());

    }
    
    public void fillcontrol3(int index) {
//        student ds = sv.get(index);
        txt_macl3.setText(tbl_benh.getValueAt(index, 0).toString());
        txt_ngaycl3.setText(tbl_benh.getValueAt(index, 1).toString());
        txt_dien3.setText(tbl_benh.getValueAt(index, 2).toString());
        txt_bieuhien3.setText(tbl_benh.getValueAt(index, 3).toString());
        txt_ghichu3.setText(tbl_benh.getValueAt(index, 4).toString());
    }
    
    public void fillcontrolNhanVien(int index) {
//        student ds = sv.get(index);
        txt_manvien.setText(tbl_nhanvien.getValueAt(index, 0).toString());
        txt_tenNvien.setText(tbl_nhanvien.getValueAt(index, 1).toString());
        txt_ngsinhNv.setText(tbl_nhanvien.getValueAt(index, 2).toString());
        String gt = tbl_nhanvien.getValueAt(index, 3).toString();
        if (gt.equals("Nam")) {
            rdonamNv.setSelected(true);
            rdonuNv.setSelected(false);
        } else {
            rdonuNv.setSelected(true);
            rdonamNv.setSelected(false);
        }
        txt_sdtNv.setText(tbl_nhanvien.getValueAt(index, 4).toString());
        txt_roleNvien.setText(tbl_nhanvien.getValueAt(index, 5).toString());
    }

    public void filltable() {
        dtm = (DefaultTableModel) tbl_ncl.getModel();
        dtm.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from NguoiCachLi";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            cl.clear();

            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString("MaCachLi"));
                data.add(rs.getString("Ten"));
                data.add(rs.getBoolean("GioiTinh") == true ? "Nam" : "Nữ");
                data.add(rs.getString("ngsinh"));
                data.add(rs.getString("Sdt"));
                data.add(rs.getString("DiaChi"));
                data.add(rs.getString("NgayCachLi"));
                data.add(rs.getString("Dien"));
                data.add(rs.getString("GhiChu"));
                // Thêm một dòng vào table model
                dtm.addRow(data);

            }
            tbl_ncl.setModel(dtm);
            dtm.fireTableDataChanged();
            rs.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void filltable1() {
        dtm1 = (DefaultTableModel) tbl_tx.getModel();
        dtm1.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from NguoiTiepXuc";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            cl.clear();

            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString("MaTiepXuc"));
                data.add(rs.getString("MaCachLi"));
                data.add(rs.getString("Ten"));
                data.add(rs.getBoolean("GioiTinh") == true ? "Nam" : "Nữ");
                data.add(rs.getString("ngsinh"));
                data.add(rs.getString("DiaChi"));
                data.add(rs.getString("Dien"));

                // Thêm một dòng vào table model
                dtm1.addRow(data);

            }
            tbl_tx.setModel(dtm1);
            dtm1.fireTableDataChanged();
            rs.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void filltable2(int index) {
        String tmp = (String) tbl_ncl.getModel().getValueAt(index, 0);
        dtm2 = (DefaultTableModel) tbl_tx1.getModel();
        dtm2.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select *from NguoiTiepXuc ";;
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            cl.clear();

            while (rs.next()) {
                String tmpString = rs.getString("MaCachLi");
                if (tmpString.equals(tmp)) {
                    Vector data = new Vector();
                    data.add(rs.getString("MaTiepXuc"));
                    data.add(rs.getString("MaCachLi"));
                    data.add(rs.getString("Ten"));
                    data.add(rs.getBoolean("GioiTinh") == true ? "Nam" : "Nữ");
                    data.add(rs.getString("ngsinh"));
                    data.add(rs.getString("DiaChi"));
                    data.add(rs.getString("Dien"));
                    // Thêm một dòng vào table model
                    dtm2.addRow(data);
                }
            }
            tbl_tx1.setModel(dtm2);
            dtm2.fireTableDataChanged();
            rs.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void filltable3() {
        dtm3 = (DefaultTableModel) tbl_benh.getModel();
        dtm3.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from benh";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            cl.clear();

            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString("macachli"));
                data.add(rs.getString("ngay"));
                data.add(rs.getString("dien"));
                data.add(rs.getString("bieuhien"));
                data.add(rs.getString("ghichu"));
                // Thêm một dòng vào table model
                dtm3.addRow(data);
            }
            tbl_benh.setModel(dtm3);
            dtm3.fireTableDataChanged();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void filltableNhanVien() {
        dtm4 = (DefaultTableModel) tbl_nhanvien.getModel();
        dtm4.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from NhanVien";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            cl.clear();

            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString("MaUser"));
                data.add(rs.getString("Ten"));
                data.add(rs.getString("ngaysinh"));
                data.add(rs.getBoolean("GioiTinh") == true ? "Nam" : "Nữ");
                data.add(rs.getString("sdt"));
                data.add(rs.getString("roler"));
                dtm4.addRow(data);
            }
            tbl_nhanvien.setModel(dtm4);
            dtm4.fireTableDataChanged();
            
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public void getImgNhanvien() throws ClassNotFoundException, SQLException{
        for (tmpImage object : ImageNvList) {
            ImageNvList.remove(object);
        }
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(url, user, pass);
        String sqlImg = "select * from HinhAnh";
        java.sql.Statement st = con.createStatement();
        ResultSet rsImg = st.executeQuery(sqlImg);
        while (rsImg.next()) {
            tmpImage tmp = new tmpImage();
            String manv = rsImg.getString("MaUser");
            byte [] imgData = rsImg.getBytes("images");
            tmp.setManv(manv);
            tmp.setImg(imgData);
            ImageNvList.add(tmp);
        }
        rsImg.close();
        for (tmpImage object : ImageNvList) {
            System.out.println(object.getImg());
        }
    }
    
    public void fillImageNvien (int index) throws SQLException, IOException{
        tmpImage tmpImg = ImageNvList.get(index);
        byte [] data = tmpImg.getImg();
        System.out.println(data);
        try {
            FileOutputStream fos = new FileOutputStream("tmpImg.jpg");
            fos.write(data);
            fos.close();
        } catch (Exception e) {
        }
        
        
        //ImageIcon icon = new ImageIcon(img);
       // lblImgNv.setIcon((ImageIcon)icon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_macl = new javax.swing.JTextField();
        txt_hoten = new javax.swing.JTextField();
        rdonam = new javax.swing.JRadioButton();
        rdonu = new javax.swing.JRadioButton();
        txt_ngaysinh = new javax.swing.JTextField();
        txt_ngaycl = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_diachi = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_ncl = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txt_ghichu = new javax.swing.JTextArea();
        btn_them1 = new javax.swing.JButton();
        btn_xoa1 = new javax.swing.JButton();
        btn_sua1 = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        txt_dien1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_sdt1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txt_tim = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        cbx_timdien = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        cbx_gt = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_tx1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_benh = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        txt_macl3 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_ngaycl3 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txt_ghichu3 = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txt_bieuhien3 = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        btnThemBenh = new javax.swing.JButton();
        btnXoaBenh = new javax.swing.JButton();
        btnSuaBenh = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        txt_dien3 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        txt_timBenh = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        cbx_timdienBenh = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_matx = new javax.swing.JTextField();
        txt_hotentx = new javax.swing.JTextField();
        txt_ngaysinhtx = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_tx = new javax.swing.JTable();
        btn_savetx = new javax.swing.JButton();
        btnExit3 = new javax.swing.JButton();
        btnSua3 = new javax.swing.JButton();
        btnXoa3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rdo_namtx = new javax.swing.JRadioButton();
        rdo_nutx = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_macl1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_diachitx = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txt_tim3 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        cbx_timdien3 = new javax.swing.JComboBox<>();
        txt_dien2 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        cbx_gt3 = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txt_manvien = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txt_tenNvien = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txt_ngsinhNv = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txt_sdtNv = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txt_roleNvien = new javax.swing.JTextField();
        lblImgNv = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbl_nhanvien = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        rdonamNv = new javax.swing.JRadioButton();
        rdonuNv = new javax.swing.JRadioButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        txt_timNv = new javax.swing.JTextField();
        btn_savetx1 = new javax.swing.JButton();
        btnXoa4 = new javax.swing.JButton();
        btnSua4 = new javax.swing.JButton();
        btnExit4 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        cbx_gtNv = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel9.setFont(new java.awt.Font("Sitka Heading", 3, 24)); // NOI18N
        jLabel9.setText("Thông tin người cách ly");

        buttonGroup1.add(rdonam);
        rdonam.setText("Nam");

        buttonGroup1.add(rdonu);
        rdonu.setText("Nữ");

        txt_diachi.setColumns(20);
        txt_diachi.setRows(5);
        jScrollPane3.setViewportView(txt_diachi);

        jLabel10.setText("Địa chỉ");

        jLabel11.setText("Ngày cách ly");

        jLabel12.setText("Ngày sinh");

        jLabel13.setText("Diện");

        jLabel14.setText("Giới tính");

        jLabel15.setText("Họ và tên");

        jLabel16.setText("Mã cách ly");

        tbl_ncl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã cách ly", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Ngày cách ly", "Diện", "Ghi chú"
            }
        ));
        tbl_ncl.getTableHeader().setReorderingAllowed(false);
        tbl_ncl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nclMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_ncl);

        jLabel17.setText("Ghi chú");

        txt_ghichu.setColumns(20);
        txt_ghichu.setRows(5);
        jScrollPane5.setViewportView(txt_ghichu);

        btn_them1.setText("Thêm");
        btn_them1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them1ActionPerformed(evt);
            }
        });

        btn_xoa1.setText("Xóa");
        btn_xoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoa1ActionPerformed(evt);
            }
        });

        btn_sua1.setText("Sửa");
        btn_sua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sua1ActionPerformed(evt);
            }
        });

        btn_exit.setText("Exit");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        txt_dien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dien1ActionPerformed(evt);
            }
        });

        jLabel25.setText("Sđt");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel26.setText("Tìm mã cách ly");

        txt_tim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txt_tim)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel27.setText("Tìm theo diện");

        cbx_timdien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F0", "F1", "F2", "F3", "F4", "F5", "None", " " }));
        cbx_timdien.setSelectedItem("None");
        cbx_timdien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_timdienItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(cbx_timdien, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cbx_timdien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel28.setText("Lọc giới tính");

        cbx_gt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "None", "" }));
        cbx_gt.setSelectedItem("None");
        cbx_gt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_gtItemStateChanged(evt);
            }
        });

        tbl_tx1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã tiếp xúc", "Mã cách ly", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ ", "Diện"
            }
        ));
        tbl_tx1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tx1MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbl_tx1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_dien1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(46, 46, 46)
                                    .addComponent(txt_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_ngaycl)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                                        .addComponent(txt_sdt1))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel28)
                                    .addComponent(btn_them1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btn_xoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_sua1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbx_gt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1309, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(102, 102, 102)
                            .addComponent(rdonam)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rdonu))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(113, 113, 113)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_hoten, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                .addComponent(txt_macl)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addComponent(jLabel9)))
                    .addContainerGap(1340, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(398, 398, 398)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(206, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel16)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel15)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txt_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txt_sdt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_ngaycl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_dien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cbx_gt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them1)
                    .addComponent(btn_xoa1)
                    .addComponent(btn_sua1)
                    .addComponent(btn_exit)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel9)
                    .addGap(38, 38, 38)
                    .addComponent(txt_macl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(txt_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdonam)
                        .addComponent(rdonu))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(538, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Thông tin người cách ly", jPanel2);

        tbl_benh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã cách ly", "Ngày", "Diện", "Biểu hiện", "Ghi chú"
            }
        ));
        tbl_benh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_benhMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_benh);

        jLabel18.setText("Mã cách ly");

        jLabel19.setText("Ngày cách ly");

        jLabel20.setText("Ghi chú");

        txt_ghichu3.setColumns(20);
        txt_ghichu3.setRows(5);
        jScrollPane7.setViewportView(txt_ghichu3);

        jLabel21.setText("Diện");

        jLabel22.setText("Biểu hiện");

        txt_bieuhien3.setColumns(20);
        txt_bieuhien3.setRows(5);
        jScrollPane8.setViewportView(txt_bieuhien3);

        jLabel23.setFont(new java.awt.Font("Sitka Heading", 3, 24)); // NOI18N
        jLabel23.setText("Thông tin biểu hiện bệnh");

        btnThemBenh.setText("Thêm");
        btnThemBenh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemBenhActionPerformed(evt);
            }
        });

        btnXoaBenh.setText("Xóa");
        btnXoaBenh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaBenhActionPerformed(evt);
            }
        });

        btnSuaBenh.setText("Sửa");
        btnSuaBenh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaBenhActionPerformed(evt);
            }
        });

        jButton19.setText("Exit");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        txt_dien3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dien3ActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel30.setText("Tìm mã cách ly");

        txt_timBenh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timBenhKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txt_timBenh)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_timBenh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel34.setText("Tìm theo diện");

        cbx_timdienBenh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F0", "F1", "F2", "F3", "F4", "F5", "None", " " }));
        cbx_timdienBenh.setSelectedItem("None");
        cbx_timdienBenh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_timdienBenhItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addGap(18, 18, 18)
                .addComponent(cbx_timdienBenh, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(cbx_timdienBenh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel21))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_macl3)
                                            .addComponent(txt_ngaycl3, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_dien3))))
                                .addComponent(jLabel22)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(jLabel23))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThemBenh, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaBenh, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaBenh, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1343, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txt_macl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(txt_ngaycl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txt_dien3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemBenh)
                    .addComponent(btnXoaBenh)
                    .addComponent(btnSuaBenh)
                    .addComponent(jButton19))
                .addGap(75, 234, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin biểu hiện bệnh", jPanel3);

        jLabel2.setFont(new java.awt.Font("Sitka Heading", 3, 24)); // NOI18N
        jLabel2.setText("Thông tin người tiếp xúc");

        tbl_tx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã tiếp xúc", "Mã cách ly", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ ", "Diện"
            }
        ));
        tbl_tx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_txMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_tx);

        btn_savetx.setText("Thêm");
        btn_savetx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_savetxActionPerformed(evt);
            }
        });

        btnExit3.setText("Exit");
        btnExit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit3ActionPerformed(evt);
            }
        });

        btnSua3.setText("Sửa");
        btnSua3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua3ActionPerformed(evt);
            }
        });

        btnXoa3.setText("Xóa");
        btnXoa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Ngày sinh");

        jLabel3.setText("Họ và tên");

        jLabel1.setText("Mã tiếp xúc");

        buttonGroup1.add(rdo_namtx);
        rdo_namtx.setText("Nam");

        buttonGroup1.add(rdo_nutx);
        rdo_nutx.setText("Nữ");

        jLabel5.setText("Giới tính");

        jLabel6.setText("Diện");

        jLabel7.setText("Mã cách ly");

        jLabel8.setText("Địa chỉ");

        txt_diachitx.setColumns(20);
        txt_diachitx.setRows(5);
        jScrollPane1.setViewportView(txt_diachitx);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel31.setText("Tìm mã tiếp xúc");

        txt_tim3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tim3KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txt_tim3)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tim3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel32.setText("Tìm theo diện");

        cbx_timdien3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F0", "F1", "F2", "F3", "F4", "F5", "None", " " }));
        cbx_timdien3.setSelectedItem("None");
        cbx_timdien3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_timdien3ItemStateChanged(evt);
            }
        });
        cbx_timdien3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_timdien3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(cbx_timdien3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cbx_timdien3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_dien2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dien2ActionPerformed(evt);
            }
        });

        jLabel29.setText("Lọc giới tính");

        cbx_gt3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "None" }));
        cbx_gt3.setSelectedItem("None");
        cbx_gt3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_gt3ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel2)
                                .addGap(0, 59, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(96, 96, 96)
                                        .addComponent(txt_matx))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(36, 36, 36)
                                        .addComponent(txt_macl1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel8))
                                        .addGap(41, 41, 41)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_hotentx)
                                            .addComponent(jScrollPane1)
                                            .addComponent(txt_ngaysinhtx)
                                            .addComponent(txt_dien2)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(50, 50, 50)
                                        .addComponent(rdo_namtx)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdo_nutx))
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(9, 9, 9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbx_gt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_savetx, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoa3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSua3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExit3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1296, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_matx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_macl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_hotentx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_namtx)
                    .addComponent(rdo_nutx)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_ngaysinhtx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_dien2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(cbx_gt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_savetx)
                    .addComponent(btnXoa3)
                    .addComponent(btnSua3)
                    .addComponent(btnExit3))
                .addGap(0, 184, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );

        jTabbedPane1.addTab("Thông tin người tiếp xúc", jPanel1);

        jPanel7.setBackground(new java.awt.Color(51, 255, 51));

        jLabel33.setFont(new java.awt.Font("Sitka Heading", 3, 24)); // NOI18N
        jLabel33.setText("Thông tin nhân viên");

        jLabel35.setText("Mã nhân viên");

        jLabel36.setText("Họ tên");

        jLabel37.setText("Ngày sinh");

        jLabel39.setText("Số điện thoại");

        jLabel40.setText("Roler");

        tbl_nhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Quyển"
            }
        ));
        tbl_nhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nhanvienMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbl_nhanvien);

        jLabel41.setText("Giới tính");

        buttonGroup1.add(rdonamNv);
        rdonamNv.setText("Nam");

        buttonGroup1.add(rdonuNv);
        rdonuNv.setText("Nữ");

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel38.setText("Tìm mã nhân viên");

        txt_timNv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timNvKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txt_timNv)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_timNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_savetx1.setText("Thêm");
        btn_savetx1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_savetx1ActionPerformed(evt);
            }
        });

        btnXoa4.setText("Xóa");
        btnXoa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa4ActionPerformed(evt);
            }
        });

        btnSua4.setText("Sửa");
        btnSua4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua4ActionPerformed(evt);
            }
        });

        btnExit4.setText("Exit");
        btnExit4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit4ActionPerformed(evt);
            }
        });

        jLabel42.setText("Lọc giới tính");

        cbx_gtNv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "None" }));
        cbx_gtNv.setSelectedItem("None");
        cbx_gtNv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_gtNvItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel33))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_manvien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tenNvien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_roleNvien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblImgNv))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_sdtNv, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel41)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(rdonamNv)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdonuNv))
                                    .addComponent(txt_ngsinhNv, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1383, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btn_savetx1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoa4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSua4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExit4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbx_gtNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_manvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tenNvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ngsinhNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(rdonuNv)
                            .addComponent(rdonamNv))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_sdtNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_roleNvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40))
                        .addGap(29, 29, 29)
                        .addComponent(lblImgNv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(cbx_gtNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_savetx1)
                    .addComponent(btnXoa4)
                    .addComponent(btnSua4)
                    .addComponent(btnExit4))
                .addContainerGap(264, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Quản lý nhân viên", jPanel11);

        jLabel24.setFont(new java.awt.Font("Sitka Heading", 2, 48)); // NOI18N
        jLabel24.setText("Bộ phận quản lý thông tin người nhiễm bệnh và cách ly coivd-19");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addGap(184, 184, 184))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_xoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoa1ActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(this, "Do you want to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (ret != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "delete from NguoiCachLi where MaCachLi = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_macl.getText());
            st.execute();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            con.close();
            filltable();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btn_xoa1ActionPerformed

    private void btnXoa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa3ActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(this, "Do you want to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (ret != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "delete from nguoitiepxuc where matiepxuc = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_matx.getText());
            st.execute();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            con.close();
            filltable1();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnXoa3ActionPerformed

    private void btnXoaBenhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaBenhActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(this, "Do you want to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (ret != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "delete from benh where MaCachLi = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_macl3.getText());
            st.execute();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            con.close();
            filltable3();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnXoaBenhActionPerformed

    private void tbl_nclMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nclMouseClicked
        // TODO add your handling code here:
        index = tbl_ncl.getSelectedRow();
        fillcontrol(index);
        filltable2(index);
    }//GEN-LAST:event_tbl_nclMouseClicked

    private void txt_dien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dien1ActionPerformed

    private void btn_them1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them1ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "insert into NguoiCachLi(  MaCachLi ,Ten,GioiTinh,ngsinh,Sdt,DiaChi,NgayCachLi,Dien,GhiChu) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_macl.getText());
            st.setString(2, txt_hoten.getText());
            boolean gt;
            if (rdonam.isSelected() == true) {
                gt = true;
            } else {
                gt = false;
            }
            st.setBoolean(3, gt);
            st.setString(4, txt_ngaysinh.getText());
            st.setString(5, txt_sdt1.getText());
            st.setString(6, txt_diachi.getText());
            st.setString(7, txt_ngaycl.getText());
            st.setString(8, txt_dien1.getText());
            st.setString(9, txt_ghichu.getText());
            st.execute();
            JOptionPane.showMessageDialog(this, "Nhập thành công");
            filltable();

        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Nhập thất bại");
        }
    }//GEN-LAST:event_btn_them1ActionPerformed

    private void btn_sua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sua1ActionPerformed
        // TODO add your handling code here:
        try {
            char tmpDien = txt_dien1.getText().charAt(1);
            int InttmpDien = Integer.parseInt(String.valueOf(tmpDien));
            String tmpDien1 =  (String) tbl_ncl.getModel().getValueAt(index, 7);
            char tmpDien1Char = tmpDien1.charAt(1);
            int InttmpDien1 = Integer.parseInt(String.valueOf(tmpDien1Char));
            if (InttmpDien == InttmpDien1 || InttmpDien<0 ) {
                JOptionPane.showMessageDialog(this, "Sua khong thanh cong");
            } else {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection(url, user, pass);
                String sql = "Update NguoiCachLi set Ten =?,GioiTinh = ?,ngsinh =?,Sdt = ?,"
                        + "DiaChi = ? ,NgayCachLi = ?,Dien = ?,GhiChu = ?"
                        + " where  MaCachLi = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(9, txt_macl.getText());
                st.setString(1, txt_hoten.getText());
                boolean gt;
                if (rdonam.isSelected()) {
                    gt = true;
                } else {
                    gt = false;
                }
                st.setBoolean(2, gt);
                st.setString(3, txt_ngaysinh.getText());
                st.setString(4, txt_sdt1.getText());
                st.setString(5, txt_diachi.getText());
                st.setString(6, txt_ngaycl.getText());
                st.setString(7, txt_dien1.getText());
                st.setString(8, txt_ghichu.getText());
                st.executeUpdate();
                dtm.setRowCount(0);
                JOptionPane.showMessageDialog(this, "Update thành công");
                filltable();

                //f2->f1->f0
                sql = "Select *from nguoitiepxuc where macachli = ? ";
                st = con.prepareStatement(sql);
                st.setString(1, txt_macl.getText());
                ResultSet rs = st.executeQuery();
                List<tmpNTX> listTX = new ArrayList<>();
                while (rs.next()) {
                    tmpNTX tX = new tmpNTX();
                    tX.setMTX(rs.getString("MaTiepXuc"));
                    tX.setDien(rs.getString("Dien"));
                    listTX.add(tX);
                }

                for (tmpNTX dien : listTX) {
                    char tmp = dien.getDien().charAt(1);
                    int Inttmp = Integer.parseInt(String.valueOf(tmp));
                    if (Inttmp>0) {
                        String ntmp = "F"+(Inttmp-1);
                        dien.setDien(ntmp);
                    }

                } 

                for (tmpNTX tmp : listTX) {
                    sql = "update nguoitiepxuc set nguoitiepxuc.dien = ? where macachli = ? and MaTiepXuc = ?";
                    st = con.prepareStatement(sql);
                    st.setString(1, tmp.getDien());
                    st.setString(2, txt_macl.getText());
                    st.setString(3, tmp.getMTX());
                    st.executeUpdate();
                }
                for(int i = 0 ; i<listTX.size();i++){
                    listTX.remove(i);
                }

                filltable2(Integer.parseInt(txt_macl.getText()));
                con.close();
            }
           
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        filltable2(index);
    }//GEN-LAST:event_btn_sua1ActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_exitActionPerformed

    private void txt_timKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKeyReleased
        // TODO add your handling code here:
        String tim = txt_tim.getText();
        TableRowSorter tb = new TableRowSorter(dtm);
        tbl_ncl.setRowSorter(tb);
        tb.setRowFilter(RowFilter.regexFilter(tim, 0));
        fillcontrol(index);
    }//GEN-LAST:event_txt_timKeyReleased

    private void cbx_timdienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_timdienItemStateChanged
        // TODO add your handling code here:
        String boloc = cbx_timdien.getSelectedItem().toString();
        locdata(boloc);
    }//GEN-LAST:event_cbx_timdienItemStateChanged

    private void cbx_gtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_gtItemStateChanged
        // TODO add your handling code here:
        String boloc1 = cbx_gt.getSelectedItem().toString();
        locdata(boloc1);
    }//GEN-LAST:event_cbx_gtItemStateChanged

    private void txt_tim3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tim3KeyReleased
        // TODO add your handling code here:
        String tim = txt_tim3.getText();
        TableRowSorter tb = new TableRowSorter(dtm1);
        tbl_tx.setRowSorter(tb);
        tb.setRowFilter(RowFilter.regexFilter(tim, 0));
        fillcontrol1(index);
    }//GEN-LAST:event_txt_tim3KeyReleased

    private void cbx_timdien3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_timdien3ItemStateChanged
        // TODO add your handling code here:
        String boloc = cbx_timdien3.getSelectedItem().toString();
        locdataTX(boloc);
    }//GEN-LAST:event_cbx_timdien3ItemStateChanged

    private void txt_dien2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dien2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dien2ActionPerformed

    private void txt_dien3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dien3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dien3ActionPerformed

    private void tbl_txMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_txMouseClicked
        // TODO add your handling code here:
        index = tbl_tx.getSelectedRow();
        fillcontrol1(index);
    }//GEN-LAST:event_tbl_txMouseClicked

    private void tbl_tx1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tx1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_tx1MouseClicked

    private void btn_savetxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_savetxActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "insert into nguoitiepxuc(matiepxuc,macachli,ten,gioitinh,ngsinh,diachi,dien) values(?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_matx.getText());
            st.setString(2, txt_macl1.getText());
            st.setString(3, txt_hotentx.getText());
            boolean gt;
            if (rdo_namtx.isSelected() == true) {
                gt = true;
            } else {
                gt = false;
            }
            st.setBoolean(4, gt);
            st.setString(5, txt_ngaysinhtx.getText());
            st.setString(6, txt_diachitx.getText());
            st.setString(7, txt_dien2.getText());
            st.execute();
            JOptionPane.showMessageDialog(this, "Nhập thành công");
            filltable1();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Nhập thất bại");
        }
    }//GEN-LAST:event_btn_savetxActionPerformed

    private void cbx_timdien3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_timdien3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_timdien3ActionPerformed

    private void cbx_gt3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_gt3ItemStateChanged
        // TODO add your handling code here:;
        String boloc3 = cbx_gt3.getSelectedItem().toString();
        locdataTX(boloc3);
    }//GEN-LAST:event_cbx_gt3ItemStateChanged

    private void btnExit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExit3ActionPerformed

    private void btnSua3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua3ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "Update nguoitiepxuc set macachli = ?,ten =?,gioitinh = ?,"
                    + "ngsinh = ? ,diachi = ?,Dien = ? where matiepxuc =?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(7, txt_matx.getText());
            st.setString(1, txt_macl1.getText());
            st.setString(2, txt_hotentx.getText());
            boolean gt;
            if (rdonam.isSelected()) {
                gt = true;
            } else {
                gt = false;
            }
            st.setBoolean(3, gt);
            st.setString(4, txt_ngaysinhtx.getText());
            st.setString(5, txt_diachitx.getText());
            st.setString(6, txt_dien2.getText());
            st.executeUpdate();
            dtm.setRowCount(0);
            JOptionPane.showMessageDialog(this, "Update thành công");
            filltable();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        filltable1();
    }//GEN-LAST:event_btnSua3ActionPerformed

    private void tbl_benhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_benhMouseClicked
        // TODO add your handling code here:
        index = tbl_benh.getSelectedRow();
        fillcontrol3(index);
    }//GEN-LAST:event_tbl_benhMouseClicked

    private void txt_timBenhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timBenhKeyReleased
        // TODO add your handling code here:
        String tim = txt_timBenh.getText();
        TableRowSorter tb = new TableRowSorter(dtm3);
        tbl_benh.setRowSorter(tb);
        tb.setRowFilter(RowFilter.regexFilter(tim, 0));
        fillcontrol3(index);
    }//GEN-LAST:event_txt_timBenhKeyReleased

    private void cbx_timdienBenhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_timdienBenhItemStateChanged
        // TODO add your handling code here:
        String boloc = cbx_timdienBenh.getSelectedItem().toString();
        locdataBenh(boloc);
    }//GEN-LAST:event_cbx_timdienBenhItemStateChanged

    private void btnThemBenhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemBenhActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "insert into Benh(macachli,ngay,dien,bieuhien,ghichu) values(?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_macl3.getText());
            st.setString(2, txt_ngaycl3.getText());
            st.setString(3, txt_dien3.getText());
            st.setString(4, txt_bieuhien3.getText());
            st.setString(5, txt_ghichu3.getText());
            st.execute();
            JOptionPane.showMessageDialog(this, "Nhập thành công");
            filltable3();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Nhập thất bại");
        }
    }//GEN-LAST:event_btnThemBenhActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void btnSuaBenhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaBenhActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "Update Benh set ngay =?,dien = ?,"
                    + "bieuhien = ? ,ghichu = ? where macachli = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(5, txt_macl3.getText());
            st.setString(1, txt_ngaycl3.getText());
            st.setString(2, txt_dien3.getText());
            st.setString(3, txt_bieuhien3.getText());
            st.setString(4, txt_ghichu3.getText());
            st.executeUpdate();
            dtm.setRowCount(0);
            JOptionPane.showMessageDialog(this, "Update thành công");
            filltable3();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        filltable3();
    }//GEN-LAST:event_btnSuaBenhActionPerformed

    private void tbl_nhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nhanvienMouseClicked
        // TODO add your handling code here:
        index = tbl_nhanvien.getSelectedRow();
        fillcontrolNhanVien(index);
        try {
            fillImageNvien(index);
        } catch (SQLException ex) {
            Logger.getLogger(duan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(duan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbl_nhanvienMouseClicked

    private void txt_timNvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timNvKeyReleased
        // TODO add your handling code here:
        String tim = txt_timNv.getText();
        TableRowSorter tb = new TableRowSorter(dtm4);
        tbl_nhanvien.setRowSorter(tb);
        tb.setRowFilter(RowFilter.regexFilter(tim, 0));
        fillcontrolNhanVien(index);
    }//GEN-LAST:event_txt_timNvKeyReleased

    private void btn_savetx1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_savetx1ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "insert into NhanVien(MaUser,Ten,ngaysinh,gioitinh,sdt,roler) values(?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_manvien.getText());
            st.setString(2, txt_tenNvien.getText());
            st.setString(3, txt_ngsinhNv.getText());
            boolean gt;
            if (rdonamNv.isSelected() == true) {
                gt = true;
            } else {
                gt = false;
            }
            st.setBoolean(4, gt);
            st.setString(5, txt_sdtNv.getText());
            st.setString(6, txt_roleNvien.getText());
            st.execute();
            JOptionPane.showMessageDialog(this, "Nhập thành công");
            filltableNhanVien();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Nhập thất bại");
        }
    }//GEN-LAST:event_btn_savetx1ActionPerformed

    private void btnXoa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa4ActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(this, "Do you want to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (ret != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "delete from nhanvien where MaUser = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_manvien.getText());
            st.execute();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            con.close();
            filltableNhanVien();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnXoa4ActionPerformed

    private void btnSua4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua4ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "Update nhanvien set ten = ?,ngaysinh =?,gioitinh = ?,"
                    + "sdt = ? ,roler = ? where mauser =?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(6, txt_manvien.getText());
            st.setString(1, txt_tenNvien.getText());
            st.setString(2, txt_ngsinhNv.getText());
            boolean gt;
            if (rdonamNv.isSelected()) {
                gt = true;
            } else {
                gt = false;
            }
            st.setBoolean(3, gt);
            st.setString(4, txt_sdtNv.getText());
            st.setString(5, txt_roleNvien.getText());
            st.executeUpdate();
            dtm4.setRowCount(0);
            JOptionPane.showMessageDialog(this, "Update thành công");
            filltableNhanVien();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        filltableNhanVien();
    }//GEN-LAST:event_btnSua4ActionPerformed

    private void btnExit4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExit4ActionPerformed

    private void cbx_gtNvItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_gtNvItemStateChanged
        // TODO add your handling code here:
        String boloc = cbx_gtNv.getSelectedItem().toString();
        locdataNv(boloc);
    }//GEN-LAST:event_cbx_gtNvItemStateChanged
    public void locdata(String boloc) {
        TableRowSorter<DefaultTableModel> sx = new TableRowSorter<DefaultTableModel>(dtm);
        tbl_ncl.setRowSorter(sx);
        if (boloc != "None") {
            sx.setRowFilter(RowFilter.regexFilter(boloc));
        } else {
            tbl_ncl.setRowSorter(sx);
        }
    }
    public void locdataTX(String boloc3) {
        TableRowSorter<DefaultTableModel> sx3 = new TableRowSorter<DefaultTableModel>(dtm1);
        tbl_tx.setRowSorter(sx3);
        if (boloc3 != "None") {
            sx3.setRowFilter(RowFilter.regexFilter(boloc3));
        } else {
            tbl_tx.setRowSorter(sx3);
        }
    }
    
    public void locdataBenh(String boloc3) {
        TableRowSorter<DefaultTableModel> sx = new TableRowSorter<DefaultTableModel>(dtm3);
        tbl_benh.setRowSorter(sx);
        if (boloc3 != "None") {
            sx.setRowFilter(RowFilter.regexFilter(boloc3));
        } else {
            tbl_benh.setRowSorter(sx);
        }
    }
    public void locdataNv(String boloc3) {
        TableRowSorter<DefaultTableModel> sx = new TableRowSorter<DefaultTableModel>(dtm4);
        tbl_nhanvien.setRowSorter(sx);
        if (boloc3 != "None") {
            sx.setRowFilter(RowFilter.regexFilter(boloc3));
        } else {
            tbl_nhanvien.setRowSorter(sx);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
       
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new duan().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(duan.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(duan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit3;
    private javax.swing.JButton btnExit4;
    private javax.swing.JButton btnSua3;
    private javax.swing.JButton btnSua4;
    private javax.swing.JButton btnSuaBenh;
    private javax.swing.JButton btnThemBenh;
    private javax.swing.JButton btnXoa3;
    private javax.swing.JButton btnXoa4;
    private javax.swing.JButton btnXoaBenh;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_savetx;
    private javax.swing.JButton btn_savetx1;
    private javax.swing.JButton btn_sua1;
    private javax.swing.JButton btn_them1;
    private javax.swing.JButton btn_xoa1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbx_gt;
    private javax.swing.JComboBox<String> cbx_gt3;
    private javax.swing.JComboBox<String> cbx_gtNv;
    private javax.swing.JComboBox<String> cbx_timdien;
    private javax.swing.JComboBox<String> cbx_timdien3;
    private javax.swing.JComboBox<String> cbx_timdienBenh;
    private javax.swing.JButton jButton19;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblImgNv;
    private javax.swing.JRadioButton rdo_namtx;
    private javax.swing.JRadioButton rdo_nutx;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonamNv;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JRadioButton rdonuNv;
    private javax.swing.JTable tbl_benh;
    private javax.swing.JTable tbl_ncl;
    private javax.swing.JTable tbl_nhanvien;
    private javax.swing.JTable tbl_tx;
    private javax.swing.JTable tbl_tx1;
    private javax.swing.JTextArea txt_bieuhien3;
    private javax.swing.JTextArea txt_diachi;
    private javax.swing.JTextArea txt_diachitx;
    private javax.swing.JTextField txt_dien1;
    private javax.swing.JTextField txt_dien2;
    private javax.swing.JTextField txt_dien3;
    private javax.swing.JTextArea txt_ghichu;
    private javax.swing.JTextArea txt_ghichu3;
    private javax.swing.JTextField txt_hoten;
    private javax.swing.JTextField txt_hotentx;
    private javax.swing.JTextField txt_macl;
    private javax.swing.JTextField txt_macl1;
    private javax.swing.JTextField txt_macl3;
    private javax.swing.JTextField txt_manvien;
    private javax.swing.JTextField txt_matx;
    private javax.swing.JTextField txt_ngaycl;
    private javax.swing.JTextField txt_ngaycl3;
    private javax.swing.JTextField txt_ngaysinh;
    private javax.swing.JTextField txt_ngaysinhtx;
    private javax.swing.JTextField txt_ngsinhNv;
    private javax.swing.JTextField txt_roleNvien;
    private javax.swing.JTextField txt_sdt1;
    private javax.swing.JTextField txt_sdtNv;
    private javax.swing.JTextField txt_tenNvien;
    private javax.swing.JTextField txt_tim;
    private javax.swing.JTextField txt_tim3;
    private javax.swing.JTextField txt_timBenh;
    private javax.swing.JTextField txt_timNv;
    // End of variables declaration//GEN-END:variables
}
