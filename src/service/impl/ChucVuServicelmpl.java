/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.ChucVu;
import service.ChucVuService;
import repository.ChucVuRepository;
import repository.impl.ChucVuRepositorylmpl;

/**
 *
 * @author ECO
 */
public class ChucVuServicelmpl implements ChucVuService {

    private final ChucVuRepository repository = new ChucVuRepositorylmpl();
    private List<ChucVu> listChucVu;

    @Override
    public String insert(ChucVu cv) {
        boolean insertSuccess = repository.insert(cv);
        return insertSuccess ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public String update(ChucVu cv) {
        boolean insertSuccess = repository.update(cv);
        return insertSuccess ? "Update thành công" : "Update thất bại";
    }

    @Override
    public List<ChucVu> selectAll() {
        listChucVu = new ArrayList<>();
        var ems = repository.selectAll();
        for (ChucVu em : ems) {
            listChucVu.add(new ChucVu(em.getId(), em.getMa(), em.getTen(), em.getMoTa()));
        }
        return listChucVu;
    }

    @Override
    public List<ChucVu> SelectbyName(String name) {
        listChucVu = new ArrayList<>();
        var ems = repository.SelectbyName(name);
        for (ChucVu em : ems) {
            listChucVu.add(new ChucVu(em.getId(), em.getMa(), em.getTen(), em.getMoTa()));
        }
        return listChucVu;
    }

}
