/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author quanc
 */
public class Notification_Quest extends javax.swing.JPanel {

    //icons8_help_65px
    public Notification_Quest() {
        initComponents();
        Icon iconClose = new ImageIcon("image/icons8_multiply_18px.png");
        Icon iconOke = new ImageIcon("image/icons8_help_65px.png");//icons8_error_120px
        jLabel5.setIcon(iconClose);

    }

    public void setContent(String string) {
        content.setText(string);
    }
    
    public JButton getButtonCancel(){
        return this.btnCancel;
    }
    
    public JButton getButtonYes(){
        return this.btnYes;
    }
    
    public void setIcon(Icon icon){
        this.lbIcon.setIcon(icon);
    }
    public void setName(String name){
        this.lbName.setText(name);
    }
    
    public void setBarColor(Color color){
    this.bar.setBackground(color);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bar = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        content = new javax.swing.JTextArea();
        btnYes = new View.ButtonDesign.Button();
        btnCancel = new View.ButtonDesign.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        setLayout(null);

        bar.setBackground(new java.awt.Color(49, 134, 234));

        javax.swing.GroupLayout barLayout = new javax.swing.GroupLayout(bar);
        bar.setLayout(barLayout);
        barLayout.setHorizontalGroup(
            barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        barLayout.setVerticalGroup(
            barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(bar);
        bar.setBounds(2, 2, 8, 179);
        add(jLabel5);
        jLabel5.setBounds(505, 2, 24, 27);

        lbIcon.setForeground(new java.awt.Color(255, 255, 255));
        add(lbIcon);
        lbIcon.setBounds(22, 24, 79, 69);

        lbName.setFont(new java.awt.Font("Segoe UI Black", 0, 22)); // NOI18N
        lbName.setForeground(new java.awt.Color(102, 102, 102));
        lbName.setText("Confirm");
        add(lbName);
        lbName.setBounds(107, 24, 89, 29);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        content.setEditable(false);
        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setColumns(20);
        content.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        content.setLineWrap(true);
        content.setRows(5);
        content.setTabSize(0);
        content.setText("Bạn có xác nhận muốn thao tác");
        content.setWrapStyleWord(true);
        content.setMargin(new java.awt.Insets(2, 0, 2, 6));
        jScrollPane1.setViewportView(content);

        add(jScrollPane1);
        jScrollPane1.setBounds(107, 59, 393, 70);

        btnYes.setBackground(new java.awt.Color(49, 134, 234));
        btnYes.setForeground(new java.awt.Color(255, 255, 255));
        btnYes.setText("Yes, I agree");
        btnYes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnYes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnYes.setRound(3);
        add(btnYes);
        btnYes.setBounds(381, 135, 142, 43);

        btnCancel.setBackground(new java.awt.Color(153, 153, 153));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCancel.setRound(3);
        add(btnCancel);
        btnCancel.setBounds(221, 135, 142, 43);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bar;
    private View.ButtonDesign.Button btnCancel;
    private View.ButtonDesign.Button btnYes;
    private javax.swing.JTextArea content;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}
