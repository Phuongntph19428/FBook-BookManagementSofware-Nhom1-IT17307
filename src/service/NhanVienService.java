/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.NhanVien;

/**
 *
 * @author ppolo
 */
public interface NhanVienService {

    String addNhanVien(NhanVien nhanVien);

    List<NhanVien> getAllNhanVien();

    List<NhanVien> getListbyName(String name);

    NhanVien getNhanVienById(String Id);

    String updateNhanVien(NhanVien nhanVien);
    
    NhanVien getNhanVienByEmail(String Email);

}
