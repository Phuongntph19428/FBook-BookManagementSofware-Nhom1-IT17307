/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.KhuyenMai;
import model.KhuyenMaiChiTiet;

/**
 *
 * @author adm
 */
public interface IKhuyenMaiChiTietRepository {

    public List<KhuyenMai> selectAll();

    public List<KhuyenMaiChiTiet> selectAllChiKhuyenMais();

    public void delete(String id);

    public void delete();

    boolean insert(KhuyenMaiChiTiet km);

    boolean update(KhuyenMaiChiTiet km);
    
      List<KhuyenMaiChiTiet> selectAll(String id);
}
