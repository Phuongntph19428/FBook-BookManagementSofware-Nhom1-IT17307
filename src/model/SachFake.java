/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import View.DesignComponent.ModelProfile;
import java.text.DecimalFormat;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author quanc
 */
public class SachFake {

    private Icon icon;
    private String name;
    private String idNXB;
    private String idTheLoai;
    private String idTacGia;
    private String Ma;
    private int soLuong;
    private int soTrang;
    private double giaBan;
    private int trangThai;
    private String barCode;
    private String moTa;

    public SachFake(Icon icon, String name, String idNXB, String idTheLoai, String idTacGia, String Ma, int soLuong, int soTrang, double giaBan, int trangThai, String barCode, String moTa) {
        this.icon = icon;
        this.name = name;
        this.idNXB = idNXB;
        this.idTheLoai = idTheLoai;
        this.idTacGia = idTacGia;
        this.Ma = Ma;
        this.soLuong = soLuong;
        this.soTrang = soTrang;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.barCode = barCode;
        this.moTa = moTa;
    }

    public SachFake(String ma, Icon icon, String name, int soLuong, double giaBan, String barCode, String moTa) {
        this.Ma = ma;
        this.icon = icon;
        this.name = name;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.barCode = barCode;
        this.moTa = moTa;
    }

    public SachFake() {
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNXB() {
        return idNXB;
    }

    public void setIdNXB(String idNXB) {
        this.idNXB = idNXB;
    }

    public String getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(String idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getIdTacGia() {
        return idTacGia;
    }

    public void setIdTacGia(String idTacGia) {
        this.idTacGia = idTacGia;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
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

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
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

    public Object[] toRowTable() {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        return new Object[]{Ma,name, new ModelProfile(icon) ,soLuong, df.format(giaBan), barCode, moTa};
    }

}
