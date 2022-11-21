/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author quanc
 */
public class Camera_Form extends javax.swing.JPanel {

    public final Webcam webcam = Webcam.getDefault();
    private byte[] hinh = null;

    public Camera_Form() {
        initComponents();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
    }

    public void setTitle(String text) {
        this.lbNameForm.setText(text);
    }
    
    public void record() {
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open();
        while (true) {
            Image image = webcam.getImage();
            if (image != null) {
                lblScreen.setIcon(new ImageIcon(image));
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbNameForm = new javax.swing.JLabel();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        lblScreen = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnCapture = new View.ButtonDesign.Button();

        setBackground(new java.awt.Color(47, 55, 90));

        lbNameForm.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lbNameForm.setForeground(new java.awt.Color(255, 255, 255));
        lbNameForm.setText("Select Photo");

        jPanelBourder1.setBackground(new java.awt.Color(17, 28, 68));

        javax.swing.GroupLayout jPanelBourder1Layout = new javax.swing.GroupLayout(jPanelBourder1);
        jPanelBourder1.setLayout(jPanelBourder1Layout);
        jPanelBourder1Layout.setHorizontalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelBourder1Layout.setVerticalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblScreen, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
        );

        btnCapture.setBackground(new java.awt.Color(31, 31, 111));
        btnCapture.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnCapture.setForeground(new java.awt.Color(255, 255, 255));
        btnCapture.setText("Capture");
        btnCapture.setFocusable(false);
        btnCapture.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(jPanelBourder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNameForm, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE))
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(386, 386, 386))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public View.ButtonDesign.Button btnCapture;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbNameForm;
    private javax.swing.JLabel lblScreen;
    // End of variables declaration//GEN-END:variables
}
