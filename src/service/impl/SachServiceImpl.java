/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.Sach;
import model.SachTacGia;
import model.TheLoaiChiTiet;
import repository.SachRepositoty;
import repository.impl.SachRepositoryImpl;
import service.SachService;

/**
 *
 * @author ppolo
 */
public class SachServiceImpl implements SachService {

    private final SachRepositoty _sachRepo;

    public SachServiceImpl() {
        _sachRepo = new SachRepositoryImpl();
    }

    @Override
    public List<Sach> getList(int position, int pageSize) {
        return _sachRepo.getList(position, pageSize);
    }

    @Override
    public boolean insertSach(Sach sach) {
        return _sachRepo.insertSach(sach);
    }

    @Override
    public boolean updateSach(Sach sach) {
        return _sachRepo.updateSach(sach);
    }

    @Override
    public Sach getSachByMa(String ma) {
        return _sachRepo.getSachByMa(ma);
    }

    @Override
    public int countAllSach() {
        return _sachRepo.countAllSach();
    }

    @Override
    public List<Sach> getListByKeyword(String keyword) {
        return _sachRepo.getListByKeyword(keyword);
    }

    @Override
    public boolean updateSachTacGia(List<SachTacGia> lstSachTacGia) {
        return _sachRepo.updateSachTacGia(lstSachTacGia);
    }

    @Override
    public boolean updateTheLoaiChiTiet(List<TheLoaiChiTiet> lstTheLoaiChiTiet) {
        return _sachRepo.updateTheLoaiChiTiet(lstTheLoaiChiTiet);
    }

    @Override
    public boolean updateSoLuongSach(String id, int soLuong) {
        return _sachRepo.updateSoLuongSach(id, soLuong);
    }

    @Override
    public boolean updateSoLuongSach(List<Sach> lstSachUpdate) {
        return _sachRepo.updateSoLuongSach(lstSachUpdate);
    }

    @Override
    public List<Sach> selectAllLowerThan(int soLuong) {
        return _sachRepo.selectAllLowerThan(soLuong);
    }

    @Override
    public Sach selectUpdateByMa(Sach sach) {
        return _sachRepo.selectUpdateSach(sach);
    }

    @Override
    public boolean insertSach(List<Sach> lstSach) {
        return _sachRepo.insertSach(lstSach);
    }

    @Override
    public Sach getSachCheck(Sach sach) {
        return _sachRepo.getSachCheck(sach);
    }
}
