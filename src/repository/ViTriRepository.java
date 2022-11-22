/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.ViTri;

/**
 *
 * @author Dieu Linh
 */
public interface ViTriRepository {

    List<ViTri> getAllViTri();

    boolean addViTri(ViTri viTri);

    boolean updateViTri(ViTri viTri);

    List<ViTri> search(String ma);
}
