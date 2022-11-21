package View.Form_Management;

import View.ManagementBookForm;
import View.PanelTagDesign.EventTags;
import View.PanelTagDesign.Item;
import View.ScrollBarCustom;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Sach;
import service.SachService;
import service.impl.SachServiceImpl;

/**
 *
 * @author quanc
 */
public class Sach_ChucNang_Form extends javax.swing.JPanel {

    private boolean isToggle = false;

    private String currentDirectory;
    private byte[] _hinh = null;
    private final SachService _sachService;

    public Sach_ChucNang_Form() {
        initComponents();
        this.Form_Chon.show(false);
        this.background.show(false);
//            this.jScrollPane1.show(false);
        ScrollBarCustom scr = new ScrollBarCustom();
        this.scroll.setVerticalScrollBar(scr);
        this.cbSelect.removeAllItems();

        String tacgia[] = {"Quân Minh",
            "Thế Phương",
            "Đức Anh",
            "Linh",
            "Hương",
            "Nam"};

        DefaultComboBoxModel model = new DefaultComboBoxModel(tacgia);
        this.cbSelect.setModel(model);
        Icon icon = new ImageIcon(new ImageIcon("image/dacnhantam.jpg").getImage().getScaledInstance(260, 320, Image.SCALE_DEFAULT));
        this.lblAvartar.setIcon(icon);

        Icon iconbgr = new ImageIcon("image/demobgr.png");
        this.background.setIcon(iconbgr);
        this.background.setBackground(new Color(0, 0, 0, 0));

        _sachService = new SachServiceImpl();

        j.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Form_Chon.show(false);
                background.show(false);
                btnSelectTheLoai.show(true);
                TruongThongTin.show(true);
            }
        });
    }

    public void setForm(Sach sach) {
        _hinh = sach.getHinh();
        if (_hinh != null) {
            lblAvartar.setIcon(new ImageIcon(new ImageIcon(_hinh).getImage().getScaledInstance(260, 320, Image.SCALE_DEFAULT)));
        }
        txtId.setText(sach.getId());
        txtBarCode.setText(sach.getBarCode());
        txtGiaBan.setText(sach.getGiaBan() + "");
        txtGiaNhap.setText(sach.getGiaNhap() + "");
        txtMa.setText(sach.getMa());
        txtMoTa.setText(sach.getMoTa());
        txtSoLuong.setText(sach.getSoLuong() + "");
        txtSoTrang.setText(sach.getSoTrang() + "");
        txtTen.setText(sach.getTen());

    }

    private void OpenCamera(String title) {
        j.setSize(1018, 750);
        Camera_Form cameraF = new Camera_Form();
        cameraF.setSize(1018, 750);
        cameraF.setTitle(title);
        j.add(cameraF, BorderLayout.CENTER);
        j.setLocationRelativeTo(this);
        j.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator4 = new javax.swing.JSeparator();
        j = new javax.swing.JDialog();
        buttonGroup1 = new javax.swing.ButtonGroup();
        TruongThongTin = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        btnSelectTheLoai = new View.ButtonDesign.Button();
        txtId = new View.DesignComponent.TextField();
        txtMa = new View.DesignComponent.TextField();
        txtTen = new View.DesignComponent.TextField();
        txtSoLuong = new View.DesignComponent.TextField();
        txtSoTrang = new View.DesignComponent.TextField();
        txtMoTa = new View.DesignComponent.TextField();
        txtGiaBan = new View.DesignComponent.TextField();
        txtGiaNhap = new View.DesignComponent.TextField();
        jLabel1 = new javax.swing.JLabel();
        txtBarCode = new View.DesignComponent.TextField();
        btnCamBarCode = new View.ButtonDesign.Button();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnSelectTacGia = new View.ButtonDesign.Button();
        btnAdd = new View.ButtonDesign.Button();
        btnUpdate = new View.ButtonDesign.Button();
        btnClear = new View.ButtonDesign.Button();
        textField12 = new View.DesignComponent.TextField();
        textField13 = new View.DesignComponent.TextField();
        rdoDangKinhDoanh = new View.ComboBoxDesign.RadioButtonCustom();
        rdoNgungKinhDoanh = new View.ComboBoxDesign.RadioButtonCustom();
        cboNhaXuatBan = new View.DesignComponent.Combobox();
        cboViTri = new View.DesignComponent.Combobox();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        lblAvartar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnChooseImage = new View.ButtonDesign.Button();
        btnCameraImage = new View.ButtonDesign.Button();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Form_Chon = new View.DesignComponent.JPanelBourder();
        cbSelect = new View.ComboBoxDesign.ComboBoxSuggestion();
        jPanel3 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        btnInBaoCao = new View.ButtonDesign.Button();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbNameForm = new javax.swing.JLabel();
        background = new View.ButtonDesign.Background();

        j.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        j.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        j.setResizable(false);

        javax.swing.GroupLayout jLayout = new javax.swing.GroupLayout(j.getContentPane());
        j.getContentPane().setLayout(jLayout);
        jLayout.setHorizontalGroup(
            jLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jLayout.setVerticalGroup(
            jLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(29, 32, 57));
        setPreferredSize(new java.awt.Dimension(1373, 850));
        setLayout(null);

        TruongThongTin.setBackground(new java.awt.Color(11, 20, 55));
        TruongThongTin.setLayout(null);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        TruongThongTin.add(jSeparator2);
        jSeparator2.setBounds(0, 120, 1370, 3);

        jPanelBourder1.setBackground(new java.awt.Color(47, 55, 90));

        btnSelectTheLoai.setBackground(new java.awt.Color(55, 69, 128));
        btnSelectTheLoai.setBorder(null);
        btnSelectTheLoai.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectTheLoai.setText("...");
        btnSelectTheLoai.setFocusable(false);
        btnSelectTheLoai.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnSelectTheLoai.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSelectTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectTheLoaiActionPerformed(evt);
            }
        });

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(47, 55, 90));
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setCaretColor(new java.awt.Color(255, 255, 255));
        txtId.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtId.setLabelText("ID");
        txtId.setLineColor(new java.awt.Color(255, 255, 255));

        txtMa.setBackground(new java.awt.Color(47, 55, 90));
        txtMa.setForeground(new java.awt.Color(255, 255, 255));
        txtMa.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMa.setLabelText("Mã Sách");
        txtMa.setLineColor(new java.awt.Color(255, 255, 255));

        txtTen.setBackground(new java.awt.Color(47, 55, 90));
        txtTen.setForeground(new java.awt.Color(255, 255, 255));
        txtTen.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTen.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTen.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTen.setLabelText("Tên Sách");
        txtTen.setLineColor(new java.awt.Color(255, 255, 255));

        txtSoLuong.setBackground(new java.awt.Color(47, 55, 90));
        txtSoLuong.setForeground(new java.awt.Color(255, 255, 255));
        txtSoLuong.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSoLuong.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSoLuong.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtSoLuong.setLabelText("Số lượng");
        txtSoLuong.setLineColor(new java.awt.Color(255, 255, 255));
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        txtSoTrang.setBackground(new java.awt.Color(47, 55, 90));
        txtSoTrang.setForeground(new java.awt.Color(255, 255, 255));
        txtSoTrang.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSoTrang.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSoTrang.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtSoTrang.setLabelText("Số Trang");
        txtSoTrang.setLineColor(new java.awt.Color(255, 255, 255));
        txtSoTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoTrangActionPerformed(evt);
            }
        });

        txtMoTa.setBackground(new java.awt.Color(47, 55, 90));
        txtMoTa.setForeground(new java.awt.Color(255, 255, 255));
        txtMoTa.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMoTa.setLabelText("Mô Tả");
        txtMoTa.setLineColor(new java.awt.Color(255, 255, 255));
        txtMoTa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTaActionPerformed(evt);
            }
        });

        txtGiaBan.setBackground(new java.awt.Color(47, 55, 90));
        txtGiaBan.setForeground(new java.awt.Color(255, 255, 255));
        txtGiaBan.setCaretColor(new java.awt.Color(255, 255, 255));
        txtGiaBan.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtGiaBan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtGiaBan.setLabelText("Giá Bán");
        txtGiaBan.setLineColor(new java.awt.Color(255, 255, 255));
        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });

        txtGiaNhap.setBackground(new java.awt.Color(47, 55, 90));
        txtGiaNhap.setForeground(new java.awt.Color(255, 255, 255));
        txtGiaNhap.setCaretColor(new java.awt.Color(255, 255, 255));
        txtGiaNhap.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtGiaNhap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtGiaNhap.setLabelText("Giá Nhập");
        txtGiaNhap.setLineColor(new java.awt.Color(255, 255, 255));
        txtGiaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaNhapActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Trạng Thái");

        txtBarCode.setBackground(new java.awt.Color(47, 55, 90));
        txtBarCode.setForeground(new java.awt.Color(255, 255, 255));
        txtBarCode.setCaretColor(new java.awt.Color(255, 255, 255));
        txtBarCode.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtBarCode.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtBarCode.setLabelText("Barcode");
        txtBarCode.setLineColor(new java.awt.Color(255, 255, 255));
        txtBarCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarCodeActionPerformed(evt);
            }
        });

        btnCamBarCode.setBackground(new java.awt.Color(47, 55, 90));
        btnCamBarCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image_Hub/icons8_camera_30px.png"))); // NOI18N
        btnCamBarCode.setToolTipText("Quét mã barcode");
        btnCamBarCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamBarCodeActionPerformed(evt);
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

        btnSelectTacGia.setBackground(new java.awt.Color(55, 69, 128));
        btnSelectTacGia.setBorder(null);
        btnSelectTacGia.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectTacGia.setText("...");
        btnSelectTacGia.setFocusable(false);
        btnSelectTacGia.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnSelectTacGia.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSelectTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectTacGiaActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(31, 31, 111));
        btnAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Tạo Mới");
        btnAdd.setToolTipText("Thêm mới sách");
        btnAdd.setFocusable(false);
        btnAdd.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(31, 31, 111));
        btnUpdate.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Cập Nhật");
        btnUpdate.setToolTipText("Cập nhật sách");
        btnUpdate.setFocusable(false);
        btnUpdate.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(31, 31, 111));
        btnClear.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Làm Mới");
        btnClear.setToolTipText("Clear form");
        btnClear.setFocusable(false);
        btnClear.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        textField12.setBackground(new java.awt.Color(47, 55, 90));
        textField12.setForeground(new java.awt.Color(255, 255, 255));
        textField12.setCaretColor(new java.awt.Color(255, 255, 255));
        textField12.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField12.setLabelText("Tác Giả");
        textField12.setLineColor(new java.awt.Color(255, 255, 255));
        textField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField12ActionPerformed(evt);
            }
        });

        textField13.setBackground(new java.awt.Color(47, 55, 90));
        textField13.setForeground(new java.awt.Color(255, 255, 255));
        textField13.setCaretColor(new java.awt.Color(255, 255, 255));
        textField13.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField13.setLabelText("Thể Loại");
        textField13.setLineColor(new java.awt.Color(255, 255, 255));
        textField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField13ActionPerformed(evt);
            }
        });

        rdoDangKinhDoanh.setBackground(new java.awt.Color(47, 55, 90));
        buttonGroup1.add(rdoDangKinhDoanh);
        rdoDangKinhDoanh.setForeground(new java.awt.Color(255, 255, 255));
        rdoDangKinhDoanh.setSelected(true);
        rdoDangKinhDoanh.setText("Đang kinh doanh");

        rdoNgungKinhDoanh.setBackground(new java.awt.Color(47, 55, 90));
        buttonGroup1.add(rdoNgungKinhDoanh);
        rdoNgungKinhDoanh.setForeground(new java.awt.Color(255, 255, 255));
        rdoNgungKinhDoanh.setText("Ngừng kinh doanh");

        cboNhaXuatBan.setBackground(new java.awt.Color(47, 55, 90));
        cboNhaXuatBan.setForeground(new java.awt.Color(255, 255, 255));
        cboNhaXuatBan.setMaximumRowCount(10);
        cboNhaXuatBan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        cboNhaXuatBan.setSelectedIndex(-1);
        cboNhaXuatBan.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cboNhaXuatBan.setLabeText("Nhà Xuất Bản");
        cboNhaXuatBan.setLineColor(new java.awt.Color(255, 255, 255));

        cboViTri.setBackground(new java.awt.Color(47, 55, 90));
        cboViTri.setForeground(new java.awt.Color(255, 255, 255));
        cboViTri.setMaximumRowCount(10);
        cboViTri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        cboViTri.setSelectedIndex(-1);
        cboViTri.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cboViTri.setLabeText("Vị Trí");
        cboViTri.setLineColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelBourder1Layout = new javax.swing.GroupLayout(jPanelBourder1);
        jPanelBourder1.setLayout(jPanelBourder1Layout);
        jPanelBourder1Layout.setHorizontalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textField13, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addComponent(cboViTri, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textField12, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSelectTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSelectTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addComponent(rdoDangKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdoNgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBourder1Layout.createSequentialGroup()
                                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboNhaXuatBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(txtBarCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCamBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
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
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoDangKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoNgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCamBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelectTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboViTri, javax.swing.GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textField13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSelectTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
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
            .addGap(0, 325, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        btnChooseImage.setBackground(new java.awt.Color(35, 35, 93));
        btnChooseImage.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnChooseImage.setForeground(new java.awt.Color(255, 255, 255));
        btnChooseImage.setText("Choose a picture for the book");
        btnChooseImage.setToolTipText("Chọn ảnh cho sách");
        btnChooseImage.setFocusable(false);
        btnChooseImage.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });

        btnCameraImage.setBackground(new java.awt.Color(47, 55, 90));
        btnCameraImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image_Hub/icons8_camera_30px.png"))); // NOI18N
        btnCameraImage.setToolTipText("Chụp ảnh sách");
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
                        .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelBourder2Layout.createSequentialGroup()
                                .addComponent(btnChooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCameraImage, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel4))
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lblAvartar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
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
        TruongThongTin.setBounds(0, 0, 1370, 800);

        Form_Chon.setBackground(new java.awt.Color(47, 55, 90));

        cbSelect.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        cbSelect.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSelectItemStateChanged(evt);
            }
        });
        cbSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSelectActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel4.setBackground(new java.awt.Color(17, 28, 68));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));
        scroll.setViewportView(jPanel4);

        jPanel3.add(scroll);

        btnInBaoCao.setBackground(new java.awt.Color(62, 77, 144));
        btnInBaoCao.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInBaoCao.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao.setText("Thêm Mới");
        btnInBaoCao.setFocusable(false);
        btnInBaoCao.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnInBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCaoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Danh Sách Chọn ");

        lbNameForm.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbNameForm.setForeground(new java.awt.Color(255, 255, 255));
        lbNameForm.setText("Thêm Tác Giả");

        javax.swing.GroupLayout Form_ChonLayout = new javax.swing.GroupLayout(Form_Chon);
        Form_Chon.setLayout(Form_ChonLayout);
        Form_ChonLayout.setHorizontalGroup(
            Form_ChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_ChonLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(Form_ChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Form_ChonLayout.createSequentialGroup()
                        .addComponent(cbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_ChonLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNameForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        Form_ChonLayout.setVerticalGroup(
            Form_ChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_ChonLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lbNameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Form_ChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(Form_ChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        add(Form_Chon);
        Form_Chon.setBounds(440, 110, 520, 500);

        background.setBackground(new java.awt.Color(0, 0, 0));
        background.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        background.setBorderPainted(false);
        background.setFocusPainted(false);
        background.setFocusable(false);
        background.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundActionPerformed(evt);
            }
        });
        add(background);
        background.setBounds(0, 0, 1370, 840);
    }// </editor-fold>//GEN-END:initComponents
    private EventTags event;
    private void backgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backgroundActionPerformed
        this.Form_Chon.show(false);
        this.background.show(false);
        this.btnSelectTheLoai.show(true);
        this.TruongThongTin.show(true);
//        revalidate();
    }//GEN-LAST:event_backgroundActionPerformed

    private void btnSelectTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectTheLoaiActionPerformed
        this.lbNameForm.setText("Thêm Thể Loại");
//        this.txtReset.requestFocusInWindow();
        this.background.show(true);
        this.btnSelectTheLoai.show(false);
        this.Form_Chon.show(true);
        this.TruongThongTin.show(false);
        //        this.background.doClick();
        //        this.jScrollPane1.show(true);

        revalidate();

    }//GEN-LAST:event_btnSelectTheLoaiActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtSoTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoTrangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoTrangActionPerformed

    private void txtMoTaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTaActionPerformed

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanActionPerformed

    private void txtGiaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaNhapActionPerformed

    private void txtBarCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarCodeActionPerformed

    private void btnSelectTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectTacGiaActionPerformed
        this.lbNameForm.setText("Thêm Tác Giả");
        String tacgia[] = {"TG",
            "Thế Phương",
            "Đức Anh",
            "Linh",
            "Hương",
            "Nam"};
        DefaultComboBoxModel model = new DefaultComboBoxModel(tacgia);
        this.cbSelect.setModel(model);
        this.background.show(true);
        this.btnSelectTheLoai.show(false);
        this.Form_Chon.show(true);
        this.TruongThongTin.show(false);
        //        this.background.doClick();
        //        this.jScrollPane1.show(true);

        revalidate();
    }//GEN-LAST:event_btnSelectTacGiaActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImageActionPerformed
        JFileChooser fileChooser = new JFileChooser(currentDirectory);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
        int result = fileChooser.showDialog(this, "Chọn ảnh");
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            currentDirectory = file.getAbsolutePath();
            Path p = Paths.get(currentDirectory);
            if (currentDirectory.endsWith(".png") || currentDirectory.endsWith(".jpg")) {
                try {
                    _hinh = Files.readAllBytes(p);
                    if (_hinh.length > 1024000) {
                        JOptionPane.showMessageDialog(this, "File không được vượt quá 1M", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        _hinh = null;
                        return;
                    }
                    lblAvartar.setIcon(new ImageIcon(new ImageIcon(_hinh).getImage().getScaledInstance(174, 210, Image.SCALE_DEFAULT)));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return;
            }

            JOptionPane.showMessageDialog(this, "Chỉ hỗ trợ file .jpg | .png", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnChooseImageActionPerformed

    private void textField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField12ActionPerformed

    private void textField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField13ActionPerformed

    private void cbSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSelectActionPerformed

        String tacgia = this.cbSelect.getSelectedItem().toString();
        Item item = new Item(tacgia);

        item.addEventRemove(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel4.remove(item);
                repaint();
                revalidate();
            }
        }
        );
        this.jPanel4.add(item);
        revalidate();
        System.out.println(item.getText());
    }//GEN-LAST:event_cbSelectActionPerformed

    private void cbSelectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSelectItemStateChanged

    }//GEN-LAST:event_cbSelectItemStateChanged

    private void btnInBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCaoActionPerformed
        ManagementBookForm mng = new ManagementBookForm();
        mng.runNotification();
    }//GEN-LAST:event_btnInBaoCaoActionPerformed

    private void btnCamBarCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamBarCodeActionPerformed
//        openedCam();
        CamJFrame cam = new CamJFrame();
        cam.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cam.webcam.close();
                cam.dispose();
                closedCam(cam);
            }
        });
        cam.setTitle("QR/ BarCode Scanner");
        cam.setVisible(true);
        cam.btnCapture.setEnabled(false);

        Thread th = new Thread(() -> {
            cam.webcam.open();
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
                    if (!(result + "").matches("\\d+")) {
                        JOptionPane.showMessageDialog(this, "BarCode không hợp lệ");
                        return;
                    }
                    txtBarCode.setText(result + "");
                    closedCam(cam);
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
                        btnCameraImage.setEnabled(true);
                        x = false;
                    }

                }
            }
        });
        thread.start();

    }//GEN-LAST:event_btnCamBarCodeActionPerformed

    private void openedCam() {
        this.background.show(true);
        this.btnSelectTheLoai.show(false);
        this.TruongThongTin.show(false);
    }

    private void btnCameraImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCameraImageActionPerformed
//        this.background.show(true);
//        this.btnSelectTheLoai.show(false);
//        this.TruongThongTin.show(false);

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
            if (_hinh != null) {
                lblAvartar.setIcon(new ImageIcon(new ImageIcon(_hinh).getImage().getScaledInstance(lblAvartar.getWidth(), lblAvartar.getHeight(), Image.SCALE_DEFAULT)));
                System.out.println(_hinh);
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
                        btnCameraImage.setEnabled(true);
                        closedCam(cam);
                        x = false;
                    }

                }
            }
        });
        thread.start();

    }//GEN-LAST:event_btnCameraImageActionPerformed

    private void closedCam(CamJFrame cam) {
        cam.webcam.close();
        cam.dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.DesignComponent.JPanelBourder Form_Chon;
    private javax.swing.JPanel TruongThongTin;
    private View.ButtonDesign.Background background;
    private View.ButtonDesign.Button btnAdd;
    private View.ButtonDesign.Button btnCamBarCode;
    private View.ButtonDesign.Button btnCameraImage;
    private View.ButtonDesign.Button btnChooseImage;
    private View.ButtonDesign.Button btnClear;
    private View.ButtonDesign.Button btnInBaoCao;
    private View.ButtonDesign.Button btnSelectTacGia;
    private View.ButtonDesign.Button btnSelectTheLoai;
    private View.ButtonDesign.Button btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private View.ComboBoxDesign.ComboBoxSuggestion cbSelect;
    private View.DesignComponent.Combobox cboNhaXuatBan;
    private View.DesignComponent.Combobox cboViTri;
    private javax.swing.JDialog j;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbNameForm;
    private javax.swing.JLabel lblAvartar;
    private View.ComboBoxDesign.RadioButtonCustom rdoDangKinhDoanh;
    private View.ComboBoxDesign.RadioButtonCustom rdoNgungKinhDoanh;
    private javax.swing.JScrollPane scroll;
    private View.DesignComponent.TextField textField12;
    private View.DesignComponent.TextField textField13;
    private View.DesignComponent.TextField txtBarCode;
    private View.DesignComponent.TextField txtGiaBan;
    private View.DesignComponent.TextField txtGiaNhap;
    private View.DesignComponent.TextField txtId;
    private View.DesignComponent.TextField txtMa;
    private View.DesignComponent.TextField txtMoTa;
    private View.DesignComponent.TextField txtSoLuong;
    private View.DesignComponent.TextField txtSoTrang;
    private View.DesignComponent.TextField txtTen;
    // End of variables declaration//GEN-END:variables
}
