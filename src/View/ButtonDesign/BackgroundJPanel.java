/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.ButtonDesign;

import View.DesignComponent.SelectMorePanel;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author quanc
 */
public class BackgroundJPanel extends JPanel{
    
    public BackgroundJPanel(){
        Background bgr = new Background("Test");
        SelectMorePanel sl = new SelectMorePanel();
        this.add(bgr);
        this.add(sl);
        
    }
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.7));
        super.paint(g2);
        g2.dispose();
    }
}
