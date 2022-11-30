/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.DesignComponent.Notification;
import View.ManagementBookForm;
import View.ScrollBarCustom;
import java.awt.Color;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.KhuyenMai;

import service.IKhuyenMaiServicer;
import service.impl.KhuyenMaiServicer;
import View.Form_Management.KhuyenMaiChiTiet_Form;

/**
 *
 * @author quanc
 */
public class KhuyenMai_Form extends javax.swing.JPanel {

//    private List<Button> listBtn = new ArrayList<>();
    private List<KhuyenMai> listKhuyenMai = new ArrayList<>();
    private IKhuyenMaiServicer iKhuyenMaiServicer;
    private String ma = "";
    private KhuyenMaiChiTiet_Form form;

    public KhuyenMai_Form() {
        initComponents();
        iKhuyenMaiServicer = new KhuyenMaiServicer();

        String columns[] = {"Id", "Mã Khuyến Mại", "Tên Khuyến Mãi", "Chiết Khấu", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Trạng Thái", "Mô Tả"};
        Object rows[][] = {};
        this.tbKhuyenMai.setModel(new DefaultTableModel(rows, columns));
        tbKhuyenMai.setDefaultEditor(Object.class, null);
        tbKhuyenMai.setBackground(Color.WHITE);
        this.tbKhuyenMai.setRowHeight(50);
        this.tbKhuyenMai.setBackground(Color.white);

        Icon iconbtnNgayBatDau = new ImageIcon("image/icons8_schedule_30px.png");
        this.btnNgayBatDau.setIcon(iconbtnNgayBatDau);
        this.btnNgayKetThuc.setIcon(iconbtnNgayBatDau);
        this.jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());

        loadTable();
        xoaFrom();
    }

    private void lamMoi() {
        ma = "";
        loadTable();
    }

    private void loadTable() {
        listKhuyenMai = iKhuyenMaiServicer.selectAll(ma);
        DefaultTableModel dtm = (DefaultTableModel) tbKhuyenMai.getModel();
        dtm.setRowCount(0);
        for (KhuyenMai km : listKhuyenMai) {
            dtm.addRow(km.toDaTaRow());
        }

    }

    private void xuatFile() {
        KhuyenMai km = new KhuyenMai();

    }

    private void fillTable(int row) {
        KhuyenMai km = listKhuyenMai.get(row);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        txtId.setText(km.getId());
        txtMaKM.setText(km.getMa());
        txtTenKM.setText(km.getTen());
        txtChietKhau.setText(String.valueOf(km.getChietKhau()));
        txtNgayBD.setText(String.valueOf(df.format(km.getNgayBatDau())));
        txtNgayKT.setText(String.valueOf(df.format(km.getNgayKetThuc())));
        String trangThai = String.valueOf(km.getTrangThai() == 1 ? "Đang hoạt động" : "Ngừng hoạt động");
        if (trangThai.equals("Đang hoạt động")) {
            rdDangHD.setSelected(true);

        } else {
            rdNgungHD.setSelected(true);

        }
        txtMoTa.setText(km.getMoTa());
    }

    private void xoaFrom() {
        txtChietKhau.setText("");
        txtId.setText("");
        txtMaKM.setText("");
        txtMoTa.setText("");
        txtNgayBD.setText("");
        txtNgayKT.setText("");
        txtTenKM.setText("");
        btgrTrangThai.clearSelection();
        txtTimKiem.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateNgayBatDau = new View.DateChooser.DateChooser();
        dateNgayKetThuc = new View.DateChooser.DateChooser();
        btgrTrangThai = new javax.swing.ButtonGroup();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        txtTimKiem = new View.DesignComponent.TextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnTimKiem = new View.ButtonDesign.Button();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhuyenMai = new View.DesignComponent.Table();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtId = new View.DesignComponent.TextField();
        txtTenKM = new View.DesignComponent.TextField();
        txtMaKM = new View.DesignComponent.TextField();
        txtChietKhau = new View.DesignComponent.TextField();
        btnSua = new View.ButtonDesign.Button();
        btnThem = new View.ButtonDesign.Button();
        jSeparator1 = new javax.swing.JSeparator();
        txtNgayBD = new View.DesignComponent.TextField();
        txtNgayKT = new View.DesignComponent.TextField();
        btnNgayBatDau = new View.ButtonDesign.Button();
        btnNgayKetThuc = new View.ButtonDesign.Button();
        jLabel3 = new javax.swing.JLabel();
        rdNgungHD = new View.ComboBoxDesign.RadioButtonCustom();
        rdDangHD = new View.ComboBoxDesign.RadioButtonCustom();
        txtMoTa = new View.DesignComponent.TextField();
        btnXoa = new View.ButtonDesign.Button();
        btnXoaFrom1 = new View.ButtonDesign.Button();

        dateNgayBatDau.setForeground(new java.awt.Color(47, 55, 90));
        dateNgayBatDau.setTextRefernce(txtNgayBD);

        dateNgayKetThuc.setForeground(new java.awt.Color(47, 55, 90));
        dateNgayKetThuc.setTextRefernce(txtNgayKT);

        setBackground(new java.awt.Color(11, 20, 55));

        jPanelBourder1.setBackground(new java.awt.Color(17, 28, 68));

        txtTimKiem.setBackground(new java.awt.Color(17, 28, 68));
        txtTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        txtTimKiem.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTimKiem.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTimKiem.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        txtTimKiem.setLabelText("Tìm Kiếm Theo Tên");
        txtTimKiem.setLineColor(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(104, 143, 222));
        jLabel2.setText("List of Discount Code");

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

        btnTimKiem.setBackground(new java.awt.Color(31, 31, 111));
        btnTimKiem.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.setFocusable(false);
        btnTimKiem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBourder1Layout = new javax.swing.GroupLayout(jPanelBourder1);
        jPanelBourder1.setLayout(jPanelBourder1Layout);
        jPanelBourder1Layout.setHorizontalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 469, Short.MAX_VALUE)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBourder1Layout.setVerticalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBourder3.setBackground(new java.awt.Color(47, 55, 90));

        jPanelBourder2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 395));

        tbKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
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
        tbKhuyenMai.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tbKhuyenMai.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tbKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhuyenMai);

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1311, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanelBourder3Layout = new javax.swing.GroupLayout(jPanelBourder3);
        jPanelBourder3.setLayout(jPanelBourder3Layout);
        jPanelBourder3Layout.setHorizontalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBourder3Layout.setVerticalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Management Book - Quản Lý Khuyến Mãi");

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setPreferredSize(new java.awt.Dimension(1330, 1));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1204, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(47, 55, 90));
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setCaretColor(new java.awt.Color(255, 255, 255));
        txtId.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtId.setLabelText("ID");
        txtId.setLineColor(new java.awt.Color(255, 255, 255));

        txtTenKM.setBackground(new java.awt.Color(47, 55, 90));
        txtTenKM.setForeground(new java.awt.Color(255, 255, 255));
        txtTenKM.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTenKM.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTenKM.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTenKM.setLabelText("Tên Khuyến Mãi");
        txtTenKM.setLineColor(new java.awt.Color(255, 255, 255));

        txtMaKM.setBackground(new java.awt.Color(47, 55, 90));
        txtMaKM.setForeground(new java.awt.Color(255, 255, 255));
        txtMaKM.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMaKM.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMaKM.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMaKM.setLabelText("Mã Khuyến Mãi");
        txtMaKM.setLineColor(new java.awt.Color(255, 255, 255));

        txtChietKhau.setBackground(new java.awt.Color(47, 55, 90));
        txtChietKhau.setForeground(new java.awt.Color(255, 255, 255));
        txtChietKhau.setCaretColor(new java.awt.Color(255, 255, 255));
        txtChietKhau.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtChietKhau.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtChietKhau.setLabelText("Chiết Khấu");
        txtChietKhau.setLineColor(new java.awt.Color(255, 255, 255));

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

        txtNgayBD.setBackground(new java.awt.Color(47, 55, 90));
        txtNgayBD.setForeground(new java.awt.Color(255, 255, 255));
        txtNgayBD.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNgayBD.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNgayBD.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtNgayBD.setLabelText("Ngày Bắt Đầu");
        txtNgayBD.setLineColor(new java.awt.Color(255, 255, 255));
        txtNgayBD.setMargin(new java.awt.Insets(2, 10, 2, 6));

        txtNgayKT.setBackground(new java.awt.Color(47, 55, 90));
        txtNgayKT.setForeground(new java.awt.Color(255, 255, 255));
        txtNgayKT.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNgayKT.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNgayKT.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtNgayKT.setLabelText("Ngày Kết Thúc");
        txtNgayKT.setLineColor(new java.awt.Color(255, 255, 255));

        btnNgayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayBatDauActionPerformed(evt);
            }
        });

        btnNgayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayKetThucActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Trạng Thái");

        rdNgungHD.setBackground(new java.awt.Color(11, 20, 55));
        btgrTrangThai.add(rdNgungHD);
        rdNgungHD.setForeground(new java.awt.Color(255, 255, 255));
        rdNgungHD.setText("Ngừng Hoạt Động");
        rdNgungHD.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        rdDangHD.setBackground(new java.awt.Color(11, 20, 55));
        btgrTrangThai.add(rdDangHD);
        rdDangHD.setForeground(new java.awt.Color(255, 255, 255));
        rdDangHD.setText("Đang Hoạt Động");
        rdDangHD.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        txtMoTa.setBackground(new java.awt.Color(47, 55, 90));
        txtMoTa.setForeground(new java.awt.Color(255, 255, 255));
        txtMoTa.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMoTa.setLabelText("Mô Tả");
        txtMoTa.setLineColor(new java.awt.Color(255, 255, 255));

        btnXoa.setBackground(new java.awt.Color(31, 31, 111));
        btnXoa.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa Khuyến Mại");
        btnXoa.setFocusable(false);
        btnXoa.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnXoaFrom1.setBackground(new java.awt.Color(31, 31, 111));
        btnXoaFrom1.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnXoaFrom1.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaFrom1.setText("Làm Mới");
        btnXoaFrom1.setFocusable(false);
        btnXoaFrom1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnXoaFrom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaFrom1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdDangHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdNgungHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXoaFrom1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanelBourder3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelBourder1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXoaFrom1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rdNgungHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(15, 15, 15)
                                    .addComponent(rdDangHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        int row = tbKhuyenMai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần cập nhập!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        } else {

            if (JOptionPane.showConfirmDialog(this, "Cập nhập", "Xác nhận", JOptionPane.OK_CANCEL_OPTION) == 0) {
                KhuyenMai km = getDaTa();
                if (km == null) {
                    return;
                }
                ManagementBookForm mng = new ManagementBookForm();
                mng.runNotificationSua();
                iKhuyenMaiServicer.update(km);
//                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                loadTable();

            }

        }
        xoaFrom();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        KhuyenMai km = getDaTa();

        if (km == null) {

            return;
        }
//        else if (iKhuyenMaiServicer.findById(txtMaKM.getText().trim()).equals(txtMaKM.getText().trim())) {
//            JOptionPane.showMessageDialog(this, "Trùng mã", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        } 
        for (KhuyenMai km1 : listKhuyenMai) {
            if (km1.getMa().equals(txtMaKM.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Trùng mã Khuyến mại", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);

                return;
            }
        }
        if (JOptionPane.showConfirmDialog(this, "Thêm", "Xác nhận", JOptionPane.OK_CANCEL_OPTION) == 0) {
//            JOptionPane.showMessageDialog(this, "Thêm thành công");
            ManagementBookForm mng = new ManagementBookForm();
            mng.runNotificationThem();
            iKhuyenMaiServicer.insert(km);
            loadTable();
//
//            form = new KhuyenMaiChiTiet_Form();
//            form.loadTableKM();

        }

        xoaFrom();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnNgayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayBatDauActionPerformed
        dateNgayBatDau.showPopup();

    }//GEN-LAST:event_btnNgayBatDauActionPerformed

    private void btnNgayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayKetThucActionPerformed
        dateNgayKetThuc.showPopup();
    }//GEN-LAST:event_btnNgayKetThucActionPerformed

    private void tbKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhuyenMaiMouseClicked
        // TODO add your handling code here:
        int row = tbKhuyenMai.getSelectedRow();
        fillTable(row);
    }//GEN-LAST:event_tbKhuyenMaiMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:

        ma = txtTimKiem.getText().trim();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cần điền mã thể loại để tìm");
            return;
        } else {
            if (iKhuyenMaiServicer.selectAll(ma).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy");
                xoaFrom();
                return;

            }
        }
//        JOptionPane.showMessageDialog(this, "Đã tìm thấy");
        ManagementBookForm mng = new ManagementBookForm();
        mng.runNotificationTim();
        loadTable();
        xoaFrom();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tbKhuyenMai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dóng cần xóa");
            return;
        } else if (JOptionPane.showConfirmDialog(this, "Xóa", "Xác nhận", JOptionPane.OK_CANCEL_OPTION) == 0) {
//            JOptionPane.showMessageDialog(this, "Xóa thành công");
            ManagementBookForm mng = new ManagementBookForm();
            mng.runNotificationXoa();
            String id = txtId.getText().trim();
            iKhuyenMaiServicer.delete(id);
            lamMoi();
            xoaFrom();

        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXoaFrom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaFrom1ActionPerformed
        // TODO add your handling code here:
        xoaFrom();
        lamMoi();

    }//GEN-LAST:event_btnXoaFrom1ActionPerformed

    private KhuyenMai getDaTa() {
        int so = 0;
        if (txtMaKM.getText().trim().length() == 0 || txtTenKM.getText().trim().length() == 0
                || txtNgayBD.getText().trim().length() == 0 || txtNgayKT.getText().trim().length() == 0
                || txtChietKhau.getText().trim().length() == 0
                || txtMoTa.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống, cần điền đầy đủ thông tin!", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (!txtNgayBD.getText().trim().matches("[0-9]{2}-[0-9]{2}-[0-9]{4}+")) {
            JOptionPane.showMessageDialog(this, "Sai định dạng ngày tháng năm! Nhập theo dạng dd-MM-yyyy", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (!txtNgayKT.getText().trim().matches("[0-9]{2}-[0-9]{2}-[0-9]{4}+")) {
            JOptionPane.showMessageDialog(this, "Sai định dạng ngày tháng năm! Nhập lại theo dạng dd-MM-yyyy", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (!txtChietKhau.getText().trim().matches("[-0-9]+")) {
            JOptionPane.showMessageDialog(this, "Chiết khấu phải là số", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (Integer.parseInt(txtChietKhau.getText().trim()) <= so) {
            JOptionPane.showMessageDialog(this, "Chiết khấu không được <=0", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        KhuyenMai km = new KhuyenMai();
        km.setId(txtId.getText().trim());
        km.setMa(txtMaKM.getText().trim());
        km.setTen(txtTenKM.getText().trim());
        km.setChietKhau(Integer.parseInt(txtChietKhau.getText().trim()));
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        try {

            km.setNgayBatDau(df.parse(txtNgayBD.getText().trim()));
            km.setNgayKetThuc(df.parse(txtNgayKT.getText().trim()));

        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMai_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        km.setTrangThai(rdDangHD.isSelected() ? 1 : 0);
        km.setMoTa(txtMoTa.getText().trim());

        return km;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgrTrangThai;
    private View.ButtonDesign.Button btnNgayBatDau;
    private View.ButtonDesign.Button btnNgayKetThuc;
    private View.ButtonDesign.Button btnSua;
    private View.ButtonDesign.Button btnThem;
    private View.ButtonDesign.Button btnTimKiem;
    private View.ButtonDesign.Button btnXoa;
    private View.ButtonDesign.Button btnXoaFrom1;
    private View.DateChooser.DateChooser dateNgayBatDau;
    private View.DateChooser.DateChooser dateNgayKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private View.ComboBoxDesign.RadioButtonCustom rdDangHD;
    private View.ComboBoxDesign.RadioButtonCustom rdNgungHD;
    private View.DesignComponent.Table tbKhuyenMai;
    private View.DesignComponent.TextField txtChietKhau;
    private View.DesignComponent.TextField txtId;
    private View.DesignComponent.TextField txtMaKM;
    private View.DesignComponent.TextField txtMoTa;
    private View.DesignComponent.TextField txtNgayBD;
    private View.DesignComponent.TextField txtNgayKT;
    private View.DesignComponent.TextField txtTenKM;
    private View.DesignComponent.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
