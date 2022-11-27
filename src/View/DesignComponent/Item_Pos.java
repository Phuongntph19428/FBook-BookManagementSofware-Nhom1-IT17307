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
        System.out.println(lbName.getText().length());
    }

    public void setGia(int Gia) {
        this.lbGia.setText(Gia+" VND");
    }
    
    public void setGiaSale(int Gia) {
        this.lbGiaSale.setText(Gia+" VND");
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
        lbGia = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbGiaSale = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbName = new javax.swing.JTextArea();
        lbSTT = new javax.swing.JLabel();

        setBackground(new java.awt.Color(17, 28, 68));
        setMaximumSize(new java.awt.Dimension(165, 219));
        setMinimumSize(new java.awt.Dimension(165, 219));
        setPreferredSize(new java.awt.Dimension(165, 219));

        jPanelBourder1.setBackground(new java.awt.Color(21, 32, 72));
        jPanelBourder1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanelBourder1.setLayout(null);

        lbGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbGia.setForeground(new java.awt.Color(255, 51, 0));
        lbGia.setText("50,000 VND");
        jPanelBourder1.add(lbGia);
        lbGia.setBounds(10, 190, 130, 10);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        lbGiaSale.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbGiaSale.setForeground(new java.awt.Color(255, 51, 0));
        lbGiaSale.setText("25,000 VND");

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

        lbIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\quanc\\Downloads\\th_n_minh_nhanh_h_n_gi_i_h_n (1).jpg")); // NOI18N
        lbIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lbIcon);
        lbIcon.setBounds(48, 6, 75, 130);

        jPanelBourder1.add(jPanel1);
        jPanel1.setBounds(0, 0, 154, 130);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        lbName.setBackground(new java.awt.Color(17, 28, 68));
        lbName.setColumns(2);
        lbName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setLineWrap(true);
        lbName.setRows(20);
        lbName.setTabSize(20);
        lbName.setText("Tủ Sách Tuổi Thần Tiên: Trẻ Con Có Phải Siêu Nhân Đâu (Và Mẹ Chúng Cũng Thế\n");
        lbName.setBorder(null);
        jScrollPane1.setViewportView(lbName);

        jPanelBourder1.add(jScrollPane1);
        jScrollPane1.setBounds(5, 130, 140, 60);

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
                .addContainerGap()
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbGia;
    private javax.swing.JLabel lbGiaSale;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JTextArea lbName;
    private javax.swing.JLabel lbSTT;
    // End of variables declaration//GEN-END:variables
}
