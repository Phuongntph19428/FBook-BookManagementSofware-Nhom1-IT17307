/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.CustomThuChi;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.KhachHang;
import model.NhanVien;
import repository.ThongKeRepository;
import repository.impl.ThongKeRepositoryImpl;
import service.ThongKeService;

/**
 *
 * @author ppolo
 */
public class ThongKeServiceImpl implements ThongKeService {

    private final ThongKeRepository _thongKeRepo;

    public ThongKeServiceImpl() {
        _thongKeRepo = new ThongKeRepositoryImpl();
    }

    @Override
    public List<CustomThuChi> getAll(Date tuNgay, Date denNgay) {
        return sortListCustomThuChi(_thongKeRepo.getAll(tuNgay, denNgay));
    }

    @Override
    public List<CustomThuChi> getAllByNhanVien(NhanVien nhanVien, Date tuNgay, Date denNgay) {
        return sortListCustomThuChi(_thongKeRepo.getAllByNhanVien(nhanVien, tuNgay, denNgay));
    }

    @Override
    public List<CustomThuChi> getAllByKhachhang(KhachHang khachHang, Date tuNgay, Date denNgay) {
        return sortListCustomThuChi(_thongKeRepo.getAllByKhachhang(khachHang, tuNgay, denNgay));
    }

    @Override
    public List<CustomThuChi> getAllThu(Date tuNgay, Date denNgay) {
        return sortListCustomThuChi(_thongKeRepo.getAllThu(tuNgay, denNgay));
    }

    @Override
    public List<CustomThuChi> getAllChi(Date tuNgay, Date denNgay) {
        return sortListCustomThuChi(_thongKeRepo.getAllChi(tuNgay, denNgay));
    }


    private List<CustomThuChi> sortListCustomThuChi(List<CustomThuChi> lstCustom) {
        Collections.sort(lstCustom, (CustomThuChi o1, CustomThuChi o2) -> o1.getNgay().getTime() - o2.getNgay().getTime() < 0 ? 1 : -1);
        return lstCustom;
    }
}
