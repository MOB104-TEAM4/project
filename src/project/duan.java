/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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
    String url = "jdbc:sqlserver://localhost:1433;database=project1;";
    DefaultTableModel dtm;
    DefaultTableModel dtm1;
    DefaultTableModel dtm2;
    DefaultTableModel dtm3;
    DefaultTableModel dtm4;
    List<tmpImage> ImageNvList = new ArrayList<>();
    String path = null;
    byte [] dataImg = null;
    String username = null;

    /**
     * Creates new form duan
     */
    public duan(String role, String userString) throws ClassNotFoundException, SQLException {
        initComponents();
        filltable();
        filltable2();
        filltable3();
        filltableNhanVien();
        getImgNhanvien();
        if (role.equals("admin")) {
            jPanel7.setVisible(true);
        } else {
            jPanel7.setVisible(false);
        }
        username = userString;
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
        txt_tiepxuc.setText(tbl_ncl.getValueAt(index, 8).toString());
       
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
                data.add(rs.getBoolean("GioiTinh") == true ? "Nam" : "N???");
                data.add(rs.getString("ngsinh"));
                data.add(rs.getString("Sdt"));
                data.add(rs.getString("DiaChi"));
                data.add(rs.getString("NgayCachLi"));
                data.add(rs.getString("Dien"));
                data.add(rs.getString("Tiepxuc"));
                // Th??m m???t d??ng v??o table model
                dtm.addRow(data);

            }
            tbl_ncl.setModel(dtm);
            dtm.fireTableDataChanged();
            rs.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    public void filltable2() {
        String tmp = (String) txt_macl.getText();
        System.out.println(tmp);
        dtm2 = (DefaultTableModel) tbl_tx1.getModel();
        dtm2.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select *from nguoicachli ";;
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            cl.clear();

            while (rs.next()) {
                String tmpString = rs.getString("tiepxuc");
                if (tmpString.equals(tmp)) {
                    Vector data = new Vector();
                    data.add(rs.getString("Macachli"));
                //    data.add(rs.getString("tiepxuc"));
                    data.add(rs.getString("Ten"));
                    data.add(rs.getBoolean("GioiTinh") == true ? "Nam" : "N???");
                    data.add(rs.getString("ngsinh"));
                    data.add(rs.getString("DiaChi"));
                    data.add(rs.getString("Dien"));
                    // Th??m m???t d??ng v??o table model
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
                // Th??m m???t d??ng v??o table model
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
                data.add(rs.getBoolean("GioiTinh") == true ? "Nam" : "N???");
                data.add(rs.getString("sdt"));
                data.add(rs.getString("roler"));
                data.add(rs.getString("pass"));
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
        ImageNvList.clear();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(url, user, pass);
        String sqlImg = "select * from nhanvien";
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
    }
    
    public void fillImageNvien (int index) throws SQLException, IOException{
        try {
            lblImgNv.setEnabled(true);
            tmpImage tmpImg = ImageNvList.get(index);
            byte [] data = tmpImg.getImg();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(data).getImage().getScaledInstance(lblImgNv.getWidth(), lblImgNv.getHeight() , Image.SCALE_SMOOTH));
            lblImgNv.setIcon(imageIcon);
        } catch (Exception e) {
            lblImgNv.setEnabled(false);
        }
        
    }
    
    public void updateImg(String path) throws SQLException, IOException{
        FileInputStream fis = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "Update nhanvien set images = ? where MaUser = ? ";
            PreparedStatement st = con.prepareStatement(sql);
            st.setBytes(1,dataImg);
            st.setString(2, txt_manvien.getText());
            st.executeUpdate();
            con.commit();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        fillImageNvien(index);
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
        txt_tiepxuc = new javax.swing.JTextField();
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
        btnEditImg = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel9.setFont(new java.awt.Font("Sitka Heading", 3, 24)); // NOI18N
        jLabel9.setText("Th??ng tin ng?????i c??ch ly");

        buttonGroup1.add(rdonam);
        rdonam.setText("Nam");

        buttonGroup1.add(rdonu);
        rdonu.setText("N???");

        txt_diachi.setColumns(20);
        txt_diachi.setRows(5);
        jScrollPane3.setViewportView(txt_diachi);

        jLabel10.setText("?????a ch???");

        jLabel11.setText("Ng??y c??ch ly");

        jLabel12.setText("Ng??y sinh");

        jLabel13.setText("Di???n");

        jLabel14.setText("Gi???i t??nh");

        jLabel15.setText("H??? v?? t??n");

        jLabel16.setText("M?? c??ch ly");

        tbl_ncl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? c??ch ly", "H??? v?? t??n", "Gi???i t??nh", "Ng??y sinh", "S??? ??i???n tho???i", "?????a ch???", "Ng??y c??ch ly", "Di???n", "Ghi ch??"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_ncl.setName(""); // NOI18N
        tbl_ncl.setRowHeight(40);
        tbl_ncl.getTableHeader().setReorderingAllowed(false);
        tbl_ncl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nclMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_ncl);
        if (tbl_ncl.getColumnModel().getColumnCount() > 0) {
            tbl_ncl.getColumnModel().getColumn(0).setMinWidth(80);
            tbl_ncl.getColumnModel().getColumn(0).setMaxWidth(80);
            tbl_ncl.getColumnModel().getColumn(1).setMinWidth(150);
            tbl_ncl.getColumnModel().getColumn(1).setMaxWidth(200);
            tbl_ncl.getColumnModel().getColumn(2).setMinWidth(80);
            tbl_ncl.getColumnModel().getColumn(2).setMaxWidth(80);
            tbl_ncl.getColumnModel().getColumn(3).setMinWidth(100);
            tbl_ncl.getColumnModel().getColumn(3).setMaxWidth(100);
            tbl_ncl.getColumnModel().getColumn(4).setMinWidth(150);
            tbl_ncl.getColumnModel().getColumn(4).setMaxWidth(150);
            tbl_ncl.getColumnModel().getColumn(5).setMinWidth(300);
            tbl_ncl.getColumnModel().getColumn(5).setMaxWidth(300);
            tbl_ncl.getColumnModel().getColumn(6).setMinWidth(100);
            tbl_ncl.getColumnModel().getColumn(6).setMaxWidth(100);
            tbl_ncl.getColumnModel().getColumn(7).setMinWidth(60);
            tbl_ncl.getColumnModel().getColumn(7).setMaxWidth(60);
            tbl_ncl.getColumnModel().getColumn(8).setMinWidth(400);
            tbl_ncl.getColumnModel().getColumn(8).setMaxWidth(400);
        }

        jLabel17.setText("Tiep xuc");

        btn_them1.setText("Th??m");
        btn_them1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them1ActionPerformed(evt);
            }
        });

        btn_xoa1.setText("X??a");
        btn_xoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoa1ActionPerformed(evt);
            }
        });

        btn_sua1.setText("S???a");
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

        jLabel25.setText("S??t");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel26.setText("T??m m?? c??ch ly");

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

        jLabel27.setText("T??m theo di???n");

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

        jLabel28.setText("L???c gi???i t??nh");

        cbx_gt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "N???", "None", "" }));
        cbx_gt.setSelectedItem("None");
        cbx_gt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_gtItemStateChanged(evt);
            }
        });

        tbl_tx1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "M?? c??ch ly", "H??? t??n", "Gi???i t??nh", "Ng??y sinh", "?????a ch??? ", "Di???n"
            }
        ));
        tbl_tx1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tx1MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbl_tx1);

        txt_tiepxuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tiepxucActionPerformed(evt);
            }
        });

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
                                    .addComponent(txt_tiepxuc, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_ngaycl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_dien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(txt_tiepxuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cbx_gt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them1)
                    .addComponent(btn_xoa1)
                    .addComponent(btn_sua1)
                    .addComponent(btn_exit))
                .addGap(13, 13, 13))
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

        jTabbedPane1.addTab("Th??ng tin ng?????i c??ch ly", jPanel2);

        tbl_benh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? c??ch ly", "Ng??y", "Di???n", "Bi???u hi???n", "Ghi ch??"
            }
        ));
        tbl_benh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_benhMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_benh);

        jLabel18.setText("M?? c??ch ly");

        jLabel19.setText("Ng??y c??ch ly");

        jLabel20.setText("Ghi ch??");

        txt_ghichu3.setColumns(20);
        txt_ghichu3.setRows(5);
        jScrollPane7.setViewportView(txt_ghichu3);

        jLabel21.setText("Di???n");

        jLabel22.setText("Bi???u hi???n");

        txt_bieuhien3.setColumns(20);
        txt_bieuhien3.setRows(5);
        jScrollPane8.setViewportView(txt_bieuhien3);

        jLabel23.setFont(new java.awt.Font("Sitka Heading", 3, 24)); // NOI18N
        jLabel23.setText("Th??ng tin bi???u hi???n b???nh");

        btnThemBenh.setText("Th??m");
        btnThemBenh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemBenhActionPerformed(evt);
            }
        });

        btnXoaBenh.setText("X??a");
        btnXoaBenh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaBenhActionPerformed(evt);
            }
        });

        btnSuaBenh.setText("S???a");
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

        jLabel30.setText("T??m m?? c??ch ly");

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

        jLabel34.setText("T??m theo di???n");

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

        jTabbedPane1.addTab("Th??ng tin bi???u hi???n b???nh", jPanel3);

        jPanel7.setBackground(new java.awt.Color(51, 255, 51));

        jLabel33.setFont(new java.awt.Font("Sitka Heading", 3, 24)); // NOI18N
        jLabel33.setText("Th??ng tin nh??n vi??n");

        jLabel35.setText("M?? nh??n vi??n");

        jLabel36.setText("H??? t??n");

        jLabel37.setText("Ng??y sinh");

        jLabel39.setText("S??? ??i???n tho???i");

        jLabel40.setText("Roler");

        tbl_nhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? nh??n vi??n", "H??? t??n", "Ng??y sinh", "Gi???i t??nh", "S??? ??i???n tho???i", "Quy???n", "M???t kh???u"
            }
        ));
        tbl_nhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nhanvienMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbl_nhanvien);

        jLabel41.setText("Gi???i t??nh");

        buttonGroup1.add(rdonamNv);
        rdonamNv.setText("Nam");

        buttonGroup1.add(rdonuNv);
        rdonuNv.setText("N???");

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel38.setText("T??m m?? nh??n vi??n");

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
                .addContainerGap(150, Short.MAX_VALUE))
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

        btn_savetx1.setText("Th??m");
        btn_savetx1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_savetx1ActionPerformed(evt);
            }
        });

        btnXoa4.setText("X??a");
        btnXoa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa4ActionPerformed(evt);
            }
        });

        btnSua4.setText("S???a");
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

        jLabel42.setText("L???c gi???i t??nh");

        cbx_gtNv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "N???", "None" }));
        cbx_gtNv.setSelectedItem("None");
        cbx_gtNv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_gtNvItemStateChanged(evt);
            }
        });

        btnEditImg.setText("choose");
        btnEditImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditImgActionPerformed(evt);
            }
        });

        jButton2.setText("reset pass");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addGap(9, 9, 9)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(lblImgNv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_roleNvien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1383, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditImg)
                                .addGap(0, 0, Short.MAX_VALUE))))
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
                                .addComponent(cbx_gtNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditImg))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblImgNv, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(cbx_gtNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_savetx1)
                    .addComponent(btnXoa4)
                    .addComponent(btnSua4)
                    .addComponent(btnExit4))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(107, 107, 107))
        );

        jLabel43.setText("chuc nang nay chi cho admin");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(384, 384, 384)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Qu???n l?? nh??n vi??n", jPanel11);

        jLabel24.setFont(new java.awt.Font("Sitka Heading", 2, 48)); // NOI18N
        jLabel24.setText("B??? ph???n qu???n l?? th??ng tin ng?????i nhi???m b???nh v?? c??ch ly coivd-19");

        jButton1.setText("Doi pass");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addGap(184, 184, 184))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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
            JOptionPane.showMessageDialog(this, "X??a th??nh c??ng");
            con.close();
            filltable();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "X??a th???t b???i");
        }
    }//GEN-LAST:event_btn_xoa1ActionPerformed

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
            JOptionPane.showMessageDialog(this, "X??a th??nh c??ng");
            con.close();
            filltable3();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "X??a th???t b???i");
        }
    }//GEN-LAST:event_btnXoaBenhActionPerformed

    private void tbl_nclMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nclMouseClicked
        // TODO add your handling code here:
        index = tbl_ncl.getSelectedRow();
        fillcontrol(index);
        filltable2();
    }//GEN-LAST:event_tbl_nclMouseClicked

    private void txt_dien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dien1ActionPerformed

    private void btn_them1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them1ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "insert into NguoiCachLi(  MaCachLi ,Ten,GioiTinh,ngsinh,Sdt,DiaChi,NgayCachLi,Dien,tiepxuc) values(?,?,?,?,?,?,?,?,?)";
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
            st.setString(9, txt_tiepxuc.getText());
            st.execute();
            JOptionPane.showMessageDialog(this, "Nh???p th??nh c??ng");
            filltable();

        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Nh???p th???t b???i");
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
                        + "DiaChi = ? ,NgayCachLi = ?,Dien = ?,tiepxuc = ?"
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
                st.setString(8, txt_tiepxuc.getText());
                st.executeUpdate();
                dtm.setRowCount(0);
                JOptionPane.showMessageDialog(this, "Update th??nh c??ng");
                filltable();

                //f2->f1->f0
                sql = "Select *from nguoicachli where tiepxuc = ? ";
                st = con.prepareStatement(sql);
                st.setString(1, txt_macl.getText());
                ResultSet rs = st.executeQuery();
                List<tmpNTX> listTX = new ArrayList<>();
                while (rs.next()) {
                    tmpNTX tX = new tmpNTX();
                    tX.setMTX(rs.getString("macachli"));
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
                    sql = "update nguoicachli set dien = ? where tiepxuc = ? and macachli = ?";
                    st = con.prepareStatement(sql);
                    st.setString(1, tmp.getDien());
                    st.setString(2, txt_macl.getText());
                    st.setString(3, tmp.getMTX());
                    st.executeUpdate();
                }
                for(int i = 0 ; i<listTX.size();i++){
                    listTX.remove(i);
                }
                filltable();
                filltable2();
                con.close();
            }
           
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        filltable2();
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

    private void txt_dien3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dien3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dien3ActionPerformed

    private void tbl_tx1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tx1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_tx1MouseClicked

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
            JOptionPane.showMessageDialog(this, "Nh???p th??nh c??ng");
            filltable3();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Nh???p th???t b???i");
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
            JOptionPane.showMessageDialog(this, "Update th??nh c??ng");
            filltable3();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        filltable3();
    }//GEN-LAST:event_btnSuaBenhActionPerformed

    private void tbl_nhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nhanvienMouseClicked
        // TODO add your handling code here:
        lblImgNv.setIcon(null);
        index = tbl_nhanvien.getSelectedRow();
        fillcontrolNhanVien(index);
        try {
            fillImageNvien(index);
        } catch (SQLException ex) {
            lblImgNv.setEnabled(false);
        } catch (IOException ex) {
            lblImgNv.setEnabled(false);
        }
    }//GEN-LAST:event_tbl_nhanvienMouseClicked

    private void txt_timNvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timNvKeyReleased
        // TODO add your handling code here:
        try {
            String tim = txt_timNv.getText();
            TableRowSorter tb = new TableRowSorter(dtm4);
            tbl_nhanvien.setRowSorter(tb);
            tb.setRowFilter(RowFilter.regexFilter(tim, 0));
            fillcontrolNhanVien(index);
        } catch (Exception e) {
            System.err.print(e);
            System.err.print("Not found");
        }
        
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
            JOptionPane.showMessageDialog(this, "Nh???p th??nh c??ng");
            filltableNhanVien();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Nh???p th???t b???i");
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
            JOptionPane.showMessageDialog(this, "X??a th??nh c??ng");
            con.close();
            filltableNhanVien();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "X??a th???t b???i");
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
            JOptionPane.showMessageDialog(this, "Update th??nh c??ng");
            filltableNhanVien();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        filltableNhanVien();
        try {
            updateImg(path);
            getImgNhanvien();
            fillImageNvien(index);
        } catch (SQLException ex) {
            Logger.getLogger(duan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(duan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(duan.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void btnEditImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditImgActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        path = f.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(lblImgNv.getWidth(), lblImgNv.getHeight() , Image.SCALE_SMOOTH));
        lblImgNv.setIcon(imageIcon);
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for(int readNum;(readNum=fis.read(buf))!=-1;){
                bos.write(buf,0,readNum);
            }
            dataImg = bos.toByteArray();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEditImgActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "Update nhanvien set pass = ? where mauser =?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "pass123");
            st.setString(2, txt_manvien.getText());
            st.executeUpdate();
            JOptionPane.showMessageDialog(this, "reset th??nh c??ng");
            filltableNhanVien();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JTextField password = new JPasswordField();
        JTextField repass = new JPasswordField();
        Object[] message = {
            "Password:", password,
            "Re-Paswrod", repass
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Doi pass.", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (password.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "??i???n ?????y ????? th??nng tin.");
            } else if (repass.getText().equals(password.getText())){
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(url, user, pass);
                    String sql = "Update nhanvien set pass = ? where mauser =?";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.setString(1, password.getText());
                    st.setString(2, username);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Doi pass th??nh c??ng.");
                    filltableNhanVien();
                    con.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "M???t kh???u kh??ng kh???p. ");
            }
        } else {
            System.out.println("Login canceled");
            System.exit(0);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_tiepxucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tiepxucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tiepxucActionPerformed
    public void locdata(String boloc) {
        TableRowSorter<DefaultTableModel> sx = new TableRowSorter<DefaultTableModel>(dtm);
        tbl_ncl.setRowSorter(sx);
        if (boloc != "None") {
            sx.setRowFilter(RowFilter.regexFilter(boloc));
        } else {
            tbl_ncl.setRowSorter(sx);
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
                    new duan("admin", null).setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(duan.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(duan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditImg;
    private javax.swing.JButton btnExit4;
    private javax.swing.JButton btnSua4;
    private javax.swing.JButton btnSuaBenh;
    private javax.swing.JButton btnThemBenh;
    private javax.swing.JButton btnXoa4;
    private javax.swing.JButton btnXoaBenh;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_savetx1;
    private javax.swing.JButton btn_sua1;
    private javax.swing.JButton btn_them1;
    private javax.swing.JButton btn_xoa1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbx_gt;
    private javax.swing.JComboBox<String> cbx_gtNv;
    private javax.swing.JComboBox<String> cbx_timdien;
    private javax.swing.JComboBox<String> cbx_timdienBenh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblImgNv;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonamNv;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JRadioButton rdonuNv;
    private javax.swing.JTable tbl_benh;
    private javax.swing.JTable tbl_ncl;
    private javax.swing.JTable tbl_nhanvien;
    private javax.swing.JTable tbl_tx1;
    private javax.swing.JTextArea txt_bieuhien3;
    private javax.swing.JTextArea txt_diachi;
    private javax.swing.JTextField txt_dien1;
    private javax.swing.JTextField txt_dien3;
    private javax.swing.JTextArea txt_ghichu3;
    private javax.swing.JTextField txt_hoten;
    private javax.swing.JTextField txt_macl;
    private javax.swing.JTextField txt_macl3;
    private javax.swing.JTextField txt_manvien;
    private javax.swing.JTextField txt_ngaycl;
    private javax.swing.JTextField txt_ngaycl3;
    private javax.swing.JTextField txt_ngaysinh;
    private javax.swing.JTextField txt_ngsinhNv;
    private javax.swing.JTextField txt_roleNvien;
    private javax.swing.JTextField txt_sdt1;
    private javax.swing.JTextField txt_sdtNv;
    private javax.swing.JTextField txt_tenNvien;
    private javax.swing.JTextField txt_tiepxuc;
    private javax.swing.JTextField txt_tim;
    private javax.swing.JTextField txt_timBenh;
    private javax.swing.JTextField txt_timNv;
    // End of variables declaration//GEN-END:variables
}
