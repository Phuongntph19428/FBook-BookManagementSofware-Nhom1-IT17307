/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.DesignComponent;

import java.awt.Color;

/**
 *
 * @author quanc
 */
public class DC_ModelLoai {
    String name;
    Color color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public DC_ModelLoai() {
    }

    public DC_ModelLoai(String name, Color color) {
        this.name = name;
        this.color = color;
    }
    
    
}
