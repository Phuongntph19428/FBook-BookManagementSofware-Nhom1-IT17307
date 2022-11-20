package View.PanelTagDesign;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.SwingUtilities;

public class Item extends javax.swing.JLayeredPane {

    public boolean isInput() {
        return input;
    }

    private EventTags eventTags;
    private ActionListener eventRemove;
    private KeyAdapter eventKey;
    private EventInput event;
    private boolean input;
    private boolean over;

    public void addEventForInput(EventInput event) {
        this.event = event;
        input = true;
        txt.setFocusable(true);
        cmd.setIcon(null);
    }

    public void addEventRemove(ActionListener event) {
        this.eventRemove = event;
    }

    public void addEventKey(KeyAdapter event) {
        this.eventKey = event;
    }

    public void setEventTags(EventTags event) {
        this.eventTags = event;
    }

    public Item(String text) {
        initComponents();
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setText(text);
        txt.setFocusable(false);
//        lbIcon.setVisible(false);
    }

    public void setIcon(Icon icon) {
//        lbIcon.setVisible(icon != null);
//        lbIcon.setIcon(icon);
    }

    public void addEventMouse() {
        addMouseEvent(this);
        addMouseEvent(cmd);
        addMouseEvent(txt);
    }

    private void addMouseEvent(Component com) {
        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                over = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                over = false;
                repaint();
            }

        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new Text();
        cmd = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(17, 28, 68));
        setMaximumSize(new java.awt.Dimension(430, 55));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        txt.setBackground(new java.awt.Color(17, 28, 68));
        txt.setColumns(20);
        txt.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txt.setForeground(new java.awt.Color(255, 255, 255));
        txt.setRows(5);
        txt.setBorder(null);
        txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFocusLost(evt);
            }
        });
        txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMouseClicked(evt);
            }
        });
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKeyTyped(evt);
            }
        });

        cmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/PanelTagDesign/close.png"))); // NOI18N
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setFocusable(false);
        cmd.setPreferredSize(new java.awt.Dimension(25, 16));
        cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(cmd, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addComponent(jSeparator1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyTyped
        if (evt.getKeyChar() == 10) {
            addItem();
        }
    }//GEN-LAST:event_txtKeyTyped

    private void txtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)) {
            txt.setFocusable(true);
            txt.grabFocus();
        }
    }//GEN-LAST:event_txtMouseClicked

    private void txtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFocusLost
        if (input) {
            addItem();
        } else {
            txt.setFocusable(false);
        }
    }//GEN-LAST:event_txtFocusLost

    private void cmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActionPerformed
        if (eventRemove != null) {
            eventRemove.actionPerformed(evt);
            //  Error when we click on icon
        }
    }//GEN-LAST:event_cmdActionPerformed

    private void txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyReleased
        eventKey.keyReleased(evt);
    }//GEN-LAST:event_txtKeyReleased

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        txt.grabFocus();
    }//GEN-LAST:event_formFocusGained

    private void addItem() {
        String text = txt.getText().trim().replace("\n", "");
        if (eventTags != null) {
            if (!eventTags.isAddAble(this, text)) {
                txt.setText(text);
                return;
            }
        }
        if (input) {
            txt.setText("");
        } else {
            txt.setText(text);
            txt.setFocusable(false);
            if (eventTags != null) {
                eventTags.onEdit(this, text);
            }
        }
        if (!text.equals("")) {
            //  Error when edit text cos it not have event we need to add event onEdit
            if (input) {
                event.addItem(text);
            }
        }
    }

//    @Override
//    protected void paintComponent(Graphics grphcs) {
//        if (!input) {
//            Graphics2D g2 = (Graphics2D) grphcs;
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            if (over) {
//                g2.setColor(new Color(212, 212, 212));
//                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
//                g2.setColor(new Color(8, 62, 243));
//                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
//            } else {
//                g2.setColor(new Color(227, 227, 227));
//                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
//            }
//        }
//        super.paintComponent(grphcs);
//    }

    public String getText() {
        return txt.getText().trim();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmd;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea txt;
    // End of variables declaration//GEN-END:variables
}
