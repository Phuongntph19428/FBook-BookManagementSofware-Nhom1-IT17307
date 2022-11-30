/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.KhuyenMai;
import model.KhuyenMaiChiTiet;
import service.IKhuyenMaiChiTietService;
import repository.IKhuyenMaiChiTietRepository;
import repository.impl.KhuyenMaiChiTietRepository;

/**
 *
 * @author adm
 */
public class KhuyenMaiChiTietService implements IKhuyenMaiChiTietService {

    private final IKhuyenMaiChiTietRepository repository = new KhuyenMaiChiTietRepository();
    private List<KhuyenMai> listKhuyenMai;
    private List<KhuyenMaiChiTiet> listKhuyenMaiChiTiet;

    @Override
    public List<KhuyenMai> selectAll() {
        listKhuyenMai = new ArrayList<>();
        var ems = repository.selectAll();
        for (KhuyenMai em : ems) {
            listKhuyenMai.add(new KhuyenMai(em.getId().toString(), em.getMa(), em.getTen(), em.getChietKhau(), em.getNgayBatDau(), em.getNgayKetThuc(), em.getTrangThai(), em.getMoTa()));
        }
        return listKhuyenMai;
    }

    @Override
    public List<KhuyenMaiChiTiet> selectAllChiKhuyenMais() {
        listKhuyenMaiChiTiet = new ArrayList<>();
        var ems = repository.selectAllChiKhuyenMais();
        for (KhuyenMaiChiTiet em : ems) {
            listKhuyenMaiChiTiet.add(new KhuyenMaiChiTiet(em.getKhuyenMai(), em.getSach()));
        }
        return listKhuyenMaiChiTiet;

    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public boolean update(KhuyenMaiChiTiet km) {
        return repository.update(km);
    }

    @Override
    public void delete() {
        repository.delete();
    }

    @Override
    public boolean insert(KhuyenMaiChiTiet km) {
        return repository.insert(km);
    }

    @Override
    public List<KhuyenMaiChiTiet> selectAll(String id) {
        return repository.selectAll(id);
    }

}
