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
@Table(name = "TacGia")
public class TacGia implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "generator")
    private String id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "Hinh")
    private byte[] hinh;

    @Column(name = "MoTa")
    private String moTa;

    @OneToMany(mappedBy = "tacGia", targetEntity = SachTacGia.class)
    private List<SachTacGia> lstSachTacGia;

    public TacGia() {
    }

    public TacGia(String id, String ma, String ten, byte[] hinh, String moTa) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.hinh = hinh;
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

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<SachTacGia> getLstSachTacGia() {
        return lstSachTacGia;
    }

    public void setLstSachTacGia(List<SachTacGia> lstSachTacGia) {
        this.lstSachTacGia = lstSachTacGia;
    }

    @Override
    public String toString() {
        return "TacGia{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", hinh=" + hinh + ", moTa=" + moTa + '}';
    }

}
