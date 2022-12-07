/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.Date;
import java.util.List;
import model.PhieuNhap;
import repository.PhieuNhapRepository;
import repository.impl.PhieuNhapRepositoryImpl;
import service.PhieuNhapService;

/**
 *
 * @author Dieu Linh
 */
public class PhieuNhapServiceImpl implements PhieuNhapService {

    private final PhieuNhapRepository PhieuNhapRepository = new PhieuNhapRepositoryImpl();

    @Override
    public List<PhieuNhap> getAllPhieuNhap() {
        return PhieuNhapRepository.getAllPhieuNhap();
    }

    @Override
    public List<PhieuNhap> getPhieuChuaNhap() {
        return PhieuNhapRepository.getPhieuChuaNhap();
    }

    @Override
    public List<PhieuNhap> getPhieuDaNhap() {
        return PhieuNhapRepository.getPhieuDaNhap();
    }

    @Override
    public boolean addphieuNhap(PhieuNhap phieuNhap) {
        return PhieuNhapRepository.addphieuNhap(phieuNhap);
    }

    @Override
    public PhieuNhap getByMa(String ma) {
        return PhieuNhapRepository.getByMa(ma);
    }

    @Override
    public List<String> cbbNcc() {
        return PhieuNhapRepository.cbbNcc();
    }

    @Override
    public String xoa(String id) {
        return PhieuNhapRepository.xoa(id);
    }

    @Override
    public String findById(String ten) {
        return PhieuNhapRepository.findById(ten);
    }

    @Override
    public boolean updatePhieuNhap(PhieuNhap phieuNhap) {
        return PhieuNhapRepository.updatePhieuNhap(phieuNhap);
    }

    @Override
    public PhieuNhap getUpdateMa(PhieuNhap phieuNhap) {
        return PhieuNhapRepository.getUpdateMa(phieuNhap);
    }

    @Override
    public List<PhieuNhap> selectByDay(Date date) {
        return PhieuNhapRepository.selectByDay(date);
    }

}
