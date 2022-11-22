/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import java.util.UUID;
import model.PhieuNhap;

/**
 *
 * @author Dieu Linh
 */
public interface PhieuNhapRepository {

    List<PhieuNhap> getAllPhieuNhap();

    boolean addphieuNhap(PhieuNhap phieuNhap);

    List<PhieuNhap> search(String ma);

    List<String> cbbNcc();

    String xoa(String id);

    String findById(String ten);

    boolean updatePhieuNhap(PhieuNhap phieuNhap);
}
