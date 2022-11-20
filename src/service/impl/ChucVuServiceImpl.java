/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.ChucVu;
import repository.ChucVuRepository;
import repository.impl.ChucVuRepositoryImpl;
import service.ChucVuService;

/**
 *
 * @author ppolo
 */
public class ChucVuServiceImpl implements ChucVuService {

    private static ChucVuRepository chucVuRepository = new ChucVuRepositoryImpl();

    @Override
    public List<ChucVu> getAllChuVu() {
        return chucVuRepository.getAllChucVu();
    }

}
