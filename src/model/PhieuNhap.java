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
@Table(name = "PhieuNhap")
public class PhieuNhap implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdNhaCungCap", nullable = false)
    private NhaCungCap nhaCungCap;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "ngayNhap")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayNhap;

    @Column(name = "MoTa")
    private String moTa;

    @OneToMany(mappedBy = "phieuNhap", targetEntity = PhieuNhapChiTiet.class)
    private List<PhieuNhapChiTiet> lstPhieuNhapCT;

    public PhieuNhap() {
    }

    public PhieuNhap(String id, NhaCungCap nhaCungCap, String ma, Date ngayNhap, String moTa) {
        this.id = id;
        this.nhaCungCap = nhaCungCap;
        this.ma = ma;
        this.ngayNhap = ngayNhap;
        this.moTa = moTa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<PhieuNhapChiTiet> getLstPhieuNhapCT() {
        return lstPhieuNhapCT;
    }

    public void setLstPhieuNhapCT(List<PhieuNhapChiTiet> lstPhieuNhapCT) {
        this.lstPhieuNhapCT = lstPhieuNhapCT;
    }

    @Override
    public String toString() {
        return "PhieuNhap{" + "id=" + id + ", nhaCungCap=" + nhaCungCap + ", ma=" + ma + ", ngayNhap=" + ngayNhap + ", moTa=" + moTa + '}';
    }

}
