/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.TheLoai;

/**
 *
 * @author adm
 */
public interface ITheLoaiServicer {

    boolean insert(TheLoai tl);

    boolean update(TheLoai tl);

    List<TheLoai> selectAll();
}
