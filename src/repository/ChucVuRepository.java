/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.ChucVu;

/**
 *
 * @author ECO
 */
public interface ChucVuRepository {

    boolean insert(ChucVu cv);

    boolean update(ChucVu cv);

    List<ChucVu> selectAll();
}
