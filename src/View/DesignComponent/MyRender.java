/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.DesignComponent;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author quanc
 */
public class MyRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, 
            Object value, 
            boolean isSelected, 
            boolean hasFocus, 
            int row, int 
                    column) {
        
        if(isSelected){
            this.setBackground(table.getBackground());
            this.setForeground(table.getForeground());
        }
        
        
        
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
