/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import model.KhachHang;

/**
 *
 * @author ppolo
 */
public interface KhachHangRepository {

    public List<KhachHang> selectAll();

    public boolean insertKhachHang(KhachHang khachHang);

    public boolean updateKhachHang(KhachHang khachHang);

    public List<KhachHang> sellectAllBySDT(String sdt);

    public boolean addDiemKhachHang(KhachHang khachHang, int diem);
    
    public KhachHang sellectByMa(String ma);
}
