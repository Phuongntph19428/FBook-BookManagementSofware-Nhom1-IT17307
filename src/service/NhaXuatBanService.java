/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.NhaXuatBan;

/**
 *
 * @author ECO
 */
public interface NhaXuatBanService {

    String insert(NhaXuatBan nxb);

    String update(NhaXuatBan nxb);

    List<NhaXuatBan> selectAll();

    List<NhaXuatBan> SelectbyName(String name);
}
