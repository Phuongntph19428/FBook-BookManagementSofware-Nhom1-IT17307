/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.DesignComponent;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Item_Pos extends javax.swing.JPanel {

    private ImageIcon imageIcon = null;

    public Item_Pos() {
        initComponents();

    }

    @Override
    public void setName(String name) {
        this.lbName.setText(name);
        System.out.println(lbName.getText().length());
    }

    public void setGia(int Gia) {
        this.lbGia.setText(Gia + " VND");
    }

    public void setGiaSale(int Gia) {
        this.lbGiaSale.setText(Gia + " VND");
    }

    public void setSTT(int index) {
        this.lbSTT.setText(index + "");
    }

    public int getSTT() {
        return Integer.parseInt(lbSTT.getText());
    }

    public void setIcon(ImageIcon imageIcon) {
        if (imageIcon == null) {
            return;
        }
        this.lbIcon.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(lbIcon.getWidth(), lbIcon.getHeight(), Image.SCALE_DEFAULT)));
        this.imageIcon = imageIcon;
    }
    
    public void hovering() {
        lbIcon.setSize(lbIcon.getWidth() + 10, lbIcon.getHeight() + 10);
        this.lbIcon.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(lbIcon.getWidth(), lbIcon.getHeight(), Image.SCALE_DEFAULT)));
        lbName.setForeground(new Color(0,255,0));
    }
    
    public void exited() {
        lbIcon.setSize(lbIcon.getWidth() - 10, lbIcon.getHeight() - 10);
        this.lbIcon.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(lbIcon.getWidth(), lbIcon.getHeight(), Image.SCALE_DEFAULT)));
        lbName.setForeground(new Color(255, 255, 255));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        lbName.setColumns(2);
        lbName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setLineWrap(true);
        lbName.setRows(20);
        lbName.setTabSize(20);
        lbName.setText("Tủ Sách Tuổi Thần Tiên: Trẻ Con Có Phải Siêu Nhân Đâu (Và Mẹ Chúng Cũng Thế\n");
        lbName.setWrapStyleWord(true);
        lbName.setBorder(null);
        lbName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public View.DesignComponent.JPanelBourder jPanelBourder1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lbGia;
    public javax.swing.JLabel lbGiaSale;
    public javax.swing.JLabel lbIcon;
    public javax.swing.JTextArea lbName;
    private javax.swing.JLabel lbSTT;
    public javax.swing.JLabel lblSoLuongTon;
    // End of variables declaration//GEN-END:variables
}
