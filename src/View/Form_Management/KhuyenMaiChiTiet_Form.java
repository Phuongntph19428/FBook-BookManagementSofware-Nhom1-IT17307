/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.ManagementBookForm;
import View.ThongBao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.KhuyenMai;
import model.KhuyenMaiChiTiet;
import model.Sach;
import service.IKhuyenMaiChiTietService;
import service.SachService;
import service.impl.KhuyenMaiChiTietService;
import service.impl.SachServiceImpl;

/**
 *
 * @author quanc
 */
public class KhuyenMaiChiTiet_Form extends javax.swing.JPanel {

    private List<KhuyenMai> listKhuyenMai = new ArrayList<>();
    private List<KhuyenMaiChiTiet> listKhuyenMaiChiTiets = new ArrayList<>();
    private List<KhuyenMaiChiTiet> listNewAdd = new ArrayList<>();
    private List<Sach> lstSach = new ArrayList<>();
    HashMap<String, KhuyenMaiChiTiet> hsKMCT = new HashMap<>();
    private IKhuyenMaiChiTietService iKhuyenMaiChiTietService;
    private SachService iSachser;
    private String id = "[]";
    private int SizelstSach = -1;

    public KhuyenMaiChiTiet_Form() {
        initComponents();
        iKhuyenMaiChiTietService = new KhuyenMaiChiTietService();
        iSachser = new SachServiceImpl();
        SizelstSach = iSachser.countAllSach();
        lstSach = iSachser.getList(0, SizelstSach);
        String columns[] = {"Id", "Mã Khuyến Mại", "Tên Khuyến Mãi", "Chiết Khấu", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Trạng Thái", "Mô Tả"};
        Object rows[][] = {};
        String columnsCiTietKM[] = {"Mã Khuyến Mãi", "Mã Sách"};
        Object rowsCT[][] = {};
        this.tblChiTietKhuyenMai.setModel(new DefaultTableModel(rowsCT, columnsCiTietKM));
        this.tblKhuyenMai.setModel(new DefaultTableModel(rows, columns));
        this.tblChiTietKhuyenMai.setRowHeight(30);
        this.tblKhuyenMai.setRowHeight(30);
        this.tblSachProcessed.setRowHeight(50);
        loadTableKM();
        loadTableKMCT();
        loadComboBoxSach();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ChonSach = new View.DesignComponent.JPanelBourder();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSachProcessed = new View.DesignComponent.Table();
        jTextField1 = new javax.swing.JTextField();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuyenMai = new View.DesignComponent.Table();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietKhuyenMai = new View.DesignComponent.Table();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        jLabel2 = new javax.swing.JLabel();
        comboboxChucVu = new View.DesignComponent.Combobox();
        jLabel1 = new javax.swing.JLabel();
        lbKhuyenMai = new javax.swing.JLabel();
        btnTimKiem4 = new View.ButtonDesign.Button();
        jPanelBourder5 = new View.DesignComponent.JPanelBourder();
        jLabel4 = new javax.swing.JLabel();
        btnTimKiem = new View.ButtonDesign.Button();
        btnTimKiem1 = new View.ButtonDesign.Button();
        btnTimKiem2 = new View.ButtonDesign.Button();
        lbIDKhuyenMai = new javax.swing.JLabel();
        btnTimKiem3 = new View.ButtonDesign.Button();

        ChonSach.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(452, 395));

        tblSachProcessed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"KM1", "20-11", "5", null, null, null, null, null, null, null},
                {"KM2", "8-3", "10", null, null, null, null, null, null, null},
                {"KM3", "1-1", "11", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khuyến Mãi", "Tên Khuyến Mãi", "Phần Trăm Giảm", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSachProcessed.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblSachProcessed.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblSachProcessed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSachProcessedMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSachProcessed);

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout ChonSachLayout = new javax.swing.GroupLayout(ChonSach);
        ChonSach.setLayout(ChonSachLayout);
        ChonSachLayout.setHorizontalGroup(
            ChonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChonSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ChonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ChonSachLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ChonSachLayout.setVerticalGroup(
            ChonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChonSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(ChonSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ChonSachLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setBackground(new java.awt.Color(11, 20, 55));

        jPanelBourder2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 395));

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"KM1", "20-11", "5"},
                {"KM2", "8-3", "10"},
                {"KM3", "1-1", "11"},
                {null, null, null}
            },
            new String [] {
                "Mã Khuyến Mãi", "Tên Khuyến Mãi", "Phần Trăm Giảm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuyenMai.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblKhuyenMai.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhuyenMai);

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(38, 38, 38)))
        );

        jPanelBourder3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 395));

        tblChiTietKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"NXB1", "VT1", "S1", "Đắc Nhân Tâm", "15", "200", "20000", "30000", "Đang Bán", "None"},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nhà Xuất Bản", "Vị Trí", "Mã", "Tên", "Số Lượng", "Số Trang", "Giá Nhập", "Giá Bán", "Trạng thái", "Hình"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietKhuyenMai.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblChiTietKhuyenMai.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblChiTietKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChiTietKhuyenMai);

        javax.swing.GroupLayout jPanelBourder3Layout = new javax.swing.GroupLayout(jPanelBourder3);
        jPanelBourder3.setLayout(jPanelBourder3Layout);
        jPanelBourder3Layout.setHorizontalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1309, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelBourder3Layout.setVerticalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
            .addGroup(jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder3Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanelBourder1.setBackground(new java.awt.Color(17, 28, 68));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(104, 143, 222));
        jLabel2.setText("Chi Tiết Khuyến Mãi");

        comboboxChucVu.setBackground(new java.awt.Color(17, 28, 68));
        comboboxChucVu.setForeground(new java.awt.Color(255, 255, 255));
        comboboxChucVu.setMaximumRowCount(10);
        comboboxChucVu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        comboboxChucVu.setLabeText("Chọn Sách");
        comboboxChucVu.setLineColor(new java.awt.Color(255, 255, 255));
        comboboxChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxChucVuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã Khuyến Mãi");

        lbKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbKhuyenMai.setForeground(new java.awt.Color(0, 102, 255));
        lbKhuyenMai.setText("-");

        btnTimKiem4.setBackground(new java.awt.Color(31, 31, 111));
        btnTimKiem4.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnTimKiem4.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem4.setText("Chọn Nhiều Sách");
        btnTimKiem4.setFocusable(false);
        btnTimKiem4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnTimKiem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBourder1Layout = new javax.swing.GroupLayout(jPanelBourder1);
        jPanelBourder1.setLayout(jPanelBourder1Layout);
        jPanelBourder1Layout.setHorizontalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(68, 68, 68)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTimKiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboboxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBourder1Layout.setVerticalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboboxChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(btnTimKiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanelBourder5.setBackground(new java.awt.Color(17, 28, 68));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(104, 143, 222));
        jLabel4.setText("Danh Sách Khuyến Mãi");

        javax.swing.GroupLayout jPanelBourder5Layout = new javax.swing.GroupLayout(jPanelBourder5);
        jPanelBourder5.setLayout(jPanelBourder5Layout);
        jPanelBourder5Layout.setHorizontalGroup(
            jPanelBourder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(923, Short.MAX_VALUE))
        );
        jPanelBourder5Layout.setVerticalGroup(
            jPanelBourder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTimKiem.setBackground(new java.awt.Color(31, 31, 111));
        btnTimKiem.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Cập Nhật");
        btnTimKiem.setFocusable(false);
        btnTimKiem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem1.setBackground(new java.awt.Color(31, 31, 111));
        btnTimKiem1.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnTimKiem1.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem1.setText("Xóa Hết");
        btnTimKiem1.setFocusable(false);
        btnTimKiem1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem1ActionPerformed(evt);
            }
        });

        btnTimKiem2.setBackground(new java.awt.Color(31, 31, 111));
        btnTimKiem2.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnTimKiem2.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem2.setText("Xóa");
        btnTimKiem2.setFocusable(false);
        btnTimKiem2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnTimKiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem2ActionPerformed(evt);
            }
        });

        lbIDKhuyenMai.setText("jLabel3");

        btnTimKiem3.setBackground(new java.awt.Color(31, 31, 111));
        btnTimKiem3.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnTimKiem3.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem3.setText("Thêm Tất Cả");
        btnTimKiem3.setFocusable(false);
        btnTimKiem3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnTimKiem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelBourder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146)
                        .addComponent(lbIDKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTimKiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelBourder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBourder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBourder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbIDKhuyenMai)
                    .addComponent(btnTimKiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadTableKM() {
        listKhuyenMai = iKhuyenMaiChiTietService.selectAll();
        DefaultTableModel dtm = (DefaultTableModel) tblKhuyenMai.getModel();
        dtm.setRowCount(0);
        for (KhuyenMai km : listKhuyenMai) {
            dtm.addRow(km.toDaTaRow());
        }

    }

    private void loadComboBoxSach() {
        for (Sach s : lstSach) {
            comboboxChucVu.addItem(s.getTen());
        }
    }

    private void loadTableKMCT() {
        listKhuyenMaiChiTiets = iKhuyenMaiChiTietService.selectAll(id);
        DefaultTableModel dtm = (DefaultTableModel) tblChiTietKhuyenMai.getModel();
        dtm.setRowCount(0);
        listNewAdd = listKhuyenMaiChiTiets;
        for (KhuyenMaiChiTiet k : listNewAdd) {
            dtm.addRow(new Object[]{
                k.getKhuyenMai().getMa(),
                k.getSach().getTen()

            });
            hsKMCT.put(k.getSach().getId(), k);

        }

    }

    private void loadListNew() {
        DefaultTableModel dtm = (DefaultTableModel) tblChiTietKhuyenMai.getModel();
        dtm.setRowCount(0);
        for (KhuyenMaiChiTiet k : listNewAdd) {
            dtm.addRow(new Object[]{
                k.getKhuyenMai().getMa(),
                k.getSach().getTen()

            });
        }

    }

    private boolean CheckExist(String id) {
        if (hsKMCT.get(id) == null) {
            return true;
        }
        return false;
    }

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        int row = this.tblKhuyenMai.getSelectedRow();
        String ma;
        try {
            ma = tblKhuyenMai.getValueAt(row, 1).toString();
            id = tblKhuyenMai.getValueAt(row, 0).toString();
        } catch (Exception e) {
            e.printStackTrace();
            ma = "-";
            id = "-";
        }
        this.lbKhuyenMai.setText("" + ma);
        this.lbIDKhuyenMai.setText(id);
        id = tblKhuyenMai.getValueAt(row, 0).toString();
        loadTableKMCT();
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void tblChiTietKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietKhuyenMaiMouseClicked

    }//GEN-LAST:event_tblChiTietKhuyenMaiMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        iKhuyenMaiChiTietService.delete(id);
        for (KhuyenMaiChiTiet km : listNewAdd) {
            iKhuyenMaiChiTietService.insert(km);
        }
        loadTableKMCT();
        ThongBao.showNoti_Succes(this, "Cập Nhật Thành Công");
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem1ActionPerformed

        listNewAdd = new ArrayList<>();
        loadListNew();

    }//GEN-LAST:event_btnTimKiem1ActionPerformed

    private void btnTimKiem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem2ActionPerformed
        // TODO add your handling code here:
        int row = tblChiTietKhuyenMai.getSelectedRow();
        if (row == -1) {
            ThongBao.showNoti_Error(this, "Vui lòng chọn dòng cần xóa");
            return;
        } else {
            listNewAdd.remove(row);
            loadListNew();
        }
    }//GEN-LAST:event_btnTimKiem2ActionPerformed

    private void comboboxChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxChucVuActionPerformed
        String maKM, IdKM;
        maKM = this.lbKhuyenMai.getText().trim();
        if (maKM.equals("-")) {
            return;
        }
        IdKM = this.lbIDKhuyenMai.getText().trim();
        int indexSelected = comboboxChucVu.getSelectedIndex();
        System.out.println(indexSelected);
        Sach s = lstSach.get(indexSelected);
        KhuyenMai km = new KhuyenMai();
        km.setMa(maKM);
        km.setId(IdKM);
        KhuyenMaiChiTiet kmct = new KhuyenMaiChiTiet(km, s);
        boolean check = CheckExist(s.getId());
        if (check == false) {
            ThongBao.showNoti_Error(this, "Đã tồn tại mã sách");
            return;
        }
        tblChiTietKhuyenMai.addRow(new Object[]{kmct.getKhuyenMai().getMa(), kmct.getSach().getTen()});
        listNewAdd.add(kmct);
        hsKMCT.put(kmct.getSach().getId(), kmct);
    }//GEN-LAST:event_comboboxChucVuActionPerformed

    private void btnTimKiem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiem3ActionPerformed

    private List<Sach> removeElemetExitst(List<KhuyenMaiChiTiet> lstKMCT, List<Sach> lstSach) {
        HashMap<String, KhuyenMaiChiTiet> hmKMCT = new HashMap<>();
        List<Sach> lstSachProcessed = new ArrayList<>();
        for (KhuyenMaiChiTiet kmct : lstKMCT) {
            hmKMCT.put(kmct.getSach().getId(), kmct);
        }
        lstSach.forEach((t) -> {
            if (hmKMCT.get(t.getId()) == null) {
                lstSachProcessed.add(t);
            }
        });
        return lstSachProcessed;
    }
    private JDialog jdialog = new JDialog();

    private void setSachDialog() {
        List<Sach> lstSachProcessed = removeElemetExitst(listKhuyenMaiChiTiets, lstSach);
        DefaultTableModel dtm = (DefaultTableModel) tblSachProcessed.getModel();
        dtm.setRowCount(0);
        for (Sach k : lstSachProcessed) {
            dtm.addRow(k.toDataRow());
        }
        jdialog.setSize(1185, 454);
        jdialog.setModal(false);
        jdialog.add(ChonSach);
        jdialog.setLocation(this.getWidth() / 2 - 200, this.getHeight() / 2 - 200);
        jdialog.show(true);
        tblSachProcessed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    System.out.println(row);
                    Sach s = lstSachProcessed.get(row);
                    lstSachProcessed.remove(row);
                    String maKM, IdKM;
                    maKM = lbKhuyenMai.getText().trim();
                    if (maKM.equals("-")) {
                        return;
                    }
                    IdKM = lbIDKhuyenMai.getText().trim();
                    KhuyenMai km = new KhuyenMai();
                    km.setMa(maKM);
                    km.setId(IdKM);
                    KhuyenMaiChiTiet kmct = new KhuyenMaiChiTiet(km, s);
                    tblChiTietKhuyenMai.addRow(new Object[]{kmct.getKhuyenMai().getMa(), kmct.getSach().getTen()});
                    dtm.removeRow(row);
                    listNewAdd.add(kmct);
                }
            }
        });
    }

    private void btnTimKiem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem4ActionPerformed
        setSachDialog();
    }//GEN-LAST:event_btnTimKiem4ActionPerformed

    private void tblSachProcessedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachProcessedMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSachProcessedMouseClicked
//    private KhuyenMaiChiTiet getDaTa() {
//        int row = tblKhuyenMai.getSelectedRow();
//        String khuyenMai = tblKhuyenMai.getValueAt(row, 0).toString();
//        String sach = tblKhuyenMai.getValueAt(row, 1).toString();
//        KhuyenMaiChiTiet kmct = new KhuyenMaiChiTiet(khuyenMai, sach);
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.DesignComponent.JPanelBourder ChonSach;
    private View.ButtonDesign.Button btnTimKiem;
    private View.ButtonDesign.Button btnTimKiem1;
    private View.ButtonDesign.Button btnTimKiem2;
    private View.ButtonDesign.Button btnTimKiem3;
    private View.ButtonDesign.Button btnTimKiem4;
    private View.DesignComponent.Combobox comboboxChucVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private View.DesignComponent.JPanelBourder jPanelBourder5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbIDKhuyenMai;
    private javax.swing.JLabel lbKhuyenMai;
    private View.DesignComponent.Table tblChiTietKhuyenMai;
    private View.DesignComponent.Table tblKhuyenMai;
    private View.DesignComponent.Table tblSachProcessed;
    // End of variables declaration//GEN-END:variables
}
