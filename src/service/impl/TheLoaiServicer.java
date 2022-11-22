/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;

import model.TheLoai;
import service.ITheLoaiServicer;
import repository.ITheLoaiRepository;
import repository.impl.TheLoaiRepository;

/**
 *
 * @author adm
 */
public class TheLoaiServicer implements ITheLoaiServicer {

    private final ITheLoaiRepository repository = new TheLoaiRepository();
    private List<TheLoai> listKhuyenMai;

    @Override
    public boolean insert(TheLoai tl) {
        return repository.insert(tl);
    }

    @Override
    public boolean update(TheLoai tl) {
        return repository.update(tl);
    }

    @Override
    public List<TheLoai> selectAll() {
        listKhuyenMai = new ArrayList<>();
        var ems = repository.selectAll();
        for (TheLoai em : ems) {
            listKhuyenMai.add(new TheLoai(em.getId().toString(), em.getMa(), em.getTen(), em.getMoTa()));
        }
        return listKhuyenMai;
    }

    @Override
    public List<TheLoai> selectTimKiem(String ma) {
        return repository.selectTimKiem(ma);
    }

}
