/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;
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
@Table(name = "NhanVien")
public class NhanVien implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdChucvu", nullable = false)
    private ChucVu chucVu;

    @ManyToOne
    @JoinColumn(name = "IdGuiBC", nullable = true)
    private NhanVien guiBC;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "Ho")
    private String ho;

    @Column(name = "TenDem")
    private String tenDem;

    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Column(name = "ngaySinh")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngaySinh;

    @Column(name = "Email")
    private String email;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "Hinh")
    private byte[] hinh;

    @Column(name = "TrangThai")
    private int trangThai;

    @OneToMany(mappedBy = "nhanVien", targetEntity = HoaDon.class)
    private List<HoaDon> lstHoaDon;

    public NhanVien() {
    }

    public NhanVien(String id, ChucVu chucVu, NhanVien guiBC, String ma, String ten, String ho, String tenDem, String gioiTinh, Date ngaySinh, String email, String sdt, String diaChi, String matKhau, byte[] hinh, int trangThai, List<HoaDon> lstHoaDon) {
        this.id = id;
        this.chucVu = chucVu;
        this.guiBC = guiBC;
        this.ma = ma;
        this.ten = ten;
        this.ho = ho;
        this.tenDem = tenDem;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.matKhau = matKhau;
        this.hinh = hinh;
        this.trangThai = trangThai;
        this.lstHoaDon = lstHoaDon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public NhanVien getGuiBC() {
        return guiBC;
    }

    public void setGuiBC(NhanVien guiBC) {
        this.guiBC = guiBC;
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

    public String getTenDem() {
        return tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public List<HoaDon> getLstHoaDon() {
        return lstHoaDon;
    }

    public void setLstHoaDon(List<HoaDon> lstHoaDon) {
        this.lstHoaDon = lstHoaDon;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "id=" + id + ", chucVu=" + chucVu + ", guiBC=" + guiBC + ", ma=" + ma + ", ten=" + ten + ", tenDem=" + tenDem + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", email=" + email + ", sdt=" + sdt + ", diaChi=" + diaChi + ", matKhau=" + matKhau + ", hinh=" + hinh + ", trangThai=" + trangThai + '}';
    }
}
