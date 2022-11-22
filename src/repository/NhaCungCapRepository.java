/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.NhaCungCap;

/**
 *
 * @author Nam Anh
 */
public interface NhaCungCapRepository {

    boolean addNhaCungCap(NhaCungCap ncc);

    List<NhaCungCap> getAllNhaCungCap();

    public boolean updateNhaCungCap(NhaCungCap nhaCungCap);
}
