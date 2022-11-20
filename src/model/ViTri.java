/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
@Table(name = "ViTri")
public class ViTri {

    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "generator")
    private String id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "MoTa")
    private String moTa;

    @OneToMany(mappedBy = "viTri", targetEntity = Sach.class)
    private List<Sach> lstSach;

    public ViTri() {
    }

    public ViTri(String id, String ma, String moTa) {
        this.id = id;
        this.ma = ma;
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
        return "ViTri{" + "id=" + id + ", ma=" + ma + ", moTa=" + moTa + '}';
    }

}
