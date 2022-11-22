/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.NhaCungCap;
import repository.NhaCungCapRepository;
import repository.impl.NhaCungCapRepositoryImpl;
import service.NhaCungCapService;

/**
 *
 * @author ppolo
 */
public class NhaCungCapServiceImpl implements NhaCungCapService {

    private static NhaCungCapRepository nhaCungCapRepository = new NhaCungCapRepositoryImpl();

    @Override
    public String addNhaCungCap(NhaCungCap ncc) {
        boolean result = nhaCungCapRepository.addNhaCungCap(ncc);
        return result ? "Thêm nhân viên thành công" : "Thêm nhân viên thất bại";
    }

    @Override
    public List<NhaCungCap> getAllNhaCungCap() {
        return nhaCungCapRepository.getAllNhaCungCap();
    }

    @Override
    public String updateNhaCungCap(NhaCungCap nhaCungCap) {
        boolean result = nhaCungCapRepository.updateNhaCungCap(nhaCungCap);
        return result ? "Update nhà cung cấp thành công" : "Update nhà cung cấp thất bại";
    }

}
