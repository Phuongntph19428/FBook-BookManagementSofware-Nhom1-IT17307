/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custommodel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ppolo
 */
public class CustomThuChi {

    private String loaiGiaoDich;
    private String ma;
    private Date ngay;
    private BigDecimal tongTien;

    public CustomThuChi() {
    }

    public CustomThuChi(String loaiGiaoDich, String ma, Date ngay, BigDecimal tongTien) {
        this.loaiGiaoDich = loaiGiaoDich;
        this.ma = ma;
        this.ngay = ngay;
        this.tongTien = tongTien;
    }

    public String getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public void setLoaiGiaoDich(String loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "CustomThuChi{" + "loaiGiaoDich=" + loaiGiaoDich + ", ma=" + ma + ", ngay=" + ngay + ", tongTien=" + tongTien + '}';
    }

    public Object[] toDataRow() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DecimalFormat df = new DecimalFormat("#,###");
        return new Object[]{loaiGiaoDich.equals("Hoa don") ? "Hóa đơn" : "Phiếu nhập", ma, sdf.format(ngay),
            loaiGiaoDich.equals("Hoa don") ? "<html> <span style='color: green'>+" + df.format(tongTien) + "<sup> vnđ<sup><span></html>" : 
                "<html><span style='color: red'>-" + df.format(tongTien) + "<sup> vnđ<sup></span></html>"};
    }
}
