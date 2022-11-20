/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.KhuyenMai;

/**
 *
 * @author adm
 */
public interface IKhuyenMaiServicer {

    boolean insert(KhuyenMai km);

   boolean update(KhuyenMai km);

    List<KhuyenMai> selectAll();
}
