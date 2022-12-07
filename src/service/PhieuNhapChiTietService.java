/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.PhieuNhapChiTiet;

/**
 *
 * @author Dieu Linh
 */
public interface PhieuNhapChiTietService {

    List<PhieuNhapChiTiet> getAllPhieuNhapChiTiet();

    boolean themPNCT(PhieuNhapChiTiet phieuNhapChiTiet);
    
    boolean themPhieuNhapCT(List<PhieuNhapChiTiet> lstPhieuNhapCT);

    boolean suaPNCT(PhieuNhapChiTiet phieuNhapChiTiet);

    String xoaPNCT(String id);
}
