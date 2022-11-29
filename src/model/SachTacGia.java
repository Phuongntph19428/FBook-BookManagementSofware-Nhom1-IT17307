/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ppolo
 */
@Entity
@Table(name = "SachTacGia")
public class SachTacGia implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "IdTacGia", nullable = false)
    private TacGia tacGia;

    @Id
    @ManyToOne
    @JoinColumn(name = "IdSach", nullable = false)
    private Sach sach;

    public SachTacGia() {
    }

    public SachTacGia(TacGia tacGia, Sach sach) {
        this.tacGia = tacGia;
        this.sach = sach;
    }

    public TacGia getTacGia() {
        return tacGia;
    }

    public void setTacGia(TacGia tacGia) {
        this.tacGia = tacGia;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    @Override
    public String toString() {
        return "SachTacGia{" + "tacGia=" + tacGia + ", sach=" + sach + '}';
    }

}
