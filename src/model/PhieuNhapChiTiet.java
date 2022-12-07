/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.persistence.Column;
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
@Table(name = "PhieuNhapChiTiet")
public class PhieuNhapChiTiet implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "IdPhieuNhap", nullable = false)
    private PhieuNhap phieuNhap;

    @Id
    @ManyToOne
    @JoinColumn(name = "IdSach", nullable = false)
    private Sach sach;

    @Column(name = "SoLuong")
    private int soLuong;

    public PhieuNhapChiTiet() {
    }

    public PhieuNhapChiTiet(PhieuNhap phieuNhap, Sach sach, int soLuong) {
        this.phieuNhap = phieuNhap;
        this.sach = sach;
        this.soLuong = soLuong;
    }

    public PhieuNhap getPhieuNhap() {
        return phieuNhap;
    }

    public void setPhieuNhap(PhieuNhap phieuNhap) {
        this.phieuNhap = phieuNhap;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Object[] toDataRow() {
        DecimalFormat df = new DecimalFormat("#,###");
        return new Object[]{phieuNhap.getMa(), sach.getMa(), sach.getTen(), soLuong, df.format(sach.getGiaNhap()) + " vnđ", df.format(sach.getGiaNhap().multiply(BigDecimal.valueOf(soLuong))) + " vnđ"};
    }

}
