/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ppolo
 */
@Entity
@Table(name = "KhuyenMai")
public class KhuyenMai implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "generator")
    private String id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "ChietKhau")
    private int chietKhau;

    @Column(name = "NgayBatDau")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayBatDau;

    @Column(name = "NgayKetThuc")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayKetThuc;

    @Column(name = "TrangThai")
    private int trangThai;

    @Column(name = "MoTa")
    private String moTa;

    @OneToMany(mappedBy = "khuyenMai", targetEntity = KhuyenMaiChiTiet.class)
    private List<KhuyenMaiChiTiet> lstKhuyenMaiCT;

    public KhuyenMai() {
    }

    public KhuyenMai(String id, String ma, String ten, int chietKhau, Date ngayBatDau, Date ngayKetThuc, int trangThai, String moTa) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.chietKhau = chietKhau;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(int chietKhau) {
        this.chietKhau = chietKhau;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<KhuyenMaiChiTiet> getLstKhuyenMai() {
        return lstKhuyenMaiCT;
    }

    public void setLstKhuyenMai(List<KhuyenMaiChiTiet> lstKhuyenMaiCT) {
        this.lstKhuyenMaiCT = lstKhuyenMaiCT;
    }

    @Override
    public String toString() {
        return "KhuyenMai{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", chietKhau=" + chietKhau + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", trangThai=" + trangThai + ", moTa=" + moTa + '}';
    }

}
