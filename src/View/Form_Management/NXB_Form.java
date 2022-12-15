/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import model.SachFake;
import View.ButtonDesign.Button;
import View.ScrollBarCustom;
import View.ThongBao;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NhaXuatBan;
import service.NhaXuatBanService;
import service.impl.NhaXuatBanServicelmpl;

/**
 *
 * @author quanc
 */
public class NXB_Form extends javax.swing.JPanel {

// 
    private List<SachFake> listS = new ArrayList<>();
    private List<Button> listBtn = new ArrayList<>();
    private List<NhaXuatBan> listNxb = new ArrayList<>();
    private final NhaXuatBanService NhaXuatBanServicer;

    public NXB_Form() {
        initComponents();
        String columns[] = {"ID", "Mã nhà xuất bản", "Tên nhà xuất bản ", "Mô tả nhà xuất bản"};
        Object rows[][] = {};
        NhaXuatBanServicer = new NhaXuatBanServicelmpl();
        this.tbNXB.setModel(new DefaultTableModel(rows, columns));
        tbNXB.setDefaultEditor(Object.class, null);
        tbNXB.setBackground(Color.WHITE);
        this.tbNXB.setRowHeight(50);
        this.tbNXB.setBackground(Color.white);
        this.jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        initTableData();
        xoaFrom();
    }

    public void initTableData1() {
        // Nơi đổ dữ liệu vào table
        // Ví dụ về đối tượng SachFake khi add Row ( sẽ tùy vào đối tượng để sửa các trường
        //new ModelTest(new ImageIcon("image/bookmark_30px.png"), "Bora", "Male", "C#", 300).toRowTable()
        //new Object[]{new ModelProfile(icon, name), Ma, soLuong, df.format(giaBan), barCode, moTa}
//        ImageIcon imageIcon = new ImageIcon(new ImageIcon("image/dacnhantam.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
////        this.table1.addRow(new SachFake("SKL1", imageIcon, "Sách 1", 100, 30000, "01293123", "Mới vl").toRowTable());
//
//        for (int i = 0; i <= 160; i++) {
//            listS.add(new SachFake("SKL1", imageIcon, "Sách " + i, 100, 30000, "01293123", "Mới vl"));
//        }
//        for (SachFake sach : listS) {
//            tbNXB.addRow(sach.toRowTable());
//        }

    }

    public void LoadTablebySearch(List<NhaXuatBan> list) {
        DefaultTableModel dtm = (DefaultTableModel) tbNXB.getModel();
        dtm.setRowCount(0);
        for (NhaXuatBan nxb : list) {
            dtm.addRow(nxb.toDaTaRow());
        }
    }

    public void initTableData() {
        listNxb = NhaXuatBanServicer.selectAll();
        System.out.println(listNxb.size());
        DefaultTableModel dtm = (DefaultTableModel) tbNXB.getModel();
        dtm.setRowCount(0);
        for (NhaXuatBan nxb : listNxb) {
            dtm.addRow(nxb.toDaTaRow());
        }
    }

    public void setColorButtonSelected(int index) {
        for (Button btn : listBtn) {
            btn.setBackground(Color.WHITE);
            btn.setForeground(Color.BLACK);
        }
        listBtn.get(index).setBackground(new Color(31, 31, 111));
        listBtn.get(index).setForeground(Color.WHITE);

    }

    private void fillTable(int row) {
//        NhaXuatBan nxb = listNxb.get(row);
//        txtIdNXB.setText(nxb.getId());
//        txtMaNXB.setText(nxb.getMa());
//        txtTenNXB.setText(nxb.getTen());
//        txtMoTa.setText(nxb.getMoTa());
        txtIdNXB.setText(tbNXB.getValueAt(row, 0).toString());
        txtMaNXB.setText(tbNXB.getValueAt(row, 1).toString());
        txtTenNXB.setText(tbNXB.getValueAt(row, 2).toString());
        txtMoTa.setText(tbNXB.getValueAt(row, 3).toString());

    }

    private void xoaFrom() {
        txtIdNXB.setText("");
        txtMaNXB.setText("");
        txtTenNXB.setText("");
        txtMoTa.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        txtTimKiem = new View.DesignComponent.TextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNXB = new View.DesignComponent.Table();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtIdNXB = new View.DesignComponent.TextField();
        txtTenNXB = new View.DesignComponent.TextField();
        txtMaNXB = new View.DesignComponent.TextField();
        txtMoTa = new View.DesignComponent.TextField();
        btnCapNhat = new View.ButtonDesign.Button();
        btnTaoMoi = new View.ButtonDesign.Button();
        btnLamMoi = new View.ButtonDesign.Button();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(11, 20, 55));
        setMaximumSize(new java.awt.Dimension(1435, 1014));
        setPreferredSize(new java.awt.Dimension(1373, 735));

        jPanelBourder1.setBackground(new java.awt.Color(17, 28, 68));

        txtTimKiem.setBackground(new java.awt.Color(17, 28, 68));
        txtTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        txtTimKiem.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTimKiem.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTimKiem.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        txtTimKiem.setLabelText("Tìm Kiếm Theo Tên");
        txtTimKiem.setLineColor(new java.awt.Color(255, 255, 255));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(104, 143, 222));
        jLabel2.setText("List of Publishing Company");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addContainerGap()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        tbNXB.setModel(new javax.swing.table.DefaultTableModel(
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
        tbNXB.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tbNXB.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tbNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNXBMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNXB);

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        jLabel1.setText("Management Book - Quản Lý Nhà Xuất Bản");

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

        txtIdNXB.setEditable(false);
        txtIdNXB.setBackground(new java.awt.Color(47, 55, 90));
        txtIdNXB.setForeground(new java.awt.Color(255, 255, 255));
        txtIdNXB.setCaretColor(new java.awt.Color(255, 255, 255));
        txtIdNXB.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtIdNXB.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtIdNXB.setLabelText("ID");
        txtIdNXB.setLineColor(new java.awt.Color(255, 255, 255));

        txtTenNXB.setBackground(new java.awt.Color(47, 55, 90));
        txtTenNXB.setForeground(new java.awt.Color(255, 255, 255));
        txtTenNXB.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTenNXB.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTenNXB.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTenNXB.setLabelText("Tên Nhà Xuất Bản");
        txtTenNXB.setLineColor(new java.awt.Color(255, 255, 255));

        txtMaNXB.setBackground(new java.awt.Color(47, 55, 90));
        txtMaNXB.setForeground(new java.awt.Color(255, 255, 255));
        txtMaNXB.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMaNXB.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMaNXB.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMaNXB.setLabelText("Mã Nhà Xuất Bản");
        txtMaNXB.setLineColor(new java.awt.Color(255, 255, 255));

        txtMoTa.setBackground(new java.awt.Color(47, 55, 90));
        txtMoTa.setForeground(new java.awt.Color(255, 255, 255));
        txtMoTa.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMoTa.setLabelText("Mô Tả");
        txtMoTa.setLineColor(new java.awt.Color(255, 255, 255));

        btnCapNhat.setBackground(new java.awt.Color(31, 31, 111));
        btnCapNhat.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.setFocusable(false);
        btnCapNhat.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnTaoMoi.setBackground(new java.awt.Color(31, 31, 111));
        btnTaoMoi.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnTaoMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoMoi.setText("Tạo Mới");
        btnTaoMoi.setFocusable(false);
        btnTaoMoi.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelBourder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBourder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTaoMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1))
                .addContainerGap(16, Short.MAX_VALUE))
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
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(10, 10, 10)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        NhaXuatBan nxb = getDaTa();
        int row = tbNXB.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "chon dong can cap nhat");
            return;
        } else if (nxb == null) {
            JOptionPane.showMessageDialog(this, "khong duoc de trong");
            return;
        } else {
            NhaXuatBanServicer.update(nxb);
            JOptionPane.showMessageDialog(this, "Cap nhat thanh cong");
            initTableData();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        // TODO add your handling code here:
        NhaXuatBan nxb = getDaTa();
        if (nxb == null) {

            return;
        }
        for (NhaXuatBan nxb1 : listNxb) {
            if (nxb1.getMa().equals(txtMaNXB.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Trùng mã Nhà Xuất bản", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);

                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Tao moi thanh cong");
        NhaXuatBanServicer.insert(nxb);
        xoaFrom();
        initTableData();
    }//GEN-LAST:event_btnTaoMoiActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
//        txtIdNXB.setText("");
//        txtMaNXB.setText("");
//        txtTenNXB.setText("");
//        txtMoTa.setText("");
//        initTableData
        xoaFrom();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tbNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNXBMouseClicked
        // TODO add your handling code here:
        int row = tbNXB.getSelectedRow();
        fillTable(row);
    }//GEN-LAST:event_tbNXBMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String txtName = this.txtTimKiem.getText().trim();
        System.out.println(txtName);
        List<NhaXuatBan> list = this.NhaXuatBanServicer.SelectbyName(txtName);
        LoadTablebySearch(list);
    }//GEN-LAST:event_txtTimKiemKeyReleased
    private NhaXuatBan getDaTa() {
        String id = txtIdNXB.getText().trim();
        String ma = txtMaNXB.getText().trim();
        String ten = txtTenNXB.getText().trim();
        String moTa = txtMoTa.getText().trim();
        NhaXuatBan nhaXuatBan = new NhaXuatBan(id, ma, ten, moTa);
        
        if(ma.isBlank() || ten.isBlank() || moTa.isBlank()){
            ThongBao.showNoti_Error(this, "Không được để chống");
        }
        
        if(ma.length() > 30){
            ThongBao.showNoti_Error(this, "Mã nhà xuất bản không được quá 30 ký tự");
            return null;
        }
         if(ten.length() > 50){
            ThongBao.showNoti_Error(this, "Tên nhà xuất bản không được quá 50 ký tự");
            return null;
        }
         
          if(moTa.length() > 100){
            ThongBao.showNoti_Error(this, "Mô tả nhà xuất bản không được quá 100 ký tự");
            return null;
        }
        return nhaXuatBan;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.ButtonDesign.Button btnCapNhat;
    private View.ButtonDesign.Button btnLamMoi;
    private View.ButtonDesign.Button btnTaoMoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private View.DesignComponent.Table tbNXB;
    private View.DesignComponent.TextField txtIdNXB;
    private View.DesignComponent.TextField txtMaNXB;
    private View.DesignComponent.TextField txtMoTa;
    private View.DesignComponent.TextField txtTenNXB;
    private View.DesignComponent.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
