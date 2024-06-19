/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkg5.chuongtrinh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static pkg5.chuongtrinh.Constant.pathGiaoTrinh;
import pkg5.chuongtrinh.lib.LoadUtil;
import pkg5.chuongtrinh.lib.WriteDataUtil;

/**
 *
 * @author adpcm
 */
public class QuanLyQuaTrinhBienSoan extends javax.swing.JFrame {
    
    ArrayList<GiaoTrinh> dsgt = new ArrayList<>();
    /**
     * Creates new form QuanLyQuaTrinhBienSoan
     */
    public QuanLyQuaTrinhBienSoan() {
        initComponents();
        ImageIcon icon = new ImageIcon("icon.png");
        ResetBtn.setIcon(icon);
        Reset.setIcon(icon);
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
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        WriteDataUtil.writeGiaoTrinhData(dsgt);
    }
    
    private boolean checkInput() throws Exception {
        if (txtSD.getText().isEmpty() || txtSM.getText().isEmpty() || txtSY.getText().isEmpty() || txtFD.getText().isEmpty() || txtFM.getText().isEmpty() || txtFD.getText().isEmpty()) {
            throw new Exception("Bạn phải nhập đầy đủ ngày tháng năm!");
        } else {
            int SD, SM, SY, FD, FM, FY;
            try {
                SD = Integer.parseInt(txtSD.getText());
                SM = Integer.parseInt(txtSM.getText());
                SY = Integer.parseInt(txtSY.getText());
                
                FD = Integer.parseInt(txtFD.getText());
                FM = Integer.parseInt(txtFM.getText());
                FY = Integer.parseInt(txtFY.getText());
              
            } catch (Exception e) {
                throw new Exception("Ngày tháng năm không hợp lệ !");
            }    
            if (SM < 1 || SM > 12) {
                throw new Exception("Tháng bắt đầu không hợp lệ !");
            }
                
            if (FM < 1 || FM > 12) {
                throw new Exception("Tháng kết thúc không hợp lệ !");
            }
                
            int DayOfMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (SD < 1 || SD > DayOfMonth[SM]) {
                throw new Exception("Ngày bắt đầu không hợp lệ !");
            }
                
            if ((SM == 2 && SD == 29) && (SY % 4 == 0 && SY % 100 != 0 || SY % 400 != 0)) {
                throw new Exception("Ngày bắt đầu không hợp lệ !");
            }
                
            if (FD < 1 || FD > DayOfMonth[FM]) {
                throw new Exception("Ngày kết thúc không hợp lệ !");
            }
                
            if ((FM == 2 && FD == 29) && (FY % 4 == 0 && FY % 100 != 0 || FY % 400 != 0)) {
                throw new Exception("Ngày kết thúc không hợp lệ !");
            }
            
            if (FY < SY) {
                throw new Exception("Năm không hợp lệ !");
            } else if (FY == SY) {
                if (SM > FM) {
                    throw new Exception("Tháng không hợp lệ !");
                } else if (SM == FM) {
                    if (SD > FD) {
                        throw new Exception("Ngày không hợp lệ !");
                    }
                }
            }
        }
        
        if (txtND.getText().isEmpty()) {
            throw new Exception("Bạn phải nhập nội dung !");
        }
        
        return true;
    }
    
    private void resetTable() {
        loadFile();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        Object[] columnNames = {"Mã GT", "Tên GT", "Môn học", "Số tín chỉ", "Khoa", "Chủ biên", "Các tác giả", "Thời gian bắt đầu", "Thời gian kết thúc"};
        model.setColumnIdentifiers(columnNames);
        Object[][] rowData = new Object[dsgt.size()][9];
        for (int i = 0; i < dsgt.size(); i++) {
            rowData[i][0] = dsgt.get(i).getMaGT();
            rowData[i][1] = dsgt.get(i).getTenGT();
            rowData[i][2] = dsgt.get(i).getTenMon().getTenMon();
            rowData[i][3] = dsgt.get(i).getTenMon().getSoTC();
            rowData[i][4] = dsgt.get(i).getKhoa().getTenKhoa();
            rowData[i][5] = dsgt.get(i).getChuBien().getTenGV();
            rowData[i][6] = dsgt.get(i).getdsTG();
            rowData[i][7] = dsgt.get(i).getTgbd().toString();
            rowData[i][8] = dsgt.get(i).getTgkt().toString();
        }
        model.setDataVector(rowData, columnNames);
        this.Table.setModel(model);
        this.add(new JScrollPane(table));
        this.pack();
        this.setVisible(true);
    }
    
    private void resetTextBox() {
        txtSD.setText("");
        txtSM.setText("");
        txtSY.setText("");
        
        txtFD.setText("");
        txtFM.setText("");
        txtFY.setText("");

        txtND.setText("");
        txtGC.setText("");
        txtGT.setText("");
    }

    private void reloadTable2(int tableRow) {
        ArrayList<BienSoan> qtbs = dsgt.get(tableRow).getBienSoan();
        
        JTable table = new JTable();
        
        DefaultTableModel model = new DefaultTableModel();
        Object[] columnNames = {"Từ ngày", "Đến ngày", "Nội dung", "Ghi chú"};
        model.setColumnIdentifiers(columnNames);
        
        Object[][] rowData = new Object[qtbs.size()][5];
        
        for (int i = 0; i < qtbs.size(); i++) {
            rowData[i][0] = qtbs.get(i).getBatDau();
            rowData[i][1] = qtbs.get(i).getKetThuc();
            rowData[i][2] = qtbs.get(i).getNoiDung();
            rowData[i][3] = qtbs.get(i).getGhiChu();

        }
        
        model.setDataVector(rowData, columnNames);
        this.Table2.setModel(model);
        this.add(new JScrollPane(table));
        this.pack();
        this.setVisible(true);
    }
    
    private void ReloadTable2(String maGT) {
        ArrayList<BienSoan> qtbs = new ArrayList<>();
        for (GiaoTrinh a : dsgt) {
            if (a.getMaGT() == maGT) {
                qtbs = a.getBienSoan();
                break;
            }
        }
        JTable table = new JTable();
        
        DefaultTableModel model = new DefaultTableModel();
        Object[] columnNames = {"Từ ngày", "Đến ngày", "Nội dung", "Ghi chú"};
        model.setColumnIdentifiers(columnNames);
        
        Object[][] rowData = new Object[qtbs.size()][4];
        
        for (int i = 0; i < qtbs.size(); i++) {
            rowData[i][0] = qtbs.get(i).getBatDau();
            rowData[i][1] = qtbs.get(i).getKetThuc();
            rowData[i][2] = qtbs.get(i).getNoiDung();
            rowData[i][3] = qtbs.get(i).getGhiChu();

        }
        
        model.setDataVector(rowData, columnNames);
        this.Table2.setModel(model);
        this.add(new JScrollPane(table));
        this.pack();
        this.setVisible(true);
    }
    
    private void generateTable(ArrayList<GiaoTrinh> list) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        Object[] columnNames = {"Mã GT", "Tên GT", "Môn học", "Số tín chỉ", "Khoa", "Chủ biên", "Các tác giả", "Thời gian bắt đầu", "Thời gian kết thúc"};
        model.setColumnIdentifiers(columnNames);
        ArrayList<GiangVien> dsTacGia;
        String cacTacGia;
        Object[][] rowData = new Object[list.size()][9];
        for (int i = 0; i < list.size(); i++) {
            rowData[i][0] = list.get(i).getMaGT();
            rowData[i][1] = list.get(i).getTenGT();
            rowData[i][2] = list.get(i).getTenMon().getTenMon();
            rowData[i][3] = list.get(i).getTenMon().getSoTC();
            rowData[i][4] = list.get(i).getKhoa().getTenKhoa();
            rowData[i][5] = list.get(i).getChuBien().getTenGV();
            dsTacGia = list.get(i).getCacTacGia();
            cacTacGia = "";
            for (GiangVien x : dsTacGia) {
                cacTacGia += x.getTenGV() + " ";
            }
            rowData[i][6] = cacTacGia;
            rowData[i][7] = list.get(i).getTgbd().toString();
            rowData[i][8] = list.get(i).getTgkt().toString();
        }
        model.setDataVector(rowData, columnNames);
        this.Table.setModel(model);
        this.add(new JScrollPane(table));
        this.pack();
        this.setVisible(true);
    }
    
    private void generateTable2(ArrayList<BienSoan> list) {
        JTable table = new JTable();
        
        DefaultTableModel model = new DefaultTableModel();
        Object[] columnNames = {"Từ ngày", "Đến ngày", "Nội dung", "Ghi chú"};
        model.setColumnIdentifiers(columnNames);
        
        Object[][] rowData = new Object[list.size()][4];
        
        for (int i = 0; i < list.size(); i++) {
            rowData[i][0] = list.get(i).getBatDau();
            rowData[i][1] = list.get(i).getKetThuc();
            rowData[i][2] = list.get(i).getNoiDung();
            rowData[i][3] = list.get(i).getGhiChu();
            }
                        
        model.setDataVector(rowData, columnNames);
        this.Table2.setModel(model);
        this.add(new JScrollPane(table));
        this.pack();
        this.setVisible(true);
    }
    
    private BienSoan getCurrentBS() {
        // Lấy thông tin biên soạn được chọn
        String[] startDate = Table2.getValueAt(Table2.getSelectedRow(), 0).toString().split("/");
        String[] endDate = Table2.getValueAt(Table2.getSelectedRow(), 1).toString().split("/");
        String nd = Table2.getValueAt(Table2.getSelectedRow(), 2).toString();
        String gc = Table2.getValueAt(Table2.getSelectedRow(), 3).toString();
        // Tạo biên soạn
        BienSoan currentBS = new BienSoan(
            new Date(Integer.parseInt(startDate[0]), Integer.parseInt(startDate[1]),Integer.parseInt(startDate[2])), 
            new Date(Integer.parseInt(endDate[0]),Integer.parseInt(endDate[1]),Integer.parseInt(endDate[2])),
                nd,
                gc
        );
        
        return currentBS;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        txtGT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        OpS_GT = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSD = new javax.swing.JTextField();
        txtSM = new javax.swing.JTextField();
        txtSY = new javax.swing.JTextField();
        txtFD = new javax.swing.JTextField();
        txtFM = new javax.swing.JTextField();
        txtFY = new javax.swing.JTextField();
        txtND = new javax.swing.JTextField();
        txtGC = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        QLBS_Them = new javax.swing.JButton();
        Sua = new javax.swing.JButton();
        Xoa = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        OpS_BS = new javax.swing.JComboBox<>();
        QLBS_TimKiem = new javax.swing.JButton();
        QLBS_QuayLai = new javax.swing.JButton();
        ResetBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ QUÁ TRÌNH BIÊN SOẠN");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setLocationByPlatform(true);
        setResizable(false);

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã GT", "Tên GT", "Môn học", "Số tín chỉ", "Khoa", "Chủ biên", "Các tác giả", "Thời gian bắt đầu", "Thời gian kết thúc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        Table.getTableHeader().setReorderingAllowed(false);
        Table.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                TableAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setResizable(false);
            Table.getColumnModel().getColumn(0).setPreferredWidth(20);
            Table.getColumnModel().getColumn(1).setResizable(false);
            Table.getColumnModel().getColumn(2).setResizable(false);
            Table.getColumnModel().getColumn(3).setResizable(false);
            Table.getColumnModel().getColumn(4).setResizable(false);
            Table.getColumnModel().getColumn(5).setResizable(false);
            Table.getColumnModel().getColumn(6).setResizable(false);
            Table.getColumnModel().getColumn(7).setResizable(false);
            Table.getColumnModel().getColumn(8).setResizable(false);
        }

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tìm kiếm giáo trình:");

        OpS_GT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã giáo trình", "Tên giáo trình", "Môn học", "Số tín chỉ", "Khoa", "Chủ biên", "Các tác giả", "Năm bắt đầu", "Năm kết thúc" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tìm kiếm theo:");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemGT(evt);
            }
        });

        Reset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGT, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(OpS_GT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(Reset)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(OpS_GT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(225, 225, 225))
        );

        Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Từ ngày", "Đến ngày", "Nội dung", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table2.setRowHeight(80);
        Table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Table2);
        if (Table2.getColumnModel().getColumnCount() > 0) {
            Table2.getColumnModel().getColumn(0).setResizable(false);
            Table2.getColumnModel().getColumn(1).setResizable(false);
            Table2.getColumnModel().getColumn(2).setResizable(false);
            Table2.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Từ ngày");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Đến ngày");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nội dung");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Ghi chú");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFD, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFM, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSY, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(txtFY))
                        .addGap(0, 53, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtND))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGC)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtND, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGC, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        QLBS_Them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        QLBS_Them.setText("Thêm");
        QLBS_Them.setPreferredSize(new java.awt.Dimension(72, 30));
        QLBS_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Them(evt);
            }
        });

        Sua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Sua.setText("Sửa");
        Sua.setPreferredSize(new java.awt.Dimension(72, 30));
        Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sua(evt);
            }
        });

        Xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Xoa.setText("Xóa");
        Xoa.setMaximumSize(new java.awt.Dimension(80, 30));
        Xoa.setMinimumSize(new java.awt.Dimension(80, 30));
        Xoa.setPreferredSize(new java.awt.Dimension(80, 30));
        Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tìm kiếm theo:");

        OpS_BS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày bắt đầu", "Ngày kết thúc", "Nội dung", "Ghi chú" }));

        QLBS_TimKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        QLBS_TimKiem.setText("Tìm kiếm");
        QLBS_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemBS(evt);
            }
        });

        QLBS_QuayLai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        QLBS_QuayLai.setText("Quay lại");
        QLBS_QuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLBS_QuayLaiActionPerformed(evt);
            }
        });

        ResetBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetBtn(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(ResetBtn)
                        .addGap(28, 28, 28)
                        .addComponent(QLBS_QuayLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(QLBS_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(OpS_BS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(QLBS_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(QLBS_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OpS_BS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QLBS_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QLBS_QuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Quá trình biên soạn:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Them(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Them
        // TODO add your handling code here:
        if (Table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn Giáo Trình !");
        } else {
            try {
                if (checkInput()) {
                    String maGT = Table.getValueAt(Table.getSelectedRow(), 0).toString();
                    int SD = Integer.parseInt(txtSD.getText());
                    int SM = Integer.parseInt(txtSM.getText());
                    int SY = Integer.parseInt(txtSY.getText());
                
                    int FD = Integer.parseInt(txtFD.getText());
                    int FM = Integer.parseInt(txtFM.getText());
                    int FY = Integer.parseInt(txtFY.getText());
                    
                    String noiDung = txtND.getText();
                    String ghiChu = txtGC.getText();
                    
                    BienSoan newBS = new BienSoan(new Date(SD, SM, SY), new Date(FD, FM, FY), noiDung, ghiChu);
                    
                    GiaoTrinh oldGT = new GiaoTrinh();
                    
                    for (GiaoTrinh x : dsgt) {
                        if (x.getMaGT().compareTo(maGT) == 0) {
                            oldGT = x;
                        }
                    }
                    
                    ArrayList<BienSoan> oldBS = oldGT.getBienSoan();
                    for (BienSoan z : oldBS) {
                        if (newBS.equals(z)) {
                            JOptionPane.showMessageDialog(rootPane, "Khoảng thời gian này đã tồn tại !");
                            return;
                        }
                    }
                    
                    oldBS.add(newBS);
                    Collections.sort(oldBS, new BienSoanSort());
                    oldGT.setBienSoan(oldBS);

                    saveFile();
                    
                    ReloadTable2(maGT);
                    
                    JOptionPane.showMessageDialog(rootPane, "Thêm thành công !");
                    resetTextBox();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e.getMessage());
            }
        }
    }//GEN-LAST:event_Them

    private void XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaActionPerformed
        // TODO add your handling code here:
        if (Table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn giáo trình !");
        } else if (Table2.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn biên soạn !");
        } else {
            
            String maGT = Table.getValueAt(Table.getSelectedRow(), 0).toString();
            GiaoTrinh oldGT = new GiaoTrinh();
            for (GiaoTrinh x : dsgt) {
                if (x.getMaGT().compareTo(maGT) == 0) {
                    oldGT = x;
                }
            }
            ArrayList<BienSoan> oldBS = oldGT.getBienSoan();
            
            
            BienSoan currentBS = getCurrentBS();
            int indexBS = -1;
            for (BienSoan x : oldBS) {
                indexBS++;
                if (x.equals(currentBS)) {
                    break;
                }
            }
            
            
            oldBS.remove(indexBS);
            
            
            ReloadTable2(maGT);
            
            resetTextBox();
            saveFile();
            JOptionPane.showMessageDialog(rootPane, "Xóa thành công !");
        }
    }//GEN-LAST:event_XoaActionPerformed

    private void Table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseClicked
        // TODO add your handling code here:
        BienSoan oldBS = getCurrentBS();
        txtSD.setText(Integer.toString(oldBS.getBatDau().getD()));
        txtSM.setText(Integer.toString(oldBS.getBatDau().getM()));
        txtSY.setText(Integer.toString(oldBS.getBatDau().getY()));
        
        txtFD.setText(Integer.toString(oldBS.getKetThuc().getD()));
        txtFM.setText(Integer.toString(oldBS.getKetThuc().getM()));
        txtFY.setText(Integer.toString(oldBS.getKetThuc().getY()));

        txtND.setText(oldBS.getNoiDung());
        txtGC.setText(oldBS.getGhiChu());
    }//GEN-LAST:event_Table2MouseClicked

    private void Sua(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sua
        // TODO add your handling code here:
        if (Table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn giáo trình !");
        } else if (Table2.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn biên soạn !");
        } else {
            try {
                if(checkInput()) {
                    
                    
                    String maGT = Table.getValueAt(Table.getSelectedRow(), 0).toString();
                    
                    
                    
                    BienSoan currentBS = getCurrentBS();
                    
                    
                    int Table2Row = Table2.getSelectedRow();
                    
                    
                    int SD = Integer.parseInt(txtSD.getText());
                    int SM = Integer.parseInt(txtSM.getText());
                    int SY = Integer.parseInt(txtSY.getText());
                
                    int FD = Integer.parseInt(txtFD.getText());
                    int FM = Integer.parseInt(txtFM.getText());
                    int FY = Integer.parseInt(txtFY.getText());
                    
                    String noiDung = txtND.getText();
                    String ghiChu = txtGC.getText();
                    
                    BienSoan newBS = new BienSoan(new Date(SD, SM, SY), new Date(FD, FM, FY), noiDung, ghiChu);
                    
                    
                    GiaoTrinh oldGT = new GiaoTrinh();
                    for (GiaoTrinh x : dsgt) {
                        if (x.getMaGT().compareTo(maGT) == 0) {
                            oldGT = x;
                        }
                    }
                    ArrayList<BienSoan> oldBS = oldGT.getBienSoan();
                    
                    
                    int bsIndex = -1;
                    for (BienSoan x : oldBS) {
                        bsIndex++;
                        if (x.equals(currentBS)) {
                            break;
                        }
                    }
                    
                    
                    oldBS.set(bsIndex, newBS);
                    Collections.sort(oldBS, new BienSoanSort());
                    
                    
                    oldGT.setBienSoan(oldBS);
                    
                    
                    ReloadTable2(maGT);
                    
                    
                    Table2.setRowSelectionInterval(Table2Row, Table2Row);
                    JOptionPane.showMessageDialog(rootPane, "Sửa thành công !");
                    resetTextBox();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e.getMessage());
            }
        }
    }//GEN-LAST:event_Sua

    private void TimKiemBS(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemBS
        // TODO add your handling code here:
        if (Table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn giáo trình !");
        } else {
            int TableRow = Table.getSelectedRow();
            int indexOp = OpS_BS.getSelectedIndex();
            ArrayList<BienSoan> dsBS = dsgt.get(TableRow).getBienSoan();
            ArrayList<BienSoan> bsFounded = new ArrayList<>();
            
            switch (indexOp) {
                case 0:
                    if (txtSD.getText().isEmpty() || txtSM.getText().isEmpty() || txtSY.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập đủ ngày tháng năm !");
                    } else {
                        int SD = Integer.parseInt(txtSD.getText());
                        int SM = Integer.parseInt(txtSM.getText());
                        int SY = Integer.parseInt(txtSY.getText());
                        Date findDate = new Date(SD, SM, SY);
                        for (BienSoan a : dsBS) {
                            if (a.getBatDau().equals(findDate)) {
                                bsFounded.add(a);
                            }
                        }
                        if (!bsFounded.isEmpty()) {
                            generateTable2(bsFounded);
                            resetTextBox();
                        } else { 
                            JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                        }
                    }   
                    break;
                case 1:
                    if (txtFD.getText().isEmpty() || txtFM.getText().isEmpty() || txtFY.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập đủ ngày tháng năm !");
                    } else {
                        int SD = Integer.parseInt(txtFD.getText());
                        int SM = Integer.parseInt(txtFM.getText());
                        int SY = Integer.parseInt(txtFY.getText());
                        Date findDate = new Date(SD, SM, SY);
                        for (BienSoan a : dsBS) {
                            if (a.getBatDau().equals(findDate)) {
                                    bsFounded.add(a);
                            }
                        }
                        if (!bsFounded.isEmpty()) {
                            generateTable2(bsFounded);
                            resetTextBox();
                        } else { 
                            JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                        }
                    }   
                    break;
                case 2:
                    if (txtND.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập nội dung !");
                    } else if(txtND.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(rootPane, "Nội dung không thể là chuỗi trống !");                    
                    } else {
                        String ndFind = txtND.getText();
                        for (BienSoan a : dsBS) {
                            if (a.getNoiDung().contains(ndFind)) {
                                    bsFounded.add(a);
                            }
                        }
                        if (!bsFounded.isEmpty()) {
                            generateTable2(bsFounded);
                            resetTextBox();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                        }
                    }
                    break;
                case 3:
                    String ndFind = txtGC.getText().trim();
                    for (BienSoan a : dsBS) {
                        if (a.getGhiChu().contains(ndFind)) {
                                bsFounded.add(a);
                        }
                    }
                    if (!bsFounded.isEmpty()) {
                        generateTable2(bsFounded);
                        resetTextBox();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                    }
                    break;
            } 
        }
    }//GEN-LAST:event_TimKiemBS

    private void QLBS_QuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLBS_QuayLaiActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        Menu mainMenu = new Menu();
        mainMenu.setVisible(true);
    }//GEN-LAST:event_QLBS_QuayLaiActionPerformed

    private void ResetBtn(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetBtn
        // TODO add your handling code here:
        if (Table.getSelectedRow() == -1) {
//            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn giáo trình !");
        } else {
            String maGT = Table.getValueAt(Table.getSelectedRow(), 0).toString();
            ReloadTable2(maGT);
            resetTextBox();
        }
    }//GEN-LAST:event_ResetBtn

    private void TimKiemGT(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemGT
        // TODO add your handling code here:
        if (txtGT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập thông tin");
        } else if (txtGT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Thông tin không thể là chuỗi trống !");
        } else {
            String keyWord = txtGT.getText();
            int indexOp = OpS_GT.getSelectedIndex();
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
                    break;
                case 1:
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
                    break;
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
                    break;
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
                    break;
                case 4:
                    for (GiaoTrinh a : dsgt) {
                        if (a.getKhoa().getTenKhoa().contains(keyWord)) {
                            gtFounded.add(a);
                        }
                    }

                    if (!gtFounded.isEmpty()) {
                        generateTable(gtFounded);
                        resetTextBox();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy !");
                    }
                    break;
                case 5:
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
                    break;
                case 6:
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
                    break;
                case 7:
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
                    break;
                case 8:
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
                    break;
            }
            
        }
    }//GEN-LAST:event_TimKiemGT

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        // TODO add your handling code here:
        String maGT = Table.getValueAt(Table.getSelectedRow(), 0).toString();
        ReloadTable2(maGT);
    }//GEN-LAST:event_TableMouseClicked

    private void TableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_TableAncestorAdded
        // TODO add your handling code here:
        resetTable();
    }//GEN-LAST:event_TableAncestorAdded

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        // TODO add your handling code here:
        resetTable();
        resetTextBox();
        DefaultTableModel model = (DefaultTableModel)Table2.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_ResetActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyQuaTrinhBienSoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyQuaTrinhBienSoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyQuaTrinhBienSoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyQuaTrinhBienSoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyQuaTrinhBienSoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> OpS_BS;
    private javax.swing.JComboBox<String> OpS_GT;
    private javax.swing.JButton QLBS_QuayLai;
    private javax.swing.JButton QLBS_Them;
    private javax.swing.JButton QLBS_TimKiem;
    private javax.swing.JButton Reset;
    private javax.swing.JButton ResetBtn;
    private javax.swing.JButton Sua;
    private javax.swing.JTable Table;
    private javax.swing.JTable Table2;
    private javax.swing.JButton Xoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtFD;
    private javax.swing.JTextField txtFM;
    private javax.swing.JTextField txtFY;
    private javax.swing.JTextField txtGC;
    private javax.swing.JTextField txtGT;
    private javax.swing.JTextField txtND;
    private javax.swing.JTextField txtSD;
    private javax.swing.JTextField txtSM;
    private javax.swing.JTextField txtSY;
    // End of variables declaration//GEN-END:variables
}
