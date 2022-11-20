/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.ScrollBarCustom;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author quanc
 */
public class KhachHang_Form extends javax.swing.JPanel {

   
    public KhachHang_Form() {
        initComponents();
        this.table1.setRowHeight(59);
        this.table1.setBackground(Color.white);
        this.jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        Icon iconbtnNgayBatDau = new ImageIcon("image/icons8_schedule_30px.png");
        this.btnNgaySinh.setIcon(iconbtnNgayBatDau);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ngaysinh = new View.DateChooser.DateChooser();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        textField1 = new View.DesignComponent.TextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new View.DesignComponent.Table();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        textField2 = new View.DesignComponent.TextField();
        textField3 = new View.DesignComponent.TextField();
        textField4 = new View.DesignComponent.TextField();
        textField5 = new View.DesignComponent.TextField();
        btnInBaoCao4 = new View.ButtonDesign.Button();
        btnInBaoCao5 = new View.ButtonDesign.Button();
        btnInBaoCao6 = new View.ButtonDesign.Button();
        jSeparator1 = new javax.swing.JSeparator();
        txtNgaySinh = new View.DesignComponent.TextField();
        btnNgaySinh = new View.ButtonDesign.Button();
        textField6 = new View.DesignComponent.TextField();
        jLabel3 = new javax.swing.JLabel();
        radioButtonCustom1 = new View.ComboBoxDesign.RadioButtonCustom();
        radioButtonCustom2 = new View.ComboBoxDesign.RadioButtonCustom();
        textField7 = new View.DesignComponent.TextField();
        textField8 = new View.DesignComponent.TextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        ngaysinh.setForeground(new java.awt.Color(47, 55, 90));
        ngaysinh.setTextRefernce(txtNgaySinh);

        setBackground(new java.awt.Color(11, 20, 55));

        jPanelBourder1.setBackground(new java.awt.Color(17, 28, 68));

        textField1.setBackground(new java.awt.Color(17, 28, 68));
        textField1.setForeground(new java.awt.Color(255, 255, 255));
        textField1.setCaretColor(new java.awt.Color(255, 255, 255));
        textField1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        textField1.setLabelText("Tìm Kiếm Theo Tên");
        textField1.setLineColor(new java.awt.Color(255, 255, 255));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 644, Short.MAX_VALUE)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        table1.setModel(new javax.swing.table.DefaultTableModel(
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
        table1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        table1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(table1);

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

        textField2.setEditable(false);
        textField2.setBackground(new java.awt.Color(47, 55, 90));
        textField2.setForeground(new java.awt.Color(255, 255, 255));
        textField2.setCaretColor(new java.awt.Color(255, 255, 255));
        textField2.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField2.setLabelText("ID");
        textField2.setLineColor(new java.awt.Color(255, 255, 255));

        textField3.setBackground(new java.awt.Color(47, 55, 90));
        textField3.setForeground(new java.awt.Color(255, 255, 255));
        textField3.setCaretColor(new java.awt.Color(255, 255, 255));
        textField3.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField3.setLabelText("Tên");
        textField3.setLineColor(new java.awt.Color(255, 255, 255));

        textField4.setBackground(new java.awt.Color(47, 55, 90));
        textField4.setForeground(new java.awt.Color(255, 255, 255));
        textField4.setCaretColor(new java.awt.Color(255, 255, 255));
        textField4.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField4.setLabelText("Mã Khách Hàng");
        textField4.setLineColor(new java.awt.Color(255, 255, 255));

        textField5.setBackground(new java.awt.Color(47, 55, 90));
        textField5.setForeground(new java.awt.Color(255, 255, 255));
        textField5.setCaretColor(new java.awt.Color(255, 255, 255));
        textField5.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField5.setLabelText("Tên Đệm");
        textField5.setLineColor(new java.awt.Color(255, 255, 255));

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
        btnInBaoCao5.setText("Tạo Mới");
        btnInBaoCao5.setFocusable(false);
        btnInBaoCao5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao5ActionPerformed(evt);
            }
        });

        btnInBaoCao6.setBackground(new java.awt.Color(31, 31, 111));
        btnInBaoCao6.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao6.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao6.setText("Làm Mới");
        btnInBaoCao6.setFocusable(false);
        btnInBaoCao6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao6ActionPerformed(evt);
            }
        });

        txtNgaySinh.setBackground(new java.awt.Color(47, 55, 90));
        txtNgaySinh.setForeground(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setText("");
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

        textField6.setBackground(new java.awt.Color(47, 55, 90));
        textField6.setForeground(new java.awt.Color(255, 255, 255));
        textField6.setCaretColor(new java.awt.Color(255, 255, 255));
        textField6.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField6.setLabelText("Họ");
        textField6.setLineColor(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Giới Tính");

        radioButtonCustom1.setBackground(new java.awt.Color(47, 55, 90));
        radioButtonCustom1.setForeground(new java.awt.Color(255, 255, 255));
        radioButtonCustom1.setText("Nam");

        radioButtonCustom2.setBackground(new java.awt.Color(47, 55, 90));
        radioButtonCustom2.setForeground(new java.awt.Color(255, 255, 255));
        radioButtonCustom2.setText("Nữ");

        textField7.setBackground(new java.awt.Color(47, 55, 90));
        textField7.setForeground(new java.awt.Color(255, 255, 255));
        textField7.setCaretColor(new java.awt.Color(255, 255, 255));
        textField7.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField7.setLabelText("Tên Đệm");
        textField7.setLineColor(new java.awt.Color(255, 255, 255));

        textField8.setBackground(new java.awt.Color(47, 55, 90));
        textField8.setForeground(new java.awt.Color(255, 255, 255));
        textField8.setCaretColor(new java.awt.Color(255, 255, 255));
        textField8.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        textField8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        textField8.setLabelText("Tên Đệm");
        textField8.setLineColor(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Điểm Tích Lũy :");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("1000 điểm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInBaoCao5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnInBaoCao4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnInBaoCao6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(824, 824, 824)
                        .addComponent(jLabel4)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanelBourder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(radioButtonCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(26, 26, 26)
                                            .addComponent(radioButtonCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(30, 30, 30)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addComponent(jSeparator1)))
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
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioButtonCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(radioButtonCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInBaoCao5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInBaoCao4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInBaoCao6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInBaoCao4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao4ActionPerformed

    private void btnInBaoCao5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao5ActionPerformed

    private void btnInBaoCao6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao6ActionPerformed

    private void btnNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgaySinhActionPerformed
        ngaysinh.showPopup();
    }//GEN-LAST:event_btnNgaySinhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.ButtonDesign.Button btnInBaoCao4;
    private View.ButtonDesign.Button btnInBaoCao5;
    private View.ButtonDesign.Button btnInBaoCao6;
    private View.ButtonDesign.Button btnNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private View.DateChooser.DateChooser ngaysinh;
    private View.ComboBoxDesign.RadioButtonCustom radioButtonCustom1;
    private View.ComboBoxDesign.RadioButtonCustom radioButtonCustom2;
    private View.DesignComponent.Table table1;
    private View.DesignComponent.TextField textField1;
    private View.DesignComponent.TextField textField2;
    private View.DesignComponent.TextField textField3;
    private View.DesignComponent.TextField textField4;
    private View.DesignComponent.TextField textField5;
    private View.DesignComponent.TextField textField6;
    private View.DesignComponent.TextField textField7;
    private View.DesignComponent.TextField textField8;
    private View.DesignComponent.TextField txtNgaySinh;
    // End of variables declaration//GEN-END:variables
}
