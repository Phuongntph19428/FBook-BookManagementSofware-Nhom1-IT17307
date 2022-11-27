/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.DesignComponent;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.SachFake;

public class Item_Pos extends javax.swing.JPanel {

    public Item_Pos() {
        initComponents();

    }

    public void setName(String name) {
        this.lbName.setText(name);
    }

    public void setGia(int Gia) {
        this.lbGia.setText(Gia+" VND");
    }
    
    public void setSTT(int index) {
        this.lbSTT.setText(index+"");
    }
    public int getSTT() {
        return Integer.parseInt(lbSTT.getText());
    }
    
    public void setIcon(Icon icon) {
        this.lbIcon.setIcon(icon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        lbName = new javax.swing.JLabel();
        lbGia = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbIcon = new javax.swing.JLabel();
        lbSTT = new javax.swing.JLabel();

        setBackground(new java.awt.Color(17, 28, 68));
        setMaximumSize(new java.awt.Dimension(165, 219));
        setMinimumSize(new java.awt.Dimension(165, 219));
        setPreferredSize(new java.awt.Dimension(165, 219));

        jPanelBourder1.setBackground(new java.awt.Color(21, 32, 72));
        jPanelBourder1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanelBourder1.setLayout(null);

        lbName.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("Đắc Nhân Tâm");
        jPanelBourder1.add(lbName);
        lbName.setBounds(10, 140, 130, 30);

        lbGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbGia.setForeground(new java.awt.Color(255, 51, 0));
        lbGia.setText("50,000 VND");
        jPanelBourder1.add(lbGia);
        lbGia.setBounds(10, 166, 130, 30);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\quanc\\Downloads\\th_n_minh_nhanh_h_n_gi_i_h_n (1).jpg")); // NOI18N
        lbIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lbIcon)
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBourder1.add(jPanel1);
        jPanel1.setBounds(0, 0, 140, 140);

        lbSTT.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSTT, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(lbSTT))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private javax.swing.JLabel lbGia;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbSTT;
    // End of variables declaration//GEN-END:variables
}
