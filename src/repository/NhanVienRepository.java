/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import model.NhanVien;

/**
 *
 * @author ppolo
 */
public interface NhanVienRepository {
    boolean addNhanVien(NhanVien nhanVien);
    
    List<NhanVien> getAllNhanVien();
    
    List<NhanVien> getListNhanVienByName(String name);
}
