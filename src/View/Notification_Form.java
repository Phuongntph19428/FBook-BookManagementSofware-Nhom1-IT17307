/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author quanc
 */
public class Notification_Form extends javax.swing.JPanel {

    

    public Notification_Form() {
        initComponents();
        Icon icon = new ImageIcon("image/icons8_help_45px.png");
        lbIcon.setIcon(icon);
    }

    public JButton getButtonYes() {
        return this.btnYes;
    }

    public JButton getButtonNo() {
        return this.btnNo;
    }
    public void setText(String text){
        this.lbText.setText(text);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNo = new View.ButtonDesign.Button();
        btnYes = new View.ButtonDesign.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbText = new javax.swing.JTextArea();
        lbIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(null);

        btnNo.setBackground(new java.awt.Color(204, 0, 0));
        btnNo.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnNo.setForeground(new java.awt.Color(255, 255, 255));
        btnNo.setText("No");
        btnNo.setFocusable(false);
        btnNo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnNo.setRound(0);
        btnNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoActionPerformed(evt);
            }
        });
        add(btnNo);
        btnNo.setBounds(220, 170, 230, 50);

        btnYes.setBackground(new java.awt.Color(0, 153, 51));
        btnYes.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnYes.setForeground(new java.awt.Color(255, 255, 255));
        btnYes.setText("Yes");
        btnYes.setFocusable(false);
        btnYes.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnYes.setRound(0);
        btnYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYesActionPerformed(evt);
            }
        });
        add(btnYes);
        btnYes.setBounds(0, 170, 230, 50);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        lbText.setEditable(false);
        lbText.setBackground(new java.awt.Color(255, 255, 255));
        lbText.setColumns(20);
        lbText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbText.setLineWrap(true);
        lbText.setRows(5);
        lbText.setText("Bạn có xác nhận muốn thao tác không ?");
        lbText.setBorder(null);
        jScrollPane1.setViewportView(lbText);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 100, 420, 60);

        lbIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\quanc\\Downloads\\icons8_box_important_55px.png")); // NOI18N
        add(lbIcon);
        lbIcon.setBounds(190, 10, 60, 60);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel1.setText("Are You Sure");
        add(jLabel1);
        jLabel1.setBounds(170, 60, 100, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed

    }//GEN-LAST:event_btnNoActionPerformed

    private void btnYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnYesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.ButtonDesign.Button btnNo;
    private View.ButtonDesign.Button btnYes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JTextArea lbText;
    // End of variables declaration//GEN-END:variables
}
