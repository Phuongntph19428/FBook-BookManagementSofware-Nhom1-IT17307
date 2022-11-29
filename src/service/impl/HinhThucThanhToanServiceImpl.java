/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.HinhThucThanhToan;
import repository.HinhThucThanhToanRepository;
import repository.impl.HinhThucThanhToanRepositoryImpl;
import service.HinhThucThanhToanService;

/**
 *
 * @author ppolo
 */
public class HinhThucThanhToanServiceImpl implements HinhThucThanhToanService {
    
    private final HinhThucThanhToanRepository _hinhThucThanhToanRepo;
    
    public HinhThucThanhToanServiceImpl() {
        _hinhThucThanhToanRepo = new HinhThucThanhToanRepositoryImpl();
    }

    @Override
    public boolean addHinhThucThanhToan(List<HinhThucThanhToan> lstHinhThucTT) {
        return _hinhThucThanhToanRepo.addHinhThucThanhToan(lstHinhThucTT);
    }

    @Override
    public boolean updateHinhThucThanhToan(List<HinhThucThanhToan> lstHinhThucTT) {
        return _hinhThucThanhToanRepo.updateHinhThucThanhToan(lstHinhThucTT);
    }

}
