/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.ScrollBarCustom;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import service.KhachHangService;
import service.impl.KhachHangServiceImpl;

/**
 *
 * @author quanc
 */
public class KhachHang_Form extends javax.swing.JPanel {

    private final KhachHangService _khachHangService;
    private List<KhachHang> _lstAllKhachHang;
    private List<KhachHang> _lstKhachHang;
   
    public KhachHang_Form() {
        initComponents();
        this.tblKhachHang.setRowHeight(59);
        this.tblKhachHang.setBackground(Color.white);
        this.jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        Icon iconbtnNgayBatDau = new ImageIcon("image/icons8_schedule_30px.png");
        this.btnNgaySinh.setIcon(iconbtnNgayBatDau);
        
        _khachHangService = new KhachHangServiceImpl();
        _lstAllKhachHang = _khachHangService.selectAll();
        _lstKhachHang = _lstAllKhachHang;
        loadTable(_lstKhachHang);
    }
    
    private void loadTable(List<KhachHang> lstKhachHang) {
        DefaultTableModel dtm = (DefaultTableModel) tblKhachHang.getModel();
        dtm.setRowCount(0);
        for (KhachHang khachHang : lstKhachHang) {
            dtm.addRow(khachHang.toDataRow());
        }
    }
    
    private KhachHang getForm() {
        String id = txtId.getText();
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        String tenDem = txtTenDem.getText().trim();
        String ho = txtHo.getText().trim();
        String ngaySinhStr = txtNgaySinh.getText().trim();
        String SDT = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String gioiTinh = rdoNam.isSelected() ? "Nam" : "Nữ";

        Date ngaySinh = null;
        if (!ngaySinhStr.isBlank()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                ngaySinh = format.parse(ngaySinhStr);
            } catch (ParseException ex) {
                Logger.getLogger(KhachHang_Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return new KhachHang(id.isBlank() ? null : id, ma, ten, tenDem, ho, gioiTinh, ngaySinh, SDT, 0, diaChi);
    }

    private void setForm(KhachHang khachHang) {
        txtId.setText(khachHang.getId());
        txtMa.setText(khachHang.getMa());
        txtTen.setText(khachHang.getTen());
        txtTenDem.setText(khachHang.getTenDem());
        txtHo.setText(khachHang.getHo());
        txtSDT.setText(khachHang.getSdt());
        txtDiaChi.setText(khachHang.getDiaChi());
        String ngaySinh = "";
        if (khachHang.getNgaySinh() != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            ngaySinh = format.format(khachHang.getNgaySinh());
        }
        txtNgaySinh.setText(ngaySinh);
        if (khachHang.getGioiTinh().equals("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        lblDiem.setText(khachHang.getDiemTichLuy() + "");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ngaysinh = new View.DateChooser.DateChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        txtSearch = new View.DesignComponent.TextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new View.DesignComponent.Table();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtId = new View.DesignComponent.TextField();
        txtTen = new View.DesignComponent.TextField();
        txtMa = new View.DesignComponent.TextField();
        txtTenDem = new View.DesignComponent.TextField();
        btnUpdate = new View.ButtonDesign.Button();
        btnAdd = new View.ButtonDesign.Button();
        btnClear = new View.ButtonDesign.Button();
        jSeparator1 = new javax.swing.JSeparator();
        txtNgaySinh = new View.DesignComponent.TextField();
        btnNgaySinh = new View.ButtonDesign.Button();
        txtHo = new View.DesignComponent.TextField();
        jLabel3 = new javax.swing.JLabel();
        rdoNam = new View.ComboBoxDesign.RadioButtonCustom();
        rdoNu = new View.ComboBoxDesign.RadioButtonCustom();
        txtSDT = new View.DesignComponent.TextField();
        txtDiaChi = new View.DesignComponent.TextField();
        jLabel4 = new javax.swing.JLabel();
        lblDiem = new javax.swing.JLabel();
        btnQRCode = new View.ButtonDesign.Button();

        ngaysinh.setForeground(new java.awt.Color(47, 55, 90));
        ngaysinh.setTextRefernce(txtNgaySinh);

        setBackground(new java.awt.Color(11, 20, 55));

        jPanelBourder1.setBackground(new java.awt.Color(17, 28, 68));

        txtSearch.setBackground(new java.awt.Color(17, 28, 68));
        txtSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSearch.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSearch.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        txtSearch.setLabelText("Tìm kiếm theo tên, mã, sđt");
        txtSearch.setLineColor(new java.awt.Color(255, 255, 255));
        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(104, 143, 222));
        jLabel2.setText("List of Customer");

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
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 539, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addContainerGap()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBourder3.setBackground(new java.awt.Color(47, 55, 90));

        jPanelBourder2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 395));

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKhachHang.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblKhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1322, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1310, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .addContainerGap()))
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
                .addComponent(jPanelBourder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Management Book - Quản Lý Khách Hàng");

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

        txtTen.setBackground(new java.awt.Color(47, 55, 90));
        txtTen.setForeground(new java.awt.Color(255, 255, 255));
        txtTen.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTen.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTen.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTen.setLabelText("Tên");
        txtTen.setLineColor(new java.awt.Color(255, 255, 255));

        txtMa.setBackground(new java.awt.Color(47, 55, 90));
        txtMa.setForeground(new java.awt.Color(255, 255, 255));
        txtMa.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMa.setLabelText("Mã Khách Hàng");
        txtMa.setLineColor(new java.awt.Color(255, 255, 255));

        txtTenDem.setBackground(new java.awt.Color(47, 55, 90));
        txtTenDem.setForeground(new java.awt.Color(255, 255, 255));
        txtTenDem.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTenDem.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTenDem.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTenDem.setLabelText("Tên Đệm");
        txtTenDem.setLineColor(new java.awt.Color(255, 255, 255));

        btnUpdate.setBackground(new java.awt.Color(31, 31, 111));
        btnUpdate.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Cập Nhật");
        btnUpdate.setFocusable(false);
        btnUpdate.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(31, 31, 111));
        btnAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Tạo Mới");
        btnAdd.setFocusable(false);
        btnAdd.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(31, 31, 111));
        btnClear.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Làm Mới");
        btnClear.setFocusable(false);
        btnClear.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        txtNgaySinh.setBackground(new java.awt.Color(47, 55, 90));
        txtNgaySinh.setForeground(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtNgaySinh.setLabelText("Ngày Sinh");
        txtNgaySinh.setLineColor(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setMargin(new java.awt.Insets(2, 10, 2, 6));

        btnNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgaySinhActionPerformed(evt);
            }
        });

        txtHo.setBackground(new java.awt.Color(47, 55, 90));
        txtHo.setForeground(new java.awt.Color(255, 255, 255));
        txtHo.setCaretColor(new java.awt.Color(255, 255, 255));
        txtHo.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtHo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtHo.setLabelText("Họ");
        txtHo.setLineColor(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Giới Tính");

        rdoNam.setBackground(new java.awt.Color(47, 55, 90));
        buttonGroup1.add(rdoNam);
        rdoNam.setForeground(new java.awt.Color(255, 255, 255));
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(47, 55, 90));
        buttonGroup1.add(rdoNu);
        rdoNu.setForeground(new java.awt.Color(255, 255, 255));
        rdoNu.setText("Nữ");

        txtSDT.setBackground(new java.awt.Color(47, 55, 90));
        txtSDT.setForeground(new java.awt.Color(255, 255, 255));
        txtSDT.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSDT.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSDT.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtSDT.setLabelText("SĐT");
        txtSDT.setLineColor(new java.awt.Color(255, 255, 255));

        txtDiaChi.setBackground(new java.awt.Color(47, 55, 90));
        txtDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        txtDiaChi.setCaretColor(new java.awt.Color(255, 255, 255));
        txtDiaChi.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtDiaChi.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtDiaChi.setLabelText("Địa chỉ");
        txtDiaChi.setLineColor(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Điểm Tích Lũy :");

        lblDiem.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblDiem.setForeground(new java.awt.Color(255, 255, 255));
        lblDiem.setText("0");

        btnQRCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQRCodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(824, 824, 824)
                        .addComponent(jLabel4)
                        .addGap(30, 30, 30)
                        .addComponent(lblDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanelBourder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(26, 26, 26)
                                            .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(30, 30, 30)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addComponent(jSeparator1))
                    .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQRCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(btnQRCode, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(lblDiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        KhachHang khachHang = getForm();
        if (khachHang.getId() == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn khách hàng");
            return;
        }

        _khachHangService.updateKhachHang(khachHang);
        JOptionPane.showMessageDialog(this, "update successfully");
        _lstAllKhachHang = _khachHangService.selectAll();
        _lstKhachHang = _lstAllKhachHang;
        loadTable(_lstKhachHang);
        clear();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        KhachHang khachHang = getForm();
        if (khachHang.getId() != null) {
            JOptionPane.showMessageDialog(this, "Clear form trước khi thêm");
            return;
        }

        _khachHangService.insertKhachHang(khachHang);
        JOptionPane.showMessageDialog(this, "insert successfully");
        _lstAllKhachHang = _khachHangService.selectAll();
        _lstKhachHang = _lstAllKhachHang;
        loadTable(_lstKhachHang);
        clear();
    }//GEN-LAST:event_btnAddActionPerformed

    private void clear() {
        txtDiaChi.setText("");
        txtHo.setText("");
        txtId.setText("");
        txtMa.setText("");
        txtNgaySinh.setText("");
        txtSDT.setText("");
        txtSearch.setText("");
        txtTen.setText("");
        txtTenDem.setText("");
        rdoNam.setSelected(true);
        lblDiem.setText("0");
    }
    
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgaySinhActionPerformed
        ngaysinh.showPopup();
    }//GEN-LAST:event_btnNgaySinhActionPerformed

    private void btnQRCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQRCodeActionPerformed
        btnQRCode.setEnabled(false);
        CamJFrame cam = new CamJFrame();
        cam.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                closedCam(cam);
                btnQRCode.setEnabled(true);
            }
        });
        cam.setTitle("QR/ BarCode Scanner");
        cam.setVisible(true);
        cam.btnCapture.setEnabled(false);

        Thread th = new Thread(() -> {
            try {
                cam.webcam.open();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Không thể mở camera");
                closedCam(cam);
            }
            while (true) {
                Image image = cam.getImage();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Result result = null;
                LuminanceSource source = new BufferedImageLuminanceSource((BufferedImage) image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException ex) {
                    //not result
                }
                if (result != null) {
                    fillByCode(result + "");
                    closedCam(cam);
                    btnQRCode.setEnabled(true);
                }

            }
        });
        th.start();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean x = true;
                while (x) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if (!cam.isFocusOwner()) {
                        closedCam(cam);
                        btnQRCode.setEnabled(true);
                        x = false;
                    }

                }
            }
        });
        thread.start();
    }//GEN-LAST:event_btnQRCodeActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        String keyword = txtSearch.getText().trim();
        _lstKhachHang = _khachHangService.searchKhachHangByKeyWord(_lstAllKhachHang, keyword);
        loadTable(_lstKhachHang);
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        int row = tblKhachHang.getSelectedRow();
        if (row == - 1) {
            return;
        }
        setForm(_lstKhachHang.get(row));
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void closedCam(CamJFrame cam) {
        cam.webcam.close();
        cam.dispose();
    }

    private void fillByCode(String info) {
        clear();
        String[] infoStr = info.split("\\|");
        String hoTen = infoStr[2];

        String[] hoTenStr = hoTen.split(" ");
        String ho = "";
        String ten = "";
        String tenDem = "";
        switch (hoTenStr.length) {
            case 1 -> ten = hoTenStr[0];
            case 2 -> {
                ho = hoTenStr[0];
                ten = hoTenStr[1];
            }
            default -> {
                ho = hoTenStr[0];
                ten = hoTenStr[hoTenStr.length - 1];
                for (int i = 1; i < hoTenStr.length - 1; i++) {
                    tenDem = hoTenStr[i] + " ";
                }
            }
        }
        txtHo.setText(ho);
        txtTen.setText(ten);
        txtTenDem.setText(tenDem);

        String diaChi = infoStr[5];
        txtDiaChi.setText(diaChi);
        String gioiTinh = infoStr[4].equals("Nam") ? "Nam" : "Nữ";
        if (gioiTinh.equals("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

        String ngay = infoStr[3].substring(0, 2);
        String thang = infoStr[3].substring(2, 4);
        String nam = infoStr[3].substring(4);
        txtNgaySinh.setText(ngay + "-" + thang + "-" + nam);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.ButtonDesign.Button btnAdd;
    private View.ButtonDesign.Button btnClear;
    private View.ButtonDesign.Button btnNgaySinh;
    private View.ButtonDesign.Button btnQRCode;
    private View.ButtonDesign.Button btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDiem;
    private View.DateChooser.DateChooser ngaysinh;
    private View.ComboBoxDesign.RadioButtonCustom rdoNam;
    private View.ComboBoxDesign.RadioButtonCustom rdoNu;
    private View.DesignComponent.Table tblKhachHang;
    private View.DesignComponent.TextField txtDiaChi;
    private View.DesignComponent.TextField txtHo;
    private View.DesignComponent.TextField txtId;
    private View.DesignComponent.TextField txtMa;
    private View.DesignComponent.TextField txtNgaySinh;
    private View.DesignComponent.TextField txtSDT;
    private View.DesignComponent.TextField txtSearch;
    private View.DesignComponent.TextField txtTen;
    private View.DesignComponent.TextField txtTenDem;
    // End of variables declaration//GEN-END:variables
}
