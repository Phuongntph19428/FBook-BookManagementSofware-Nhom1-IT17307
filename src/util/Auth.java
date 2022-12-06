/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import model.ChucVu;
import model.NhanVien;
import repository.NhanVienRepository;
import repository.impl.NhanVienRepositoryImpl;

/**
 *
 * @author ppolo
 */
public class Auth {
    private static final ChucVu chucVu = new ChucVu("B2FA6082-4A0C-44B1-A094-40131326A1DA", "CV01", "Quản lý", null);
    private static NhanVien nhanVien = new NhanVien("2D687099-FC5F-439D-A93D-019423C45BFF", chucVu, "NV01", "A", "Nguyễn", "Văn", "Nam", null,
            "A@gmail.com", "0123456", "Hà Nội", "123456", null, 1);
    public static final String QUAN_LY = "CV01";
    public static final String NHAN_VIEN_BAN_HANG = "CV02S";
    
    private static NhanVien nVien;
    private static ChucVu cVu;
    
    NhanVienRepository nvRepo = new NhanVienRepositoryImpl();
    
    public static NhanVien getNhanVien() {
        return nhanVien;
    }
    
    public static void setNhanVien(NhanVien nv){
        nhanVien = nv;
    }
    
    public static void setChucVu(ChucVu cv){
        cVu = cv;
    }
    public boolean getNhanVieninDatabase(String userName , String passWord){
        NhanVien nhanVien1 = nvRepo.getNhanVien(userName, passWord);
        if(nhanVien1 == null){
            return false;
        }
        setNhanVien(nhanVien1);
        setChucVu(nhanVien1.getChucVu());
        return true;
    }
}
