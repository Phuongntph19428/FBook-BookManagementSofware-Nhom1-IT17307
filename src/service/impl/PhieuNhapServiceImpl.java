/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import java.util.UUID;
import model.PhieuNhap;
import repository.PhieuNhapRepository;
import repository.impl.PhieuNhapRepositoryImpl;
import service.PhieuNhapService;

/**
 *
 * @author Dieu Linh
 */
public class PhieuNhapServiceImpl implements PhieuNhapService {

    private PhieuNhapRepository PhieuNhapRepository = new PhieuNhapRepositoryImpl();

    @Override
    public List<PhieuNhap> getAllPhieuNhap() {
        return PhieuNhapRepository.getAllPhieuNhap();
    }

    @Override
    public boolean addphieuNhap(PhieuNhap phieuNhap) {
        return PhieuNhapRepository.addphieuNhap(phieuNhap);
    }

    @Override
    public List<PhieuNhap> listSearch(String ma) {
        return PhieuNhapRepository.search(ma);
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

}
