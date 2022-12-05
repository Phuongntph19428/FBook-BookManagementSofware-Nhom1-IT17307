/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.NhanVien;
import repository.impl.NhanVienRepositoryImpl;
import service.NhanVienService;

/**
 *
 * @author ppolo
 */
public class NhanVienServiceImpl implements NhanVienService {

    private static NhanVienRepositoryImpl nhanVienRepositoryImpl = new NhanVienRepositoryImpl();

    @Override
    public String addNhanVien(NhanVien nhanVien) {
        boolean result = nhanVienRepositoryImpl.addNhanVien(nhanVien);
        return result ? "Thêm nhân viên thành công" : "Thêm nhân viên thất bại";
    }

    @Override
    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepositoryImpl.getAllNhanVien();
    }

    @Override
    public List<NhanVien> getListbyName(String name) {
        return nhanVienRepositoryImpl.getListNhanVienByName(name);
    }

    @Override
    public NhanVien getNhanVienById(String Id) {
        return nhanVienRepositoryImpl.getNhanVienById(Id);
    }

    @Override
    public String updateNhanVien(NhanVien nhanVien) {
        boolean result = nhanVienRepositoryImpl.updateNhanVien(nhanVien);
        return result ? "Sửa nhân viên thành công" : "Sửa nhân viên thất bại";
    }

    }
