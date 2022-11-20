/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.DesignComponent;

/**
 *
 * @author quanc
 */
public class ModelAction {

    ModelSinhVien student;
    EventAction event;

    public ModelAction(ModelSinhVien student, EventAction event) {
        this.student = student;
        this.event = event;
    }

    public ModelAction() {
    }

    public ModelSinhVien getStudent() {
        return student;
    }

    public void setStudent(ModelSinhVien student) {
        this.student = student;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

}
