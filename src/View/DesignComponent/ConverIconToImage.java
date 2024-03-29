/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.DesignComponent;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.AbstractButton;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author quanc
 */
public class ConverIconToImage implements Icon {

    private Icon wrappee;
    private Icon standIn;

    public ConverIconToImage(Icon wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public int getIconHeight() {
        return wrappee.getIconHeight();
    }

    @Override
    public int getIconWidth() {
        return wrappee.getIconWidth();
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (standIn == this) {
            paintFallback(c, g, x, y);
        } else if (standIn != null) {
            standIn.paintIcon(c, g, x, y);
        } else {
            try {
                wrappee.paintIcon(c, g, x, y);
            } catch (ClassCastException e) {
                createStandIn(e, x, y);
                standIn.paintIcon(c, g, x, y);
            }
        }
    }

    /**
     * @param e
     */
    private void createStandIn(ClassCastException e, int x, int y) {
        try {
            Class<?> clazz = getClass(e);
            JComponent standInComponent = getSubstitute(clazz);
            standIn = createImageIcon(standInComponent, x, y);
        } catch (Exception e1) {
            // something went wrong - fallback to this painting
            standIn = this;
        }
    }

    private Icon createImageIcon(JComponent standInComponent, int x, int y) {
        BufferedImage image = new BufferedImage(getIconWidth(),
                getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        try {
            wrappee.paintIcon(standInComponent, g, 0, 0);
            return new ImageIcon(image);
        } finally {
            g.dispose();
        }
    }

    /**
     * @param clazz
     * @throws IllegalAccessException
     */
    private JComponent getSubstitute(Class<?> clazz) throws IllegalAccessException {
        JComponent standInComponent;
        try {
            standInComponent = (JComponent) clazz.newInstance();
        } catch (InstantiationException e) {
            standInComponent = new AbstractButton() {

            };
            ((AbstractButton) standInComponent).setModel(new DefaultButtonModel());
        }
        return standInComponent;
    }

    private Class<?> getClass(ClassCastException e) throws ClassNotFoundException {
        String className = e.getMessage();
        className = className.substring(className.lastIndexOf(" ") + 1);
        return Class.forName(className);

    }

    private void paintFallback(Component c, Graphics g, int x, int y) {
        g.drawRect(x, y, getIconWidth(), getIconHeight());
        g.drawLine(x, y, x + getIconWidth(), y + getIconHeight());
        g.drawLine(x + getIconWidth(), y, x, y + getIconHeight());
    }

    public static void main(String[] args) {
        Icon icon = new ImageIcon("image/icons8_qr_code_50px.png");
        icon = new ConverIconToImage(icon);
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        System.out.println(image.getSource());
    }
}
