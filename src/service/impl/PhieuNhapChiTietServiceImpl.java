/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.PhieuNhapChiTiet;
import repository.PhieuNhapChiTietRepository;
import repository.impl.PhieuNhapChiTietRepositoryImpl;
import service.PhieuNhapChiTietService;

/**
 *
 * @author Dieu Linh
 */
public class PhieuNhapChiTietServiceImpl implements PhieuNhapChiTietService {

    private PhieuNhapChiTietRepository PhieuNhapChiTietRepository = new PhieuNhapChiTietRepositoryImpl();

    @Override
    public List<PhieuNhapChiTiet> getAllPhieuNhapChiTiet() {
        return PhieuNhapChiTietRepository.getAllPhieuNhapChiTiet();
    }

    @Override
    public boolean themPNCT(PhieuNhapChiTiet phieuNhapChiTiet) {
        return PhieuNhapChiTietRepository.themPNCT(phieuNhapChiTiet);
    }

    @Override
    public boolean suaPNCT(PhieuNhapChiTiet phieuNhapChiTiet) {
        return PhieuNhapChiTietRepository.suaPNCT(phieuNhapChiTiet);
    }

    @Override
    public String xoaPNCT(String id) {
        return PhieuNhapChiTietRepository.xoaPNCT(id);
    }

    @Override
    public boolean themPhieuNhapCT(List<PhieuNhapChiTiet> lstPhieuNhapCT) {
        return PhieuNhapChiTietRepository.themPhieuNhapCT(lstPhieuNhapCT);
    }

}
