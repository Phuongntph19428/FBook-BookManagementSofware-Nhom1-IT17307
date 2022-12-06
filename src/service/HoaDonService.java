/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.PhieuNhapChiTiet;

/**
 *
 * @author ppolo
 */
public interface HoaDonService {

    public boolean taoHoaDon(HoaDon hoaDon);

    public boolean removeAllHoaDonCT(HoaDon hoaDon);

    public boolean removeHoaDonChiTiet(HoaDonChiTiet hoaDonCT);

    public boolean insertHoaDonChiTiet(HoaDonChiTiet hoaDonCT);

    public boolean updateHoaDonChiTiet(HoaDonChiTiet hoaDonCT);

    public boolean updateHoaDonChiTiet(List<HoaDonChiTiet> lstHoaDonCT, HoaDon hoaDon);

    public boolean updateHoaDon(HoaDon hoaDon);

    public int genMaHoaDon();

    public List<HoaDon> sellectAll();

    public List<HoaDon> sellectAllHoaDonCho();

    public List<HoaDon> sellectAllHoaDonDangVanChuyen();

    public List<HoaDon> sellectAllHoaDonDaHuy();

    public List<HoaDon> selectAllHoaDonDaThanhToan();

    public HoaDon getByMaHD(String maHD);

    public List<HoaDonChiTiet> getAllByMaHD(String maHD);

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien3();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong3();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien2();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong2();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien1();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong1();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien0();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong0();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeBieuDoTron();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoTron();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu1();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu2();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu3();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu4();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu5();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien4();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong4();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThuHienThiNgay();

    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi1();

    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi2();

    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi3();

    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi4();

    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi5();
}
