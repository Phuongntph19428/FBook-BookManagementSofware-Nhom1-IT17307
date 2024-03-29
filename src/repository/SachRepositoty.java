/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import model.Sach;
import model.SachTacGia;
import model.TheLoaiChiTiet;

/**
 *
 * @author ppolo
 */
public interface SachRepositoty {

    public boolean insertSach(Sach sach);
    
    public boolean insertSach(List<Sach> lstSach);
    
    public boolean updateSach(Sach sach);
    
    public boolean updateSoLuongSach(String id, int soLuong);
    
    public boolean updateSoLuongSach(List<Sach> lstSachUpdate);
    
    public Sach getSachByMa(String ma);
    
    public Sach getSachCheck(Sach sach);
    
    public List<Sach> getList(int position, int pageSize);
    
    public int countAllSach();
    
    public List<Sach> getListByKeyword(String keyword);  
    
    public boolean updateSachTacGia(List<SachTacGia> lstSachTacGia);
    
    public boolean updateTheLoaiChiTiet(List<TheLoaiChiTiet> lstTheLoaiChiTiet);
    
    public List<Sach> selectAllLowerThan(int soLuong); 
    
    public Sach selectUpdateSach(Sach sach);
    
}
