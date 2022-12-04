/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.TacGia;
import repository.TacGiaRepository;
import repository.impl.TacGiaRepositoryImpl;
import service.TacGiaService;

/**
 *
 * @author ppolo
 */
public class TacGiaServiceImpl implements TacGiaService {

    private final TacGiaRepository _tacGiaRepo;

    public TacGiaServiceImpl() {
        _tacGiaRepo = new TacGiaRepositoryImpl();
    }

    @Override
    public List<TacGia> selectAll() {
        return _tacGiaRepo.selectAll();
    }

    @Override
    public boolean insertTacGia(TacGia tacGia) {
        return _tacGiaRepo.insertTacGia(tacGia);
    }

    @Override
    public boolean updateTacGia(TacGia tacGia) {
        return _tacGiaRepo.updateTacGia(tacGia);
    }

    @Override
    public List<TacGia> searchTacGiaByKeyWord(List<TacGia> lstTacGia, String keyword) {
        List<TacGia> lst = new ArrayList<>();
        for (TacGia tacGia : lstTacGia) {
            if(tacGia.getTen().toLowerCase().contains(keyword.toLowerCase()) || tacGia.getMa().toLowerCase().contains(keyword.toLowerCase())) {
                lst.add(tacGia);
            }
        }
        return lst;
    }

    @Override
    public TacGia sellectByMa(String ma) {
        return _tacGiaRepo.sellectByMa(ma);
    }

    @Override
    public TacGia selectUpdateByMa(TacGia tacGia) {
        return _tacGiaRepo.selectUpdateByMa(tacGia);
    }

}
