/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author quanc
 */
public class MenuItem extends javax.swing.JPanel {

    /**
     * @return the subMenu
     */
    public ArrayList<MenuItem> getSubMenu() {
        return subMenu;
    }

    public JLabel getIcon() {
        return this.lbIcon;
    }

    public JLabel getIconUpDown() {
        return this.lbIconUpDown;
    }
    public void setBackgroundJPanel(Color color){
        this.setBackground(color);
    }
    public JLabel getNameLabel(){
        return this.lbName;
    }

    public void setIconUpDown(Icon icon) {
        this.lbIconUpDown.setIcon(icon);
    }

    private ArrayList<MenuItem> subMenu = new ArrayList<>();
//    ManagementBookForm mn = new ManagementBookForm();

    public MenuItem(Icon icon, String menuName, ActionListener act, MenuItem... subItems) {
        initComponents();
        lbIcon.setIcon(icon);
        lbName.setText(menuName);
        this.setSize(new Dimension(Integer.MAX_VALUE, 54));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 54));
        this.setMinimumSize(new Dimension(Integer.MAX_VALUE, 54));
        for (int i = 0; i < subItems.length; i++) {
            this.subMenu.add(subItems[i]);
            subItems[i].setVisible(false);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getPreferredSize().height;
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TOP_ALIGNMENT));
        g2.fillRect(0,2,width,38);
        g2.setComposite(AlphaComposite.SrcOver);
        g2.drawLine(30, 30, 30, height-40);
        super.paintComponent(g); 
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIconUpDown = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(13, 7, 48));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(lbIconUpDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 30, 30));
        add(lbIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 200, 50));

        lbName.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("              Menu Name Here");
        lbName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbNameMousePressed(evt);
            }
        });
        add(lbName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 56));
    }// </editor-fold>//GEN-END:initComponents
    private boolean showing = false;

    public boolean getShowing() {
        return this.showing;
    }
    private void lbNameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNameMousePressed
        if (showing) {
            hideMenu();
            showing = false;
        } else {
            showMenu();
            showing = true;
        }
    }//GEN-LAST:event_lbNameMousePressed

    private void showMenu() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < subMenu.size(); i++) {
                    
                    sleep();
                    subMenu.get(i).setVisible(true);

                }
//                mn.setUpDownIcon("up");
            }
        }).start();
    }

    private void hideMenu() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = subMenu.size() - 1; i >= 0; i--) {
                    sleep();
                    subMenu.get(i).setVisible(false);

                }
//                mn.setUpDownIcon("down");
            }
        }).start();

    }

    private void sleep() {
        try {
            Thread.sleep(29);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbIconUpDown;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}
