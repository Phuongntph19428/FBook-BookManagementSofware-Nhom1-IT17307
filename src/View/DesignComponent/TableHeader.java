/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.DesignComponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author quanc
 */
public class TableHeader extends JLabel{
    private Color color = new Color(153,153,153);
    public TableHeader(String text) {
        super(text);
        setOpaque(true);
        setBackground(Color.decode("#f9faff"));
        setFont(new Font("Segoe UI Semibold", 1, 16));
//        setForeground(new Color(10,10,10));
        setForeground(color);
        setBorder(new EmptyBorder(10,5,10,5));
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(230,230,230));
        g2.drawLine(0, getHeight()-1, getWidth(), getHeight() - 1);
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
