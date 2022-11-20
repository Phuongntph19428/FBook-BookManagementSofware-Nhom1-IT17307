/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.TheLoai;

/**
 *
 * @author adm
 */
public interface ITheLoaiRepository {

    boolean insert(TheLoai km);

    boolean update(TheLoai km);

    public List<TheLoai> selectAll();
}
