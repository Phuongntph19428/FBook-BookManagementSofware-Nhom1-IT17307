/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "IdHoaDon", nullable = false)
    private HoaDon hoaDon;

    @Id
    @ManyToOne
    @JoinColumn(name = "IdSach", nullable = false)
    private Sach sach;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "DonGia")
    private BigDecimal donGia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(HoaDon hoaDon, Sach sach, int soLuong, BigDecimal donGia) {
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
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

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "hoaDon=" + hoaDon + ", sach=" + sach + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }

    
    
}
