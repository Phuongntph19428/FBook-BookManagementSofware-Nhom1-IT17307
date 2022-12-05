/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import custommodel.ThuChiModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dieu Linh
 */
public interface ThuChiRepository {

    List<String> cbbNhanVien();

    List<String> cbbKhachHang();

    String findById(String ten);
//    List<ThuChiModel> dsThuChi(int loaiThuChi, Date ngayBatDau, Date ngayKetThuc, int loaiNguoi);

    List<ThuChiModel> getThuNhanVien(String idNhanVien, Date ngayBatDau, Date ngayKetThuc);

    List<ThuChiModel> getKhachHang(String idNhanVien, Date ngayBatDau, Date ngayKetThuc);

    List<ThuChiModel> tienChi(Date ngayBatDau, Date ngayKetThuc);
}
