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
@Table(name = "KhuyenMaiChiTiet")
public class KhuyenMaiChiTiet implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "IdKhuyenMai", nullable = false)
    private KhuyenMai khuyenMai;

    @Id
    @ManyToOne
    @JoinColumn(name = "IdSach", nullable = false)
    private Sach sach;

    public KhuyenMaiChiTiet() {
    }

    public KhuyenMaiChiTiet(KhuyenMai khuyenMai, Sach sach) {
        this.khuyenMai = khuyenMai;
        this.sach = sach;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    @Override
    public String toString() {
        return "KhuyenMaiChiTiet{" + "khuyenMai=" + khuyenMai + ", sach=" + sach + '}';
    }

    public Object[] toDaTaRow() {
        return new Object[]{khuyenMai, sach};
    }
}
