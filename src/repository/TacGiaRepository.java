/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
<<<<<<< HEAD
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.TacGia;

/**
 *
 * @author ppolo
 */
public interface TacGiaRepository {

    public List<TacGia> selectAll();
    
    public boolean insertTacGia(TacGia tacGia);
    
    public boolean updateTacGia(TacGia tacGia);
    
    public List<TacGia> searchTacGiaByKeyWord(String keyword);
    
    public TacGia sellectByMa(String ma);
    
    public TacGia selectUpdateByMa(TacGia tacGia);
    
}
