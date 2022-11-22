/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.NhaCungCap;
import model.PhieuNhap;
import service.PhieuNhapService;
import service.impl.PhieuNhapServiceImpl;

/**
 *
 * @author quanc
 */
public class PhieuNhap_Form extends javax.swing.JPanel {
    
    private DefaultTableModel model = new DefaultTableModel();
    private PhieuNhapService phieuNhapService = new PhieuNhapServiceImpl();
    private DefaultComboBoxModel cbbModel = new DefaultComboBoxModel();
    private List<PhieuNhap> listPN = new ArrayList<>();
    private List<String> cbbNcc = new ArrayList<>();
    
    public PhieuNhap_Form() {
        initComponents();
        String columns[] = {"ID", "Mã", "Nhà cung cap", "Ngày nhâp", "Trang thái"};
        Object rows[][] = {};
        this.cbbTTNCC.disable();
        this.tablePhieuNhap.setRowHeight(59);
        this.tablePhieuNhap.setBackground(Color.white);
        this.tablePhieuNhap.setModel(new DefaultTableModel(rows, columns));
        listPN = phieuNhapService.getAllPhieuNhap();
        cbbNcc = phieuNhapService.cbbNcc();
        loadCbbNcc(cbbNcc);
        loadPhieuNhap(listPN);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ngayNhap = new View.DateChooser.DateChooser();
        pagePanel = new javax.swing.JPanel();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        txtSearch = new View.DesignComponent.TextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnInBaoCao3 = new View.ButtonDesign.Button();
        btnInBaoCao1 = new View.ButtonDesign.Button();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePhieuNhap = new View.DesignComponent.Table();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtId = new View.DesignComponent.TextField();
        txtMaPN = new View.DesignComponent.TextField();
        txtNgayNhap = new View.DesignComponent.TextField();
        txtMoTa = new View.DesignComponent.TextField();
        cbbNCC = new View.DesignComponent.Combobox();
        btnNgayKetThuc = new View.ButtonDesign.Button();
        cbbTTNCC = new View.DesignComponent.Combobox();
        btnXoa = new View.ButtonDesign.Button();
        txtClear = new View.ButtonDesign.Button();
        btnThem = new View.ButtonDesign.Button();
        btnSua = new View.ButtonDesign.Button();

        ngayNhap.setForeground(new java.awt.Color(47, 55, 90));
        ngayNhap.setTextRefernce(txtNgayNhap);

        setBackground(new java.awt.Color(11, 20, 55));

        pagePanel.setBackground(new java.awt.Color(29, 32, 57));
        pagePanel.setLayout(new java.awt.GridLayout(1, 0));

        jPanelBourder1.setBackground(new java.awt.Color(17, 28, 68));

        txtSearch.setBackground(new java.awt.Color(17, 28, 68));
        txtSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSearch.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSearch.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        txtSearch.setLabelText("Tìm Kiếm Theo Tên");
        txtSearch.setLineColor(new java.awt.Color(255, 255, 255));
        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(104, 143, 222));
        jLabel2.setText("List of Receipt");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(1, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelBourder1Layout = new javax.swing.GroupLayout(jPanelBourder1);
        jPanelBourder1.setLayout(jPanelBourder1Layout);
        jPanelBourder1Layout.setHorizontalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(771, 771, 771))
        );
        jPanelBourder1Layout.setVerticalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        btnInBaoCao3.setBackground(new java.awt.Color(31, 31, 111));
        btnInBaoCao3.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao3.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image_Hub/icons8_new_copy_35px.png"))); // NOI18N
        btnInBaoCao3.setText("Import Excel");
        btnInBaoCao3.setFocusable(false);
        btnInBaoCao3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao3ActionPerformed(evt);
            }
        });

        btnInBaoCao1.setBackground(new java.awt.Color(31, 31, 111));
        btnInBaoCao1.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao1.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image_Hub/icons8_microsoft_excel_35px.png"))); // NOI18N
        btnInBaoCao1.setText("Export Excel");
        btnInBaoCao1.setFocusable(false);
        btnInBaoCao1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao1ActionPerformed(evt);
            }
        });

        jPanelBourder3.setBackground(new java.awt.Color(47, 55, 90));

        jPanelBourder2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 395));

        tablePhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
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
        tablePhieuNhap.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tablePhieuNhap.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tablePhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePhieuNhap);

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(77, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanelBourder3Layout = new javax.swing.GroupLayout(jPanelBourder3);
        jPanelBourder3.setLayout(jPanelBourder3Layout);
        jPanelBourder3Layout.setHorizontalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelBourder3Layout.setVerticalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Management Book - Quản Lý Phiếu Nhập");

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setPreferredSize(new java.awt.Dimension(1330, 1));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1330, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(11, 20, 55));

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(47, 55, 90));
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setCaretColor(new java.awt.Color(255, 255, 255));
        txtId.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtId.setLabelText("ID");
        txtId.setLineColor(new java.awt.Color(255, 255, 255));

        txtMaPN.setBackground(new java.awt.Color(47, 55, 90));
        txtMaPN.setForeground(new java.awt.Color(255, 255, 255));
        txtMaPN.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMaPN.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMaPN.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMaPN.setLabelText("Mã Phiếu Nhập");
        txtMaPN.setLineColor(new java.awt.Color(255, 255, 255));

        txtNgayNhap.setBackground(new java.awt.Color(47, 55, 90));
        txtNgayNhap.setForeground(new java.awt.Color(255, 255, 255));
        txtNgayNhap.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNgayNhap.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNgayNhap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtNgayNhap.setLabelText("Ngày Nhập");
        txtNgayNhap.setLineColor(new java.awt.Color(255, 255, 255));

        txtMoTa.setBackground(new java.awt.Color(47, 55, 90));
        txtMoTa.setForeground(new java.awt.Color(255, 255, 255));
        txtMoTa.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMoTa.setLabelText("Mô Tả");
        txtMoTa.setLineColor(new java.awt.Color(255, 255, 255));

        cbbNCC.setBackground(new java.awt.Color(47, 55, 90));
        cbbNCC.setForeground(new java.awt.Color(255, 255, 255));
        cbbNCC.setMaximumRowCount(10);
        cbbNCC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        cbbNCC.setSelectedIndex(-1);
        cbbNCC.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        cbbNCC.setLabeText("Nhà Cung Cấp");
        cbbNCC.setLineColor(new java.awt.Color(255, 255, 255));

        btnNgayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayKetThucActionPerformed(evt);
            }
        });

        cbbTTNCC.setBackground(new java.awt.Color(47, 55, 90));
        cbbTTNCC.setForeground(new java.awt.Color(255, 255, 255));
        cbbTTNCC.setMaximumRowCount(10);
        cbbTTNCC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đã Thêm Vào Kho", "Chưa Thêm Vào Kho" }));
        cbbTTNCC.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        cbbTTNCC.setLabeText("Nhà Cung Cấp");
        cbbTTNCC.setLineColor(new java.awt.Color(255, 255, 255));

        btnXoa.setBackground(new java.awt.Color(31, 31, 111));
        btnXoa.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setFocusable(false);
        btnXoa.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbTTNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addComponent(cbbTTNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        txtClear.setBackground(new java.awt.Color(31, 31, 111));
        txtClear.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        txtClear.setForeground(new java.awt.Color(255, 255, 255));
        txtClear.setText("Làm Mới");
        txtClear.setFocusable(false);
        txtClear.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClearActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(31, 31, 111));
        btnThem.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Tạo Mới");
        btnThem.setFocusable(false);
        btnThem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(31, 31, 111));
        btnSua.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Cập Nhật");
        btnSua.setFocusable(false);
        btnSua.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInBaoCao3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnInBaoCao1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(pagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanelBourder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelBourder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInBaoCao3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInBaoCao1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder3, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    public void loadPhieuNhap(List<PhieuNhap> listPN) {
        model = (DefaultTableModel) tablePhieuNhap.getModel();
        model.setRowCount(0);
        for (PhieuNhap phieuNhap : listPN) {
            model.addRow(new Object[]{phieuNhap.getId(), phieuNhap.getMa(), phieuNhap.getNhaCungCap().getTen(), phieuNhap.getNgayNhap(), phieuNhap.trangThai()});
        }
    }
    
    public void loadCbbNcc(List<String> listNcc) {
        cbbModel = (DefaultComboBoxModel) cbbNCC.getModel();
        cbbNCC.removeAllItems();
        for (String string : listNcc) {
            cbbModel.addElement(string);
        }
    }
    
    public void clearForm() {
        txtId.setText("");
        txtMaPN.setText("");
        txtNgayNhap.setText("");
        txtMoTa.setText("");
        cbbNCC.setSelectedIndex(0);
//        txtId.setText("");
    }
    private void btnInBaoCao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao3ActionPerformed

    private void btnInBaoCao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao1ActionPerformed

    private void btnNgayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayKetThucActionPerformed
        ngayNhap.showPopup();
    }//GEN-LAST:event_btnNgayKetThucActionPerformed

    private void txtClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClearActionPerformed
        clearForm();
    }//GEN-LAST:event_txtClearActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String tenNcc = cbbNCC.getSelectedItem().toString();
        String idNcc = phieuNhapService.findById(tenNcc);
        NhaCungCap ncc = new NhaCungCap();
        ncc.setId(idNcc);
        SimpleDateFormat ngayNhap = new SimpleDateFormat("dd-mm-yyyy");
        PhieuNhap phieuNhap = new PhieuNhap();
        phieuNhap.setMa(txtMaPN.getText());
        phieuNhap.setNhaCungCap(ncc);
        try {
            phieuNhap.setNgayNhap(ngayNhap.parse(txtNgayNhap.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(PhieuNhap_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        phieuNhap.setMoTa(txtMoTa.getText());
        if (cbbTTNCC.getSelectedItem().toString().equalsIgnoreCase("Da them vao kho")) {
            phieuNhap.setTrangThai(1);
        } else {
            phieuNhap.setTrangThai(0);
            
        }
        
        phieuNhapService.addphieuNhap(phieuNhap);
        listPN = phieuNhapService.getAllPhieuNhap();
        loadPhieuNhap(listPN);

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
//        SimpleDateFormat ngayNhap= new SimpleDateFormat("dd-mm-yyyy");
//        PhieuNhap phieuNhap = new PhieuNhap();
//        phieuNhap.setMa(txtMaPN.getText());
//        phieuNhap.setNhaCungCap((NhaCungCap) cbbNCC.getSelectedItem());
//        try {
//            phieuNhap.setNgayNhap(ngayNhap.parse(txtNgayNhap.getText()));
//        } catch (ParseException ex) {
//            Logger.getLogger(PhieuNhap_Form.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        phieuNhap.setMoTa(txtMoTa.getText());
//        phieuNhapService.(phieuNhap);
//        listPN = phieuNhapService.getAllPhieuNhap();
//        loadPhieuNhap(listPN);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        String ten = txtSearch.getText();
        List<PhieuNhap> listSearch = phieuNhapService.listSearch(ten);
        loadPhieuNhap(listSearch);
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        String id = txtId.getText();
        phieuNhapService.xoa(id);
        listPN = phieuNhapService.getAllPhieuNhap();
        loadPhieuNhap(listPN);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tablePhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePhieuNhapMouseClicked
        int row = tablePhieuNhap.getSelectedRow();
        PhieuNhap phieuNhap = phieuNhapService.getAllPhieuNhap().get(row);
//        SimpleDateFormat ngayNhap = new SimpleDateFormat("dd-mm-yyyy");
        txtId.setText(phieuNhap.getId());
        txtMaPN.setText(phieuNhap.getMoTa());
        txtNgayNhap.setText(String.valueOf(phieuNhap.getNgayNhap()));
//        cbbNCC=phieuNhap.getNhaCungCap()
        txtId.setText(phieuNhap.getId());
    }//GEN-LAST:event_tablePhieuNhapMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.ButtonDesign.Button btnInBaoCao1;
    private View.ButtonDesign.Button btnInBaoCao3;
    private View.ButtonDesign.Button btnNgayKetThuc;
    private View.ButtonDesign.Button btnSua;
    private View.ButtonDesign.Button btnThem;
    private View.ButtonDesign.Button btnXoa;
    private View.DesignComponent.Combobox cbbNCC;
    private View.DesignComponent.Combobox cbbTTNCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private javax.swing.JScrollPane jScrollPane1;
    private View.DateChooser.DateChooser ngayNhap;
    private javax.swing.JPanel pagePanel;
    private View.DesignComponent.Table tablePhieuNhap;
    private View.ButtonDesign.Button txtClear;
    private View.DesignComponent.TextField txtId;
    private View.DesignComponent.TextField txtMaPN;
    private View.DesignComponent.TextField txtMoTa;
    private View.DesignComponent.TextField txtNgayNhap;
    private View.DesignComponent.TextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
