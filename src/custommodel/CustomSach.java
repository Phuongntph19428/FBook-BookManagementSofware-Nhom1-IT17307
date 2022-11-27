/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custommodel;

import java.math.BigDecimal;

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

    public CustomSach() {
    }

    public CustomSach(String id, String ma, String ten, int soLuong, BigDecimal giaBan, BigDecimal giaSale, Integer chietKhau, byte[] hinh) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giaSale = giaSale;
        this.chietKhau = chietKhau;
        this.hinh = hinh;
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

}
