/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.DesignComponent;

/**
 *
 * @author quanc
 */
public class Model_Month {

    private int mounth;
    private String monthText;

    public Model_Month() {
    }

    public Model_Month(int mounth, String monthText) {
        this.mounth = mounth;
        this.monthText = monthText;
    }

    public int getMounth() {
        return mounth;
    }

    public void setMounth(int mounth) {
        this.mounth = mounth;
    }

    public String getMonthText() {
        return monthText;
    }

    public void setMonthText(String monthText) {
        this.monthText = monthText;
    }

    @Override
    public String toString() {
        return "Model_Month{" + "mounth=" + mounth + ", monthText=" + monthText + '}';
    }
    

}
