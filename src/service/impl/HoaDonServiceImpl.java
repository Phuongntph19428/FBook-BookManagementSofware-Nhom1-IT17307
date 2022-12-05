/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.PhieuNhapChiTiet;
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
        _hoaDonRepo = new HoaDonRepositoryImpl();
    }

    @Override
    public boolean taoHoaDon(HoaDon hoaDon) {
        return _hoaDonRepo.taoHoaDon(hoaDon);
    }

    @Override
    public HoaDon getByMaHD(String maHD) {
        return _hoaDonRepo.getByMaHD(maHD);
    }
    
    @Override
    public boolean removeAllHoaDonCT(HoaDon hoaDon) {
        return _hoaDonRepo.deleteHoaDonChiTiet(hoaDon);
    }

    @Override
    public boolean removeHoaDonChiTiet(HoaDonChiTiet hoaDonCT) {
        return _hoaDonRepo.removeHoaDonChiTiet(hoaDonCT);
    }

    @Override
    public boolean insertHoaDonChiTiet(HoaDonChiTiet hoaDonCT) {
        return _hoaDonRepo.insertHoaDonChiTiet(hoaDonCT);
    }

    @Override
    public boolean updateHoaDonChiTiet(HoaDonChiTiet hoaDonCT) {
        return _hoaDonRepo.updateHoaDonChiTiet(hoaDonCT);
    }

    @Override
    public boolean updateHoaDon(HoaDon hoaDon) {
        return _hoaDonRepo.updateHoaDon(hoaDon);
    }

    @Override
    public int genMaHoaDon() {
        String lastHD = _hoaDonRepo.getLastHoaDon();
        System.out.println(lastHD);
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

    @Override
    public List<HoaDonChiTiet> getAllByMaHD(String maHD) {
        return _hoaDonRepo.getAllByMaHD(maHD);
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien3() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsTongTien3();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong3() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsCoutSoLuong3();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien2() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsTongTien2();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong2() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsCoutSoLuong2();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien1() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsTongTien1();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong1() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsCoutSoLuong1();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien0() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsTongTien0();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong0() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsCoutSoLuong0();
    }

    @Override
    public boolean updateHoaDonChiTiet(List<HoaDonChiTiet> lstHoaDonCT, HoaDon hoaDon) {
        return _hoaDonRepo.updateHoaDonChiTiet(lstHoaDonCT, hoaDon);
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoTron() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsThongKeSachBieuDoTron();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien4() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsTongTien4();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong4() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsCoutSoLuong4();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThuHienThiNgay() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThuHienThiNgay();
    }

    @Override
    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi1() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi1();
    }

    @Override
    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi2() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi2();
    }

    @Override
    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi3() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi3();
    }

    @Override
    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi4() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi4();
    }

    @Override
    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi5() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi5();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu1() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu1();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu2() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu2();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu3() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu3();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu4() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu4();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu5() {
        return _hoaDonRepo.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu5();
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeBieuDoTron() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
