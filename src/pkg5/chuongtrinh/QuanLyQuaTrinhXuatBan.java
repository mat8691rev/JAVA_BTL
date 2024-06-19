/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkg5.chuongtrinh;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static pkg5.chuongtrinh.Constant.pathGiaoTrinh;
import pkg5.chuongtrinh.lib.LoadUtil;
import pkg5.chuongtrinh.lib.WriteDataUtil;

/**
 *
 * @author Admind
 */
public class QuanLyQuaTrinhXuatBan extends javax.swing.JFrame {

    ArrayList<GiaoTrinh> dsgt = new ArrayList<>();

    /**
     * Creates new form QuanLyQuaTrinhXuatBan
     */
    public QuanLyQuaTrinhXuatBan() {
        initComponents();
        //ImageIcon icon = new ImageIcon("icon.png");
        //ResetBtn.setIcon(icon);
        //Reset.setIcon(icon);
    }
    
    private void loadFile() {
        dsgt = LoadUtil.loadGiaoTrinh(pathGiaoTrinh);
    }
    
    private void saveFile() {
//        FileOutputStream fo;
//        ObjectOutputStream out;
//        try {
//            fo = new FileOutputStream("GiaoTrinh.txt");
//            out = new ObjectOutputStream(fo);
//            out.writeObject(dsgt);
//            out.close();
//            fo.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        WriteDataUtil.writeGiaoTrinhData(dsgt);
    }

    public static boolean after(Date date1, Date date2) {
        int year1 = date1.getY();
        int month1 = date1.getM();
        int day1 = date1.getD();

        int year2 = date2.getY();
        int month2 = date2.getM();
        int day2 = date2.getD();

        if (year1 > year2) {
            return true;
        } else if (year1 == year2 && month1 > month2) {
            return true;
        } else if (year1 == year2 && month1 == month2 && day1 > day2) {
            return true;
        }
        return false;
    }
    private void resetTable() {
        loadFile();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        Object[] columnNames = {"Mã GT", "Tên GT", "Môn học", "Số tín chỉ", "Chủ Biên", "Các Tác Giả", "Thời gian bắt đầu", "Thời gian kết thúc", "Trạng Thái"};
        model.setColumnIdentifiers(columnNames);
        Object[][] rowData = new Object[dsgt.size()][9];
        for (int i = 0; i < dsgt.size(); i++) {
            rowData[i][0] = dsgt.get(i).getMaGT();
            rowData[i][1] = dsgt.get(i).getTenGT();
            rowData[i][2] = dsgt.get(i).getTenMon().getTenMon();
            rowData[i][3] = dsgt.get(i).getTenMon().getSoTC();
            rowData[i][4] = dsgt.get(i).getChuBien().getTenGV();
            rowData[i][5] = dsgt.get(i).getdsTG();
            rowData[i][6] = dsgt.get(i).getTgbd().toString();
            rowData[i][7] = dsgt.get(i).getTgkt().toString();
            
            ArrayList<BienSoan> dsBienSoan = dsgt.get(i).getBienSoan();
            Date latestDate = null;
            for (int j=0; j<dsBienSoan.size(); j++){
                Date date = new Date(dsBienSoan.get(j).getKetThuc());
                if (latestDate == null || after(date, latestDate)){
                    latestDate = new Date(date);
                }
            }
            boolean dangKy = dsgt.get(i).isDangKy();
            boolean bienSoan = !(dsBienSoan.isEmpty()) || dsgt.get(i).getTgkt().equals(latestDate);
            if (!dangKy) {
                rowData[i][8] = "Chưa đăng ký";
            } else if (!bienSoan) {
                rowData[i][8] = "Chưa biên soạn";
            } else if (dsgt.get(i).isXuatBan()) {
                rowData[i][8] = "Đã xuất bản";
            } else {
                rowData[i][8] = "Chờ xuất bản";
            }
        }
        model.setDataVector(rowData, columnNames);
        this.jTable1.setModel(model);
        this.add(new JScrollPane(table));
        this.pack();
        this.setVisible(true);
    }
    
    private void resetTextBox() {
        txtGT.setText("");
    }
    
    private void generateTable(ArrayList<GiaoTrinh> list) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        Object[] columnNames = {"Mã GT", "Tên GT", "Môn học", "Số tín chỉ", "Chủ Biên", "Các Tác Giả", "Thời gian bắt đầu", "Thời gian kết thúc", "Trạng Thái"};
        model.setColumnIdentifiers(columnNames);
        ArrayList<GiangVien> dsTacGia;
        String cacTacGia;
        Object[][] rowData = new Object[list.size()][9];
        for (int i = 0; i < list.size(); i++) {
            rowData[i][0] = list.get(i).getMaGT();
            rowData[i][1] = list.get(i).getTenGT();
            rowData[i][2] = list.get(i).getTenMon().getTenMon();
            rowData[i][3] = list.get(i).getTenMon().getSoTC();
            rowData[i][4] = list.get(i).getChuBien().getTenGV();
            rowData[i][5] = list.get(i).getdsTG();
            rowData[i][6] = list.get(i).getTgbd().toString();
            rowData[i][7] = list.get(i).getTgkt().toString();
            
            ArrayList<BienSoan> dsBienSoan = list.get(i).getBienSoan();
            Date latestDate = null;
            for (int j=0; j<dsBienSoan.size(); j++){
                Date date = new Date(dsBienSoan.get(j).getKetThuc());
                if (latestDate == null || after(date, latestDate)){
                    latestDate = new Date(date);
                }
            }
            boolean dangKy = list.get(i).isDangKy();
            boolean bienSoan = !(dsBienSoan.isEmpty()) || list.get(i).getTgkt().equals(latestDate);
            if (!dangKy) {
                rowData[i][8] = "Chưa đăng ký";
            } else if (!bienSoan) {
                rowData[i][8] = "Chưa biên soạn";
            } else if (list.get(i).isXuatBan()) {
                rowData[i][8] = "Đã xuất bản";
            } else {
                rowData[i][8] = "Chờ xuất bản";
            }
        }
        model.setDataVector(rowData, columnNames);
        this.jTable1.setModel(model);
        this.add(new JScrollPane(table));
        this.pack();
        this.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jDialog3 = new javax.swing.JDialog();
        jDialog4 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        xuatBan = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtGT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Op_GT = new javax.swing.JComboBox<>();
        TimKiem = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ QUÁ TRÌNH XUẤT BẢN");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã GT", "Tên GT", "Tên Môn", "Số TC", "Chủ Biên", "Các Tác Giả", "TG Bắt Đầu", "TG Kết Thúc", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
        }

        xuatBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        xuatBan.setText("Xuất Bản");
        xuatBan.setMinimumSize(new java.awt.Dimension(100, 30));
        xuatBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xuatBanMouseClicked(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Quay Lại");
        jButton3.setMaximumSize(new java.awt.Dimension(100, 30));
        jButton3.setMinimumSize(new java.awt.Dimension(100, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Reset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Reset.setText("Làm Mới");
        Reset.setInheritsPopupMenu(true);
        Reset.setMinimumSize(new java.awt.Dimension(100, 30));
        Reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResetMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tìm kiếm giáo trình:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tìm kiếm theo:");

        Op_GT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã giáo trình", "Tên giáo trình", "Môn học", "Số tín chỉ", "Chủ biên", "Các tác giả", "Năm bắt đầu", "Năm kết thúc" }));

        TimKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TimKiem.setText("Tìm kiếm");
        TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemTimKiemGT(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGT, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(Op_GT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(xuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(328, 328, 328)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(Op_GT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addComponent(xuatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(Reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(55, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable1AncestorAdded
        // TODO add your handling code here:
        resetTable();
    }//GEN-LAST:event_jTable1AncestorAdded

    private void xuatBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xuatBanMouseClicked
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn giáo trình !");
        } else {
            try {
                String maGT = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                for (int i = 0; i < dsgt.size(); i++){
                    if (dsgt.get(i).getMaGT().compareTo(maGT) == 0) {
                        ArrayList<BienSoan> dsBienSoan = dsgt.get(i).getBienSoan();
                        Date latestDate = null;
                        for (int j=0; j<dsBienSoan.size(); j++){
                            Date date = new Date(dsBienSoan.get(j).getKetThuc());
                            if (latestDate == null || after(date, latestDate)){
                                latestDate = new Date(date);
                            }
                        }
                        boolean dangKy = dsgt.get(i).isDangKy();
                        boolean bienSoan = !(dsBienSoan.isEmpty()) || dsgt.get(i).getTgkt().equals(latestDate);
                        if (!dangKy) {
                            JOptionPane.showMessageDialog(rootPane, "Giáo trình chưa đăng ký");
                        } else if (!bienSoan) {
                            JOptionPane.showMessageDialog(rootPane, "Giáo trình chưa hoàn thành biên soạn");
                        } else if (dsgt.get(i).isXuatBan()) {
                            JOptionPane.showMessageDialog(rootPane, "Giáo trình đã xuất bản trước đó");
                        } else {
                            dsgt.get(i).setXuatBan(true);
                            generateTable(dsgt);
                            JOptionPane.showMessageDialog(rootPane, "Xuất bản thành công");
                        }
                    }
                }
                saveFile();
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(rootPane, e.getMessage());
            }
        }
    }//GEN-LAST:event_xuatBanMouseClicked

    private void ResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetMouseClicked
        // TODO add your handling code here:
        resetTable();
    }//GEN-LAST:event_ResetMouseClicked

    private void TimKiemTimKiemGT(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemTimKiemGT
        // TODO add your handling code here:
        if (txtGT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập thông tin");
        } else if (txtGT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Thông tin không thể là chuỗi trống !");
        } else {
            String keyWord = txtGT.getText();
            int indexOp = Op_GT.getSelectedIndex();
            System.out.println(indexOp);
            ArrayList<GiaoTrinh> gtFounded = new ArrayList<>();

            switch (indexOp) {
                case 0: 
                    for (GiaoTrinh a : dsgt) {
                        if (a.getMaGT().contains(keyWord)) {
                            gtFounded.add(a);
                        }
                    }
                    
                    if (!gtFounded.isEmpty()) {
                        generateTable(gtFounded);
                        resetTextBox();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                    }
                
                case 1 : {
                    for (GiaoTrinh a : dsgt) {
                        if (a.getTenGT().contains(keyWord)) {
                            gtFounded.add(a);
                        }
                    }
                    
                    if (!gtFounded.isEmpty()) {
                        generateTable(gtFounded);
                        resetTextBox();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                    }
                }
                case 2: 
                    for (GiaoTrinh a : dsgt) {
                        if (a.getTenMon().getTenMon().contains(keyWord)) {
                            gtFounded.add(a);
                        }
                    }
                    
                    if (!gtFounded.isEmpty()) {
                        generateTable(gtFounded);
                        resetTextBox();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                    }
                
                case 3: 
                    for (GiaoTrinh a : dsgt) {
                        if (a.getTenMon().getSoTC() == Integer.parseInt(keyWord)) {
                            gtFounded.add(a);
                        }
                    }
                    
                    if (!gtFounded.isEmpty()) {
                        generateTable(gtFounded);
                        resetTextBox();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                    }
                
                case 4:
                    for (GiaoTrinh a : dsgt) {
                        if (a.getChuBien().getTenGV().contains(keyWord)) {
                            gtFounded.add(a);
                        }
                    }
                    
                    if (!gtFounded.isEmpty()) {
                        generateTable(gtFounded);
                        resetTextBox();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                    }
                
                case 5: 
                    for (GiaoTrinh a : dsgt) {
                        if (a.getdsTG().contains(keyWord)) {
                            gtFounded.add(a);
                        }
                    }
                    
                    if (!gtFounded.isEmpty()) {
                        generateTable(gtFounded);
                        resetTextBox();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                    }
                
                case 6: 
                    for (GiaoTrinh a : dsgt) {
                        if (a.getTgbd().getY() == Integer.parseInt(keyWord)) {
                            gtFounded.add(a);
                        }
                    }
                    
                    if (!gtFounded.isEmpty()) {
                        generateTable(gtFounded);
                        resetTextBox();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                    }
                
                case 7: 
                    for (GiaoTrinh a : dsgt) {
                        if (a.getTgkt().getY() == Integer.parseInt(keyWord)) {
                            gtFounded.add(a);
                        }
                    }
                    
                    if (!gtFounded.isEmpty()) {
                        generateTable(gtFounded);
                        resetTextBox();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                    }
                
            }

        }
    }//GEN-LAST:event_TimKiemTimKiemGT

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      setVisible(false);
        Menu mainMenu = new Menu();
        mainMenu.setVisible(true);
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyQuaTrinhXuatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyQuaTrinhXuatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyQuaTrinhXuatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyQuaTrinhXuatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new QuanLyQuaTrinhXuatBan().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Op_GT;
    private javax.swing.JButton Reset;
    private javax.swing.JButton TimKiem;
    private javax.swing.JButton jButton3;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtGT;
    private javax.swing.JButton xuatBan;
    // End of variables declaration//GEN-END:variables
}
