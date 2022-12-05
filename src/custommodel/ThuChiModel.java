/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custommodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import model.KhachHang;
import model.NhanVien;

/**
 *
 * @author Dieu Linh
 */
@Data
public class ThuChiModel implements Serializable{

    private Integer loaiGiaoDich;
    private Date ngayThanhToan;
    private Double tienThu;
    private Double tienChi;
    private String idNhanVien;
    private String idKhachHang;
    private String ten;

    public ThuChiModel( String idNhanVien, String ten,Double tienThu, Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
        this.tienThu = tienThu;
        this.idNhanVien = idNhanVien;
        this.ten = ten;
    }
    public ThuChiModel( Double tienChi, Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
        this.tienChi = tienChi;

    }

}
