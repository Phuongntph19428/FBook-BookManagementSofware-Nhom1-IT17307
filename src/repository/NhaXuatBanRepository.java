/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;
import java.util.List;
import model.NhaXuatBan;
/**
 *
 * @author ECO
 */
public interface NhaXuatBanRepository {
    
    boolean insert(NhaXuatBan nxb);

    boolean update(NhaXuatBan nxb);

    List<NhaXuatBan> selectAll();
}
