/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.Date;
import java.util.List;
import model.PhieuNhap;

/**
 *
 * @author Dieu Linh
 */
public interface PhieuNhapService {

    List<PhieuNhap> getAllPhieuNhap();

    List<PhieuNhap> getPhieuChuaNhap();

    List<PhieuNhap> getPhieuDaNhap();

    boolean addphieuNhap(PhieuNhap phieuNhap);

    PhieuNhap getByMa(String ma);

    List<String> cbbNcc();

    String xoa(String id);

    String findById(String ten);

    boolean updatePhieuNhap(PhieuNhap phieuNhap);

    PhieuNhap getUpdateMa(PhieuNhap phieuNhap);

    List<PhieuNhap> selectByDay(Date date);

}
