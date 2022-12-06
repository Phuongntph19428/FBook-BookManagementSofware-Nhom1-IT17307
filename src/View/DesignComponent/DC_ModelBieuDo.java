/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.DesignComponent;

import javax.swing.JLabel;

/**
 *
 * @author quanc
 */
public class DC_ModelBieuDo{

    private String label;
    private double values[];

    public DC_ModelBieuDo(String label, double[] values) {
        this.label = label;
        this.values = values;
    }

    public DC_ModelBieuDo() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public double getMaxValue() {
        double max = 0;
        for (double v : values) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }
}
