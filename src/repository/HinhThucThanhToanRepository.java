/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import model.HinhThucThanhToan;

/**
 *
 * @author ppolo
 */
public interface HinhThucThanhToanRepository {
    
    public boolean addHinhThucThanhToan(List<HinhThucThanhToan> lstHinhThucTT);
    
    public boolean updateHinhThucThanhToan(List<HinhThucThanhToan> lstHinhThucTT);
    
}
