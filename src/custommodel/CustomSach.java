/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custommodel;

import View.DesignComponent.ModelProfile;
import java.awt.Image;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import model.Sach;

/**
 *
 * @author ppolo
 */
public class CustomSach {

    private String id;
    private String ma;
    private String ten;
    private int soLuong;
    private BigDecimal giaBan;
    private BigDecimal giaSale;
    private Integer chietKhau;
    private byte[] hinh;
    private String viTri;
    private String tacGia;
    private String theLoai;
    private String nhaXB;

    public CustomSach() {
    }

    public CustomSach(String id, String ma, String ten, int soLuong, BigDecimal giaBan, BigDecimal giaSale, Integer chietKhau, byte[] hinh, String viTri, String tacGia, String theLoai, String nhaXB) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giaSale = giaSale;
        this.chietKhau = chietKhau;
        this.hinh = hinh;
        this.viTri = viTri;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.nhaXB = nhaXB;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public BigDecimal getGiaSale() {
        return giaSale;
    }

    public void setGiaSale(BigDecimal giaSale) {
        this.giaSale = giaSale;
    }

    public Integer getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(Integer chietKhau) {
        this.chietKhau = chietKhau;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public Sach getSach() {
        return new Sach(id, null, null, ma, ten, soLuong, 0, giaBan, giaBan, 1, hinh, null, null);
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public Object[] toDataRow() {
        DecimalFormat df = new DecimalFormat("#,###");
        return new Object[]{ma, ten, hinh == null ? null : new ModelProfile(new ImageIcon(new ImageIcon(hinh).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT))),
            soLuong, giaSale == null ? df.format(giaBan) : df.format(giaSale), giaSale == null
            ? "<html>" + giaBan.multiply(BigDecimal.valueOf(soLuong)) + "<sup style=\"Color: Red\">vnđ</sup></html>"
            : "<html>" + giaSale.multiply(BigDecimal.valueOf(soLuong)) + "<sup style=\"Color: Red\">vnđ</sup></html>"};
    }

}
