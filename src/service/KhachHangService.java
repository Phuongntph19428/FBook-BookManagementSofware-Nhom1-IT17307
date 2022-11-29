/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.KhachHang;

/**
 *
 * @author ppolo
 */
public interface KhachHangService {

    public List<KhachHang> selectAll();

    public boolean insertKhachHang(KhachHang khachHang);

    public boolean updateKhachHang(KhachHang khachHang);

    public List<KhachHang> searchKhachHangByKeyWord(List<KhachHang> lstKhachHang, String keyword);
    
    public List<KhachHang> sellectAllBySDT(String sdt);
}
