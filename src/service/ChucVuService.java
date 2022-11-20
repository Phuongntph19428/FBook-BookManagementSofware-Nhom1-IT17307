/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.ChucVu;

/**
 *
 * @author ECO
 */
public interface ChucVuService {

    String insert(ChucVu cv);

    String update(ChucVu cv);

    List<ChucVu> selectAll();
}
