/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import model.HoaDon;
import model.HoaDonChiTiet;

/**
 *
 * @author ppolo
 */
public interface HoaDonRepository {

    public boolean taoHoaDon(HoaDon hoaDon);

    public boolean deleteHoaDonChiTiet(HoaDon hoaDon);

    public boolean removeHoaDonChiTiet(HoaDonChiTiet hoaDonCT);

    public boolean insertHoaDonChiTiet(HoaDonChiTiet hoaDonCT);

    public boolean updateHoaDonChiTiet(HoaDonChiTiet hoaDonCT);

    public boolean updateHoaDon(HoaDon hoaDon);

    public String getLastHoaDon();

    public List<HoaDon> sellectAll();

    public List<HoaDon> sellectAllHoaDonCho();

    public List<HoaDon> sellectAllHoaDonDangVanChuyen();

    public List<HoaDon> sellectAllHoaDonDaHuy();

    public List<HoaDon> selectAllHoaDonDaThanhToan();

    public List<HoaDonChiTiet> getAllByMaHD(String maHD);

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien3();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong3();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien2();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong2();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien1();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong1();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien0();

    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong0();

}
