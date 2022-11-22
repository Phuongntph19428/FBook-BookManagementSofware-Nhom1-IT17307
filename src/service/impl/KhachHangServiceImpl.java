/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.KhachHang;
import repository.KhachHangRepository;
import repository.impl.KhachHangRepositoryImpl;
import service.KhachHangService;

/**
 *
 * @author ppolo
 */
public class KhachHangServiceImpl implements KhachHangService {

    private final KhachHangRepository _khachHangRepo;

    public KhachHangServiceImpl() {
        _khachHangRepo = new KhachHangRepositoryImpl();
    }

    @Override
    public List<KhachHang> selectAll() {
        return _khachHangRepo.selectAll();
    }

    @Override
    public boolean insertKhachHang(KhachHang khachHang) {
        return _khachHangRepo.insertKhachHang(khachHang);
    }

    @Override
    public boolean updateKhachHang(KhachHang khachHang) {
        return _khachHangRepo.updateKhachHang(khachHang);
    }

    @Override
    public List<KhachHang> searchKhachHangByKeyWord(List<KhachHang> lstKhachHang, String keyword) {
        List<KhachHang> lst = new ArrayList<>();
        for (KhachHang khachHang : lstKhachHang) {
            if ((khachHang.getTen() + " " + khachHang.getHo() + " " + khachHang.getTenDem()).toLowerCase().contains(keyword.toLowerCase())
                    || khachHang.getMa().toLowerCase().contains(keyword.toLowerCase()) || khachHang.getSdt().contains(keyword)) {
                lst.add(khachHang);
            }
        }
        return lst;
    }
}
