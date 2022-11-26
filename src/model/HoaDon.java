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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ppolo
 */
@Entity
@Table(name = "HoaDon")
public class HoaDon implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdNhanVien", nullable = false)
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "IdKhachHang", nullable = false)
    private KhachHang khachHang;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "NgayTao")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ngayTao;

    @Column(name = "NgayShip")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ngayShip;

    @Column(name = "NgayNhan")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ngayNhan;

    @Column(name = "NgayThanhToan")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ngayThanhToan;

    @Column(name = "TrangThai")
    private int trangThai;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "SoDiemSuDung")
    private int soDiemSuDung;

    @OneToMany(mappedBy = "hoaDon", targetEntity = HoaDonChiTiet.class)
    private List<HoaDonChiTiet> lstHoaDonCT;

    @OneToMany(mappedBy = "hoaDon", targetEntity = HinhThucThanhToan.class)
    private List<HinhThucThanhToan> lstHinhThucThanhToan;

    public HoaDon() {
    }

    public HoaDon(String id, NhanVien nhanVien, KhachHang khachHang, String ma, String ten, Date ngayTao, Date ngayShip, Date ngayNhan, Date ngayThanhToan, int trangThai, String moTa, int soDiemSuDung) {
        this.id = id;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.ma = ma;
        this.ten = ten;
        this.ngayTao = ngayTao;
        this.ngayShip = ngayShip;
        this.ngayNhan = ngayNhan;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
        this.moTa = moTa;
        this.soDiemSuDung = soDiemSuDung;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayShip() {
        return ngayShip;
    }

    public void setNgayShip(Date ngayShip) {
        this.ngayShip = ngayShip;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getSoDiemSuDung() {
        return soDiemSuDung;
    }

    public void setSoDiemSuDung(int soDiemSuDung) {
        this.soDiemSuDung = soDiemSuDung;
    }

    public List<HoaDonChiTiet> getLstHoaDonCT() {
        return lstHoaDonCT;
    }

    public void setLstHoaDonCT(List<HoaDonChiTiet> lstHoaDonCT) {
        this.lstHoaDonCT = lstHoaDonCT;
    }

    public List<HinhThucThanhToan> getLstHinhThucThanhToan() {
        return lstHinhThucThanhToan;
    }

    public void setLstHinhThucThanhToan(List<HinhThucThanhToan> lstHinhThucThanhToan) {
        this.lstHinhThucThanhToan = lstHinhThucThanhToan;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "id=" + id + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + ", ma=" + ma + ", ten=" + ten + ", ngayTao=" + ngayTao + ", ngayShip=" + ngayShip + ", ngayNhan=" + ngayNhan + ", trangThai=" + trangThai + ", moTa=" + moTa + '}';
    }

}
