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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ppolo
 */
@Entity
@Table(name = "PhieuNhap")
public class PhieuNhap implements Serializable {
    
    public final static int DATHEMVAOKHO = 1;
    public final static int CHUATHEMVAOKHO = 0;    

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

    @Column(name = "NgayNhap")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ngayNhap;

    @Column(name = "TrangThai")
    private int trangThai;

    @Column(name = "MoTa")
    private String moTa;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "phieuNhap", targetEntity = PhieuNhapChiTiet.class)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PhieuNhapChiTiet> lstPhieuNhapCT;

    public PhieuNhap() {
    }

    public PhieuNhap(String id, NhaCungCap nhaCungCap, String ma, Date ngayNhap, int trangThai, String moTa) {
        this.id = id;
        this.nhaCungCap = nhaCungCap;
        this.ma = ma;
        this.ngayNhap = ngayNhap;
        this.trangThai = trangThai;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public List<PhieuNhapChiTiet> getLstPhieuNhapCT() {
        return lstPhieuNhapCT;
    }

    public void setLstPhieuNhapCT(List<PhieuNhapChiTiet> lstPhieuNhapCT) {
        this.lstPhieuNhapCT = lstPhieuNhapCT;
    }

    @Override
    public String toString() {
        return "PhieuNhap{" + "id=" + id + ", nhaCungCap=" + nhaCungCap.getTen() + ", ma=" + ma + ", ngayNhap=" + ngayNhap + ", moTa=" + moTa + ", trangThai" + trangThai + '}';
    }
    
    public Object[] toDataRow() {
        return new Object[]{ma, nhaCungCap.getTen(), ngayNhap, trangThai==0?"Chưa nhập vào kho" : "Đã nhập vào kho"};
    }
}
