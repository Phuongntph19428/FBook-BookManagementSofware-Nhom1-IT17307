/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.Sach;

/**
 *
 * @author ppolo
 */
public interface SachService {

    public boolean insertSach(Sach sach);

    public boolean updateSach(Sach sach);

    public List<Sach> getList(int position, int pageSize);
    
    public int countAllSach();
    
    public List<Sach> getListByKeyword(String keyword);

}
