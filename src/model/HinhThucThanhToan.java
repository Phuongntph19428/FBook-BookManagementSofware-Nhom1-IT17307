/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ppolo
 */
public class HinhThucThanhToan {

    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdHoaDon", nullable = false)
    private HoaDon hoaDon;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "HinhThucThanhToan")
    private int hinhThucThanhToan;

    @Column(name = "TienThanhToan")
    private BigDecimal tienThanhToan;

    public HinhThucThanhToan() {
    }

    public HinhThucThanhToan(String id, HoaDon hoaDon, String ma, int hinhThucThanhToan, BigDecimal tienThanhToan) {
        this.id = id;
        this.hoaDon = hoaDon;
        this.ma = ma;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.tienThanhToan = tienThanhToan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public int getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(int hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public BigDecimal getTienThanhToan() {
        return tienThanhToan;
    }

    public void setTienThanhToan(BigDecimal tienThanhToan) {
        this.tienThanhToan = tienThanhToan;
    } 

}
