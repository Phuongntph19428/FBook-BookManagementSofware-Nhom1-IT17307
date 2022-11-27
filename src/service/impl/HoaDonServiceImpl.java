/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.HoaDon;
import model.HoaDonChiTiet;
import repository.HoaDonRepository;
import repository.impl.HoaDonRepositoryImpl;
import service.HoaDonService;

/**
 *
 * @author ppolo
 */
public class HoaDonServiceImpl implements HoaDonService {

    private final HoaDonRepository _hoaDonRepo;

    public HoaDonServiceImpl() {
        _hoaDonRepo = new  HoaDonRepositoryImpl();
    }

    @Override
    public boolean taoHoaDon(HoaDon hoaDon) {
        return _hoaDonRepo.taoHoaDon(hoaDon);
    }

    @Override
    public boolean removeAllHoaDonCT(HoaDon hoaDon) {
        return _hoaDonRepo.deleteHoaDonChiTiet(hoaDon);
    }

    @Override
    public boolean insertHoaDonChiTiet(HoaDonChiTiet hoaDonCT) {
        return _hoaDonRepo.insertHoaDonChiTiet(hoaDonCT);
    }

    @Override
    public boolean updateHoaDon(HoaDon hoaDon) {
        return _hoaDonRepo.updateHoaDon(hoaDon);
    }

    @Override
    public int genMaHoaDon() {
        String lastHD = _hoaDonRepo.getLastHoaDon();
        int maHoaDonInt = Integer.parseInt(lastHD.substring(2));
        return maHoaDonInt + 1;
    }

    @Override
    public List<HoaDon> sellectAll() {
        return _hoaDonRepo.sellectAll();
    }

    @Override
    public List<HoaDon> sellectAllHoaDonCho() {
        return _hoaDonRepo.sellectAllHoaDonCho();
    }

    @Override
    public List<HoaDon> sellectAllHoaDonDangVanChuyen() {
        return _hoaDonRepo.sellectAllHoaDonDangVanChuyen();
    }

    @Override
    public List<HoaDon> sellectAllHoaDonDaHuy() {
        return _hoaDonRepo.sellectAllHoaDonDaHuy();
    }

    @Override
    public List<HoaDon> selectAllHoaDonDaThanhToan() {
        return _hoaDonRepo.selectAllHoaDonDaThanhToan();
    }

}
