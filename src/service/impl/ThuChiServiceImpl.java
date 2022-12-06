/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.ThuChiModel;
import java.util.Date;
import java.util.List;
import repository.ThuChiRepository;
import repository.impl.ThuChiRepositoryImpl;
import service.ThuChiService;

/**
 *
 * @author Dieu Linh
 */
public class ThuChiServiceImpl implements ThuChiService {

    private ThuChiRepository ThuChiRepository = new ThuChiRepositoryImpl();

    @Override
    public List<String> cbbNhanVien() {
        return ThuChiRepository.cbbNhanVien();
    }

    @Override
    public List<String> cbbKhachHang() {
        return ThuChiRepository.cbbKhachHang();
    }

    @Override
    public List<ThuChiModel> getThuNhanVien(String idNhanVien, Date ngayBatDau, Date ngayKetThuc) {
        return ThuChiRepository.getThuNhanVien(idNhanVien, ngayBatDau, ngayKetThuc);
    }

    @Override
    public List<ThuChiModel> getKhachHang(String idKhachHang, Date ngayBatDau, Date ngayKetThuc) {
        return ThuChiRepository.getKhachHang(idKhachHang, ngayBatDau, ngayKetThuc);
    }

    @Override
    public String findById(String ten) {
        return ThuChiRepository.findById(ten);
    }

    @Override
    public List<ThuChiModel> tienChi(Date ngayBatDau, Date ngayKetThuc) {
        return ThuChiRepository.tienChi(ngayBatDau, ngayKetThuc);
    }

}
