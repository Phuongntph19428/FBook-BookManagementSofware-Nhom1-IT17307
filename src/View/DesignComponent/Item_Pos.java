/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.DesignComponent;

import custommodel.CustomSach;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class Item_Pos extends javax.swing.JPanel {

    private ImageIcon imageIcon = null;

    private CustomSach _custom = null;

    public Item_Pos(CustomSach custom) {
        _custom = custom;
        initComponents();
        setGraphic(custom);
        addEvent();
    }

    private void addEvent() {
        lbIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (javax.swing.SwingUtilities.isRightMouseButton(e)) {
                    showDetailDialog(e);
                }
            }

        });

        lbName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (javax.swing.SwingUtilities.isRightMouseButton(e)) {
                    showDetailDialog(e);
                }
            }

        });
    }

    private void showDetailDialog(MouseEvent e) {
        JPopupMenu popup = new JPopupMenu();
        JMenuItem item = new JMenuItem("Thông tin chi tiết");
        popup.add(item);
        popup.show(e.getComponent(), e.getX(), e.getY());
        item.addActionListener((java.awt.event.ActionEvent evt) -> {
            loadDialogProductDetail();
        });
    }

    private void loadDialogProductDetail() {
        if (_custom != null) {
            if (_custom.getHinh() == null) {
                return;
            }

            this.imageIcon = new ImageIcon(_custom.getHinh());
            this.lblHinhDetail.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(320, 320, Image.SCALE_DEFAULT)));
            this.lblTenSachDetail.setText(_custom.getTen());
            this.lblNhaXuatBan.setText(_custom.getNhaXB());
            this.lblTacGia.setText(_custom.getTacGia());
            this.lblTheLoai.setText(_custom.getTheLoai());
            this.lblViTri.setText(_custom.getViTri());
            this.lblSoLuong.setText("<html> <span style='background-color: #34568b; color: white'>Số lượng: " + _custom.getSoLuong() + "</span></html>");
            this.lblGia.setText("<html> " + df.format(_custom.getGiaSale()) + " đ <s style='color: white; font-size: 16px'> " + df.format(_custom.getGiaBan()) + " đ</s> <span style='background-color: FF0000; color: white'> -" + _custom.getChietKhau() + " %</span></html>");
            DialogChiTietSP.setModal(true);
            DialogChiTietSP.setSize(1050, 536);
            DialogChiTietSP.setLocationRelativeTo(null);
            DialogChiTietSP.setVisible(true);
        }
    }

    private final DecimalFormat df = new DecimalFormat("#,###");

    private void setGraphic(CustomSach custom) {
        this.lbName.setText(custom.getTen());
        this.lbGia.setText("<html><s>" + df.format(custom.getGiaBan()) + " VNĐ" + "</s><sup style=\"color: 5DEB1B\"> - " + custom.getChietKhau() + "%</sup></html>");
        this.lblSoLuongTon.setText("Số lượng tồn: " + custom.getSoLuong());
        this.lbGiaSale.setText("<html>" + df.format(custom.getGiaSale()) + "<sup> vnđ<sup></hmtl>");
        if (custom.getHinh() == null) {
            return;
        }
        this.imageIcon = new ImageIcon(custom.getHinh());
        this.lbIcon.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(lbIcon.getWidth(), lbIcon.getHeight(), Image.SCALE_DEFAULT)));
    }

    public void setSTT(int index) {
        this.lbSTT.setText(index + "");
    }

    public int getSTT() {
        return Integer.parseInt(lbSTT.getText());
    }

    public void hovering() {
        if (imageIcon != null) {
            lbIcon.setSize(lbIcon.getWidth() + 10, lbIcon.getHeight() + 10);
            this.lbIcon.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(lbIcon.getWidth(), lbIcon.getHeight(), Image.SCALE_DEFAULT)));
        }
        lbName.setForeground(new Color(0, 255, 0));
    }

    public void exited() {
        if (imageIcon != null) {
            lbIcon.setSize(lbIcon.getWidth() - 10, lbIcon.getHeight() - 10);
            this.lbIcon.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(lbIcon.getWidth(), lbIcon.getHeight(), Image.SCALE_DEFAULT)));
        }
        lbName.setForeground(new Color(255, 255, 255));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DialogChiTietSP = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        lblHinhDetail = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lblTenSachDetail = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        lblNhaXuatBan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTacGia = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTheLoai = new javax.swing.JLabel();
        lblViTri = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        lbGia = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbGiaSale = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbName = new javax.swing.JTextArea();
        lblSoLuongTon = new javax.swing.JLabel();
        lbSTT = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(11, 20, 55));

        lblHinhDetail.setMaximumSize(new java.awt.Dimension(320, 320));
        lblHinhDetail.setMinimumSize(new java.awt.Dimension(320, 320));
        lblHinhDetail.setPreferredSize(new java.awt.Dimension(320, 320));

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 0, 28)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 255, 204));
        jLabel13.setText("Chi tiết sách");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        lblTenSachDetail.setEditable(false);
        lblTenSachDetail.setBackground(new java.awt.Color(11, 20, 55));
        lblTenSachDetail.setColumns(1);
        lblTenSachDetail.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTenSachDetail.setForeground(new java.awt.Color(255, 255, 255));
        lblTenSachDetail.setLineWrap(true);
        lblTenSachDetail.setRows(1);
        lblTenSachDetail.setTabSize(1);
        lblTenSachDetail.setText("Tủ Sách Tuổi Thần Tiên: Trẻ Con Có Phải Siêu Nhân Đâu (Và Mẹ Chúng Cũng Thế ");
        lblTenSachDetail.setWrapStyleWord(true);
        lblTenSachDetail.setBorder(null);
        lblTenSachDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTenSachDetail.setMaximumSize(new java.awt.Dimension(101, 20));
        lblTenSachDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTenSachDetailMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTenSachDetailMouseExited(evt);
            }
        });
        jScrollPane3.setViewportView(lblTenSachDetail);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nhà xuất bản:");

        lblNhaXuatBan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblNhaXuatBan.setForeground(new java.awt.Color(0, 255, 255));
        lblNhaXuatBan.setText("Nhà xuất bản");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tác giả:");

        lblTacGia.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTacGia.setForeground(new java.awt.Color(0, 255, 255));
        lblTacGia.setText("Tác giả");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Thể loại:");

        lblTheLoai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTheLoai.setForeground(new java.awt.Color(0, 255, 255));
        lblTheLoai.setText("Tác giả");

        lblViTri.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblViTri.setForeground(new java.awt.Color(0, 255, 255));
        lblViTri.setText("Tác giả");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Vị trí:");

        lblGia.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblGia.setForeground(new java.awt.Color(255, 0, 0));
        lblGia.setText("20000000000 đ");

        lblSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSoLuong.setForeground(new java.awt.Color(255, 255, 255));
        lblSoLuong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSoLuong.setText("Số lượng:");
        lblSoLuong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblSoLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblHinhDetail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblViTri, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblGia, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel13)
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHinhDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblNhaXuatBan))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblTacGia))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblTheLoai))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblViTri))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblGia)))
                .addGap(18, 18, 18)
                .addComponent(lblSoLuong)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DialogChiTietSPLayout = new javax.swing.GroupLayout(DialogChiTietSP.getContentPane());
        DialogChiTietSP.getContentPane().setLayout(DialogChiTietSPLayout);
        DialogChiTietSPLayout.setHorizontalGroup(
            DialogChiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
            .addGroup(DialogChiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DialogChiTietSPLayout.setVerticalGroup(
            DialogChiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
            .addGroup(DialogChiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DialogChiTietSPLayout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setBackground(new java.awt.Color(17, 28, 68));
        setMaximumSize(new java.awt.Dimension(165, 256));
        setMinimumSize(new java.awt.Dimension(165, 256));
        setPreferredSize(new java.awt.Dimension(165, 219));

        jPanelBourder1.setBackground(new java.awt.Color(21, 32, 72));
        jPanelBourder1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanelBourder1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelBourder1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelBourder1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelBourder1MouseExited(evt);
            }
        });
        jPanelBourder1.setLayout(null);

        lbGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbGia.setForeground(new java.awt.Color(255, 51, 0));
        lbGia.setText("50, 000VND");
        lbGia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbGiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbGiaMouseExited(evt);
            }
        });
        jPanelBourder1.add(lbGia);
        lbGia.setBounds(10, 220, 130, 20);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel1MouseExited(evt);
            }
        });
        jPanel1.setLayout(null);

        lbGiaSale.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbGiaSale.setText("25,000 VND");
        lbGiaSale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbGiaSaleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbGiaSaleMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lbGiaSale, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lbGiaSale)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 100, 20);

        lbIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbIconMouseExited(evt);
            }
        });
        jPanel1.add(lbIcon);
        lbIcon.setBounds(28, 6, 100, 110);

        jPanelBourder1.add(jPanel1);
        jPanel1.setBounds(0, 0, 154, 120);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        lbName.setEditable(false);
        lbName.setBackground(new java.awt.Color(17, 28, 68));
        lbName.setColumns(1);
        lbName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setLineWrap(true);
        lbName.setRows(1);
        lbName.setTabSize(1);
        lbName.setText("Tủ Sách Tuổi Thần Tiên: Trẻ Con Có Phải Siêu Nhân Đâu (Và Mẹ Chúng Cũng Thế ");
        lbName.setWrapStyleWord(true);
        lbName.setBorder(null);
        lbName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbName.setMaximumSize(new java.awt.Dimension(101, 20));
        lbName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbNameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbNameMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(lbName);

        jPanelBourder1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 120, 140, 60);

        lblSoLuongTon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSoLuongTon.setForeground(new java.awt.Color(255, 255, 255));
        lblSoLuongTon.setText("Số lượng tồn: ");
        lblSoLuongTon.setToolTipText("");
        lblSoLuongTon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSoLuongTon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSoLuongTonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSoLuongTonMouseExited(evt);
            }
        });
        jPanelBourder1.add(lblSoLuongTon);
        lblSoLuongTon.setBounds(10, 190, 130, 20);

        lbSTT.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSTT, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(lbSTT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        hovering();
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseExited
        exited();
    }//GEN-LAST:event_jPanel1MouseExited

    private void jPanelBourder1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBourder1MouseEntered
        hovering();
    }//GEN-LAST:event_jPanelBourder1MouseEntered

    private void jPanelBourder1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBourder1MouseExited
        exited();
    }//GEN-LAST:event_jPanelBourder1MouseExited

    private void lbNameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNameMouseEntered
        hovering();
    }//GEN-LAST:event_lbNameMouseEntered

    private void lbNameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNameMouseExited
        exited();
    }//GEN-LAST:event_lbNameMouseExited

    private void lbGiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbGiaMouseEntered
        hovering();
    }//GEN-LAST:event_lbGiaMouseEntered

    private void lbGiaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbGiaMouseExited
        exited();
    }//GEN-LAST:event_lbGiaMouseExited

    private void lbGiaSaleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbGiaSaleMouseEntered
        hovering();
    }//GEN-LAST:event_lbGiaSaleMouseEntered

    private void lbGiaSaleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbGiaSaleMouseExited
        exited();
    }//GEN-LAST:event_lbGiaSaleMouseExited

    private void lblSoLuongTonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSoLuongTonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSoLuongTonMouseEntered

    private void lblSoLuongTonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSoLuongTonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSoLuongTonMouseExited

    private void jPanelBourder1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBourder1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelBourder1MouseClicked

    private void lbIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbIconMouseEntered
        hovering();
    }//GEN-LAST:event_lbIconMouseEntered

    private void lbIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbIconMouseExited
        exited();
    }//GEN-LAST:event_lbIconMouseExited

    private void lblTenSachDetailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTenSachDetailMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTenSachDetailMouseEntered

    private void lblTenSachDetailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTenSachDetailMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTenSachDetailMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogChiTietSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public View.DesignComponent.JPanelBourder jPanelBourder1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JLabel lbGia;
    public javax.swing.JLabel lbGiaSale;
    public javax.swing.JLabel lbIcon;
    public javax.swing.JTextArea lbName;
    private javax.swing.JLabel lbSTT;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblHinhDetail;
    private javax.swing.JLabel lblNhaXuatBan;
    private javax.swing.JLabel lblSoLuong;
    public javax.swing.JLabel lblSoLuongTon;
    private javax.swing.JLabel lblTacGia;
    public javax.swing.JTextArea lblTenSachDetail;
    private javax.swing.JLabel lblTheLoai;
    private javax.swing.JLabel lblViTri;
    // End of variables declaration//GEN-END:variables
}
