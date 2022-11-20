/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.ButtonDesign;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;

/**
 *
 * @author quanc
 */
public class Background extends JButton {

    public Background() {

    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
    }
    

    public Background(String text) {
        this.setText(text);
        this.setSize(1000, 500);
        this.setFocusable(false);
        setContentAreaFilled(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));
        super.paint(g2);
        g2.dispose();
    }

}
