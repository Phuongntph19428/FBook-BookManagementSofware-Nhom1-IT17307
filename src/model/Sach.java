/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import View.DesignComponent.ModelProfile;
import java.awt.Image;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.ImageIcon;

/**
 *
 * @author ppolo
 */
@Entity
@Table(name = "Sach")
public class Sach implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "generator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdNXB", nullable = false)
    private NhaXuatBan nhaXuatBan;

    @ManyToOne
    @JoinColumn(name = "IdViTri", nullable = true)
    private ViTri viTri;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "SoTrang")
    private int soTrang;

    @Column(name = "GiaNhap")
    private BigDecimal giaNhap;

    @Column(name = "GiaBan")
    private BigDecimal giaBan;

    @Column(name = "TrangThai")
    private int trangThai;

    @Column(name = "Hinh")
    private byte[] hinh;

    @Column(name = "BarCode")
    private String barCode;

    @Column(name = "MoTa")
    private String moTa;

    @OneToMany(mappedBy = "sach", targetEntity = HoaDonChiTiet.class)
    private List<HoaDonChiTiet> lstHoaDonCT;

    @OneToMany(mappedBy = "sach", targetEntity = SachTacGia.class)
    private List<SachTacGia> lstSachTacGia;

    @OneToMany(mappedBy = "sach", targetEntity = KhuyenMaiChiTiet.class)
    private List<KhuyenMaiChiTiet> lstKhuyenMaiCT;

    @OneToMany(mappedBy = "sach", targetEntity = TheLoaiChiTiet.class)
    private List<TheLoaiChiTiet> lstTheLoaiCT;

    @OneToMany(mappedBy = "sach", targetEntity = PhieuNhapChiTiet.class)
    private List<PhieuNhapChiTiet> lstPhieuNhapCT;

    public Sach(String id, NhaXuatBan nhaXuatBan, ViTri viTri, String ma, String ten, int soLuong, int soTrang, BigDecimal giaNhap, BigDecimal giaBan, int trangThai, byte[] hinh, String barCode, String moTa) {
        this.id = id;
        this.nhaXuatBan = nhaXuatBan;
        this.viTri = viTri;
        this.ma = ma;
        this.ten = ten;
        this.soLuong = soLuong;
        this.soTrang = soTrang;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.hinh = hinh;
        this.barCode = barCode;
        this.moTa = moTa;
    }

    public Sach() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NhaXuatBan getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(NhaXuatBan nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public ViTri getViTri() {
        return viTri;
    }

    public void setViTri(ViTri viTri) {
        this.viTri = viTri;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<HoaDonChiTiet> getLstHoaDonChiTiet() {
        return lstHoaDonCT;
    }

    public void setLstHoaDonChiTiet(List<HoaDonChiTiet> lstHoaDonCT) {
        this.lstHoaDonCT = lstHoaDonCT;
    }

    public List<SachTacGia> getLstSachTacGia() {
        return lstSachTacGia;
    }

    public void setLstSachTacGia(List<SachTacGia> lstSachTacGia) {
        this.lstSachTacGia = lstSachTacGia;
    }

    public List<KhuyenMaiChiTiet> getLstKhuyenMaiChiTiet() {
        return lstKhuyenMaiCT;
    }

    public void setLstKhuyenMaiChiTiet(List<KhuyenMaiChiTiet> lstKhuyenMaiCT) {
        this.lstKhuyenMaiCT = lstKhuyenMaiCT;
    }

    public List<PhieuNhapChiTiet> getLstPhieuNhapCT() {
        return lstPhieuNhapCT;
    }

    public void setLstPhieuNhapCT(List<PhieuNhapChiTiet> lstPhieuNhapCT) {
        this.lstPhieuNhapCT = lstPhieuNhapCT;
    }

    public List<HoaDonChiTiet> getLstHoaDonCT() {
        return lstHoaDonCT;
    }

    public void setLstHoaDonCT(List<HoaDonChiTiet> lstHoaDonCT) {
        this.lstHoaDonCT = lstHoaDonCT;
    }

    public List<KhuyenMaiChiTiet> getLstKhuyenMaiCT() {
        return lstKhuyenMaiCT;
    }

    public void setLstKhuyenMaiCT(List<KhuyenMaiChiTiet> lstKhuyenMaiCT) {
        this.lstKhuyenMaiCT = lstKhuyenMaiCT;
    }

    public List<TheLoaiChiTiet> getLstTheLoaiCT() {
        return lstTheLoaiCT;
    }

    public void setLstTheLoaiCT(List<TheLoaiChiTiet> lstTheLoaiCT) {
        this.lstTheLoaiCT = lstTheLoaiCT;
    }

    @Override
    public String toString() {
        return "Sach{" + "id=" + id + ", nhaXuatBan=" + nhaXuatBan + ", viTri=" + viTri + ", ma=" + ma + ", ten=" + ten + ", soLuong=" + soLuong + ", soTrang=" + soTrang + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", trangThai=" + trangThai + ", hinh=" + hinh + ", barCode=" + barCode + ", moTa=" + moTa + '}';
    }
    
    public Object[] toDataRow() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new Object[]{nhaXuatBan.getTen(), viTri.getMa(), ma, ten, soLuong, soTrang, df.format(giaNhap), df.format(giaBan), trangThai == 0? "Ngừng kinh doanh" : "Đang kinh doanh",
            new ModelProfile(new ImageIcon(new ImageIcon(hinh).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)))};
    }

}
