/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.NhaXuatBan;
import service.NhaXuatBanService;
import repository.NhaXuatBanRepository;
import repository.impl.NhaXuatBanRepositorylmpl;

/**
 *
 * @author ECO
 */
public class NhaXuatBanServicelmpl implements NhaXuatBanService {

    private final NhaXuatBanRepository repository = new NhaXuatBanRepositorylmpl();
    private List<NhaXuatBan> listNhaXuatBan;

    @Override
    public String insert(NhaXuatBan nxb) {
        boolean insertSuccess = repository.insert(nxb);
        return insertSuccess ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public String update(NhaXuatBan nxb) {
        boolean insertSuccess = repository.update(nxb);
        return insertSuccess ? "Update thành công" : "Update thất bại";
    }

    @Override
    public List<NhaXuatBan> selectAll() {
        listNhaXuatBan = new ArrayList<>();
        System.out.println(listNhaXuatBan.size());
        var ems = repository.selectAll();
        for (NhaXuatBan em : ems) {
            listNhaXuatBan.add(new NhaXuatBan(em.getId(), em.getMa(), em.getTen(), em.getMoTa()));
        }
        return listNhaXuatBan;
    }

    @Override
    public List<NhaXuatBan> SelectbyName(String name) {
        listNhaXuatBan = new ArrayList<>();
        var ems = repository.SelectbyName(name);
        for (NhaXuatBan em : ems) {
            listNhaXuatBan.add(new NhaXuatBan(em.getId(), em.getMa(), em.getTen(), em.getMoTa()));
        }
        return listNhaXuatBan;
    }

}
