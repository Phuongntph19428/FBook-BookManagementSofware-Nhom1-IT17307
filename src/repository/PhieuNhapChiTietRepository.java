/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import repository.impl.*;
import java.util.List;
import model.PhieuNhapChiTiet;

/**
 *
 * @author Dieu Linh
 */
public interface PhieuNhapChiTietRepository {

    List<PhieuNhapChiTiet> getAllPhieuNhapChiTiet();

    boolean themPNCT(PhieuNhapChiTiet phieuNhapChiTiet);

    boolean suaPNCT(PhieuNhapChiTiet phieuNhapChiTiet);

    String xoaPNCT(String id);
}
