/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.ButtonDesign.Button;
import View.ScrollBarCustom;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.TacGia;
import service.TacGiaService;
import service.impl.TacGiaServiceImpl;

/**
 *
 * @author quanc
 */
public class TacGia_Form extends javax.swing.JPanel {

    private List<Button> listBtn = new ArrayList<>();

    private String currentDirectory;
    private final TacGiaService _tacGiaService = new TacGiaServiceImpl();
    private List<TacGia> _lstAllTacGia;
    private List<TacGia> _lstTacGia;

    private byte[] _hinh;

    public TacGia_Form() {
        initComponents();
        tblTacGia.setDefaultEditor(Object.class, null);
        tblTacGia.setBackground(Color.WHITE);
        this.tblTacGia.setRowHeight(50);
        this.tblTacGia.setBackground(Color.white);
        this.jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("image/imagedefault.jpg").getImage().getScaledInstance(195, 232, Image.SCALE_DEFAULT));
        this.lblAvatar.setIcon(imageIcon);
//        showTarget(1);

        _lstAllTacGia = _tacGiaService.selectAll();
        _lstTacGia = _lstAllTacGia;
        loadTable(_lstTacGia);
    }

    public void setColorButtonSelected(int index) {
        for (Button btn : listBtn) {
            btn.setBackground(Color.WHITE);
            btn.setForeground(Color.BLACK);
        }
        listBtn.get(index).setBackground(new Color(31, 31, 111));
        listBtn.get(index).setForeground(Color.WHITE);

    }

    private void loadTable(List<TacGia> lstTacGia) {

        DefaultTableModel dtm = (DefaultTableModel) tblTacGia.getModel();
        dtm.setRowCount(0);
        for (TacGia tacGia : lstTacGia) {
            dtm.addRow(tacGia.toDataRow());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        txtSearch = new View.DesignComponent.TextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTacGia = new View.DesignComponent.Table();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtId = new View.DesignComponent.TextField();
        txtTenTacGia = new View.DesignComponent.TextField();
        txtMaTacGia = new View.DesignComponent.TextField();
        txtMoTa = new View.DesignComponent.TextField();
        btnUpdate = new View.ButtonDesign.Button();
        btnAdd = new View.ButtonDesign.Button();
        btnClear = new View.ButtonDesign.Button();
        jSeparator1 = new javax.swing.JSeparator();
        lblAvatar = new javax.swing.JLabel();
        btnChooseImage = new View.ButtonDesign.Button();
        jSeparator2 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(11, 20, 55));
        setPreferredSize(new java.awt.Dimension(1234, 751));

        jPanelBourder1.setBackground(new java.awt.Color(17, 28, 68));

        txtSearch.setBackground(new java.awt.Color(17, 28, 68));
        txtSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSearch.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSearch.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        txtSearch.setLabelText("Tìm kiếm theo tên, mã");
        txtSearch.setLineColor(new java.awt.Color(255, 255, 255));
        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(104, 143, 222));
        jLabel2.setText("List of Author");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        tblTacGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Hình"
            }
        ));
        tblTacGia.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblTacGia.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTacGiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTacGia);
        if (tblTacGia.getColumnModel().getColumnCount() > 0) {
            tblTacGia.getColumnModel().getColumn(0).setMinWidth(100);
            tblTacGia.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblTacGia.getColumnModel().getColumn(0).setMaxWidth(100);
            tblTacGia.getColumnModel().getColumn(2).setMinWidth(100);
            tblTacGia.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblTacGia.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder2Layout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanelBourder3Layout = new javax.swing.GroupLayout(jPanelBourder3);
        jPanelBourder3.setLayout(jPanelBourder3Layout);
        jPanelBourder3Layout.setHorizontalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBourder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBourder3Layout.setVerticalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Management Book - Quản Lý Tác Giả");

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

        txtTenTacGia.setBackground(new java.awt.Color(47, 55, 90));
        txtTenTacGia.setForeground(new java.awt.Color(255, 255, 255));
        txtTenTacGia.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTenTacGia.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTenTacGia.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTenTacGia.setLabelText("Tên Tác Giả");
        txtTenTacGia.setLineColor(new java.awt.Color(255, 255, 255));

        txtMaTacGia.setBackground(new java.awt.Color(47, 55, 90));
        txtMaTacGia.setForeground(new java.awt.Color(255, 255, 255));
        txtMaTacGia.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMaTacGia.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMaTacGia.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMaTacGia.setLabelText("Mã Tác Giả");
        txtMaTacGia.setLineColor(new java.awt.Color(255, 255, 255));

        txtMoTa.setBackground(new java.awt.Color(47, 55, 90));
        txtMoTa.setForeground(new java.awt.Color(255, 255, 255));
        txtMoTa.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMoTa.setLabelText("Mô Tả");
        txtMoTa.setLineColor(new java.awt.Color(255, 255, 255));

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

        lblAvatar.setText("jLabel3");

        btnChooseImage.setBackground(new java.awt.Color(31, 31, 111));
        btnChooseImage.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnChooseImage.setForeground(new java.awt.Color(255, 255, 255));
        btnChooseImage.setText("Chọn Ảnh");
        btnChooseImage.setFocusable(false);
        btnChooseImage.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelBourder3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(15, Short.MAX_VALUE)
                        .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnChooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTenTacGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtMaTacGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                                            .addComponent(txtMoTa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(24, 24, 24))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelBourder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private TacGia getFrom() {
        String id = txtId.getText();
        String ma = txtMaTacGia.getText().trim();
        String ten = txtTenTacGia.getText().trim();
        String mota = txtMoTa.getText().trim();

        return new TacGia(id.isBlank() ? null : id, ma, ten, _hinh, mota.isBlank() ? null : mota);

    }

    private void setForm(TacGia tacGia) {
        txtId.setText(tacGia.getId());
        txtMaTacGia.setText(tacGia.getMa());
        txtTenTacGia.setText(tacGia.getTen());
        txtMoTa.setText(tacGia.getMoTa() == null ? "" : tacGia.getMoTa());
        _hinh = tacGia.getHinh();
        if (_hinh != null) {
            lblAvatar.setIcon(new ImageIcon(new ImageIcon(_hinh).getImage().getScaledInstance(174, 210, Image.SCALE_DEFAULT)));
        }

    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        TacGia tacGia = getFrom();
        if (tacGia.getId() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn tác giả");
            return;
        }
        _tacGiaService.updateTacGia(tacGia);
        _lstAllTacGia = _tacGiaService.selectAll();
        clear();
        loadTable(_lstAllTacGia);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        TacGia tacGia = getFrom();
        if (tacGia.getId() != null) {
            JOptionPane.showMessageDialog(this, "Clear form trước khi thêm");
            return;
        }
        _tacGiaService.insertTacGia(tacGia);
        _lstAllTacGia = _tacGiaService.selectAll();
        clear();
        loadTable(_lstAllTacGia);
    }//GEN-LAST:event_btnAddActionPerformed

    private void clear() {
        txtId.setText("");
        txtMaTacGia.setText("");
        txtMoTa.setText("");
        txtTenTacGia.setText("");
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
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
                    lblAvatar.setIcon(new ImageIcon(new ImageIcon(_hinh).getImage().getScaledInstance(174, 210, Image.SCALE_DEFAULT)));
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

    private void tblTacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTacGiaMouseClicked
        int row = tblTacGia.getSelectedRow();
        if (row == -1) {
            return;
        }
        setForm(_lstTacGia.get(row));
    }//GEN-LAST:event_tblTacGiaMouseClicked

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        String keyword = txtSearch.getText().trim();
        _lstTacGia = _tacGiaService.searchTacGiaByKeyWord(_lstAllTacGia, keyword);
        loadTable(_lstTacGia);
    }//GEN-LAST:event_txtSearchCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.ButtonDesign.Button btnAdd;
    private View.ButtonDesign.Button btnChooseImage;
    private View.ButtonDesign.Button btnClear;
    private View.ButtonDesign.Button btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAvatar;
    private View.DesignComponent.Table tblTacGia;
    private View.DesignComponent.TextField txtId;
    private View.DesignComponent.TextField txtMaTacGia;
    private View.DesignComponent.TextField txtMoTa;
    private View.DesignComponent.TextField txtSearch;
    private View.DesignComponent.TextField txtTenTacGia;
    // End of variables declaration//GEN-END:variables
}
