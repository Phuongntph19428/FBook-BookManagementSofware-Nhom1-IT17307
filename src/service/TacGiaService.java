/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.TacGia;

/**
 *
 * @author ppolo
 */
public interface TacGiaService {

    public List<TacGia> selectAll();

    public boolean insertTacGia(TacGia tacGia);

    public boolean updateTacGia(TacGia tacGia);

    public List<TacGia> searchTacGiaByKeyWord(List<TacGia> lstTacGia, String keyword);
}
