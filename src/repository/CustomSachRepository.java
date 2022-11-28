/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import custommodel.CustomSach;
import java.util.List;

/**
 *
 * @author ppolo
 */
public interface CustomSachRepository {

    public List<CustomSach> getList(int position, int pageSize);
    
    public List<CustomSach> getAllByKeyword(String keyword); 
    
    public int countAllSach();

}
