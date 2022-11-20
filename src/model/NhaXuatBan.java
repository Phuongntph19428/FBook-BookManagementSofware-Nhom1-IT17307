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
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ppolo
 */
@Entity
@Table(name = "NhaXuatBan")
public class NhaXuatBan implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "MoTa")
    private String moTa;

    @OneToMany(mappedBy = "nhaXuatBan", targetEntity = Sach.class)
    private List<Sach> lstSach;

    public NhaXuatBan() {
    }

    public NhaXuatBan(String id, String ma, String ten, String moTa) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<Sach> getLstSach() {
        return lstSach;
    }

    public void setLstSach(List<Sach> lstSach) {
        this.lstSach = lstSach;
    }

    @Override
    public String toString() {
        return "NhaXuatBan{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", moTa=" + moTa + '}';
    }

    public Object[] tObjects() {
        return new Object[]{id, ma, ten, moTa};
    }

    public NhaXuatBan(String ma, String ten, String moTa) {
        this.ma = ma;
        this.ten = ten;
        this.moTa = moTa;
    }
}
