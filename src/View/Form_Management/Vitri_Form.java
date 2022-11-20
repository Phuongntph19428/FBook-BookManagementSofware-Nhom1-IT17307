/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import Model.SachFake;
import View.ButtonDesign.Button;
import View.ScrollBarCustom;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import model.ViTri;
import service.ViTriService;
import service.impl.ViTriServiceImpl;

/**
 *
 * @author quanc
 */
public class Vitri_Form extends javax.swing.JPanel {

    private List<ViTri> listViTri = new ArrayList<>();
    private List<Button> listBtn = new ArrayList<>();
    private ViTriService viTriService = new ViTriServiceImpl();
    private DefaultTableModel model = new DefaultTableModel();

    public Vitri_Form() {
        initComponents();
        String columns[] = {"ID", "Mã", "Mô Tả"};
        Object rows[][] = {};
        this.tableViTri.setModel(new DefaultTableModel(rows, columns));
        tableViTri.setDefaultEditor(Object.class, null);
        tableViTri.setBackground(Color.WHITE);
        this.tableViTri.setRowHeight(50);
        this.tableViTri.setBackground(Color.white);
//        initTableData();
        listViTri = viTriService.getAllViTri();
        loadViTri(listViTri);

    }

//    public void initTableData() {
//     
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        textField1 = new View.DesignComponent.TextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableViTri = new View.DesignComponent.Table();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtID = new View.DesignComponent.TextField();
        txtMaViTri = new View.DesignComponent.TextField();
        txtMoTa = new View.DesignComponent.TextField();
        btnSua = new View.ButtonDesign.Button();
        btnThem = new View.ButtonDesign.Button();
        btnInBaoCao6 = new View.ButtonDesign.Button();
        jSeparator1 = new javax.swing.JSeparator();

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
        jLabel2.setText("List of Location");

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

        tableViTri.setModel(new javax.swing.table.DefaultTableModel(
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
        tableViTri.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tableViTri.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tableViTri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableViTriMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableViTri);

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
        jLabel1.setText("Management Book - Quản Lý Vị Trí");

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

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(47, 55, 90));
        txtID.setForeground(new java.awt.Color(255, 255, 255));
        txtID.setCaretColor(new java.awt.Color(255, 255, 255));
        txtID.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtID.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtID.setLabelText("ID");
        txtID.setLineColor(new java.awt.Color(255, 255, 255));

        txtMaViTri.setBackground(new java.awt.Color(47, 55, 90));
        txtMaViTri.setForeground(new java.awt.Color(255, 255, 255));
        txtMaViTri.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMaViTri.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMaViTri.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMaViTri.setLabelText("Mã Vị Trí");
        txtMaViTri.setLineColor(new java.awt.Color(255, 255, 255));

        txtMoTa.setBackground(new java.awt.Color(47, 55, 90));
        txtMoTa.setForeground(new java.awt.Color(255, 255, 255));
        txtMoTa.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMoTa.setLabelText("Mô Tả");
        txtMoTa.setLineColor(new java.awt.Color(255, 255, 255));

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(txtMaViTri, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInBaoCao6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1))
                .addContainerGap(17, Short.MAX_VALUE))
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
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaViTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInBaoCao6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(10, 10, 10)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
     public void loadViTri(List<ViTri> listViTri) {
        model = (DefaultTableModel) tableViTri.getModel();
        model.setRowCount(0);
        for (ViTri viTri : listViTri) {
            model.addRow(new Object[]{viTri.getId(), viTri.getMa(), viTri.getMoTa()});
        }

    }
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int row = tableViTri.getSelectedRow();
        ViTri viTri = viTriService.getAllViTri().get(row);
        viTri.setMa(txtMaViTri.getText());
        viTri.setMoTa(txtMoTa.getText());
        viTriService.updateViTri(viTri);
        listViTri = viTriService.getAllViTri();
        loadViTri(listViTri);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        ViTri viTri = new ViTri();
        viTri.setMa(txtMaViTri.getText());
        viTri.setMoTa(txtMoTa.getText());
        viTriService.addViTri(viTri);
        listViTri = viTriService.getAllViTri();
        loadViTri(listViTri);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnInBaoCao6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao6ActionPerformed

    private void tableViTriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableViTriMouseClicked
        int row = tableViTri.getSelectedRow();
        ViTri viTri = listViTri.get(row);
        txtID.setText(viTri.getId());
        txtMaViTri.setText(viTri.getMa());
        txtMoTa.setText(viTri.getMoTa());
    }//GEN-LAST:event_tableViTriMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.ButtonDesign.Button btnInBaoCao6;
    private View.ButtonDesign.Button btnSua;
    private View.ButtonDesign.Button btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private View.DesignComponent.Table tableViTri;
    private View.DesignComponent.TextField textField1;
    private View.DesignComponent.TextField txtID;
    private View.DesignComponent.TextField txtMaViTri;
    private View.DesignComponent.TextField txtMoTa;
    // End of variables declaration//GEN-END:variables
}
