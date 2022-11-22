/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.ViTri;
import repository.ViTriRepository;
import repository.impl.ViTriRepositoryImpl;
import service.ViTriService;

/**
 *
 * @author Dieu Linh
 */
public class ViTriServiceImpl implements ViTriService {

    private ViTriRepository ViTriRepository = new ViTriRepositoryImpl();

    @Override
    public List<ViTri> getAllViTri() {
        return ViTriRepository.getAllViTri();
    }

    @Override
    public boolean addViTri(ViTri viTri) {
        return ViTriRepository.addViTri(viTri);
    }

    @Override
    public boolean updateViTri(ViTri viTri) {
        return ViTriRepository.updateViTri(viTri);
    }

    @Override
    public List<ViTri> search(String ma) {
        return ViTriRepository.search(ma);
    }

}
