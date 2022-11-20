/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ppolo
 */
@Entity
@Table(name = "NhaCungCap")
public class NhaCungCap implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "generator")
    private String id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "MoTa")
    private String moTa;

    @OneToMany(mappedBy = "nhaCungCap", targetEntity = PhieuNhap.class)
    private List<PhieuNhap> lstPhieuNhap;

    public NhaCungCap() {
    }

    public NhaCungCap(String id, String ma, String ten, String sdt, String diaChi, String moTa) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.diaChi = diaChi;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<PhieuNhap> getLstPhieuNhap() {
        return lstPhieuNhap;
    }

    public void setLstPhieuNhap(List<PhieuNhap> lstPhieuNhap) {
        this.lstPhieuNhap = lstPhieuNhap;
    }

    @Override
    public String toString() {
        return "NhaCungCap{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", sdt=" + sdt + ", diaChi=" + diaChi + ", moTa=" + moTa + '}';
    }

}
