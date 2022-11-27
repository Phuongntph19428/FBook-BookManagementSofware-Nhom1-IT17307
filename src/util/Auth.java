/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import model.ChucVu;
import model.NhanVien;

/**
 *
 * @author ppolo
 */
public class Auth {
    private static final ChucVu chucVu = new ChucVu("B2FA6082-4A0C-44B1-A094-40131326A1DA", "CV01", "Quản lý", null);
    private static final NhanVien nhanVien = new NhanVien("2D687099-FC5F-439D-A93D-019423C45BFF", chucVu, "NV01", "A", "Nguyễn", "Văn", "Nam", null,
            "A@gmail.com", "0123456", "Hà Nội", "123456", null, 1);

    public static NhanVien getNhanVien() {
        return nhanVien;
    }
}
