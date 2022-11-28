/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import custommodel.CustomSach;
import java.util.List;
import repository.CustomSachRepository;
import repository.impl.CustomSachRepositoryImpl;
import service.CustomSachService;

/**
 *
 * @author ppolo
 */
public class CustomSachServiceImpl implements CustomSachService {

    private final CustomSachRepository _customSachRepo;

    public CustomSachServiceImpl() {
        _customSachRepo = new CustomSachRepositoryImpl();
    }

    @Override
    public List<CustomSach> getList(int position, int pageSize) {
        return _customSachRepo.getList(position, pageSize);
    }

    @Override
    public List<CustomSach> getAllByKeyword(String keyword) {
        return _customSachRepo.getAllByKeyword(keyword);
    }
    
    @Override
    public int countAllSach() {
        return _customSachRepo.countAllSach();
    }

}
