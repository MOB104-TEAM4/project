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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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

    /**
     * Creates new form duan
     */
    public duan() {
        initComponents();
        filltable();
        filltable1();
        filltable2(index);
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
            rdonam.setSelected(true);
            rdonu.setSelected(false);
        } else {
            rdonu.setSelected(true);
            rdonam.setSelected(false);
        }
        txt_ngaysinhtx.setText(tbl_tx.getValueAt(index, 4).toString());

        txt_diachitx.setText(tbl_tx.getValueAt(index, 5).toString());

        txt_dien2.setText(tbl_tx.getValueAt(index, 6).toString());

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
        System.out.println(index);
        String tmp = (String) tbl_ncl.getModel().getValueAt(index, 0);
        dtm1 = (DefaultTableModel) tbl_tx1.getModel();
        dtm1.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select *from NguoiTiepXuc ";;
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            cl.clear();

            while (rs.next()) {
                String tmpString = rs.getString("MaCachLi");
                System.out.println(tmpString);
                System.out.println(tmp);
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
                    dtm1.addRow(data);
                }
            }
            tbl_tx1.setModel(dtm1);
            dtm1.fireTableDataChanged();
            rs.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_matx = new javax.swing.JTextField();
        txt_hotentx = new javax.swing.JTextField();
        txt_ngaysinhtx = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_tx = new javax.swing.JTable();
        btn_savetx = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
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
        cbx_timdien1 = new javax.swing.JComboBox<>();
        txt_dien2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_benh = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        txt_macl3 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_ngaycl3 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txt_diachi3 = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txt_bieuhien3 = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        txt_dien3 = new javax.swing.JTextField();
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

        cbx_gt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam ", "Nữ", "None", " " }));
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
                                        .addComponent(txt_sdt1)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28)
                            .addComponent(btn_them1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_xoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_sua1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbx_gt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        jButton3.setText("Exit");

        jButton2.setText("Sửa");

        jButton1.setText("Xóa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        cbx_timdien1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F0", "F1", "F2", "F3", "F4", "F5", "None", " " }));
        cbx_timdien1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_timdien1ItemStateChanged(evt);
            }
        });
        cbx_timdien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_timdien1ActionPerformed(evt);
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
                .addComponent(cbx_timdien1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cbx_timdien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_dien2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dien2ActionPerformed(evt);
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
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(96, 96, 96)
                                        .addComponent(txt_matx, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
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
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
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
                        .addComponent(btn_savetx, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(97, 97, 97)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(btn_savetx)
                    .addComponent(jButton3))
                .addGap(0, 113, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );

        jTabbedPane1.addTab("Thông tin người tiếp xúc", jPanel1);

        tbl_benh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tbl_benh);

        jLabel18.setText("Mã cách ly");

        jLabel19.setText("Ngày cách ly");

        jLabel20.setText("Địa chỉ");

        txt_diachi3.setColumns(20);
        txt_diachi3.setRows(5);
        jScrollPane7.setViewportView(txt_diachi3);

        jLabel21.setText("Diện");

        jLabel22.setText("Biểu hiện");

        txt_bieuhien3.setColumns(20);
        txt_bieuhien3.setRows(5);
        jScrollPane8.setViewportView(txt_bieuhien3);

        jLabel23.setFont(new java.awt.Font("Sitka Heading", 3, 24)); // NOI18N
        jLabel23.setText("Thông tin biểu hiện bệnh");

        jButton15.setText("Tìm theo mã cách ly");

        jButton16.setText("Thêm");

        jButton17.setText("Xóa");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("Sửa");

        jButton19.setText("Exit");

        jButton20.setText("Tìm theo diện");

        jButton21.setText("Tìm theo ngày");

        txt_dien3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dien3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_macl3)
                                        .addComponent(txt_ngaycl3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_dien3))))
                            .addComponent(jLabel22)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1324, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton17)
                            .addComponent(jButton16)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton18)
                            .addComponent(jButton19))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin biểu hiện bệnh", jPanel3);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void tbl_nclMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nclMouseClicked
        // TODO add your handling code here:
        index = tbl_ncl.getSelectedRow();
        System.out.println(index);
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
    }//GEN-LAST:event_txt_tim3KeyReleased

    private void cbx_timdien1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_timdien1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_timdien1ItemStateChanged

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

    private void cbx_timdien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_timdien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_timdien1ActionPerformed
    public void locdata(String boloc) {
        TableRowSorter<DefaultTableModel> sx = new TableRowSorter<DefaultTableModel>(dtm);
        tbl_ncl.setRowSorter(sx);
        if (boloc != "None") {
            sx.setRowFilter(RowFilter.regexFilter(boloc));
        } else {
            tbl_ncl.setRowSorter(sx);
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
                new duan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_savetx;
    private javax.swing.JButton btn_sua1;
    private javax.swing.JButton btn_them1;
    private javax.swing.JButton btn_xoa1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbx_gt;
    private javax.swing.JComboBox<String> cbx_timdien;
    private javax.swing.JComboBox<String> cbx_timdien1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdo_namtx;
    private javax.swing.JRadioButton rdo_nutx;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JTable tbl_benh;
    private javax.swing.JTable tbl_ncl;
    private javax.swing.JTable tbl_tx;
    private javax.swing.JTable tbl_tx1;
    private javax.swing.JTextArea txt_bieuhien3;
    private javax.swing.JTextArea txt_diachi;
    private javax.swing.JTextArea txt_diachi3;
    private javax.swing.JTextArea txt_diachitx;
    private javax.swing.JTextField txt_dien1;
    private javax.swing.JTextField txt_dien2;
    private javax.swing.JTextField txt_dien3;
    private javax.swing.JTextArea txt_ghichu;
    private javax.swing.JTextField txt_hoten;
    private javax.swing.JTextField txt_hotentx;
    private javax.swing.JTextField txt_macl;
    private javax.swing.JTextField txt_macl1;
    private javax.swing.JTextField txt_macl3;
    private javax.swing.JTextField txt_matx;
    private javax.swing.JTextField txt_ngaycl;
    private javax.swing.JTextField txt_ngaycl3;
    private javax.swing.JTextField txt_ngaysinh;
    private javax.swing.JTextField txt_ngaysinhtx;
    private javax.swing.JTextField txt_sdt1;
    private javax.swing.JTextField txt_tim;
    private javax.swing.JTextField txt_tim3;
    // End of variables declaration//GEN-END:variables
}
