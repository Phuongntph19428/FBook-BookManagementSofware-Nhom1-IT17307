/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ppolo
 */
@Entity
@Table(name = "HinhThucThanhToan")
public class HinhThucThanhToan implements Serializable {

    public static final int THANHTOANBANGTIENMAT = 0;
    public static final int THANHTOANBANGBANKING = 1;    
    
    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdHoaDon", nullable = false)
    private HoaDon hoaDon;
    
    @Column(name = "HinhThucThanhToan")
    private int hinhThucThanhToan;

    @Column(name = "TienThanhToan")
    private BigDecimal tienThanhToan;

    public HinhThucThanhToan() {
    }

    public HinhThucThanhToan(String id, HoaDon hoaDon, int hinhThucThanhToan, BigDecimal tienThanhToan) {
        this.id = id;
        this.hoaDon = hoaDon;
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
