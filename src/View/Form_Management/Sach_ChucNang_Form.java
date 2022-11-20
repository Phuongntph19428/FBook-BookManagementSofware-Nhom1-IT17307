package View.Form_Management;

import View.ManagementBookForm;
import View.PanelTagDesign.EventTags;
import View.PanelTagDesign.Item;
import View.ScrollBarCustom;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author quanc
 */
public class Sach_ChucNang_Form extends javax.swing.JPanel {
    
    private boolean isToggle = false;    
    
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
        this.lbAvatar.setIcon(icon);

        Icon iconbgr = new ImageIcon("image/demobgr.png");
        this.background.setIcon(iconbgr);
        this.background.setBackground(new Color(0, 0, 0, 0));
    }
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator4 = new javax.swing.JSeparator();
        TruongThongTin = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        btnSelectTacGia = new View.ButtonDesign.Button();
        textField1 = new View.DesignComponent.TextField();
        textField2 = new View.DesignComponent.TextField();
        textField3 = new View.DesignComponent.TextField();
        textField4 = new View.DesignComponent.TextField();
        textField5 = new View.DesignComponent.TextField();
        textField6 = new View.DesignComponent.TextField();
        textField7 = new View.DesignComponent.TextField();
        textField8 = new View.DesignComponent.TextField();
        jLabel1 = new javax.swing.JLabel();
        textField9 = new View.DesignComponent.TextField();
        button1 = new View.ButtonDesign.Button();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnSelectTacGia1 = new View.ButtonDesign.Button();
        btnInBaoCao3 = new View.ButtonDesign.Button();
        btnInBaoCao4 = new View.ButtonDesign.Button();
        btnInBaoCao5 = new View.ButtonDesign.Button();
        textField10 = new View.DesignComponent.TextField();
        btnSelectTacGia2 = new View.ButtonDesign.Button();
        textField12 = new View.DesignComponent.TextField();
        textField13 = new View.DesignComponent.TextField();
        radioButtonCustom1 = new View.ComboBoxDesign.RadioButtonCustom();
        radioButtonCustom2 = new View.ComboBoxDesign.RadioButtonCustom();
        combobox1 = new View.DesignComponent.Combobox();
        combobox2 = new View.DesignComponent.Combobox();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        lbAvatar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnInBaoCao6 = new View.ButtonDesign.Button();
        button2 = new View.ButtonDesign.Button();
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

        setBackground(new java.awt.Color(29, 32, 57));
        setPreferredSize(new java.awt.Dimension(1373, 850));
        setLayout(null);

        TruongThongTin.setBackground(new java.awt.Color(11, 20, 55));
        TruongThongTin.setLayout(null);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        TruongThongTin.add(jSeparator2);
        jSeparator2.setBounds(0, 120, 1370, 3);

        jPanelBourder1.setBackground(new java.awt.Color(47, 55, 90));

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

        textField1.setEditable(false);
        textField1.setBackground(new java.awt.Color(47, 55, 90));
        textField1.setForeground(new java.awt.Color(255, 255, 255));
        textField1.setCaretColor(new java.awt.Color(255, 255, 255));
        textField1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField1.setLabelText("ID");
        textField1.setLineColor(new java.awt.Color(255, 255, 255));

        textField2.setBackground(new java.awt.Color(47, 55, 90));
        textField2.setForeground(new java.awt.Color(255, 255, 255));
        textField2.setCaretColor(new java.awt.Color(255, 255, 255));
        textField2.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField2.setLabelText("Mã Sách");
        textField2.setLineColor(new java.awt.Color(255, 255, 255));

        textField3.setBackground(new java.awt.Color(47, 55, 90));
        textField3.setForeground(new java.awt.Color(255, 255, 255));
        textField3.setCaretColor(new java.awt.Color(255, 255, 255));
        textField3.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField3.setLabelText("Tên Sách");
        textField3.setLineColor(new java.awt.Color(255, 255, 255));

        textField4.setBackground(new java.awt.Color(47, 55, 90));
        textField4.setForeground(new java.awt.Color(255, 255, 255));
        textField4.setCaretColor(new java.awt.Color(255, 255, 255));
        textField4.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField4.setLabelText("Số lượng");
        textField4.setLineColor(new java.awt.Color(255, 255, 255));
        textField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField4ActionPerformed(evt);
            }
        });

        textField5.setBackground(new java.awt.Color(47, 55, 90));
        textField5.setForeground(new java.awt.Color(255, 255, 255));
        textField5.setCaretColor(new java.awt.Color(255, 255, 255));
        textField5.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField5.setLabelText("Số Trang");
        textField5.setLineColor(new java.awt.Color(255, 255, 255));
        textField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField5ActionPerformed(evt);
            }
        });

        textField6.setBackground(new java.awt.Color(47, 55, 90));
        textField6.setForeground(new java.awt.Color(255, 255, 255));
        textField6.setCaretColor(new java.awt.Color(255, 255, 255));
        textField6.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField6.setLabelText("Mô Tả");
        textField6.setLineColor(new java.awt.Color(255, 255, 255));
        textField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField6ActionPerformed(evt);
            }
        });

        textField7.setBackground(new java.awt.Color(47, 55, 90));
        textField7.setForeground(new java.awt.Color(255, 255, 255));
        textField7.setCaretColor(new java.awt.Color(255, 255, 255));
        textField7.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField7.setLabelText("Giá Bán");
        textField7.setLineColor(new java.awt.Color(255, 255, 255));
        textField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField7ActionPerformed(evt);
            }
        });

        textField8.setBackground(new java.awt.Color(47, 55, 90));
        textField8.setForeground(new java.awt.Color(255, 255, 255));
        textField8.setCaretColor(new java.awt.Color(255, 255, 255));
        textField8.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField8.setLabelText("Giá Nhập");
        textField8.setLineColor(new java.awt.Color(255, 255, 255));
        textField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField8ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Trạng Thái");

        textField9.setBackground(new java.awt.Color(47, 55, 90));
        textField9.setForeground(new java.awt.Color(255, 255, 255));
        textField9.setCaretColor(new java.awt.Color(255, 255, 255));
        textField9.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField9.setLabelText("Barcode");
        textField9.setLineColor(new java.awt.Color(255, 255, 255));
        textField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField9ActionPerformed(evt);
            }
        });

        button1.setBackground(new java.awt.Color(47, 55, 90));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image_Hub/icons8_camera_30px.png"))); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
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

        btnSelectTacGia1.setBackground(new java.awt.Color(55, 69, 128));
        btnSelectTacGia1.setBorder(null);
        btnSelectTacGia1.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectTacGia1.setText("...");
        btnSelectTacGia1.setFocusable(false);
        btnSelectTacGia1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnSelectTacGia1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSelectTacGia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectTacGia1ActionPerformed(evt);
            }
        });

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

        textField10.setBackground(new java.awt.Color(47, 55, 90));
        textField10.setForeground(new java.awt.Color(255, 255, 255));
        textField10.setCaretColor(new java.awt.Color(255, 255, 255));
        textField10.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField10.setLabelText("Khuyến Mãi");
        textField10.setLineColor(new java.awt.Color(255, 255, 255));
        textField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField10ActionPerformed(evt);
            }
        });

        btnSelectTacGia2.setBackground(new java.awt.Color(55, 69, 128));
        btnSelectTacGia2.setBorder(null);
        btnSelectTacGia2.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectTacGia2.setText("...");
        btnSelectTacGia2.setFocusable(false);
        btnSelectTacGia2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnSelectTacGia2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSelectTacGia2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectTacGia2ActionPerformed(evt);
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

        radioButtonCustom1.setBackground(new java.awt.Color(47, 55, 90));
        radioButtonCustom1.setForeground(new java.awt.Color(255, 255, 255));
        radioButtonCustom1.setText("Đang Hoạt Động");

        radioButtonCustom2.setBackground(new java.awt.Color(47, 55, 90));
        radioButtonCustom2.setForeground(new java.awt.Color(255, 255, 255));
        radioButtonCustom2.setText("Ngừng Hoạt Động");

        combobox1.setBackground(new java.awt.Color(47, 55, 90));
        combobox1.setForeground(new java.awt.Color(255, 255, 255));
        combobox1.setMaximumRowCount(10);
        combobox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        combobox1.setSelectedIndex(-1);
        combobox1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        combobox1.setLabeText("Nhà Xuất Bản");
        combobox1.setLineColor(new java.awt.Color(255, 255, 255));

        combobox2.setBackground(new java.awt.Color(47, 55, 90));
        combobox2.setForeground(new java.awt.Color(255, 255, 255));
        combobox2.setMaximumRowCount(10);
        combobox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        combobox2.setSelectedIndex(-1);
        combobox2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        combobox2.setLabeText("Vị Trí");
        combobox2.setLineColor(new java.awt.Color(255, 255, 255));

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
                        .addGap(268, 268, 268)
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addComponent(btnSelectTacGia2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textField12, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textField13, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSelectTacGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSelectTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addComponent(btnInBaoCao3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnInBaoCao4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnInBaoCao5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addComponent(radioButtonCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(radioButtonCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBourder1Layout.createSequentialGroup()
                                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combobox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textField7, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(textField9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combobox2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14))))
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
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combobox2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioButtonCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioButtonCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSelectTacGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSelectTacGia2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textField13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSelectTacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInBaoCao3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInBaoCao4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInBaoCao5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
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
        background.setIcon(new javax.swing.ImageIcon("C:\\Users\\quanc\\Downloads\\demobgr.png")); // NOI18N
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
        this.btnSelectTacGia.show(true);
        this.TruongThongTin.show(true);
//        revalidate();
    }//GEN-LAST:event_backgroundActionPerformed

    private void btnSelectTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectTacGiaActionPerformed
        this.lbNameForm.setText("Thêm Thể Loại");
//        this.txtReset.requestFocusInWindow();
        this.background.show(true);
        this.btnSelectTacGia.show(false);
        this.Form_Chon.show(true);
        this.TruongThongTin.show(false);
        //        this.background.doClick();
        //        this.jScrollPane1.show(true);

        revalidate();

    }//GEN-LAST:event_btnSelectTacGiaActionPerformed

    private void textField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField4ActionPerformed

    private void textField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField5ActionPerformed

    private void textField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField6ActionPerformed

    private void textField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField7ActionPerformed

    private void textField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField8ActionPerformed

    private void textField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField9ActionPerformed

    private void btnSelectTacGia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectTacGia1ActionPerformed
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
        this.btnSelectTacGia.show(false);
        this.Form_Chon.show(true);
        this.TruongThongTin.show(false);
        //        this.background.doClick();
        //        this.jScrollPane1.show(true);

        revalidate();
    }//GEN-LAST:event_btnSelectTacGia1ActionPerformed

    private void btnInBaoCao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao3ActionPerformed

    private void btnInBaoCao4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao4ActionPerformed

    private void btnInBaoCao5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao5ActionPerformed

    private void btnInBaoCao6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao6ActionPerformed

    private void textField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField10ActionPerformed

    private void btnSelectTacGia2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectTacGia2ActionPerformed
        this.lbNameForm.setText("Thêm Khuyến Mãi");
         String tacgia[] = {"KM",
            "Thế Phương",
            "Đức Anh",
            "Linh",
            "Hương",
            "Nam"};
        DefaultComboBoxModel model = new DefaultComboBoxModel(tacgia);
        this.cbSelect.setModel(model);
        this.background.show(true);
        this.btnSelectTacGia.show(false);
        this.Form_Chon.show(true);
        this.TruongThongTin.show(false);
        //        this.background.doClick();
        //        this.jScrollPane1.show(true);

        revalidate();
    }//GEN-LAST:event_btnSelectTacGia2ActionPerformed

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

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
     this.isToggle = true;
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.DesignComponent.JPanelBourder Form_Chon;
    private javax.swing.JPanel TruongThongTin;
    private View.ButtonDesign.Background background;
    private View.ButtonDesign.Button btnInBaoCao;
    private View.ButtonDesign.Button btnInBaoCao3;
    private View.ButtonDesign.Button btnInBaoCao4;
    private View.ButtonDesign.Button btnInBaoCao5;
    private View.ButtonDesign.Button btnInBaoCao6;
    private View.ButtonDesign.Button btnSelectTacGia;
    private View.ButtonDesign.Button btnSelectTacGia1;
    private View.ButtonDesign.Button btnSelectTacGia2;
    private View.ButtonDesign.Button button1;
    private View.ButtonDesign.Button button2;
    private View.ComboBoxDesign.ComboBoxSuggestion cbSelect;
    private View.DesignComponent.Combobox combobox1;
    private View.DesignComponent.Combobox combobox2;
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
    private javax.swing.JLabel lbAvatar;
    private javax.swing.JLabel lbNameForm;
    private View.ComboBoxDesign.RadioButtonCustom radioButtonCustom1;
    private View.ComboBoxDesign.RadioButtonCustom radioButtonCustom2;
    private javax.swing.JScrollPane scroll;
    private View.DesignComponent.TextField textField1;
    private View.DesignComponent.TextField textField10;
    private View.DesignComponent.TextField textField12;
    private View.DesignComponent.TextField textField13;
    private View.DesignComponent.TextField textField2;
    private View.DesignComponent.TextField textField3;
    private View.DesignComponent.TextField textField4;
    private View.DesignComponent.TextField textField5;
    private View.DesignComponent.TextField textField6;
    private View.DesignComponent.TextField textField7;
    private View.DesignComponent.TextField textField8;
    private View.DesignComponent.TextField textField9;
    // End of variables declaration//GEN-END:variables
}
