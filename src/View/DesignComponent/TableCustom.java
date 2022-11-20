/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.DesignComponent;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author quanc
 */
public class TableCustom extends JTable{
    private BufferedImage imageShadow;

    public TableCustom(BufferedImage imageShadow) {
        this.imageShadow = imageShadow;
    }
    
    public TableCustom(){
        setRowHeight(100);
        getTableHeader().setReorderingAllowed(false);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        return super.prepareRenderer(renderer, row, column); 
        
    }

    @Override
    protected void paintComponent(Graphics g) { // design Table
        super.paintComponent(g); 
    }
    
    
    
    
}
