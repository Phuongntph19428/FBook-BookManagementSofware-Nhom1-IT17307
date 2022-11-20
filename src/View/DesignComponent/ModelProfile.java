/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.DesignComponent;

import javax.swing.Icon;

/**
 *
 * @author quanc
 */
public class ModelProfile {

    Icon icon;
    String name;

    public ModelProfile(Icon icon, String name) {
        this.icon = icon;
        this.name = name;
    }
    
    public ModelProfile(Icon icon){
        this.icon = icon;
    }

    public ModelProfile() {
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
