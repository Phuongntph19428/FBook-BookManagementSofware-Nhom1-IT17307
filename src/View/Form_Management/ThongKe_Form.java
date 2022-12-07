/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.ThongBao;
import custommodel.CustomThuChi;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import model.NhanVien;
import service.KhachHangService;
import service.NhanVienService;
import service.ThongKeService;
import service.impl.KhachHangServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.ThongKeServiceImpl;

/**
 *
 * @author ppolo
 */
public class ThongKe_Form extends javax.swing.JPanel {

    private final NhanVienService _nhanVienService;
    private List<NhanVien> _lstNhanVien;

    private final KhachHangService _khachHangService;
    private List<KhachHang> _lstKhachHang;

    private final ThongKeService _thongKeService;
    private List<CustomThuChi> _lstThuChi;

    public ThongKe_Form() {
        initComponents();
        Icon icon = new ImageIcon("image/icons8_schedule_30px.png");
        btnNgayBatDau.setIcon(icon);
        btnNgayKetThuc.setIcon(icon);

        cbbNVorKH.setEnabled(false);
        _nhanVienService = new NhanVienServiceImpl();
        _khachHangService = new KhachHangServiceImpl();
        _thongKeService = new ThongKeServiceImpl();

        _lstNhanVien = _nhanVienService.getAllNhanVien();
        _lstKhachHang = _khachHangService.selectAll();
    }

    private void loadCbbNhanVien() {
        cbbNVorKH.removeAllItems();
        for (NhanVien nhanVien : _lstNhanVien) {
            cbbNVorKH.addItem(nhanVien);
        }
    }

    private void loadCbbKhachHang() {
        cbbNVorKH.removeAllItems();
        for (KhachHang khachHang : _lstKhachHang) {
            cbbNVorKH.addItem(khachHang);
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

        ngayBatDau = new View.DateChooser.DateChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        ngayKetThuc = new View.DateChooser.DateChooser();
        jLabel4 = new javax.swing.JLabel();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThuChi = new View.DesignComponent.Table();
        lblTongThu = new javax.swing.JLabel();
        lblTongChi = new javax.swing.JLabel();
        txtTuNgay = new View.DesignComponent.TextField();
        btnNgayBatDau = new View.ButtonDesign.Button();
        jLabel7 = new javax.swing.JLabel();
        btnNgayKetThuc = new View.ButtonDesign.Button();
        txtDenNgay = new View.DesignComponent.TextField();
        jLabel1 = new javax.swing.JLabel();
        cbbTheo = new View.ComboBoxDesign.ComboBoxSuggestion();
        jLabel8 = new javax.swing.JLabel();
        cbbNVorKH = new View.ComboBoxDesign.ComboBoxSuggestion();
        label = new javax.swing.JLabel();
        rdoTatCa = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        rdoTienVao = new javax.swing.JRadioButton();
        rdoTienRa = new javax.swing.JRadioButton();
        btnTimKiem = new View.ButtonDesign.Button();
        btnRefresh = new View.ButtonDesign.Button();

        ngayBatDau.setForeground(new java.awt.Color(47, 55, 90));
        ngayBatDau.setTextRefernce(txtTuNgay);

        ngayKetThuc.setForeground(new java.awt.Color(47, 55, 90));
        ngayKetThuc.setTextRefernce(txtDenNgay);

        setBackground(new java.awt.Color(11, 20, 55));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(104, 143, 222));
        jLabel4.setText("Thống kê doanh thu");

        jPanelBourder3.setBackground(new java.awt.Color(47, 55, 90));

        jPanelBourder2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 395));

        tblThuChi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Loại giao dịch", "Mã giao dịch", "Ngày giao dịch", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThuChi.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblThuChi.setRowHeight(40);
        tblThuChi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblThuChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuChiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThuChi);

        lblTongThu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongThu.setForeground(new java.awt.Color(0, 153, 51));
        lblTongThu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongThu.setText("Tổng thu:");

        lblTongChi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongChi.setForeground(new java.awt.Color(255, 0, 0));
        lblTongChi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongChi.setText("Tổng chi:");

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanelBourder2Layout.createSequentialGroup()
                .addContainerGap(754, Short.MAX_VALUE)
                .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTongChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTongThu, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder2Layout.createSequentialGroup()
                .addContainerGap(436, Short.MAX_VALUE)
                .addComponent(lblTongThu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTongChi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(149, Short.MAX_VALUE)))
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
                .addContainerGap(17, Short.MAX_VALUE))
        );

        txtTuNgay.setBackground(new java.awt.Color(47, 55, 90));
        txtTuNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtTuNgay.setText("");
        txtTuNgay.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTuNgay.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTuNgay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTuNgay.setLabelText("Từ ngày");
        txtTuNgay.setLineColor(new java.awt.Color(255, 255, 255));
        txtTuNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTuNgayActionPerformed(evt);
            }
        });

        btnNgayBatDau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image_Hub/icons8_camera_30px.png"))); // NOI18N
        btnNgayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayBatDauActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Theo:");

        btnNgayKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image_Hub/icons8_camera_30px.png"))); // NOI18N
        btnNgayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayKetThucActionPerformed(evt);
            }
        });

        txtDenNgay.setBackground(new java.awt.Color(47, 55, 90));
        txtDenNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtDenNgay.setText("");
        txtDenNgay.setCaretColor(new java.awt.Color(255, 255, 255));
        txtDenNgay.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtDenNgay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtDenNgay.setLabelText("Đến ngày");
        txtDenNgay.setLineColor(new java.awt.Color(255, 255, 255));
        txtDenNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDenNgayActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("-");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cbbTheo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Nhân viên", "Khách hàng" }));
        cbbTheo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        cbbTheo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbbTheoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbbTheoPopupMenuWillBecomeVisible(evt);
            }
        });
        cbbTheo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTheoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Thời gian:");

        cbbNVorKH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Nhân viên", "Khách hàng" }));
        cbbNVorKH.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        cbbNVorKH.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbbNVorKHPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbbNVorKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNVorKHActionPerformed(evt);
            }
        });

        label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label.setForeground(new java.awt.Color(255, 255, 255));
        label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label.setText("Nhân viên:");

        buttonGroup1.add(rdoTatCa);
        rdoTatCa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoTatCa.setForeground(new java.awt.Color(255, 255, 255));
        rdoTatCa.setSelected(true);
        rdoTatCa.setText("Tất cả");
        rdoTatCa.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        rdoTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTatCaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Loại giao dịch:");

        buttonGroup1.add(rdoTienVao);
        rdoTienVao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoTienVao.setForeground(new java.awt.Color(255, 255, 255));
        rdoTienVao.setText("Tiền vào");
        rdoTienVao.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        buttonGroup1.add(rdoTienRa);
        rdoTienRa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoTienRa.setForeground(new java.awt.Color(255, 255, 255));
        rdoTienRa.setText("Tiền ra");
        rdoTienRa.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        btnTimKiem.setBackground(new java.awt.Color(31, 31, 111));
        btnTimKiem.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setFocusable(false);
        btnTimKiem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(31, 31, 111));
        btnRefresh.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh");
        btnRefresh.setFocusable(false);
        btnRefresh.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshMouseClicked(evt);
            }
        });
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbTheo, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(label)
                                .addGap(18, 18, 18)
                                .addComponent(cbbNVorKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(rdoTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(rdoTienVao, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(rdoTienRa, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(jPanelBourder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(25, 25, 25)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbbTheo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbNVorKH, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label)
                    .addComponent(jLabel10)
                    .addComponent(rdoTatCa)
                    .addComponent(rdoTienVao)
                    .addComponent(rdoTienRa))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8))
                            .addComponent(btnNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDenNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNgayKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(620, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(257, 257, 257)
                    .addComponent(jPanelBourder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTuNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTuNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTuNgayActionPerformed

    private void txtDenNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDenNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDenNgayActionPerformed

    private void cbbTheoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbbTheoPopupMenuWillBecomeInvisible
        switch (cbbTheo.getSelectedIndex()) {
            case 0 -> {
                rdoTatCa.setEnabled(true);
                cbbNVorKH.setEnabled(false);
                rdoTienRa.setEnabled(true);
                rdoTienVao.setEnabled(true);
                rdoTatCa.setSelected(true);
            }
            case 1 -> {
                loadCbbNhanVien();
                label.setText("Nhân viên:");
                cbbNVorKH.setEnabled(true);
                rdoTatCa.setEnabled(false);
                rdoTienRa.setEnabled(false);
                rdoTienVao.setEnabled(false);
                rdoTatCa.setSelected(true);
            }
            default -> {
                loadCbbKhachHang();
                label.setText("Khách hàng:");
                cbbNVorKH.setEnabled(true);
                rdoTatCa.setEnabled(false);
                rdoTienRa.setEnabled(false);
                rdoTienVao.setEnabled(false);
                rdoTatCa.setSelected(true);
            }
        }

    }//GEN-LAST:event_cbbTheoPopupMenuWillBecomeInvisible

    private void cbbNVorKHPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbbNVorKHPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbNVorKHPopupMenuWillBecomeInvisible

    private void btnNgayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayBatDauActionPerformed
        ngayBatDau.showPopup();
    }//GEN-LAST:event_btnNgayBatDauActionPerformed

    private void btnNgayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayKetThucActionPerformed
        ngayKetThuc.showPopup();
    }//GEN-LAST:event_btnNgayKetThucActionPerformed

    private void cbbNVorKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNVorKHActionPerformed

    }//GEN-LAST:event_cbbNVorKHActionPerformed

    private void cbbTheoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTheoActionPerformed

    }//GEN-LAST:event_cbbTheoActionPerformed

    private void rdoTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTatCaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoTatCaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String tuNgayStr = txtTuNgay.getText();
        String denNgayStr = txtDenNgay.getText();

        if (tuNgayStr.isBlank() || denNgayStr.isBlank()) {
            ThongBao.showNoti_Error(this, "Bạn hãy chọn ngày thống kê");
            return;
        }

        Date tuNgay = null;
        Date denNgay = null;
        try {
            tuNgay = sdf.parse(tuNgayStr);
            denNgay = sdf.parse(denNgayStr);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        if (tuNgay == null || denNgay == null) {
            ThongBao.showNoti_Error(this, "Tạm thời không thể thống kê. Mời bạn thử lại");
            return;
        }

        switch (cbbTheo.getSelectedIndex()) {
            case 0 -> {
                if (rdoTatCa.isSelected()) {
                    selectAll(tuNgay, denNgay);
                } else if (rdoTienRa.isSelected()) {
                    selectAllChi(tuNgay, denNgay);
                } else {
                    selectAllThu(tuNgay, denNgay);
                }
            }
            case 1 ->
                selectAllByNhanVien(tuNgay, denNgay);
            default ->
                selectAllByKhachHang(tuNgay, denNgay);
        }

        loadTableThuChi();

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private final DecimalFormat df = new DecimalFormat("#,###");

    private void loadTableThuChi() {
        DefaultTableModel dtm = (DefaultTableModel) tblThuChi.getModel();
        dtm.setRowCount(0);
        BigDecimal tienThu = BigDecimal.ZERO;
        BigDecimal tienChi = BigDecimal.ZERO;
        for (CustomThuChi customThuChi : _lstThuChi) {
            dtm.addRow(customThuChi.toDataRow());
            if (customThuChi.getLoaiGiaoDich().equals("Hoa don")) {
                tienThu = tienThu.add(customThuChi.getTongTien());
            } else {
                tienChi = tienChi.add(customThuChi.getTongTien());
            }
        }
        lblTongThu.setText("<html>Tổng thu: " + df.format(tienThu) + "<sup>vnđ</sup></html>");
        lblTongChi.setText("<html>Tổng chi: " + df.format(tienChi) + "<sup>vnđ</sup></html>");
    }

    private void selectAll(Date tuNgay, Date denNgay) {
        _lstThuChi = _thongKeService.getAll(tuNgay, denNgay);
    }

    private void selectAllChi(Date tuNgay, Date denNgay) {
        _lstThuChi = _thongKeService.getAllChi(tuNgay, denNgay);
    }

    private void selectAllThu(Date tuNgay, Date denNgay) {
        _lstThuChi = _thongKeService.getAllThu(tuNgay, denNgay);
    }

    private void selectAllByNhanVien(Date tuNgay, Date denNgay) {
        int row = cbbNVorKH.getSelectedIndex();
        if (row == -1) {
            ThongBao.showNoti_Error(this, "Bạn hãy chọn nhân viên trước");
            return;
        }
        _lstThuChi = _thongKeService.getAllByNhanVien(_lstNhanVien.get(row), tuNgay, denNgay);
    }

    private void selectAllByKhachHang(Date tuNgay, Date denNgay) {
        int row = cbbNVorKH.getSelectedIndex();
        if (row == -1) {
            ThongBao.showNoti_Error(this, "Bạn hãy chọn khách hàng trước");
            return;
        }
        _lstThuChi = _thongKeService.getAllByKhachhang(_lstKhachHang.get(row), tuNgay, denNgay);
    }

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked

    }//GEN-LAST:event_btnTimKiemMouseClicked

    private void tblThuChiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuChiMouseClicked

    }//GEN-LAST:event_tblThuChiMouseClicked

    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshMouseClicked

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        cbbTheo.setSelectedIndex(0);
        rdoTatCa.setEnabled(true);
        cbbNVorKH.setEnabled(false);
        rdoTienRa.setEnabled(true);
        rdoTienVao.setEnabled(true);
        rdoTatCa.setSelected(true);
        _lstThuChi = new ArrayList<>();
        loadTableThuChi();
        _lstKhachHang = _khachHangService.selectAll();
        _lstNhanVien = _nhanVienService.getAllNhanVien();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void cbbTheoPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbbTheoPopupMenuWillBecomeVisible

    }//GEN-LAST:event_cbbTheoPopupMenuWillBecomeVisible


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.ButtonDesign.Button btnNgayBatDau;
    private View.ButtonDesign.Button btnNgayKetThuc;
    private View.ButtonDesign.Button btnRefresh;
    private View.ButtonDesign.Button btnTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private View.ComboBoxDesign.ComboBoxSuggestion cbbNVorKH;
    private View.ComboBoxDesign.ComboBoxSuggestion cbbTheo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lblTongChi;
    private javax.swing.JLabel lblTongThu;
    private View.DateChooser.DateChooser ngayBatDau;
    private View.DateChooser.DateChooser ngayKetThuc;
    private javax.swing.JRadioButton rdoTatCa;
    private javax.swing.JRadioButton rdoTienRa;
    private javax.swing.JRadioButton rdoTienVao;
    private View.DesignComponent.Table tblThuChi;
    private View.DesignComponent.TextField txtDenNgay;
    private View.DesignComponent.TextField txtTuNgay;
    // End of variables declaration//GEN-END:variables
}
