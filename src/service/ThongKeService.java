/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import custommodel.CustomThuChi;
import java.util.Date;
import java.util.List;
import model.KhachHang;
import model.NhanVien;

/**
 *
 * @author ppolo
 */
public interface ThongKeService {

    public List<CustomThuChi> getAll(Date tuNgay, Date denNgay);

    public List<CustomThuChi> getAllThu(Date tuNgay, Date denNgay);

    public List<CustomThuChi> getAllChi(Date tuNgay, Date denNgay);

    public List<CustomThuChi> getAllByNhanVien(NhanVien nhanVien, Date tuNgay, Date denNgay);

    public List<CustomThuChi> getAllByKhachhang(KhachHang khachHang, Date tuNgay, Date denNgay);
}
