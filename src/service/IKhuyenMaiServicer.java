/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import java.util.UUID;
import model.KhuyenMai;

/**
 *
 * @author adm
 */
public interface IKhuyenMaiServicer {

    boolean insert(KhuyenMai km);
    List<KhuyenMai> selectAll(String ma);
    public void delete(String id);
    boolean update(KhuyenMai km);
    public String findById(String ma);

}
