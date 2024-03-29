/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.ThongBao;
import View.soundeffect.MySoundEffect;
import java.awt.Dimension;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.ChucVu;
import model.NhanVien;
import service.ChucVuService;
import service.NhanVienService;
import service.impl.ChucVuServicelmpl;
import service.impl.NhanVienServiceImpl;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import util.MyMD5;

/**
 *
 * @author quanc
 */
public class NhanVien_ChucNang_Form extends javax.swing.JPanel {

    private NhanVienService nhanVienService;
    private ChucVuService chucVuService;
    private List<ChucVu> listChucVu;
    private List<NhanVien> listnv = new ArrayList<>();
    /**
     * Creates new form NhanVien_ChucNang_Form
     */
    private boolean isToggle = false;
    private final String DEFAULT_IMAGE = "image/nhanvien.jpg";

    private String currentDirectory;
    private byte[] _hinh = null;

    public NhanVien_ChucNang_Form() {
        initComponents();
        nhanVienService = new NhanVienServiceImpl();
        chucVuService = new ChucVuServicelmpl();
        listChucVu = chucVuService.selectAll();
        comboboxChucVu.setModel(new DefaultComboBoxModel());
        for (int i = 0; i < listChucVu.size(); i++) {
            comboboxChucVu.addItem(listChucVu.get(i).getTen());
        }

        Icon icon = new ImageIcon(new ImageIcon("image/nhanvien.jpg").getImage().getScaledInstance(260, 320, Image.SCALE_DEFAULT));
        this.lblAvartar.setIcon(icon);

        Icon iconBtnNgaySinh = new ImageIcon("image/icons8_schedule_30px.png");
        this.btnNgaySinh.setIcon(iconBtnNgaySinh);

        nhanVienService = new NhanVienServiceImpl();
    }

    public void FillComponent(NhanVien nv) {
        this.txtID.setText(nv.getId());
        NhanVien nhanVien = nhanVienService.getNhanVienById(nv.getId());

        if (nhanVien != null) {
            setForm(nhanVien);
        }
    }

    public JButton getButton() {
        return this.btnThemNhanVien;
    }

    private void setForm(NhanVien nhanVien) {
        _hinh = nhanVien.getHinh();
        setAvartar();
        txtID.setText(nhanVien.getId());
        txtTenDem.setText(nhanVien.getTenDem());
        txtDiaChi.setText(nhanVien.getDiaChi());
        txtEmail.setText(nhanVien.getEmail());
        txtNgaySinh.setText(nhanVien.getNgaySinh().toString());
        txtHo.setText(nhanVien.getHo());
        txtMaNhanVien.setText(nhanVien.getMa());
//        txtMatKhau.setText(nhanVien.getMatKhau());
        txtTen.setText(nhanVien.getTen());
        txtSDT.setText(nhanVien.getSdt());
        comboboxChucVu.setSelectedItem(nhanVien.getChucVu().getTen());
        comboboxTrangThai.setSelectedItem(nhanVien.getTrangThai());
        if (nhanVien.getGioiTinh().equals("Nam")) {
            radioButtonCustom1.setSelected(true);
        } else {
            radioButtonCustom2.setSelected(true);
        }
    }

    private void clear() {
        _hinh = null;
        setAvartar();
        txtID.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtHo.setText("");
        txtID.setText("");
        txtMaNhanVien.setText("");
        txtNgaySinh.setText("");
        txtSDT.setText("");
        txtTen.setText("");
        radioButtonCustom1.setSelected(true);
        txtTenDem.setText("");

    }

    private NhanVien getForm() {
        String id = txtID.getText();
        String maNhanVien = txtMaNhanVien.getText().trim();
        String ten = txtTen.getText().trim().trim();
        String tenDem = txtTenDem.getText().trim();
        String ho = txtHo.getText().trim();
        String email = txtEmail.getText().trim();
        String sdt = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String dateStr = txtNgaySinh.getText();
        int trangThai = comboboxTrangThai.getSelectedIndex() == 1 ? NhanVien.DANGHI : NhanVien.DANGLAM;

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException ex) {

        }
        
        if (maNhanVien.isBlank() || ten.isBlank() || tenDem .isBlank() || ho.isBlank() || email.isBlank() || sdt.isBlank() || diaChi.isBlank() || dateStr .isBlank()) {
            ThongBao.showNoti_Error(this, "Khoonng được để trống");
        }

        if (maNhanVien.length() > 30) {
            ThongBao.showNoti_Error(this, "Mã nhân viên không được quá 30 ký tự");
            return null;
        }

        if (ten.length() > 30) {
            ThongBao.showNoti_Error(this, "Tên nhân viên không được quá 30 ký tự");
            return null;
        }

        if (tenDem.length() > 30) {
            ThongBao.showNoti_Error(this, "Tên đệm nhân viên không được quá 30 ký tự");
            return null;
        }

        if (ho.length() > 30) {
            ThongBao.showNoti_Error(this, "Họ nhân viên không được quá 30 ký tự");
            return null;
        }

        if (email.length() > 50) {
            ThongBao.showNoti_Error(this, "Email không được quá 50 ký tự");
            return null;
        }

        if (sdt.length() > 12) {
            ThongBao.showNoti_Error(this, "Số điện thoại không được quá 12 ký tự");
            return null;
        }

        if (diaChi.length() > 100) {
            ThongBao.showNoti_Error(this, "Địa chỉ không được quá 100 ký tự");
            return null;
        }


        ChucVu chucVu = listChucVu.get(comboboxChucVu.getSelectedIndex());
//        Integer trangThai = (Integer) comboboxTrangThai.getSelectedItem();
        NhanVien nhanvien = new NhanVien();

        if (!txtID.getText().isBlank()) {
            nhanvien.setId(id);
        }
        nhanvien.setMa(maNhanVien);
        nhanvien.setTen(ten);
        nhanvien.setTenDem(tenDem);
        nhanvien.setHo(ho);
        nhanvien.setEmail(email);
        nhanvien.setSdt(sdt);
        nhanvien.setDiaChi(diaChi);
        nhanvien.setMatKhau(MyMD5.getMd5("123456"));
        nhanvien.setTrangThai(trangThai);
        nhanvien.setNgaySinh(date);
        if (radioButtonCustom1.isSelected()) {
            nhanvien.setGioiTinh("Nam");
        } else {
            nhanvien.setGioiTinh("Nu");
        }
        nhanvien.setChucVu(chucVu);
        nhanvien.setHinh(_hinh);
        return nhanvien;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        NgaySinh = new View.DateChooser.DateChooser();
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
        btnThemNhanVien = new View.ButtonDesign.Button();
        btnCapNhatNhanVien = new View.ButtonDesign.Button();
        btnLamMoi = new View.ButtonDesign.Button();
        txtDiaChi = new View.DesignComponent.TextField();
        comboboxChucVu = new View.DesignComponent.Combobox();
        radioButtonCustom1 = new View.ComboBoxDesign.RadioButtonCustom();
        radioButtonCustom2 = new View.ComboBoxDesign.RadioButtonCustom();
        comboboxTrangThai = new View.DesignComponent.Combobox();
        btnResetPassword = new View.ButtonDesign.Button();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        lblAvartar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnChooseImage = new View.ButtonDesign.Button();
        btnCameraImage = new View.ButtonDesign.Button();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        NgaySinh.setForeground(new java.awt.Color(47, 55, 90));
        NgaySinh.setTextRefernce(txtNgaySinh);

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
        btnNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgaySinhActionPerformed(evt);
            }
        });

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

        btnThemNhanVien.setBackground(new java.awt.Color(31, 31, 111));
        btnThemNhanVien.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnThemNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNhanVien.setText("Tạo Mới");
        btnThemNhanVien.setFocusable(false);
        btnThemNhanVien.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        btnCapNhatNhanVien.setBackground(new java.awt.Color(31, 31, 111));
        btnCapNhatNhanVien.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnCapNhatNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatNhanVien.setText("Cập Nhật");
        btnCapNhatNhanVien.setFocusable(false);
        btnCapNhatNhanVien.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnCapNhatNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatNhanVienActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(31, 31, 111));
        btnLamMoi.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.setFocusable(false);
        btnLamMoi.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
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

        comboboxChucVu.setBackground(new java.awt.Color(47, 55, 90));
        comboboxChucVu.setForeground(new java.awt.Color(255, 255, 255));
        comboboxChucVu.setMaximumRowCount(10);
        comboboxChucVu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        comboboxChucVu.setSelectedIndex(-1);
        comboboxChucVu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        comboboxChucVu.setLabeText("Chức Vụ");
        comboboxChucVu.setLineColor(new java.awt.Color(255, 255, 255));

        radioButtonCustom1.setBackground(new java.awt.Color(47, 55, 90));
        buttonGroup1.add(radioButtonCustom1);
        radioButtonCustom1.setForeground(new java.awt.Color(255, 255, 255));
        radioButtonCustom1.setSelected(true);
        radioButtonCustom1.setText("Nam");
        radioButtonCustom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonCustom1ActionPerformed(evt);
            }
        });

        radioButtonCustom2.setBackground(new java.awt.Color(47, 55, 90));
        buttonGroup1.add(radioButtonCustom2);
        radioButtonCustom2.setForeground(new java.awt.Color(255, 255, 255));
        radioButtonCustom2.setText("Nữ");

        comboboxTrangThai.setBackground(new java.awt.Color(47, 55, 90));
        comboboxTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        comboboxTrangThai.setMaximumRowCount(10);
        comboboxTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đang làm", "Đã nghỉ" }));
        comboboxTrangThai.setSelectedIndex(-1);
        comboboxTrangThai.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        comboboxTrangThai.setLabeText("Trạng Thái");
        comboboxTrangThai.setLineColor(new java.awt.Color(255, 255, 255));
        comboboxTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxTrangThaiActionPerformed(evt);
            }
        });

        btnResetPassword.setBackground(new java.awt.Color(31, 31, 111));
        btnResetPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnResetPassword.setForeground(new java.awt.Color(255, 255, 255));
        btnResetPassword.setText("Reset mật khẩu");
        btnResetPassword.setFocusable(false);
        btnResetPassword.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPasswordActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenDem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btnCapNhatNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                                .addComponent(btnThemNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(btnResetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(btnNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(comboboxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addComponent(btnThemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhatNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        TruongThongTin.add(jPanelBourder1);
        jPanelBourder1.setBounds(390, 130, 970, 620);

        jPanelBourder2.setBackground(new java.awt.Color(47, 55, 90));

        lblAvartar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

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

        btnChooseImage.setBackground(new java.awt.Color(35, 35, 93));
        btnChooseImage.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnChooseImage.setForeground(new java.awt.Color(255, 255, 255));
        btnChooseImage.setText("Choose a picture for the book");
        btnChooseImage.setFocusable(false);
        btnChooseImage.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });

        btnCameraImage.setBackground(new java.awt.Color(47, 55, 90));
        btnCameraImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image_Hub/icons8_camera_30px.png"))); // NOI18N
        btnCameraImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCameraImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder2Layout.createSequentialGroup()
                .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAvartar, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                            .addGroup(jPanelBourder2Layout.createSequentialGroup()
                                .addComponent(btnChooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCameraImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addComponent(lblAvartar, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCameraImage, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnChooseImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    }//GEN-LAST:event_txtNgaySinhActionPerformed

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed

        NhanVien nv = getForm();
        if (nv == null) {
            return;
        }
        
        if(nv.getId() != null) {
            JOptionPane.showMessageDialog(this, "Clear form trước khi thêm", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (NhanVien nv1 : listnv) {
            System.out.println(nv1.getMa() + " - " + nv.getMa());
            if (nv1.getMa().equals(nv.getMa())) {
                JOptionPane.showMessageDialog(this, "Trùng mã Nhân Viên", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);

                return;
            }
            if (nv1.getEmail().equals(nv.getEmail())) {
                JOptionPane.showMessageDialog(this, "Trùng email Nhân Viên", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);

                return;
            }
            if (nv1.getSdt().equals(nv.getSdt())) {
                JOptionPane.showMessageDialog(this, "Trùng sdt Nhân Viên", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);

                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Tao moi thanh cong");
        nhanVienService.addNhanVien(nv);
        clear();

        NhanVien_Form form = new NhanVien_Form();
        form.initTableData();
    }//GEN-LAST:event_btnThemNhanVienActionPerformed


    private void btnCapNhatNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatNhanVienActionPerformed
//        NhanVien nhanVien = getForm();
//        String insertStatus = nhanVienService.updateNhanVien(nhanVien);
//        JOptionPane.showMessageDialog(this, "Cập nhật thành công");

        NhanVien nv = getForm();
        if (nv.getId() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhan vien");
            return;
        }

        listnv = nhanVienService.getAllNhanVien();
        for (NhanVien nv1 : listnv) {
            if (nv1.getMa().equals(txtMaNhanVien.getText().trim()) && !nv1.getId().equals(nv.getId())) {
                JOptionPane.showMessageDialog(this, "Trùng mã Nhân Viên", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);

                return;
            }
            if (nv1.getEmail().equals(nv.getEmail().trim()) && !nv1.getId().equals(nv.getId())) {
                JOptionPane.showMessageDialog(this, "Trùng email Nhân Viên", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);

                return;
            }
            if (nv1.getSdt().equals(nv.getSdt().trim()) && !nv1.getId().equals(nv.getId())) {
                JOptionPane.showMessageDialog(this, "Trùng sdt Nhân Viên", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);

                return;
            }
        }

        System.out.println(nv.toString());
        nhanVienService.updateNhanVien(nv);

        JOptionPane.showMessageDialog(this, "Update successfully");
        clear();
        FillComponent(nv);

        NhanVien_Form form = new NhanVien_Form();
        form.initTableData();
    }//GEN-LAST:event_btnCapNhatNhanVienActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clear();

        NhanVien_Form form = new NhanVien_Form();
        form.initTableData();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImageActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser(currentDirectory);
        fileChooser.setPreferredSize(new Dimension(800, 600));
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
        int result = fileChooser.showDialog(this, "Chọn ảnh");
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            currentDirectory = file.getAbsolutePath();
            Path p = Paths.get(currentDirectory);
            if (currentDirectory.toLowerCase().endsWith(".png") || currentDirectory.toLowerCase().endsWith(".jpg")) {
                try {
                    _hinh = Files.readAllBytes(p);
                    if (_hinh.length > 1024000) {
                        JOptionPane.showMessageDialog(this, "File không được vượt quá 1M", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        _hinh = null;
                        return;
                    }
                    setAvartar();
                } catch (NoSuchFileException nofile) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy file");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return;
            }

            JOptionPane.showMessageDialog(this, "Chỉ hỗ trợ file .jpg | .png", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnChooseImageActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void radioButtonCustom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonCustom1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButtonCustom1ActionPerformed

    private void comboboxTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboboxTrangThaiActionPerformed

    private void btnResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPasswordActionPerformed
        String matKhau = MyMD5.getMd5("123456");
        NhanVien nv = this.getForm();
        if (nv.getId().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui long chon Nhan Vien can Reset Mat Khau");
            return;
        }
        int kq = JOptionPane.showConfirmDialog(this, "Ban co xac nhan muon reset");
        if (kq == JOptionPane.YES_OPTION) {
            nhanVienService.updateNhanVien(nv);
            JOptionPane.showConfirmDialog(this, "Reset thanh cong");
        }

    }//GEN-LAST:event_btnResetPasswordActionPerformed

    private void btnCameraImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCameraImageActionPerformed
        // TODO add your handling code here:

        btnCameraImage.setEnabled(false);
        CamJFrame cam = new CamJFrame();
        cam.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cam.webcam.close();
                btnCameraImage.setEnabled(true);
                closedCam(cam);
            }
        });
        cam.setTitle("Take a picture");
        cam.setVisible(true);
        cam.btnCapture.addActionListener((java.awt.event.ActionEvent evt1) -> {
            BufferedImage image = cam.webcam.getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(image, "png", baos);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            _hinh = baos.toByteArray();
            MySoundEffect.play(MySoundEffect.PATH_CAPTURE_PICTURE);
            if (_hinh != null) {
                lblAvartar.setIcon(new ImageIcon(new ImageIcon(_hinh).getImage().getScaledInstance(lblAvartar.getWidth(), lblAvartar.getHeight(), Image.SCALE_DEFAULT)));
                this.TruongThongTin.show(true);
            }
            btnCameraImage.setEnabled(true);
            closedCam(cam);
        });

        Thread th = new Thread(() -> {
            cam.record();
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
//                        cam.webcam.close();
//                        cam.dispose();
                        closedCam(cam);
                        btnCameraImage.setEnabled(true);
                        x = false;
                    }

                }
            }
        });
        thread.start();

    }//GEN-LAST:event_btnCameraImageActionPerformed

    private void btnNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgaySinhActionPerformed
        NgaySinh.showPopup();


    }//GEN-LAST:event_btnNgaySinhActionPerformed
    private void setAvartar() {
        if (_hinh == null) {
            lblAvartar.setIcon(new ImageIcon(new ImageIcon(DEFAULT_IMAGE).getImage().getScaledInstance(lblAvartar.getWidth(), lblAvartar.getHeight(), Image.SCALE_DEFAULT)));
            return;
        }
        lblAvartar.setIcon(new ImageIcon(new ImageIcon(_hinh).getImage().getScaledInstance(lblAvartar.getWidth(), lblAvartar.getHeight(), Image.SCALE_DEFAULT)));
    }

    private void closedCam(CamJFrame cam) {
        cam.webcam.close();
        cam.dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.DateChooser.DateChooser NgaySinh;
    private javax.swing.JPanel TruongThongTin;
    private View.ButtonDesign.Button btnCameraImage;
    private View.ButtonDesign.Button btnCapNhatNhanVien;
    private View.ButtonDesign.Button btnChooseImage;
    private View.ButtonDesign.Button btnLamMoi;
    private View.ButtonDesign.Button btnNgaySinh;
    private View.ButtonDesign.Button btnResetPassword;
    private View.ButtonDesign.Button btnThemNhanVien;
    private javax.swing.ButtonGroup buttonGroup1;
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
    private javax.swing.JLabel lblAvartar;
    private View.ComboBoxDesign.RadioButtonCustom radioButtonCustom1;
    private View.ComboBoxDesign.RadioButtonCustom radioButtonCustom2;
    private View.DesignComponent.TextField txtDiaChi;
    private View.DesignComponent.TextField txtEmail;
    private View.DesignComponent.TextField txtHo;
    private View.DesignComponent.TextField txtID;
    private View.DesignComponent.TextField txtMaNhanVien;
    private View.DesignComponent.TextField txtNgaySinh;
    private View.DesignComponent.TextField txtSDT;
    private View.DesignComponent.TextField txtTen;
    private View.DesignComponent.TextField txtTenDem;
    // End of variables declaration//GEN-END:variables
}
