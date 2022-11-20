/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.ScrollBarCustom;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.ChucVu;
import model.NhanVien;
import service.ChucVuService;
import service.NhanVienService;
import service.impl.ChucVuServiceImpl;
import service.impl.NhanVienServiceImpl;

/**
 *
 * @author quanc
 */
public class NhanVien_ChucNang_Form extends javax.swing.JPanel {

    private NhanVienService nhanVienService;
    private ChucVuService chucVuService;
    private List<ChucVu> listChucVu;

    /**
     * Creates new form NhanVien_ChucNang_Form
     */
    public NhanVien_ChucNang_Form() {
        initComponents();
        nhanVienService = new NhanVienServiceImpl();
        chucVuService = new ChucVuServiceImpl();
        listChucVu = chucVuService.getAllChuVu();
        for (int i = 0; i < listChucVu.size(); i++) {
            comboboxChucVu.addItem(listChucVu.get(i).getMoTa());
        }

        Icon icon = new ImageIcon(new ImageIcon("image/nhanvien.jpg").getImage().getScaledInstance(260, 320, Image.SCALE_DEFAULT));
        this.lbAvatar.setIcon(icon);

        Icon iconBtnNgaySinh = new ImageIcon("image/icons8_schedule_30px.png");
        this.btnNgaySinh.setIcon(iconBtnNgaySinh);

    }

    private NhanVien getForm() {
        String maNhanVien = txtMaNhanVien.getText();
        String ten = txtTen.getText();
        String tenDem = txtTenDem.getText();
        String ho = txtHo.getText();
        String email = txtEmail.getText();
        String sdt = txtSDT.getText();
        String diaChi = txtDiaChi.getText();
        String matKhau = txtMatKhau.getText();
        String dateStr = txtNgaySinh.getText();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException ex) {

        }

        String moTaChucVu = comboboxChucVu.getSelectedItem().toString();
        NhanVien nhanvien = new NhanVien();
        nhanvien.setMa(maNhanVien);
        nhanvien.setTen(ten);
        nhanvien.setTenDem(tenDem);
        nhanvien.setHo(ho);
        nhanvien.setEmail(email);
        nhanvien.setSdt(sdt);
        nhanvien.setDiaChi(diaChi);
        nhanvien.setMatKhau(matKhau);
        nhanvien.setNgaySinh(date);
        if (radioButtonCustom1.isSelected()) {
            nhanvien.setGioiTinh("Nam");
        } else {
            nhanvien.setGioiTinh("Nu");
        }

        for (ChucVu chucVu : listChucVu) {
            if (chucVu.getMoTa().equals(moTaChucVu)) {
                nhanvien.setChucVu(chucVu);
            }
        }

        return nhanvien;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TruongThongTin = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        txtID = new View.DesignComponent.TextField();
        txtMaNhanVien = new View.DesignComponent.TextField();
        txtTen = new View.DesignComponent.TextField();
        txtTenDem = new View.DesignComponent.TextField();
        txtHo = new View.DesignComponent.TextField();
        txtSDT = new View.DesignComponent.TextField();
        txtEmail = new View.DesignComponent.TextField();
        jLabel1 = new javax.swing.JLabel();
        txtNgaySinh = new View.DesignComponent.TextField();
        btnNgaySinh = new View.ButtonDesign.Button();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnInBaoCao3 = new View.ButtonDesign.Button();
        btnInBaoCao4 = new View.ButtonDesign.Button();
        btnInBaoCao5 = new View.ButtonDesign.Button();
        txtDiaChi = new View.DesignComponent.TextField();
        txtMatKhau = new View.DesignComponent.TextField();
        comboboxChucVu = new View.DesignComponent.Combobox();
        radioButtonCustom1 = new View.ComboBoxDesign.RadioButtonCustom();
        radioButtonCustom2 = new View.ComboBoxDesign.RadioButtonCustom();
        comboboxTrangThai = new View.DesignComponent.Combobox();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        lbAvatar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnInBaoCao6 = new View.ButtonDesign.Button();
        button2 = new View.ButtonDesign.Button();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(11, 20, 55));
        setLayout(null);

        TruongThongTin.setBackground(new java.awt.Color(11, 20, 55));
        TruongThongTin.setLayout(null);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        TruongThongTin.add(jSeparator2);
        jSeparator2.setBounds(0, 120, 1370, 3);

        jPanelBourder1.setBackground(new java.awt.Color(47, 55, 90));

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(47, 55, 90));
        txtID.setForeground(new java.awt.Color(255, 255, 255));
        txtID.setCaretColor(new java.awt.Color(255, 255, 255));
        txtID.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtID.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtID.setLabelText("ID");
        txtID.setLineColor(new java.awt.Color(255, 255, 255));
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        txtMaNhanVien.setBackground(new java.awt.Color(47, 55, 90));
        txtMaNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        txtMaNhanVien.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMaNhanVien.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMaNhanVien.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMaNhanVien.setLabelText("Mã Nhân Viên");
        txtMaNhanVien.setLineColor(new java.awt.Color(255, 255, 255));

        txtTen.setBackground(new java.awt.Color(47, 55, 90));
        txtTen.setForeground(new java.awt.Color(255, 255, 255));
        txtTen.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTen.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTen.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTen.setLabelText("Tên ");
        txtTen.setLineColor(new java.awt.Color(255, 255, 255));
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        txtTenDem.setBackground(new java.awt.Color(47, 55, 90));
        txtTenDem.setForeground(new java.awt.Color(255, 255, 255));
        txtTenDem.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTenDem.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTenDem.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTenDem.setLabelText("Tên Đệm");
        txtTenDem.setLineColor(new java.awt.Color(255, 255, 255));
        txtTenDem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDemActionPerformed(evt);
            }
        });

        txtHo.setBackground(new java.awt.Color(47, 55, 90));
        txtHo.setForeground(new java.awt.Color(255, 255, 255));
        txtHo.setCaretColor(new java.awt.Color(255, 255, 255));
        txtHo.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtHo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtHo.setLabelText("Họ");
        txtHo.setLineColor(new java.awt.Color(255, 255, 255));
        txtHo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoActionPerformed(evt);
            }
        });

        txtSDT.setBackground(new java.awt.Color(47, 55, 90));
        txtSDT.setForeground(new java.awt.Color(255, 255, 255));
        txtSDT.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSDT.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSDT.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtSDT.setLabelText("Số Điện Thoại");
        txtSDT.setLineColor(new java.awt.Color(255, 255, 255));
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        txtEmail.setBackground(new java.awt.Color(47, 55, 90));
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        txtEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        txtEmail.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtEmail.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtEmail.setLabelText("Email");
        txtEmail.setLineColor(new java.awt.Color(255, 255, 255));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Giới Tính");

        txtNgaySinh.setBackground(new java.awt.Color(47, 55, 90));
        txtNgaySinh.setForeground(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtNgaySinh.setLabelText("Ngày Sinh");
        txtNgaySinh.setLineColor(new java.awt.Color(255, 255, 255));
        txtNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgaySinhActionPerformed(evt);
            }
        });

        btnNgaySinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image_Hub/icons8_camera_30px.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Basic information");

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(824, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        btnInBaoCao3.setBackground(new java.awt.Color(31, 31, 111));
        btnInBaoCao3.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao3.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao3.setText("Tạo Mới");
        btnInBaoCao3.setFocusable(false);
        btnInBaoCao3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao3ActionPerformed(evt);
            }
        });

        btnInBaoCao4.setBackground(new java.awt.Color(31, 31, 111));
        btnInBaoCao4.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao4.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao4.setText("Cập Nhật");
        btnInBaoCao4.setFocusable(false);
        btnInBaoCao4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao4ActionPerformed(evt);
            }
        });

        btnInBaoCao5.setBackground(new java.awt.Color(31, 31, 111));
        btnInBaoCao5.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao5.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao5.setText("Làm Mới");
        btnInBaoCao5.setFocusable(false);
        btnInBaoCao5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao5ActionPerformed(evt);
            }
        });

        txtDiaChi.setBackground(new java.awt.Color(47, 55, 90));
        txtDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        txtDiaChi.setCaretColor(new java.awt.Color(255, 255, 255));
        txtDiaChi.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtDiaChi.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtDiaChi.setLabelText("Địa Chỉ");
        txtDiaChi.setLineColor(new java.awt.Color(255, 255, 255));
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        txtMatKhau.setBackground(new java.awt.Color(47, 55, 90));
        txtMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        txtMatKhau.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMatKhau.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMatKhau.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMatKhau.setLabelText("Mật Khẩu");
        txtMatKhau.setLineColor(new java.awt.Color(255, 255, 255));
        txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauActionPerformed(evt);
            }
        });

        comboboxChucVu.setBackground(new java.awt.Color(47, 55, 90));
        comboboxChucVu.setForeground(new java.awt.Color(255, 255, 255));
        comboboxChucVu.setMaximumRowCount(10);
        comboboxChucVu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        comboboxChucVu.setSelectedIndex(-1);
        comboboxChucVu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        comboboxChucVu.setLabeText("Chức Vụ");
        comboboxChucVu.setLineColor(new java.awt.Color(255, 255, 255));

        radioButtonCustom1.setBackground(new java.awt.Color(47, 55, 90));
        radioButtonCustom1.setForeground(new java.awt.Color(255, 255, 255));
        radioButtonCustom1.setText("Nam");
        radioButtonCustom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonCustom1ActionPerformed(evt);
            }
        });

        radioButtonCustom2.setBackground(new java.awt.Color(47, 55, 90));
        radioButtonCustom2.setForeground(new java.awt.Color(255, 255, 255));
        radioButtonCustom2.setText("Nữ");

        comboboxTrangThai.setBackground(new java.awt.Color(47, 55, 90));
        comboboxTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        comboboxTrangThai.setMaximumRowCount(10);
        comboboxTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        comboboxTrangThai.setSelectedIndex(-1);
        comboboxTrangThai.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        comboboxTrangThai.setLabeText("Trạng Thái");
        comboboxTrangThai.setLineColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelBourder1Layout = new javax.swing.GroupLayout(jPanelBourder1);
        jPanelBourder1.setLayout(jPanelBourder1Layout);
        jPanelBourder1Layout.setHorizontalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addComponent(comboboxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addComponent(radioButtonCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(radioButtonCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenDem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnInBaoCao5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btnInBaoCao4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                                .addComponent(btnInBaoCao3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addComponent(comboboxChucVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))))
        );
        jPanelBourder1Layout.setVerticalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioButtonCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(radioButtonCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addComponent(btnInBaoCao3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInBaoCao4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInBaoCao5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addComponent(comboboxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        TruongThongTin.add(jPanelBourder1);
        jPanelBourder1.setBounds(390, 130, 970, 620);

        jPanelBourder2.setBackground(new java.awt.Color(47, 55, 90));

        lbAvatar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Image Book");

        jPanel5.setPreferredSize(new java.awt.Dimension(311, 1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        btnInBaoCao6.setBackground(new java.awt.Color(35, 35, 93));
        btnInBaoCao6.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao6.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao6.setText("Choose a picture for the book");
        btnInBaoCao6.setFocusable(false);
        btnInBaoCao6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao6ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(47, 55, 90));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image_Hub/icons8_camera_30px.png"))); // NOI18N

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder2Layout.createSequentialGroup()
                .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                            .addGroup(jPanelBourder2Layout.createSequentialGroup()
                                .addComponent(btnInBaoCao6, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel4)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnInBaoCao6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        TruongThongTin.add(jPanelBourder2);
        jPanelBourder2.setBounds(10, 130, 370, 510);

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Điều chỉnh sản phẩm");
        TruongThongTin.add(jLabel5);
        jLabel5.setBounds(20, 60, 184, 26);

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Management Book - Quản Lý Sách");
        TruongThongTin.add(jLabel6);
        jLabel6.setBounds(20, 20, 420, 33);

        add(TruongThongTin);
        TruongThongTin.setBounds(10, 0, 1360, 800);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void txtTenDemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDemActionPerformed

    private void txtHoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgaySinhActionPerformed

    private void btnInBaoCao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao3ActionPerformed
        NhanVien nhanVien = getForm();
        String insertStatus = nhanVienService.addNhanVien(nhanVien);
        JOptionPane.showMessageDialog(this, insertStatus);
    }//GEN-LAST:event_btnInBaoCao3ActionPerformed

    private void btnInBaoCao4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao4ActionPerformed

    private void btnInBaoCao5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao5ActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauActionPerformed

    private void btnInBaoCao6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao6ActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void radioButtonCustom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonCustom1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButtonCustom1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TruongThongTin;
    private View.ButtonDesign.Button btnInBaoCao3;
    private View.ButtonDesign.Button btnInBaoCao4;
    private View.ButtonDesign.Button btnInBaoCao5;
    private View.ButtonDesign.Button btnInBaoCao6;
    private View.ButtonDesign.Button btnNgaySinh;
    private View.ButtonDesign.Button button2;
    private View.DesignComponent.Combobox comboboxChucVu;
    private View.DesignComponent.Combobox comboboxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbAvatar;
    private View.ComboBoxDesign.RadioButtonCustom radioButtonCustom1;
    private View.ComboBoxDesign.RadioButtonCustom radioButtonCustom2;
    private View.DesignComponent.TextField txtDiaChi;
    private View.DesignComponent.TextField txtEmail;
    private View.DesignComponent.TextField txtHo;
    private View.DesignComponent.TextField txtID;
    private View.DesignComponent.TextField txtMaNhanVien;
    private View.DesignComponent.TextField txtMatKhau;
    private View.DesignComponent.TextField txtNgaySinh;
    private View.DesignComponent.TextField txtSDT;
    private View.DesignComponent.TextField txtTen;
    private View.DesignComponent.TextField txtTenDem;
    // End of variables declaration//GEN-END:variables
}
