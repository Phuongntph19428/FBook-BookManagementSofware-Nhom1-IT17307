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
@Table(name = "TheLoaiChiTiet")
public class TheLoaiChiTiet implements Serializable {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "IdTheLoai", nullable = false)
    private TheLoai theLoai;

    @Id
    @ManyToOne
    @JoinColumn(name = "IdSach", nullable = false)
    private Sach sach;

    public TheLoaiChiTiet() {
    }

    public TheLoaiChiTiet(TheLoai theLoai, Sach sach) {
        this.theLoai = theLoai;
        this.sach = sach;
    }

    public TheLoai getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(TheLoai theLoai) {
        this.theLoai = theLoai;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    @Override
    public String toString() {
        return "TheLoaiChiTiet{" + "theLoai=" + theLoai + ", sach=" + sach + '}';
    }
}
