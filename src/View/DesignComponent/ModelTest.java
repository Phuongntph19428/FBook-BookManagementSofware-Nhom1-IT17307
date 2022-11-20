/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.DesignComponent;

import java.text.DecimalFormat;
import javax.swing.Icon;

/**
 *
 * @author quanc
 */
public class ModelTest {

    Icon icon;
    String Trunm;
    String gender;
    String course;
    double fees;

    public ModelTest(Icon icon, String Trunm, String gender, String course, double fees) {
        this.icon = icon;
        this.Trunm = Trunm;
        this.gender = gender;
        this.course = course;
        this.fees = fees;
    }

    public ModelTest() {
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTrunm() {
        return Trunm;
    }

    public void setTrunm(String Trunm) {
        this.Trunm = Trunm;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
    
    public Object[] toRowTable(){
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        return new Object[]{new ModelProfile(icon, Trunm),gender , course,df.format(fees)};
    }
    
}
